package com.cnfantasia.server.api.userQuestion.service;

import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.domainbase.userQuestion.entity.UserQuestion;

import java.util.List;

/**
 * @ClassName: IUserQuestionService
 * @Date: 2017-02-23 14:31
 * @Auther: kangduo
 * @Description: ()
 */
public interface IUserQuestionService {
    public Integer addUserQuestion(UserQuestion uq, List<RequestFileEntity> picInfoList);
}
