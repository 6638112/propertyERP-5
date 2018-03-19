package com.cnfantasia.server.ms.commUuid.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Vector;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.server.ms.commUuid.dao.ICommUuidDao;


/**
 * 描述:(唯一编号) 具体业务Service层实现
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommUuidService implements ICommUuidService {
	private ICommUuidDao commUuidDao;
	public void setCommUuidDao(ICommUuidDao commUuidDao) {
		this.commUuidDao = commUuidDao;
	}

	@Override
	public BigInteger getNextUuidBigInteger(String tableName) {
			List<BigInteger> resList = getNextUuidBigInteger(tableName, 1);
			return resList.get(0);
	}
	
	/**
	 * 注意增加同步处理
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public List<BigInteger> getNextUuidBigInteger(String tableName, int size) {
		synchronized(tableName){
			List<BigInteger> resList = new Vector<BigInteger>();
			if(size>0){
//				// 加size写入 更新写在前，用于锁住表
//				int res = commUuidDao.updateCommUuid(tableName,size);
//				if(res<=0){
//					throw new RuntimeException("Update uuid cause exception,tableName is "+tableName+",res is "+res+".");
//				}
//				// 获取当前 需要加入查询锁
//				CommUuid um = commUuidDao.selectCommUuidBySeqId(tableName);
				BigInteger startId = null;
//				startId = um.getNumber();
				startId = commUuidDao.fetchAndUpdateUuid(tableName, size);
				// 返回获取的
				for(int i=size-1;i>=0;i--){
					resList.add(startId.subtract(new BigInteger(i+"")));
				}
			}
			if(size!=resList.size()){
				throw new RuntimeException("Fetch uuid multy cause exception,tableName is "+tableName+",size is "+size+".");
			}
			return resList;
		}
		
	}

}
