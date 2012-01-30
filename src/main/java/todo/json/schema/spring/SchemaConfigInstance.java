package todo.json.schema.spring;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

/**
 * Maps a media type to the target java class it should be converted to and a
 * json-schema that should be used to do the conversion.
 *
 * @author Will O'Brien
 */
public class SchemaConfigInstance {
    
    private MediaType mediaType;
    private Resource schema;
    private Class clazz;

    /**
     * Create new SchemaConfigInstance.
     * @param type String - MIME type this configuration maps. eg
     *             "application/some.schema.def+json"
     * @param schema Resource containing the schema definition in plain text
     * @param clazz Class this config will map to.
     */
    public SchemaConfigInstance(String type,
                                Resource schema,
                                Class clazz) {
        
        if(type == null 
           || type.equals("")
                || schema == null
                || clazz == null) {
            throw new IllegalArgumentException("Null arguments not accepted");
        }

        String[] split = type.split("/");
        mediaType = new MediaType(split[0], split[1]);

        this.clazz = clazz;
        this.schema = schema;
    }

    /**
     * MIME type this configuration maps eg. ("application/some.schema
     * .type+json")
     * @return MediaType
     */
    public MediaType getMediaType() {
        return mediaType;
    }

    /**
     * @return Resource
     */
    public Resource getSchema() {
        return schema;
    }


    /**
     * Get class that this config instance will produce.
     * @return
     */
    public Class getClazz() {
        return clazz;
    }

}
