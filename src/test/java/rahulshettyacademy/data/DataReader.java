package rahulshettyacademy.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	
    public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
    	
    	//read json to string
        String jsonContent = new String(Files.readAllBytes(Paths.get(filepath)));
        //give UTF_8 deprecated message

        //String to Hashmap using jackson databind
        
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});

        return data;
    }


	
}
