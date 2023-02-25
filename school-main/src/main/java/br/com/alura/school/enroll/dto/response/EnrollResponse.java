package br.com.alura.school.enroll.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EnrollResponse {

    @JsonProperty
    private String email;

    @JsonProperty("quantidade_matriculas")
    private Long enrollQuantity;

    public EnrollResponse() {
    }

    public EnrollResponse(String email, Long enrollQuantity) {
        this.email = email;
        this.enrollQuantity = enrollQuantity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getEnrollQuantity() {
        return enrollQuantity;
    }

    public void setEnrollQuantity(Long enrollQuantity) {
        this.enrollQuantity = enrollQuantity;
    }

    @Override
    public String toString() {
        return "EnrollResponse{" +
                "email='" + email + '\'' +
                ", enrollQuantity=" + enrollQuantity +
                '}';
    }
}
