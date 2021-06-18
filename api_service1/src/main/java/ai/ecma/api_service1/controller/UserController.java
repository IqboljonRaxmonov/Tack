package ai.ecma.api_service1.controller;

import ai.ecma.api_service1.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/")
public class UserController {
    @Autowired
    AuthService authService;

    @GetMapping("getUserInfo")
    public HttpEntity<?> getUserInfo(@RequestParam String passSeriya,
                                     @RequestParam String passNumber){
        return ResponseEntity.ok().body(authService.getUserInfo(passSeriya, passNumber));
    }
    @GetMapping("getUserId")
    public HttpEntity<?> getUserId(@RequestParam String passSeriya,
                                     @RequestParam String passNumber){
        return ResponseEntity.ok().body(authService.getUserId(passSeriya, passNumber));
    }
}
