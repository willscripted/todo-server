package todo.json.schema;

import eu.vahlas.json.schema.JSONSchema;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Will O'Brien
 * Date: 1/9/12
 * Time: 2:52 PM
 */
public class SchemaBasedJSONValidator {

    private final SchemaEvaluator schemaEvaluator;

    public SchemaBasedJSONValidator(JSONSchema[] schemasFound) {

        schemaEvaluator = new SchemaEvaluator();

        populateSchemaMap(schemasFound);
    }

    private void populateSchemaMap(JSONSchema[] schemasFound) {
        for (int i = 0; i < schemasFound.length; i++) {
            if (schemasFound[i] != null) {
                JSONSchema curSchema = schemasFound[i];
//                schemaEvaluator.addSchema(curSchema.getName(),
//                                          curSchema.getJsonSchemaFile());
            }
        }
    }


    public String validate(String json, String schemaName) {
        if (schemaEvaluator.hasSchema(schemaName)) {
            throw new IllegalArgumentException("No schema defined for schema "
                                               + "name");
        }

        return schemaEvaluator.eval(json, schemaName);
    }
}
