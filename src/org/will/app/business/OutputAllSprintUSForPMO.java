package org.will.app.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.will.app.model.Operator;
import org.will.app.model.PStory;
import org.will.app.model.Project;
import org.will.app.model.Sprint;
import org.will.app.model.Story;
import org.will.app.model.Task;
import org.will.app.model.User;

public class OutputAllSprintUSForPMO extends OutPutAllSprintDataForPMO
{
	
	public OutputAllSprintUSForPMO(Operator operator)
	{
		super(operator);
	}
	
	public List<PStory> executePStory()
	{
		List<PStory> pstories = new ArrayList<PStory>();
		for (String pk : operator.getPkeys())
		{
			operator.setPkey(pk);
			pstories.addAll(countSingleProjectUS());
		}
		
		return pstories;
	}
		
	private List<PStory> countSingleProjectUS()
	{
		List<PStory> pstories = new ArrayList<PStory>();
		List<Story> stories = CommonDataDownload.getAllStoriesWithCurrentSprint(operator);
		Sprint sprint = CommonDataDownload.getCurrentSprintWithCurrentRelease(operator);
		if(sprint == null)
		{
			//��ǰ�޵���������
			return pstories ;
		}
		List<Project> projects = Project.getAllProjectInDB();
		Project project = getProject(operator.getPkey(), projects);	
		List<User> users = User.getAllUsers();
		
		
		
		if(project != null && sprint !=null)
		{
			for (Story story : stories)
			{
				PStory pstory = new PStory();
				
				pstory.setDepartment("����Ӧ��ƽ̨");
				//�Ŷ�
				pstory.setGroup(project.getPname());
				//�ŶӸ�����
				pstory.setGroupLeader(project.getPleader());
				//��Ŀ����
				pstory.setProjectName("������Ŀ");
				
				pstory.setUserStoryType("�̶�����");
				//������Դ
				pstory.setUserStorySource("����");
				
				//US����
				pstory.setUserStoryName(story.getName());
				//US���ȼ�
				pstory.setUserStoryRank(story.getRank());	
				
				List<Task> intasks = Arrays.asList(story.getTasks());
				//US�ܹ�����
				float i = 0;
				List<String> owners = new ArrayList<String>();
				for(Task task: intasks)
				{
									
					//0:todo 1:in progress 2:done
					//��todoʱ���task��initial�ֶ�Ϊ�գ�������Ӧ��ȡestimation
					if(task.getState().equals("0") && task.getName() != null && task.getEstimation() != null)
					{
						i = i + Float.parseFloat(task.getEstimation());
						processOwner(users, task, owners);
					}
					else
					{
						if(task.getInitial() != null)
						{						
							i = i + Float.parseFloat(task.getInitial());
							processOwner(users, task, owners);
						}
					}
					
				}
				//US������
				pstory.setStoryOwner(processString(owners));
				//US�ܹ�����
				pstory.setStoryWorkTime(countWorkTimeForDay(i));
				
				//US��ʼʱ��
				pstory.setStoryStartTime(userStoryStartTimeProcess(story));
				
				
				//US���ʱ��
				if(story.getDoneDate() != null)
				{
					pstory.setStoryRealFinishTime(dateAdapter(story.getDoneDate()));
					//US�����ʱ��
					pstory.setStoryPlanFinishTime(dateAdapter(story.getDoneDate()));
				}
				else
				{
					//������ֹʱ��
					pstory.setStoryPlanFinishTime(dateAdapter(sprint.getEndDate()));
				}
							
				
				//US״̬
				pstory.setUserStoryState(storyState(story.getState()));
				
				pstory.setStoryID(story.getUid());
				
				pstories.add(pstory);
			
			}
			
		}
		
		//��urgent task�����������������Ϊһ��US
		processUrgentTask(pstories,sprint,project,users);
		
		
		return pstories;
	}
	
