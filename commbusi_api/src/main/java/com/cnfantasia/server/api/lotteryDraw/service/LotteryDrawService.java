package com.cnfantasia.server.api.lotteryDraw.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.lotteryDraw.dao.LotteryDrawDao;
import com.cnfantasia.server.api.lotteryDraw.entity.LotteryDrawEntity;
import com.cnfantasia.server.api.lotteryDraw.entity.LotteryDrawListEntity;
import com.cnfantasia.server.api.lotteryDraw.entity.LotteryDrawProductEntity;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domainbase.ebuyOrder.dao.IEbuyOrderBaseDao;
import com.cnfantasia.server.domainbase.lotteryDrawProduct.service.LotteryDrawProductBaseService;
import com.cnfantasia.server.domainbase.lotteryDrawRecord.dao.ILotteryDrawRecordBaseDao;
import com.cnfantasia.server.domainbase.lotteryDrawRecord.service.LotteryDrawRecordBaseService;
import com.cnfantasia.server.domainbase.msPrizeOption.dao.IMsPrizeOptionBaseDao;
import com.cnfantasia.server.domainbase.msPrizeOption.entity.MsPrizeOption;
import com.cnfantasia.server.domainbase.user.dao.IUserBaseDao;
import com.cnfantasia.server.domainbase.user.entity.User;

/**
 * @ClassName: LotteryDrawService
 * @Date: 2016-11-25 14:49
 * @Auther: yanghua
 * @Description:(幸运抽奖)
 */
public class LotteryDrawService implements ILotteryDrawService {
	
	private Log logger = LogFactory.getLog(getClass());
	
    @Resource
    private LotteryDrawDao lotteryDrawDao;
    @Resource
    private LotteryDrawProductBaseService drawProductBaseService;
    @Resource
    private LotteryDrawRecordBaseService lotteryDrawRecordBaseService;
	@Resource
	private IUserBaseDao userBaseDao;
	@Resource
	private IEbuyOrderBaseDao ebuyOrderBaseDao;
	@Resource
	private ILotteryDrawRecordBaseDao lotteryDrawRecordBaseDao;
	@Resource
	private IMsPrizeOptionBaseDao msPrizeOptionBaseDao;

    @Override
    public LotteryDrawListEntity getLotteryDraw(BigInteger userId) {
        LotteryDrawListEntity lotteryDrawListEntity = new LotteryDrawListEntity();
        //当前用户参与记录
        LotteryDrawEntity lotteryDrawEntity = lotteryDrawDao.getDrawRecordByUser(userId);
        //其他用户中奖记录
        List<LotteryDrawEntity> lotteryDrawList = lotteryDrawDao.getDrawRecordList(userId);//排除当前用户
		//奖品列表
		List<LotteryDrawProductEntity> lotteryDrawProductList = lotteryDrawDao.getLotteryDrawProduct();
		List<String> names = new ArrayList<String>();
		for (int i = 0; i < lotteryDrawProductList.size(); i++) {
			names.add(lotteryDrawProductList.get(i).getName());
		}
		lotteryDrawListEntity.setNames(names);
        lotteryDrawListEntity.setUserRecordList(lotteryDrawList);
        lotteryDrawListEntity.setProducts(lotteryDrawProductList);
        if(lotteryDrawEntity != null) {//中奖
            lotteryDrawListEntity.setPrizeStatus(lotteryDrawEntity.getPrizeStatus());//2未中奖， 1 已经中奖, 3 已经领奖
            lotteryDrawListEntity.setUserRecord(lotteryDrawEntity);
			lotteryDrawListEntity.setDrawStatus(1);//2未抽奖， 1 已经抽奖
        } else {
            lotteryDrawListEntity.setPrizeStatus(2);//2未中奖， 1 已经中奖
			lotteryDrawListEntity.setDrawStatus(2);//2未抽奖， 1 已经抽奖
        }

        return lotteryDrawListEntity;
    }

