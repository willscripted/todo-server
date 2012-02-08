package todo.json.schema;

import org.apache.commons.io.IOUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

/**
 *
 */
public class ValidationDelegator implements Filter {

    private URL jsonSchemaValidator;

    private Pattern jsonSchemaContentTypePattern = Pattern.compile
            ("^application/(.+)\\+json;?.*$");


    public ValidationDelegator() throws MalformedURLException {
        jsonSchemaValidator = new URL("http://localhost:3333");


    }

    public void destroy() {

    }

    public void doFilter(ServletRequest req,
                         ServletResponse resp,
                         FilterChain chain)
            throws ServletException, IOException {

        System.out
                .println("Hit filter");
        String contentType = req.getContentType();

        if (contentType == null) {
            contentType = "";
        }
        System.out
                .println(req.getContentType());

        // If content (exists && ) type matches
        if (jsonSchemaContentTypePattern.matcher(contentType)
                                        .matches()) {

            System.out
                    .println("Hit special handling");

            // Do special chain
            doJsonSchemaValidation(req,
                                   resp,
                                   chain);

        } else {
            System.out
                    .println("otherChain");
            chain.doFilter(req,
                           resp);
        }
    }

    private void doJsonSchemaValidation(ServletRequest req,
                                        ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        MultiReadHttpServletRequest wrapper = new
                MultiReadHttpServletRequest((HttpServletRequest) req);

        HttpURLConnection connection =
                (HttpURLConnection) jsonSchemaValidator.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("content-type",
                                      wrapper.getContentType());

        IOUtils.copy(wrapper.getInputStream(),
                     connection.getOutputStream());


        if (connection.getResponseCode() == 200) {
            chain.doFilter(wrapper,
                           resp);
        } else {

            HttpServletResponse response = (HttpServletResponse) resp;

            response.setStatus(connection.getResponseCode());

            IOUtils.copy(connection.getErrorStream(),
                         response.getOutputStream());
        }
    }

    public void init(FilterConfig config)
            throws ServletException {

    }
}
