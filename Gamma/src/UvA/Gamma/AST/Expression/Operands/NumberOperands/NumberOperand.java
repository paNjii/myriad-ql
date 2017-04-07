package UvA.Gamma.AST.Expression.Operands.NumberOperands;

import UvA.Gamma.AST.Expression.Expression;
import UvA.Gamma.Visitors.Visitor;

/**
 * Created by Tjarco, 21-03-17.
 */
public abstract class NumberOperand extends Expression {
    protected Expression left;
    protected Expression right;

    public NumberOperand(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        left.accept(visitor);
        right.accept(visitor);
    }

    @Override
    public String toString() {
        return this.left.toString() + this.right.toString();
    }
}
