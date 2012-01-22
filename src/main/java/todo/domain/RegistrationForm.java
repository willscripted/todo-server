package todo.domain;

import org.hibernate.annotations.Entity;

import javax.persistence.Id;
import java.util.Date;

/**
 * Encapsulates the data required to register.
 *
 * @author Will O'Brien
 */
@Entity
public class RegistrationForm {

    @Id
    private Long id;

    private Long created;
    private Long sessionId;

    private Boolean agreeToTOS;
    
    private String username;
    private String email;
    private String password;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return new Date(created);
    }

    public void setCreated(Date created) {
        this.created = created.getTime();
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Boolean getAgreeToTOS() {
        return agreeToTOS;
    }

    public void setAgreeToTOS(Boolean agreeToTOS) {
        this.agreeToTOS = agreeToTOS;
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
}
