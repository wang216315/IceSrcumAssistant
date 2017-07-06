package org.will.app.business;

import java.util.HashMap;
import java.util.Map;

import org.will.app.conmunication.JsonOperator;
import org.will.app.model.Operator;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;


public class URLCreator
{
	public static JsonOperator Creator(Operator operator)
	{
		
		Map<String,String> properties = new HashMap<String,String>();
		String auth = operator.getUsername()+":"+operator.getPassword();		
		properties.put("authorization", "Basic "+Base64.encode(auth.getBytes()));
		properties.put("accept", "application/json");
		String requestUrl ;
		JsonOperator op;
		String requestMethod;
		
		switch (operator.getCommand())
		{
		case "TASK":
			requestUrl = "http://icescrum.intra.ffan.com/icescrum/ws/p/"+operator.getPkey()+"/task/";
			requestMethod = "GET";
			break;
		case "STORY":
			requestUrl = "http://icescrum.intra.ffan.com/icescrum/ws/p/"+operator.getPkey()+"/story/";
			requestMethod = "GET";
			break;
		case "SPRINT":
			requestUrl = "http://icescrum.intra.ffan.com/icescrum/ws/p/"+operator.getPkey()+"/sprint/";
			requestMethod = "GET";
			break;
		default:
			requestUrl = "http://icescrum.intra.ffan.com/icescrum/ws/p/"+operator.getPkey()+"/task/";
			requestMethod = "GET";		
			break;		
		}
		
		op = new JsonOperator();
		op.setRequestUrl(requestUrl);
		op.setRequestMethod(requestMethod);
		op.setProperties(properties);
		
		return op;
		
	}
}
