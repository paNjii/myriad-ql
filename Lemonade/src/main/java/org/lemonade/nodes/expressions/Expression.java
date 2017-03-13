package org.lemonade.nodes.expressions;

import org.lemonade.nodes.ASTNode;
import org.lemonade.nodes.Position;
import org.lemonade.visitors.ASTVisitor;
import org.lemonade.visitors.interfaces.ExpressionVisitor;

public abstract class Expression extends ASTNode {

    public Expression() {
        super();
    }
    public abstract <T> T accept(ExpressionVisitor<T> visitor);
}
