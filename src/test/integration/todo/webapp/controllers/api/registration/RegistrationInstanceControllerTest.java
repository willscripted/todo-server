package todo.webapp.controllers.api.registration;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.server.MockMvc;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.server.setup.MockMvcBuilders.standaloneSetup;


/**
 * User: Will O'Brien
 * Date: 1/28/12
 * Time: 8:57 PM
 */
public class RegistrationInstanceControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = standaloneSetup(new RegistrationInstanceController())
                .build();
    }

    @Test
    public void testHandlerType() throws Exception {
        this.mockMvc.perform(
                        get("/api/registration/5/")
                )
                .andExpect
                        (handler().type(RegistrationInstanceController.class)
                        );
    }


}
