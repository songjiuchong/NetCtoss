package com.netctoss.action.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import com.netctoss.pojo.Admin;

public class ToLoginAction{
	
	//输出属性;
	private Admin admin;
	private boolean remember;
	
	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		Cookie[] cookies = request.getCookies();
		boolean remembered = false;
		if(cookies!=null){
		for(Cookie c:cookies){
			if(c.getName().equals("remembered")){
				remember = true;
				remembered = true;
			}
		}
		}
		//如果客户在之前成功登陆时选择了记住密码,则在cookie销毁之前访问登陆界面时
		//保存在cookie中的用户名和密码将被显示在页面上;
		if(remembered){
			admin = new Admin();
			for(Cookie c:cookies){
				if(c.getName().equals("adminCode")){
					admin.setAdminCode(c.getValue());
				}
			}
			for(Cookie c:cookies){
				if(c.getName().equals("password")){
					admin.setPassword(c.getValue());
				}
			}
		}
		return "success";
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}

}
