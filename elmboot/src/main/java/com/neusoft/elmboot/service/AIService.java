package com.neusoft.elmboot.service;

import java.util.List;

public interface AIService {
	
	public List<String> listOptionByAdj(String adj);
	
	public List<String> getContinuousAnswer(String question, String conversation_id);
}
