/**   
* @Title: PropertiesUtil.java 
* @Description: properties属性文件工具类
* @date 2014年6月12日 上午11:23:20 
*/ 
package com.hong.tools.SE;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
    private  Properties prop ;
    public PropertiesUtil(String file){
        prop = new Properties();
        try {
            prop.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getKeyValue(String key){
       return  prop.getProperty(key).trim();
    }

}
