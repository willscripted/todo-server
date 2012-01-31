package todo.json.schema.spring;

import org.springframework.http.converter.HttpMessageNotReadableException;

/**
 * Thrown for http messages that are syntactically correct but semantically
 * invalid according to their json-schema.
 *
 * @author Will O'Brien
 */
public class HttpMessageUnprocessableException
        extends HttpMessageNotReadableException {

    /**
     * @param msg Describing syntactic error.
     */
    public HttpMessageUnprocessableException(final String msg) {
        super(msg);
    }
}
