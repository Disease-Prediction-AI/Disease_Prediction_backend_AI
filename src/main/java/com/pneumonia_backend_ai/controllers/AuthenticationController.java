package com.pneumonia_backend_ai.controllers;

import com.pneumonia_backend_ai.entities.User;
import com.pneumonia_backend_ai.dto.AuthenticationRequest;
import com.pneumonia_backend_ai.dto.AuthenticationResponse;
import com.pneumonia_backend_ai.dto.Response;
import com.pneumonia_backend_ai.security.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    public final AuthenticationService service;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody User request){
        System.out.println("inside controller");
        System.out.println(request);
        return ResponseEntity.ok(
                Response.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .status(HttpStatus.CREATED)
                        .data(Map.of("user ",service.register(request)))
                        .message("user saved")
                        .build()
        );
    }
}
