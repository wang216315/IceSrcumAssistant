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
				
				ptask.setDepartment("����Ӧ��ƽ̨");
				//�Ŷ�
				ptask.setGroup(project.getPname());
				//�ŶӸ�����
				ptask.setGroupLeader(project.getPleader());
				//��Ŀ����
				ptask.setProjectName("������Ŀ");
				
				ptask.setUserStoryType("�̶�����");
				//������Դ
				ptask.setUserStorySource("����");
				
				
				for (Story story : stories) 
				{
					//��task�������ڸ�story
					if(isBelongTheUS(story, task))
					{
						//US����
						ptask.setUserStoryName(story.getName());
						//US���ȼ�
						ptask.setUserStoryRank(story.getRank());						
						//US״̬
						ptask.setUserStoryState(storyState(story.getState()));
						
						
						break;
					}
					else  //�����task�������κ�US�����ڽ������⴦��
					{
						//US����
						ptask.setUserStoryName(task.getName());
						//US���ȼ�
						ptask.setUserStoryRank(task.getRank());						
						//US״̬
						ptask.setUserStoryState(taskState(task.getState()));
					}
				}
				//task����
				ptask.setTaskName(task.getName());
				//task������
				if(task.getResponsible() != null)
				{
					User user = User.findUser(users, task.getResponsible().getId());
					if(user != null)
					{
						ptask.setTaskOwner(user.getName());
					}
				}
				
				
				//task������
				if(task.getInitial() != null)
				{
					ptask.setTaskWorkTime(String.valueOf((Float.parseFloat(task.getInitial())/8)));
				}
				//sprint����
				ptask.setParentSprintName(sprint.getGoal());
				//������ʼʱ��
				ptask.setSprintStartTime(dateAdapter(sprint.getStartDate()));
				//task���ʱ��
				if(task.getDoneDate() != null)
				{
					ptask.setTaskFinishedTime(dateAdapter(task.getDoneDate()));
				}

				//������ֹʱ��
				ptask.setSprintEndTime(dateAdapter(sprint.getEndDate()));
				
				//task״̬
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
			s = "����";
			break;
		case "5":
			s = "�з���";
			break;
		case "7":
			s = "���";
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
			s = "����";
			break;
		case "1":
			s = "�з���";
			break;
		case "2":
			s = "���";
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
