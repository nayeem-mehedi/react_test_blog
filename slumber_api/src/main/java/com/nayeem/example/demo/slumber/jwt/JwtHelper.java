package com.nayeem.example.demo.slumber.jwt;

import com.nayeem.example.demo.slumber.configuration.settings.JwtSettings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClock;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class JwtHelper implements Serializable {
    static final String CLAIM_KEY_ID = "id";
    static final String AUTHORITIES_KEY = "auth";
    private static final long serialVersionUID = 8037016766140638085L;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    JwtSettings jwtSettings;

    private Clock clock = DefaultClock.INSTANCE;

    public String generateAccessToken(Authentication authentication) {
        final String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        Map<String, Object> claims = new HashMap<>();
        //claims.put(CLAIM_KEY_ID,userDetails.getId()); USER ID
        claims.put(AUTHORITIES_KEY,authorities);
        final Date created = clock.now();

        return createToken(authentication.getName(), claims, created, jwtSettings.getAccessExpirationInSeconds(), getSigningKey(jwtSettings.getAccessKey()));
    }

    public String refreshToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        final Date created = clock.now();

        return createToken(username, claims, created, jwtSettings.getRefreshExpirationInSeconds(), getSigningKey(jwtSettings.getRefreshKey()));
    }

    public UsernamePasswordAuthenticationToken getAuthentication(final String token, final Authentication existingAuth, final UserDetails userDetails) {
        final Claims claims = getAllClaimsFromToken(token,jwtSettings.getAccessKey());

        final Collection authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), authorities);
    }

    public Boolean validateAccessToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromAccessToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token, jwtSettings.getAccessKey());
    }

    public Boolean validateRefreshToken(String token) {
        return !isTokenExpired(token, jwtSettings.getRefreshKey());
    }

    public String getUsernameFromAccessToken(String token) {
        return getClaimFromToken(token, jwtSettings.getAccessKey(), Claims::getSubject);
    }

    public String getUsernameFromValidationToken(String token) {
        return getClaimFromToken(token, jwtSettings.getRefreshKey(), Claims::getSubject);
    }

    private String createToken(String user, Map<String, Object> claims, Date created, int expireTime, Key key) {
        final Date expiration = new Date(created.getTime() + expireTime * 1000);

        return Jwts.builder()
                .signWith(key)
                .setClaims(claims)
                .setSubject(user)
                .setIssuedAt(created)
                .setExpiration(expiration)
                .compact();
    }

    private Boolean isTokenExpired(String token, String key) {
        final Date expiration = getExpirationDateFromToken(token, key);
        return expiration.before(clock.now());
    }

    private Date getExpirationDateFromToken(String token, String key) {
        return getClaimFromToken(token, key, Claims::getExpiration);
    }

    private <T> T getClaimFromToken(String token, String key, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token, key);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token, String key) {
        return Jwts.parser().setSigningKey(getSigningKey(key)).parseClaimsJws(token).getBody();
    }

    private Key getSigningKey(String secret) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
