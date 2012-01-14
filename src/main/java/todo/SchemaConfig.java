package todo;

import org.springframework.core.io.Resource;

/**
 * User: Will O'Brien
 * Date: 1/13/12
 * Time: 8:51 PM
 */
public class SchemaConfig {
    
    private String mediaType;
    private Resource schemaLocation;
    private Class clazz;

    public SchemaConfig(String mediaType,
                        Resource schemaLocation,
                        Class clazz) {
        
        if(mediaType == null 
           || mediaType.equals("")
                || schemaLocation == null
                || clazz == null) {
            throw new IllegalArgumentException("Null arguments not accepted");
        }
        
        this.mediaType = mediaType;
        this.clazz = clazz;
        this.schemaLocation = schemaLocation;
    }

    public String getMediaType() {
        return mediaType;
    }

    public Resource getSchemaLocation() {
        return schemaLocation;
    }


    public Class getClazz() {
        return clazz;
    }

}
