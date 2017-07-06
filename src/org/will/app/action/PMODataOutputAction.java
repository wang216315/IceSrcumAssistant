package org.will.app.action;


import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.will.app.business.OutputAllSprintUSForPMO;
import org.will.app.business.OutputPMODateForExcel;
import org.will.app.model.*;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PMODataOutputAction extends ActionSupport
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7333543367814201133L;

	private Operator operator;
	
	private InputStream fis;
	
	private List<PTask> ptasks;
	
	private List<PStory> pstories;
	
	private String downDownFileName;
	
	public String execute() throws Exception
	{
		//获取真实项目路径
		String projectPath = ServletActionContext.getServletContext().getRealPath(File.separator);
		String tempPath = projectPath + "/WEB-INF/classes/tempPMO.xlsx";
		operator.getProperties().put("projectPath", projectPath);
		operator.getProperties().put("tempFilePath", tempPath);
		
		OutputAllSprintUSForPMO pmo = new OutputAllSprintUSForPMO(operator);
		
		pstories = pmo.executePStory();
		System.out.println(pstories.size());
		//ActionContext.getContext().getSession().remove("ptasks");
		ActionContext.getContext().getSession().remove("pstories");
		ActionContext.getContext().getSession().remove("operator");
		//ActionContext.getContext().getSession().put("ptasks", ptasks);
		ActionContext.getContext().getSession().put("pstories", pstories);
		ActionContext.getContext().getSession().put("operator", operator);
		return SUCCESS;
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	public String downloadPMODataExcel() throws Exception
	{
		pstories = (List<PStory>) ActionContext.getContext().getSession().get("pstories");
		operator = (Operator) ActionContext.getContext().getSession().get("operator");
		OutputPMODateForExcel opfe = new OutputPMODateForExcel(operator);				
		DownloadFile dfile = opfe.execute(pstories);	
		//System.out.println(path);
		fis = ServletActionContext.getServletContext().getResourceAsStream("/WEB-INF/classes/"+dfile.getFileFullName());
		//System.out.println(dfile.getFileName());
		downDownFileName = new String(dfile.getFileFullName().getBytes(),"ISO8859-1");
		return SUCCESS;
	}
	


	public List<PTask> getPtasks() {
		return ptasks;
	}

	public void setPtasks(List<PTask> ptasks) {
		this.ptasks = ptasks;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}



	public InputStream getFis() {
		return fis;
	}



	public void setFis(InputStream fis) {
		this.fis = fis;
	}



	public String getDownDownFileName() 
	{
		return downDownFileName;
	}



	public void setDownDownFileName(String downDownFileName)
	{
		this.downDownFileName = downDownFileName;
	}



	public List<PStory> getPstories() {
		return pstories;
	}



	public void setPstories(List<PStory> pstories) {
		this.pstories = pstories;
	}



}
