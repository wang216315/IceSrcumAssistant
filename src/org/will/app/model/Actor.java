package org.will.app.model;

public class Actor
{
	private String uid;
	
	private String id;
	
	private String creationDate;
	
	private Story[] stories;
	
	private String useFrequency;
	
	private String description;
	
	private String name;
	
	private String lastUpdated;
	
	private String expertnessLevel;
	
	private String satisfactionCriteria;
	
	private String notes;
	
	private String[] tags;
	
	private String instances;
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public Story[] getStories() {
		return stories;
	}

	public void setStories(Story[] stories) {
		this.stories = stories;
	}

	public String getUseFrequency() {
		return useFrequency;
	}

	public void setUseFrequency(String useFrequency) {
		this.useFrequency = useFrequency;
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

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getExpertnessLevel() {
		return expertnessLevel;
	}

	public void setExpertnessLevel(String expertnessLevel) {
		this.expertnessLevel = expertnessLevel;
	}

	public String getSatisfactionCriteria() {
		return satisfactionCriteria;
	}

	public void setSatisfactionCriteria(String satisfactionCriteria) {
		this.satisfactionCriteria = satisfactionCriteria;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public String getInstances() {
		return instances;
	}

	public void setInstances(String instances) {
		this.instances = instances;
	}
}
