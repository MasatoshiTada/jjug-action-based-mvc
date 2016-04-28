package com.example.rest.feature;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ValidationTemplate {
    /**
     * 検証エラー時に遷移すべき画面のパスを指定します。
     */
    String value();
}
