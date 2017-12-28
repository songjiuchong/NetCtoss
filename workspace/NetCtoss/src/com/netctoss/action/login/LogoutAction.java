package com.netctoss.action.login;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;

import com.netctoss.action.BaseAction;

public class LogoutAction extends BaseAction{
	
	public String execute(){
		//将验证用户是否登录成功的key("admin")以及其value(admin对象)从session映射中删除
		//之后除非重新成功登录再次将管理员信息注册入session,不然将无法通过Login拦截器;
		session.remove("admin");
		//清除记住密码时保存的cookie;
		Cookie remembered = new Cookie("remembered","");
		remembered.setMaxAge(0);
		remembered.setPath("/NetCtoss/login/");
		Cookie adminCode = new Cookie("adminCode","");
		adminCode.setMaxAge(0);
		adminCode.setPath("/NetCtoss/login/");
		Cookie password = new Cookie("password","");
		password.setMaxAge(0);
		password.setPath("/NetCtoss/login/");
		ServletActionContext.getResponse().addCookie(remembered);
		ServletActionContext.getResponse().addCookie(adminCode);
		ServletActionContext.getResponse().addCookie(password);
		return "success";
	}
}
