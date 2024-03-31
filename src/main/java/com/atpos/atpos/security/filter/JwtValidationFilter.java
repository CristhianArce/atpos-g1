package com.atpos.atpos.security.filter;

import com.atpos.atpos.security.SimpleGrantedAuthorityJsonCreator;
import com.atpos.atpos.user.entity.Role;
import com.atpos.atpos.user.entity.User;
import com.atpos.atpos.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.atpos.atpos.security.TokenJwtConfig.CONTENT_TYPE;
import static com.atpos.atpos.security.TokenJwtConfig.HEADER_AUTHORIZATION;
import static com.atpos.atpos.security.TokenJwtConfig.PREFIX_TOKEN;
import static com.atpos.atpos.security.TokenJwtConfig.SECRET_KEY;
import static com.atpos.atpos.security.TokenJwtConfig.getSigningKey;

public class JwtValidationFilter extends BasicAuthenticationFilter {


    private UserRepository userRepository;

    public JwtValidationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String header = request.getHeader(HEADER_AUTHORIZATION);

        if (header == null || !header.startsWith(PREFIX_TOKEN)) {
            chain.doFilter(request, response);
            return;
        }
        String token = header.replace(PREFIX_TOKEN, "");

        try {
            Claims claims = Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
            String username = claims.getSubject();
            Object authoritiesClaims = claims.get("authorities");

            Collection<? extends GrantedAuthority> authorities = Arrays.asList(
                    new ObjectMapper()
                            .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)
                            .readValue(authoritiesClaims.toString().getBytes(), SimpleGrantedAuthority[].class)
            );


            User user = userRepository.findByUsername(username).get();
            List<Role> roles = user.getRoles();
            List<String> rolesInDB = roles.stream()
                    .map(Role::getName)
                    .toList();

            List<String> rolesFromToken = authorities.stream().map(GrantedAuthority::getAuthority).toList();
            List<String> differences = rolesFromToken.stream().filter(x -> !rolesInDB.contains(x)).toList();

            if (!differences.isEmpty()) {

                user.setEnabled(false);
                userRepository.save(user);
                Map<String, String> body = new HashMap<>();
                body.put("message", "You have tried to scalate privileges you don't have!, Blocking username, please contact your admin if this is an error, to enable again.");

                response.getWriter().write(new ObjectMapper().writeValueAsString(body));
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType(CONTENT_TYPE);
                return;
            }

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        } catch (JwtException e) {
            Map<String, String> body = new HashMap<>();
            body.put("error", e.getMessage());
            body.put("message", "The provided JSON Web Token is not valid!");

            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(CONTENT_TYPE);
        }
    }

}
