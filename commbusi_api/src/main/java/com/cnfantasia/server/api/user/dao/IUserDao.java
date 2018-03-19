package com.cnfantasia.server.api.user.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.user.entity.HobbyEntity;
import com.cnfantasia.server.api.user.entity.UserEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.hobby.entity.Hobby;
import com.cnfantasia.server.domainbase.user.entity.User;

/**
 * 描述:() 具体业务Dao层接口
 *
 * @author null
 * @version 1.00
 */
public interface IUserDao {

    /**
     * 根据用户编号查询用户详情
     *
     * @param userId
     * @return
     */
    public UserEntity selectUserById(BigInteger userId);

    /**
     * 查询所有可选的兴趣爱好列表
     *
     * @param pageModel
     * @param userId
     * @return
     */
    public List<HobbyEntity> selectAllHobbyList(PageModel pageModel, BigInteger userId);

    /**
     * 查询用户的兴趣爱好列表
     *
     * @param userId
     * @return
     */
    public List<Hobby> selectHobbyListByUserId(BigInteger userId);

    /**
     * 用户批量取消兴趣爱好列表
     *
     * @param userId
     * @param cancelHobbyIds
     * @return
     */
    public Integer cancelHobbyIdsBatch(BigInteger userId, List<BigInteger> cancelHobbyIds);

    /**
     * 更改用户信息，允许签名为空
     *
     * @param user
     * @return
     */
    public int updateUserSignatureNull(User user);

    /**
     * 更新用户的mobile字段为空
     *
     * @param userId
     */
    public int update_user_mobile_to_null(BigInteger userId);

    /**
     * 查询用户是否有业务数据，条件是：1）不是默认门牌，2）有床单；3）有师傅信息；4）有楼下店铺信息
     *
     * @param userId
     * @return 具体的业务数据，若大于0表示有业务数据，否则没有
     */
    public int selectHasBusinessDataCountByUserId(BigInteger userId);

    int changeCouponUser(BigInteger fromUserId, BigInteger toUserId);
}
