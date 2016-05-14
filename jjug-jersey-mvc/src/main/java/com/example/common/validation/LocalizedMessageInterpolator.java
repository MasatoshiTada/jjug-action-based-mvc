package com.example.common.validation;

import javax.validation.MessageInterpolator;
import java.util.Locale;

public class LocalizedMessageInterpolator implements MessageInterpolator {

    private Locale locale;
    private MessageInterpolator interpolator;

    public LocalizedMessageInterpolator(Locale locale, MessageInterpolator interpolator) {
        this.locale = locale;
        this.interpolator = interpolator;
    }

    @Override
    public String interpolate(String messageTemplate, Context context) {
        return interpolator.interpolate(messageTemplate, context, locale);
    }

    @Override
    public String interpolate(String messageTemplate, Context context, Locale locale) {
        return interpolator.interpolate(messageTemplate, context, locale);
    }
}
