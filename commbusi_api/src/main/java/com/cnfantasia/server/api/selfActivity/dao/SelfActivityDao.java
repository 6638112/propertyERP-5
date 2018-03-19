package com.cnfantasia.server.api.selfActivity.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.api.selfActivity.entity.SeftActivityWithDetail;
import com.cnfantasia.server.api.selfActivity.entity.SelfActivity4List;
import com.cnfantasia.server.business.pub.page.TotalCountGetter;

public class SelfActivityDao extends AbstractBaseDao{

	public int qrySelfActivityListForCount(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "selfActivity.qrySelfActivityList", paramMap);
	}

	public List<SelfActivity4List> qrySelfActivityListForPage(Map<String, Object> paramMap) {
		return sqlSession.selectList("selfActivity.qrySelfActivityList", paramMap);
	}

    public SeftActivityWithDetail qrySelfActivityWithDetaiById(BigInteger id) {
		return sqlSession.selectOne("selfActivity.qrySelfActivityWithDetaiById", id);
    }
}
