package io.github.javaasasecondlanguage.flitter.controllers.dto;

public class ResponseDTO<T> {
    private T data;
    private String errorMessage;

    public ResponseDTO() {
        this.data = null;
        this.errorMessage = null;
    }

    public ResponseDTO(T data) {
        this.data = data;
        this.errorMessage = null;
    }

    public ResponseDTO(String errorMessage) {
        this.data = null;
        this.errorMessage = errorMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
