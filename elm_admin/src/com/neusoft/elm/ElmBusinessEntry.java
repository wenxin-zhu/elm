package com.neusoft.elm;
import java.util.Scanner;


public class ElmBusinessEntry {
	Scanner input = new Scanner(System.in);
	 
	 System.out.println("---------------------------------------------------------");
	 System.out.println("|\t\t\t 饿了么后台管理系统 \t\t\t|");
	 System.out.println("---------------------------------------------------------");
	 
	 BusinessView businessView = new BusinessViewImpl();
	 
	 //商家登录
	 Business business = businessView.login();
	 
	 if(business!=null) {
	 int menu = 0;
	 while(menu!=5) {
	 //输出一级菜单
	 System.out.println("\n======= 一级菜单（商家管理）1.查看商家信息=2.修改商家信息=3.更
	新密码=4.所属商品管理=5.退出系统=======");
	 System.out.println("请输入你的选择：");
	 menu = input.nextInt();
	 
	 switch(menu) {

}
