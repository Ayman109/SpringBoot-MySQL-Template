package com.example.springboot_mysql_template.User;

import com.example.springboot_mysql_template.Authentification.AuthenticateResponse;
import com.example.springboot_mysql_template.Authentification.AuthenticationRequest;
import com.example.springboot_mysql_template.Authentification.RegisterRequest;
import com.example.springboot_mysql_template.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
}
