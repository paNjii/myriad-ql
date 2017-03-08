package org.uva.taxfree.model.node.blocks;

import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.node.Node;
import org.uva.taxfree.model.node.expression.ExpressionNode;

import java.util.Set;

public class IfStatementNode extends BlockNode {
    private final ExpressionNode expression;

    public IfStatementNode(ExpressionNode expression, Set<Node> children) {
        super(children);
        this.expression = expression;
    }

    @Override
    protected boolean isVisible() {
        return ("true".equals(expression.evaluate()));
    }

    @Override
    public void setVisible(boolean isVisible) {
        super.setVisible(isVisible() && isVisible);
    }

    @Override
    public void fillSymbolTable(SymbolTable symbolTable) {
        symbolTable.addExpression(expression);
    }
}