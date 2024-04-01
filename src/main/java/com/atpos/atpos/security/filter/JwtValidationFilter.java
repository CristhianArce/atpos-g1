package com.atpos.atpos.security.filter;

import com.atpos.atpos.user.entity.Role;
import com.atpos.atpos.user.entity.User;
import com.atpos.atpos.user.repository.UserRepository;
import com.atpos.atpos.utils.ElevationOfPrivilegesDetectedFaultsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

import static com.atpos.atpos.security.TokenJwtConfig.CONTENT_TYPE;
import static com.atpos.atpos.security.TokenJwtConfig.HEADER_AUTHORIZATION;
import static com.atpos.atpos.security.TokenJwtConfig.PREFIX_TOKEN;
import static com.atpos.atpos.security.TokenJwtConfig.getSigningKey;

public class JwtValidationFilter extends BasicAuthenticationFilter {


    private UserRepository userRepository;
    private ElevationOfPrivilegesDetectedFaultsService detectedFaultsService;

    public JwtValidationFilter(AuthenticationManager authenticationManager,
                               UserRepository userRepository,
                               ElevationOfPrivilegesDetectedFaultsService detectedFaultsService) {
        super(authenticationManager);
        this.userRepository = userRepository;
        this.detectedFaultsService = detectedFaultsService;
    }


    private  static List<String> convertObjectToList(Object authoritiesClaims) {
        List<String> stringList = new ArrayList<>();

        if (authoritiesClaims instanceof Iterable) {
            for (Object element : (Iterable<?>) authoritiesClaims) {
                stringList.add(element.toString());
            }
        } else {
            stringList.add(authoritiesClaims.toString());
        }

        return stringList;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        long validationStartTime = System.nanoTime();
        long validationEndTime;

        String header = request.getHeader(HEADER_AUTHORIZATION);
        if (header == null || !header.startsWith(PREFIX_TOKEN)) {
            chain.doFilter(request, response);
            return;
        }
        String token = header.replace(PREFIX_TOKEN, "");

        try {
            Claims claims = Jwts
                    .parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            String username = claims.getSubject();
            Object authoritiesClaims = claims.get("authorities");

            Collection<? extends GrantedAuthority> authorities = convertObjectToList(authoritiesClaims)
                    .stream()
                    .map(SimpleGrantedAuthority::new)
                    .toList();

            var optionalUser = userRepository.findByUsername(username);
            if(optionalUser.isEmpty()) throw new RuntimeException("The provided username is not valid");
            User user = optionalUser.get();
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
                validationEndTime = System.nanoTime();
                var elapsedTime = validationStartTime - validationEndTime / 1e6;
                detectedFaultsService.detectedFault(
                        "Attempt of Elevation of Privileges",
                        elapsedTime,
                        "SEVERE"
                        );
                return;
            }

            Date licenseExpirationDate = new Date((Long) claims.get("licenseExpiration"));
            var ldtLicenseExpirationDate = licenseExpirationDate
                    .toInstant().atOffset(ZoneOffset.UTC)
                    .toLocalDateTime();
            if (ldtLicenseExpirationDate.isBefore(LocalDateTime.now())) {
                Map<String, String> body = new HashMap<>();
                body.put("message", "Your license has expired!");
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
            body.put("message", "The provided JSON Web Token is not Valid!");

            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(CONTENT_TYPE);
        }
    }

}
