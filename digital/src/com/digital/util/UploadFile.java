package com.digital.util;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
/*
 * ljl 2012-11-12
 * 文件上傳
 * */
public class UploadFile {
	
	private static final long serialVersionUID = 572146812454l ;
    private static final int BUFFER_SIZE = 16 * 1024 ;
	public String saveFile(File myFile,String fileSrc,String fileName){
		String tempFileName = getExtention(fileName);
		tempFileName = getFileName(tempFileName);
		File tempFile = new File(ServletActionContext.getServletContext().getRealPath(fileSrc)+File.separator+tempFileName);
		copy(myFile,tempFile);
		return fileSrc+File.separator+tempFileName;
	}
	private static String getExtention(String fileName){
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}
	private static String getFileName(String fileName){
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMM");
		String sysFileName = date.getTime()+fileName;
		sysFileName = dateFormat.format(date)+sysFileName;
		return sysFileName;
	}
	private static void copy(File src, File dst)  {
        try {
           InputStream in = null ;
           OutputStream out = null ;
            try {                
               in = new BufferedInputStream( new FileInputStream(src), BUFFER_SIZE);
               out = new BufferedOutputStream( new FileOutputStream(dst), BUFFER_SIZE);
                byte [] buffer = new byte [BUFFER_SIZE];
                while (in.read(buffer) > 0 ) {
                   out.write(buffer);
               }
           } finally {
                if ( null != in) {
                   in.close();
               }
                if ( null != out) {
                   out.close();
               }
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
	
	//創建文件存放目錄
	
	public String generateFolder(String fileSrc){
		String subDir=new DateUtil().getDateFormat("yyyy/MM/dd");
		subDir=fileSrc+"/"+subDir;
		File f=new File(ServletActionContext.getServletContext().getRealPath(subDir));
		if(f.exists() && f.isDirectory()){
			return subDir;
		}else{
			try {
				f.mkdirs();
				return subDir;
			} catch (Exception e) {
				return fileSrc;
			}			
		}
	}
}
