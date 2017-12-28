package com.netctoss.pojo;

import java.util.HashSet;
import java.util.Set;

import com.netctoss.util.PrivilegeReader;

public class Role {
	
	private String id;  
	//private Integer id; //由于Hibernate无法无法自动将数据库中取得的类型转化成预期不同的类型放入实体类,这里暂时用Integer类型;
	private String name;
	//权限ID;
	//由于Struts2标签的checkboxlist的listKey属性只能识别String,所以这里选用String类型;
	private String[] privilegeIds;
	
	//追加属性,用于存储相关的Admin对象信息;
	private Set<Admin> admins = new HashSet<Admin>();
	
	//(Alternative Method);
	//如果不通过RoleVo也可以在此处设置一个连接所有privilegeIds对应权限名的字符串,属于自运算的结果,
	//不需要通过数据库获得,且只需要get方法;
	private String privilegeNames;
	public String getPrivilegeNames(){
		if(privilegeIds == null)
			return null;
		privilegeNames="";
		for(int i=0;i<privilegeIds.length;i++){
			String privilegeId = privilegeIds[i];
			String privilegeName = PrivilegeReader.getPrivilegeNameById(privilegeId);
			privilegeNames += (i==0)? privilegeName:("、"+privilegeName);
		}
		return privilegeNames;
	}
	
	public Set<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(Set<Admin> admins) {
		this.admins = admins;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getPrivilegeIds() {
		return privilegeIds;
	}
	public void setPrivilegeIds(String[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
