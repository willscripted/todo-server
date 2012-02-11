package todo.webapp.dto.converters;

import org.dozer.CustomConverter;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import todo.domain.User;
import todo.persistence.UserDao;

/**
 *
 */
public class UserConverter implements CustomConverter {

    @Autowired
    private UserDao userDao;

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
        if(sourceFieldValue instanceof Long) {
            return userDao.findByPrimaryKey(sourceFieldValue);
        }
        // User -> String
        else if(sourceFieldValue instanceof User) {
            User user = (User) sourceFieldValue;
            return user.getId();
        }
        // Else err
        else {
            throw new MappingException("Could not convert.");
        }
    }
}
