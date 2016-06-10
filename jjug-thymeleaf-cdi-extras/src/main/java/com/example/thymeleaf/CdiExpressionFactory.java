package com.example.thymeleaf;

import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.expression.IExpressionObjectFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CdiExpressionFactory implements IExpressionObjectFactory {

    private static final String EXPRESSION_OBJECT_NAME = "cdi";

    private static final Set<String> ALL_EXPRESSION_OBJECT_NAMES =
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(EXPRESSION_OBJECT_NAME)));

    @Override
    public Set<String> getAllExpressionObjectNames() {
        return ALL_EXPRESSION_OBJECT_NAMES;
    }

    @Override
    public Object buildObject(IExpressionContext context, String expressionObjectName) {
        if (EXPRESSION_OBJECT_NAME.equals(expressionObjectName)) {
            return new Cdi();
        }
        return null;
    }

    @Override
    public boolean isCacheable(String expressionObjectName) {
        return false;
    }
}
