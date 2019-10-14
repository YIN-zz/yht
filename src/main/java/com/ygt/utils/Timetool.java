package com.ygt.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作
 * @author lujun
 *
 */
public class Timetool {
	
	/**
	 * 常驻
	 */
	public static Timetool that = new Timetool();
	
	/**
	 * 日期格式
	 */
	private final String MATCHESSTR = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 获得当前时间
	 */
	public  String getNowTime() {
		return getTimeStr(new Date());
	}
	
	/**
	 * 获得一个时间数据
	 * @param date
	 * @return
	 */
	public String getTimeStr(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(MATCHESSTR);
		return sdf.format(date);
	}
	
	/**
	 * 获得正常的还有多少天格式
	 * @param time
	 * @param whatIsThat
	 * @return
	 */
	public String howManyTimeForThat(String time,String whatIsThat) {
		return howManyTimeForThat(time,whatIsThat,false);
	}
	/**
	 * 获得很强格式的还有多长时间格式
	 * @param time
	 * @param whatIsThat
	 * @return
	 */
	public String howManyTimeForThatPlus(String time,String whatIsThat) {
		return howManyTimeForThat(time,whatIsThat,true);
	}
	
	/**
	 * 距离某天还有多久
	 * @param time
	 * @param whatIsThat
	 * @return
	 */
	private String howManyTimeForThat(String time,String whatIsThat,boolean plus) {
		Date thatTime = getParseStr(time);
		long num = thatTime.getTime();
		long now = new Date().getTime();
		long second = (num-now)/1000;
		long minute = second/60;
		String minuteStr = minute+"";
		second -=minute*60;
		long hour = minute/60;
		minute -=hour*60;
		long day = hour/24;
		hour -=day*24;
		long year = day/365;
		day -=year*365;
		if(plus)
			return "距"+whatIsThat+"还有"+minuteStr+"秒";
		else
			return "距"+whatIsThat+"还有"+(year<1?"":year+"年")+(day<1?"":day+"天")+(hour<1?"":hour+"小时")+(minute<1?"":minute+"分钟")+(second<1?"":second+"秒");
		
	}
	
	/**
	 * 判断一个日期是否已经过去
	 * @param time 代表日期的字段年月日
	 * @return 过期返回true 没过期返回false
	 */
	public boolean isAfter(String time) {
		Date thatTime  = getParseStr(time);
		return new Date().after(thatTime);
	}
	
	/**
	 * 判断哪个日期更早
	 * @param time1
	 * @param time2
	 * @return time2更早为true time1为false
	 */
	public boolean isAfter(String time1,String time2) {
		Date thatTime1 = getParseStr(time1);
		Date thatTime2 = getParseStr(time2);
		return thatTime1.after(thatTime2);
	}
	
	/**
	 * 判断哪个日期更早
	 * @param time1
	 * @param time2
	 * @return time2更早为true time1为false
	 */
	public boolean isAfter(Date time1,String time2) {
		Date thatTime2 = getParseStr(time2);
		return time1.after(thatTime2);
	}
	
	/**
	 * 判断哪个日期更早
	 * @param time1
	 * @param time2
	 * @return time2更早为true time1为false
	 */
	public boolean isAfter(String time1,Date time2) {
		Date thatTime1 = getParseStr(time1);
		return thatTime1.after(time2);
	}
	
	/**
	 * 判断一个日期是否是几天前的
	 * @param time
	 * @param num -1`一天前` -2`两天前`
	 * @return 是返回true
	 */
	public boolean isAfter(String time,int num) {
		Calendar parseDate = Calendar.getInstance();
		parseDate.add(Calendar.DAY_OF_YEAR, num);
		Date date = parseDate.getTime();
		Date thatTime = getParseStr(time);
		return date.after(thatTime);
	}
	
	/**
	 * 获得一个日期的计算后的日期
	 * @param time 增加之前的时间
	 * @param dayNum 增加的天数
	 * @return
	 */
	public String getAndTimeStr(String time,int dayNum) {
		Date thatTime = getParseStr(time);
		return getAndTimeStr(thatTime,dayNum);
	}
	
