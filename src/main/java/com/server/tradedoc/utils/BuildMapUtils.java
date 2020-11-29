package com.server.tradedoc.utils;

import com.server.tradedoc.utils.error.CustomException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * BuildMapUtils
 *
 * @author DatDV
 */
@Component
public class BuildMapUtils {

    public Map<String , Object> buildMapSearch(Object object){
        Map<String , Object> result = new HashMap<>();
        try {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.get(object) != null){
                    if (field.get(object) instanceof Integer) {
                        result.put(field.getName().toLowerCase() , Integer.parseInt((String) field.get(object)));
                    } else if (field.get(object) instanceof Long) {
                        result.put(field.getName().toLowerCase() , Long.parseLong(field.get(object).toString()));
                    } else {
                        result.put(field.getName().toLowerCase() , BuildQueryUtils.formatLikeStringSql(field.get(object).toString()));
                    }
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException e){
            throw new CustomException("build map fails" , CommonUtils.putError("class" , "ERR_002"));
        }
        return result;
    }
}
