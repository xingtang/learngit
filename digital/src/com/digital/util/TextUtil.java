package com.digital.util;

import java.io.File;
import java.io.StringReader;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import com.kukugame.util.ipsearch.IPSeeker;

public class TextUtil {
	/**
	 * velocity 文本顯示。 顯示制定長度
	 * 
	 * @param length
	 * @param str
	 * @return
	 */
	public String subString(int length, String str) {
		if (str == null)
			return "";
		StringBuffer resultStr = new StringBuffer();
		int strLength = 0;
		str = str.replaceAll("<br>", "&nbsp;");
		for (int i = 0; i < str.length(); i++) {
			if (isChinese(str.charAt(i))) {
				strLength = strLength + 2;
			} else {
				if (Character.isUpperCase(str.charAt(i))) {
					strLength = strLength + 2;
				} else {
					strLength++;
				}

			}
			resultStr.append(str.charAt(i));
			if (strLength > (2 * length)) {
				return resultStr.toString() + "...";
			}
			// if (String.valueOf(str.charAt(i)).getBytes().length > 1) {
			// strLength = strLength + 2;
			// }else{
			// if(Character.isUpperCase(str.charAt(i))){
			// strLength = strLength + 2;
			// }else{
			// strLength ++;
			// }
			//            	  
			// }
			// resultStr.append(str.charAt(i));
			// if(strLength > (2 * length)){
			// return resultStr.toString()+"...";
			// }
		}
		return str;
	}

	public float divided(int a, int b) {
		if (a == 0 || b == 0)
			return 0;
		else {
			return ((float) a / b) * 100;
		}

	}

	public String subStringWithoutOmitted(int length, String str) {
		if (str == null)
			return "";
		StringBuffer resultStr = new StringBuffer();
		int strLength = 0;
		for (int i = 0; i < str.length(); i++) {
			if (isChinese(str.charAt(i))) {
				strLength = strLength + 2;
			} else {
				if (Character.isUpperCase(str.charAt(i))) {
					strLength = strLength + 2;
				} else {
					strLength++;
				}

			}
			resultStr.append(str.charAt(i));
			if (strLength > (2 * length)) {
				return resultStr.toString();
			}
		}
		return str;
	}

	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	/**
	 * velocity 文本顯示 數字顯示，在輸入數的基礎上+1 num ： 遞增的數
	 */
	public int incremental(int num) {
		return ++num;
	}

	/**
	 * velocity 文本顯示 數字顯示，在輸入數的基礎上+1 num ： 遞增的數 total : 最大的值，不能超過他
	 */
	public int incremental(int num, int total) {
		if (num >= total) {
			return total;
		}
		return ++num;
	}

	/**
	 * velocity 文本顯示 數字顯示，在輸入數的基礎上-1 num : 遞減的數
	 */
	public int decreasing(int num) {
		return --num;
	}

	/**
	 * velocity 文本顯示 數字顯示，在輸入數的基礎上-1 num : 遞減的數 least : 最小的書，不能小於它
	 */
	public int decreasing(int num, int least) {
		if (num <= least) {
			return least;
		}
		return --num;
	}

	/**
	 * velocity 文本顯示 數字顯示，得出兩個數值的除值。 divisor 除以 denominator
	 * 
	 * @param divisor
	 *            除數
	 * @param denominator
	 *            被除數
	 * @return
	 */
	public float division(int divisor, int denominator) {
		// System.out.println("divisor : "+divisor+" denominator:"+denominator);
		if (denominator == 0)
			return (float) 0.0;
		if (divisor == 0)
			return (float) 0.0;
		// System.out.println(" pass divisor : "+divisor+"
		// denominator:"+denominator);
		float floatDivisor = divisor;
		float floatdenominator = denominator;
		float result = floatDivisor / floatdenominator * 100;
		BigDecimal b = new BigDecimal(result);
		result = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
		// b.setScale(2, BigDecimal.ROUND_HALF_UP)
		return result;
	}

	/**
	 * 對訣比例數。 velocity 文本顯示 數字顯示，得出 票數1 除以 （票數1 + 票數2）
	 * 
	 * @param stat1
	 *            票數1
	 * @param stat2
	 *            票數2
	 * @param width
	 *            比例總長度
	 * @return
	 */
	public float vsProportional(int stat1, int stat2, int width) {
		if ((stat1 + stat2) == 0)
			return width / 2;// 如果兩數相加為0則代表沒有人投票。兩邊比例為一半
		float floatStat1 = stat1;
		// float floatStat2 = stat2;

		float result = floatStat1 / (stat1 + stat2) * width;
		if (result > (width - 100))
			result = (width - 100);
		if (result < 100)
			return 100;// 如果某一方比例長度小於100，防止頁面比例不好看，默認將此比例長度設為100
		BigDecimal b = new BigDecimal(result);
		result = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
		return result;
	}

	/**
	 * 根據用戶帳號（user_account） 返回商家用戶的模板路徑
	 * 
	 */
	public String showSubdomains(String userAccount) {
		if (userAccount == null || "".equals(userAccount))
			return "#";
		// return "http://"+userAccount+".mmcarcar.com";
		return "/company/" + userAccount;
	}

