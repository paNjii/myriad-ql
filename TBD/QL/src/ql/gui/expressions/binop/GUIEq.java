package ql.gui.expressions.binop;

import ql.gui.GUIEvaluator;
import ql.gui.GUIExpr;
import ql.gui.expressions.GUIBinOp;

/**
 * Created by Erik on 7-2-2017.
 */
public class GUIEq extends GUIBinOp {

    public GUIEq(GUIExpr left, GUIExpr right) {
        super(left, right);
    }

    @Override
    public <T> T accept(GUIEvaluator<T> visitor) {
        return visitor.visit(this);
    }
}
