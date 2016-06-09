package com.example.rest.validation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.CDI;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@ApplicationScoped
public class LocalizedValidator {

    private ConcurrentMap<Locale, Validator> validatorCache = new ConcurrentHashMap<>();

    public <T> Set<ConstraintViolation<T>> validate(T object, Class<?>... groups) {
        HttpServletRequest httpServletRequest = CDI.current().select(HttpServletRequest.class).get();
        Locale locale = httpServletRequest.getLocale();
        System.out.println("[" + this.getClass().getSimpleName() + "] locale = " + locale);
        Validator validator = validatorCache.computeIfAbsent(locale, (keyLocale) -> {
            System.out.println("Created: " + keyLocale);
            return Validation.byDefaultProvider()
                    .configure()
                    .messageInterpolator(new LocalizedMessageInterpolator(keyLocale))
                    .buildValidatorFactory()
                    .getValidator();
        });
        return validator.validate(object, groups);
    }
}
