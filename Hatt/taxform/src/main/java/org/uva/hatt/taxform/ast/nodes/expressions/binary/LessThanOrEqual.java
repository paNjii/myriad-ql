package org.uva.hatt.taxform.ast.nodes.expressions.binary;

import org.uva.hatt.taxform.ast.nodes.expressions.Expression;
//import org.uva.hatt.taxform.ast.visitors.ExpressionVisitor;

public class LessThanOrEqual extends Binary{

    public LessThanOrEqual(int lineNumber, Expression lhs, Expression rhs){
        super(lineNumber, lhs, rhs);
    }

    //@Override
//    public <T> T accept(ExpressionVisitor<T> expressionVisitor){
//        return expressionVisitor.visit(this);
//    }
}
