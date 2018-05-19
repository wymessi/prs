package com.wymessi.utils;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import com.wymessi.param.CustomParam;

public class CustomDateUtils {

	/**
	 * 设置正确的日期格式
	 * 
	 * @param param
	 * @param startToEndTime
	 * @throws ParseException
	 */
	public static void setTimeRange(CustomParam param, String startToEndTime) {
		String[] strs = startToEndTime.split("~");
		for (String string : strs) {
			Date date = null;
			try {
				date = DateUtils.parseDate(string.trim(), "yyyy-MM-dd HH:mm:ss");
			} catch (ParseException e) {
				throw new RuntimeException("时间转化异常");
			}
			if (param.getStartTime() == null) {
				param.setStartTime(date);
			} else {
				param.setEndTime(date);
			}

		}
	}
}
