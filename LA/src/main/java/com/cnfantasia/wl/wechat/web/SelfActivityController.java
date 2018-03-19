package com.cnfantasia.wl.wechat.web;

import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.selfActivity.entity.SeftActivityWithDetail;
import com.cnfantasia.server.api.selfActivity.service.SelfActivityService;
import com.cnfantasia.server.business.pub.session.SessionValueManager;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domainbase.dredgeProduct.service.IDredgeProductBaseService;
import com.cnfantasia.server.domainbase.ebuyProduct.service.IEbuyProductBaseService;
import com.cnfantasia.server.domainbase.selfActivity.service.ISelfActivityBaseService;
import com.cnfantasia.server.domainbase.selfActivityDetail.entity.SelfActivityDetail;
import com.cnfantasia.server.ms.pub.constant.PathConstants;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/selfActivity")
public class SelfActivityController extends BaseController {
	private Log logger = LogFactory.getLog(getClass());

	@Resource
	private SelfActivityService selfActivityService;
	@Resource
	private ISelfActivityBaseService selfActivityBaseService;
	@Resource
	IEbuyProductBaseService ebuyProductBaseService;
	@Resource
	IDredgeProductBaseService dredgeProductBaseService;
	
	@Resource
	private IFileServerService fileServerService;

	@RequestMapping(value = "/view.html", method = RequestMethod.GET)
    public ModelAndView view(HttpServletRequest request, BigInteger id) throws ServletException, IOException {
		SeftActivityWithDetail sa = selfActivityService.qrySelfActivityWithDetaiById(id);
		List<Object> jumpParamNameList = new ArrayList<Object>(sa.getSadList().size());
		for(int i = 0; i < sa.getSadList().size(); i++){
			SelfActivityDetail selfActivityDetail = sa.getSadList().get(i);
			if(selfActivityDetail.getJumpType() == 0){
				jumpParamNameList.add(null);
			}else if(selfActivityDetail.getJumpType() == 1){
				BigInteger productId = new BigInteger(selfActivityDetail.getJumpParam());
				jumpParamNameList.add(ebuyProductBaseService.getEbuyProductBySeqId(productId));
			}else if(selfActivityDetail.getJumpType() == 2){
				BigInteger dredgeProductId = new BigInteger(selfActivityDetail.getJumpParam());
				jumpParamNameList.add(dredgeProductBaseService.getDredgeProductBySeqId(dredgeProductId));
			}else if(selfActivityDetail.getJumpType() == 3){
				jumpParamNameList.add(selfActivityDetail.getJumpParam());
			}

			selfActivityDetail.setPicUrl(fileServerService.getAccessUrl(PathConstants.SELF_ACTIVITY_PIC.replaceFirst("/", ""), selfActivityDetail)
					+ selfActivityDetail.getPicUrl());
		}

		SessionValueManager.putRecommendUserId(request);
		logger.info("get phone start..." + ParamUtils.getString(request,"phone", null));
		SessionValueManager.putRecommendUserPhone(request);//同时支持推荐人手机号推荐 added by wenfq 2017-11-28
		logger.info("get phone end..." + request.getSession().getAttribute(SessionValueManager.Recommend_User_Phone));

		ModelAndView modelAndView = new ModelAndView("/selfActivity/selfActivityView");
		modelAndView.addObject("sa", sa);
		modelAndView.addObject("paramList", jumpParamNameList);
		return modelAndView;
	}
}
