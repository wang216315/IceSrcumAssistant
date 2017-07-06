package org.will.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Operator
{
	private String username;
	
	private String password;
	
	private String pkey;
	
	private String command;
	
	private List<String> pkeys = new ArrayList<String>();
	
	
	private Map<String,String> properties = new HashMap<String, String>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPkey() {
		return pkey;
	}

	public void setPkey(String pkey) {
		this.pkey = pkey;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public Map<String,String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String,String> properties) {
		this.properties = properties;
	}

	public List<String> getPkeys() {
		return pkeys;
	}

	public void setPkeys(List<String> pkeys) {
		this.pkeys = pkeys;
	}
	
	
}
