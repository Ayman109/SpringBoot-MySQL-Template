package com.example.springboot_mysql_template.User;


import com.example.springboot_mysql_template.Authentification.AuthenticateResponse;
import com.example.springboot_mysql_template.Authentification.AuthenticationRequest;
import com.example.springboot_mysql_template.Authentification.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

}
