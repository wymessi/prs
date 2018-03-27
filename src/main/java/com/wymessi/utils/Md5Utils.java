package com.wymessi.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class Md5Utils {

	public static String md5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		 //确定计算方法
	    MessageDigest md5=MessageDigest.getInstance("MD5");
	    BASE64Encoder base64en = new BASE64Encoder();
	    //加密后的字符串
	    String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
	    return newstr;
	}
}
