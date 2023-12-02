package com.pneumonia_backend_ai.controllers;

import com.pneumonia_backend_ai.dto.Response;
import com.pneumonia_backend_ai.dto.SymptomsRequest;
import com.pneumonia_backend_ai.services.DiseaseSymptomsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/symptoms")
public class DiseaseSymptomsController {

    private final DiseaseSymptomsService diseaseSymptomsService;

    @GetMapping("/disease")
    public ResponseEntity<Response> getDiseaseDetails(@RequestBody SymptomsRequest symptomsRequest){
        return ResponseEntity.ok(
                Response.builder()
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message("details of disease")
                        .data(Map.of("diseaseDetails", diseaseSymptomsService.getDiseaseDetails(symptomsRequest.getSymptoms())))
                        .build()
        );
    }
}
