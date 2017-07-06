package org.will.app.model;


public class Story {
	
	private String uid;
	
	private String inProgressDate;
	
	private String executionFrequency;
	
	private String state;
	
	private String actor;
	
	private String type;
	
	private Creator creator;
	
	private String id;
	
	private String doneDate;
		
	private String rank;
	
	private String description;
	
	private String name;
	
	private String feature;
	
	private Object[] acceptanceTests;
	
	private String acceptedDate;
	
	private String effort;
	
	private String lastUpdated;
	
	private Task[] tasks;
	
	private Object[] tags;
	
	private String dependsOn;
	
	private Story[] dependences;
	
	private Sprint parentSprint ;
	
	private String estimatedDate ;
	
	private String creationDate ;
	
	private String plannedDate;
	
	private String suggestedDate;
	
	private String notes;
	
	private String origin;
	
	private String testState;
	
	private String affectVersion;
	
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

	public String getExecutionFrequency() {
		return executionFrequency;
	}

	public void setExecutionFrequency(String executionFrequency) {
		this.executionFrequency = executionFrequency;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Creator getCreator() {
		return creator;
	}

	public void setCreator(Creator creator) {
		this.creator = creator;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}


	public String getAcceptedDate() {
		return acceptedDate;
	}

	public void setAcceptedDate(String acceptedDate) {
		this.acceptedDate = acceptedDate;
	}

	public String getEffort() {
		return effort;
	}

	public void setEffort(String effort) {
		this.effort = effort;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}


	public Object[] getTags() {
		return tags;
	}

	public void setTags(Object[] tags) {
		this.tags = tags;
	}

	public String getDependsOn() {
		return dependsOn;
	}

	public void setDependsOn(String dependsOn) {
		this.dependsOn = dependsOn;
	}

	public Story[] getDependences() {
		return dependences;
	}

	public void setDependences(Story[] dependences) {
		this.dependences = dependences;
	}





	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getPlannedDate() {
		return plannedDate;
	}

	public void setPlannedDate(String plannedDate) {
		this.plannedDate = plannedDate;
	}

	public String getSuggestedDate() {
		return suggestedDate;
	}

	public void setSuggestedDate(String suggestedDate) {
		this.suggestedDate = suggestedDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getEstimatedDate() {
		return estimatedDate;
	}

	public void setEstimatedDate(String estimatedDate) {
		this.estimatedDate = estimatedDate;
	}

	public String getTestState() {
		return testState;
	}

	public void setTestState(String testState) {
		this.testState = testState;
	}

	public Object[] getComments() {
		return comments;
	}

	public void setComments(Object[] comments) {
		this.comments = comments;
	}

	public Object[] getAcceptanceTests() {
		return acceptanceTests;
	}

	public void setAcceptanceTests(Object[] acceptanceTests) {
		this.acceptanceTests = acceptanceTests;
	}

	public String getAffectVersion() {
		return affectVersion;
	}

	public void setAffectVersion(String affectVersion) {
		this.affectVersion = affectVersion;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public Sprint getParentSprint() {
		return parentSprint;
	}

	public void setParentSprint(Sprint parentSprint) {
		this.parentSprint = parentSprint;
	}

	public Task[] getTasks() {
		return tasks;
	}

	public void setTasks(Task[] tasks) {
		this.tasks = tasks;
	}


	
	


	

}
