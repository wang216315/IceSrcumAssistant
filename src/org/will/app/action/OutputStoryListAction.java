package org.will.app.action;

import java.util.ArrayList;
import java.util.List;

import org.will.app.business.StoryStatistic;
import org.will.app.model.Operator;
import org.will.app.model.Story;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class OutputStoryListAction extends ActionSupport
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8073929272904944449L;
	
	private List<Story> stories = new ArrayList<Story>();
	
	private Operator operator;
	
	
	public String execute() throws Exception
	{
		StoryStatistic storyStatistic = new StoryStatistic(operator);
		stories = storyStatistic.execute();
		ActionContext.getContext().getSession().put("stories", stories);
		return SUCCESS;
		
	}


	public List<Story> getStories() {
		return stories;
	}


	public void setStories(List<Story> stories) {
		this.stories = stories;
	}


	public Operator getOperator() {
		return operator;
	}


	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	
}
