package com.example.rest.validation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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

    @Inject
    private HttpServletRequest httpServletRequest;

    public <T> Set<ConstraintViolation<T>> validate(T object, Class<?>... groups) {
        Locale locale = httpServletRequest.getLocale();
        Validator validator = validatorCache.computeIfAbsent(locale, (keyLocale) ->
            Validation.byDefaultProvider()
                    .configure()
                    .messageInterpolator(new LocalizedMessageInterpolator(keyLocale))
                    .buildValidatorFactory()
                    .getValidator()
        );
        return validator.validate(object, groups);
    }
}
