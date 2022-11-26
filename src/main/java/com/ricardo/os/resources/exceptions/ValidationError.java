package com.ricardo.os.resources.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ValidationError extends StandardError {
    private static final long serialVersionUID = 1L;

    public ValidationError(){
        super();
    }

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Long timestamp, Integer status, String error) {
        super(timestamp, status, error);
    }

    public void addError(String fieldName, String message){
        this.errors.add(new FieldMessage(fieldName, message));
    }
}
