package com.digital.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 圖片縮略
 * @author dong
 *
 */
public class ResizeImg
{
 private String srcFile;
 private String destFile;
 private int w;
 private int h;
 private Image img;
 public ResizeImg(){}
 /*
  * 構造方法
  * FILE：要縮略的文件對象。
  * resizeFileName : 縮略后的文件名稱
  */
 public ResizeImg(File file,String resizeFileName) throws IOException{
	 this.srcFile = file.getName();
	 this.destFile = resizeFileName;
	 img = javax.imageio.ImageIO.read(file);
		 w = img.getWidth(null);
		 h = img.getHeight(null);
	 
	 
 }
 /*
  * 構造方法
  * FILE：要縮略的文件路徑。
  * resizeFileName : 縮略后的文件名稱
  */
 public ResizeImg(String filename,String resizefilename) throws IOException
 {
  File _file = new File(filename); 
     this.srcFile = _file.getName();
     this.destFile = resizefilename;
     img = javax.imageio.ImageIO.read(_file);
     w = img.getWidth(null); 
     h = img.getHeight(null); 
 }
public boolean cutImg(int x, int y)
   {
   float factor = getCutFactor(x,y);
   if((int)(factor)>1)
    factor =1;
   int new_x = (int)(w*factor);
   int new_y = (int)(h*factor);
   if(w<x)
    new_x = w;
   if(h<y)
    new_y = h;
   
   int start_x = (x-new_x)/2;
   int start_y = (y-new_y)/2;
   
   BufferedImage _image = new BufferedImage(x,y,BufferedImage.TYPE_INT_RGB);
    Graphics g = _image.getGraphics();
    g.setColor(Color.WHITE);
    g.fillRect(0,0,x,y);
  
    if(g.drawImage(img,start_x,start_y,new_x,new_y,null))
    {
     FileOutputStream out;
     try {
     out = new FileOutputStream(destFile);
     JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
     try {
      encoder.encode(_image);
      out.close();
     } catch (ImageFormatException e) {
    
      e.printStackTrace();
      return false;
     } catch (IOException e) {
     
      e.printStackTrace();
      return false;
     } 
     
    } catch (FileNotFoundException e) {
    
     e.printStackTrace();
     return false;
    } 
    }
    else
    {
     return false;
    }
   return true;
     
   }



 //注意，這個方法只供 相冊封面壓縮使用
public boolean resizeImportImage(int x,int y)
{
 float factor = getFactor(x,y);
 int new_x = (int)(w*factor);
 int new_y = (int)(h*factor);
 if(w<x)
  new_x = w;
 if(h<y)
  new_y = h;
 int start_x = (x-new_x)/2;
 int start_y = (y-new_y)/2;
 
 
 BufferedImage _image = new BufferedImage(x,y,BufferedImage.TYPE_INT_RGB);
  Graphics g = _image.getGraphics();
  g.setColor(Color.WHITE);
  g.fillRect(0,0,x,y);
 
//  if(g.drawImage(img,start_x,start_y,new_x,new_y,null))
//  {
  g.drawImage(img.getScaledInstance(187, 125, Image.SCALE_SMOOTH), 0, 0, null);
   FileOutputStream out;
   try {
   out = new FileOutputStream(destFile);
   JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
   JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(_image);
   jep.setQuality(1, false);
   encoder.setJPEGEncodeParam(jep);
   try {
    encoder.encode(_image);
    out.close();
    g.dispose();
   } catch (ImageFormatException e) {
  
    e.printStackTrace();
    return false;
   } catch (IOException e) {
    
    e.printStackTrace();
    return false;
   } 
   
  } catch (FileNotFoundException e) {

   e.printStackTrace();
   return false;
  } 
//  }
//  else
//  {
//   return false;
//  }
 return true;
}



