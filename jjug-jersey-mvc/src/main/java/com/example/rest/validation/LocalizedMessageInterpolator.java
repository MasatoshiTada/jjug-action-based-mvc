package com.example.rest.validation;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;

import java.util.Locale;

public class LocalizedMessageInterpolator extends ResourceBundleMessageInterpolator {

    private final Locale locale;

    public LocalizedMessageInterpolator(Locale locale) {
        super(new NoFallbackControlResourceBundleLocator());
        this.locale = locale;
    }

    @Override
    public String interpolate(String messageTemplate, Context context) {
        return super.interpolate(messageTemplate, context, this.locale);
    }

    @Override
    public String interpolate(String messageTemplate, Context context, Locale locale) {
        return super.interpolate(messageTemplate, context, locale);
    }
}
