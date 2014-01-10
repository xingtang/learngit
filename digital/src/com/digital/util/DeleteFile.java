package com.digital.util;
import java.io.File;
import org.apache.struts2.ServletActionContext;
/*
 * ljl 2012-11-12
 * 刪除文件
 * */
public class DeleteFile {
	public DeleteFile(){
		super();
	}
	
	//遞歸刪除文件及所在的空文件夾
	public int fileDelete(String fileSrc){
		if(!fileSrc.equals(""))
			{
			
			if(!fileSrc.startsWith("/"))
			{
				fileSrc="/"+fileSrc;
			}
			try {
				File f=new File(ServletActionContext.getServletContext().getRealPath(fileSrc));
				if(!f.exists()){
						return 1;
					}
				if(f.exists() && f.isFile()){
					f.delete();
					fileSrc = fileSrc.substring(0,fileSrc.lastIndexOf("/"));
					
					fileDelete(fileSrc);
				}
				if(f.exists() && f.isDirectory()){		
					
						if(f.listFiles().length>0)	
							return 1;
						else
							{
							f.delete();
							fileSrc = fileSrc.substring(0,fileSrc.lastIndexOf("/"));
							fileDelete(fileSrc);
							}
							
				}
				
				} catch (Exception e) {
						return 0;
					}	
			return 1;
			}
		else{
			return 1;
		}
	}
	
	//刪除文件夾及子文件
	public int folderDelete(String folderSrc){
		if(!folderSrc.startsWith("/"))
			folderSrc="/"+folderSrc;
		try {
			File f=new File(ServletActionContext.getServletContext().getRealPath(folderSrc));			
			if(f.exists() && f.isDirectory()){						
				if(f.listFiles().length>0)	
					return 1;
				else
					{
						f.delete();						
					}
						
			}
			} catch (Exception e) {
					return 0;
				}	
		return 1;
	}
	
}
