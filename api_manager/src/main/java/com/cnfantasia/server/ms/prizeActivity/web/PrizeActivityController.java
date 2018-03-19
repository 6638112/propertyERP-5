package com.cnfantasia.server.ms.prizeActivity.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.commbusi.prizeActivity.constant.PrizeActivityDict;
import com.cnfantasia.server.commbusi.prizeActivity.entity.MsPrizeActivityEntity;
import com.cnfantasia.server.commbusi.prizeActivity.entity.MsPrizeGiftEntity;
import com.cnfantasia.server.commbusi.prizeActivity.entity.MsPrizeOptionEntity;
import com.cnfantasia.server.commbusi.prizeActivity.entity.MsPrizeOptionForList;
import com.cnfantasia.server.commbusi.prizeActivity.service.IPrizeActivityService;
import com.cnfantasia.server.commbusi.prizeActivity.service.IPrizeOptionService;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domainbase.msPrizeActivity.entity.MsPrizeActivity;
import com.cnfantasia.server.domainbase.msPrizeOption.entity.MsPrizeOption;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.pub.session.UserContext;

@Controller
@RequestMapping("/prizeActivity")
public class PrizeActivityController extends BaseController{
	@Resource(type=IPrizeActivityService.class)
	private IPrizeActivityService prizeActService;
	@Resource(type=IPrizeOptionService.class)
	private IPrizeOptionService prizeOptService;                                                                   
	
