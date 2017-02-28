package ui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import value.StringValue;
import value.BoolValue;
import value.Value;
import ui.field.Field;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import ast.Form;
import ast.Question;
import ast.Visitor;
import ast.type.Type;

public class Questionnaire extends Application implements Notifier {
	
	// TODO static variables??
	private static Form form;
	private static Map<String, Value> answers;
	private static GridPane grid;
	
    public static void main(Form f) {
    	form = f;
    	answers = new HashMap<>();
        launch();
    }
        
    @Override
    public void start(Stage primaryStage) {
       
        primaryStage.setTitle(form.getId());
        
        grid = initGrid();
        
        Scene scene = new Scene(grid, 500, 275);
        primaryStage.setScene(scene);
             
        renderTitle(grid, form.getId());
        
        renderQuestionnaire(grid);
        
        primaryStage.show();

    }
    
    private GridPane initGrid() {
    	
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        return grid;
    }
    
    
    private void setAnswers(List<QuestionnaireQuestion> activeQuestions) {
    	
    	// TODO change
    	for (QuestionnaireQuestion question : activeQuestions) {
    		
    		Value value = answers.get(question.getName());
    		if (value == null) {
    			continue;
    		}
    		
    		question.setAnswer(value);
    	}
    }
    
    private void renderQuestionnaire(GridPane grid) {
        
    	List<QuestionnaireQuestion> activeQuestions = renderQuestions(grid);
        
    	setAnswers(activeQuestions);
    	
        Button btn = renderButton(grid, activeQuestions.size() + 2);
         
        // TODO move to function submit
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, activeQuestions.size() + 2);
                
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
            	for (QuestionnaireQuestion activeQuestion : activeQuestions) {
            		
            		Value answer = activeQuestion.getAnswer();
            		if (answer.getValue() == null) {
                        actiontarget.setFill(Color.FIREBRICK);
                        actiontarget.setText("Please Fill in all Fields");
                        return;
            		}
            		System.out.println(activeQuestion.getAnswer().getValue());
    
            	}  
                actiontarget.setFill(Color.SPRINGGREEN);
                actiontarget.setText("Thank you for filling\n in the questionnaire");
                
            }
        });
    }
    
    private void renderTitle(GridPane grid, String title) {
        Text scenetitle;
        scenetitle = new Text(title);
        
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
    }
    
    private List<QuestionnaireQuestion> renderQuestions(GridPane grid) {
        
    	QuestionnaireVisitor qVisitor = new QuestionnaireVisitor(answers);
    	qVisitor.visit(form);
    	List<QuestionnaireQuestion> activeQuestions = qVisitor.getActiveQuestions();
    	
    	int rowIndex = 0;
        for (QuestionnaireQuestion question : activeQuestions) {
            
        	Label questionLabel = new Label(question.getLabel());
            grid.add(questionLabel, 0, 1 + rowIndex); 
            Field field = question.getEntryField().getField();
            grid.add((Control) question.getEntryField().getField(), 1, 1 + rowIndex);
            
            field.addListener(this);
            ++rowIndex;
        }
        
        return activeQuestions;
    }
    
    private Button renderButton(GridPane grid, int rowIndex) {
    	 
        Button btn = new Button("Submit");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 3, rowIndex);
        
        return btn;
    }

	@Override
	// TODO change to already implemented observer pattern
	public void updateQuestionnaire(String name, Value newValue) {
    	Value oldAnswer = answers.get(name); 
		if (oldAnswer == null || !newValue.getValue().equals(oldAnswer.getValue())) {
			answers.put(name, newValue);
			// Save the title
			Node title = grid.getChildren().get(0);
	    	grid.getChildren().clear();
	    	grid.add(title, 0, 0);
	        renderQuestionnaire(grid);
		}
	}
}