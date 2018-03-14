package com.wymessi.utils;

public class Result<T> {

	private String code;
	private String msg;
	private  T data;
	
	public static <T> Result<T> success(T data){
		Result<T> r = new Result<T>();
		r.code = "200";
		r.msg = "success";
		r.setData(data);
		return r;
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
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
