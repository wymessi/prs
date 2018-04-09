package com.wymessi.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理器
 * @author wangye
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseBody
	@ExceptionHandler(JsonFormatException.class)
	public Map<String,String> exceptionHandleJsonFormatException(JsonFormatException exception) {
		Map<String,String> map = new HashMap<String,String>();
        map.put("code", exception.getCode());
        map.put("msg", exception.getMsg());
        return map;
	}
	
	@ResponseBody
	@ExceptionHandler(UnloginException.class)
	public ModelAndView exceptionHandleUnloginException(UnloginException exception) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("message");
		mv.addObject("message", exception.getMessage());
		mv.addObject("href", exception.getHref());
		return mv;
	}
	
	/*@ResponseBody
	@ExceptionHandler(Exception.class)
	public String exceptionHandle(Exception exception) {
		
		return "未知错误,请联系管理员！！！";
	}*/
}
