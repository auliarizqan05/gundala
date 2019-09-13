package co.id.gooddoctor.gundala.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class JsonUtil {

    public static String objectToJson(Object content){
        ObjectMapper mapper = new ObjectMapper();
        String result = "";
        try {
            result = mapper.writeValueAsString(content);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static Object jsonToObject(String content, Class clazz ){
        ObjectMapper mapper = new ObjectMapper();
        Object result = null;
        try {
            result = mapper.readValue(content, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
