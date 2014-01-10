package com.digital.struts.service.admin;

import com.digital.ibatis.dao.DAdminMessageDAO;
import com.digital.ibatis.dto.DAdminMessage;
import com.digital.ibatis.dto.DAdminMessageExample;
import com.digital.ibatis.dto.DAdminMessageExample.Criteria;
import org.apache.log4j.Logger;
import java.util.List;

public class DAdminMessageService {
	
	private final Logger logger = Logger.getLogger(DAdminMessageService.class);
	
	private DAdminMessageDAO dadminMessageDao = null;	
	
	
	
	public DAdminMessageDAO getDadminMessageDao() {
		return dadminMessageDao;
	}
	public void setDadminMessageDao(DAdminMessageDAO dadminMessageDao) {
		this.dadminMessageDao = dadminMessageDao;
	}
	/**
	 * 添加後臺管理員信息方法
	 * Date:20131226
	 * Auther:tx
	 * Para
	 * dadminMessage:管理員對象
	 * */
	public Integer insertFcAdminMessage(DAdminMessage dadminMessage){		
    	try {
    		dadminMessageDao.insert(dadminMessage);
		} catch (Exception e) {
			logger.debug(e.getMessage());
			return 0;
		}
			
		return 1;
	}
	/*
	 * 獲取後臺管理員信息集合的方法
	 * Date:20131226
	 * Auther:tx
	 * para:
	 * currentPage :頁碼
	 * pageSize：頁面顯示數量
	 * searchKeywords：搜索關鍵字***/
	public List<DAdminMessage> showFcAdminMessage(int currentPage, int pageSize,String searchKeywords){
		DAdminMessageExample fcAdminExample = new DAdminMessageExample();
		Criteria criteria = fcAdminExample.createCriteria();
		if(searchKeywords!=null && !searchKeywords.equals("")){
			criteria.andDAdminUserLike("%"+searchKeywords.trim()+"%");
		}
		fcAdminExample.setOrderByClause(" d_admin_id desc limit "+(currentPage-1) * pageSize+","+pageSize);
		List<DAdminMessage> list = dadminMessageDao.selectByExample(fcAdminExample);
		return list;
	}
	/**
	 * 根據用戶主鍵搜索用戶信息
	 * Date:20131226
	 * Auther:tx
	 * para:id:主鍵
	 *  */
	public DAdminMessage findFcAdminMessageById(Integer id){
		DAdminMessage fcadminMessage = dadminMessageDao.selectByPrimaryKey(id);
		return fcadminMessage;
	}
	/**根據用戶登錄賬號獲取信息
	 * Date:20131226
	 * Auther:tx
	 * para:name :用戶登錄賬號
	 * */
	public DAdminMessage findFcAdminMessageByName(String name){
		DAdminMessageExample fcAdminExample = new DAdminMessageExample();
		fcAdminExample.createCriteria().andDAdminUserEqualTo(name);		
		List<DAdminMessage> list= dadminMessageDao.selectByExample(fcAdminExample);
		DAdminMessage fcAdminMessage = null;
		if(list != null && list.size()>0) fcAdminMessage = list.get(0);
		return fcAdminMessage;
	}
	/**
	 * 根據關鍵字獲取其信息數量
	 * Date:20131226
	 * Auther:tx
	 * Para:
	 * searchKeywords:搜索關鍵字（用戶登錄賬號）*/
	public int countFcAdminMessage(String searchKeywords){
		DAdminMessageExample dAdminExample = new DAdminMessageExample();
		Criteria criteria = dAdminExample.createCriteria();
		if(searchKeywords!=null && !searchKeywords.equals("")){
			criteria.andDAdminUserLike("%"+searchKeywords.trim()+"%");
		}
		int total = dadminMessageDao.countByExample(dAdminExample);
		return total;
		
	}
	/**
	 * 更新用戶信息的方法
	 * Date:20131226
	 * Auther:tx
	 * Para:dAdminMessage:用戶對象
	 * */
	public int updateFcAdminMessage(DAdminMessage dAdminMessage){
		try {
			dadminMessageDao.updateByPrimaryKeySelective(dAdminMessage);
		} catch (Exception e) {
			logger.debug(e.getMessage());
			return 0;
			// TODO: handle exception
		}
		return 1;
		
	}
	/**
	 * 根據用戶主鍵刪除用戶信息
	 * Date:20131226
	 * Auther:tx
	 * Para:
	 * id：主鍵**/
	public int deleteFcAdminMessage(Integer id){
		 try {
			 dadminMessageDao.deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.debug(e.getMessage());
			return 0;
		}
		return 1;
	}
}
