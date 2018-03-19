/**   
* Filename:    MicroblogDao.java   
* @version:    1.0  
* Create at:   2014年7月22日 上午10:18:48   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月22日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.microblog.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.microblog.entity.MicroblogContentEntity;
import com.cnfantasia.server.api.microblog.entity.XibaoEntity;
import com.cnfantasia.server.api.microblog.entity.XibaoGroupBuilding;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;
import com.cnfantasia.server.domainbase.lanternFestivalPrize.entity.LanternFestivalPrize;
import com.cnfantasia.server.domainbase.microblogContent.entity.MicroblogContent;

/**
 * Filename:    MicroblogDao.java
 * @version:    1.0.0
 * Create at:   2014年7月22日 上午10:18:48
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月22日       shiyl             1.0             1.0 Version
 */
public class MicroblogDao extends AbstractBaseDao implements IMicroblogDao{

	@Override
	public List<MicroblogContentEntity> selectMicroblogList(BigInteger microBlogType, BigInteger groupBuildId,
			PageModel pageModel,BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("microBlogType", microBlogType);
		tmpMap.put("groupBuildId", groupBuildId);
		tmpMap.put("userId", userId);
		String pageSqlKey="microblog.select_microblog_list_page";
		String countSqlKey="microblog.select_microblog_list_count";
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}

	@Override
	public MicroblogContentEntity selectMicroblogDetail(BigInteger microBolgId,BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("microBolgId", microBolgId);
		tmpMap.put("userId", userId);
		return sqlSession.selectOne("microblog.select_microblog_detail", tmpMap);
	}

	@Override
	public MicroblogContent selectMostHotMicBlog(BigInteger microBlogType,BigInteger groupBuildId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("microBlogType", microBlogType);
		tmpMap.put("groupBuildId", groupBuildId);
		return sqlSession.selectOne("microblog.select_mostHot_micblog", tmpMap);
	}

	@Override
	public String selectAllMicroblogLastUpdTime(BigInteger microBlogType,BigInteger groupBuildId,PageModel pageModel,BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("microBlogType", microBlogType);
		tmpMap.put("groupBuildId", groupBuildId);
//		tmpMap.put("_begin", pageModel.begin);
//		tmpMap.put("_length", pageModel.length);
//		tmpMap.put("userId", userId);
		return sqlSession.selectOne("microblog.select_All_Microblog_LastUpdTime",tmpMap);
	}

	@Override
	public Integer selectHaveSendBlogUserCount(BigInteger groupBuildId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("groupBuildId", groupBuildId);
		return sqlSession.selectOne("microblog.select_HaveSend_Blog_UserCount", tmpMap);
	}

	@Override
	public Integer selectUserSendBlogForToday(BigInteger userId, BigInteger groupBuildId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("groupBuildId", groupBuildId);
		return sqlSession.selectOne("microblog.select_User_Blog_For_Today", tmpMap);
	}

	@Override
	public String selectMicroblogLinkDetail(BigInteger microBolgId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("microBolgId", microBolgId);
		return sqlSession.selectOne("microblog.select_Microblog_LinkDetail", tmpMap);
	}
	
	public XibaoGroupBuilding selectXibaoGroupBuilding(Map<String,Object> paramMap) {
		return sqlSession.selectOne("microblog.selectXibaoGroupBuilding", paramMap);
	}
	
	public List<XibaoEntity> selectXibaoList(Map<String,Object> paramMap) {
		return sqlSession.selectList("microblog.selectXibaoList", paramMap);
	}

	@Override
	public int getSelectXibaoListCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("microblog.selectXibaoListCount", paramMap);
	}

	@Override
	public List<LanternFestivalPrize> selectLanternFestivalPrizeList(Map<String, Object> paramMap) {
		return sqlSession.selectList("microblog.select_Lantern_festival_prize_list", paramMap);
	}

	@Override
	public int getSelectLanternFestivalPrizeCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("microblog.select_Lantern_festival_prize_Count", paramMap);
	}

}
