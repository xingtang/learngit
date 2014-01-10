package com.digital.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

	/**
	 * zyh
	 * 比較時間,返回相差天數
	 * @param date
	 * @return
	 */
	public static long dateDiff(Date date) {
		// 按照传入的格式生成一个simpledateformate对象
		if(date == null){
			return  0;
		}
		long nd = 1000*24*60*60;
		// 一天的毫秒数
		long nh = 1000*60*60;
		// 一小时的毫秒数
		long nm = 1000*60;
		// 一分钟的毫秒数
		long ns = 1000;
		// 一秒钟的毫秒数
		long diff; 
		long day = 0;
		try { 
			// 获得两个时间的毫秒时间差异
			diff = new Date().getTime() - date.getTime();
			day = diff/nd;
			// 计算差多少天
			//long hour = diff%nd/nh;
			// 计算差多少小时
			//long min = diff%nd%nh/nm;
			// 计算差多少分钟
			//long sec = diff%nd%nh%nm/ns;
			// 计算差多少秒
			// 输出结果
			//System.out.println("时间相差："+day+"天"+hour+"小时"+min+"分钟"+sec+"秒。");
			
		 } catch (Exception e) { 
			 e.printStackTrace(); 
		 } 
		 return day;
	} 	
	/**
	 * 解析時間轉換成中文格式的字符串
	 * @param date 傳入的時間
	 * @return
	 */
	public String parseDateToStringCn(Date date){
		return date.getYear()+1900+"年"+date.getMonth()+"月"+date.getDay()+"日 "
		+date.getHours()+"時"+date.getMinutes()+"分";
	}
	/**
	 * 解析字符串為日期 yyyy-MM-dd HH:mm:ss
	 * @param yyyyMMdd
	 * @return Date
	 */
	public  Date parseStringToDate(String yyyyMMdd){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=null;
		try {
			date=sdf.parse(yyyyMMdd.toString());
		} catch (ParseException e) {
			date=new Date();
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 當前時間
	 * 返回一個格式化String類型的日期
	 * @param strFormat 日期格式："yyyyMM","MM-DD-yyyy" 等等
	 * @return
	 */
	public String getDateFormat(String strFormat){
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat(strFormat);
		String strDateFormat = dateFormat.format(date);
		return strDateFormat;
	}
	/**
	 * 當前時間
	 * 返回一個格式化String類型的日期 格式為："yyyy-MM-DD hh:mm:ss"
	 * @return yyyy-MM-DD hh:mm:ss
	 */
	public String getDateFormat(){
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD hh:mm:ss");
		return dateFormat.format(date);
	}
	/**
	 * 返回一個格式化String類型的日期
	 * @param strFormat 日期格式："yyyyMM","MM-DD-yyyy" 等等
	 * @param date 要轉換的日期時間
	 * @return
	 */
	public String getDateFormat(String strFormat,Date date){
		if(date == null)return "";
		 DateFormat dateFormat = new SimpleDateFormat(strFormat);
		String strDateFormat = dateFormat.format(date);
		return strDateFormat;
	}
	/**
     * 返回一個格式化String類型的日期 格式為："yyyy-MM-DD hh:mm:ss"
     * @param date 要轉換的日期時間
	 * @return yyyy-MM-DD hh:mm:ss
	 */
	public String getDateFormat(Date date){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return dateFormat.format(date);
	}
	/**
	 * 返回格林時間毫秒秒數的后9位，用于生成編號
	 * Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this Date object. 
	 * @return
	 */
	public  String getTimeStr(){
		Date date =new Date();
		String strTime = date.getTime()+"";
		return strTime.substring(4);
	}
	/**
	 * 返回一個List<Integer>的集合，內容為1900年到當前年份的時間集合，用於頁面年份下拉框
	 * @return
	 */
	public List<Integer> getDateList(){
		List <Integer> dateList=new ArrayList <Integer>();  
		int i = 0;
		Calendar calendar = Calendar.getInstance();
		
		for(i=calendar.get(calendar.YEAR);i>=1900;i--){
			dateList.add(i);
		}
		return dateList;
	}
	
	/**
	 * 返回一個List<Integer>的集合，內容為startYear到當前年份的時間集合，用於頁面年份下拉框
	 * @param startYear 開始的年份
	 * @return
	 */
	public List<Integer> getDateList(int startYear){
		List <Integer> dateList=new ArrayList <Integer>();  
		int i = 0;
		Calendar calendar = Calendar.getInstance();
		
		for(i=calendar.get(calendar.YEAR);i>=startYear;i--){
			dateList.add(i);
		}
		return dateList;
	}
	/**
	 返回一個List<Integer>的集合，內容為startYear到endYear的時間集合，用於頁面年份下拉框
	 * @param startYear 開始的年份
	 * @param endYear 結束的年份
	 * @return
	 */
	public List<Integer> getDateList(int startYear, int endYear){
		List <Integer> dateList=new ArrayList <Integer>();  
		int i = 0;
		for(i=endYear;i>=startYear;i--){
			dateList.add(i);
		}
		return dateList;
	}
	
    /**
     * 轉換字符字符串為日期類型
     * @param format 日期的格式
     * @param dateStr 日期的字符串
     * @return 
     */
	public Date parseString2Date(String format,String dateStr){
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format); 
		try{
		date = sdf.parse(dateStr);
		}catch(Exception ex){}
		return date;
	}
	
	

	
	/**
	 * @param format "yyyy-MM-dd HH:mm:ss"
	 * @param date1 "yyyy-MM-dd HH:mm:ss"
	 * @param date2 "yyyy-MM-dd HH:mm:ss"
	 */
	public int compareDate(String date1 ,String date2 ,String format){
		int sign=0;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date d1;
		Date d2;
		Date now = new Date();
		try {
			d1 = sdf.parse(date1.toString());
			d2 = sdf.parse(date2.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return sign=2;
		}
		if(d1.compareTo(now)==-1){//输出的值是“-1”，说明 d1 在 now 之前。
			return sign=-1;
		}
		if(d2.compareTo(now)==1){//输出的值是“1”，说明 d2 在now之后。
			return sign=1;
		}
		return sign;
	}
}
