package org.ql;

import javafx.application.Application;
import javafx.stage.Stage;
import org.ql.ast.Form;
import org.ql.gui.GUIHandlerSingleton;
import org.ql.parser.Parser;
import org.ql.typechecker.TypeChecker;
import org.ql.typechecker.issues.IssuesStorage;

public class Main extends Application {
    public static void main(String args[]) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Form form = runParser();

        if(hasFormTypeErrors(form)) {
            System.out.println("TypeChecker: An issue was found!");
        } else {
            runGUI(primaryStage, form);
        }
    }

    // TODO: Get exampleForm file from the project.
    public Form runParser() {
        Parser parser = new Parser();
        Form form = parser.parseForm(
                "form TestForm {" +
                    "string yourName: \"What's your name?\" = \"testname\";" +
                    "boolean hasSoldHouse: \"Did you sell a house in 2010?\" = false;" +
                    "if (hasSoldHouse) {" +
                        "boolean sellingPrice: \"What was the selling price?\" = true;" +
                    "}" +
                "}");

        return form;
    }

    public boolean hasFormTypeErrors(Form form) {
        TypeChecker typeChecker = new TypeChecker();
        IssuesStorage issues = typeChecker.checkForm(form);

        return issues.hasErrors();
    }

    public void runGUI(Stage primaryStage, Form form) {
        GUIHandlerSingleton guiHandler = new GUIHandlerSingleton(primaryStage, form);
        guiHandler.guiHandler.runGUI();
    }
}
