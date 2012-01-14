package todo;

import org.junit.Before;
import todo.json.schema.spring.JSONSchemaBasedHttpMessageConverter;

/**
 * User: Will O'Brien
 * Date: 1/13/12
 * Time: 7:14 PM
 */
public class JSONSchemaBasedHttpMessageConverterTest {

    private JSONSchemaBasedHttpMessageConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = new JSONSchemaBasedHttpMessageConverter();
    }




}
