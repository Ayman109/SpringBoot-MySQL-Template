package com.example.springboot_mysql_template.Authentification;


import com.example.springboot_mysql_template.User.UserEntity;
import com.example.springboot_mysql_template.User.UserRepository;
import com.example.springboot_mysql_template.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticateResponse register(RegisterRequest request) {

        jwtService = new JwtService();

        var user = new UserEntity(
                request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getRole()
        );
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticateResponse.builder()
                .accessToken(jwtToken)
                .build();

    }


    public AuthenticateResponse auth(AuthenticationRequest request) {
        jwtService = new JwtService();


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                ));


        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticateResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}