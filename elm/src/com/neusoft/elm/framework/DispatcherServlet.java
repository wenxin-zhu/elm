package com.neusoft.elm.framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neusoft.elm.controller.FoodController;
import com.neusoft.elm.controller.UserController;

/**
* 自定义前端控制器 拦截url格式要求： /控制器类名/控制器方法名
*/
@WebServlet("/") //拦截所有的请求

public class DispatcherServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//中文编码处理
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		
		//获取客户端请求路径
		String path = request.getServletPath();
		System.out.println(path);
		
		//根据请求路径将其解析
		String className = path.substring(1,path.lastIndexOf("/"));
		String methodName = path.substring(path.lastIndexOf("/")+1);
		
		PrintWriter out = null;
		
		
		try {
			//通过描述类获取对象
			Class clazz = Class.forName("com.neusoft.elm.controller."+className);
			Object controller = clazz.newInstance();
			//通过描述类获取方法
			Method method = clazz.getMethod(methodName,new Class[]{HttpServletRequest.class});
			//调用该对象中的方法
			Object result = method.invoke(controller,new Object[] {request});
			System.out.println(result);
			
			out = response.getWriter();
			//解析成json数据
			ObjectMapper om = new ObjectMapper();
			out.print(om.writeValueAsString(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("DispatcherServlet信息：请求url："+path);
			System.out.println("DispatcherServlet信息：类名："+className+"\t方法名："+methodName);
		}finally {
			out.close();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
}
