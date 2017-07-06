package org.will.app.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.will.app.conmunication.HttpJsonCilent;
import org.will.app.conmunication.JsonOperator;
import org.will.app.model.Operator;
import org.will.app.model.Sprint;
import org.will.app.model.Story;
import org.will.app.model.Task;

import net.sf.json.JSONArray;

public class CommonDataDownload
{
	
	
	public static List<Task> getAllTasksWithCurrentSprint(Operator operator)
	{
		operator.setCommand("TASK");
		JsonOperator jp = URLCreator.Creator(operator);
		JSONArray jsonArray = HttpJsonCilent.httpRequest(jp);

		@SuppressWarnings("unchecked")
		List<Task> tasks = JSONArray.toList(jsonArray,Task.class);
		
		return tasks;
	}
	
	public static List<Story> getAllStoriesWithCurrentSprint(Operator operator)
	{
		List<Task> tasks = getAllTasksWithCurrentSprint(operator);
		
		
		operator.setCommand("STORY");
		JsonOperator jp = URLCreator.Creator(operator);
		JSONArray jsonArray = HttpJsonCilent.httpRequest(jp);
		
		
		@SuppressWarnings("unchecked")
		List<Story> stories = JSONArray.toList(jsonArray,Story.class);
		
		Sprint sprint = CommonDataDownload.getCurrentSprintWithCurrentRelease(operator);
		Date sprintStartDate = null;
		if(sprint != null)
		{
			sprintStartDate = CommonParse.formatDate(sprint.getStartDate());
		}
		
		List<Story> newstroies = new ArrayList<Story>();
		
		if(sprint != null && sprintStartDate != null)
		{
			for (Story story : stories)
			{
				//– state: 1: suggested (sandbox), 2: accepted (backlog), 3: estimated (backlog), 4: planned (sprint), 5: in progress (sprint), 7: done (sprint)
				//– type: 0: user story, 2: defect, 3: technical story
				//– testState (summary of story’s acceptance tests states): 0: no test, 1: to check, 5: failed, 10: success
				if(story.getState().equals("5")||story.getState().equals("7"))
				{
					Date storyStartDate = CommonParse.formatDate(story.getInProgressDate());
					//US的in progress时间在当前迭代时间之后
					if(storyStartDate.after(sprintStartDate))
					{						
						
						List<Task> intasks = Arrays.asList(story.getTasks());
						
						List<Task> targetTasks = new ArrayList<Task>();
						
						
						
						for(int i=0;i<=intasks.size()-1;i++)
						{
							
							Task task = findTask(intasks.get(i).getId(), tasks);
							if(task != null)
							{
								//intasks.set(i, task);
								targetTasks.add(task);
							}		
						}	
						Task[] tempTasks = new Task[targetTasks.size()];
						story.setTasks(targetTasks.toArray(tempTasks));
						newstroies.add(story);
						
					}
				}
			}
				
		}
				
		return newstroies;
	}
	
	private static Task findTask(String id,List<Task> tasks)
	{
		for (Task task : tasks)
		{
			if(task.getId().equals(id))
			{
				return task;
			}
		}
		return null;
	}
	
	public static Sprint getCurrentSprintWithCurrentRelease(Operator operator)
	{
		operator.setCommand("SPRINT");
		JsonOperator jp = URLCreator.Creator(operator);
		JSONArray jsonArray = HttpJsonCilent.httpRequest(jp);
		
		@SuppressWarnings("unchecked")
		List<Sprint> sprints = JSONArray.toList(jsonArray,Sprint.class);
		
		for (Sprint sprint : sprints)
		{
			//sprint正好处于in progress
			if(sprint.getState().equals("2"))
			{
				return sprint;
			}
				
		}				
		return null;
	}
}
