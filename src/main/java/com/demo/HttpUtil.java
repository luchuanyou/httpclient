package com.demo;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {

	public static void main(String[] args) {
//		String url = "https://www.montblanc.com/products/us/en/10883.pdp.redirect.html";
		String url = "http://127.0.0.1:8080/tbMemberFundRate/queryFundRateDetail";
		String requestObj = "{name:jack}";
		try {
			post(url, requestObj);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void post(String url, Object requestObj)
			throws ClientProtocolException, IOException {
		// 创建一个默认的HttpClient
		HttpClient httpclient = new DefaultHttpClient();
		try {
			// 以post方式请求
			HttpPost httppost = new HttpPost(url);
			
			String reqStr = new ObjectMapper().writeValueAsString(requestObj);
			StringEntity params = new StringEntity(reqStr);
			httppost.setEntity(params);
			// 打印请求地址
			System.out.println("executing request "
					+ httppost.getRequestLine().getUri());
			// 创建响应处理器处理服务器响应内容
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			// 执行请求并获取结果
			String responseBody = httpclient.execute(httppost, responseHandler);
			System.out.println("----------------------------------------");
			System.out.println(responseBody);
			System.out.println("----------------------------------------");
		} finally {
			// 当不再需要HttpClient实例时,关闭连接管理器以确保释放所有占用的系统资源
			httpclient.getConnectionManager().shutdown();
		}
	}
	
	//url:http://api.opentracker.net/api/views/view_pages.jsp?login=demo@opentracker.net&password=demo123&site=www.opentracker.net&filterByUrlTitle=*login*&offset=0&limit=10
	public static void get(String url)
			throws ClientProtocolException, IOException {
		// 创建一个默认的HttpClient
		HttpClient httpclient = new DefaultHttpClient();
		try {
			// 以get方式请求
			HttpGet httpGet = new HttpGet(url);
			// 打印请求地址
			System.out.println("executing request "
					+ httpGet.getRequestLine().getUri());
			// 创建响应处理器处理服务器响应内容
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			// 执行请求并获取结果
			String responseBody = httpclient.execute(httpGet, responseHandler);
			System.out.println("----------------------------------------");
			System.out.println(responseBody);
			System.out.println("----------------------------------------");
		} finally {
			// 当不再需要HttpClient实例时,关闭连接管理器以确保释放所有占用的系统资源
			httpclient.getConnectionManager().shutdown();
		}
	}
}