	/**
	 * 顯示圖片路徑 檢測圖片安路徑是否正確， 開頭是否多了"/" 或少了"/" 如果此路徑真的沒有圖片，防止頁面上出面圖片錯誤顯現。
	 * 把此圖片路徑改變為系統默認圖片 ui/www/images/no_car_logo.gif
	 * 
	 * @param Str
	 * @return
	 */
	public String showPhoto(String photoStr) {
		if (photoStr == null) {
			return photoStr = "/ui/www-po/html/images/demand_10.png";
		}

		// 如果是引用網絡的圖片，則判斷是否有http://，有就返回。
		// 簡單的判斷，如果需要準確的判定，有待改寫判斷 by dong 2010-04-26
		if (photoStr.indexOf("http://") != -1) {
			return photoStr;
		}

		File sys_file = new File(this.getClass().getResource("/").getPath());
		String sys_file_src = sys_file.getPath();
		// 得到當前工程路徑C:\Documents%20and%20Settings\Administrator\workspaces\.metadata\.plugins\com.genuitec.eclipse.easie.tomcat.myeclipse\tomcat\webapps\mcar\WEB-INF\classes
		// window下路徑用‘/’��
		sys_file_src = sys_file_src.replace("\\", "/");
		sys_file_src = sys_file_src.replace("%20", " ");

		if ("".equals(photoStr.trim())) {
			photoStr = "/ui/www-po/html/images/demand_10.png";
		} else {
			if ("//".equals(photoStr.substring(0, 2))) {
				photoStr = photoStr.substring(1);
			} else if (!"/".equals(photoStr.substring(0, 1))) {
				photoStr = "/" + photoStr;
			}
			File photo = new File(sys_file_src + "/../../" + photoStr);
			if (!photo.isFile()) {
				photoStr = "/ui/www-po/html/images/demand_10.png";
			}
		}
		return photoStr;
		// if()
	}

	public String showCustomPhoto(String photoStr, String defaultImgSrc) {
		if (photoStr == null) {
			return defaultImgSrc;
		}

		// 如果是引用網絡的圖片，則判斷是否有http://，有就返回。
		// 簡單的判斷，如果需要準確的判定，有待改寫判斷 by dong 2010-04-26
		if (photoStr.indexOf("http://") != -1) {
			return photoStr;
		}

		File sys_file = new File(this.getClass().getResource("/").getPath());
		String sys_file_src = sys_file.getPath();
		// 得到當前工程路徑C:\Documents%20and%20Settings\Administrator\workspaces\.metadata\.plugins\com.genuitec.eclipse.easie.tomcat.myeclipse\tomcat\webapps\mcar\WEB-INF\classes
		// window下路徑用‘/’��
		sys_file_src = sys_file_src.replace("\\", "/");
		sys_file_src = sys_file_src.replace("%20", " ");

		if ("".equals(photoStr.trim())) {
			return defaultImgSrc;
		} else {
			if ("//".equals(photoStr.substring(0, 2))) {
				photoStr = photoStr.substring(1);
			} else if (!"/".equals(photoStr.substring(0, 1))) {
				photoStr = "/" + photoStr;
			}
			File photo = new File(sys_file_src + "/../../" + photoStr);
			if (!photo.isFile()) {
				photoStr = "/upload_images/logo.gif";
			}
		}
		return photoStr;
		// if()
	}

	public String showPrizePhoto(String photoStr) {
		if (photoStr == null) {
			return photoStr = "/ui/www/scbaom/images/pic_gift_default.jpg";
		}

		// 如果是引用網絡的圖片，則判斷是否有http://，有就返回。
		// 簡單的判斷，如果需要準確的判定，有待改寫判斷 by dong 2010-04-26
		if (photoStr.indexOf("http://") != -1) {
			return photoStr;
		}

		File sys_file = new File(this.getClass().getResource("/").getPath());
		String sys_file_src = sys_file.getPath();
		// 得到當前工程路徑C:\Documents%20and%20Settings\Administrator\workspaces\.metadata\.plugins\com.genuitec.eclipse.easie.tomcat.myeclipse\tomcat\webapps\mcar\WEB-INF\classes
		// window下路徑用‘/’��
		sys_file_src = sys_file_src.replace("\\", "/");
		sys_file_src = sys_file_src.replace("%20", " ");

		if ("".equals(photoStr.trim())) {
			photoStr = "/upload_images/logo.gif";
		} else {
			if ("//".equals(photoStr.substring(0, 2))) {
				photoStr = photoStr.substring(1);
			} else if (!"/".equals(photoStr.substring(0, 1))) {
				photoStr = "/" + photoStr;
			}
			File photo = new File(sys_file_src + "/../../" + photoStr);
			if (!photo.isFile()) {
				photoStr = "/upload_images/logo.gif";
			}
		}
		return photoStr;
		// if()
	}

