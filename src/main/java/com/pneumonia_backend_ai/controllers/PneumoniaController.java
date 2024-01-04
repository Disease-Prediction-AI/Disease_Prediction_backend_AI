package com.pneumonia_backend_ai.controllers;

import com.pneumonia_backend_ai.dto.LCPatientInfoRequest;
import com.pneumonia_backend_ai.dto.Response;
import com.pneumonia_backend_ai.services.PneumoniaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RequiredArgsConstructor

@Controller
@RequestMapping("api/pneumonia")
public class PneumoniaController {

    private final PneumoniaService pneumoniaService;

    @PostMapping("/upload")
    public ResponseEntity<Response> getPrediction(@RequestParam("image") MultipartFile file){
        return ResponseEntity.ok(
                Response.builder()
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message("pneumonia prediction")
                        .data(Map.of("prediction", pneumoniaService.getPneumoniaPrediction(file)))
                        .build()
        );
    }
}
