package org.will.app.action;

import java.io.File;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;

public class DownloadAction implements Action
{
	private InputStream is;
	
	private String fileName;
	
	
	
	public String execute() throws Exception
	{
		//获取当前运行的项目路径
		String projectPath = ServletActionContext.getServletContext().getRealPath(File.separator);
		//为InputStream的is流参数赋值
		is = ServletActionContext.getServletContext().getResourceAsStream(projectPath+"\\电子表格.xlsx");
		fileName = new String("我的电子表格.xlsx".getBytes(),"ISO8859-1");
		return SUCCESS;
	}
	public InputStream getIs() {
		return is;
	}

	public void setIs(InputStream is) {
		this.is = is;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}

