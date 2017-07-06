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
			//当前无迭代被激活
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
				
				pstory.setDepartment("开放应用平台");
				//团队
				pstory.setGroup(project.getPname());
				//团队负责人
				pstory.setGroupLeader(project.getPleader());
				//项目名称
				pstory.setProjectName("蓝海项目");
				
				pstory.setUserStoryType("固定需求");
				//需求来源
				pstory.setUserStorySource("技术");
				
				//US名称
				pstory.setUserStoryName(story.getName());
				//US优先级
				pstory.setUserStoryRank(story.getRank());	
				
				List<Task> intasks = Arrays.asList(story.getTasks());
				//US总工作量
				float i = 0;
				List<String> owners = new ArrayList<String>();
				for(Task task: intasks)
				{
									
					//0:todo 1:in progress 2:done
					//在todo时候得task，initial字段为空，工作量应该取estimation
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
				//US责任人
				pstory.setStoryOwner(processString(owners));
				//US总工作量
				pstory.setStoryWorkTime(countWorkTimeForDay(i));
				
				//US开始时间
				pstory.setStoryStartTime(userStoryStartTimeProcess(story));
				
				
				//US完成时间
				if(story.getDoneDate() != null)
				{
					pstory.setStoryRealFinishTime(dateAdapter(story.getDoneDate()));
					//US做完的时间
					pstory.setStoryPlanFinishTime(dateAdapter(story.getDoneDate()));
				}
				else
				{
					//迭代截止时间
					pstory.setStoryPlanFinishTime(dateAdapter(sprint.getEndDate()));
				}
							
				
				//US状态
				pstory.setUserStoryState(storyState(story.getState()));
				
				pstory.setStoryID(story.getUid());
				
				pstories.add(pstory);
			
			}
			
		}
		
		//把urgent task栏里面的所有任务处理为一个US
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
		pstory.setDepartment("开放应用平台");
		//团队
		pstory.setGroup(project.getPname());
		//团队负责人
		pstory.setGroupLeader(project.getPleader());
		//项目名称
		pstory.setProjectName("蓝海项目");
		
		pstory.setUserStoryType("固定需求");
		//需求来源
		pstory.setUserStorySource("技术");
		
		//US名称
		pstory.setUserStoryName("及时任务");
		//US优先级
		pstory.setUserStoryRank("10");	
		
		
		//US总工作量
		float i = 0;
		List<String> owners = new ArrayList<String>();
		for(Task task: urgenttasks)
		{
			
			//0:todo 1:in progress 2:done
			//在todo时候得task，initial字段为空，工作量应该取estimation
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
		//US责任人
		pstory.setStoryOwner(processString(owners));
		//US总工作量
		pstory.setStoryWorkTime(countWorkTimeForDay(i));
		
		//System.out.println(sprint.getStartDate());
		//US开始时间
		pstory.setStoryStartTime(dateAdapter(sprint.getStartDate()));
		
		
		//US状态
		pstory.setUserStoryState(processUSState(urgenttasks));
		
		
		//US完成时间
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
			//确认us中是否有inprogress状态的task
			target = findTask(tasks,"1");
		} catch (Exception e) {
			System.out.println(story.getId());
		}
		
		if(target != null)
		{
			//如果有，找最早时间的task的in progress时间作为us的开始时间
			return dateAdapter(target.getInProgressDate());
			
		}
		else
		{
			//如果没有，看done里面有无task
			target = findTask(tasks,"2");
			
			if(target != null)
			{
				//如果有，取done里面task中inprogress最早的时间作为us开始时间
				return dateAdapter(target.getInProgressDate());
			}
			else
			{
				//如果done里没有task，inprogress也没有task，但todo有，说明US还没有开始开做，时间取空
				return "";
			}
		}
			
		
		//不管todo里面没有有task，但这个时候代码能到这一步，说明该in progress和done 都没有task，代表这个us就没有规划开始做，所以时间取空
		
	}
	//根据条件查询task,state:0，todo，1，in progress，2 done
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
					//如果当前target的in progress时间要早于task，不变，如果新task时间还要早，则target替换为新task
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
			target = s+"、" +target;
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
		

		//task责任人
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
