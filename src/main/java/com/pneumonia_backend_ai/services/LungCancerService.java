package com.pneumonia_backend_ai.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pneumonia_backend_ai.dto.LCPatientInfoRequest;
import com.pneumonia_backend_ai.dto.LCPredictionResponse;
import com.pneumonia_backend_ai.utils.ProcessUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor

@Service
public class LungCancerService {

    private final ObjectMapper objectMapper;
    private final ProcessUtils processUtils;

    public LCPredictionResponse getPrediction(LCPatientInfoRequest request){

        List<String> symptoms = request.convertToList();

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python3", processUtils.resolvePythonScriptPath("predict/predict_lung_cancer.py"));
            processBuilder.command().addAll(symptoms);
            Process process = processBuilder.start();
            String jsonResponse = processUtils.readOutput(process);
            //System.out.println(jsonResponse);

            int exitCode = process.waitFor();

            if (exitCode == 0){
                LCPredictionResponse response = objectMapper.readValue(jsonResponse, LCPredictionResponse.class);
                return response;
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
