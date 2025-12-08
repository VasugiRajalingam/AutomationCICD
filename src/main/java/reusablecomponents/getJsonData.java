package reusablecomponents;

import org.apache.commons.io.FileUtils;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class getJsonData {
    public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
        String jsonDataContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")
                + "//src//main//java//resources//testdata.json"), StandardCharsets.UTF_8);

        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, String>> data = objectMapper.readValue(jsonDataContent,
                new TypeReference<List<HashMap<String, String>>>() {});
        return data;
    }
}
