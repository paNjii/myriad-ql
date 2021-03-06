package org.lemonade.gui;

import java.io.File;
import java.util.List;

import org.lemonade.visitors.EvaluateVisitor;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class QLGui {

    // Gui wide elements
    private Stage stage;
    private File questionnaireFile;

    // File selection elements
    private Scene selectionScene;
    private VBox errorBox;

    // Questionnaire elements
    private Scene questionnaireScene;
    private GridPane questionsGridPane;
    private int questionsRowCount;
    private Label fileStatusLabel;

    public QLGui(Stage stage, ButtonCallback buttonCallback) {
        this.stage = stage;
        setUpFileSelectionScene(buttonCallback);

        this.stage.setScene(selectionScene);
        this.stage.setWidth(1000);
        this.stage.setHeight(800);
        this.stage.setResizable(false);
        this.stage.show();
    }

    private void setUpFileSelectionScene(ButtonCallback buttonCallback) {
        stage.setTitle("Form");

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add((new FileChooser.ExtensionFilter("Select a .ql file", "*.ql")));

        final Label fileLabel = new Label();
        final Button openButton = new Button("Select a questionnaire");
        openButton.setOnAction(e -> {
            questionnaireFile = fileChooser.showOpenDialog(stage);
            if (questionnaireFile != null) {
                fileLabel.setText(questionnaireFile.getPath());
            }
        });

        final Button submitFileButton = new Button("Submit");
        submitFileButton.setOnAction(e -> {
            if (questionnaireFile != null) {
                setUpQuestionnaireScene(buttonCallback);
                if (buttonCallback.goToQuestionnaire(questionnaireFile))
                    this.stage.setScene(questionnaireScene);
            }
        });

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(openButton, submitFileButton, fileLabel);

        errorBox = new VBox();
        errorBox.setSpacing(6);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(errorBox);
        scrollPane.setPrefViewportHeight(650);
        scrollPane.setPrefViewportWidth(950);

        AnchorPane anchorPane = new AnchorPane();
        AnchorPane.setBottomAnchor(hBox, 10.0);
        AnchorPane.setLeftAnchor(hBox, 5.0);
        AnchorPane.setTopAnchor(scrollPane, 10.0);
        AnchorPane.setLeftAnchor(scrollPane, 5.0);
        anchorPane.getChildren().addAll(hBox, scrollPane);
        anchorPane.setPadding(new Insets(10, 10, 10, 10));

        selectionScene = new Scene(anchorPane);
    }

    private void setUpQuestionnaireScene(ButtonCallback buttonCallback) {
        // Set up GridPane for individual questions
        questionsGridPane = new GridPane();
        ColumnConstraints constraints = new ColumnConstraints();
        constraints.setPercentWidth(100);
        questionsGridPane.getColumnConstraints().addAll(constraints);
        this.questionsRowCount = 0;

        // Set callback function for submit button
        final Button submitQuestionnaireButton = new Button("Submit form");
        submitQuestionnaireButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add((new FileChooser.ExtensionFilter(".json", "*.json")));
            fileChooser.setTitle("Save to JSON");

            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                buttonCallback.submitForm(file);
            }
        });

        // Set callback function for back button
        final Button backButton = new Button("Select new questionnaire");
        backButton.setOnAction(e -> stage.setScene(selectionScene));

        // Set container for the footer items
        fileStatusLabel = new Label();
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(submitQuestionnaireButton, backButton, fileStatusLabel);

        // Combine all elements in a parent AnchorPane
        final AnchorPane rootGroup = new AnchorPane();
        AnchorPane.setBottomAnchor(hBox, 10.0);
        AnchorPane.setLeftAnchor(hBox, 5.0);
        AnchorPane.setTopAnchor(questionsGridPane, 10.0);
        AnchorPane.setLeftAnchor(questionsGridPane, 5.0);
        AnchorPane.setRightAnchor(questionsGridPane, 5.0);
        rootGroup.getChildren().addAll(questionsGridPane, hBox);
        rootGroup.setPadding(new Insets(10, 10, 10, 10));

        questionnaireScene = new Scene(rootGroup);
    }

    public void addErrors(String message, List<String> errors) {
        errorBox.getChildren().clear();
        errorBox.getChildren().add(new Label(message));

        errors.forEach(error -> {
            Label label = new Label(error);
            label.setTextFill(Color.RED);
            errorBox.getChildren().add(label);
        });
    }

    public void addQuestion(GuiQuestion guiQuestion) {
        final GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(3, 6, 3, 6));

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        gridPane.getColumnConstraints().addAll(col1, col2);

        GridPane.setConstraints(guiQuestion.getLabelElement().getWidget(), 0, 0);
        GridPane.setConstraints(guiQuestion.getElement().getWidget(), 1, 0);
        gridPane.getChildren().addAll(guiQuestion.getLabelElement().getWidget(), guiQuestion.getElement().getWidget());
        gridPane.managedProperty().bind(gridPane.visibleProperty());

        questionsGridPane.addRow(questionsRowCount++, gridPane);
    }

    public void addUserInputListeners(GuiForm guiRoot, EvaluateVisitor evaluateVisitor) {
        Parent rootGroup = questionnaireScene.getRoot();
        rootGroup.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> guiRoot.accept(evaluateVisitor));
        rootGroup.addEventFilter(KeyEvent.KEY_RELEASED, e -> guiRoot.accept(evaluateVisitor));
    }

    public void updateFileStatus(String message, boolean successful) {
        if (successful)
            fileStatusLabel.setTextFill(Color.BLACK);
        else
            fileStatusLabel.setTextFill(Color.RED);

        fileStatusLabel.setText(message);
    }

}
