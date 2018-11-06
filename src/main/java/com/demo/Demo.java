package com.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class Demo {
	public static void main(String[] args) {
		String requestUrl = "http://openapi-test.jpaas-matrixoff00.baidu.com/rest/2.0/vis-ocr/v1/ocr/general";   
		Map<String, String> map = new HashMap<String, String>();
		String accessToken = "21.21cda41bd9739ce5a083f3326f64b610.2592000.1469180474.1686270206-11101624";
		String imageStr = ImageUtil.getImageStr("c:/car.JPG");
		map.put("access_token", accessToken);
		map.put("image", imageStr);
		getDoPostResponseDataByURL(requestUrl, map, "UTF-8", false);
	}
	public   static  String getDoPostResponseDataByURL(String url,   
            Map<String, String> params, String charset,  boolean  pretty) {   
           
        StringBuffer response =  new  StringBuffer();   
           
        HttpClient client =  new  HttpClient();   
        HttpMethod method =  new  PostMethod(url);   
           
         //设置Http Post数据    
         if  (params !=  null ) {   
            HttpMethodParams p =  new  HttpMethodParams();   
             for  (Map.Entry<String, String> entry : params.entrySet()) {   
            	 System.out.println(entry.getKey());
                p.setParameter(entry.getKey(), entry.getValue());   
            }   
            method.setParams(p);   
        }   
         try  {   
            client.executeMethod(method);   
             if  (method.getStatusCode() == HttpStatus.SC_OK) {   
                 //读取为 InputStream，在网页内容数据量大时候推荐使用   
                BufferedReader reader =  new  BufferedReader(   
                         new  InputStreamReader(method.getResponseBodyAsStream(),   
                                charset));   
                String line;   
                 while  ((line = reader.readLine()) !=  null ) {   
                     if  (pretty)   
                        response.append(line).append(System.getProperty( "line.separator" ));   
                     else   
                        response.append(line);   
                }   
                reader.close();   
            }   
        }  catch  (IOException e) {   
            System.out.println( "执行HTTP Post请求"  + url +  "时，发生异常！" );   
            e.printStackTrace();   
        }  finally  {   
            method.releaseConnection();   
        }   
        System.out.println( "--------------------" +response.toString());   
         return  response.toString();   
    }    
}
