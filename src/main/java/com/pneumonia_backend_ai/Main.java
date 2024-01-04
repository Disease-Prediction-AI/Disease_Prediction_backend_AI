package com.pneumonia_backend_ai;

import  com.fasterxml.jackson.databind.ObjectMapper;
import com.pneumonia_backend_ai.dto.DiseaseResponse;
import com.pneumonia_backend_ai.dto.LCPatientInfoRequest;
import com.pneumonia_backend_ai.dto.LCPredictionResponse;
import com.pneumonia_backend_ai.utils.ProcessUtils;

import java.io.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println(getDiseaseDetails(List.of("muscle_wasting", "patches_in_throat", "high_fever", "extra_marital_contacts")));
        //System.out.println(getPrediction(new LCPatientInfoRequest("M","19","NO","NO","NO","NO","NO","NO","YES","NO","YES","NO","NO","NO","NO")));
    }

    private static DiseaseResponse getDiseaseDetails(List<String> symptoms){
        ObjectMapper objectMapper = new ObjectMapper();
        ProcessUtils processUtils = new ProcessUtils();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python3", processUtils.resolvePythonScriptPath("predict/predict_disease_prediction.py"));
            processBuilder.command().addAll(symptoms);
            Process process = processBuilder.start();
            String jsonResponse = processUtils.readOutput(process);

            int exitCode = process.waitFor();

            if (exitCode == 0){
                System.out.println(jsonResponse);
                DiseaseResponse diseaseResponse = objectMapper.readValue(jsonResponse,DiseaseResponse.class);
                return diseaseResponse;
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static LCPredictionResponse getPrediction(LCPatientInfoRequest request){
        ObjectMapper objectMapper = new ObjectMapper();
        ProcessUtils processUtils = new ProcessUtils();
        List<String> symptoms = request.convertToList();

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python3", processUtils.resolvePythonScriptPath("predict/predict_lung_cancer.py"));
            processBuilder.command().addAll(symptoms);
            Process process = processBuilder.start();
            String jsonResponse = processUtils.readOutput(process);
            System.out.println(jsonResponse);

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
