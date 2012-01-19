/*
 * File: Task.java
 * Author: Will O'Brien
 * Copyright: Will O'Brien (c) 2011
 */
package todo.domain;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.schema.JsonSchema;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Represents a record for a single, atomic unit of work.
 *
 * @author Will O'Brien
 */
@Entity
@NamedQueries({
                      @NamedQuery(name = "findCurrentTasks",
                                  query = "from Task t where t.complete = "
                                          + "false and t.cur= true"),
                      @NamedQuery(name = "findPendingTasks",
                                  query = "from Task t where t.complete = "
                                          + "false and t.cur = false"),
                      @NamedQuery(name = "countCurrent",
                                  query = "select count(*) from Task t where "
                                          + "t.cur = true and t.complete = "
                                          + "false"),
                      @NamedQuery(name = "countPending",
                                  query = "select count(*) from Task t where "
                                          + "t.cur = false and t.complete = "
                                          + "false"),
                      @NamedQuery(name = "countOutstanding",
                                  query = "select count(*) from Task t where "
                                          + "t.complete = false")
              })
@Table(name = "task")
public class Task implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",
                      strategy = "increment")
    private Long id;


    // Task
    @Column(nullable = false)
    private String title;

    @Lob
    private String description;

    // Timing
    private Long estTime; // in milliseconds
    private Long timeSpent; // in milliseconds

    // Completed
    private boolean complete;
    private boolean cur;

    // Vars that affect points
    private int priority;

    @Column(nullable = false)
    private User user;


    public Task() {
        this.complete = false;
        this.cur = false;
        this.description = "";
        this.estTime = -1L;
        this.priority = 10;
        this.timeSpent = 0L;
        this.title = "";

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getEstTime() {
        return estTime;
    }

    public void setEstTime(Long estTime) {
        this.estTime = estTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isCur() {
        return cur;
    }

    public void setCur(boolean cur) {
        this.cur = cur;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Long getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Long timeSpent) {
        this.timeSpent = timeSpent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static void main(String[] args) throws JsonMappingException {

        ObjectMapper mapper = new ObjectMapper();
        JsonSchema schema = mapper.generateJsonSchema(Task.class);
        System.out.println(schema.toString());

    }

}
