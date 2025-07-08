package com.dsa_edu_be.dsa_edu_be.Utils;

import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;

@Component
public class JsonUtils {
    public boolean isValidJson(String str){
        try{
            JsonParser.parseString(str);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
}
