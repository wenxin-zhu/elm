package com.neusoft.elm.service;

import java.util.List;

import com.neusoft.elm.po.Business;

public interface BusinessService {

	// ���ݵ�ͷ����Ų�ѯ�����̼���Ϣ
	public List<Business> listBusinessByOrderTypeId(Integer orderTypeId);
	// �����̼ұ�Ų�ѯ�̼���Ϣ
	public Business getBusinessById(Integer businessId);
}
