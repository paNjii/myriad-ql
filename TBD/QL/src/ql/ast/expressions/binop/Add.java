package ql.ast.expressions.binop;

import ql.ast.Expr;
import ql.ast.expressions.BinOp;
import ql.ast.visistor.ASTVisitor;

/**
 * Created by Erik on 7-2-2017.
 */
public class Add extends BinOp {

    public Add(Expr left, Expr right, int rowNumber) {
        super(left, right, rowNumber);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
