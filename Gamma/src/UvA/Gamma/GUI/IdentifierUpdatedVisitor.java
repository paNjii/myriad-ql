package UvA.Gamma.GUI;

import UvA.Gamma.AST.Computed;
import UvA.Gamma.AST.Condition;
import UvA.Gamma.AST.Expression.Values.IdentifierValue;
import UvA.Gamma.Visitors.BaseVisitor;

/**
 * Created by Tjarco, 05-04-17.
 */
public class IdentifierUpdatedVisitor extends BaseVisitor {
    private IdentifierValue updatedValue;

    IdentifierUpdatedVisitor(IdentifierValue updatedValue) {
        this.updatedValue = updatedValue;
    }


    @Override
    public void visit(Computed computed) {
        computed.updateValue();
    }

    @Override
    public void visit(Condition condition) {
        condition.evaluateExpression();
    }

    @Override
    public void visit(IdentifierValue value) {
        assert updatedValue != null;
        value.updateValue(updatedValue);
    }
}
