package com.wymessi.filter;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.alibaba.druid.support.http.StatViewServlet;


@WebServlet(urlPatterns = "/druid/*", 
    initParams={
            @WebInitParam(name="allow",value="127.0.0.1"),// IP白名单 
            @WebInitParam(name="deny",value="192.168.16.111"),// IP黑名单
            @WebInitParam(name="resetEnable",value="false")// 禁止HTML页面Reset All按钮
    })
public class DruidStatViewServlet extends StatViewServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}