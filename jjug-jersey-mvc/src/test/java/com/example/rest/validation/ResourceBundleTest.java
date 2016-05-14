package com.example.rest.validation;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @see http://itpro.nikkeibp.co.jp/article/COLUMN/20071102/286295/
 */
public class ResourceBundleTest {

    @Test
    public void getNoFallbackControlを指定したらロケールなしのプロパティファイルが読み込まれる() {
        ResourceBundle.Control control =
                ResourceBundle.Control.getNoFallbackControl(
                        ResourceBundle.Control.FORMAT_DEFAULT);
        // "cs"はプロパティファイルを準備していないロケール
        ResourceBundle bundle = ResourceBundle.getBundle("ValidationMessages", new Locale("cs"), control);
        String actual = bundle.getString("employee.id.notblank");
        String expected = "(Def)Employee ID must not be blank.";
        assertThat(actual, is(expected));
    }

    @Test
    public void getNoFallbackControlを指定したなかったらロケールjaのプロパティファイルが読み込まれる() {
        // "cs"はプロパティファイルを準備していないロケール
        ResourceBundle bundle = ResourceBundle.getBundle("ValidationMessages", new Locale("cs"));
        String actual = bundle.getString("employee.id.notblank");
        String expected = "社員IDは必須入力です。";
        assertThat(actual, is(expected));
    }

    @Test
    public void ロケールROOTを指定したらロケールなしのプロパティファイルが読み込まれる() {
        ResourceBundle bundle = ResourceBundle.getBundle("ValidationMessages", Locale.ROOT);
        String actual = bundle.getString("employee.id.notblank");
        String expected = "(Def)Employee ID must not be blank.";
        assertThat(actual, is(expected));
    }
}
