package com.pneumonia_backend_ai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class LCPredictionResponse {

    @JsonProperty("cancer_prediction")
    private String cancerPrediction;

}
