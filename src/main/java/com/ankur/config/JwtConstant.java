package com.ankur.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConstant {

    public static String SECRET_KEY;
    public static String JWT_HEADER;

    @Value("${jwt.secret}")
    public void setSecretKey(String secretKey) {
        SECRET_KEY = secretKey;
    }

    @Value("${jwt.header}")
    public void setJwtHeader(String jwtHeader) {
        JWT_HEADER = jwtHeader;
    }
}
