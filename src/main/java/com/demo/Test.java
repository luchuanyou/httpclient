package com.demo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class Test {
	public static void main(String[] args)throws Exception{   
        //String requestUrl = "http://123.125.97.xxx:8804/ecpaycus/test/JFB_web_visit.jsp?phoneNo=18600000001";   
        //System.out.println(sendGetRequest(requestUrl));   
        String requestUrl = "http://openapi-test.jpaas-matrixoff00.baidu.com/rest/2.0/vis-ocr/v1/ocr/general";
        System.out.println();
        System.out.println(sendPostRequest(requestUrl));   
    }   
	/**  
     * 发送带有表单参数的POST请求  
     * @param requestUrl 请求的地址(不含参数)  
     * @return 响应内容  
     */  
    @SuppressWarnings("finally")   
    public static String sendPostRequest(String requestUrl) {   
        long responseLength = 0;       //响应长度   
        String responseContent = null; //响应内容   
        HttpClient httpClient = new DefaultHttpClient();                 //创建默认的httpClient实例   
        HttpPost httpPost = new HttpPost(requestUrl);                    //创建HttpPost   
        List<NameValuePair> formParams = new ArrayList<NameValuePair>(); //创建参数队列   
        
        String accessToken = "21.21cda41bd9739ce5a083f3326f64b610.2592000.1469180474.1686270206-11101624";
        String imageStr = ImageUtil.getImageStr("c:/1234.jpg");
        System.out.println(imageStr);
        formParams.add(new BasicNameValuePair("access_token", accessToken));   
        formParams.add(new BasicNameValuePair("image", imageStr));   
        try {   
            httpPost.setEntity(new UrlEncodedFormEntity(formParams, "UTF-8"));   
            HttpResponse response = httpClient.execute(httpPost); //执行POST请求   
            HttpEntity entity = response.getEntity();             //获取响应实体   
            if (null != entity) {   
                responseLength = entity.getContentLength();   
                responseContent = EntityUtils.toString(entity, "iso8859-1");   
                EntityUtils.consume(entity); //Consume response content   
            }
            System.out.println("请求地址: " + httpPost.getURI());   
            System.out.println("响应状态: " + response.getStatusLine());   
            System.out.println("响应长度: " + responseLength);   
            System.out.println("响应内容: " + responseContent);   
        } catch (UnsupportedEncodingException e) {   
            e.printStackTrace();   
        } catch (ClientProtocolException e) {   
            e.printStackTrace();   
        } catch (ParseException e) {   
            e.printStackTrace();   
        } catch (IOException e) {   
            e.printStackTrace();   
        } finally {   
            httpClient.getConnectionManager().shutdown(); //关闭连接，释放资源   
            return responseContent;   
        }   
    }   
}
