/**   
* Filename:    MybatisInterceptor.java   
* @version:    1.0  
* Create at:   2014年8月8日 上午6:39:04   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.pub.mybatis;

import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.business.pub.CommBaseEntity;
import com.cnfantasia.server.business.pub.constant.CommDictConstants;
import com.cnfantasia.server.business.pub.constant.SysCommColums;
import com.cnfantasia.server.business.pub.mybatis.CommMybatisInterceptor;

/**
 * Filename:    MybatisInterceptor.java
 * @version:    1.0.0
 * Create at:   2014年8月8日 上午6:39:04
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月8日       shiyl             1.0             1.0 Version
 */
@Intercepts({
	@Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
	@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class,
			ResultHandler.class }) })
public class MybatisInterceptor extends CommMybatisInterceptor{
	protected Properties properties;// 注意不要使用private
	@Override
	public void setProperties(Properties properties0) {
		this.properties = properties0;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void doMap(Map tmp,String sqlStrUpperCaseTrim){
		if (sqlStrUpperCaseTrim.startsWith("INSERT") && tmp.containsKey(SysCommColums.sys0AddUser)) {
			if(tmp.get(SysCommColums.sys0AddUser)==null){
				tmp.put(SysCommColums.sys0AddUser, UserContext.getOperId());// 新增
			}
		} else if (sqlStrUpperCaseTrim.startsWith("UPDATE")) {
			if (tmp.containsKey(SysCommColums.sys0DelState)&&CommDictConstants.RecordState.DELETED.equals(tmp.get(SysCommColums.sys0DelState)) && tmp.containsKey(SysCommColums.sys0DelUser)) {
				if(tmp.get(SysCommColums.sys0DelUser)==null){
					tmp.put(SysCommColums.sys0DelUser, UserContext.getOperId());// 删除
				}
			} else if(tmp.containsKey(SysCommColums.sys0UpdUser)){
				if(tmp.get(SysCommColums.sys0UpdUser)==null){
					tmp.put(SysCommColums.sys0UpdUser, UserContext.getOperId());// 更新
				}
			}
		}
	}
	
	@Override
	public void doCommBaseEntity(CommBaseEntity tmp,String sqlStrUpperCaseTrim){
		if (sqlStrUpperCaseTrim.startsWith("INSERT")) {
			if(tmp.getSys0AddUser()==null){
				tmp.setSys0AddUser(UserContext.getOperIdBigInteger());// 新增
			}
		} else if (sqlStrUpperCaseTrim.startsWith("UPDATE")) {
			if(tmp.getSys0DelState()!=null&&CommDictConstants.RecordState.DELETED.compareTo(tmp.getSys0DelState())==0){
				if(tmp.getSys0DelUser()==null){
					tmp.setSys0DelUser(UserContext.getOperIdBigInteger());// 删除
				}
			}else if(tmp.getSys0UpdUser()==null){
				tmp.setSys0UpdUser(UserContext.getOperIdBigInteger());// 更新
			}
		}
	}
	
}
