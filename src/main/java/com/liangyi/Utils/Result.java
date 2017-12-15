package com.liangyi.Utils;

import java.io.Serializable;

public class Result implements Serializable {
    private static final long serialVersionUID = -2368446516546812379L;

    private boolean isError;
    private int errorType;
    private String errorMessage;
    private Object result;

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public int getErrorType() {
        return errorType;
    }

    public void setErrorType(int errorType) {
        this.errorType = errorType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "isError=" + isError +
                ", errorType=" + errorType +
                ", errorMessage='" + errorMessage + '\'' +
                ", result=" + result +
                '}';
    }
}