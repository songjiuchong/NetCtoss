package com.netctoss.interceptor;

import com.netctoss.util.HibernateUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class OpenSessionInViewInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		arg0.invoke(); //执行action,result->jsp;
		HibernateUtil.closeSession();
		return null;
	}
}
