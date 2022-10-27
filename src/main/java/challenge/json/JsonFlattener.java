package challenge.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Flattens input object representing Json.
 */
public class JsonFlattener {
    private static List<Class> PRIMITIVES = List.of(String.class, Integer.class, Double.class, Boolean.class, Long.class, Character.class);

    /**
     * Returns flattened Object by recursively calling itself for any nested Objects
     * @param jsonObject Map representing jsonObject
     * @return Flattened Object
     */
    public Map<String, Object> flatten(Map<String, Object> jsonObject) {
        if (jsonObject == null) {
            return null;
        }

        if (jsonObject.isEmpty()) {
            return jsonObject;
        }

        Map<String, Object> result = new HashMap<>();
        for (var entry : jsonObject.entrySet()) {
            if (entry.getValue() != null && !isPrimitive(entry.getValue())) {
                Map<String, Object> flattenedObject = flatten((Map<String, Object>) entry.getValue());
                for (var flattenedEntry : flattenedObject.entrySet()) {
                    String newKey = String.format("%s.%s", entry.getKey(), flattenedEntry.getKey());
                    result.put(newKey, flattenedEntry.getValue());
                }
            } else {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    private boolean isPrimitive(Object value) {
        return PRIMITIVES.contains(value.getClass());
    }
}
