package br.com.alura.school.enroll.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class StandardError {

    private String errorMessage;

    @JsonFormat(pattern = "dd/MM/yyyy H:mm:ss", locale = "pt_BR", timezone = "Brazil/East")
    private Date timestamp;

    public StandardError(String errorMessage, Date timestamp) {
        this.errorMessage = errorMessage;
        this.timestamp = timestamp;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
