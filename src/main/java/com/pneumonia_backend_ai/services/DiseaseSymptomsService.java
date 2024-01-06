package com.pneumonia_backend_ai.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pneumonia_backend_ai.dto.DiseaseResponse;
import com.pneumonia_backend_ai.utils.ProcessUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;


@RequiredArgsConstructor

@Service
public class DiseaseSymptomsService {

    private final ObjectMapper objectMapper;
    private final ProcessUtils processUtils;

    public DiseaseResponse getDiseaseDetails(List<String> symptoms){
        try {
            System.out.println("symptoms : " + symptoms);
            ProcessBuilder processBuilder = new ProcessBuilder("python3", processUtils.resolvePythonScriptPath("predict/predict_disease_prediction.py"));
            processBuilder.command().addAll(symptoms);
            Process process = processBuilder.start();
            String jsonResponse = processUtils.readOutput(process);
            System.out.println(jsonResponse);

            int exitCode = process.waitFor();

            if (exitCode == 0){
                DiseaseResponse diseaseResponse = objectMapper.readValue(jsonResponse,DiseaseResponse.class);
                return diseaseResponse;
            }

        } catch (IOException  | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
