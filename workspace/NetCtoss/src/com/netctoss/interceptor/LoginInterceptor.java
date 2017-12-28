package com.netctoss.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init() {
		// TODO Auto-generated method stub

	}

	public String intercept(ActionInvocation arg0) throws Exception {
		//1.从session取出admin;
		Map<String,Object> session = arg0.getInvocationContext().getSession();
		Object admin = session.get("admin");
		
		//2.判断user是否为空;
		if(admin==null){
			//2.1为空说明还未登录过,那么转到登录页面;
			return "login";
		}else{
			//2.2不为空,说明已经登录过了,调用Action;
			return arg0.invoke();
		}
	}

}
