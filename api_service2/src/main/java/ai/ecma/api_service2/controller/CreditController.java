package ai.ecma.api_service2.controller;

import ai.ecma.api_service2.payload.ApiResponse;
import ai.ecma.api_service2.payload.RequestDto;
import ai.ecma.api_service2.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@RestController
@RequestMapping("/api/credit/")
public class CreditController {

    @Autowired
    CreditService creditService;

    @PostMapping("getCredit")
    public HttpEntity<?> getCredit(HttpServletRequest httpServletRequest, @RequestBody RequestDto requestDto) throws ParseException {
        ApiResponse apiResponse = creditService.getCredit(httpServletRequest, requestDto);
        return ResponseEntity.status(!apiResponse.isSuccess()?403:200).body(apiResponse);
    }
}
