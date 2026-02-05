package com.ankur.config;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {

    private SecretKey key;

    @jakarta.annotation.PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
    }

    // Generate JWT token
    public String generateToken(Authentication auth) {

        String authorities = populateAuthorities(auth.getAuthorities());

        String jwt = Jwts.builder()
                .setSubject(auth.getName()) // email
                .claim("authorities", authorities)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24h
                .signWith(key)
                .compact();

        return jwt;
    }

    // Extract email
    public String getEmailFromJwtToken(String jwt) {

        if(jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);
        }

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        return claims.getSubject();
    }

    // Convert authorities to string
    public String populateAuthorities(Collection<? extends GrantedAuthority> collection) {

        Set<String> auths = new HashSet<>();

        for(GrantedAuthority authority : collection) {
            auths.add(authority.getAuthority());
        }

        return String.join(",", auths);
    }

}
