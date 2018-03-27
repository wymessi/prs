package com.wymessi.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.wymessi.exception.JsonFormatException;



public class RequestUtils {

	
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	
	public static <T> T toObject(HttpServletRequest request, Class<T> clazz) throws IOException{
		
		JSONObject jsonObject = toJson(request);
		if (jsonObject == null)
			return null;
		JSONObject dataJson = jsonObject.getJSONObject("data");
		if (dataJson == null)
			return null;
		T t = null;
		try{
			t = JSON.toJavaObject(dataJson, clazz);
		}catch (Exception e) {
			throw new JsonFormatException("json转化异常");
		}			
		return t;
	}
	
	public static JSONObject toJson(HttpServletRequest request) throws IOException{
		InputStream in = request.getInputStream();
		InputStreamReader reader = new InputStreamReader(in, DEFAULT_CHARSET);
		String content = IOUtils.toString(reader);
		if(StringUtils.isBlank(content)) {
			return null;
		}
		return JSON.parseObject(content);
	}
	
	public static JSONObject toParameter(HttpServletRequest request) throws IOException {
		JSONObject json = toJson(request);
		return json == null ? null : json.getJSONObject("data");
	}
}
