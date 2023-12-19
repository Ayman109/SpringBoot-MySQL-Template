package com.example.springboot_mysql_template.security.jwt;

import com.example.springboot_mysql_template.User.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtService {

    public JwtService() {
    }

    private final String SECRET_KEY= "ddbe253e3edf95e5e35e32aa0905d656b5102766b2e073a1f9cc0654294c2f32";
    public String extractUserName(String token){
        return  extractClaim(token , Claims::getSubject) ;
    }


    //lezemk tefhem faza <T> ???
    public <T> T extractClaim(String token , Function<Claims,T> claimsResolver){
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }



    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>() , userDetails);
    }

    public Boolean validationToken (String token , UserDetails userDetails){
        String username = extractUserName(token);
        return userDetails.getUsername().equals(username) && !extractClaim(token,Claims::getExpiration).before(new Date()) ;
    }

    public String generateToken(
            Map<String,Object> extraClaims,
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSinginKey() , SignatureAlgorithm.HS256)
                .compact();
    }


    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSinginKey())
                .build()
                .parseClaimsJwt(token)
                .getBody();
    }


    private Key getSinginKey() {
        byte[] decoder = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decoder);
    }


}
