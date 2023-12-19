package com.example.springboot_mysql_template.Authentification;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AuthenticateResponse {
    @JsonProperty("access_token")
    private String accessToken;

}
