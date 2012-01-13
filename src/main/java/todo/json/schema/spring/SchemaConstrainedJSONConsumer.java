package todo.json.schema.spring;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: Will O'Brien
 * Date: 1/9/12
 * Time: 1:15 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SchemaConstrainedJSONConsumer {
    /**
     * Path to schema
     * @return
     */
    String value();
}
