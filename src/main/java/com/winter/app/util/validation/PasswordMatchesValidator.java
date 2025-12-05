package com.winter.app.util.validation;

import com.winter.app.users.UserDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        UserDTO user = (UserDTO) obj;
        boolean isValid = user.getPassword().equals(user.getPasswordCheck());
        
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                   .addPropertyNode("passwordCheck").addConstraintViolation();
        }
        
        return isValid;
    }
}
