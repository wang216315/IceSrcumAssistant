package org.will.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Employee 
{
	public Employee(String id,String name,String group)
	{
		this.pid = id;
		this.name = name;
		this.group = group;
	}
	
	private String pid;
	
	private String name;
	
	private String group;
	//迭代task总数
	private int sprintTotalTask = 0;
	//迭代总工作量
	private float sprintInitialTime = 0;
	//已经完成工作量
	private float sprintFinishedTime = 0;	
	//迭代剩余工作量
	private float sprintRemainingTime = 0;	
	//个人正常Task列表
	private List<Task> tasks = new ArrayList<Task>();
	//个人异常task（特指take了任务，但是没有评估工作量或初始工作量为null的任务，需要人工处理）
	private List<Task> errorTask = new ArrayList<Task>();
	//已经完成工作量百分比
	private float sprintFinishedPercent = 0; 

	public String getPid() 
	{
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}


	public int getSprintTotalTask() {
		return sprintTotalTask;
	}

	public void setSprintTotalTask(int sprintTotalTask) {
		this.sprintTotalTask = sprintTotalTask;
	}

	public List<Task> getErrorTask() {
		return errorTask;
	}

	public void setErrorTask(List<Task> errorTask) {
		this.errorTask = errorTask;
	}


	public static List<Employee> getUserIDNameRalation(Operator operator)
	{

		
			
		Map<String,String> nameIdRelation = new HashMap<String,String>();
		
		nameIdRelation.put("232", "李承珍,Android");
		nameIdRelation.put("97", "贺青龙,Android");
		nameIdRelation.put("202", "梁瑞,Android");		
		nameIdRelation.put("203", "王振宇,Android");
		nameIdRelation.put("11", "宗福享,Android");		
		nameIdRelation.put("201", "陈学斌,Android");
		nameIdRelation.put("355", "王华,Android");
		
		
		nameIdRelation.put("82", "赵宏伟,iOS");
		nameIdRelation.put("204", "王楠,iOS");
		nameIdRelation.put("146", "苑军霞,iOS");
			
		nameIdRelation.put("223", "孙显娇,XAPI");
		nameIdRelation.put("225", "张福睦,XAPI");
		nameIdRelation.put("285", "时永旺,XAPI");

		
		List<Employee> employees = new ArrayList<Employee>();
		
		
		for (Entry<String,String> entry : nameIdRelation.entrySet())
		{
			String id = entry.getKey();
			String[] temps = entry.getValue().split(",");
			String name = temps[0];
			String group = temps[1];
			Employee e = new Employee(id,name,group);
			employees.add(e);
		}
		
				
		return employees;
		
	}

	public float getSprintInitialTime() {
		return sprintInitialTime;
	}

	public void setSprintInitialTime(float sprintInitialTime) {
		this.sprintInitialTime = sprintInitialTime;
	}

	public float getSprintRemainingTime() {
		return sprintRemainingTime;
	}

	public void setSprintRemainingTime(float sprintRemainingTime) {
		this.sprintRemainingTime = sprintRemainingTime;
	}

	public float getSprintFinishedTime() {
		return sprintFinishedTime;
	}

	public void setSprintFinishedTime(float sprintFinishedTime) {
		this.sprintFinishedTime = sprintFinishedTime;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public float getSprintFinishedPercent() {
		return sprintFinishedPercent;
	}

	public void setSprintFinishedPercent(float sprintFinishedPercent) {
		this.sprintFinishedPercent = sprintFinishedPercent;
	}
	
	
	

	
}
