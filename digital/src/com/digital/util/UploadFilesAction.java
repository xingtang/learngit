package com.digital.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;


public class UploadFilesAction  {

	private File attachment;

	private String attachmentContentType;

	private String attachmentFileName;

	public String execute() {
		String base = "d:/LargeUpload/";
		File file = new File(base);
		if (!file.exists()){
			file.mkdirs();
		}
			
		String tempdir=base+attachmentFileName;
		File f=new File(tempdir);
		try {
			FileOutputStream out = new FileOutputStream(f);
			InputStream in = new FileInputStream(attachment);
			byte[] buffer = new byte[1024];
			while (in.read(buffer) != -1){
				out.write(buffer);
			}				
			out.close();
			in.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "success";
	}

	public File getAttachment() {
		return attachment;
	}

	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}

	public String getAttachmentContentType() {
		return attachmentContentType;
	}

	public void setAttachmentContentType(String attachmentContentType) {
		this.attachmentContentType = attachmentContentType;
	}

	public String getAttachmentFileName() {
		return attachmentFileName;
	}

	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}

}
