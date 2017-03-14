/**
 * QLTestSetUp.java.
 */

package ql.unittests;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import ql.antlr.QLLexer;
import ql.antlr.QLParser;
import ql.astnodes.ASTVisitor;
import ql.astnodes.Form;

import org.junit.Before;
import ql.astnodes.Node;
import ql.astnodes.types.Type;
import ql.gui.formenvironment.Context;
import ql.semanticchecker.IdentifierChecker;
import ql.semanticchecker.TypeChecker;
import ql.semanticchecker.messagehandling.MessageData;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public abstract class QLTestSetUp {

    protected Form form;
    protected Context formContext;
    protected IdentifierChecker identifierChecker;
    protected TypeChecker typeChecker;
    protected String inputFileName;

    private static final String PATH = "./src/ql/unittests/qltestforms/";

    @Before
    public void setUp() throws IOException {

        if (inputFileName == null) {
            System.err.println("Undefined filename.");
            System.exit(-1);
        }

        String inputFilePath = PATH.concat(inputFileName);

        InputStream qlInputStream = new FileInputStream(inputFilePath);

        ANTLRInputStream input = new ANTLRInputStream(qlInputStream);
        QLLexer lexer = new QLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);

        ParseTree parseTree = parser.form();
        ASTVisitor astVisitor = new ASTVisitor();
        Node nodeAST = parseTree.accept(astVisitor);

        form = (Form) nodeAST;
        formContext =  new Context();

        MessageData messages = new MessageData();
        Map<String, Type> identifierToTypeMap = new HashMap<>();

        identifierChecker = new IdentifierChecker(form, identifierToTypeMap, messages);
        typeChecker= new TypeChecker(form, identifierToTypeMap, messages);
    }
}
