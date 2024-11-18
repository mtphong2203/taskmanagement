package com.maiphong.taskmanagement.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maiphong.taskmanagement.dtos.auth.LoginRequestDTO;
import com.maiphong.taskmanagement.dtos.auth.LoginResponseDTO;
import com.maiphong.taskmanagement.dtos.user.UserCreateDTO;
import com.maiphong.taskmanagement.services.AuthService;
import com.maiphong.taskmanagement.services.TokenService;
import com.maiphong.taskmanagement.services.UserService;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserService userService;
    private final TokenService tokenService;

    public AuthController(AuthService authService, AuthenticationManagerBuilder authenticationManagerBuilder,
            UserService userService, TokenService tokenService) {
        this.authService = authService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequestDTO.getUsername(), loginRequestDTO.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = tokenService.generateAccessToken(authentication);

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setAccessToken(accessToken);

        return ResponseEntity.ok(loginResponseDTO);

    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody UserCreateDTO userCreateDTO) {
        if (authService.existByUsername(userCreateDTO.getUsername())) {
            return ResponseEntity.badRequest().body(false);
        }
        boolean result = userService.create(userCreateDTO);

        return ResponseEntity.ok(result);
    }

}
