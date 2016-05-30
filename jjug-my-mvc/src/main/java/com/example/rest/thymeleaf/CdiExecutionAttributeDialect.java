package com.example.rest.thymeleaf;

import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.standard.StandardDialect;
import org.thymeleaf.standard.expression.IStandardVariableExpression;
import org.thymeleaf.standard.expression.IStandardVariableExpressionEvaluator;
import org.thymeleaf.standard.expression.StandardExpressionExecutionContext;

public class CdiExecutionAttributeDialect extends StandardDialect {

    // TODO: 未実装

    private static class CdiExtendedVariableExpressionevaluator implements IStandardVariableExpressionEvaluator {

        @Override
        public Object evaluate(IExpressionContext context, IStandardVariableExpression expression, StandardExpressionExecutionContext expContext) {
            String expressionStr = expression.getExpression();
            System.out.println("================================= ");
            System.out.println(expressionStr);
            System.out.println("================================= ");
            return null;
        }
    }
}
