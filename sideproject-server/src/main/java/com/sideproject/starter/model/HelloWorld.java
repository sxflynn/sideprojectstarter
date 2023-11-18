package com.sideproject.starter.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class HelloWorld {

private String message;


    public HelloWorld(String message) {
        this.message = message;
    }

    public HelloWorld() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