	/**
	 * 用戶LOGO 顯示圖片路徑 檢測圖片安路徑是否正確， 開頭是否多了"/" 或少了"/" 如果此路徑真的沒有圖片，防止頁面上出面圖片錯誤顯現。
	 * 把此圖片路徑改變為系統默認圖片 ui/www/images/no_car_logo.gif
	 * 
	 * @param Str
	 * @return
	 */
	public String showUserPhoto(String photoStr) {
		if (photoStr == null) {
			return photoStr = "/upload_images/user_logo/logo128.jpg";
		}
		File sys_file = new File(this.getClass().getResource("/").getPath());
		String sys_file_src = sys_file.getPath();
		// 得到當前工程路徑C:\Documents%20and%20Settings\Administrator\workspaces\.metadata\.plugins\com.genuitec.eclipse.easie.tomcat.myeclipse\tomcat\webapps\mcar\WEB-INF\classes
		// window下路徑用‘/’��
		sys_file_src = sys_file_src.replace("\\", "/");
		sys_file_src = sys_file_src.replace("%20", " ");

		if ("".equals(photoStr.trim())) {
			photoStr = "/upload_images/user_logo/logo128.jpg";
		} else {
			if ("//".equals(photoStr.substring(0, 2))) {
				photoStr = photoStr.substring(1);
			} else if (!"/".equals(photoStr.substring(0, 1))) {
				photoStr = "/" + photoStr;
			}
			File photo = new File(sys_file_src + "/../../" + photoStr);
			if (!photo.isFile()) {
				photoStr = "/upload_images/user_logo/logo128.jpg";
			}
		}
		return photoStr;
		// if()
	}

	/**
	 * 用戶LOGO 顯示圖片縮略路徑 檢測縮略圖片安路徑是否正確， 開頭是否多了"/" 或少了"/"
	 * 如果此路徑真的沒有圖片，防止頁面上出面圖片錯誤顯現。 把此圖片路徑改變為系統默認圖片 ui/www/images/no_car_logo.gif
	 * 
	 * @param Str
	 * @return
	 */
	public String showUserReducePhoto(String photoStr) {
		if (photoStr == null) {
			return photoStr = "/upload_images/user_logo/logo50.jpg";
		}
		File sys_file = new File(this.getClass().getResource("/").getPath());
		String sys_file_src = sys_file.getPath();
		// 得到當前工程路徑C:\Documents%20and%20Settings\Administrator\workspaces\.metadata\.plugins\com.genuitec.eclipse.easie.tomcat.myeclipse\tomcat\webapps\mcar\WEB-INF\classes
		// window下路徑用‘/’��
		sys_file_src = sys_file_src.replace("\\", "/");
		sys_file_src = sys_file_src.replace("%20", " ");

		if ("".equals(photoStr.trim())) {
			photoStr = "/upload_images/user_logo/logo50.jpg";
		} else {
			if ("//".equals(photoStr.substring(0, 2))) {
				photoStr = getSmallPhotoName(photoStr.substring(1));
			} else if (!"/".equals(photoStr.substring(0, 1))) {
				photoStr = getSmallPhotoName("/" + photoStr);

			} else {
				photoStr = getSmallPhotoName(photoStr);

			}
			File photo = new File(sys_file_src + "/../../" + photoStr);
			if (!photo.isFile()) {
				photoStr = "/upload_images/user_logo/logo50.jpg";
			}
		}
		return photoStr;
		// if()
	}

	/**
	 * 社群LOGO 顯示圖片路徑 檢測圖片安路徑是否正確， 開頭是否多了"/" 或少了"/" 如果此路徑真的沒有圖片，防止頁面上出面圖片錯誤顯現。
	 * 把此圖片路徑改變為系統默認圖片
	 * 
	 * @param Str
	 * @return
	 */
	public String showAssociationPhoto(String photoStr) {
		if (photoStr == null) {
			return photoStr = "/upload_images/association_logo/logo128.jpg";
		}
		File sys_file = new File(this.getClass().getResource("/").getPath());
		String sys_file_src = sys_file.getPath();
		// 得到當前工程路徑C:\Documents%20and%20Settings\Administrator\workspaces\.metadata\.plugins\com.genuitec.eclipse.easie.tomcat.myeclipse\tomcat\webapps\mcar\WEB-INF\classes
		// window下路徑用‘/’��
		sys_file_src = sys_file_src.replace("\\", "/");
		sys_file_src = sys_file_src.replace("%20", " ");

		if ("".equals(photoStr.trim())) {
			photoStr = "/upload_images/association_logo/logo128.jpg";
		} else {
			if ("//".equals(photoStr.substring(0, 2))) {
				photoStr = photoStr.substring(1);
			} else if (!"/".equals(photoStr.substring(0, 1))) {
				photoStr = "/" + photoStr;
			}
			File photo = new File(sys_file_src + "/../../" + photoStr);
			if (!photo.isFile()) {
				photoStr = "/upload_images/association_logo/logo128.jpg";
			}
		}
		return photoStr;
		// if()
	}

	/**
	 * 社群small_LOGO 50X50 顯示圖片路徑 檢測圖片安路徑是否正確， 開頭是否多了"/" 或少了"/"
	 * 如果此路徑真的沒有圖片，防止頁面上出面圖片錯誤顯現。 把此圖片路徑改變為系統默認圖片
	 * 
	 * @param Str
	 * @return
	 */
	public String showAssociationSmallPhoto(String photoStr) {
		if (photoStr == null) {
			return photoStr = "/upload_images/association_logo/logo50.jpg";
		}
		File sys_file = new File(this.getClass().getResource("/").getPath());
		String sys_file_src = sys_file.getPath();
		// 得到當前工程路徑C:\Documents%20and%20Settings\Administrator\workspaces\.metadata\.plugins\com.genuitec.eclipse.easie.tomcat.myeclipse\tomcat\webapps\mcar\WEB-INF\classes
		// window下路徑用‘/’��
		sys_file_src = sys_file_src.replace("\\", "/");
		sys_file_src = sys_file_src.replace("%20", " ");

		if ("".equals(photoStr.trim())) {
			photoStr = "/upload_images/association_logo/logo50.jpg";
		} else {
			if ("//".equals(photoStr.substring(0, 2))) {
				photoStr = photoStr.substring(1);
			} else if (!"/".equals(photoStr.substring(0, 1))) {
				photoStr = "/" + photoStr;
			}
			File photo = new File(sys_file_src + "/../../" + photoStr);
			if (!photo.isFile()) {
				photoStr = "/upload_images/association_logo/logo50.jpg";
			}
		}
		return photoStr;
		// if()
	}

