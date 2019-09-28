package com.nayeem.example.demo.slumber.controller;

import com.nayeem.example.demo.slumber.Exception.AuthenticationException;
import com.nayeem.example.demo.slumber.configuration.settings.JwtSettings;
import com.nayeem.example.demo.slumber.dto.*;
import com.nayeem.example.demo.slumber.entity.User;
import com.nayeem.example.demo.slumber.jwt.JwtHelper;
import com.nayeem.example.demo.slumber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
public class AuthenticationRestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userService;

    @Autowired
    private UserService userService1;

    @RequestMapping(value = "${jwt.token.auth_uri}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthTokenRequest authenticationRequest)
            throws AuthenticationException {

        final Authentication authentication = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        SecurityContextHolder.getContext().setAuthentication((authentication));

        User user = userService1.getUserByUsername(authenticationRequest.getUsername());

        final String accessToken = jwtHelper.generateAccessToken(authentication);
        //final String refreshToken = jwtHelper.refreshToken(authenticationRequest.getUsername());

        return ResponseEntity.ok(new TokenResponse(accessToken, user));
    }

//    @RequestMapping(value = "${jwt.token.refresh_uri}", method = RequestMethod.POST)
//    public ResponseEntity<?> refreshAndGetAuthenticationToken(@RequestBody RefreshTokenRequest refreshRequest, HttpServletRequest request) {
//        String refresh_token = refreshRequest.getToken();
//        //final String token = refresh_token.substring(7);
//
//        if (jwtHelper.validateRefreshToken(refresh_token)) {
//            //Add Redis logic
//            String username = jwtHelper.getUsernameFromValidationToken(refresh_token);
//
//            final UserDetails userDetails = userService.loadUserByUsername(username);
//            final Authentication authentication = authenticate(userDetails.getUsername(),  userDetails.getPassword());
//            SecurityContextHolder.getContext().setAuthentication((authentication));
//            final String accessToken = jwtHelper.generateAccessToken(authentication);
//
//            User user = userService1.getUserByUsername(username);
//
//            return ResponseEntity.ok(new TokenResponse(accessToken, "", user));
//        } else {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
//        }
//    }

    private Authentication authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("INVALID_CREDENTIALS", e);
        }
    }
}
