package com.netctoss.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

//父Action类去实现SessionAware接口,在子类Action被实例化时会先实例化父Action类并获得此session对象,
//之后子类Action就可以随意调用;
public abstract class BaseAction implements SessionAware {
	
	protected Map<String, Object> session;
	
	public void setSession(Map<String, Object> arg0) {
		session=arg0;
	}

}
