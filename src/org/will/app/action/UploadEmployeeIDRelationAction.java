package org.will.app.action;


import org.will.app.business.InputEmployeeIDRelationship;
import org.will.app.model.UploadFile;

import com.opensymphony.xwork2.ActionSupport;


public class UploadEmployeeIDRelationAction extends ActionSupport
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4171748050877463302L;

	private UploadFile uploadFile;
	
	public String execute() throws Exception
	{
	
		InputEmployeeIDRelationship inputs = new InputEmployeeIDRelationship();
		inputs.execute(uploadFile);
		
		return SUCCESS;
	}

	public UploadFile getUploadFile() 
	{
		return uploadFile;
	}

	public void setUploadFile(UploadFile uploadFile) {
		this.uploadFile = uploadFile;
	}
}