	/**
	 * 抽奖
	 *
	 * @param userId
	 */
	@Override
	public JsonResponse drawLottery(BigInteger userIdReq) {
		JsonResponse jsonResponse = new JsonResponse();

		BigInteger userId = UserContext.getOperIdBigInteger();
//		BigInteger userId = new BigInteger("5260130");
		logger.debug("userIdReq:" + userIdReq + ",userId:" + userId);
		if(userId == null) {
			userId = userIdReq;
		}
		// 判断能否抽奖：手机号不为空 && 有缴物业费记录 && 从未抽过奖
		User user = userBaseDao.selectUserBySeqId(userId);
		if(user==null || StringUtils.isBlank(user.getMobile())){// 1、手机号不为空
			jsonResponse.setMessage("您暂未绑定手机，请前往个人中心绑定！");
			jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
		} else {
			/*Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("buyerId", userId);
			//paramMap.put("type", 2);
			paramMap.put("payStatus", 2);
			int ebuyOrderCount = ebuyOrderBaseDao.selectEbuyOrderCount(paramMap, false);
			if(ebuyOrderCount>0){// 2、有缴物业费记录
				paramMap.clear();
				paramMap.put("tUserId", userId);
				int ldCount = lotteryDrawRecordBaseDao.selectLotteryDrawRecordCount(paramMap, false);
				if(ldCount>0){
					jsonResponse.setMessage("您已抽过奖，每个用户只有一次抽奖机会！");
					jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
				} else {// 抽奖处理
					paramMap.clear();
					paramMap.put("userId", userId);
					BigInteger msPrizeOptionId = lotteryDrawDao.drawLottery(paramMap);
					if(msPrizeOptionId!=null && msPrizeOptionId.intValue()!=-1){
						MsPrizeOption msPrizeOption = msPrizeOptionBaseDao.selectMsPrizeOptionBySeqId(msPrizeOptionId);
						LotteryDrawEntity lotteryDrawEntity = new LotteryDrawEntity();
						lotteryDrawEntity.setId(msPrizeOptionId);
						lotteryDrawEntity.setName(msPrizeOption.getName());
						lotteryDrawEntity.setMobile(user.getMobile());
						lotteryDrawEntity.setDesc(msPrizeOption.getLuckDesc());

						jsonResponse.putDataAll(MapConverter.toMap(lotteryDrawEntity));
					}
				}
			} else {
				jsonResponse.setMessage("暂未产生消费记录，没有抽奖资格！");
				jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
			}*/
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userId", userId);
			int microblogContentCount = lotteryDrawDao.selectMicroblogContentCountByUserId(paramMap);
			
			Date now = new Date();
			Date ativeEndDate = DateUtils.convertStrToDate("2017-02-11 18:00:00","yyyy-MM-dd HH:mm:ss");
			
			if(now.after(ativeEndDate)){
				jsonResponse.setMessage("本次活动已结束！");
				jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
				return jsonResponse;
			}
			
			if(microblogContentCount>0){// 2、活动期有发街坊贴记录
				paramMap.clear();
				paramMap.put("tUserId", userId);
				int ldCount = lotteryDrawRecordBaseDao.selectLotteryDrawRecordCount(paramMap, false);
				if(ldCount>0){
					jsonResponse.setMessage("您已抽过奖，每个用户只有一次抽奖机会！");
					jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
				} else {// 抽奖处理
					paramMap.clear();
					paramMap.put("userId", userId);
					BigInteger msPrizeOptionId = lotteryDrawDao.drawLottery(paramMap);
					if(msPrizeOptionId!=null && msPrizeOptionId.intValue()!=-1){
						MsPrizeOption msPrizeOption = msPrizeOptionBaseDao.selectMsPrizeOptionBySeqId(msPrizeOptionId);
						LotteryDrawEntity lotteryDrawEntity = new LotteryDrawEntity();
						lotteryDrawEntity.setId(msPrizeOptionId);
						lotteryDrawEntity.setName(msPrizeOption.getName());
						lotteryDrawEntity.setMobile(user.getMobile());
						lotteryDrawEntity.setDesc(msPrizeOption.getLuckDesc());

						jsonResponse.putDataAll(MapConverter.toMap(lotteryDrawEntity));
					}
				}
			} else {
				jsonResponse.setMessage("请先在街坊发贴，才有抽奖资格");
				jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
			}
		}
		return jsonResponse;
	}
}