	/**
	 * 获得一个日期的计算后的日期
	 * @param time 增加之前的时间
	 * @param dayNum 增加的天数
	 * @return
	 */
	public String getAndTimeStr(Date time,int dayNum) {
		Calendar parseDate = Calendar.getInstance();
		parseDate.setTime(time);
		parseDate.add(Calendar.DAY_OF_YEAR, dayNum);
		time = parseDate.getTime();
		return this.getNomalDateStrPlus(time);
	}
	
	
	/**
	 * 把给的日期转为早上0:00
	 * @param time
	 * @return
	 */
	public String getNomalDateStr(Date time) {
//		Date thatTime = getParseStr(time);
		SimpleDateFormat sdf = new SimpleDateFormat(MATCHESSTR);
		String timeStr = sdf.format(time);
		timeStr = timeStr.substring(0, 10)+" 00:00:00"; //2018-12-12 12:30:20
		return timeStr;
	}
	
	/**
	 * 把给的日期字符串转为规范格式早上0:00
	 * @param time
	 * @return
	 */
	public String getNomalDateStr(String time) {
		Date thatTime = getParseStr(time);
		return getNomalDateStr(thatTime);
	}
	/**
	 * 把给定的日期字符转化为规范格式 晚上23:59
	 * @param time
	 * @return
	 */
	//2018-02-25 08:30:03
	public String getNomalDateStrPlus (Date thatTime) {
//		Date thatTime = getParseStr(time);//获取标志日期格式
		String time = getTimeStr(thatTime);
		time = time.substring(0, 10); //2018-12-12 12:30:20
		thatTime = getParseStr(time);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(thatTime);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		calendar.add(Calendar.SECOND, -1);
		SimpleDateFormat sdf = new SimpleDateFormat(MATCHESSTR);
		return sdf.format(calendar.getTime());
	}
	
	/**
	 * 给定日期转为标准格式 晚上23:59
	 * @param time
	 * @return
	 */
	public String getNomalDateStrPlus (String timeStr) {
		Date time = getParseStr(timeStr);
		return getNomalDateStrPlus(time);
	}
	
	/**
	 * 根据字符串分析日期
	 * @param time
	 * @return
	 */
	public Date getDate(String time) {
		return getParseStr(time);
	}
	
	/**
	 * 分析日期格式
	 * @param time
	 * @return
	 */
	private Date getParseStr(String time) {
		if(time.length()>10) {
			SimpleDateFormat sdf = new SimpleDateFormat(MATCHESSTR);
			try {
				return sdf.parse(time);
			} catch (ParseException e) {
				e.printStackTrace();
				throw new RuntimeException("不加时分秒,或固定格式:"+MATCHESSTR);
			}
		}
		
		char yearMiddleStr = time.charAt(4);
		String parseStr = "";
		if((yearMiddleStr+"").matches("^\\d$")) { //如果没有中间符号 *******
			if(time.length() == 6) { //201855 ******
				parseStr = "yyyyMd";
			}else if(time.length() == 8){ //20180102  ********
				parseStr = "yyyyMMdd";
			}else if(time.length() == 7) { //2018011   2018101  2018121 *******
				if(Integer.parseInt(yearMiddleStr+"") == 0) { //2018011 ****0**
					parseStr = "yyyyMMd";
				}else if(time.charAt(5) == 0) { //2018101 *****0*
					parseStr = "yyyyMdd";
				}else if(Integer.parseInt(yearMiddleStr+"") !=1 ){ //****5** ****9**
					parseStr = "yyyyMdd";
				}else { //****1**
					if(Integer.parseInt(time.charAt(5)+"")>2) { //****13*
						parseStr = "yyyyMdd";
					}else { //****12* ||****11*
						throw new RuntimeException(time+",这是几月几号?");
					}
				}
			}
		}else { //有中间符号 ****-**-**
			String mounthStr = "";//月符号解析
			int data = time.lastIndexOf(yearMiddleStr);
			if(data == 6) { //****-*-*
				mounthStr = "M";
			}else if(data == 7) { //****-**-* || ****-**-**
				mounthStr = "MM";
			}
			String dayStr = ""; //天符号解析
			String parseLastStr = time.substring(data+1);
			if(parseLastStr.length()==2) {
				dayStr = "dd";
			}else if(parseLastStr.length() == 1) {
				dayStr = "d";
			}
			parseStr = "yyyy"+yearMiddleStr+mounthStr+yearMiddleStr+dayStr;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(parseStr); //进行日期解析
		Date thatTime  = null; 
		try {
			thatTime = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		sdf = null;
		parseStr = null;
		return thatTime;
	}
}
