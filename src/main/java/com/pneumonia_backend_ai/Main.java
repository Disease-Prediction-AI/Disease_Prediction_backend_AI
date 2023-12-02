package com.pneumonia_backend_ai;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pneumonia_backend_ai.dto.DiseaseResponse;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println(getDiseaseDetails(List.of("muscle_wasting", "patches_in_throat", "high_fever", "extra_marital_contacts")));

    }

    private static DiseaseResponse getDiseaseDetails(List<String> symptoms){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python3", resolvePythonScriptPath("predict/predict_disease_prediction.py"));
            processBuilder.command().addAll(symptoms);
            Process process = processBuilder.start();
            String jsonResponse = readOutput(process);

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

    private static String resolvePythonScriptPath(String filename) {
        File file = new File("src/main/resources/" + filename);
        return file.getAbsolutePath();
    }

    private static String readOutput(Process process) throws IOException {

        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line);
        }

        return output.toString();
    }
}
