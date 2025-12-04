package com.winter.app.util.validation;

import com.winter.app.users.UsersDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        UsersDTO user = (UsersDTO) obj;
        boolean isValid = user.getPassword().equals(user.getPasswordCheck());
        
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                   .addPropertyNode("passwordCheck").addConstraintViolation();
        }
        
        return isValid;
    }
}
