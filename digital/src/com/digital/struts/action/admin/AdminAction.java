package com.digital.struts.action.admin;


import java.util.List;

import com.digital.ibatis.dto.DAdminMessage;
import com.digital.struts.service.admin.DAdminMessageService;
//import com.fmac.struts.service.admin.FcPermissionService;
import com.digital.util.CipherUtil;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminAction implements Action {
	private DAdminMessage admins;
	private DAdminMessageService fcAdminMessageService;
	private String adminPassword;
	private int currentPage;
	private int countTotal;
	private int pageSize = 20;
	private int pageTotal;
	private String resultMessage;
	private List<DAdminMessage> adminList;
	//private FcPermissionService fcPermissionService;
	//private List<FcPermission> permissionList;
	private String permissions;
	private String searchKeywords;

	public String execute() throws Exception {
		setFcAdminAdmins((DAdminMessage) (ActionContext.getContext().getSession().get("digitalAdminiStrator")));
		return SUCCESS;
	}
    /**
     * 根據用戶的登錄賬號獲取用戶信息
     * Date:20131227
     * Auther:tx
     * */
	public String editAdminInfo() {
		admins = fcAdminMessageService.findFcAdminMessageByName(admins.getdAdminUser());
		return SUCCESS;
	}

	/*
	 * 修改用戶信息的方法
	 * Date:20131227
	 * Auther:tx
	 * **/
	public String updateAdminInfo() {
		if (adminPassword != null && adminPassword.trim().length() > 0) {			
			admins.setdAdminPwd(CipherUtil.generatePassword(adminPassword));
		}
		fcAdminMessageService.updateFcAdminMessage(admins);
		return SUCCESS;
	}

	/***/
	public String administratorList() {
		if (this.currentPage < 1)
			setCurrentPage(1);
		setCountTotal(this.fcAdminMessageService.countFcAdminMessage(searchKeywords));
		setPageTotal(this.countTotal / this.pageSize+ (this.countTotal % this.pageSize > 0 ? 1 : 0));
		if ((this.currentPage > this.pageTotal) && (this.pageTotal != 0))
			setCurrentPage(this.pageTotal);
		setAdminList(this.fcAdminMessageService.showFcAdminMessage(	this.currentPage, this.pageSize, searchKeywords));
		return "success";
	}

	/**
	 * 根據用戶主鍵刪除用戶信息的方法
	 * Date:20131227
	 * Auther:tx
	 * */
	public String deleteAdministrator() {
		DAdminMessage a = (DAdminMessage) ActionContext.getContext().getSession().get("digitalAdminiStrator");
		this.admins = this.fcAdminMessageService.findFcAdminMessageById(this.admins.getdAdminId().intValue());
		if ((a.getdAdminRole() != 1)|| (this.admins.getdAdminRole().byteValue() == 1)) {
			this.resultMessage = "deny";
			return "success";
		}
		int result = this.fcAdminMessageService.deleteFcAdminMessage(this.admins.getdAdminId().intValue());
		if (result == 1)
			this.resultMessage = "success";
		else
			this.resultMessage = "failed";
		return "success";
	}

	/***/
	public String saveAdministrator() {
		DAdminMessage a = this.fcAdminMessageService.findFcAdminMessageByName(this.admins.getdAdminUser());
		if (a != null) {
			this.resultMessage = "deny";
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
			this.admins.setdAdminRegTime(new Date());
			this.admins.setdAdminState(true);
			this.admins.setdAdminRole(0);
			this.admins.setdAdminNo(sdf.format(new Date()));			
			this.admins.setdAdminPwd(CipherUtil.generatePassword(this.admins.getdAdminPwd()));
			this.admins.setdAdminPermission(this.permissions);
			this.fcAdminMessageService.insertFcAdminMessage(this.admins);
			this.resultMessage = "success";
		}
		return "success";
	}

	public String updateAdministrator() {
		DAdminMessage b = this.fcAdminMessageService.findFcAdminMessageById(this.admins.getdAdminId().intValue());
		DAdminMessage a = (DAdminMessage) ActionContext.getContext().getSession().get("digitalAdminiStrator");
		if ((a.getdAdminRole().byteValue() != 1)|| (b.getdAdminRole().byteValue() == 1)) {
			this.resultMessage = "deny";
		} else {
			if ((this.admins.getdAdminPwd() != null)&& (this.admins.getdAdminPwd().trim().length() > 0)) {
				b.setdAdminPwd(CipherUtil.generatePassword(this.admins.getdAdminPwd()));
			}
			b.setdAdminPermission(this.permissions);
			this.fcAdminMessageService.updateFcAdminMessage(b);
			this.resultMessage = "success";
		}
		return "success";
	}

	/**跟新用戶狀態信息的方法
	 * Date:20131227
	 * Auther:tx
	 * */
	public String updateAdminStatus() {
		DAdminMessage b = this.fcAdminMessageService.findFcAdminMessageById(this.admins.getdAdminId().intValue());
		DAdminMessage a = (DAdminMessage) ActionContext.getContext().getSession().get("digitalAdminiStrator");
		if ((a.getdAdminRole().byteValue() != 1)|| (b.getdAdminRole().byteValue() == 1)) {
			this.resultMessage = "deny";
		} else {
			b.setdAdminState(this.admins.getdAdminState());
			this.fcAdminMessageService.updateFcAdminMessage(b);
			this.resultMessage = "success";
		}
		return "success";
	}
	
	
	public String adminMenu() {
		return SUCCESS;
	}
	public String nimdaSublevelMenu() {
		return SUCCESS;
	}
	
	public String adminAbout() {
		return SUCCESS;
	}

	public DAdminMessage getFcAdminAdmins() {
		return admins;
	}

	public void setFcAdminAdmins(DAdminMessage admins) {
		this.admins = admins;
	}

	public void setfcAdminMessageService(DAdminMessageService fcAdminMessageService) {
		this.fcAdminMessageService = fcAdminMessageService;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public DAdminMessage getAdmins() {
		return admins;
	}

	public void setAdmins(DAdminMessage admins) {
		this.admins = admins;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCountTotal() {
		return countTotal;
	}

	public void setCountTotal(int countTotal) {
		this.countTotal = countTotal;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public List<DAdminMessage> getAdminList() {
		return adminList;
	}

	public void setAdminList(List<DAdminMessage> adminList) {
		this.adminList = adminList;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	@JSON(serialize = false)
	public DAdminMessageService getFcAdminMessageService() {
		return fcAdminMessageService;
	}

	public String getSearchKeywords() {
		return searchKeywords;
	}

	public void setSearchKeywords(String searchKeywords) {
		this.searchKeywords = searchKeywords;
	}
}
