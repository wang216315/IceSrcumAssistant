package org.will.app.business;


import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

import org.will.app.model.Operator;
import org.will.app.model.Story;
import org.will.app.model.Task;



public class StoryStatistic 
{
	private Operator operator;
	
	public StoryStatistic(Operator operator)
	{
		this.operator = operator;
	
	}
	
	public List<Story> execute()
	{
		
		List<Story> stories = new ArrayList<Story>();
		for (String pk : operator.getPkeys())
		{
			operator.setPkey(pk);
			stories.addAll(getOneProjectStories());
		}
		

		return stories;
	}
	
	private List<Story> getOneProjectStories()
	{
		List<Story> stories = CommonDataDownload.getAllStoriesWithCurrentSprint(operator);
		  
		List<Task> tasks = CommonDataDownload.getAllTasksWithCurrentSprint(operator);
			
		CombineData(stories, tasks);
		
		return stories;
	}

	
	private  Task findTask(String id,List<Task> tasks)
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
	
	private  void CombineData(List<Story> stories,List<Task> tasks)
	{		
		for (Story story : stories)
		{
			List<Task> intasks = Arrays.asList(story.getTasks());
			
			for(int i=0;i<=intasks.size()-1;i++)
			{
				
				Task task = findTask(intasks.get(i).getId(), tasks);
				if(task != null)
				{
					intasks.set(i, task);
				}		
			}
		}
	}
	
	
}
