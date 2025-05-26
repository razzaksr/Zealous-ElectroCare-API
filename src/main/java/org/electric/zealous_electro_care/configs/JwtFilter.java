package org.electric.zealous_electro_care.configs;

import java.io.IOException;
import java.util.Date;

import org.electric.zealous_electro_care.middles.UserMiddle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private UserMiddle userMiddle;

    private Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            try {
                Jws<Claims> claims = jwtUtility.validateToken(token);
                Date expiration = claims.getBody().getExpiration();
                logger.info("Token Expiry Time: " + expiration);

                username = claims.getBody().getSubject();
                logger.info("username found "+username);
            } catch (Exception e) {
                System.out.println("Invalid or expired token");
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userMiddle.loadUserByUsername(username);
            logger.info(username+" trying to call user details");
            UsernamePasswordAuthenticationToken authentication = 
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            logger.info(username+"Authorization created in filter");
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.info(username+"Authorization set ");
        }

        filterChain.doFilter(request, response);
    }
}