	/**
	 * 顯示圖片縮略路徑 檢測縮略圖片安路徑是否正確， 開頭是否多了"/" 或少了"/" 如果此路徑真的沒有圖片，防止頁面上出面圖片錯誤顯現。
	 * 把此圖片路徑改變為系統默認圖片 ui/www/images/no_car_logo.gif
	 * 
	 * @param Str
	 * @return
	 */
	public String showReducePhoto(String photoStr) {
		if (photoStr == null) {
			return photoStr = "/upload_images/logo.gif";
		}
		// 如果是引用網絡的圖片，則判斷是否有http://，有就返回。
		// 簡單的判斷，如果需要準確的判定，有待改寫判斷 by dong 2010-04-26
		if (photoStr.indexOf("http://") != -1) {
			return photoStr;
		}
		File sys_file = new File(this.getClass().getResource("/").getPath());
		String sys_file_src = sys_file.getPath();
		// 得到當前工程路徑C:\Documents%20and%20Settings\Administrator\workspaces\.metadata\.plugins\com.genuitec.eclipse.easie.tomcat.myeclipse\tomcat\webapps\mcar\WEB-INF\classes
		// window下路徑用‘/’��
		sys_file_src = sys_file_src.replace("\\", "/");
		sys_file_src = sys_file_src.replace("%20", " ");

		if ("".equals(photoStr.trim())) {
			photoStr = "/upload_images/logo.gif";
		} else {
			if ("//".equals(photoStr.substring(0, 2))) {
				photoStr = getSmallPhotoName(photoStr.substring(1));
			} else if (!"/".equals(photoStr.substring(0, 1))) {
				photoStr = getSmallPhotoName("/" + photoStr);

			} else {
				photoStr = getSmallPhotoName(photoStr);
			}
			File photo = new File(sys_file_src + "/../../" + photoStr);
			if (!photo.isFile()) {
				photoStr = "/upload_images/logo.gif";
			}
		}
		return photoStr;
		// if()
	}

	/**
	 * 社群圖片 顯示圖片縮略路徑 檢測縮略圖片安路徑是否正確， 開頭是否多了"/" 或少了"/" 如果此路徑真的沒有圖片，防止頁面上出面圖片錯誤顯現。
	 * 把此圖片路徑改變為系統默認圖片 ui/www/images/no_car_logo.gif
	 * 
	 * @param Str
	 * @return
	 */
	public String showAssociationReducePhoto(String photoStr) {
		if (photoStr == null) {
			return photoStr = "/upload_images/association_logo/logo50.jpg";
		}
		File sys_file = new File(this.getClass().getResource("/").getPath());
		String sys_file_src = sys_file.getPath();
		// 得到當前工程路徑C:\Documents%20and%20Settings\Administrator\workspaces\.metadata\.plugins\com.genuitec.eclipse.easie.tomcat.myeclipse\tomcat\webapps\mcar\WEB-INF\classes
		// window下路徑用‘/’��
		sys_file_src = sys_file_src.replace("\\", "/");
		sys_file_src = sys_file_src.replace("%20", " ");

		if ("".equals(photoStr.trim())) {
			photoStr = "/upload_images/association_logo/logo50.jpg";
		} else {
			if ("//".equals(photoStr.substring(0, 2))) {
				photoStr = getSmallPhotoName(photoStr.substring(1));
			} else if (!"/".equals(photoStr.substring(0, 1))) {
				photoStr = getSmallPhotoName("/" + photoStr);

			} else {
				photoStr = getSmallPhotoName(photoStr);

			}
			File photo = new File(sys_file_src + "/../../" + photoStr);
			if (!photo.isFile()) {
				photoStr = "/upload_images/association_logo/logo50.jpg";
			}
		}
		return photoStr;
		// if()
	}

	/**
	 * 返回縮略圖的路徑名稱 在原圖路徑名稱上加首碼“thumb_” 例：photoName : xxx/xxx.jpg; 結果 :
	 * xxx/thumb_xxx.jpg;
	 * 
	 * @param photoName
	 *            原圖的路徑名稱
	 * @return 返回縮略圖的路徑名稱
	 */
	public String getSmallPhotoName(String photoName) {
		int pos = photoName.lastIndexOf("/");
		String small_logo_prefix = photoName.substring(0, pos + 1);
		String small_logo_suffix = photoName.substring(pos + 1);
		return small_logo_prefix + "thumb_" + small_logo_suffix;

	}

