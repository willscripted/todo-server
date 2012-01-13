package todo.json.schema.spring;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import todo.json.schema.SchemaBasedJSONValidator;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;

/**
 * Validates all application/json requests to ensure they are well formed and
 * syntactically correct.
 *
 * Additionally, the filter validates the json against the appropriate schema
 * if a schema was declared.
 *
 * User: Will O'Brien
 * Date: 1/9/12
 * Time: 11:40 AM
 */
public class JSONSchemaFilter {

    private final SchemaBasedJSONValidator validator;

    @Autowired
    public JSONSchemaFilter(final SchemaBasedJSONValidator v) {
        this.validator = v;
    }

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws ServletException {

        HandlerMethod hand = (HandlerMethod) handler;
        SchemaConstrainedJSONConsumer methodAnnotation =
                hand.getMethodAnnotation(SchemaConstrainedJSONConsumer.class);


        if (methodAnnotation != null) {


            String schemaName = methodAnnotation.value();
            String json = null;

            try {
                {
                    ServletInputStream inputStream = request.getInputStream();

                    InputStreamReader reader = new InputStreamReader
                            (inputStream);
                    StringBuffer stringBuffer = new StringBuffer();
                    String line = null;
//
//                    while ((line = reader.readLine()) != null) {
//                        stringBuffer.append(line);
//                    }

                    json = stringBuffer.toString();
                }

                request.getReader()
                       .reset();

            } catch (IOException e) {
                e.printStackTrace();
                rejectUnreadable(response);
                return false;
            }

            String errors = validator.validate(json,
                                               schemaName);

            if (errors.equals("")) {
                return true;
            } else {
                response.setStatus(422);
                response.setContentType("application/json");
                try {
                    response.getOutputStream()
                            .print(errors);
                } catch (IOException e) {
                    response.setStatus(500);
                    return false;
                }
                return false;
            }
        }


        return true;
    }

    private void rejectUnreadable(HttpServletResponse response) {
        response.setStatus(400);
    }
    
//
//    public boolean newPreHandle(HttpServletRequest req,
//                                HttpServletResponse rsp,
//                                Object handler) {
//
//        String contentType = req.getContentType();
//
//
//        // If 'application/json'
//        if(contentType.equals("application/json")){
//
//            // Reject if malformed
//            if(!isValidJSON(json)) {
//                rejectInvalidJSON(rsp);
//                return false;
//            }
//
//        }
//
//
//        // If Annotated
//        if(handlerIsAnnotated(handler)) {
//
//            // Reject if not 'application/json'
//            if(contentType.equals("application/json")) {
//                rejectNonJSONRequest(rsp);
//                return false;
//            }
//
//            // Reject if json does conform to schema
//            String result = validateJSONAgainstSchema(json, schema);
//            if(!result.equals("")) {
//                rejectNonConformingJSON(rsp);
//                return false;
//            }
//
//        }
//
//        // Else continue interceptor chain
//        return true;
//
//    }
//
//
//
//    public void readStream(HttpServletRequest req) throws IOException {
//
//        ServletInputStream inputStream = req.getInputStream();
//        InputStreamReader reader = new InputStreamReader(inputStream);
//
//
//        StringWriter writer = new StringWriter();
//        IOUtils.copy(inputStream, writer);
//        String theString = writer.toString();
//
//
//    }
//
//

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {


        filterChain.doFilter(servletRequest, servletResponse);


    }

    public void destroy() {

    }
}
