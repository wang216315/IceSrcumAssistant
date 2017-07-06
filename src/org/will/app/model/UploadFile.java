package org.will.app.model;

import java.io.File;

public class UploadFile
{
	private File file;
	
	private String savefilePath;
	
	private String savefileName;
	
	private String savefileContentType;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getSavefilePath() {
		return savefilePath;
	}

	public void setSavefilePath(String savefilePath) {
		this.savefilePath = savefilePath;
	}

	public String getSavefileName() {
		return savefileName;
	}

	public void setSavefileName(String savefileName) {
		this.savefileName = savefileName;
	}

	public String getSavefileContentType() {
		return savefileContentType;
	}

	public void setSavefileContentType(String savefileContentType) {
		this.savefileContentType = savefileContentType;
	}
	
	
	
	
}
