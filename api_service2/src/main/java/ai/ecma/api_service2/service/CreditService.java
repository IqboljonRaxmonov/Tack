package ai.ecma.api_service2.service;

import ai.ecma.api_service2.entity.Credit;
import ai.ecma.api_service2.entity.CreditInfo;
import ai.ecma.api_service2.payload.ApiResponse;
import ai.ecma.api_service2.payload.CreditDto;
import ai.ecma.api_service2.payload.RequestDto;
import ai.ecma.api_service2.repository.CreditInfoRepository;
import ai.ecma.api_service2.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CreditService {

    @Autowired
    CreditRepository creditRepository;
    @Autowired
    CreditInfoRepository creditInfoRepository;

    public ApiResponse getCredit(HttpServletRequest httpServletRequest, RequestDto requestDto) {
        RestTemplate restTemplate = new RestTemplate();
        String url = makeUrlWithParams("http://localhost/api/user/getUserId?", requestDto);
        HttpEntity request = getHttpRequest(httpServletRequest);
        ResponseEntity<UUID> response = null;
        try {
            response = restTemplate.exchange(url, HttpMethod.GET, request, UUID.class);
            if (!response.hasBody())
                return new ApiResponse("User not found", false, null);
        } catch (HttpClientErrorException e) {
            return new ApiResponse("No authorization for you", false);
        }

        double annualSalary = requestDto.getSalary() * 12;
        double actualAnnualSalary = annualSalary - annualSalary * 0.3;
        Credit credit = creditRepository.findAll().get(0);

        if ((requestDto.getCreditAmount() + requestDto.getCreditAmount() * credit.getPercent() * 0.01) < actualAnnualSalary) {
            CreditInfo creditInfo = new CreditInfo();
            creditInfo.setUserId(response.getBody());
            creditInfo.setCredit(credit);
            creditInfo.setAmount(requestDto.getCreditAmount());
            creditInfo.setSalary(requestDto.getSalary());
            creditInfo.setDate(Date.valueOf(LocalDate.now()));

            creditInfoRepository.save(creditInfo);

            return new ApiResponse(true, new CreditDto(
                    "You have been granted credit",
                    requestDto.getCreditAmount() / 12,
                    requestDto.getCreditAmount() * (credit.getPercent() / 100),
                    requestDto.getCreditAmount(),
                    credit.getPercent()));
        } else {
            return new ApiResponse(false, new CreditDto(
                    "Your monthly salary was not enough for the amount of credit you asked for. You can get a loan in the following amounts",
                    actualAnnualSalary / 12,
                    actualAnnualSalary * (credit.getPercent() / 100),
                    actualAnnualSalary,
                    credit.getPercent()));
        }

    }

    private String makeUrlWithParams(String url, RequestDto requestDto) {
        String newUrl = url;
        List<String> params = new ArrayList<>();
        params.add("passSeriya=" + requestDto.getPassSeriya());
        params.add("passNumber=" + requestDto.getPassNumber());

        for (String param : params) {
            newUrl += param + "&";
        }
        return newUrl;
    }

    private HttpEntity getHttpRequest(HttpServletRequest httpServletRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", httpServletRequest.getHeader("Authorization"));
        HttpEntity request = new HttpEntity(headers);
        return request;
    }
}
