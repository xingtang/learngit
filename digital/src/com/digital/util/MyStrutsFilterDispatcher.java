package com.digital.util;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.dispatcher.FilterDispatcher;

import com.opensymphony.xwork2.ActionContext;

public class MyStrutsFilterDispatcher extends FilterDispatcher {
	/**
	 *  struts2的攔截器與fck 上傳文件時衝突
	 * 重寫struts2的攔截器，增加了判斷，如果是fck的編輯器上傳文件，不進行攔截。
	 * 判斷路徑是否有fckeditor 實現在fckeditor下的操作不進行攔截
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		//初始化語言
		// TODO Auto-generated method stub
		//Map map = ActionContext.getContext().getSession();
		HttpServletRequest request = (HttpServletRequest)  req;      
		HttpServletResponse response = (HttpServletResponse) res;      
		HttpSession session = request.getSession(); 
		String  language=(String)session.getAttribute("language");
		String param_str=request.getQueryString(); 
		
		Locale defaultLocale = Locale.getDefault();
		
		//String country = defaultLocale.getCountry();//返回国家地区代码

		//String language = defaultLocale.getLanguage();//返回国家的语言

		//String displayCountry = defaultLocale.getDisplayCountry();//返回适合向用户显示的国家信息

		//String displayLanaguage = defaultLocale.getDisplayLanguage();//返回适合向用户展示的语言信息
		
		//String displayName = defaultLocale.getDisplayName();//返回适合向用户展示的语言环境名
		
		if(language == null){
			language =  defaultLocale.getLanguage();
			if(language.equals("en")){
				language="zh";
			}
		}
		
		if((param_str != null && param_str.indexOf("lgType=po")!=-1 )){
			session.setAttribute("language", "po");
		}else if(param_str != null && param_str.indexOf("lgType=en")!=-1){
			session.setAttribute("language", "zh");
		}else if(param_str != null && param_str.indexOf("lgType=zh")!=-1){
			session.setAttribute("language", "zh");
		}else{
			session.setAttribute("language", language);
		}
		
		String url = ((HttpServletRequest)req).getRequestURI(); 
		if (url.indexOf("fckeditor") < 0 && url.indexOf("/ckeditor/uploader") < 0) {
			super.doFilter(req, res, chain);
		} else {
			chain.doFilter(req, res);
		}	
//		if ("/fmac/ueditor/jsp/imageUp.jsp".equals(url)) {  
//            //System.out.println("使用自定义的过滤器");  
//            chain.doFilter(req, res);  
//        }else{  
//           // System.out.println("使用默认的过滤器");  
//            super.doFilter(req, res, chain);  
//        }  
		//
		 
	}

}
