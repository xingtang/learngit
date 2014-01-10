package com.digital.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;  
import org.apache.struts2.ServletActionContext;

public class ZipUtil {
	
	private static final int BUFFER_SIZE = 16 * 1024 ;
	
	//得到後綴名
	public String getExtention(String fileName){
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}
	
	public String getFileName(String fileName){
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMM");
		String sysFileName = date.getTime()+fileName;
		sysFileName = dateFormat.format(date)+sysFileName;
		return sysFileName;
	}
	
	//創建文件夾
	public String generateFolder(String fileSrc){
		//String subDir=new DateUtil().getDateFormat("yyyy/MM/dd");
		//subDir=fileSrc+"/"+subDir;
		File f=new File(ServletActionContext.getServletContext().getRealPath(fileSrc));
		if(f.exists() && f.isDirectory()){
			return fileSrc;
		}else{
			try {
				f.mkdirs();
				return fileSrc;
			} catch (Exception e) {
				return fileSrc;
			}			
		}
	}
	
	/**
	 * 將上傳至伺服器的src 對象（系統臨時文件對象），copy 到指定的dst 對象
	 * @param src 原文件的file對象
	 * @param dst 生成后的file對象
	 */
	public void copy(File src, File dst)  {
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
    
    
	/** 
     * 构建目录 
     * @param outputDir 
     * @param subDir 
     */  
    public void createDirectory(String outputDir,String subDir){  
          
        File file = new File(outputDir);    
        if(!(subDir == null || subDir.trim().equals(""))){//子目录不为空      
            file = new File(ServletActionContext.getServletContext().getRealPath(outputDir) + "/" + subDir);  
        }
        if(!file.exists()){              
            file.mkdirs();  
        }  
          
    }
    
    /** 
     * 构建并刪除原有目录 
     * @param outputDir 
     * @param subDir 
     */  
    public void createAndDelDirectory(String outputDir,String subDir){  
          
        File file = new File(ServletActionContext.getServletContext().getRealPath(outputDir));    
        if(!(subDir == null || subDir.trim().equals(""))){//子目录不为空      
            file = new File(ServletActionContext.getServletContext().getRealPath(outputDir) + "/" + subDir);  
        }
        if(file.exists()&&file.isDirectory()){
//	        try {
//				org.apache.commons.io.FileUtils.deleteDirectory(file);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
        	try {
				del(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			file = new File(ServletActionContext.getServletContext().getRealPath(outputDir));
        }
        file.mkdirs();
    }
    
    public void del(File f) throws IOException{    
    	if(f.exists() && f.isDirectory()){
    	   if(f.listFiles().length==0){
    	      f.delete();   
    	   }else{
    	       File delFile[]=f.listFiles();   
    	       int i =f.listFiles().length;   
    	       for(int j=0;j<i;j++){   
    	           if(delFile[j].isDirectory()){   
    	                 del(new File(delFile[j].getAbsolutePath()));
    	           }   
    	           delFile[j].delete();
    	      }   
    	   }   
    	}       
    } 
    
    public String getContent(String path){
    	String content = "";
    	InputStreamReader reader = null;
    	try {
			FileInputStream fis = new FileInputStream(path);
			reader=new InputStreamReader(fis,"utf-8");//指定字符集
			char[] c=new char[1];
	    	while((reader.read(c))!=-1)
	    	{
	    		content+=c[0];
	    	}
	    	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return content;
    }
}
