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
public class MyValidator {

    @Inject
    private HttpServletRequest httpServletRequest;

//    @Context
//    private HttpHeaders httpHeaders;

    public <T> Set<ConstraintViolation<T>> validate(T object, Class<?>... groups) {
//        Locale locale = httpHeaders.getAcceptableLanguages().get(0);
        Locale locale = httpServletRequest.getLocale();
        System.out.println("[" + this.getClass().getSimpleName() + "] locale = " + locale);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.usingContext()
                .messageInterpolator(new LocalizedMessageInterpolator(locale, factory.getMessageInterpolator()))
                .getValidator();
        return validator.validate(object, groups);
    }
}
