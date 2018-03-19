package com.cnfantasia.server.api.userQuestion.web;

import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.userQuestion.constant.UserQuestionDict;
import com.cnfantasia.server.api.userQuestion.service.IUserQuestionService;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.utils.CommRequestFileParser;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domainbase.userQuestion.entity.UserQuestion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;

/**
 * @ClassName: UserQuestionController
 * @Date: 2017-02-24 9:16
 * @Auther: kangduo
 * @Description: ()
 */
@Controller
@RequestMapping("/userQuestion")
public class UserQuestionController extends BaseController {

    @Resource
    private IUserQuestionService userQuestionService;
    @Resource
    private ICommonRoomService commonRoomService;

    @RequestMapping(value = "/askHelp.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse askHelp(HttpServletRequest request) {
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        BigInteger roomId = commonRoomService.getDefaultRoomIdByUserId(userId);
        Integer type = ParamUtils.getInteger(request, "type", UserQuestionDict.QuestionType.PROPERTY_QUESTION);
        String content = ParamUtils.getString(request, "content", "无");

        UserQuestion userQuestion = new UserQuestion();
        userQuestion.setType(type);
        userQuestion.setContent(content);
        userQuestion.setUserId(userId);
        userQuestion.setStatus(UserQuestionDict.QuestionStatus.ORIGIN);
        userQuestion.settRoomFId(roomId);

        List<RequestFileEntity> picInfoList = CommRequestFileParser.parseRequsetFileInfoDimLike(request, "picInfo");
        userQuestionService.addUserQuestion(userQuestion, picInfoList);
        return new JsonResponse();
    }
}
