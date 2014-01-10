package com.digital.util;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 * 
 * 圖片上傳  ljl  2012-10-30
 * 實現本地圖片上傳到伺服器
 * @author dong
 *
 */
public class UploadImage {
	private static final Logger log = Logger.getLogger(UploadImage.class);
	private static final long serialVersionUID = 572146812454l ;
    private static final int BUFFER_SIZE = 16 * 1024 ;
 
	public String saveFile(File myFile, String fileSrc, String fileName){
		String afterFileName = getExtention(fileName);
		afterFileName = getFileName(afterFileName);
		fileSrc = this.generateFolder(fileSrc);
		File file = new File(ServletActionContext.getServletContext().getRealPath(fileSrc)+"/"+afterFileName);
		try {
			copyFile(myFile,file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileSrc+"/"+afterFileName;
	}
	//得到後綴名�o����W
	public static String getExtention(String fileName){
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}
	/**
	 * 重命名文件名，防止上傳圖片在伺服器上出現相同名稱錯誤
	 * 以yyyyMM+格林時間毫秒數+原文件名
	 * 例:file: a.jpg,    sysFile:200906123445566a.jpg
	 * @param fileName 原文件名
	 * @return 重新生成的文件名
	 */
	public static String getFileName(String fileName){
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMM");
		String sysFileName = date.getTime()+fileName;
		sysFileName = dateFormat.format(date)+sysFileName;
		return sysFileName;
	}
	/**
	 * 將上傳至伺服器的src 對象（系統臨時文件對象），copy 到指定的dst 對象
	 * @param src 原文件的file對象
	 * @param dst 生成后的file對象
	 */
	public static void copy(File src, File dst)  {
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
	 * 複製文件
	 * @param oldFile
	 * @param newFile
	 * @throws IOException
	 */
	public static void copyFile(File oldFile, File newFile) throws IOException {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(oldFile);
			fos = new FileOutputStream(newFile);
			byte[] buffer = new byte[1024];
			int ins;
			while((ins = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, ins);
			}
		} catch(IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			if(fis != null)
				fis.close();
			if(fos != null)
				fos.close();
		}
	}

	/**
	 * 根據年月自動生成文件夾,如果不存在,創建之後再返回
	 * @param fileSrc 上傳文件夾路徑
	 * @return 返回上傳圖片在伺服器的相對目錄(文件夾)
	 */
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
	 /**
     * 圖片上傳
     * 實現本地圖片上傳到伺服器
     * 由於系統需求，try{}中的resizeImge 為圖片比例圖功能對象，用於生成縮略圖，
     * @param myFile  上傳圖片的FILE類型對象
     * @param fileSrc  圖片上傳到伺服器的相對路徑
     * @param fileName  圖片上傳到伺服器的名稱
     * @return 返回上傳圖片在伺服器的相對位置
     */
	public String saveImaqe(File myFile, String fileSrc, String fileName){
		String imageFileName = getExtention(fileName);
		imageFileName = getFileName(imageFileName);
		fileSrc = this.generateFolder(fileSrc);
		File imageFile = new File(ServletActionContext.getServletContext().getRealPath(fileSrc)+"/"+imageFileName);
		copy(myFile,imageFile);
		try {
			ResizeImg resizeImg = new ResizeImg(myFile,ServletActionContext.getServletContext().getRealPath(fileSrc)+"/thumb_"+imageFileName);
			resizeImg.resize(100,145);
		//	ImageUtil imageUtil = new ImageUtil();
		//   imageUtil.pressImage(ServletActionContext.getServletContext().getRealPath(fileSrc)+"/"+imageFileName, ServletActionContext.getServletContext().getRealPath(fileSrc)+"/thumb_"+imageFileName, 1, 1, 0.5F);
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
		
		return fileSrc+"/"+imageFileName;
	}
	
	//2012-11-18 ljl //生成原圖和縮放圖
	
	public List<String> saveImaqeZoon(File myFile, String fileSrc, String fileName,int width,int height){
		List listurl = new ArrayList();
		String imageFileName = getExtention(fileName);
		imageFileName = getFileName(imageFileName);
		fileSrc = this.generateFolder(fileSrc);
		File imageFile = new File(ServletActionContext.getServletContext().getRealPath(fileSrc)+"/"+imageFileName);
		copy(myFile,imageFile);
		try {
			//縮放圖生成
			ResizeImg resizeImg = new ResizeImg(myFile,ServletActionContext.getServletContext().getRealPath(fileSrc)+"/thumb_"+imageFileName);
			resizeImg.resizeImportImage(width,height);//按長寬壓縮，
			//resizeImg.cutImg(width,height);//剪切之后縮放。
//			try {
				//按比例壓縮，在背景中居中。
//				resizeImg.saveImageAsJpg(ServletActionContext.getServletContext().getRealPath(fileSrc)+"/"+imageFileName, ServletActionContext.getServletContext().getRealPath(fileSrc)+"/thumb_"+imageFileName, 120, 90, true);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
		String url1=fileSrc+"/"+imageFileName;
		String url2=fileSrc+"/thumb_"+imageFileName;
		listurl.add(url1);
		listurl.add(url2);
		return listurl;
	}
	
//2013 03-19 //生成原圖和縮放圖
	
	public List<String> saveImaqeZoonH(File myFile, String fileSrc, String fileName,int width,int height){
		List listurl = new ArrayList();
		String imageFileName = getExtention(fileName);
		imageFileName = getFileName(imageFileName);
		fileSrc = this.generateFolder(fileSrc);
		File imageFile = new File(ServletActionContext.getServletContext().getRealPath(fileSrc)+"/"+imageFileName);
		copy(myFile,imageFile);
		try {
			//縮放圖生成
			ResizeImg resizeImg = new ResizeImg(myFile,ServletActionContext.getServletContext().getRealPath(fileSrc)+"/thumb_"+imageFileName);
			//resizeImg.resizeImportImage(width,height);//按長寬壓縮，
			resizeImg.cutImg(width,height);//剪切之后縮放。
//			try {
				//按比例壓縮，在背景中居中。
//				resizeImg.saveImageAsJpg(ServletActionContext.getServletContext().getRealPath(fileSrc)+"/"+imageFileName, ServletActionContext.getServletContext().getRealPath(fileSrc)+"/thumb_"+imageFileName, 120, 90, true);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
		String url1=fileSrc+"/"+imageFileName;
		String url2=fileSrc+"/thumb_"+imageFileName;
		listurl.add(url1);
		listurl.add(url2);
		return listurl;
	}
}
