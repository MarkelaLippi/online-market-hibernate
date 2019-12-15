package com.gmail.roadtojob2019.servicemodule.services.validators;

import com.gmail.roadtojob2019.servicemodule.services.UserService;
import com.gmail.roadtojob2019.servicemodule.services.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/*
public class UniqueEmailConstraintValidator implements ConstraintValidator<UniqueEmail, UserDTO> {
    @Autowired
    private UserService userService;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) { }

    @Override
    public boolean isValid(UserDTO userDTO, ConstraintValidatorContext ctx) {
        if (userService.findUserByEmail(userDTO.getEmail()).isPresent()) {
            ctx.disableDefaultConstraintViolation();
            ctx
                    .buildConstraintViolationWithTemplate("{validation.unique.email}")
                    .addPropertyNode("email")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
*/
