package todo.webapp.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import todo.domain.Task;
import todo.domain.User;
import todo.json.schema.spring.JSONSchemaBasedHttpMessageConverter;
import todo.json.schema.spring.SchemaConfigInstance;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Will O'Brien
 */
@Configuration
public class JSONMessageConverterConfig implements ApplicationContextAware {

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

    private Set<SchemaConfigInstance> getSchemas() {
        Set<SchemaConfigInstance>
                configInstances = new HashSet<SchemaConfigInstance>();

        configInstances.add(getTaskSchemaConfig());
        configInstances.add(getUserSchemaConfig());

        return configInstances;
    }


    // User
    public SchemaConfigInstance getUserSchemaConfig() {
        Resource schemaDefinition = context.getResource("/WEB-INF/schemas/user.json");
        return new SchemaConfigInstance("application/todo.user+json",
                                schemaDefinition,
                                User.class);
    }

    // Task
    public SchemaConfigInstance getTaskSchemaConfig() {
        Resource schemaDefinition = context.getResource("/WEB-INF/schemas/task.json");
        return new SchemaConfigInstance("application/todo.task+json",
                                schemaDefinition,
                                Task.class);
    }


}
