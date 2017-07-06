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
		//��ȡ��ǰ���е���Ŀ·��
		String projectPath = ServletActionContext.getServletContext().getRealPath(File.separator);
		//ΪInputStream��is��������ֵ
		is = ServletActionContext.getServletContext().getResourceAsStream(projectPath+"\\���ӱ��.xlsx");
		fileName = new String("�ҵĵ��ӱ��.xlsx".getBytes(),"ISO8859-1");
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