 public boolean resize(int x,int y)
  {
   float factor = getFactor(x,y);
   int new_x = (int)(w*factor);
   int new_y = (int)(h*factor);
   if(w<x)
    new_x = w;
   if(h<y)
    new_y = h;
   int start_x = (x-new_x)/2;
   int start_y = (y-new_y)/2;
   
   
   BufferedImage _image = new BufferedImage(x,y,BufferedImage.TYPE_INT_RGB);
    Graphics g = _image.getGraphics();
    g.setColor(Color.WHITE);
    g.fillRect(0,0,x,y);
   
//    if(g.drawImage(img,start_x,start_y,new_x,new_y,null))
//    {
    g.drawImage(img.getScaledInstance(new_x, new_y, Image.SCALE_SMOOTH), 0, 0, null);
     FileOutputStream out;
     try {
     out = new FileOutputStream(destFile);
     JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
     JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(_image);
     jep.setQuality(1, false);
     encoder.setJPEGEncodeParam(jep);
     try {
      encoder.encode(_image);
      out.close();
      g.dispose();
     } catch (ImageFormatException e) {
    
      e.printStackTrace();
      return false;
     } catch (IOException e) {
      
      e.printStackTrace();
      return false;
     } 
     
    } catch (FileNotFoundException e) {
  
     e.printStackTrace();
     return false;
    } 
//    }
//    else
//    {
//     return false;
//    }
   return true;
  }
 
 public float getFactor(int x,int y)
  {
   if((w/x)>(h/y))
    return (float)x/w;
   else
    return (float)y/h;
  }
  public float getCutFactor(int x,int y)
  {
   if((w/x)>(h/y))
    return (float)y/h;
   else
    return (float)x/w;
  }
  
   public String getDestFile() {
     return destFile;
   }
  
   public int getSrcWidth() {
     return w;
   }
   
