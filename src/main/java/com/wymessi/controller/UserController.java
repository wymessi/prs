package com.wymessi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wymessi.po.user.User;
import com.wymessi.service.UserService;
import com.wymessi.utils.RequestUtils;
import com.wymessi.utils.Result;

@RestController
@RequestMapping("/user")
public class UserController {
   
	@Autowired
	private UserService userService;
	
    @RequestMapping("/findById")
    public User findById(HttpServletRequest request) throws Exception{
    	JSONObject json = RequestUtils.toParameter(request);
    	int id = json.getIntValue("id");
    	User user = userService.findById(id);
        return user;
    }
    
    @RequestMapping("/add")
    public Result<User> add(HttpServletRequest request) throws Exception{
    	User user = RequestUtils.toObject(request, User.class);
    	userService.add(user);
        return Result.success(user);
    }
    
}
