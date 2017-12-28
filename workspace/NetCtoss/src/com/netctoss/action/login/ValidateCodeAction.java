package com.netctoss.action.login;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.netctoss.action.BaseAction;
import com.netctoss.util.ImageUtil;

public class ValidateCodeAction extends BaseAction {
	
	//输出属性;
	private InputStream imageStream;
	
	public String execute(){
		
		//1,调用组件,生成图片和验证码;
		Map<String,BufferedImage> imageMap = ImageUtil.createImage();
		
		//2,将验证码记录到session,之后验证会需要;
		String imageCode = imageMap.keySet().iterator().next();
		session.put("imageCode",imageCode);
		
		//3,将生成的图片转换成输入流,赋值给输出属性;
		BufferedImage image = imageMap.get(imageCode);
		try {
			imageStream = ImageUtil.getInputStream(image);
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}
		//4,返回success,找对应的Result做输出;
		return "success";
	}
	
	public void setImageStream(InputStream imageStream) {
		this.imageStream = imageStream;
	}
	
	public InputStream getImageStream() {
		return imageStream;
	}
}
