package com.pneumonia_backend_ai.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pneumonia_backend_ai.dto.DiseaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;


@RequiredArgsConstructor

@Service
public class DiseaseSymptomsService {

    private final ObjectMapper objectMapper;

    public DiseaseResponse getDiseaseDetails(List<String> symptoms){
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python3", resolvePythonScriptPath("predict/predict_disease_prediction.py"));
            processBuilder.command().addAll(symptoms);
            Process process = processBuilder.start();
            String jsonResponse = readOutput(process);
            System.out.println("Error Stream: " + readErrorStream(process));
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


    private String resolvePythonScriptPath(String filename) {
        File file = new File("src/main/resources/" + filename);
        return file.getAbsolutePath();
    }

    private String readOutput(Process process) throws IOException {

        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line);
        }

        return output.toString();
    }

    private String readErrorStream(Process process) throws IOException {
        InputStream errorStream = process.getErrorStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(errorStream));

        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line);
        }

        return output.toString();
    }
}
