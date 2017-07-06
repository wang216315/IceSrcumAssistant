package org.will.app.conmunication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map.Entry;


import net.sf.json.JSONArray;

public class HttpJsonCilent
{
	
	public static JSONArray httpRequest(JsonOperator jsonOperator) 
	{
		

		JSONArray jsonArray = null;
		StringBuffer buffer = new StringBuffer();
		InputStream inputStream = null;
		try
		{
			URL url = new URL(jsonOperator.getRequestUrl());
			HttpURLConnection httpUrlConn = (HttpURLConnection)url.openConnection();
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);	
			//System.out.println(auth);
			for (Entry<String,String> entry : jsonOperator.getProperties().entrySet())
			{
				httpUrlConn.setRequestProperty(entry.getKey(), entry.getValue());
			}
			//设置请求方式
			httpUrlConn.setRequestMethod(jsonOperator.getRequestMethod());
			
			if("GET".equalsIgnoreCase(jsonOperator.getRequestMethod()))
			{
				httpUrlConn.connect();
			}
		   
			inputStream = httpUrlConn.getInputStream();
			if(httpUrlConn.getResponseCode() == 200)
			{
					
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
			
			
					
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);		
			String str = null;
			while((str = bufferedReader.readLine())!=null)
			{
				buffer.append(str);
				
			}

			bufferedReader.close();
			inputStream.close();
			//System.out.println(buffer.toString());
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
	
			jsonArray = JSONArray.fromObject(buffer.toString());
			}

			//jsonObject = JSONObject.fromObject(buffer.toString());
		}catch(ConnectException ce)
		{
			ce.printStackTrace();
			System.out.println("icesrcum server connection time out");
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("http request error");
		}finally
		{
			try
			{
				if(inputStream != null)
				{
					inputStream.close();
				}
			
			}catch(IOException e)
			{
				e.printStackTrace();			
			}
		}
		return jsonArray;
	}
}
