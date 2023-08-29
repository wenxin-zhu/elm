package com.neusoft.elm.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

// 通用工具类
public class CommonUtil {
	
	// 获得当前时间
	public static String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		return sdf.format(calendar.getTime());
	}
}
