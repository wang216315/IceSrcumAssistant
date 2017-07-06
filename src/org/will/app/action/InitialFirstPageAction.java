package org.will.app.action;

import java.util.ArrayList;
import java.util.List;

import org.will.app.model.Project;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class InitialFirstPageAction extends ActionSupport
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2391841450185181569L;
	
	//必须是action类的属性，有get方法，才能把这个参数放入session中,存session中的名字也必须和属性名一样
	private List<Project> projects = new ArrayList<Project>();

	public String execute()
	{
		ActionContext.getContext().getSession().remove(projects);
		projects = Project.getAllProjectInDB();
		//System.out.println(projects.get(0).getPkey());
		ActionContext.getContext().getSession().put("projects", projects);	
		return SUCCESS;
		
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}


}
