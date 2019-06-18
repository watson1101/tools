package com.hong.tools.SE;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 *@date   2014年6月26日
 **/
public class PropertiesUtils {
	
	private static Properties prop = new Properties();
	
	//解析配置文件
	static{
		try{
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("conf.properties"));
			Set<Object> keyset = prop.keySet();
			Iterator<Object> it = keyset.iterator();
			while(it.hasNext()){
				String key = (String)it.next();
				String value = null;
				try{
					value = new String(prop.getProperty(key).getBytes("ISO-8859-1"), "utf-8");
				}catch(UnsupportedEncodingException exp){
					value = prop.getProperty(key);
				}
				prop.setProperty(key, value);
			}
		}catch(Exception exp){
			throw new RuntimeException("读取Properties配置文件出错："+exp.getMessage());
		}
	}
	
	/**
	 * 根据键值获取对应的值
	 * @param key(键值)
	 * @return(对应的值)
	 */
	public static String getValue(String key){
		String value = null;
		if(key != null){
			value = prop.getProperty(key);
		}
		if(value != null){
			value = value.trim();
		}
		return value;
	}
}
