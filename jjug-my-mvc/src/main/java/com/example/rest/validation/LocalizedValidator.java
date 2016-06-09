package com.example.rest.validation;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Locale;
import java.util.Set;

@RequestScoped
public class LocalizedValidator {

    @Inject
    private HttpServletRequest httpServletRequest;

    public <T> Set<ConstraintViolation<T>> validate(T object, Class<?>... groups) {
        Locale locale = httpServletRequest.getLocale();
        System.out.println("[" + this.getClass().getSimpleName() + "] locale = " + locale);
        ValidatorFactory factory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new LocalizedMessageInterpolator(locale))
                .buildValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(object, groups);
    }
}
