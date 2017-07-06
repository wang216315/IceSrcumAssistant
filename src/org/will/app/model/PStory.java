package org.will.app.model;

public class PStory extends PTask
{
	private String storyWorkTime;
	
	private String storyOwner;
	
	private String storyStartTime;
	
	private String storyRealFinishTime;
	
	private String storyPlanFinishTime;
	
	private String storyID;

	public String getStoryWorkTime() {
		return storyWorkTime;
	}

	public void setStoryWorkTime(String storyWorkTime) {
		this.storyWorkTime = storyWorkTime;
	}

	public String getStoryOwner() {
		return storyOwner;
	}

	public void setStoryOwner(String storyOwner) {
		this.storyOwner = storyOwner;
	}

	public String getStoryStartTime() {
		return storyStartTime;
	}

	public void setStoryStartTime(String storyStartTime) {
		this.storyStartTime = storyStartTime;
	}

	public String getStoryRealFinishTime() {
		return storyRealFinishTime;
	}

	public void setStoryRealFinishTime(String storyRealFinishTime) {
		this.storyRealFinishTime = storyRealFinishTime;
	}

	public String getStoryPlanFinishTime() {
		return storyPlanFinishTime;
	}

	public void setStoryPlanFinishTime(String storyPlanFinishTime) {
		this.storyPlanFinishTime = storyPlanFinishTime;
	}

	public String getStoryID() {
		return storyID;
	}

	public void setStoryID(String storyID) {
		this.storyID = storyID;
	}
	
}
