package javafxtaskmanager;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Tristan
 * @version 1.1 Removing tasks from the task list
 *      > added button to taskListView items
 *      > added remove functionality to each items in taskListView
 */
public class managerController implements Initializable {
    
    @FXML private AnchorPane mainScene;
    @FXML private Button closeWindowButton, resizeWindowButton, 
            minimiseWindowButton, addTaskButton;
    @FXML private ListView taskListView;
    @FXML private TextField newTaskTextField;
    
    private List<TaskItem> taskList;
    private ObservableList<TaskItem> data;
    
    private boolean isMaximized;
    
    // ACTION METHODS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // main scene config
        
        // variable config
        
        // Buttons config
        this.initializeWindowButtons();
        this.initializeFunctionalButtons();
        
        // ListView config
        this.initializeTaskList();
        
    }    
    
    private void initializeWindowButtons() {
        
        // close button config
        // action closes application
        closeWindowButton.setOnAction(event -> {
            
            Platform.exit(); 
            
        });
        // hover in: fade transition effect
        closeWindowButton.setOnMouseEntered(event -> {
            
            closeWindowButton.setStyle("-fx-background-color:#DB1021;"
                    + "-fx-text-fill:white;" 
                    + "-fx-background-radius:0;");
            
            FadeTransition fade = new FadeTransition(Duration.millis(100), 
                    closeWindowButton);
            fade.setFromValue(0.0);
            fade.setToValue(1.0);
            fade.play();
            
        });
        // hover out: fade transition effect
        closeWindowButton.setOnMouseExited(event -> {
            
            closeWindowButton.setStyle("-fx-background-color:#323336;"
                    + "-fx-text-fill:white;" 
                    + "-fx-background-radius:0;");
            
        });
        
        // resize window config
        // action fits application to screen size or to previous application size
        resizeWindowButton.setOnAction(event -> {
            
            isMaximized = !isMaximized;
            
            Stage stage = (Stage) mainScene.getScene().getWindow();
            stage.setMaximized(isMaximized);
            
        });
        // hover in: fade transition effect
        resizeWindowButton.setOnMouseEntered(event -> {
            
            resizeWindowButton.setStyle("-fx-background-color:#808486;"
                    + "-fx-text-fill:white;" 
                    + "-fx-background-radius:0;");
            
            FadeTransition fade = new FadeTransition(Duration.millis(100), 
                    resizeWindowButton);
            fade.setFromValue(0.0);
            fade.setToValue(1.0);
            fade.play();
            
        });
        // hover out: fade transition effect
        resizeWindowButton.setOnMouseExited(event -> {
            
            resizeWindowButton.setStyle("-fx-background-color:#323336;"
                    + "-fx-text-fill:white;" 
                    + "-fx-background-radius:0;");
            
        });
        
        // minimise window config
        // action moves application to background
        minimiseWindowButton.setOnAction(event -> {
            
            Stage stage = (Stage) mainScene.getScene().getWindow();
            stage.setIconified(true);
            
        });
        // hover in: fade transition effect
        minimiseWindowButton.setOnMouseEntered(event -> {
            
            minimiseWindowButton.setStyle("-fx-background-color:#808486;"
                    + "-fx-text-fill:white;" 
                    + "-fx-background-radius:0;");
            
            FadeTransition fade = new FadeTransition(Duration.millis(100), 
                    minimiseWindowButton);
            fade.setFromValue(0.0);
            fade.setToValue(1.0);
            fade.play();
            
        });
        // hover out: fade transition effect
        minimiseWindowButton.setOnMouseExited(event -> {
            
            minimiseWindowButton.setStyle("-fx-background-color:#323336;"
                    + "-fx-text-fill:white;" 
                    + "-fx-background-radius:0;");
        });
    }
    private void initializeFunctionalButtons() {
        
        // add button config
        // action: adds a new task into the list
        addTaskButton.setOnMouseClicked(event -> {
            
            String task = newTaskTextField.getText();

            // tasks will not be added if newTaskTextField is empty
            if (!task.isEmpty()) {
                
                TaskItem taskItem = new TaskItem (task, "this is a test", 
                    new Date(), 1);
                
                taskList.add(taskItem);
                
                // action: removes the selected task from the list
                Button button = taskItem.getRemoveButton();
                
                button.setOnMouseClicked(removeEvent -> {
                    taskList.remove(taskItem);
                    taskListView.getItems().remove(taskItem);
                    
                    data = FXCollections.observableArrayList(taskList);
                    taskListView.setItems(data);
                });
                
                // update taskListView
                data = FXCollections.observableArrayList(taskList);
                taskListView.setItems(data);

                newTaskTextField.setText("");
            }
            
        });
        // hover in: fade transition effect
        addTaskButton.setOnMouseEntered(event -> {
            
            addTaskButton.setStyle("-fx-background-color:#4267B2;"
                    + "-fx-text-fill:white;" 
                    + "-fx-background-radius:30;");
            
            FadeTransition fade = new FadeTransition(Duration.millis(100), 
                    addTaskButton);
            fade.setFromValue(0.0);
            fade.setToValue(1.0);
            fade.play();
            
        });
        // hover out: fade transition effect
        addTaskButton.setOnMouseExited(event -> {
            
            addTaskButton.setStyle("-fx-background-color:#636468;"
                    + "-fx-text-fill:white;" 
                    + "-fx-background-radius:30;");
            
        });
    }
    private void initializeTaskList() {
        
        // initiating task list view
        taskList = new ArrayList<TaskItem>();
        
        data = FXCollections.observableList(taskList);
        taskListView.setItems(data);
        
        // action: check selected item
        taskListView.setOnMouseClicked(event -> {
            
            TaskItem task = (TaskItem) taskListView.getSelectionModel().getSelectedItem();
            
            String newTitle = task.getTitle();
            if (newTitle.charAt(0) == '✔') {
                newTitle = newTitle.substring(2);
            } else {
                newTitle = "✔ " + task.getTitle();
            }
            task.setTitle(newTitle);

            
            int index = taskListView.getSelectionModel().getSelectedIndex();
            taskListView.getItems().set(index, task);
            
        });
        
    }
}
