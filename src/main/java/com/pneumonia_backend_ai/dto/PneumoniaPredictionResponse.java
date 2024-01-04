package com.pneumonia_backend_ai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PneumoniaPredictionResponse {

    @JsonProperty("pneumonia_prediction")
    private String pneumoniaPrediction;
}