	/**
	 * 活动列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/activityList.html")
	public ModelAndView activityList(HttpServletRequest request) {
		UserContext.getOperIdBigIntegerMustExist();
		//1.搜集参数
		//2.交互
		handleListOrSearchPrizeActivity(request);
		//3.结果处理
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/prizeActivity/activityList");
		return mav;
	}
	
	/**
	 * 跳转到活动新增页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/activityAdd.html")
	public ModelAndView activityAdd(HttpServletRequest request) {
		UserContext.getOperIdBigIntegerMustExist();
		//1.搜集参数
		//2.交互
		//查询所有可用的奖项列表
		List<MsPrizeOption> optionList = prizeActService.getMsPrizeOptionAvalList(null, null,null);
		//3.结果处理
		request.setAttribute("optionList",optionList);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/prizeActivity/activityAdd");
		return mav;
	}
	
	/**
	 * 跳转到活动编辑页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/activityUpd.html")
	public ModelAndView activityUpd(HttpServletRequest request) {
		UserContext.getOperIdBigIntegerMustExist();
		//1.搜集参数
		BigInteger actId = ParamUtils.getBigInteger(request, "actId", null);
		//2.交互
		//查询所有可用的奖项列表
		MsPrizeActivityEntity prizeActDetail = actId==null?null:prizeActService.getMsPrizeActivityDetail(actId);
		List<MsPrizeOption> optionList = prizeActService.getMsPrizeOptionAvalList(null, null,actId);
		//3.结果处理
		request.setAttribute("detail", prizeActDetail);
		if(prizeActDetail!=null){
			request.setAttribute("shareIconPath", ApplicationContextBothUtil.getAbsolutePath(prizeActDetail.getShareIcon(), SysParamKey.PrizeActivity_Icon_BasePath, prizeActDetail.getLastUpdTime()));
		}
		request.setAttribute("optionList", optionList);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/prizeActivity/activityUpd");
		return mav;
	}
	
	/**
	 * 活动详情页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/activityDetail.html")
	public ModelAndView activityDetail(HttpServletRequest request) {
		UserContext.getOperIdBigIntegerMustExist();
		//1.搜集参数
		BigInteger actId = ParamUtils.getBigInteger(request, "actId", null);
		//2.交互
		MsPrizeActivityEntity prizeActDetail = actId==null?null:prizeActService.getMsPrizeActivityDetail(actId);
		//3.结果处理
		request.setAttribute("detail", prizeActDetail);
		if(prizeActDetail!=null){
			request.setAttribute("shareIconPath", ApplicationContextBothUtil.getAbsolutePath(prizeActDetail.getShareIcon(), SysParamKey.PrizeActivity_Icon_BasePath, prizeActDetail.getLastUpdTime()));
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/prizeActivity/activityDetail");
		return mav;
	}
	
	/**
	 * 奖项列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/optionList.html")
	public ModelAndView optionList(HttpServletRequest request) {
		UserContext.getOperIdBigIntegerMustExist();
		//1.搜集参数
		//2.交互
		handleListOrSearchPrizeOption(request);
		//3.结果处理
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/prizeActivity/optionList");
		return mav;
	}
	
	
	/**
	 * 奖项详情页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/optionDetail.html")
	public ModelAndView optionDetail(HttpServletRequest request) {
		UserContext.getOperIdBigIntegerMustExist();
		//1.搜集参数
		BigInteger optId = ParamUtils.getBigInteger(request, "optId", null);
		//2.交互
		MsPrizeOptionEntity detail = optId==null?null:prizeOptService.getMsPrizeOptionDetail(optId);
		//3.结果处理
		request.setAttribute("detail", detail);
		if(detail!=null){
			request.setAttribute("iconPath", ApplicationContextBothUtil.getAbsolutePath(detail.getIcon(), SysParamKey.PrizeOption_Icon_BasePath, detail.getLastUpdTime()));
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/prizeActivity/optionDetail");
		return mav;
	}
	
	/**
	 * 奖项编辑页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/optionUpd.html")
	public ModelAndView optionUpd(HttpServletRequest request) {
		UserContext.getOperIdBigIntegerMustExist();
		//1.搜集参数
		BigInteger optId = ParamUtils.getBigInteger(request, "optId", null);
		//2.交互
		MsPrizeOptionEntity detail = optId==null?null:prizeOptService.getMsPrizeOptionDetail(optId);
		//3.结果处理
		request.setAttribute("detail", detail);
		if(detail!=null){
			request.setAttribute("iconPath", ApplicationContextBothUtil.getAbsolutePath(detail.getIcon(), SysParamKey.PrizeOption_Icon_BasePath, detail.getLastUpdTime()));
		}
		request.setAttribute("isGlobal", detail.fetchIsGlobal());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/prizeActivity/optionUpd");
		return mav;
	}
	/**
	 * 奖项新增页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/optionAdd.html")
	public ModelAndView optionAdd(HttpServletRequest request) {
		UserContext.getOperIdBigIntegerMustExist();
		//1.搜集参数
		//2.交互
		//3.结果处理
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/prizeActivity/optionAdd");
		return mav;
	}
	
	/**
	 * 奖品列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/giftList.html")
	public ModelAndView giftList(HttpServletRequest request) {
		UserContext.getOperIdBigIntegerMustExist();
		//1.搜集参数
		//2.交互
		handleListOrSearchPrizeGift(request);
		//3.结果处理
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/prizeActivity/giftList");
		return mav;
	}
	
	/**
	 *  按条件查询活动列表
	 * @param request
	 */
	private void handleListOrSearchPrizeActivity(HttpServletRequest request) {
		//分页信息
		PageModel pageModel = null;
		{
			String pageIndexName = new org.displaytag.util.ParamEncoder("row").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);// 页数的参数名 
			int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));// 每页显示的条数 
			int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName))-1);// 当前页    
			pageModel = new PageModel((curPageIndex)*pageSize, pageSize);
		}
		{//查询数据
			String name = ParamUtils.getString(request, "name",null);
			Integer activityStatus = ParamUtils.getInteger(request, "activityStatus", PrizeActivityDict.Qry_ActivityStatus.ALL);
			String startTime = ParamUtils.getString(request, "startTime", null);
			String endTime = ParamUtils.getString(request, "endTime", null);
			List<MsPrizeActivity> priActList = prizeActService.getMsPrizeActivityList(name, activityStatus, startTime, endTime, pageModel);
			request.setAttribute("resList", priActList);
			request.setAttribute("resultSize", pageModel.count);
		}
	}
	/**
	 *  按条件查询奖项列表
	 * @param request
	 */
	private void handleListOrSearchPrizeOption(HttpServletRequest request) {
		//分页信息
		PageModel pageModel = null;
		{
			String pageIndexName = new org.displaytag.util.ParamEncoder("row").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);// 页数的参数名 
			int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));// 每页显示的条数 
			int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName))-1);// 当前页    
			pageModel = new PageModel((curPageIndex)*pageSize, pageSize);
		}
		{//查询数据
			String name = ParamUtils.getString(request, "name", null);
			Integer qryStatus = ParamUtils.getInteger(request, "qryStatus", PrizeActivityDict.Qry_PrizeOptionStatus.ALL);
			Integer useStatus = ParamUtils.getInteger(request, "useStatus", PrizeActivityDict.Qry_PrizeOptionStatus.ALL);
			List<MsPrizeOptionForList> priOptList = prizeOptService.getMsPrizeOptionList(name, qryStatus,useStatus, pageModel);
			request.setAttribute("resList", priOptList);
			request.setAttribute("resultSize", pageModel.count);
		}
	}
	
	/**
	 *  按条件查询奖品列表
	 * @param request
	 */
	private void handleListOrSearchPrizeGift(HttpServletRequest request) {
		//分页信息
		PageModel pageModel = null;
		{
			String pageIndexName = new org.displaytag.util.ParamEncoder("row").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);// 页数的参数名 
//			int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));// 每页显示的条数 
			int pageSize = 5;// 每页显示的条数 
			int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName))-1);// 当前页    
			pageModel = new PageModel((curPageIndex)*pageSize, pageSize);
		}
		{//查询数据
			BigInteger optId = ParamUtils.getBigInteger(request, "optId", null);
			String codeName = ParamUtils.getString(request, "codeName", null);
			String valueCode = ParamUtils.getString(request, "valueCode", null);
			String uqMark = ParamUtils.getString(request, "uqMark", null);
			Integer qryStatus = ParamUtils.getInteger(request, "qryStatus", PrizeActivityDict.Qry_PrizeGiftStatus.ALL);
			List<MsPrizeGiftEntity> resList = prizeOptService.getMsPrizeGiftList(optId, codeName, valueCode, uqMark, qryStatus, pageModel);
			request.setAttribute("optId", optId);
			request.setAttribute("resList", resList);
			request.setAttribute("resultSize", pageModel.count);
		}
	}
	
	/**
	 * 导出模板
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping("/exportPrizeGiftTemplate.html")
	public ModelAndView exportPayBillTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserContext.getOperIdBigIntegerMustExist();
		String filePath = request.getSession().getServletContext().getRealPath("/docs/jfq_prizeOpt_priGift.xls");
		FileInputStream fin = new FileInputStream(new File(filePath));
		HSSFWorkbook wb = new HSSFWorkbook(fin);
		wb.setSheetName(0, "奖品信息");
		String fileName = "PrizeGiftTemplate.xls";
//		fileName = this.encodeFilename(fileName, request);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName);
		//wb.write(response.getOutputStream());
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
		return null;
	}

//	/**
//	 * 设置下载文件中文件的名称
//	 * 
//	 * @param filename
//	 * @param request
//	 * @return
//	 */
//	private String encodeFilename(String filename, HttpServletRequest request) {
//		/**
//		 * 获取客户端浏览器和操作系统信息 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE
//		 * 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)
//		 * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1;
//		 * zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6
//		 */
//		String agent = request.getHeader("USER-AGENT");
//		try {
//			if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {
//				String newFileName = URLEncoder.encode(filename, "UTF-8");
//				newFileName = StringUtils.replace(newFileName, "+", "%20");
//				if (newFileName.length() > 150) {
//					newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");
//					newFileName = StringUtils.replace(newFileName, " ", "%20");
//				}
//				return newFileName;
//			}
//			if ((agent != null) && (-1 != agent.indexOf("Mozilla")))
//				return URLEncoder.encode(filename, "UTF-8");
//
//			return filename;
//		} catch (Exception ex) {
//			return filename;
//		}
//	}
	
	/**
	 * 选择城市页面
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping("/citySelect.html")
	public ModelAndView citySelect(HttpServletRequest request){
		//1.搜集参数
		//2.交互
		//3.结果处理
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/prizeActivity/citySelect");
		return mav;
	}
}
