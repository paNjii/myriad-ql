package org.lemonade.nodes.expressions.binary;

import org.lemonade.nodes.expressions.BinaryExpression;
import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class PlusBinary extends BinaryExpression {

    public PlusBinary() {
        super();
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}