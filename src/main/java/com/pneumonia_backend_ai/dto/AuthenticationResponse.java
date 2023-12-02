package com.pneumonia_backend_ai.dto;


import com.pneumonia_backend_ai.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor



public class AuthenticationResponse {

    private User user;

    private String token;

}