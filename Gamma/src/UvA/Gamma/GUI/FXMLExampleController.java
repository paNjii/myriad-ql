package UvA.Gamma.GUI;

import UvA.Gamma.AST.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class FXMLExampleController {
    private int rowCount;
    private Form form;


    public void addFormItem(Form form) {
        this.form = form;
        for (FormItem item : form.getFormItems()) {
            item.show(this);
        }
    }

    @FXML
    private GridPane grid;

    @FXML
    public void showQuestion(Question question) {
        Text questionLabel = new Text(question.getQuestion());
        TextField input = new TextField();

        input.textProperty().addListener((observable, oldValue, newValue) ->
                form.getFormItems().parallelStream().forEach(item -> item.idChanged(form, question, newValue))
        );

        grid.addRow(++rowCount, questionLabel, input);
    }

    public void showComputed(Computed computed) {
        Text label = new Text(computed.getLabel());
        Text result = new Text(computed.getStringValueProperty().get());

        result.textProperty().bind(computed.getStringValueProperty());

        grid.addRow(++rowCount, label, result);
    }

    public void showCondition(Condition condition) {
        if (condition.evaluateExpression()) {
            condition.getFormItems().forEach(item -> item.show(this));
        } else {
            condition.getElseBlockItems().forEach(item -> item.show(this));
        }
    }

}