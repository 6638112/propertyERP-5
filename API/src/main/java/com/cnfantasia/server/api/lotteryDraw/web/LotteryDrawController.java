/**   
* Filename:    PlotpropertyController.java   
* @version:    1.0  
* Create at:   2014年8月13日 上午7:48:00   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月13日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.lotteryDraw.web;

import java.math.BigInteger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.lotteryDraw.entity.LotteryDrawListEntity;
import com.cnfantasia.server.api.lotteryDraw.service.ILotteryDrawService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;

/**
 * @ClassName: LotteryDrawDao
 * @Date: 2016-11-25 14:48
 * @Auther: yanghua
 * @Description:(幸运抽奖)
 */

@RequestMapping("/lotteryDraw")
@Controller
public class LotteryDrawController extends BaseController{
	@Resource
	private ILotteryDrawService lotteryDrawService;

	/**
	 * 幸运抽奖
	 */
	@RequestMapping("/getLotteryDraw.html")
	public ModelAndView getLotteryDraw(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdBigInteger();
		//2.交互
		LotteryDrawListEntity lotteryDrawListEntity = lotteryDrawService.getLotteryDraw(userId);
		//3.结果处理
		modelAndView.setViewName("/lotteryDraw/index");
		modelAndView.addObject("lotteryDrawListEntity",lotteryDrawListEntity);
		modelAndView.addObject("userId", userId);
		return modelAndView;
	}

	/**
	 * 抽奖逻辑处理
	 */
	@RequestMapping("/drawLottery.json")
	@ResponseBody
	public JsonResponse drawLottery(HttpServletRequest request){
		BigInteger userId = ParamUtils.getBigInteger(request, "userId", null);
		//BigInteger userId = new BigInteger("5260130");
		return lotteryDrawService.drawLottery(userId);
	}
}
