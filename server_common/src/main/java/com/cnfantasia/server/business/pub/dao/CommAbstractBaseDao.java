package com.cnfantasia.server.business.pub.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.cnfantasia.server.business.pub.constant.CommDictConstants;

/**
 * MyBatis会话获得类，其作为所有数据操作Dao 类的父类
 * */
@Repository //TODO 是否可以注释掉？
public abstract class CommAbstractBaseDao {
	// private Logger logger = Logger.getLogger(this.getClass());

	/** 判断是否为模糊查询的sql参数字段名 **/
	public static final String QUERY_TYPE_IF_DIM = "_QUERY_TYPE_IF_DIM";
	/** 根据序列号查询时，主键对应的key */
	public static final String PARAMETER = "_seqIdParameter";
	
	protected static final Integer RecordState_DELETED=CommDictConstants.RecordState.DELETED;//已删除
	protected SqlSessionTemplate sqlSession;

	/*public SqlSessionTemplate getSqlSession() {
		return sqlSession;
	}*/

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
}
