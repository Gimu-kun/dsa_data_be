package com.dsa_edu_be.dsa_edu_be.Utils;

import org.json.JSONArray;
import org.springframework.stereotype.Component;

@Component
public class ArrayUtils {
    public boolean isJsonArray(String raw){
        try{
            new JSONArray(raw);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