	/**
	 * 根據座標（定義區域點的座標）判斷出定義熱點形狀 ","出現兩次為：circle圓形 ","出現三次為：rect矩形 其他為多邊行 poly
	 * 
	 * @param coords
	 * @return
	 */
	public String getShapeNameByCoords(String coords) {
		if (coords == null || "".equals(coords))
			return "poly";
		int count = 0;
		int start = coords.indexOf(",");

		while (start != -1) {
			start = coords.indexOf(",", start + 1);
			count++;
		}
		if (count == 2)
			return "circle";
		if (count == 3)
			return "rect";

		return "poly";
	}

	/**
	 * 根據用戶類型來判斷是否有讀取的權限 通過& 運算來判斷 這里傳入的用戶類型ID值都是已經運算過的。 原本用戶類型是1，2.....9 現在變成 :
	 * 2 的（ＩＤ值－１）次方 1:1, 2:2, 3:4, 4:8, 5:16........
	 * 
	 * @param user_type
	 *            用戶類型ID 運算過的值
	 * @param user_type
	 *            權限ID
	 * @return
	 */
	public String checkIsGroup(int user_type, long sGroupid) {
		long result = sGroupid & user_type;

		return result != 0 ? "true" : "false";
	}

	/**
	 * 根據用戶類型來判斷是否有讀取的權限 通過& 運算來判斷 這里傳入的用戶類型ID值是原值。 再這裡在進行預算 原本用戶類型是1，2.....9
	 * 現在變成 : 2 的（ＩＤ值－１）次方 1:1, 2:2, 3:4, 4:8, 5:16........
	 * 
	 * @param user_type
	 *            用戶類型ID 原值
	 * @param user_type
	 *            權限ID
	 * @return
	 */
	public String checkIsGroupByUserType(int user_type, long sGroupid) {
		user_type = 1 << (user_type - 1);
		long result = sGroupid & user_type;

		return result != 0 ? "true" : "false";
	}

	/**
	 * 返回日期的不同顯示，用於論壇帖子顯示的時間更加人性化
	 * 
	 * @param d
	 *            傳入的日期，用於和當前日期比較
	 * @return
	 */
	public String dateDiff(Date d) {
		if (d == null)
			return "null";
		DateFormat dateFormat = new SimpleDateFormat("MM月dd日");
		Date now = new Date();
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(now);
		c2.setTime(d);
		long ms = c1.getTimeInMillis() - c2.getTimeInMillis();
		long m = ms / (1000 * 60);// 獲取分鐘
		long s = ms / 1000;// 秒

		if (s < 60)
			return s + " 秒前";
		else if (m >= 1 && m < 60)
			return m + " 分鐘前";
		else if (m >= 60 && m < 60 * 24)
			return (m / 60) + " 小時前";
		else if (m < 60 * 24 * 2)
			return "昨天";
		else if (m < 60 * 24 * 3)
			return "前天";
		else
			return "" + dateFormat.format(d);

	}

	public String dateDiff2(Date d) {
		if (d == null)
			return "";
		DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
		Date now = new Date();
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(now);
		c2.setTime(d);
		long ms = c1.getTimeInMillis() - c2.getTimeInMillis();
		long m = ms / (1000 * 60);// 獲取分鐘
		long s = ms / 1000;// 秒

		if (s < 60)
			return s + " 秒前";
		else if (m >= 1 && m < 60)
			return m + " 分鐘前";
		else if (m >= 60 && m < 60 * 24)
			return (m / 60) + " 小時前";
		else if (m < 60 * 24 * 2)
			return "昨天";
		else if (m < 60 * 24 * 3)
			return "前天";
		else if (m < 60 * 24 * 7)
			return m / (60 * 24) + "天前";
		else
			return "" + dateFormat.format(d);
	}

	/**
	 * 用于判斷投票是否過期,和系統日期比較
	 * 
	 * @param d
	 *            ：傳入投票有效日期
	 * @return true:表示過期 flase：相反
	 */
	public boolean isExpired(Date d) {
		if (d == null)
			return false;
		Date now = new Date();
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(now);
		c2.setTime(d);
		long ms = c1.getTimeInMillis() - c2.getTimeInMillis();

		if (ms > 0)
			return true;
		else
			return false;
	}

	public static void main(String arg[]) {
		// String f = "[img=2,200]sds[/img]";
		// String f="<img
		// src=\"http://localhost:8081/ui/admin/js/xheditor/zh-tw/xheditor_emot/default/shy.gif\"
		// alt=\"Shy\" />asdfa<strong>sdf</strong>a";
		String f = " 111<img src=\"http://localhost:8081/ui/admin/js/xheditor/zh-tw/xheditor_emot/default/mad.gif\" />2222 <img src=\"http://localhost:8081/ui/admin/js/xheditor/zh-tw/xheditor_emot/default/shy.gif\" alt=\"Shy\" />aaa<span style=\"color:#f10b00;\">bb</span>ccc<img src=\"http://localhost:8081/ui/admin/js/xheditor/zh-tw/xheditor_emot/default/handshake.gif\" alt=\"Handshake\" />dd<u>ee</u>ff";
		// String f="<img
		// src='http://localhost:8081/ui/admin/js/xheditor/zh-tw/xheditor_emot/default/shy.gif'
		// alt='Shy' />asdfa<strong>sdf</strong>a";
		System.out.print(removalHtmlTagsWithoutImg(f, 0));

	}

