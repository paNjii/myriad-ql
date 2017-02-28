package org.ql.typechecker.exception;


import org.ql.ast.Identifier;
import org.ql.ast.Node;

public class UndefinedIdentifierException extends RuntimeException implements TypeError {
    private final Identifier identifier;

    public UndefinedIdentifierException(Identifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public Node getNode() {
        return null;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}