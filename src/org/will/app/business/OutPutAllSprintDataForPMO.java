package org.will.app.business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


import org.will.app.model.*;



public class OutPutAllSprintDataForPMO
{
	Operator operator;
	
	
	
	public OutPutAllSprintDataForPMO(Operator operator)
	{
		this.operator = operator;
	}
	
	public List<PTask> execute()
	{
		List<PTask> ptasks = new ArrayList<PTask>();
		for (String pk : operator.getPkeys())
		{
			operator.setPkey(pk);
			ptasks.addAll(getOneProjectPTasks());
		}
		
		return ptasks;
	}
	
	
	
	private List<PTask> getOneProjectPTasks()
	{
		List<Task> tasks = CommonDataDownload.getAllTasksWithCurrentSprint(operator);
		List<Story> stories = CommonDataDownload.getAllStoriesWithCurrentSprint(operator);
		Sprint sprint = CommonDataDownload.getCurrentSprintWithCurrentRelease(operator);
		List<Project> projects = Project.getAllProjectInDB();
		Project project = getProject(operator.getPkey(), projects);	
		List<User> users = User.getAllUsers();
		
		
		List<PTask> ptasks = new ArrayList<PTask>();

		
		
		if(project != null && sprint != null)
		{
		
			for (Task task : tasks)
			{
				
				if((task.getType() != null&&task.getType().equals("10"))||task.getInitial() == null)
				{
					continue;
				}
				
							
				PTask ptask = new PTask();
				
				ptask.setDepartment("开放应用平台");
				//团队
				ptask.setGroup(project.getPname());
				//团队负责人
				ptask.setGroupLeader(project.getPleader());
				//项目名称
				ptask.setProjectName("蓝海项目");
				
				ptask.setUserStoryType("固定需求");
				//需求来源
				ptask.setUserStorySource("技术");
				
				
				for (Story story : stories) 
				{
					//该task正好属于该story
					if(isBelongTheUS(story, task))
					{
						//US名称
						ptask.setUserStoryName(story.getName());
						//US优先级
						ptask.setUserStoryRank(story.getRank());						
						//US状态
						ptask.setUserStoryState(storyState(story.getState()));
						
						
						break;
					}
					else  //如果该task不属于任何US，则在进行特殊处理
					{
						//US名称
						ptask.setUserStoryName(task.getName());
						//US优先级
						ptask.setUserStoryRank(task.getRank());						
						//US状态
						ptask.setUserStoryState(taskState(task.getState()));
					}
				}
				//task名称
				ptask.setTaskName(task.getName());
				//task责任人
				if(task.getResponsible() != null)
				{
					User user = User.findUser(users, task.getResponsible().getId());
					if(user != null)
					{
						ptask.setTaskOwner(user.getName());
					}
				}
				
				
				//task工作量
				if(task.getInitial() != null)
				{
					ptask.setTaskWorkTime(String.valueOf((Float.parseFloat(task.getInitial())/8)));
				}
				//sprint名称
				ptask.setParentSprintName(sprint.getGoal());
				//迭代开始时间
				ptask.setSprintStartTime(dateAdapter(sprint.getStartDate()));
				//task完成时间
				if(task.getDoneDate() != null)
				{
					ptask.setTaskFinishedTime(dateAdapter(task.getDoneDate()));
				}

				//迭代截止时间
				ptask.setSprintEndTime(dateAdapter(sprint.getEndDate()));
				
				//task状态
				ptask.setTaskState(taskState(task.getState()));
				
				
				ptasks.add(ptask);
			}
		}
		
	
		return ptasks;
	}
	
	String dateAdapter(String dateStr)
	{

		Date date = CommonParse.formatDate(dateStr);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");		
		return sf.format(date);

	}
	
	String storyState(String state)
	{
		String s = "ERROR";
		switch (state) 
		{
		case "4":
			s = "待办";
			break;
		case "5":
			s = "研发中";
			break;
		case "7":
			s = "完成";
			break;
		default:
			break;
		}
		return s;
	}
	
	String taskState(String state)
	{
		String s = "ERROR";
		switch (state) 
		{
		case "0":
			s = "待办";
			break;
		case "1":
			s = "研发中";
			break;
		case "2":
			s = "完成";
			break;
		default:
			break;
		}
		return s;
	}
	
	Project getProject(String pkey,List<Project> projects)
	{
		for (Project project : projects)
		{
			if(project.getPkey().equals(pkey))
			{
				return project;
			}
		}
		return null;
	}
	
	boolean isBelongTheUS(Story story,Task task)
	{
		for (Task taskid :Arrays.asList(story.getTasks())) 
		{
			if(taskid.getId().equals(task.getId()))
			{
				return true;
			}
		}
		
		return false;
	}
}
