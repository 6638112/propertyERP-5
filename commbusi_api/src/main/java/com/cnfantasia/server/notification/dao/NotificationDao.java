package com.cnfantasia.server.notification.dao;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.smsMq.entity.SmsMq;
import com.cnfantasia.server.domainbase.wechatDredgebillMq.entity.WechatDredgebillMq;

public class NotificationDao extends AbstractBaseDao {
	/**
	 * 往(微信公众号维修单消息队列)新增一条记录
	 * @param wechatDredgebillMq
	 * @return
	 */
	public int insertWechatDredgebillMq(WechatDredgebillMq wechatDredgebillMq){
		return sqlSession.insert("notification.insert_wechatDredgebillMq",wechatDredgebillMq);
	}

	/**
	 * 往(手机短信消息队列)新增一条记录
	 * @param smsMq
	 * @return
	 */
	public int insertSmsMq(SmsMq smsMq){
		return sqlSession.insert("notification.insert_smsMq",smsMq);
	}
}
