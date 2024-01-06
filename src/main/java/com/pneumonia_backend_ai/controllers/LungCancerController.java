package com.pneumonia_backend_ai.controllers;


import com.pneumonia_backend_ai.dto.LCPatientInfoRequest;
import com.pneumonia_backend_ai.dto.Response;
import com.pneumonia_backend_ai.services.LungCancerService;
import com.pneumonia_backend_ai.utils.ProcessUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor

@RestController
@RequestMapping("api/lung-cancer")
public class LungCancerController {

    private final LungCancerService lungCancerService;

    @PostMapping("/predict")
    public ResponseEntity<Response> getPrediction(@RequestBody LCPatientInfoRequest request){
        return ResponseEntity.ok(
                Response.builder()
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message("lung cancer prediction")
                        .data(Map.of("prediction", lungCancerService.getPrediction(request)))
                        .build()
        );
    }
}
