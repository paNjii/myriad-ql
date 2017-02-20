package ql.ast.literals;

import ql.ast.ASTNode;
import ql.ast.Expr;
import ql.ast.QLLiteral;
import ql.ast.expressions.numop.Add;
import ql.ast.visistor.ASTVisitor;

/**
 * Created by Erik on 7-2-2017.
 */
public class QLInt extends QLLiteral {
    private int qlInteger;

    public QLInt(int qlInteger){
        this.qlInteger = qlInteger;
    }

    public int getQlInteger() {
        return qlInteger;
    }

    @Override
    public String toString() {
        return String.valueOf(qlInteger);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public QLLiteral add(QLLiteral other) {
        return other.addEval(this);
    }

    public QLLiteral addEval(QLInt other) {
        return new QLInt(this.getQlInteger() + other.getQlInteger());
    }

    public QLLiteral addEval(QLFloat other) {
        return new QLFloat(this.getQlInteger() + other.getQlFloat());
    }

    @Override
    public QLLiteral div(QLLiteral other) {
        return other.divEval(this);
    }

    @Override
    public QLLiteral divEval(QLInt other) {
        return new QLInt(other.getQlInteger() / this.getQlInteger());
    }

    @Override
    public QLLiteral eq(QLLiteral other) {
        return new QLBoolean(other.equals(this));
    }

    @Override
    public QLLiteral gEq(QLLiteral other) {
        return (new QLBoolean(other.equals(this))).or(gT(other));
    }

    @Override
    public QLLiteral gT(QLLiteral other) {
        return lEq(other).not();
    }

    @Override
    public QLLiteral gTEval(QLInt other) {
        return  new QLBoolean(this.getQlInteger() > other.getQlInteger());
    }

    @Override
    public QLLiteral lEq(QLLiteral other) {
        return (new QLBoolean(other.equals(this))).or(lT(other));
    }

    @Override
    public QLLiteral lT(QLLiteral other) {
        return other.gTEval(this);
    }

    @Override
    public QLLiteral mul(QLLiteral other) {
        return other.mulEval(this);
    }

    @Override
    public QLLiteral mulEval(QLInt other) {
        return new QLInt(this.getQlInteger() * other.getQlInteger());
    }

    @Override
    public QLLiteral nEq(QLLiteral other) {
        return new QLBoolean(!other.equals(this));
    }

    @Override
    public QLLiteral sub(QLLiteral other) {
        return other.neg().add(this);
    }

    @Override
    public QLLiteral neg() {
        return new QLInt(-getQlInteger());
    }

    @Override
    public QLLiteral pos() {
        return new QLInt(-getQlInteger());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QLInt qlInt = (QLInt) o;

        return qlInteger == qlInt.qlInteger;
    }
}
