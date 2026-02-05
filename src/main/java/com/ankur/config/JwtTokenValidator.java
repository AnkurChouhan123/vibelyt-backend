package com.ankur.config;

import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenValidator extends OncePerRequestFilter {

    private static final SecretKey key =
            Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader(JwtConstant.JWT_HEADER);

        // Skip if no token
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = header.substring(7);

        try {

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();

            // ✅ FIXED: get email from subject
            String email = claims.getSubject();

            String authorities = claims.get("authorities", String.class);

            List<GrantedAuthority> auths =
                    authorities != null ?
                    AuthorityUtils.commaSeparatedStringToAuthorityList(authorities)
                    : AuthorityUtils.NO_AUTHORITIES;

            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(email, null, auths);

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {

            SecurityContextHolder.clearContext();

        }

        filterChain.doFilter(request, response);
    }
}
