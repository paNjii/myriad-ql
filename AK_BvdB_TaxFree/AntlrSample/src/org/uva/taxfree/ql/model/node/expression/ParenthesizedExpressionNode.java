package org.uva.taxfree.ql.model.node.expression;

import org.uva.taxfree.ql.gui.MessageList;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.ql.model.types.Type;

import java.util.Set;

public class ParenthesizedExpressionNode extends ExpressionNode {
    private final ExpressionNode mExpression;

    public ParenthesizedExpressionNode(ExpressionNode expression) {
        super();
        mExpression = expression;
    }

    @Override
    public String evaluate() {
        return mExpression.evaluate();
    }


    @Override
    public boolean asBoolean() {
        return Boolean.valueOf(evaluate());
    }

    @Override
    public int asInteger() {
        return Integer.valueOf(evaluate());
    }

    @Override
    public String asString() {
        return evaluate();
    }

    @Override
    public void fillSymbolTable(SymbolTable symbolTable) {
        mExpression.fillSymbolTable(symbolTable);
    }

    @Override
    public Type getType() {
        return mExpression.getType();
    }

    @Override
    public void getDependencies(Set<String> dependencies) {
        mExpression.getDependencies(dependencies);
    }

    @Override
    public void checkSemantics(SymbolTable symbolTable, MessageList semanticsMessages) {
        mExpression.checkSemantics(symbolTable, semanticsMessages);
    }

    @Override
    protected boolean isConstant() {
        return mExpression.isConstant();
    }
}
