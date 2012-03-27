package todo.webapp.controllers.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Will O'Brien
 */
@Controller
public class AppController {
    
    @RequestMapping("/app/")
    public String app() {
        return "app/base";
    }
    
    @RequestMapping("/app/manifest/")
    public String manifest() {
        return "app/manifest";
    }
    
    
}
