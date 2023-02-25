package br.com.alura.school.enroll.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NewEnrollRequest {

    @Size(max=20)
    @NotBlank
    @JsonProperty
    private String username;

    public NewEnrollRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
