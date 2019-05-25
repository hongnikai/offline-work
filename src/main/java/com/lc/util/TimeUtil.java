package com.lc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  @描述：获取系统时间
 *  @author LC   (●'◡'●)  lc
 *  创建时间：2018-3-2 上午9:35
 */
public class TimeUtil {

	public static String getSystemTimeFormart(){

		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String eva_time = simple.format(System.currentTimeMillis());

		return eva_time;
	}

	/**
	 *  @描述：     获取当前系统当前时间 long类型
	 *  @return 	long类型当前系统时间   返回时间戳
	 */
	public static long timeMath(){
		return System.currentTimeMillis();
	}

	/**
	 *  @描述：
	 *  @return 	输出时间戳  long类型
	 * @throws ParseException
	 */
	public static long getRequestTimeStampLong(String time) throws ParseException{
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = simpleDateFormat.parse(time);
		long ts = date.getTime();
		//res = String.valueOf(ts);
		return ts;
	}

	/**
	 * @deprecated   获取时间戳 转换成String 类型的时间
	 * @param timeStamp
	 * @return
	 */
	public static String getStringByTimeStamp(long timeStamp){
		Date date = new Date(timeStamp);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = simpleDateFormat.format(date);
		return d;
	}


	public static void main(String[] args) {
	}


}