	private void processUrgentTask(List<PStory> pstories,Sprint sprint,Project project,List<User> users)
	{
		List<Task> tasks = CommonDataDownload.getAllTasksWithCurrentSprint(operator);
		List<Task> urgenttasks = new ArrayList<Task>();
		for (Task task : tasks)
		{
			if(task.getType() == null)
			{
				continue;
			}
			if(task.getType().equals("11"))
			{
				urgenttasks.add(task);
			}
		}
		
		PStory pstory = new PStory();
		pstory.setDepartment("����Ӧ��ƽ̨");
		//�Ŷ�
		pstory.setGroup(project.getPname());
		//�ŶӸ�����
		pstory.setGroupLeader(project.getPleader());
		//��Ŀ����
		pstory.setProjectName("������Ŀ");
		
		pstory.setUserStoryType("�̶�����");
		//������Դ
		pstory.setUserStorySource("����");
		
		//US����
		pstory.setUserStoryName("��ʱ����");
		//US���ȼ�
		pstory.setUserStoryRank("10");	
		
		
		//US�ܹ�����
		float i = 0;
		List<String> owners = new ArrayList<String>();
		for(Task task: urgenttasks)
		{
			
			//0:todo 1:in progress 2:done
			//��todoʱ���task��initial�ֶ�Ϊ�գ�������Ӧ��ȡestimation
			if(task.getState().equals("0") && task.getName() != null && task.getEstimation() != null)
			{
				i = i + Float.parseFloat(task.getEstimation());
				processOwner(users, task, owners);
			}
			else
			{
				if(task.getInitial() != null)
				{						
					i = i + Float.parseFloat(task.getInitial());
					processOwner(users, task, owners);
				}
			}
			
		}
		//US������
		pstory.setStoryOwner(processString(owners));
		//US�ܹ�����
		pstory.setStoryWorkTime(countWorkTimeForDay(i));
		
		//System.out.println(sprint.getStartDate());
		//US��ʼʱ��
		pstory.setStoryStartTime(dateAdapter(sprint.getStartDate()));
		
		
		//US״̬
		pstory.setUserStoryState(processUSState(urgenttasks));
		
		
		//US���ʱ��
		pstory.setStoryRealFinishTime(dateAdapter(sprint.getEndDate()));
		
		pstory.setStoryPlanFinishTime(dateAdapter(sprint.getEndDate()));
		
		pstory.setStoryID("100000");
					
			
		pstories.add(pstory);
		
	}
	
	private String userStoryStartTimeProcess(Story story)
	{
		
		List<Task> tasks = Arrays.asList(story.getTasks());
		Task target = null;
		try {
			//ȷ��us���Ƿ���inprogress״̬��task
			target = findTask(tasks,"1");
		} catch (Exception e) {
			System.out.println(story.getId());
		}
		
		if(target != null)
		{
			//����У�������ʱ���task��in progressʱ����Ϊus�Ŀ�ʼʱ��
			return dateAdapter(target.getInProgressDate());
			
		}
		else
		{
			//���û�У���done��������task
			target = findTask(tasks,"2");
			
			if(target != null)
			{
				//����У�ȡdone����task��inprogress�����ʱ����Ϊus��ʼʱ��
				return dateAdapter(target.getInProgressDate());
			}
			else
			{
				//���done��û��task��inprogressҲû��task����todo�У�˵��US��û�п�ʼ������ʱ��ȡ��
				return "";
			}
		}
			
		
		//����todo����û����task�������ʱ������ܵ���һ����˵����in progress��done ��û��task���������us��û�й滮��ʼ��������ʱ��ȡ��
		
	}
	//����������ѯtask,state:0��todo��1��in progress��2 done
	private Task findTask(List<Task> tasks,String state)
	{
		Task target = null;
		for (Task task : tasks)
		{		
			if(task.getState().equals(state))
			{
				if(target == null)
				{
					target = task;
				}
				else 
				{
					//�����ǰtarget��in progressʱ��Ҫ����task�����䣬�����taskʱ�仹Ҫ�磬��target�滻Ϊ��task
					if(CommonParse.formatDate(target.getInProgressDate()).after(CommonParse.formatDate(task.getInProgressDate())))
					{
						target = task;
					}
				}
			}
		}
		return target;
	}
	
	
	private String countWorkTimeForDay(float i)
	{
		return String.valueOf(i / 8);
	}
	
	private String processUSState(List<Task> tasks)
	{
		boolean flag = true;
		for (Task task : tasks)
		{
			if(task.getState().equals("0") || task.getState().equals("1"))
			{
				flag = false;
			}
		}
		if(flag)
		{
			return storyState("7");
		}
		else
		{
			return storyState("5");
		}
	}
	
	private String processString(List<String> owners)
	{
		String target = "";
		int count = 0;
		for (String s : owners)
		{
			target = s+"��" +target;
			count++;
			
		}
		if(count != 0)
		{
			target = target.substring(0, target.length() - 1);
		}
		return target;
	}
	
	private void processOwner(List<User> users,Task task,List<String> owners)
	{
		

		//task������
		if(task.getResponsible() != null)
		{
			User user = User.findUser(users, task.getResponsible().getId());
			if(user != null && !isContained(owners,user.getName()))
			{
				owners.add(user.getName());
			}
		}
	}
	
	private boolean isContained(List<String> owners,String name)
	{
		for (String o : owners)
		{
			if(o.equals(name))
			{
				return true;
			}
		}
		return false;
	}


	
}
