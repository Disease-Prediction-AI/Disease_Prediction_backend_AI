package com.pneumonia_backend_ai.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class DiseaseResponse {

    @JsonProperty
    private String disease;

    @JsonProperty
    private double probability;

    @JsonProperty
    private String description;

    @JsonProperty
    private List<String> precautions;
}
