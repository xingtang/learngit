package com.digital.struts.action.admin;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public class ExitAction  implements Action{
	public String execute() throws Exception{
		ActionContext.getContext().getSession().put("digitalAdminiStrator", null);
		return SUCCESS;
	}
	
}