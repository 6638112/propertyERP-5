package com.cnfantasia.server.commbusi.microblogQueue.serviceUn;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.commbusi.microblogQueue.entity.PropertyGoodNewsShellEntity;
import com.cnfantasia.server.domainbase.microblogQueue.entity.MicroblogQueue;

public interface IMicroblogQueueService{
	
	/**
	 * 队列服务批量插入街坊消息数据
	 * */
	void saveMQForQueueService(List<MicroblogQueue> mqList);
	/**
	 * 单个插入数据
	 * @param signal
	 */
	void saveMQForQueueService(MicroblogQueue signal);
	
	/**
	 * 特喜，大家庭又添一丁，欢迎解放号"+userId+"用户加入"+groupBuilding.getName()+"小区。
	 * @param userId
	 * @param gbId
	 * @param gbName
	 */
	void joinGroupBuilding(BigInteger userId, BigInteger gbId, String gbName, BigInteger roomId);
	
	/**
	 * 【月】月份【小区名】小区已经有【用户数】个在解放区上缴费，总计节省物业费【节省金额】元，哇塞，省下不少钱哦！
	 * @param gbId
	 * @param shellEntity
	 */
	void goodNews(BigInteger gbId, PropertyGoodNewsShellEntity shellEntity);
	
	
	/**
	 * 解放号"+userId+"用户已通过门牌验证！已认证的小伙伴快来报名体验“全年物业费打折”福利，同小区5户成团报名即可，详情加群：458449740
	 * @param userId
	 * @param gbId
	 */
	void userRoomValidate(String userId, BigInteger gbId, BigInteger roomId);
	
	/**
	 * 喜讯喜讯，【小区名】小区已正式与解放区签约合作，大家可以在解放区上用折扣缴物业费了（此处应有掌声）！
	 * @param gbId
	 * @param gbName
	 */
	void groupBuildIsSign(BigInteger gbId, String gbName);
	
	/**
	 * 传说中的0折现身了，【小区名】小区解放号【解放号】用户抽到0折，本月物业全免！这绝对是人品爆棚！每天起床点三下积累人品，你也行！
	 * @param userId
	 * @param userHasRoomFId
	 */
	void discountZero(BigInteger userId, BigInteger userHasRoomFId);
	
	/**
	 * 【物业公告标题】【物业公告内容】
	 * @param msgId
	 * @param title
	 * @param content
	 * @param pushTime
	 * @param gbIds
	 */
	void propertyNotice(String msgId, String title, String content, String pushTime, String[] gbIds);
	
	/**
	 * 在解放区购物居然没花钱！解放号【解放号】用户在解放区超市用粮票购物分文没花！别再羡慕嫉妒恨了，每天抽低折，下个免单的就是你。
	 * @param userId
	 * @param gbId
	 */
	void hbPayFree(BigInteger userId, BigInteger gbId);
	
	/**
	 * 小伙伴们注意啦，离本月最后缴费时间还有【天数】天，还没用折扣缴费的小伙伴赶紧缴费吧！
	 */
	void propertyPayRemind(int leftDay, BigInteger gbId);
	
	/**
	 * 破记录啰，【小区名】小区解放号【解放号】用户抽到【折扣】折，刷新本月小区最低折扣，不服赶紧来挑战，每天坚持抽下个记录由你创造！
	 * @param userId
	 * @param gbId
	 * @param gbName
	 * @param discount
	 */
	void groupBuildLeastDiscount(BigInteger userId, BigInteger gbId, String gbName, Double discount, BigInteger userHasRoomFId);
	/**
	 * @param levelCode
	 * @return
	 */
	boolean checkCanPush(Long levelCode);
}
