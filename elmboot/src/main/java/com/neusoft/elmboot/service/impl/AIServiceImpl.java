package com.neusoft.elmboot.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.elmboot.service.AIService;
import org.python.util.PythonInterpreter;
import org.springframework.stereotype.Service;

@Service
public class AIServiceImpl implements AIService {
	
	@Override
	public List<String> listOptionByAdj(String adj) {
		Process proc;
		List<String> list = new ArrayList();
		try {
			String pythonPath = "gpt/python";
			String pythonFilePath = "gpt_api.py";
			String[] args1 = new String[] { pythonPath, pythonFilePath, adj};
			proc = Runtime.getRuntime().exec(args1);
			/*
			 * String数组里的那一行很重要 首先一定要设置好你所使用的python的位置，切记不要直接使用python，因为系统会默认使用自带的python，
			 * 所以一定要设置好你所使用的python的位置，
			 * 否则可能会出现意想不到的问题（比如说我使用的是anaconda中的python，而ubuntu系统会默认调用自带的python，
			 * 而我自带的python中并没有numpy库，所以会造成相应的代码不会执行的问题，所以设置好python的位置是很重要的）。
			 * 还有就是要设置好py文件的位置，使用绝对路径。在这里插入代码片 还有就是可以看出，此方法可以满足我们python代码中调用第三方库的情况，简单实用。
			 */
			BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "GBK"));
			String line = null;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
				list.add(line);
			}
			in.close();
			proc.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<String> getContinuousAnswer(String question, String conversation_id) {
		Process proc;
		List<String> list = new ArrayList();
		try {
			String[] args1 = new String[] { "C:\\Users\\ZWX\\AppData\\Local\\Programs\\Python\\Python310\\python",
					"E:/Desktop/gpt_api.py", question, conversation_id};
			proc = Runtime.getRuntime().exec(args1);
			BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "GBK"));
			StringBuffer buf = new StringBuffer();
			String line = null;
			while ((line = in.readLine()) != null) {
//				System.out.println(line);
				buf.append(line);
			}
			String answer = buf.toString();
			System.out.println(answer);
			list.add(answer.split("&")[0]);
			list.add(answer.split("&")[1]);
			in.close();
			proc.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return list;
	}
}
