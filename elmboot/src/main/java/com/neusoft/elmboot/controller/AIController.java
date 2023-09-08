package com.neusoft.elmboot.controller;

import java.util.List;

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
		return aiService.listOptionByAdj(adj);
	}
	
	@RequestMapping("/getContinuousAnswer")
	public List<String> getContinuousAnswer(String question, String conversation_id) {
		return aiService.getContinuousAnswer(question, conversation_id);
	}
}