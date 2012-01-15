package todo.webapp.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.HandlerExceptionResolver;
import todo.json.schema.spring.JSONSchemaBasedHttpMessageConverter;
import todo.json.schema.spring.SchemaConfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * User: Will O'Brien
 * Date: 1/14/12
 * Time: 9:45 AM
 */
@Configuration
public class JSONMessageConverterConfig implements ApplicationContextAware{

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        context = applicationContext;
    }

    @Bean
    public JSONSchemaBasedHttpMessageConverter
    jsonSchemaBasedHttpMessageConverter() throws IOException {
        JSONSchemaBasedHttpMessageConverter converter = new
                JSONSchemaBasedHttpMessageConverter();

        converter.setSchemas(getSchemas());

        return converter;
    }

    private Set<SchemaConfig> getSchemas() {
        Set<SchemaConfig> configs = new HashSet<SchemaConfig>();

        configs.add(getTaskSchemaConfig());
        configs.add(getUserSchemaConfig());

        return configs;
    }


    // User
    public SchemaConfig getUserSchemaConfig() {
        Resource schemaDefinition = context.getResource("/WEB-INF/schemas/user.json");
        return new SchemaConfig("application/todo.user+json",
                                schemaDefinition,
                                todo.hibernate.entities.User.class);
    }

    // Task
    public SchemaConfig getTaskSchemaConfig() {
        Resource schemaDefinition = context.getResource("/WEB-INF/schemas/task.json");
        return new SchemaConfig("application/todo.task+json",
                                schemaDefinition,
                                todo.hibernate.entities.Task.class);
    }


}
