package com.wymessi.exception;

/**
 * 未登录异常
 * 
 * @author 王冶
 *
 */
public class UnloginException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;
	private String href;

	public UnloginException(String message, String href) {
		super();
		this.message = message;
		this.href = href;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}	

}
