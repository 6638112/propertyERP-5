package com.cnfantasia.server.api.access.service;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.cnfantasia.server.api.access.constant.AccessDict.Code;
import com.cnfantasia.server.domainbase.htCarGb.dao.IHtCarGbBaseDao;
import com.cnfantasia.server.domainbase.htCarGb.entity.HtCarGb;

/**
 * 车禁基类
 * 
 * @author liyulin
 * @version 1.0 2017年12月7日 下午4:11:26
 */
@Service
public class BaseCarService {

	private static List<HtCarGb> allHtCarGbList = null;
	@Resource
	private IHtCarGbBaseDao htCarGbBaseDao;

	/**
	 * 数据初始化
	 */
	@PostConstruct
	public void initAllHTCarGb() {
		if (allHtCarGbList == null) {
			refreshAllHTCarGb();
		}
	}

	public void refreshAllHTCarGb() {
		allHtCarGbList = htCarGbBaseDao.selectHtCarGbByCondition(null, true);
	}

	public static List<HtCarGb> getAllHtCarGbList() {
		return allHtCarGbList;
	}

	public static Code getCodeByGbId(BigInteger gbId) {
		String code = StringUtils.EMPTY;
		for (HtCarGb htCarGb : allHtCarGbList) {
			if (htCarGb.gettGroupBuildingFId().compareTo(gbId) == 0) {
				code = htCarGb.getCode();
				break;
			}
		}
		
		return Code.valueOf(code);
	}
}
