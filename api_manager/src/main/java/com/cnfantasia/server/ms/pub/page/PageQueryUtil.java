/**   
* Filename:    PageQueryUtil.java   
* @version:    1.0  
* Create at:   2014年5月17日 上午6:08:30   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月17日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.ms.pub.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.ms.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.ms.pub.utils.MapConverter;

/**
 * Filename:    PageQueryUtil.java
 * @version:    1.0.0
 * Create at:   2014年5月17日 上午6:08:30
 * Description: 分页查询工具类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月17日       shiyl             1.0             1.0 Version
 */
public class PageQueryUtil {
	public static <T,Q> List<T>  selectPageList(SqlSession sqlSession,Q queryModel, PageModel pageModel,String pageSqlKey,String countSqlKey) {
		Map<String,Object> paramMap = generateQueryMap(queryModel);
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){
			totalCount=selectTotalCount(sqlSession, queryModel, countSqlKey);
		}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("参数合并发生信息丢失。");}
		
		List<T> resMap= sqlSession.selectList(pageSqlKey,paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return (List<T>)resMap;
	}

	public static <Q> int selectTotalCount(SqlSession sqlSession,Q queryModel,String countSqlKey) {
		Map<String,Object> paramMapForCount = generateQueryMap(queryModel);
		return sqlSession.selectOne(countSqlKey, paramMapForCount);
	}
	
	@SuppressWarnings("unchecked")
	private static <Q> Map<String,Object> generateQueryMap(Q queryModel){
		boolean isDim = false;//精确查询
		Map<String,Object> paramMap = null;
		if(queryModel==null){
			paramMap = new HashMap<String, Object>();
		}else{
			if(queryModel instanceof Map){
				paramMap = (Map<String,Object>)queryModel;
			}else{
				paramMap = MapConverter.toMap(queryModel);
			}
		}
		paramMap.put(AbstractBaseDao.QUERY_TYPE_IF_DIM,isDim);
		return paramMap;
	}
}
