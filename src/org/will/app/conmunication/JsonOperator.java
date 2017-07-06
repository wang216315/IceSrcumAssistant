package org.will.app.conmunication;


import java.util.HashMap;

import java.util.Map;

public class JsonOperator
{
	
	
	private String requestMethod;
	
	private String requestUrl;
	
	private Map<String,String> properties = new HashMap<String,String>();

	

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public Map<String,String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String,String> properties) {
		this.properties = properties;
	}
	
	
}
