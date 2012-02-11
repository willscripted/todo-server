package todo.domain;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.schema.JsonSchema;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Will O'Brien
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "findAllUsernames",
                    query = "select u.username from User u"),
        @NamedQuery(name = "removeUserWithUsername",
                    query = "delete from User u where u.username=:username")
              })
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",
                      strategy = "increment")
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private long created;

    private boolean enabled;

    @ElementCollection
    private Set<String> authorities;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email == null) {
            throw new IllegalArgumentException();
        }
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreated() {
        return new Date(created);
    }

    public void setCreated(Date created) {
        if(created == null) {
            throw new IllegalArgumentException();
        }
        this.created = created.getTime();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        if(password == null) {
            throw new IllegalArgumentException();
        }
        this.password = password;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        if(username == null) {
            throw new IllegalArgumentException();
        }
        this.username = username;
    }

    public static void main(String[] args) throws JsonMappingException {
        
        ObjectMapper mapper = new ObjectMapper();
        JsonSchema schema = mapper.generateJsonSchema(User.class);
        System.out.println(schema.toString());
        
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {

        if(id == null) {
            throw new IllegalArgumentException();
        }
        this.id = id;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }
}
