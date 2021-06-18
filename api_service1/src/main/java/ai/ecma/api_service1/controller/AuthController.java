package ai.ecma.api_service1.controller;

import ai.ecma.api_service1.payload.ApiResponse;
import ai.ecma.api_service1.payload.UserDto;
import ai.ecma.api_service1.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("register")
    public HttpEntity<?> RegisterUser(@RequestBody UserDto userDto){
        if (!userDto.getPassword().equals(userDto.getPrePassword())){
            return ResponseEntity.status(409).body("Password and prepassword are not equals");
        }
        // Pastdagi shartlar postmendan so'rov berish uchun tekshirib olindi.
        // Agarda API ga clientdan so'rov keladigan bo'lsa bu tekshiruvlar shart emas
        else if (authService.existUserNameOrPhoneNumber(userDto.getEmail()) ||
                    authService.existUserNameOrPhoneNumber(userDto.getPhoneNumber()) ||
                    authService.existsByPassSeriyaAndPassNumber(userDto.getPassSeriya(), userDto.getPassNumber())){
            return ResponseEntity.status(409).body("Passport number or userName already exist ");
        }
        ApiResponse apiResponse= authService.registerUser(userDto);

        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse.getMessage());
    }
}
