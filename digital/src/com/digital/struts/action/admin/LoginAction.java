package com.digital.struts.action.admin;

import com.digital.struts.service.admin.DAdminMessageService;
import com.digital.ibatis.dto.DAdminMessage;
import com.digital.util.CipherUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;


public class LoginAction {

	private DAdminMessageService adminService;

	private String adminName;
	private String adminPassword;
	private String identifyingCode;

	public String getIdentifyingCode() {
		return identifyingCode;
	}

	public void setIdentifyingCode(String identifyingCode) {
		this.identifyingCode = identifyingCode;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String execute() throws Exception {
		if (ActionContext.getContext().getSession().get("digitalAdminiStrator") == null) {
			return "error";
		} else {
			return "success";
		}

	}

	public String adminLogin() {
		DAdminMessage dAdminMessage = adminService.findFcAdminMessageByName(this.getAdminName());
		// 如果adminMessage == null時，沒有此管理員不能登陸
		if (dAdminMessage == null) {
			return "error";
		}
		// 判斷管理員的狀態，state：0 禁用！ 不能成功登陸
		if (dAdminMessage.getdAdminState() == false) {
			return "error";
		}
		// 判斷管理員的狀態，state：0 禁用！ 不能成功登陸
		// String adminLoginIdentifyingCode =
		// (String)(ActionContext.getContext().getSession().get("adminLoginIdentifyingCode"));

		// 下麵就是將session中保存的驗證碼字符串與客戶輸入的驗證碼字符對比
		// if(adminLoginIdentifyingCode.equalsIgnoreCase(this.getIdentifyingCode())){
		CipherUtil cipherUtil = new CipherUtil();
		if (cipherUtil.validatePassword(dAdminMessage.getdAdminPwd(), this.getAdminPassword())) {
			ActionContext.getContext().getSession().put("digitalAdminiStrator",dAdminMessage);
			return "success";
		}
		// }
		return "error";

	}	
	
	public DAdminMessageService getAdminService() {
		return adminService;
	}

	public void setAdminService(DAdminMessageService adminService) {
		this.adminService = adminService;
	}

	public String getAdminName() {
		return adminName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}
}
