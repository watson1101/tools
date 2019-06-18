package com.hong.tools.SE;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by suneee on 2017/12/19.
 */
public class JsonDateValueProcessor implements JsonValueProcessor {
	private String format ="yyyy-MM-dd HH:mm:ss";

	public JsonDateValueProcessor() {
		super();
	}

	public JsonDateValueProcessor(String format) {
		super();
		this.format = format;
	}

	@Override
	public Object processArrayValue(Object paramObject,
									JsonConfig paramJsonConfig) {
		return process(paramObject);
	}

	@Override
	public Object processObjectValue(String paramString, Object paramObject,
									 JsonConfig paramJsonConfig) {
		return process(paramObject);
	}


	private Long process(Object value){
		if(value instanceof Date){
			// SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
			// return sdf.format(value);
			return ((Date) value).getTime();
		}
		return null;
	}

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<>();
		map.put("aa", new Date());
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSONObject json = new JSONObject();
		JSONObject jsonArray1 = json.fromObject(map,jsonConfig);
		System.out.println(jsonArray1);
		JSONObject jsonObject = JSONObject.fromObject(map);
		System.out.println(jsonObject);
	}

}