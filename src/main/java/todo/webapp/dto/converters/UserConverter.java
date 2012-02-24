package todo.webapp.dto.converters;

import org.dozer.CustomConverter;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import todo.domain.User;
import todo.services.UserService;

/**
 *
 */
public class UserConverter implements CustomConverter {

    @Autowired
    private UserService userService;

    public UserConverter() {
    }

    @Override
    public Object convert(Object existingDestinationFieldValue,
                          Object sourceFieldValue, Class<?> destinationClass,
                          Class<?> sourceClass) {
        // If null return null
        if(sourceFieldValue == null) {
            return null;
        }

        // String -> User
        if(sourceFieldValue instanceof String) {
            return userService.getUserByUsername((String) sourceFieldValue);
        }
        // User -> String
        else if(sourceFieldValue instanceof User) {
            User user = (User) sourceFieldValue;
            return user.getUsername();
        }
        // Else err
        else {
            throw new MappingException("Could not convert.");
        }
    }
}
