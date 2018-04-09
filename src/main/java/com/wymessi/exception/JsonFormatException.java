package com.wymessi.exception;
/**
 * request请求转化成json转化异常
 * @author 王冶
 *
 */
public class JsonFormatException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String code;
	private String msg;
	
	public JsonFormatException(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public JsonFormatException(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
}
