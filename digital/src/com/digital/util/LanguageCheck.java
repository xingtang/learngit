package com.digital.util;

import java.util.Map;
import com.opensymphony.xwork2.ActionContext;

public class LanguageCheck {
	
	/**
	 * 檢查session中保存的语言类型 返回相应的类型
	 */
	@SuppressWarnings("unchecked")
	public static String getLanguage(){
		String lang = "";
		Map map = ActionContext.getContext().getSession();
		if(map.containsKey("zh")){
			lang = "zh";
		}else if(map.containsKey("en")){
			lang="en";
		}else if(map.containsKey("po")){
			lang ="po";
		}else{
			lang ="zh";
		}
		return lang;
	}
	
	
	/**
	 * 使用session保存类型语言
	 */
	@SuppressWarnings("unchecked")
	public static void setLanguage(int lg,String lgType){
		Map map = ActionContext.getContext().getSession();			
		switch(lg){
			case 1:  //中文 1
				map.remove("en");
				map.remove("po");
				map.put("zh", lg);
//				if(map.containsKey("lgType"))
//					map.remove("lgType");
//				map.put("lgType", lgType);
			break;
			case 2:  //英文 2
				map.remove("zh");
				map.remove("po");
				map.put("en", lg);
//				if(map.containsKey("lgType"))
//					map.remove("lgType");
//				map.put("lgType", lgType);
			break;
			case 3:  //葡文 3
				map.remove("zh");
				map.remove("en");
				map.put("po", lg);
//				if(map.containsKey("lgType"))
//					map.remove("lgType");
//				map.put("lgType", lgType);
			break;
			default:
				break;
		} 	   	 
	}
}
