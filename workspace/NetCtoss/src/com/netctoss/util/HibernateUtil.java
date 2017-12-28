package com.netctoss.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static SessionFactory sf;
	//可以将session与当前处理线程绑定;
	private static ThreadLocal<Session> t1 = new ThreadLocal<Session>();
	
	static{
		//加载hibernate.cfg.xml;
		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		//获取SessionFactory;
		sf = conf.buildSessionFactory();
	}
	public static Session getSession(){
		Session session = t1.get();
		if(session == null){
			session  = sf.openSession();
			t1.set(session);
		}
		//获取Session连接;
		return session;
	}
	
	//关闭Session;
	public static void closeSession(){
		Session session = t1.get();
		t1.set(null);
		if(session != null && session.isOpen()){
			session.close();
		}
	}
}
