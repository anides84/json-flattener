package challenge.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static org.assertj.core.api.Assertions.assertThat;


public class JsonFlattenerTest {
    private JsonFlattener jsonFlattener;
    private ObjectMapper objectMapper;
    private TypeReference<HashMap<String,Object>> typeRef = new TypeReference<>() {};
    private Path workingDir;

    @BeforeEach
    public void setup() {
        this.workingDir = Path.of("", "src/test/resources/challenge/json");
        jsonFlattener = new JsonFlattener();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void flatten_shouldFlattenMapCorrectly() throws IOException {
        Map<String, Object> inputJsonObject = readFile("inputJson.json");
        Map<String, Object> result = jsonFlattener.flatten(inputJsonObject);
        Map<String, Object> outputJsonObject = readFile("outputJson.json");
        assertThat(result).isEqualToComparingFieldByFieldRecursively(outputJsonObject);
    }

    @Test
    public void flatten_shouldHandleEmptyJsonObject() throws IOException {
        assertThat(jsonFlattener.flatten(emptyMap())).isEmpty();
    }

    @Test
    public void flatten_shouldHandleNullJsonObject() throws IOException {
        assertThat(jsonFlattener.flatten(null)).isNull();
    }

    private Map<String, Object> readFile(String fileName) throws IOException {
        Path file = this.workingDir.resolve(fileName);
        String inputJson = Files.readString(file);
        return objectMapper.readValue(inputJson, typeRef);
    }
}
