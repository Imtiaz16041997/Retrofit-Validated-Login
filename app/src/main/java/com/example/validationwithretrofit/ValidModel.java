package com.example.validationwithretrofit;

public class ValidModel {
    String message;

    public ValidModel(String message) {
        this.message = message;
    }

    public ValidModel() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
