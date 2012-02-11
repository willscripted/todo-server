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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Represents a record for a single, atomic unit of work.
 *
 * @author Will O'Brien
 */
@Entity
@NamedQueries({
                      @NamedQuery(name = "findIncompleteTasks",
                                  query = "from Task t where t.complete = "
                                          + "false and t.user = :user")
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

    // Completed
    private boolean complete;

    @ManyToOne(optional = false)
    private User user;


    public Task() {
        this.complete = false;
        this.description = "";
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
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
