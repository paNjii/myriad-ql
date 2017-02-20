package ast.atom;

import ast.Visitor;
import ast.expression.Expression;

public abstract class Atom extends Expression {
	
	public Number getNumber() {
		return null;
	}
	
	// TODO do we want to add this?
	@Override
	public abstract void accept(Visitor v);
	
}