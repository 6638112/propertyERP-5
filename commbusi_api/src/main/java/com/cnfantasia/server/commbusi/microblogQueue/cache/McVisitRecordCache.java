package com.cnfantasia.server.commbusi.microblogQueue.cache;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import com.cnfantasia.server.api.cache.constant.CacheConstant;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;


/**
 * 街坊微博列表访问记录缓存
 * @author shiyl
 *
 */
public class McVisitRecordCache {
	private static final Long defaultStep = 1L;
	private static PushState stateStart = null;
	private static String lastStateConfig = null;
	private synchronized static void initStateStart(){
		if(stateStart!=null){
			stateStart.clear();
		}else{
			stateStart = PushState.stateStart;
		}
		PushState currSate = stateStart;//当前位置
		String stateConfig = ApplicationContextBothUtil.getSysParamManager().getSysParaValue(SysParamKey.MicroBlogPushTime);
		String[] pushHours = stateConfig.split(",");
		for(int i=0;i<pushHours.length;i++){
			PushState nextState = new PushState(Integer.parseInt(pushHours[i]), i+1, null);
			currSate.setNextState(nextState);
			currSate = nextState;
		}
		currSate.setNextState(PushState.stateEnd);
		lastStateConfig = stateConfig;//缓存configStr
	}
	public static PushState getPushStateStart(){
		if(stateStart==null){
			synchronized (McVisitRecordCache.class) {
				if(stateStart==null){
					initStateStart();
				}
			}
		}else{//检查是否需要更新
			String stateConfig = ApplicationContextBothUtil.getSysParamManager().getSysParaValue(SysParamKey.MicroBlogPushTime);
			if(lastStateConfig==null||!stateConfig.equals(lastStateConfig)){
				synchronized (McVisitRecordCache.class) {
					if(!stateConfig.equals(lastStateConfig)){
						initStateStart();
					}
				}
			}
		}
		return stateStart;
	}
	
//	public static void main(String[] args) {
//		System.out.println(stateStart.handle(0, 0L));
//		System.out.println(stateStart.handle(5, 1L));
//		System.out.println(stateStart.handle(6, 1L));
//		System.out.println(stateStart.handle(12, 1L));
//		System.out.println(stateStart.handle(13, 2L));
//		System.out.println(stateStart);
//		System.out.println((stateStart==PushState.stateStart));
//	}
	
	/**
	 * 现在是否可推送
	 * @param gbId
	 * @return
	 */
	public static boolean checkIfPushNow(BigInteger gbId){
		Long pushCount = getPushCount(gbId);
		int currHour = getCurrHour();
		return getPushStateStart().handle(currHour, pushCount);
	}
	
	private static int getCurrHour(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(ApplicationContextBothUtil.getNowTime());
		int currHour = cal.get(Calendar.HOUR_OF_DAY);
		return currHour;
	}
	/**
	 * 增加微博推送记录数
	 * @param gbId
	 * @return
	 */
	public static Long addPushCount(BigInteger gbId){
		String field = getField(gbId);
		if(field==null){return 0L;}
		String nowKey = getPushRecordModelKey();
		if(!RedisCacheHandler.exsits(nowKey)){//懒清除,只保留一天的
			String codeNamePerfix = getPushRecordPerfix();
			RedisCacheHandler.clearCachePerfixName(codeNamePerfix);
		}
		return RedisCacheHandler.hincrBy(nowKey, field, defaultStep);
	}
	/**
	 * 标记为当前微博推送已推完
	 * @param gbId
	 * @return
	 */
	public static void makrPushCountAsFull(BigInteger gbId){
		String field = getField(gbId);
		if(field!=null){
			String nowKey = getPushRecordModelKey();
			if(!RedisCacheHandler.exsits(nowKey)){//懒清除,只保留一天的
				String codeNamePerfix = getPushRecordPerfix();
				RedisCacheHandler.clearCachePerfixName(codeNamePerfix);
			}
			int currHour = getCurrHour();
			Long allowMaxCount  = stateStart.getCurrAllowMaxCount(currHour);
			RedisCacheHandler.hset(nowKey, field, allowMaxCount+"");
		}
		
	}
	/**
	 * 查询已推送记录数
	 * @param gbId
	 * @return
	 */
	public static Long getPushCount(BigInteger gbId){
		String field = getField(gbId);
		if(field!=null){
			String value = RedisCacheHandler.hget(getPushRecordModelKey(), field);
			if(value!=null){
				return Long.valueOf(value);
			}
		}
		return 0L;
	}
	
	
	/**
	 * 设置为微博列表初始访问,移除field
	 * @param gbId
	 */
	public static void initVisitCount(BigInteger gbId){
		String field = getField(gbId);
		if(field==null){return;}
		RedisCacheHandler.hdel(getVisitRecordModelKey(), field);
	}
	
	
	/**
	 * 增加微博列表访问次数
	 * @param gbId 小区Id
	 * @return
	 */
	public static Long addVisitCount(BigInteger gbId){
		String field = getField(gbId);
		if(field==null){return 0L;}
		return RedisCacheHandler.hincrBy(getVisitRecordModelKey(), field, defaultStep);
	}
	
	/**
	 * 查询微博列表访问次数
	 * @param gbId 小区Id
	 * @return
	 */
	public static Long getVisitCount(BigInteger gbId){
		String field = getField(gbId);
		if(field!=null){
			String value = RedisCacheHandler.hget(getVisitRecordModelKey(), field);
			if(value!=null){
				return Long.valueOf(value);
			}
		}
		return 0L;
	}
	
//	public static boolean getIfVisit(BigInteger gbId){
//		return getVisitCount(gbId)>0?true:false;
//	}
	
	public static Set<BigInteger> getIsVisitGbList(){
		Set<String> setStr = RedisCacheHandler.hkeys(getVisitRecordModelKey());
		if(setStr!=null&&setStr.size()>0){
			Set<BigInteger> setBig = new HashSet<BigInteger>();
			for(String tmpStr:setStr){
				setBig.add(new BigInteger(tmpStr));
			}
			return setBig;
		}
		return null;
	}
	
	private static String getVisitRecordModelKey() {
		return CacheConstant.ModelCode.hset_mc_vis_rec;
	}
	
	private static String getPushRecordModelKey() {
		String nowDay = ApplicationContextBothUtil.getDualDao().getNowDay();
		return getPushRecordPerfix()+"_"+nowDay;
	}
	private static String getPushRecordPerfix(){
		return CacheConstant.ModelCode.hset_mc_push_count;
	}
	
	private static String getField(BigInteger gbId){
		if(gbId!=null){
			return gbId+"";
		}
		return null;
	}
	
	
}
