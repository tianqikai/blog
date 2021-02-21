package com.tqk.blog.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.tqk.blog.bean.DateObject;
import com.tqk.blog.enums.ResultEnum;
import com.tqk.blog.execption.BlogException;
import org.apache.commons.lang.StringUtils;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DateUtil {
	public static void main(String[] args) {
		System.out.println(getDate());
		String time="20190425123033".substring(8, 14);
		System.out.println(time);
//		System.out.println(nDaysBetweenTwoDate("20200523","20200525","yyyyMMdd"));
//		System.out.println(getTimeDiff("20200427123033"));
//		System.out.println(getDatestr("20190425"));
//		System.out.println(add1Datestr("20190430"));
//		System.out.println(add1Date(new Date()));
		System.out.println(getDateShort("yyyy-MM-dd HH:mm:ss"));
//		System.out.println(DateToStr(new Date()));
//		System.out.println("aaa:"+getDateShort("2020-05-25","yyyy-MM-dd","yyyyMMdd"));
		System.out.println(DateToStr(new Date(),"yyyy-MM-dd"));
		System.out.println(getDateShort("2020-06-07","yyyy-MM-dd","yyyy.MM.dd"));
		System.out.println(nDaysBetweenTwoDate(DateToStr(new Date(),"yyyy-MM-dd"),"2020-07-01","yyyy-MM-dd"));
	}
	/**
	 * 计算传入时间距离当前时间多久
	 *
	 * @param date
	 * @return
	 */
	public static DateObject getTimeDiff(String date) {
		log.info("输入日期："+date);
	    if (StringUtils.isEmpty(date)) {
	    	log.info("输入字段不能为空{}"+date);
	        return null;
	    }
	    StringBuilder sb = new StringBuilder();
	    DateObject dateObject=new DateObject();
	    try {
	    	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	        Date parse = formatter.parse(date);
	        Date now = new Date();
	        // 这样得到的差值是微秒级别
	        long diff = now.getTime() - parse.getTime();
	        // 只能精确到日 无法具细到年 月 不能确定一个月具体多少天 不能确定一年具体多少天
	        // 获取日
	        long day = diff / (1000 * 60 * 60 * 24);
	        diff = diff % (1000 * 60 * 60 * 24);
	        if (day > 0) {
	            sb.append(day).append("天");
	        }
	        // 获取时
	        long hour = diff / (1000 * 60 * 60);
	        diff = diff % (1000 * 60 * 60);
	        if (hour > 0) {
	            sb.append(hour).append("时");
	        }
	        // 获取分
	        long min = diff / (1000 * 60);
	        diff = diff % (1000 * 60);
	        if (min > 0) {
	            sb.append(min).append("分");
	        }
	        // 获取秒
	        long sec = diff / 1000;
	        if (sec > 0) {
	            sb.append(sec).append("秒");
	        }
	        dateObject.setDay(day);
	        dateObject.setHour(hour);
	        dateObject.setMin(min);
	        dateObject.setSec(sec);
	        dateObject.setTime(String.valueOf(sb));
	        log.info("时间差{}",sb);
	        return dateObject;
	    } catch (Exception e) {
	        return null;
	    }
	}

	/**
	 *
	 * @return
	 */
	public static Date  getDate() {
		Date currentTime = new Date();
		return currentTime;
	}
	/**
	 * 	返回短时间字符串格式yyyyMMdd yyyy-MM-dd
	 * @return
	 */
	public static String getDateShort(String pattern) {
	   Date currentTime = new Date();
	   SimpleDateFormat formatter = new SimpleDateFormat(pattern);
	   String dateString = formatter.format(currentTime);
	   return dateString;
	}
	/**
	 * 
	 * @param datestr
	 * @param pattern1
	 * @param pattern2
	 * @return
	 */
	public static String getDateShort(String datestr,String pattern1,String pattern2) {
	   SimpleDateFormat formatter = new SimpleDateFormat(pattern1);
		Date date = null;
		try {
			date = formatter.parse(datestr);
		} catch (Exception e) {
			throw new BlogException(ResultEnum.PARAMS_ERROR,"日期转换失败");
		}
		
	   SimpleDateFormat formatter2 = new SimpleDateFormat(pattern2);		
	   String dateString = formatter2.format(date);
	   return dateString;
	}
	/**
	 * 日期加一处理
	 * String date
	 */
	public static String add1Datestr(String datestr) {
		Date date=StrToDate(datestr);
		Calendar   calendar = new GregorianCalendar(); 
		calendar.setTime(date); 
		calendar.add(Calendar.DATE,1); //把日期往后增加一天,整数  往后推,负数往前移动 
		date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMdd" ); 
	    String str = sdf.format(date); 		
		return str;
	}
	/**
	 * 日期加一处理
	 * String date
	 */
	public static String add1Date(Date date) {
		Calendar calendar = new GregorianCalendar(); 
		calendar.setTime(date); 
		calendar.add(Calendar.DATE,1); //把日期往后增加一天,整数  往后推,负数往前移动 
		date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMdd" ); 
	    String str = sdf.format(date); 		
		return str;
	}
	/**
	* 字符串转换成日期
	* @param str
	* @return date
	*/
	public static Date StrToDate(String str) {

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (Exception e) {
			throw new BlogException(ResultEnum.PARAMS_ERROR,"字符串转换成日期失败");
		}
		return date;
	}
	/**
	 * 日期转换成字符串
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String DateToStr(Date date,String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		String str = format.format(date);
		return str;
	}
	/**
	 *
	 * DateObject.java
	 * @param startDate
	 * @param endDate
	 * @param pattern
	 * @return
	 */
    public static int nDaysBetweenTwoDate(String startDate, String endDate,String pattern) {  
        SimpleDateFormat df = new SimpleDateFormat(pattern);  
        Date firstDate = null;  
        Date secondDate = null;  
        try {  
            firstDate = df.parse(startDate);  
            secondDate = df.parse(endDate);  
        } catch (Exception e) {  
            // 日期型字符串格式错误  
            log.info("日期型字符串格式错误");
			throw new BlogException(ResultEnum.PARAMS_ERROR,"计算日期时间差失败");
        }  
        int nDay = (int) ((secondDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000));  
        return nDay;  
    }

}

