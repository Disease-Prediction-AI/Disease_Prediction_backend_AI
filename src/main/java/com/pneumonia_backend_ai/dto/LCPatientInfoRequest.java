package com.pneumonia_backend_ai.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class LCPatientInfoRequest {

    @JsonProperty
    private String gender;

    @JsonProperty
    private String age;

    @JsonProperty
    private String smoking;

    @JsonProperty
    private String anxiety;

    @JsonProperty
    private String yellowFingers;

    @JsonProperty
    private String peerPressure;

    @JsonProperty
    private String chronicDisease;

    @JsonProperty
    private String fatigue;

    @JsonProperty
    private String allergy;

    @JsonProperty
    private String wheezing;

    @JsonProperty
    private String alcoholConsuming;

    @JsonProperty
    private String coughing;

    @JsonProperty
    private String shortnessOfBreath;

    @JsonProperty
    private String swallowingDifficulty;

    @JsonProperty
    private String chestPain;


    public Map<String,String> convertToMap(){
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,String> objectMap = objectMapper.convertValue(this, new TypeReference<Map<String, String>>() {});
        return objectMap;
    }

    public List<String> convertToList(){
        return new ArrayList<>(this.convertToMap().values());
    }

}
