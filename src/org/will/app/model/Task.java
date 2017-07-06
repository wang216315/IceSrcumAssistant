package org.will.app.model;



public class Task
{
	private String uid;
	
	private String inProgressDate;
	
	private Responsible responsible;
	
	private String initial;
	
	private String estimation;
	
	private Story parentStory;
	
	private String state;
	
	private String lastUpdated;
	
	private String type;
	
	private Object[] participants;
	
	private Creator creater;
		
	private String id;
	
	private String creationDate;
	
	private String doneDate;
	
	private String rank;
	
	private Backlog backlog;
	
	private String color;
		
	private String description;
	
	private String name;
	
	private String blocked;
	
	private String notes;
	
	private Object[] tags;
	
	private Object[] comments;	

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getInProgressDate() {
		return inProgressDate;
	}

	public void setInProgressDate(String inProgressDate) {
		this.inProgressDate = inProgressDate;
	}



	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public String getEstimation() {
		return estimation;
	}

	public void setEstimation(String estimation) {
		this.estimation = estimation;
	}



	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getDoneDate() {
		return doneDate;
	}

	public void setDoneDate(String doneDate) {
		this.doneDate = doneDate;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}



	public String getBlocked() {
		return blocked;
	}

	public void setBlocked(String blocked) {
		this.blocked = blocked;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}


	public Backlog getBacklog() {
		return backlog;
	}

	public void setBacklog(Backlog backlog) {
		this.backlog = backlog;
	}



	public Story getParentStory() {
		return parentStory;
	}

	public void setParentStory(Story parentStory) {
		this.parentStory = parentStory;
	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}





	public Object[] getComments() {
		return comments;
	}

	public void setComments(Object[] comments) {
		this.comments = comments;
	}

	public Object[] getTags() {
		return tags;
	}

	public void setTags(Object[] tags) {
		this.tags = tags;
	}

	public Object[] getParticipants() {
		return participants;
	}

	public void setParticipants(Object[] participants) {
		this.participants = participants;
	}

	public Responsible getResponsible() {
		return responsible;
	}

	public void setResponsible(Responsible responsible) {
		this.responsible = responsible;
	}

	public Creator getCreater() {
		return creater;
	}

	public void setCreater(Creator creater) {
		this.creater = creater;
	}



	

	
	
}
