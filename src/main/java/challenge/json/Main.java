package challenge.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        // read file from input
        String inputJson = readInputFile();

        // convert to Map<String, Object> which can represent any Json object
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<HashMap<String,Object>> typeRef = new TypeReference<>() {};
        Map<String, Object> inputJsonObject =  objectMapper.readValue(inputJson, typeRef);

        // invoke {@code "challenge.json.JsonFlattener"} to flatten the object
        JsonFlattener jsonFlattener = new JsonFlattener();
        Map<String, Object> flattenedJsonObject = jsonFlattener.flatten(inputJsonObject);

        // Write flattened object to console (pretty-print)
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(flattenedJsonObject));
    }

    private static String readInputFile() throws IOException {
        BufferedReader in = null;
        StringBuilder jsonInputBuilder = new StringBuilder();
        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while ((line = in.readLine()) != null) {
                jsonInputBuilder.append(line);
            }
        }
        catch (IOException e) {
            System.out.println("IOException reading System.in");
            e.printStackTrace();
        }
        finally {
            if (in != null) {
                in.close();
            }
        }
        return jsonInputBuilder.toString();
    }
}
