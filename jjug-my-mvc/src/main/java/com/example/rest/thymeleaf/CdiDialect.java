package com.example.rest.thymeleaf;

import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

public class CdiDialect extends AbstractDialect implements IExpressionObjectDialect {

    private static final IExpressionObjectFactory EXPRESSION_OBJECT_FACTORY = new CdiExpressionFactory();

    public CdiDialect() {
        super("cdis");
    }

    @Override
    public IExpressionObjectFactory getExpressionObjectFactory() {
        return EXPRESSION_OBJECT_FACTORY;
    }
}
