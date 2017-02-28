package org.uva.taxfree.model.node.statement;

import org.uva.taxfree.model.node.expression.ConditionNode;

public class BooleanCalculatedField extends CalculatedField {
    public BooleanCalculatedField(String label, String id, ConditionNode condition) {
        super(label, id, condition);
    }
}
