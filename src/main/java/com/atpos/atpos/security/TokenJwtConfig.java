package com.atpos.atpos.security;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class TokenJwtConfig {
    
    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "application/json";

}
