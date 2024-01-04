package com.pneumonia_backend_ai.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pneumonia_backend_ai.dto.PneumoniaPredictionResponse;
import com.pneumonia_backend_ai.utils.ProcessUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor

@Service
public class PneumoniaService {

    private final ObjectMapper objectMapper;
    private final ProcessUtils processUtils;

    public PneumoniaPredictionResponse getPneumoniaPrediction(MultipartFile file){
        Path tempFile = null;
        try {
            tempFile = Files.createTempFile("upload-", ".png");
            Files.copy(file.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);
            ProcessBuilder processBuilder = new ProcessBuilder("python3", processUtils.resolvePythonScriptPath("predict/predict_pneumonia.py"));
            processBuilder.command().add(tempFile.toString());
            Process process = processBuilder.start();
            String jsonResponse = processUtils.readOutput(process);
            jsonResponse = parseJsonOnly(jsonResponse);
            System.out.println("response : " + jsonResponse);

            int exitCode = process.waitFor();
            Files.delete(tempFile);

            if (exitCode == 0){
                PneumoniaPredictionResponse response = objectMapper.readValue(jsonResponse, PneumoniaPredictionResponse.class);
                return response;
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (tempFile != null) {
                try {
                    Files.deleteIfExists(tempFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private String parseJsonOnly(String response){
        Pattern p = Pattern.compile("\\{[^}]*\\}");
        Matcher matcher = p.matcher(response);
        if (matcher.find()) {
            return matcher.group(0);
        }

        return null;
    }

}
