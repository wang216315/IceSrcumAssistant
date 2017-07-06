package org.will.app.action;

import java.util.ArrayList;

import java.util.List;

import org.will.app.business.WorkTimeStatistic;
import org.will.app.model.Employee;
import org.will.app.model.Operator;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CountTaskTimeForEachPersonAction extends ActionSupport
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6785560382659862186L;
	
	private Operator operator;
	
	private List<Employee> employees = new ArrayList<Employee>() ;
	
	
	public void setEmployees(List<Employee> employees)
	{
		this.employees = employees;
	}
	
	public List<Employee> getEmployees()
	{
		return this.employees;
	}
	

	public String execute() throws Exception
	{
		ActionContext.getContext().getSession().remove(employees);
		
		WorkTimeStatistic wts = new WorkTimeStatistic(operator);
		employees = wts.execute();
		ActionContext.getContext().getSession().put("employees", employees);	
		
		return SUCCESS;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	
	




}
