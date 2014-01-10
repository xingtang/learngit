package com.digital.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**  
 * �圖片工具類，圖片水印，文字水印，縮放，補白等��L�A��r��L�A�Y��A�ɥյ�
 * @author 
 */  
public class ImageUtil {
	/**�圖片格式 JPG*/
	private static final String PICTRUE_FORMATE_JPG = "jpg";
	
	ImageUtil(){}
	
	/**
	 * 添加圖片水印
	 * @param targetImg  目標圖片路徑
     * @param waterImg 水印圖片路徑
     * @param x  水印圖片距離目標圖片左側的偏移量， 如果 x <=-2, 則在正中間 ;  x =-1, 則在最右邊
     * @param y 水印圖片距離目標圖片左側的偏移量， 如果 y <=-2 , 則在正中間;  y =-1, 則在最下邊
     * @param alpha 透明度（0.0--1.0 ， 0.0為完成透明，1.0為完全不透明）
	 */
	public final  void pressImage(String targetImg, String waterImg, int x, int y, float alpha){
		try {
			File file = new File(targetImg);
			Image image = ImageIO.read(file);
			int width = image.getWidth(null);
			int height = image.getHeight(null);
			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);   
			
			Graphics2D g = bufferedImage.createGraphics(); 
			g.drawImage(image, 0, 0, width, height, null);
			
			File waterImgFile = new File(waterImg);
			Image waterImage = null;
			try {
				waterImage = ImageIO.read(waterImgFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			int width_l = waterImage.getWidth(null);
			int height_l = waterImage.getHeight(null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));   
			
			int widthDiff = width - width_l;
			int heightDiff = height - height_l;
			if(x == -1){
				x = widthDiff;
			}else if(x <= -2){
				x = widthDiff / 2;
			}else if(x > widthDiff){
				x = widthDiff;
			}
			if(y == -1){
				y = heightDiff;
			}if(y <= -2){
				y = heightDiff / 2;
			}else if(y > heightDiff){
				y = heightDiff;
			}
			g.drawImage(waterImage, x, y ,width_l, height_l,null);
			//��L��󵲧�
			g.dispose();
			
			ImageIO.write(bufferedImage, PICTRUE_FORMATE_JPG, file);   
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**  
	 * 添加文字水印
     * @param targetImg  目標圖片路徑
     * @param pressText 水印文字
     * @param fontName 字體名稱
     * @param fontStyle 字體樣式 如：粗體和斜體（Font.BOLD|Font.ITALIC）
     * @param fontSize 字體大小
     * @param color 字體顏色
     * @param x 水印圖片距離目標圖片左側的偏移量， 如果 x < 0, 則在正中間�h�b������  
     * @param y 水印圖片距離目標圖片左側的偏移量， 如果 x < 0, 則在正中間 �h�b������ 
     * @param alpha 透明度（0.0--1.0 ， 0.0為完成透明，1.0為完全不透明）
     */  
	public  void pressText(String targetImg, String pressText, String fontName, int fontStyle, int fontSize, Color color, int x, int y, float alpha) {
		try {
			File file = new File(targetImg);
			
			Image image = ImageIO.read(file);
			int width = image.getWidth(null);
			int height = image.getHeight(null);
			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);   
			
			Graphics2D g = bufferedImage.createGraphics();
			g.drawImage(image, 0, 0, width, height, null);
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.setColor(color);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));   

			int width_l = fontSize * getLength(pressText);
			int height_l = fontSize;
			int widthDiff = width - width_l;
			int heightDiff = height - height_l;
			if(x < 0){   
                x = widthDiff / 2;   
            }else if(x > widthDiff){   
                x = widthDiff;   
            }   
            if(y < 0){   
                y = heightDiff / 2;   
            }else if(y > heightDiff){   
                y = heightDiff;   
            }   
               
            g.drawString(pressText, x, y + height_l);   
            g.dispose();   
            ImageIO.write(bufferedImage, PICTRUE_FORMATE_JPG, file);   


			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}  
	 /**  
	  * 獲取字符長度，一個漢字作為一個字符，一個英文字母作為0.5個字符
     * @param text  
     * @return 字符長度，如:text="中國" 返回2; text="test" 返回2 ; text="中國ABC" 返回4
     * 
     */  
    public  int getLength(String text) {   
        int textLength = text.length();   
        int length = textLength;   
        for (int i = 0; i < textLength; i++) {   
            if (String.valueOf(text.charAt(i)).getBytes().length > 1) {   
                length++;   
            }   
        }   
        return (length % 2 == 0) ? length / 2 : length / 2 + 1;   
    }   

    /**  
     *  圖片縮放  
     * @param filePath 圖片路徑
     * @param height 高度
     * @param width 寬度
     * @param bb比例不對是是否需要補白
     */  
    public  void resize(String filePath, int height, int width, boolean bb) {   
        try {   
            double ratio = 0; //�Y����       
            File f = new File(filePath);      
            BufferedImage bi = ImageIO.read(f);      
            Image itemp = bi.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);      
            //�p����
            if ((bi.getHeight() > height) || (bi.getWidth() > width)) {      
                if (bi.getHeight() > bi.getWidth()) {      
                    ratio = new Double(height) / bi.getHeight();      
                } else {      
                    ratio = new Double(width) / bi.getWidth();      
                }      
                AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);      
                itemp = op.filter(bi, null);      
            }      
            if (bb) {      
                BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);      
                Graphics2D g = image.createGraphics();      
                g.setColor(Color.white);      
                g.fillRect(0, 0, width, height);      
                if (width == itemp.getWidth(null))      
                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);      
                else     
                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);      
                g.dispose();      
                itemp = image;      
            }   
            ImageIO.write((BufferedImage) itemp, "jpg", f);      
        } catch (IOException e) {   
            e.printStackTrace();   
        }   
    }   


//    public static void main(String[] args) throws IOException {   
//        pressImage("C:\\pic\\jpg", "C:\\pic\\test.gif", 5000, 5000, 0f);   
//        pressText("C:\\pic\\jpg", "��J���L", "���^", Font.BOLD|Font.ITALIC, 20, Color.BLACK, 0, 0, 8f);   
//        resize("C:\\pic\\4.jpg", 1000, 500, true);   
//    }   



	/*
	public static void watermark(String imageUrl) {
		URL url = null;
		try {
			url = new URL(imageUrl);
			// url = new URL("http://bbs.ntsky.com/images/logo.gif");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		Image image = null;
		try {
			image = ImageIO.read(url);
			
			int tempWidth = image.getWidth(null);
			int tempHeight = image.getHeight(null);
			int x = (int) (tempWidth * 0.1);
			int y = (int) (tempHeight * 0.9);
			BufferedImage bufferedImage = drawImageFrame(image, tempWidth, tempHeight);
			
			Graphics graphics = bufferedImage.getGraphics();
			//font color
			graphics.setColor(new Color(Integer.parseInt("000000",16)));
			
			//font
			Font mFont = new Font("���^",Font.PLAIN,12);//�q�{�r��
			graphics.setFont(mFont);
			
			//��X��r
			graphics.drawString("www.mmcarcar", 10, 20);
			FileOutputStream out = new FileOutputStream("c:/test.jpg");
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(bufferedImage);
			out.close();
		} catch (IOException ioE) {
			ioE.printStackTrace();
			// TODO: handle exception
		} catch (ImageFormatException e){
			e.printStackTrace();
		}

	}
	*/

}
