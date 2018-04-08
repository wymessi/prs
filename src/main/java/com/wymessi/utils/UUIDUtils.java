package com.wymessi.utils;

import java.util.UUID;

public class UUIDUtils {

	/**
	 * 生成随机字符串
	 * @return
	 */
	public static String generateUUIDString(){
		return UUID.randomUUID().toString();
	}
}
