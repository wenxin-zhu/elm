package com.neusoft.elm.dao;

import java.util.List;

import com.neusoft.elm.po.Business;

public interface BusinessDao {

	// ���ݵ�ͷ����Ų�ѯ�����̼���Ϣ
	public List<Business> listBusinessByOrderTypeId(Integer orderTypeId) throws Exception;
	// �����̼ұ�Ų�ѯ�̼���Ϣ
	public Business getBusinessById(Integer businessId) throws Exception;
}
