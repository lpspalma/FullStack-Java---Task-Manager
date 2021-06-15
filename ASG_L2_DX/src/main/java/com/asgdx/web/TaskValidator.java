package com.asgdx.web;

import com.asgdx.model.Task;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TaskValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Task.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        String empty = "Not Empty";

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", empty);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", empty);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dueTime", empty);
    }
}
