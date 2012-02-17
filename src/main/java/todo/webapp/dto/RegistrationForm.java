package todo.webapp.dto;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.schema.JsonSchema;
import org.hibernate.annotations.GenericGenerator;
import todo.domain.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Encapsulates the data required to register.
 *
 * @author Will O'Brien
 */
public class RegistrationForm {

    private Long created;

    private String sessionId;
    
    private String username;
    private String email;
    private String password;

    public Date getCreated() {
        return new Date(created);
    }

    public void setCreated(Date created) {
        this.created = created.getTime();
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public User getUser() {

        User user = new User();

        try {
            user.setCreated(new Date());
            user.setPassword(this.getPassword());
            user.setEmail(getEmail());
            user.setUsername(getUsername());
            user.setEnabled(true);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Form is not in valid state", e);
        }

        return user;
    }

    public static void main(String[] args) throws JsonMappingException {

        ObjectMapper mapper = new ObjectMapper();
        JsonSchema schema = mapper.generateJsonSchema(RegistrationForm.class);
        System.out.println(schema.toString());

    }

}
