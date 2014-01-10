package com.digital.util;

import java.util.Map;

import com.digital.ibatis.dto.DAdminMessage;
import com.digital.struts.action.admin.LoginAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckAdminLoginInterceptor extends AbstractInterceptor{
	public static final String LOGIN_KEY = "digitalAdminiStrator";
	public static final String LOGIN_PAGE = "admin.login";
	
	public String intercept(ActionInvocation actionInvocation) throws Exception{
		//對LoginAction 不做該項攔截
		Object action = actionInvocation.getAction();
		if(action instanceof LoginAction){
			return actionInvocation.invoke();
		}
		
		//確認session中是否存在fmacAdminiStrator
		Map session = actionInvocation.getInvocationContext().getSession();
		DAdminMessage admin = (DAdminMessage) session.get(LOGIN_KEY);
		if(admin != null && admin.getdAdminState() == true){
			//確認session中是否存在fmacAdminiStrator
			return actionInvocation.invoke();
		}else{
			//否則終止後續操作，返回LOGIN
			return LOGIN_PAGE;
		}
		
		
	}
	
}
