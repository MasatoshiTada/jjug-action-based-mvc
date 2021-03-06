package com.example.rest.validation;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;

import java.util.Locale;

class LocalizedMessageInterpolator extends ResourceBundleMessageInterpolator {

    private final Locale locale;

    LocalizedMessageInterpolator(Locale locale) {
        super(new NoFallbackResourceBundleLocator());
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
