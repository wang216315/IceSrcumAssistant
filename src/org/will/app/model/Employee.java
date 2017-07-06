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
	//����task����
	private int sprintTotalTask = 0;
	//�����ܹ�����
	private float sprintInitialTime = 0;
	//�Ѿ���ɹ�����
	private float sprintFinishedTime = 0;	
	//����ʣ�๤����
	private float sprintRemainingTime = 0;	
	//��������Task�б�
	private List<Task> tasks = new ArrayList<Task>();
	//�����쳣task����ָtake�����񣬵���û���������������ʼ������Ϊnull��������Ҫ�˹�����
	private List<Task> errorTask = new ArrayList<Task>();
	//�Ѿ���ɹ������ٷֱ�
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
		
		nameIdRelation.put("232", "�����,Android");
		nameIdRelation.put("97", "������,Android");
		nameIdRelation.put("202", "����,Android");		
		nameIdRelation.put("203", "������,Android");
		nameIdRelation.put("11", "�ڸ���,Android");		
		nameIdRelation.put("201", "��ѧ��,Android");
		nameIdRelation.put("355", "����,Android");
		
		
		nameIdRelation.put("82", "�Ժ�ΰ,iOS");
		nameIdRelation.put("204", "���,iOS");
		nameIdRelation.put("146", "Է��ϼ,iOS");
			
		nameIdRelation.put("223", "���Խ�,XAPI");
		nameIdRelation.put("225", "�Ÿ���,XAPI");
		nameIdRelation.put("285", "ʱ����,XAPI");

		
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
