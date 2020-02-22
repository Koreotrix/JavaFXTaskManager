package javafxtaskmanager;

import java.util.Date;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 *
 * @author Tristan
 * @version 1.1 Removing tasks from the task list
 *      > extends HBox
 *      > change title from String to Label
 *      > added removeButton
 */
public class TaskItem extends HBox{
    
    private Label titleLabel = new Label();
    private Button removeButton = new Button();
    
    private String description;
    private Date dueDate;
    private int priority;
    
    public TaskItem() {
        this("", "",new Date(), 0);
    }
    
    public TaskItem(String title, String description, Date dueDate, int priority) {
        super();
        
        setTitle(title);
        titleLabel.setMaxWidth(Double.MAX_VALUE);
        setDescription(description);
        setDueDate(dueDate);
        setPriority(priority);
        HBox.setHgrow(titleLabel, Priority.ALWAYS);
        
        removeButton.setText("-");
        
        
        this.getChildren().addAll(titleLabel, removeButton);
    }
    
    public String getTitle()        { return this.titleLabel.getText(); }
    public String getDescription()  { return this.description; }
    public Date   getDueDate()      { return this.dueDate; }
    public int    getPriority()     { return this.priority; }
    public Button getRemoveButton() { return this.removeButton; }
    
    public void setTitle(String title)             { titleLabel.setText(title); }
    public void setDescription(String description) { this.description = description; }
    public void setDueDate(Date date)              { this.dueDate = date; }
    public void setPriority(int priority)          { this.priority = priority; }
    public void setButton(Button button)           { this.removeButton = button; }
    
    @Override
    public String toString() {
        return getTitle();
    }
}