   public int getSrcHeight() {
     return h;
   }
//   public static void main(String[] args)
//   {
//    ResizeImg ri = null;
//  try {
//   ri = new ResizeImg("RR1_T7DeWDLqELA0.jpg","RR1_T7DeWDLqELA0_thumbnail.jpg");
//   ri.resize(500,500);
//  } catch (IOException e) {
//  
//   e.printStackTrace();
//  }
//    
//   }
   
   
	    /**   
	     * 实现图像的等比缩放,按長寬比例小的縮   
	     * @param source   
	     * @param targetW   
	     * @param targetH   
	     * @return   
	     */   
	    private static BufferedImage small_scale_resize(BufferedImage source, int targetW,    
	            int targetH) {    
	        // targetW，targetH分别表示目标长和宽    
	        int type = source.getType();    
	        BufferedImage target = null;    
	        double sx = (double) targetW / source.getWidth();    
	        double sy = (double) targetH / source.getHeight();    
	        // 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放    
	        // 则将下面的if else语句注释即可    
	        if (sx < sy) {    
	            sx = sy;    
	            targetW = (int) (sx * source.getWidth());    
	        } else {    
	            sy = sx;    
	            targetH = (int) (sy * source.getHeight());    
	        }   
	        
	        if (type == BufferedImage.TYPE_CUSTOM) { // handmade    
	            ColorModel cm = source.getColorModel();    
	            WritableRaster raster = cm.createCompatibleWritableRaster(targetW,    
	                    targetH);    
	            boolean alphaPremultiplied = cm.isAlphaPremultiplied();    
	            target = new BufferedImage(cm, raster, alphaPremultiplied, null);    
	        } else   
	            target = new BufferedImage(targetW, targetH, type);    
	        Graphics2D g = target.createGraphics();    
	        // smoother than exlax:    
	        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,    
	                RenderingHints.VALUE_INTERPOLATION_BICUBIC);    
	        g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));    
	        g.dispose();    
	        return target;    
	    }   
	    
	    /**   
	     * 实现图像的等比缩放,按長寬比例大的縮,填充白底    
	     * @param source   
	     * @param targetW   
	     * @param targetH  
	     * @param isPadding 是否填充底色
	     * @return   
	     */   
	    private static boolean big_scale_resize(BufferedImage source, int targetW,    
	            int targetH,File targetFile,boolean isPadding) {    
	        // targetW，targetH分别表示目标长和宽    
	        int type = source.getType();    
	        BufferedImage target = null;    
	        double sx = (double) source.getWidth() / targetW;    
	        double sy = (double) source.getHeight() / targetH;  
	        int scale_w=targetW;
	        int scale_h=targetH;
	        
	        // 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放    
	        // 则将下面的if else语句注释即可    
	        if (sx < sy) {    
	            sx = sy;    
	            scale_w = (int) (source.getWidth()/sx);    
	        } else {    
	            sy = sx;    
	            scale_h = (int) (source.getHeight()/sy);    
	        }   
	        
	        if (type == BufferedImage.TYPE_CUSTOM) { // handmade    
	            ColorModel cm = source.getColorModel();    
	            WritableRaster raster = cm.createCompatibleWritableRaster(scale_w,    
	            		scale_h);    
	            boolean alphaPremultiplied = cm.isAlphaPremultiplied();    
	            target = new BufferedImage(cm, raster, alphaPremultiplied, null);    
	        } else   
	            target = new BufferedImage(scale_w, scale_h, type);    
	        Graphics2D g = target.createGraphics(); 

	        // smoother than exlax:    
	        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,    
	                RenderingHints.VALUE_INTERPOLATION_BICUBIC);    
	        g.drawRenderedImage(source, AffineTransform.getScaleInstance(1/sx, 1/sy));
	        
	        if(!isPadding){
	        	try {
					ImageIO.write(target, targetFile.getName().substring(targetFile.getName().lastIndexOf('.') + 1), targetFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }else{
		        BufferedImage newImage=new BufferedImage(targetW,targetH,BufferedImage.TYPE_INT_RGB);   
		        Graphics2D ggg =newImage.createGraphics();
		        ggg.fillRect(0, 0, targetW,targetH);
		        int start_x=(targetW-scale_w)/2;
		        int start_y=(targetH-scale_h)/2;
		        
		        if(ggg.drawImage(target,start_x,start_y,scale_w,scale_h,null))
		        {
		        	try {
		        		ImageIO.write(newImage, targetFile.getName().substring(targetFile.getName().lastIndexOf('.') + 1), targetFile);
		        	} catch (IOException e) {
						e.printStackTrace();
					}
		        	
//			         FileOutputStream out;
//			         try {
//			        	  out = new FileOutputStream(targetFile);
//				          JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//						  encoder.encode(newImage);
//				          out.close();        
//			         } catch (ImageFormatException e) {
//			        	e.printStackTrace();
//			        	return false;
//			         }catch (FileNotFoundException e) {
//						e.printStackTrace();
//						return false;
//					 }catch (IOException e) {
//						e.printStackTrace();
//						return false;
//					 }
			         
		         }
		        ggg.dispose();
	        }
	        
	        g.dispose();  
	        return false;
	        //return target;    
	    }  
	   
	    /**   
	     * 实现图像的等比缩放和缩放后的截取，只產生一張縮圖  
	     * @param inFilePath 要截取文件的路径   
	     * @param outFilePath 截取后输出的路径   
	     * @param width 要截取宽度   
	     * @param hight 要截取的高度   
	     * @param proportion   
	     * @throws Exception   
	     */   
	    public static void saveImageAsJpg(String inFilePath, String outFilePath,    
	            int width, int hight, boolean proportion)throws Exception {    
	         File file = new File(inFilePath);    
	         InputStream in = new FileInputStream(file);    
	         File saveFile = new File(outFilePath);    
	         String fileName = saveFile.getName(); //文件名   
             String formatName = fileName.substring(fileName.lastIndexOf('.') + 1);//後綴名
             
	        BufferedImage srcImage = ImageIO.read(in);   
	        //BufferedImage srcImage2=ImageIO.read(in);
	        if (width > 0 || hight > 0) {    
	            // 原图的大小    
	            int sw = srcImage.getWidth();    
	            int sh = srcImage.getHeight();    
	            // 如果原图像的大小小于要缩放的图像大小，直接将要缩放的图像复制过去    
	            if (sw > width && sh > hight) {    
	                srcImage = small_scale_resize(srcImage, width, hight);    
	            }else if((sw>width && (sw/sh>width/hight))||(sh>hight && (sh/sw>hight/width))){
	            	big_scale_resize(srcImage, width, hight,saveFile,true);//不填充底色
	            	in.close();
	            	return;
	            } else {    
	            	//big_scale_resize(srcImage, width, hight,saveFile);
	                ImageIO.write(srcImage, formatName, saveFile);
	                in.close();
	                return;    
	            }    
	        }    
	        // 缩放后的图像的宽和高    
	        int w = srcImage.getWidth();    
	        int h = srcImage.getHeight();    
	        // 如果缩放后的图像和要求的图像宽度一样，就对缩放的图像的高度进行截取    
	        if (w == width) {  
	        	if(h>hight*1.2){
	        		big_scale_resize(srcImage, width, hight,saveFile,true);//false表示不填充底色
	        		//srcImage=big_scale_resize(srcImage, width, hight);
	                //ImageIO.write(srcImage, formatName, saveFile);
	        		in.close();
	                return;
	        	}else{
		            // 计算X轴坐标    
		            int x = 0;    
		            int y = h / 2 - hight / 2;    
		            saveSubImage(srcImage, new Rectangle(x, y, width, hight), saveFile);
	        	}
	        }    
	        // 否则如果是缩放后的图像的高度和要求的图像高度一样，就对缩放后的图像的宽度进行截取    
	        else if (h == hight) {  
	        	if(w>width*1.2){
	        		big_scale_resize(srcImage, width, hight,saveFile,true);//false表示不填充底色
	        		//srcImage=big_scale_resize(srcImage, width, hight);
	                //ImageIO.write(srcImage, formatName, saveFile);
	        		in.close();
	                return;
	        	}else{
		            // 计算X轴坐标    
		            int x = w / 2 - width / 2;    
		            int y = 0;    
		            saveSubImage(srcImage, new Rectangle(x, y, width, hight), saveFile);
	        	}
	        }    
	        in.close();    
	    }    
	    
	    /**   
	     * 实现缩放后的截图   
	     * @param image 缩放后的图像   
	     * @param subImageBounds 要截取的子图的范围   
	     * @param subImageFile 要保存的文件   
	     * @throws IOException   
	     */   
	    private static void saveSubImage(BufferedImage image,    
	            Rectangle subImageBounds, File subImageFile) throws IOException {    
	        if (subImageBounds.x < 0 || subImageBounds.y < 0   
	                || subImageBounds.width - subImageBounds.x > image.getWidth()    
	                || subImageBounds.height - subImageBounds.y > image.getHeight()) {    
	        //    System.out.println("Bad   subimage   bounds");    
	            return;    
	        }    
	        BufferedImage subImage = image.getSubimage(subImageBounds.x,subImageBounds.y, subImageBounds.width, subImageBounds.height);    
	        String fileName = subImageFile.getName();    
	        String formatName = fileName.substring(fileName.lastIndexOf('.') + 1);    
	        ImageIO.write(subImage, formatName, subImageFile);    
	    }    
	    
	    /**
	     * 將原圖縮放
	     * @param inFilePath 原圖路徑
	     * @param outFilePath 縮圖路徑
	     * @param width_limit 寬度限制(如：圖片的為750 資訊600)
	     */
	    public void resize_only(String inFilePath, String outFilePath,int width_limit)throws Exception{
	    	File file = new File(inFilePath);  
	    	//InputStream in = new FileInputStream(file); 
	        BufferedImage srcImage = ImageIO.read(file);
	        // 原图的大小    
            int sw = srcImage.getWidth();    
            int sh = srcImage.getHeight();//file.length();
            //如果寬度大於750，進行縮放(太大把頁面樣式撐開)，750,750*image.getHeight()/image.getWidth()
            if(sw>width_limit){
		        File saveFile = new File(outFilePath);    
		        String fileName = saveFile.getName(); //文件名   
	            String formatName = fileName.substring(fileName.lastIndexOf('.') + 1);//後綴名
	            
            	srcImage = small_scale_resize(srcImage, width_limit, width_limit*srcImage.getHeight()/srcImage.getWidth());
            	ImageIO.write(srcImage, formatName, saveFile);
            }

            //in.close();
	    }
}

