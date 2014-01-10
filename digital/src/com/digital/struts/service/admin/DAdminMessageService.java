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
	 * �K�[��O�޲z���H����k
	 * Date:20131226
	 * Auther:tx
	 * Para
	 * dadminMessage:�޲z����H
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
	 * �����O�޲z���H�����X����k
	 * Date:20131226
	 * Auther:tx
	 * para:
	 * currentPage :���X
	 * pageSize�G������ܼƶq
	 * searchKeywords�G�j������r***/
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
	 * �ھڥΤ�D��j���Τ�H��
	 * Date:20131226
	 * Auther:tx
	 * para:id:�D��
	 *  */
	public DAdminMessage findFcAdminMessageById(Integer id){
		DAdminMessage fcadminMessage = dadminMessageDao.selectByPrimaryKey(id);
		return fcadminMessage;
	}
	/**�ھڥΤ�n���㸹����H��
	 * Date:20131226
	 * Auther:tx
	 * para:name :�Τ�n���㸹
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
	 * �ھ�����r�����H���ƶq
	 * Date:20131226
	 * Auther:tx
	 * Para:
	 * searchKeywords:�j������r�]�Τ�n���㸹�^*/
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
	 * ��s�Τ�H������k
	 * Date:20131226
	 * Auther:tx
	 * Para:dAdminMessage:�Τ��H
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
	 * �ھڥΤ�D��R���Τ�H��
	 * Date:20131226
	 * Auther:tx
	 * Para:
	 * id�G�D��**/
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
