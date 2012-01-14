package todo;

import org.springframework.http.converter.HttpMessageNotReadableException;

/**
 * User: Will O'Brien
 * Date: 1/13/12
 * Time: 8:10 PM
 */
public class HttpMessageUnprocessableException extends HttpMessageNotReadableException {
    public HttpMessageUnprocessableException(String msg) {
        super(msg);
    }
}
