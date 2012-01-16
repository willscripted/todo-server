package todo.json.schema.spring;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

/**
 * User: Will O'Brien
 * Date: 1/13/12
 * Time: 8:51 PM
 */
public class SchemaConfigInstance {
    
    private MediaType mediaType;
    private Resource schemaLocation;
    private Class clazz;

    public SchemaConfigInstance(String type,
                                Resource schemaLocation,
                                Class clazz) {
        
        if(type == null 
           || type.equals("")
                || schemaLocation == null
                || clazz == null) {
            throw new IllegalArgumentException("Null arguments not accepted");
        }

        String[] split = type.split("/");
        mediaType = new MediaType(split[0], split[1]);

        this.clazz = clazz;
        this.schemaLocation = schemaLocation;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public Resource getSchemaLocation() {
        return schemaLocation;
    }


    public Class getClazz() {
        return clazz;
    }

}
