package dev.danvega.jwt.model;


import java.util.List;

public class ResponseModel<T> {

    private boolean success;
    private String message;
    private List<String> errors;
    private List<T> data;

    public ResponseModel(boolean success, String message, List<String> errors, List<T> data) {
        this.success = success;
        this.message = message;
        this.errors = errors;
        this.data = data;
    }

    // Getters and setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}