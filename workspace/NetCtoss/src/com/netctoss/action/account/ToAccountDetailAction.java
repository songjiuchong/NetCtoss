package com.netctoss.action.account;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.account.IAccountDao;
import com.netctoss.pojo.Account;

public class ToAccountDetailAction {
	
	//输入属性;
	private Integer id;

	//输出属性;
	private Account account; 
	private String recommenderIdCard;

	public String execute(){
		IAccountDao dao = DAOFactory.getIAccountDao();
		try {
			account = dao.findAccountById(id);
			Integer recommenderId = account.getRecommenderId();
			Account recommenderAccount = dao.findAccountById(recommenderId);
			if(recommenderAccount!=null){
				recommenderIdCard = recommenderAccount.getIdcardNo();
			}
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRecommenderIdCard() {
		return recommenderIdCard;
	}

	public void setRecommenderIdCard(String recommenderIdCard) {
		this.recommenderIdCard = recommenderIdCard;
	}

}
