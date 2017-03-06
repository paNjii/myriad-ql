package com.matthewchapman.ql.core;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.ast.statement.IfElseStatement;
import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.validator.AbstractQLVisitor;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Created by matt on 02/03/2017.
 */
public class CoreParserTest extends AbstractQLVisitor<Void> {

    private String testInput;
    private CoreParser parser = new CoreParser();
    private int questionsCount = 0;
    private int ifStatementCount = 0;
    private int ifElseStatementCount = 0;

    @Before
    public void setUp() {
        FileReader reader = new FileReader();
        testInput = reader.readFile(new File("res/test.txt"));
    }

    @Test
    public void buildQLAST() {
        Form form = parser.buildQLAST(testInput);

        final int EXPECTED_STATEMENTS = 8;
        final int EXPECTED_QUESTIONS = 5;
        final int EXPECTED_IFS = 2;
        final int EXPECTED_IF_ELSES = 1;

        for(Statement statement : form.getStatements()) {
            statement.accept(this);
        }

        assertEquals(EXPECTED_STATEMENTS, form.getStatements().size());
        assertEquals(EXPECTED_QUESTIONS, questionsCount);
        assertEquals(EXPECTED_IFS, ifStatementCount);
        assertEquals(EXPECTED_IF_ELSES, ifElseStatementCount);
    }

    @Override
    public Void visit(Question question) {
        ++questionsCount;
        return null;
    }

    @Override
    public Void visit(IfStatement ifStatement) {
        ++ifStatementCount;
        return null;
    }

    @Override
    public Void visit(IfElseStatement ifElseStatement) {
        ++ifElseStatementCount;
        return null;
    }
}