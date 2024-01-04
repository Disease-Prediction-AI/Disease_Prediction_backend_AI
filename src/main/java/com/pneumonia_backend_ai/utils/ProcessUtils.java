package com.pneumonia_backend_ai.utils;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.*;

@Data
@RequiredArgsConstructor

@Component
public class ProcessUtils {

    public String resolvePythonScriptPath(String filename) {
        File file = new File("src/main/resources/" + filename);
        return file.getAbsolutePath();
    }

    public String readOutput(Process process) throws IOException {

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
