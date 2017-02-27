package org.ql.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.ql.ast.Expression;
import org.ql.ast.Form;
import org.ql.ast.Node;
import org.ql.ast.Statement;
import org.ql.grammar.QLLexer;
import org.ql.grammar.QLParser;
import org.ql.grammar.QLVisitor;

public class Parser {

    private final QLVisitor<Node> visitor = new AntlrVisitor();

    public Form parseForm(String code) {
        return (Form) visitor.visit(createParser(code).form());
    }

    public Statement parseStatement(String code) {
        return (Statement) visitor.visit(createParser(code).statement());
    }

    public Expression parseExpression(String code) {
        return (Expression) visitor.visit(createParser(code).expression());
    }

    private QLParser createParser(String code) {
        return new QLParser(new CommonTokenStream(new QLLexer(new ANTLRInputStream(code))));
    }
}
