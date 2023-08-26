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
* �Զ���ǰ�˿����� ����url��ʽҪ�� /����������/������������
*/
@WebServlet("/") //�������е�����

public class DispatcherServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ı��봦��
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		
		//��ȡ�ͻ�������·��
		String path = request.getServletPath();
		System.out.println(path);
		
		//��������·���������
		String className = path.substring(1,path.lastIndexOf("/"));
		String methodName = path.substring(path.lastIndexOf("/")+1);
		
		PrintWriter out = null;
		
		
		try {
			//ͨ���������ȡ����
			Class clazz = Class.forName("com.neusoft.elm.controller."+className);
			Object controller = clazz.newInstance();
			//ͨ���������ȡ����
			Method method = clazz.getMethod(methodName,new Class[]{HttpServletRequest.class});
			//���øö����еķ���
			Object result = method.invoke(controller,new Object[] {request});
			System.out.println(result);
			
			out = response.getWriter();
			//������json����
			ObjectMapper om = new ObjectMapper();
			out.print(om.writeValueAsString(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("DispatcherServlet��Ϣ������url��"+path);
			System.out.println("DispatcherServlet��Ϣ��������"+className+"\t��������"+methodName);
		}finally {
			out.close();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
}
