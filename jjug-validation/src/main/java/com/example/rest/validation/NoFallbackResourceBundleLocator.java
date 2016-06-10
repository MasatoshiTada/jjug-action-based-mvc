package com.example.rest.validation;

import org.hibernate.validator.spi.resourceloading.ResourceBundleLocator;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

/**
 * 指定されたロケールのValidationMessagesが無かった場合は、
 * JVMデフォルトロケール(ja)ではなく、ロケールなしのValidationMessagesを読み込みます。
 *
 * @see http://itpro.nikkeibp.co.jp/article/COLUMN/20071102/286295/
 */
class NoFallbackResourceBundleLocator implements ResourceBundleLocator {
    @Override
    public ResourceBundle getResourceBundle(Locale locale) {
        Control control = Control.getNoFallbackControl(Control.FORMAT_DEFAULT);
        ResourceBundle bundle = ResourceBundle.getBundle("ValidationMessages", locale, control);
        return bundle;
    }
}
