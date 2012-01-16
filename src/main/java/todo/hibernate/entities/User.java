package todo.hibernate.entities;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.schema.JsonSchema;
import org.hibernate.annotations.Entity;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Will O'Brien
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "findAllUsernames",
                    query = "select u.username from User u")
              })
@Table(name = "user")
public class User implements Serializable {
    
    private String username;
    private String password;
    private Date created;
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static void main(String[] args) throws JsonMappingException {
        
        ObjectMapper mapper = new ObjectMapper();
        JsonSchema schema = mapper.generateJsonSchema(User.class);
        System.out.println(schema.toString());
        
    }
    
}
