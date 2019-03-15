package com.hyp.utils;

import com.google.gson.Gson;

/**
 * 

* @Title: GsonUtils  

* @Description: json转换工具  

* @author HanYupeng  

* @date 2018-06-15 10:36
 */
public class GsonUtils {

    private static final Gson gson = new Gson();

    public static String toJsonString(Object object){
      return object==null?null:gson.toJson(object);
    }
}
