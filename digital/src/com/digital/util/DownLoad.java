package com.digital.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class DownLoad  extends ActionSupport {	
	/**
	 * 文件下载的 Action
	 */
	private String fileName;

		
    public void setFileName() {
    //得到请求下载的文件名  
		   String fname = ServletActionContext.getRequest().getParameter("name");
		      this.fileName = fname;
   }

   public String getFileName() throws UnsupportedEncodingException {
		  return fileName;
   }
   
   public InputStream getDownloadFile() {
		 this.setFileName();
		 int endNum=fileName.lastIndexOf("/")+1;
	     int startNum=fileName.indexOf("/");
	     String uploadFile=fileName.substring(startNum, endNum);
	     fileName=fileName.substring(endNum);
	     //System.out.println("++++++++"+fileName);
		 return ServletActionContext.getServletContext().getResourceAsStream(uploadFile+fileName);
   }
  
  @Override
  public String execute() throws Exception {
		        return SUCCESS;
  }
}
