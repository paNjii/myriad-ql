package QL.ast.expression;

import QL.ast.ExpressionVisitor;
import QL.ast.Node;


public abstract class Expression extends Node {
	
	public Expression(int line) {
		super(line);
	}

	public abstract <T> T accept(ExpressionVisitor<T> v);
	
}
