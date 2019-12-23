package com.gmail.roadtojob2019.servicemodule.services.validators;

import com.gmail.roadtojob2019.servicemodule.services.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Service
public class UserValidator implements Validator {

    private static final String ONLY_LATIN_SYMBOLS_REGEX = "[a-zA-Z]+";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    @Autowired
    private javax.validation.Validator validator;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Set<ConstraintViolation<Object>> validates = validator.validate(obj);

        for (ConstraintViolation<Object> constraintViolation : validates) {
            String propertyPath = constraintViolation.getPropertyPath().toString();
            String message = constraintViolation.getMessage();
            errors.rejectValue(propertyPath, "", message);
        }

        UserDTO userDTO = (UserDTO) obj;
        if (userDTO.getLastName().length() > 40 && userDTO.getLastName().length() < 1) {
            errors.rejectValue("lastName", "validation.size.lastName");
        }
        if (!userDTO.getLastName().matches(ONLY_LATIN_SYMBOLS_REGEX)) {
            errors.rejectValue("lastName", "validation.type.lastName");
        }

        if (userDTO.getName().length() > 20 && userDTO.getName().length() < 1) {
            errors.rejectValue("name", "validation.size.name");
        }
        if (!userDTO.getName().matches(ONLY_LATIN_SYMBOLS_REGEX)) {
            errors.rejectValue("name", "validation.type.name");
        }

        if (userDTO.getMiddleName().length() > 40 && userDTO.getMiddleName().length() < 1) {
            errors.rejectValue("middleName", "validation.size.middleName");
        }
        if (!userDTO.getMiddleName().matches(ONLY_LATIN_SYMBOLS_REGEX)) {
            errors.rejectValue("middleName", "validation.type.middleName");
        }

        if (userDTO.getEmail().length() > 50 && userDTO.getMiddleName().length() < 1) {
            errors.rejectValue("email", "validation.size.email");
        }
        if (!userDTO.getEmail().matches(EMAIL_REGEX)) {
            errors.rejectValue("email", "validation.form.email");
        }

        if (userDTO.getRole().length() < 1) {
            errors.rejectValue("role", "validation.size.role");
        }
    }
}