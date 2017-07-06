package org.will.app.action;

import org.will.app.business.InputEmployeeIDRelationship;
import org.will.app.model.User;

import com.opensymphony.xwork2.ActionSupport;

public class InsertAccountAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3646750308822186817L;
	private User user;

	public String execute() {
		if (user != null && !user.getName().isEmpty()) {

			user.setSmiscount("wangyue116");
			InputEmployeeIDRelationship inputer = new InputEmployeeIDRelationship();
			if (inputer.executeInsertSingleUser(user)) {
				return SUCCESS;
			}
		}
		return ERROR;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
