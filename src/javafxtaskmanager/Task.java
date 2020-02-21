package javafxtaskmanager;

import java.util.Date;

/**
 *
 * @author Tristan
 * @version 1.0 initializing Task class
 *      > added necessary field variables
 *      > added constructors
 *      > added accessor methods
 *      > added mutator methods
 *      > override toString() method
 */
public class Task {
    
    private String title;
    private String description;
    private Date dueDate;
    private int priority;
    
    public Task() {
        this("", "",new Date(), 0);
    }
    
    public Task(String title, String description, Date dueDate, int priority) {
        setTitle(title);
        setDescription(description);
        setDueDate(dueDate);
        setPriority(priority);
    }
    
    public String getTitle()       { return this.title; }
    public String getDescription() { return this.description; }
    public Date   getDueDate()     { return this.dueDate; }
    public int    getPriority()    { return this.priority; }
    
    public void setTitle(String title)             { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setDueDate(Date date)              { this.dueDate = date; }
    public void setPriority(int priority)          { this.priority = priority; }
    
    @Override
    public String toString() {
        return getTitle();
    }
}
