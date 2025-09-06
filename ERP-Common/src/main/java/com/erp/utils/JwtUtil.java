package com.erp.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    /**
     * generate jwt
     * @param secretKey
     * @param ttlMillis jwt expiry time (ms)
     * @param claims    message
     * @return
     */
    public static String createJWT(String secretKey, long ttlMillis, Map<String, Object> claims) {
        // jwt header
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // time generating jwt
        long expMillis = System.currentTimeMillis() + ttlMillis;
        Date exp = new Date(expMillis);

        // set jwt body
        JwtBuilder builder = Jwts.builder()
                // set claim
                .setClaims(claims)
                // set algorithem and secret key for sigature
                .signWith(signatureAlgorithm, secretKey.getBytes(StandardCharsets.UTF_8))
                // set expration
                .setExpiration(exp);

        return builder.compact();
    }

    /**
     * Token decoding
     *
     * @param secretKey
     * @param token
     * @return
     */
    public static Claims parseJWT(String secretKey, String token) {
        // get DefaultJwtParser
        Claims claims = Jwts.parser()
                // set signing key
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                // set jwt to be parsed
                .parseClaimsJws(token).getBody();
        return claims;
    }
}
