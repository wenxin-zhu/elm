package com.neusoft.elm.view.impl;

import com.neusoft.elm.view.BusinessView;

import java.util.List;
import java.util.Scanner;

import com.neusoft.elm.dao.BusinessDao;
import com.neusoft.elm.dao.impl.BusinessDaoImpl;
import com.neusoft.elm.po.Business;

public class BusinessViewImpl implements BusinessView{
	
	Scanner input = new Scanner(System.in);
	//管理层查看商家
	@Override
	public void listBusinessAll() {
		BusinessDao dao = new BusinessDaoImpl();
		List<Business> list = dao.listBusiness();
		System.out.println("商家编号\t商家名称\t商家地址\t商家介绍\t起送费\t配送费");
		for(Business b : list) {
		System.out.println(b.getBusinessId()+"\t"+b.getBusinessName()+"\t"+b.getBusinessAddress()+"\t"+b.getBusinessExplain()+"\t"+b.getStarPrice()+"\t"+b.getDeliveryPrice());
		}
	}
	
	@Override
	public void showBusiness(Integer businessId) {
		BusinessDao dao = new BusinessDaoImpl();
		Business business = dao.getBusinessById(businessId);
		System.out.println(business);
	}

	@Override
	public Business login() {
		System.out.println("请输入商家编号：");
		int businessId = input.nextInt();
		System.out.println("请输入密码：");
		String password = input.next();
		
		BusinessDao dao = new BusinessDaoImpl();
		
		return dao.getBusinessByIdByPass(businessId, password);
	}

	@Override
	public void editBusiness(Integer businessId) {
		BusinessDao dao = new BusinessDaoImpl();
		Business business = dao.getBusinessById(businessId);
		System.out.println(business);
		
		String inputStr = "";
		System.out.println("是否修改商家名称(y/n)：");
		inputStr = input.next();
		if(inputStr.equals("y")) {
			System.out.println("请输入新的商家名称：");
			business.setBusinessName(input.next());
		}
		
		System.out.println("是否修改商家地址(y/n)：");
		 inputStr = input.next();
		 if(inputStr.equals("y")) {
		 System.out.println("请输入新的商家地址：");
		 business.setBusinessAddress(input.next());
		 }
		 
		 System.out.println("是否修改商家介绍(y/n)：");
		 inputStr = input.next();
		 if(inputStr.equals("y")) {
		 System.out.println("请输入新的商家介绍：");
		 business.setBusinessExplain(input.next());
		 }
		 
		 System.out.println("是否修改起送费(y/n)：");
		 inputStr = input.next();
		 if(inputStr.equals("y")) {
		 System.out.println("请输入新的起送费：");
		 business.setStarPrice(input.nextDouble());
		 }
		 
		 System.out.println("是否修改配送费(y/n)：");
		 inputStr = input.next();
		 if(inputStr.equals("y")) {
		 System.out.println("请输入新的配送费：");
		 business.setDeliveryPrice(input.nextDouble());
		 }
		 
		 int result = dao.updateBusiness(business);
		 if(result>0) {
			 System.out.println("\n修改商家信息成功！\n");
			 }else {
			 System.out.println("\n修改商家信息失败！\n");
			 }
	}
	
	@Override
	public void updateBusinessByPassword(Integer businessId) {
		BusinessDao dao = new BusinessDaoImpl();
		Business business = dao.getBusinessById(businessId);
		
		System.out.println("\n请输入旧密码：");
		String oldPass = input.next();
		System.out.println("\n请输入新密码：");
		String password = input.next();
		System.out.println("\n请再次输入新密码：");
		String beginPassword = input.next();
		
		if(!business.getPassword().equals(oldPass)) {
			System.out.println("\n旧密码输入错误！");
		}else if(!beginPassword.equals(password)) {
			System.out.println("\n两次输入密码不一致！");
		}else {
			int result = dao.updateBusinessByPassword(businessId, beginPassword);
			if(result>0) {
				 System.out.println("\n修改密码成功！");
				 }else {
				 System.out.println("\n修改密码失败！");
				 }
		}
	}
	
}
