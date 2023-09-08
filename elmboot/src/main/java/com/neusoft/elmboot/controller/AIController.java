package com.neusoft.elmboot.controller;

import java.util.List;

import com.neusoft.elmboot.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.elmboot.service.AIService;

@RestController
@RequestMapping("/AIController")
public class AIController {

	@Autowired
	private AIService aiService;
	
	@RequestMapping("/listOptionByAdj")
	public List<String> listOptionByAdj(String adj) throws Exception {
		List<String> list = aiService.listOptionByAdj(adj);
		adj = (adj.equals("0")) ? "\"\"" : adj;
		System.out.println(CommonUtil.getCurrentDate()+" 提出的关键词 "+adj+"    AI 回复："+list);
		return list;
	}
	
	@RequestMapping("/getContinuousAnswer")
	public List<String> getContinuousAnswer(String question, String conversation_id) {
		String c_id = (conversation_id.equals("null")) ? "new" : conversation_id;
		System.out.println(CommonUtil.getCurrentDate()+" 提出问题 "+question+" ("+c_id+")");
		return aiService.getContinuousAnswer(question, conversation_id);
	}
}