package com.ygt.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sheldon on 2017/11/8.
 */
public class BaseUtils {

    public static Map<String,Object> paramToMap(HttpServletRequest request){

        Enumeration parameterNames = request.getParameterNames();
        Map<String,Object> paramMap = new HashMap<>();
        while (parameterNames.hasMoreElements()) {
            String key = (String) parameterNames.nextElement();
            String value = request.getParameter(key);
            paramMap.put(key,value);
        }
        return paramMap;
    };

}
