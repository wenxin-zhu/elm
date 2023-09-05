package com.neusoft.elmboot.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CommonUtil {
	
	// 获取当前时间
	public static String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		return sdf.format(calendar.getTime());
	}
}
