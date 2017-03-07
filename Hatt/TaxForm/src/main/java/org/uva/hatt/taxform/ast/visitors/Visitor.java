package org.uva.hatt.taxform.ast.visitors;

import org.uva.hatt.taxform.ast.nodes.*;
import org.uva.hatt.taxform.ast.nodes.expressions.BooleanExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.ComputationExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.Expression;
import org.uva.hatt.taxform.ast.nodes.expressions.GroupedExpression;
import org.uva.hatt.taxform.ast.nodes.items.*;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.BooleanLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.Identifier;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.IntegerLiteral;
import org.uva.hatt.taxform.ast.nodes.expressions.literals.StringerLiteral;
import org.uva.hatt.taxform.ast.nodes.types.*;
import org.uva.hatt.taxform.ast.nodes.types.Boolean;
import org.uva.hatt.taxform.ast.nodes.types.Integer;
import org.uva.hatt.taxform.ast.nodes.types.String;

public interface Visitor<T> {
    T visit(Form node);
    T visit(FormId node);
    T visit(Question node);
    T visit(Conditional node);
    T visit(Boolean node);
    T visit(Integer node);
    T visit(Money node);
    T visit(String node);
    T visit(ValueType node);
    T visit(BooleanExpression node);
    T visit(ComputationExpression node);
    T visit(GroupedExpression node);
    T visit(Identifier identifier);
    T visit(StringerLiteral stringerLiteral);
    T visit(IntegerLiteral integerLiteral);
    T visit(BooleanLiteral booleanLiteral);
    T visit(Expression expression);
}
