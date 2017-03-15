package com.matthewchapman.ql.ast.expression.binary;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.ast.QLVisitable;
import com.matthewchapman.ql.validation.visitor.QLVisitor;

/**
 * Created by matt on 24/02/2017.
 * <p>
 * Integer addition class
 */
public class Addition extends BinaryOperation implements QLVisitable {

    public Addition(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return "(" + getLeft() + " + " + getRight() + ")";
    }

    @Override
    public <T> T accept(QLVisitor<T> visitor, String context) {
        return visitor.visit(this, context);
    }
}