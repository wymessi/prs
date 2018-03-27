package com.wymessi.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理器
 * @author wangye
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseBody
	@ExceptionHandler(JsonFormatException.class)
	public String exceptionHandle(JsonFormatException exception) {
		StringBuilder builder = new StringBuilder();
		builder.append("{code:")
        .append(exception.getCode())
        .append(",msg:")
        .append(exception.getMsg())
        .append("}");
		return builder.toString();
	}
	
	/*@ResponseBody
	@ExceptionHandler(Exception.class)
	public String exceptionHandle(Exception exception) {
		
		return "未知错误,请联系管理员！！！";
	}*/
}
