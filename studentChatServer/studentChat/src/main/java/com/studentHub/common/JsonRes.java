package com.studentHub.common;

import com.alibaba.fastjson.JSONObject;

public class JsonRes {


    public static JSONObject resSuccess(){
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("flag", true);
        return jsonObj;
    }

    public static JSONObject resError(){
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("flag", false);
        return jsonObj;
    }


}
