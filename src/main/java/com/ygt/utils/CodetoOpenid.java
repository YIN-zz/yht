package com.ygt.utils;

import java.util.Map;

import com.ygt.common.utils.HttpUtils;
import com.ygt.common.utils.JsonUtils;

public class CodetoOpenid {
	public  static String appId="wxab7a57a2d58e8f25";
	public static String appSecret="fc90212d11b9dd2a8602d293dd2e4d2e";
	public static String grantType="authorization_code";
	public static String requestUrl="https://api.weixin.qq.com/sns/jscode2session";
public static String getSessionByCode(String code) {  
    String url = requestUrl + "?appid=" + appId + "&secret=" + appSecret + "&js_code=" + code + "&grant_type="  
            + grantType;
    // 发送请求  
    System.out.println(url);
    String data = HttpUtils.sendGet(url, null);
    Map<String, String> json = null;
    try {
        json = JsonUtils.readValue(data);
    } catch (Exception e) {
        e.printStackTrace();
    }
    // 形如{"session_key":"6w7Br3JsRQzBiGZwvlZAiA==","openid":"oQO565cXXXXXEvc4Q_YChUE8PqB60Y"}的字符串
    return json.get("openid");
}  


public static void main(String[] args) {
	String code="0332Aj6F1EiLY60lxy4F16Ln6F12Aj6Z";
	System.out.println(getSessionByCode(code));
}
}  

