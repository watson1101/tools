package com.hong.tools.SE;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

public class PayUtil {
	 /*** 
     * MD5加密 生成32位md5码
     * @param 待加密字符串
     * @return 返回32位md5码
     */
    public static String toMd5(String inStr){
        MessageDigest md5 = null;
        byte[] ba=null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            ba = inStr.getBytes("UTF-8");
        } catch (Exception e) { 
            e.printStackTrace();
            return "";
        }
        byte[] mb = md5.digest(ba);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < mb.length; i++) {
            int val = ((int) mb[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
    
    public static String postString(String url,String msg) throws Exception{
    	try{ 
	    	URL u = new URL(url); 
	    	HttpURLConnection con = (HttpURLConnection) u.openConnection(); 
	    	con.setRequestMethod("POST"); 
	    	con.setDoOutput(true); 
	    	con.setDoInput(true); 
	    	con.setUseCaches(false);  
	    	con.setRequestProperty("Content-Type","text/html; charset=utf-8");  
	    	OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");  
	    	osw.write(msg); 
	    	osw.flush(); 
	    	osw.close();  
	    	
	    	InputStreamReader ins = new InputStreamReader(con.getInputStream(), "UTF-8");  
	    	int v=ins.read();
	    	StringBuilder sb=new StringBuilder();
	    	while(v!=-1){
	    		sb.append((char)v);
	    		v=ins.read();
	    	}
	    	ins.close();
	    	con.disconnect(); 
	    	return sb.toString();
    	}catch(Exception e){
    		throw e;
    	}
    }
    /**  
     * 二进制数据编码为BASE64字符串   加密
     * @param bytes  
     * @return  
     * @throws Exception  
     */    
    public static String encode(final String string) {    
        try {  
            return new String(Base64.encodeBase64(string.getBytes("UTF-8")),"UTF-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
            return "";  
        }    
    } 
    /** 
     * 解密 
     * @param bytes  
     * @return  
     */    
    public static String decode(final String string) {    
        try {  
            return new String(Base64.decodeBase64(string.getBytes("UTF-8")), "utf-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
            return "";  
        }  
    }

}
