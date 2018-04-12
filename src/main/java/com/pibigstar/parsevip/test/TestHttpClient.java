package com.pibigstar.parsevip.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class TestHttpClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//定义httpClient的实例
		HttpClient httpclient = new HttpClient();
		//创建get方法的实例
		GetMethod getMethod = new GetMethod("http://jb51.net");
		//使用系统提供的默认恢复策略
		//  getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

		//创建post方法实例
		PostMethod postMethod = new UTF8PostMethod("http://jb51.net");
		//  
		//  //填入各个表单域的值
		//  NameValuePair[] data = {new NameValuePair("user_name", "user_name"),new NameValuePair("password","password")};
		//  
		//  //将表单的值放入到post方法中
		//  postMethod.setRequestBody(data);
		//  
		//  postMethod.getParams().setParameter(
		//    "http.protocol.cookie-policy",CookiePolicy.BROWSER_COMPATIBILITY);
		//  postMethod.setRequestHeader("Referer", "http://jb51.net");
		try{
			//执行GET方法
			//   int statusCode = httpclient.executeMethod(getMethod);

			//执行post方法
			int statusCode = httpclient.executeMethod(postMethod);
			if(statusCode == HttpStatus.SC_MOVED_TEMPORARILY){
				Header locationHeader = postMethod.getResponseHeader("Location");
				String location = null;
				if(locationHeader != null){
					location = locationHeader.getValue();
				}
				postMethod = new PostMethod(location);
				postMethod.setRequestHeader("Referer", "http://jb51.net/login");
				NameValuePair[] data1 = {new NameValuePair("user_name", "user_name"),new NameValuePair("password","password")};
				postMethod.setRequestBody(data1);
				postMethod.getParams().setParameter(
						"http.protocol.cookie-policy",CookiePolicy.BROWSER_COMPATIBILITY);
				int statusCode1 = httpclient.executeMethod(postMethod);
				if(statusCode1 != HttpStatus.SC_OK){
					System.out.println("Method is wrong " + postMethod.getStatusLine());
				}
			}
			if(statusCode != HttpStatus.SC_OK){
				System.out.println("Method is wrong " + postMethod.getStatusLine());
			}
			InputStream responseBody = postMethod.getResponseBodyAsStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(responseBody,"utf-8"));
			String line = reader.readLine();
			while(line != null){
				System.out.println(new String(line.getBytes()));
				line = reader.readLine();
			}

		}
		catch (HttpException e) {
			// TODO: handle exception
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
		}catch (IOException e) {
			// TODO: handle exception
			System.out.println("the line is wrong!");
			e.printStackTrace();
		}finally{
			getMethod.releaseConnection();//释放链接
			postMethod.releaseConnection();
		}
	}
	//Inner class for UTF-8 support   
	public static class UTF8PostMethod extends PostMethod{   
		public UTF8PostMethod(String url){   
			super(url);   
		}   
		@Override   
		public String getRequestCharSet() {   
			//return super.getRequestCharSet();   
			return "UTF-8";   
		}
	}

}