	/**
	 * 去除文本中的HTML語法，剩下純文本字段信息 html change to text param htmlText 內容 param
	 * textSize 文本長度， 如果為0，則所有文字輸出
	 */
	public String removalHtmlTags(String htmlText, int textSize) {

		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";
		// String regEx_html = "<[^>]&&[^img(\\s+[^>]+?)\\/?]+>";
		String regEx_html = "<[^>]+>";

		Pattern p_style = Pattern
				.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Pattern p_script = Pattern.compile(regEx_script,
				Pattern.CASE_INSENSITIVE);
		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);

		if (htmlText == null) {
			return htmlText;
		}

		// stripping script tags whether the tag contains "\n" or "\r" or not.
		Matcher m_script = p_script.matcher(htmlText);
		String htmlStr = m_script.replaceAll("");

		// stripping style tags whether the tag contains "\n" or "\r" or not.
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll("");

		// stripping html tags but continue to have the "\n" and "\r" in right
		// place.
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll("");

		if (textSize == 0) {
			return htmlStr;
		} else {
			if (htmlStr.length() < textSize) {
				return htmlStr.substring(0, htmlStr.length());
			} else {
				return htmlStr.substring(0, textSize);
			}

		}

	}

	/**
	 * 去除文本中的HTML語法(除了IMG)，剩下純文本字段信息 html change to text param htmlText 內容 param
	 * textSize 文本長度， 如果為0，則所有文字輸出
	 */
	public static String removalHtmlTagsWithoutImg(String htmlText, int textSize) {
		// System.out.println(htmlText);
		Matcher m = null;
		m = Pattern.compile("alt=\"(.[^\\[]{1,20})\"\\/?",
				Pattern.DOTALL | Pattern.CASE_INSENSITIVE | Pattern.MULTILINE)
				.matcher(htmlText);

		htmlText = m.replaceAll("");
		// System.out.println(htmlText);

		m = Pattern.compile("<img src=\"(.[^\\[]{1,140})\"?\" />",
				Pattern.DOTALL | Pattern.CASE_INSENSITIVE | Pattern.MULTILINE)
				.matcher(htmlText);

		htmlText = m.replaceAll("[img]$1[/img]");

		// System.out.println(htmlText);

		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";
		String regEx_html = "<[^>]+>";

		Pattern p_style = Pattern
				.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Pattern p_script = Pattern.compile(regEx_script,
				Pattern.CASE_INSENSITIVE);
		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);

		if (htmlText == null) {
			return htmlText;
		}

		// stripping script tags whether the tag contains "\n" or "\r" or not.
		Matcher m_script = p_script.matcher(htmlText);
		String htmlStr = m_script.replaceAll("");

		// stripping style tags whether the tag contains "\n" or "\r" or not.
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll("");

		// stripping html tags but continue to have the "\n" and "\r" in right
		// place.
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll("");

		UbbUtil ubbUtil = new UbbUtil();
		htmlStr = ubbUtil.ubbDecode(htmlStr);

		if (textSize == 0) {
			return htmlStr;
		} else {
			if (htmlStr.length() < textSize) {
				return htmlStr.substring(0, htmlStr.length());
			} else {
				return htmlStr.substring(0, textSize);
			}

		}

	}

	/**
	 * 根據月日得到所對應的星座
	 */
	public String showConstellationByBirtyday(int month, int day) {
		String star = "";
		if (month == 1 && day >= 20 || month == 2 && day <= 18) {
			star = "水瓶座";
		}

		if (month == 2 && day >= 19 || month == 3 && day <= 20) {
			star = "雙魚座";
		}

		if (month == 3 && day >= 21 || month == 4 && day <= 19) {
			star = "白羊座";
		}

		if (month == 4 && day >= 20 || month == 5 && day <= 20) {
			star = "金牛座";
		}

		if (month == 5 && day >= 21 || month == 6 && day <= 21) {
			star = "雙子座";
		}

		if (month == 6 && day >= 22 || month == 7 && day <= 22) {
			star = "巨蟹座";
		}

		if (month == 7 && day >= 23 || month == 8 && day <= 22) {
			star = "獅子座";
		}

		if (month == 8 && day >= 23 || month == 9 && day <= 22) {
			star = "處女座";
		}

		if (month == 9 && day >= 23 || month == 10 && day <= 22) {
			star = "天秤座";
		}

		if (month == 10 && day >= 23 || month == 11 && day <= 21) {
			star = "天蝎座";
		}

		if (month == 11 && day >= 22 || month == 12 && day <= 21) {
			star = "射手座";
		}

		if (month == 12 && day >= 22 || month == 1 && day <= 19) {
			star = "摩羯座";
		}

		return star;

	}

	public String showAddressByIP(String ipStr) {
		IPSeeker ip = IPSeeker.getInstance();
		if (ipStr == null || "".equals(ipStr))
			return "";
		return ip.getAddress(ipStr);
	}

	/**
	 * 是否顯示。 showPublic 2 :任何人可看 返回TRUE showPublic 0 :任何人不可看 返回FALSE showPublic
	 * 1 :社友可看，判斷是否社友關係，isSameAssociation是否為真可看，反之不能看。
	 * 
	 * @param isSameAssociation
	 *            是否社友關係
	 * @param showPublic
	 *            當前欄目公開程度
	 * @return
	 */
	public String isPublicShow(String isSameAssociation, String showPublic) {
		// System.out.println("isSameAssociation : "+isSameAssociation+"
		// showPublic : "+showPublic);
		String result = "false";
		if (showPublic == null || "".equals(showPublic)) {
			return result;
		}
		if ("2".equals(showPublic))
			return "true";
		if ("0".equals(showPublic))
			return "false";
		if ("1".equals(showPublic) && "true".equals(isSameAssociation))
			return "true";

		return result;
	}

	/**
	 * 根據性別顯示他（男），她（女），TA（空值）
	 * 
	 * @param userSex
	 * @return
	 */
	public String showTa(String userSex) {
		if (userSex == null || "".equals(userSex))
			return "Ta";
		if ("1".equals(userSex))
			return "她";
		if ("0".equals(userSex))
			return "他";
		return "Ta";
	}

	/**
	 * 檢查是否結束， 開始日期大於結束日期 結束返回true; 沒有結束返回false;
	 * 
	 * @param beginDate
	 *            開始日期
	 * @param endDate
	 *            結束日期
	 * @return
	 */
	public String checkIsEnd(Date beginDate, Date endDate) {
		String result = "false";
		if (beginDate == null || endDate == null)
			return result;
		if (beginDate.after(endDate)) {
			result = "true";
		}
		return result;
	}

	/**
	 * 把html轉為文本
	 * 
	 * @param pText
	 * @return
	 */
	public String encodeHTMLAsText(String pText) {
		StringTokenizer tokenizer = new StringTokenizer(pText, "&<>\"", true);
		int tokenCount = tokenizer.countTokens();

		/* no encoding's needed */
		if (tokenCount == 1)
			return pText;
		/*
		 * text.length + (tokenCount * 6) gives buffer large enough so no
		 * addition memory would be needed and no costly copy operations would
		 * occur
		 */
		StringBuffer buffer = new StringBuffer(pText.length() + tokenCount * 6);
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			if (token.length() == 1) {
				switch (token.charAt(0)) {
				case '&':
					buffer.append("&amp;");
					break;
				case '<':
					buffer.append("&lt;");
					break;
				case '>':
					buffer.append("&gt;");
					break;
				case '"':
					buffer.append("&quot;");
					break;
				default:
					buffer.append(token);
				}
			} else {
				buffer.append(token);
			}
		}
		return buffer.toString();
	}

	/**
	 * 顯示視屏文件的圖片
	 * 
	 * @param content
	 *            (格式:視屏文件#video-space#視屏圖片)
	 * @return
	 */
	public String showVideoPhoto(String content) {
		if (content == null || "".equals(content)) {
			return "";
		}
		return content.split("#video-space#")[1];
	}

	/**
	 * 顯示視屏文件的路徑
	 * 
	 * @param content
	 *            (格式:視屏文件#video-space#視屏圖片)
	 * @return
	 */
	public String showVideoSrc(String content) {
		if (content == null || "".equals(content)) {
			return "";
		}
		return content.split("#video-space#")[0];
	}

	/**
	 * 截取帶html標記的字符串
	 * 
	 * @param param
	 *            要截取的字符串
	 * @param length
	 *            截取的長度
	 * @return 截取后的字符串
	 */
	public String subStringHTML(String param, int length) {
		if (param == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int n = 0;
		char temp;
		boolean isCode = false;
		boolean isHTML = false;
		for (int i = 0; i < param.length(); i++) {
			temp = param.charAt(i);
			if (temp == '<') {
				isCode = true;
			} else if (temp == '&') {
				isHTML = true;
			} else if (temp == '>' && isCode) {
				n = n - 1;
				isCode = false;
			} else if (temp == ';' && isHTML) {
				isHTML = false;
			}

			if (!isCode && !isHTML) {
				n = n + 1;
				if ((temp + "").getBytes().length > 1) {
					n = n + 1;
				}
			}

			sb.append(temp);
			if (n >= length) {
				break;
			}
		}

		String temp_result = sb.toString().replaceAll("(>)[^<>]*(<?)", "$1$2");
		temp_result = temp_result
				.replaceAll(
						"</?(AREA|BASE|BASEFONT|BODY|BR|COL|COLGROUP|DD|DT|FRAME|HEAD|HR|HTML|IMG|INPUT|ISINDEX|LI|LINK|META|OPTION|P|PARAM|TBODY|TD|TFOOT|TH|THEAD|TR|area|base|basefont|body|br|col|colgroup|dd|dt|frame|head|hr|html|img|input|isindex|li|link|meta|option|p|param|tbody|td|tfoot|th|thead|tr)[^<>]*/?>",
						"");
		temp_result = temp_result.replaceAll("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>",
				"$2");
		Pattern p = Pattern.compile("<([a-zA-Z]+)[^<>]*>");
		Matcher m = p.matcher(temp_result);
		List<String> endHTML = new ArrayList<String>();
		while (m.find()) {
			endHTML.add(m.group(1));
		}
		for (int i = endHTML.size() - 1; i >= 0; i--) {
			sb.append("</");
			sb.append(endHTML.get(i));
			sb.append(">");
		}
		sb.append("...");
		return sb.toString();
	}

	
	public String setKeywordColor(String kw, String title) {

		if (kw == null || kw.equals("") || title == null || title.equals(""))
			return title == null ? "" : title;

		String str = "";
		String temp = "";

		int a = title.indexOf(kw);
		while (a > 0) {
			// System.out.println("a:"+a);
			temp = temp + title.substring(0, a);
			String keyword = "<span style='color:red'>"
					+ title.substring(a, a + kw.length()) + "</span>";
			// System.out.println("temp:"+temp);
			temp = temp + keyword;
			// System.out.println("temp:"+temp);
			title = title.substring(a + kw.length());
			a = title.indexOf(kw);
		}
		str = temp + title;
		// System.out.println("str:"+str);
		return str;
	}

	/**
	 * 將多個標籤加上鏈接
	 * 
	 * @param kw
	 *            傳入的關鍵字
	 * @return
	 */
	public List<String> fenjieKeyword(String kw) {
		List<String> keywordList = new ArrayList<String>();
		if (kw == null || kw.equals("")) {
			return keywordList;
		}

		// StringBuffer sb=new StringBuffer();
		if (kw.indexOf(" ") == -1) {// 只有一個關鍵字
			// sb.append("<em><a href='#'>"+kw+"</a></em>&nbsp;&nbsp;");
			// sb.append("<em>"+kw+"</em>");
			keywordList.add(kw);
		} else {// 多個關鍵字
			String[] temp = kw.split(" ");
			for (String s : temp) {
				// sb.append("<em><a href='#'>"+s+"</a></em>&nbsp;&nbsp;");
				// sb.append("<em>"+s+"</em>");
				keywordList.add(s);
			}
		}

		return keywordList;
	}

	/**
	 * 切詞
	 * 
	 * @return
	 */
	private static final Analyzer iKAnalyzer = new CJKAnalyzer();

	public String splitKeyword(String keyword, String tremName, String add) {
		if (keyword == null || "".equals(keyword)) {
			return "";
		}
		tremName = tremName == "" ? "" : tremName + ":";
		StringBuilder sb = new StringBuilder();
		StringReader reader = new StringReader(keyword);
		TokenStream tks = iKAnalyzer.tokenStream("", reader);
		boolean isFirst = true;
		Token tk = new Token();
		try {
			while (tks.next(tk) != null) {
				if (isFirst) {
					sb.append(tremName + tk.term() + " ");
				} else {
					sb.append(add + " " + tremName + tk.term() + " ");
				}
				isFirst = false;

			}
		} catch (Exception ex) {
		}
		return sb.toString().trim();
	}

	/**
	 * 定制文本長度
	 * 
	 * @param length
	 * @param str
	 * @return
	 */
	public String blockString(int length, String str) {
		String oldStr = str.trim();
		String newStr = "";
		if (length < 1 || null == str || "".equals(str))
			return newStr;
		if (str != null && !str.equals(""))
			newStr = oldStr.length() > length ? oldStr.substring(0, length)
					+ "..." : oldStr;
		return newStr;
	}

	/**
	 * 定制文本長度，不帶省略號
	 * 
	 * @param length
	 * @param str
	 * @return
	 */
	public String blockStringWithout(int length, String str) {
		String oldStr = str.trim();
		String newStr = "";
		if (length < 1 || null == str || "".equals(str))
			return newStr;

		newStr = oldStr.length() > length ? oldStr.substring(0, length)
				: oldStr;
		return newStr;
	}

	/**
	 * @param format
	 *            "yyyy-MM-dd HH:mm:ss"
	 * @param date1
	 *            "yyyy-MM-dd HH:mm:ss"開始時間
	 * @param date2
	 *            "yyyy-MM-dd HH:mm:ss"結束時間
	 */
	public int compareDate(String date1, String date2, String format) {
		int sign = 0;
		if (!date1.substring(0, 4).equals("1900")
				&& !date2.substring(0, 4).equals("1900")) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date d1 = new Date();
			Date d2 = new Date();
			Date now = new Date();
			try {
				d1 = sdf.parse(date1.toString());
				d2 = sdf.parse(date2.toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				sign = 2;
			}

			if (d1.compareTo(now) == 1) {// 输出的值是“-1”，说明 d1 在 now 之后。
				sign = -1;
			}
			if (d2.compareTo(now) == -1) {// 输出的值是“1”，说明 d2 在now之前。
				sign = 1;
			}
		}

		return sign;
	}

	/**
	 * 求兩個數的差值
	 * 
	 * @param count
	 * @param received
	 * @return
	 */
	public int compareCount(int count, int received) {
		return count - received;
	}

	//替換字符串中的特殊字符
	public static String htmlEscape(String strData) {
		if (strData == null) {
			return "";
		}
		strData = replaceString(strData, "&", "&amp;");
		strData = replaceString(strData, "<", "&lt;");
		strData = replaceString(strData, ">", "&gt;");
		strData = replaceString(strData, "'", "&apos;");
		strData = replaceString(strData, "\"", "&quot;");
		return strData;
	}

	public static String replaceString(String strData, String regex,
			String replacement) {
		if (strData == null) {
			return null;
		}
		int index;
		index = strData.indexOf(regex);
		String strNew = "";
		if (index >= 0) {
			while (index >= 0) {
				strNew += strData.substring(0, index) + replacement;
				strData = strData.substring(index + regex.length());
				index = strData.indexOf(regex);
			}
			strNew += strData;
			return strNew;
		}
		return strData;
	}
	
	//替換字符串中的"<br>"
	
}
