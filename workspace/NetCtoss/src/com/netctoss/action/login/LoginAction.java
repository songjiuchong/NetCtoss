package com.netctoss.action.login;

import javax.servlet.http.Cookie;
import org.apache.struts2.ServletActionContext;

import com.netctoss.action.BaseAction;
import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.login.IAdminDao;
import com.netctoss.pojo.Admin;

public class LoginAction extends BaseAction{
	
	//输入属性:
	private Admin admin;	//这里需要注意的是,在action被实例化的时候,这个对象参数会被初始化为null,但是如果当前端控制器接收到类似admin.xxx
	 						//这样的参数时会立即为这个引用变量实例化,然后再给其xxx参数赋值,所以在没有这样形式的参数传递给前端控制器的情况下,
	 						//这个属性将会是null,会直接导致下面语句中的.get方法产生空指针异常;
	
	private String validateCode; //获取页面上用户输入的验证码;
	
	private boolean remember; //用户是否选择记住密码(只有当Login验证通过的时候才会去执行记住密码操作);
	
	
	//输出属性:
	private String errorMsg;
	
	private String validateErrorMsg;
	
	
	public String execute(){
		
		//1,调用DAO根据输入属性查询是否存在该用户;
		IAdminDao dao = DAOFactory.getAdminDao();
		Admin a = null;
		
		//由于从session这个Map类型对象中直接取出的是Object类型对象,这里只能先强转为String类型才可以在之后用equalsIngoreCase比较;
		String imageCode = (String)session.get("imageCode");
		
		try {
			a = dao.findByCodeAndPassword(admin.getAdminCode(),admin.getPassword());
			
		//如果用户不通过/toLogin来登陆,而是直接/login登陆则会发生admin为null造成的空指针异常;
		} catch (RuntimeException e){  
			return "login";
		} catch (DAOException e) {
			e.printStackTrace();
			//如果发生异常返回到登录页面,同时将"异常"信息通过输出属性给用户作为提醒;
			errorMsg = "服务器繁忙,稍后再试！";
			return "loginAgain";
		}
		//2,判断查询结果是否为空;
		if(a == null){
			//2.1,如果为空则返回error;
			errorMsg = "账号或密码错误！";
			return "loginAgain";
		}else if(!imageCode.equalsIgnoreCase(validateCode)){
			validateErrorMsg = "验证码错误！";
			return "loginAgain";
		}else{
			//2.2,如果不为空则将用户写入session,返回success;
			session.put("admin", a);
			//如果用户选择记住密码,则将指定的cookie对象赋值给response对象;
			if(remember){
			Cookie remembered = new Cookie("remembered","true");
			remembered.setPath("/NetCtoss/login/");
			remembered.setMaxAge(3600);
			Cookie adminCode = new Cookie("adminCode",admin.getAdminCode());
			adminCode.setPath("/NetCtoss/login/");
			adminCode.setMaxAge(3600);
			Cookie password = new Cookie("password",admin.getPassword());
			password.setPath("/NetCtoss/login/");
			password.setMaxAge(3600);
			ServletActionContext.getResponse().addCookie(remembered);
			ServletActionContext.getResponse().addCookie(adminCode);
			ServletActionContext.getResponse().addCookie(password);
			}else{
				//如果用户选择不保存,则需要将对应的cookie注销;
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
			}
			return "success";
		}
		
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public String getValidateErrorMsg() {
		return validateErrorMsg;
	}

	public void setValidateErrorMsg(String validateErrorMsg) {
		this.validateErrorMsg = validateErrorMsg;
	}

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}

}
