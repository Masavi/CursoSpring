package com.example.comandos.validators;

import com.example.comandos.ConsumidorForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by jt on 12/24/15.
 */
@Component
public class ConsumidorFormValidator implements Validator{

    @Override
    public boolean supports(Class<?> aClass) {
        return ConsumidorForm.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ConsumidorForm customerForm = (ConsumidorForm) target;

        if(!customerForm.getPasswordText().equals(customerForm.getPasswordTextConf())){
            errors.rejectValue("passwordText", "PasswordsDontMatch.customerForm.passwordText", "Passwords Don't Match");
            errors.rejectValue("passwordTextConf", "PasswordsDontMatch.customerForm.passwordTextConf", "Passwords Don't Match");
        }
    }
}
