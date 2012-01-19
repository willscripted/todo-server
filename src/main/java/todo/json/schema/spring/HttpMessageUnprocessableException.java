package todo.json.schema.spring;

import org.springframework.http.converter.HttpMessageNotReadableException;

/**
 * Thrown when a received message was syntactically correct but semantically
 * invalid.
 *
 * @author Will O'Brien
 */
public class HttpMessageUnprocessableException
        extends HttpMessageNotReadableException {

    /**
     * Create new.
     * @param msg Describing syntactic error.
     */
    public HttpMessageUnprocessableException(final String msg) {
        super(msg);
    }
}
