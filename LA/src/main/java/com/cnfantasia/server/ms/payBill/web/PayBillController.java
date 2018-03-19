package com.cnfantasia.server.ms.payBill.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;
import com.cnfantasia.server.ms.payBill.constant.PayBillDict;
import com.cnfantasia.server.ms.payBill.entity.PayBillEntity;
import com.cnfantasia.server.ms.payBill.entity.PayBillWithFeeDetailEntity;
import com.cnfantasia.server.ms.payBill.service.IPayBillService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.pub.utils.DateUtil;

@Controller
@RequestMapping("/payBill")
public class PayBillController extends BaseController{
	private Log logger = LogFactory.getLog(getClass());
	
	private IPayBillService payBillService;

	public void setPayBillService(IPayBillService payBillService) {
		this.payBillService = payBillService;
	}

	/**
	 * 物业账单列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/list.html")
	public ModelAndView list(HttpServletRequest request) {

		Map<String, Object> paramMap = new HashMap();
		paramMap.put("omsUserId", UserContext.getCurrUser().getId());

		handleListOrSearch(request, paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/payBill/payBillList");
		return modelAndView;
	}

	/**
	 * 编辑
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/edit.html")
	public ModelAndView edit(HttpServletRequest request) {
		String id = request.getParameter("id");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/payBill/payBillEdit");
		return modelAndView;
	}

	/**
	 * 保存
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/save.html")
	public ModelAndView save(HttpServletRequest request) {


		request.setAttribute("result", "账号保存成功");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/payBill/payBillOprtSuccess");
		return modelAndView;
	}

	/**
	 * 查看明细
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/viewDetail.html")
	public ModelAndView viewDetail(HttpServletRequest request) {
		String id = request.getParameter("id");
		PayBillWithFeeDetailEntity payBillImportedEntity = payBillService.getPayBillForDetail(new BigInteger(id));
		request.setAttribute("payBill", payBillImportedEntity);

		request.setAttribute("payBillWithPayRecored", payBillService.select_payBill_with_payRecord(id));

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/payBill/payBillDetail");
		return modelAndView;
	}

	/**
	 * 导入账单
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/importPayBill.html")
	public ModelAndView importPayBill(HttpServletRequest request) throws Exception {
		//		Uploader uploader = new Uploader(request);
		//		uploader.upload();
		//RequestFileEntity requestFileEntity = CommRequestFileParser.parseRequsetFileInfo(request, "excelFile");
		String result = "导入成功";
		if (request instanceof MultipartHttpServletRequest) {
			// 转型为Spring的MultipartHttpRequest(重点的所在)
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 根据前台的name名称得到上传的文件
			MultipartFile uploadExcelfile = multipartRequest.getFile("excelFile");
			HSSFWorkbook wb = new HSSFWorkbook(uploadExcelfile.getInputStream());
			HSSFSheet sheet = wb.getSheetAt(0);
			int startRow = 4; //从第5行（含）开始导入数据
			int feeDetailColumnStart = 6;//从第6列（含）开始，
			int feeDetailColumnEnd = 23;//从第23列（含）结束，
			int feeDetailInterval = 3;//每3列是一份缴费明细

			List<PayBillWithFeeDetailEntity> payBills = new ArrayList<PayBillWithFeeDetailEntity>();
			for (int i = startRow; i < sheet.getLastRowNum() + 1; i++) {
				PayBillWithFeeDetailEntity payBill = new PayBillWithFeeDetailEntity();
				payBill.setIsPay(PayBillDict.isPayed_no);
				Date payBillMonth = sheet.getRow(i).getCell(0).getDateCellValue();
				if (payBillMonth == null) {//处理空行的情况，有可能用户没有删除空白行
					continue;
				}
				payBill.setMonth(DateUtil.formatSecond.format(payBillMonth));
				payBill.setGroupBuildingName("" + sheet.getRow(i).getCell(1).getStringCellValue().trim());//小区名称
				if (sheet.getRow(i).getCell(2).getCellType() == HSSFCell.CELL_TYPE_STRING) {//字符串
					payBill.setBuildingName(sheet.getRow(i).getCell(2).getStringCellValue());
				} else {//数字
					payBill.setBuildingName("" + (int) sheet.getRow(i).getCell(2).getNumericCellValue());
				}

				if (sheet.getRow(i).getCell(3).getCellType() == HSSFCell.CELL_TYPE_STRING) {
					payBill.setRealRoomUnitName(sheet.getRow(i).getCell(3).getStringCellValue());
				} else {
					payBill.setRealRoomUnitName("" + (int) sheet.getRow(i).getCell(3).getNumericCellValue());
				}

				if (sheet.getRow(i).getCell(4).getCellType() == HSSFCell.CELL_TYPE_STRING) {
					payBill.setRealRoomNum(sheet.getRow(i).getCell(4).getStringCellValue());
				} else {
					payBill.setRealRoomNum("" + (int) sheet.getRow(i).getCell(4).getNumericCellValue());
				}

				payBill.setPropertyProprietorName(sheet.getRow(i).getCell(5).getStringCellValue().replace(" ", ""));//业主名称，要去掉所有空格
				for (int j = feeDetailColumnStart; j <= feeDetailColumnEnd; j += feeDetailInterval) {//每3列是一份缴费明细
					PropertyFeeDetail pfd = new PropertyFeeDetail();
					int type = (j - feeDetailColumnStart) / feeDetailInterval + 1;
					pfd.setType(type);
					if (type == 6) {//最后一个用“其它费用”代替 枚举代号是9
						pfd.setType(PayBillDict.PropertyFeeDetailDict.FeeType_Other);
					}

					switch (type) {
					case 1:
						pfd.setName("管理费");
						break;
					case 2:
						pfd.setName("主体金");
						break;
					case 3:
						pfd.setName("垃圾处理费");
						break;
					case 4:
						pfd.setName("水费");
						break;
					case 5:
						pfd.setName("污水处理费");
						break;
					case 6:
						pfd.setName("其它费用");
						break;

					default:
						break;
					}

					if (sheet.getRow(i).getCell(j) == null || sheet.getRow(i).getCell(j).getNumericCellValue() <= 0) {
						//总价 为空或0时，跳过不需要保存
						continue;
					} else {
						pfd.setTotalPrice(sheet.getRow(i).getCell(j).getNumericCellValue() * 100);//总价
					}

					if (sheet.getRow(i).getCell(j + 1) == null || sheet.getRow(i).getCell(j + 1).getNumericCellValue() <= 0) {
						pfd.setSignalPrice(null);//单价
					} else {
//						pfd.setSignalPrice((long) (sheet.getRow(i).getCell(j + 1).getNumericCellValue() * 100));//单价
					}

					if (sheet.getRow(i).getCell(j + 2) == null || sheet.getRow(i).getCell(j + 2).getNumericCellValue() <= 0) {
						pfd.setPriceUnitValue(null);//用量
					} else {
						pfd.setPriceUnitValue((long) sheet.getRow(i).getCell(j + 2).getNumericCellValue());//用量
						pfd.setPriceUnitName(sheet.getRow(3).getCell(j + 2).getStringCellValue());//计量单位
					}

					payBill.addPropertyFeeDetail(pfd);
				}
				payBill.setAmount((long) (sheet.getRow(i).getCell(feeDetailColumnEnd + 1).getNumericCellValue() * 100));
				payBills.add(payBill);
			}

			result = payBillService.saveImportPayBill(payBills);
		}

		request.setAttribute("result", result);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/payBill/payBillOprtSuccess");
		return modelAndView;
	}

	/**
	 * 导出
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/exportPayBill.html")
	public ModelAndView exportPayBill(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HashMap<String, Object> paramMap = (HashMap<String, Object>) request.getSession().getAttribute("payBillListQueryParamMap");
		int resultSize = payBillService.getPayBill_byUserId_forCount(paramMap);
		List<PayBillEntity> searchRestList = payBillService.getPayBillList_byUserId_forPage(0, resultSize, paramMap);
		List<BigInteger> payBillIdList = new ArrayList<BigInteger>();
		for (int i = 0; i < searchRestList.size(); i++) {
			payBillIdList.add(searchRestList.get(i).getId());
		}

		List<PayBillWithFeeDetailEntity> payBillImportedEntyList = payBillService.getExportedPayBill(payBillIdList);
		String resultMsg = payBillImportedEntyList.size() == 0 ? "没有可导出的数据" : "导出成功";

		if (payBillImportedEntyList.size() > 0) {
			String filePath = request.getSession().getServletContext().getRealPath("/docs/jfq_payBill_bank_statement.xls");
			FileInputStream fin = new FileInputStream(new File(filePath));
			HSSFWorkbook wb = new HSSFWorkbook(fin);
			HSSFSheet sheet = wb.getSheetAt(0);
			for (int i = 0; i < payBillImportedEntyList.size(); i++) {
				PayBillWithFeeDetailEntity pb = payBillImportedEntyList.get(i);
				HSSFRow row = sheet.getRow(i + 4);
				if (row == null) {
					row = sheet.createRow(i + 4);
				}
				row.createCell(0).setCellValue(pb.getMonth()); // 月份	小区	原合同号	楼栋	单元	房号	业主姓名

				row.createCell(1).setCellValue(pb.getGroupBuildingName());//小区	
				row.createCell(2).setCellValue(pb.getContractNum());//原合同号	
				row.createCell(3).setCellValue(pb.getBuildingName());//楼栋
				row.createCell(4).setCellValue(pb.getRealRoomUnitName());//单元	
				row.createCell(5).setCellValue(pb.getRealRoomNum());//房号	
				row.createCell(6).setCellValue(pb.getPropertyProprietorName());//业主姓名

				for (int j = 0; j < pb.getPropertyFeeDetails().size(); j++) {
					PropertyFeeDetail detail = pb.getPropertyFeeDetails().get(j);
					if (j == 0) {//管理费
						if (detail.getTotalPrice() != null)
							row.createCell(7).setCellValue(detail.getTotalPrice() / 100.0);//分项总价
						if (detail.getSignalPrice() != null)
							row.createCell(8).setCellValue(detail.getSignalPrice() / 100.0);//分项单价
						if (detail.getPriceUnitValue() != null)
							row.createCell(9).setCellValue(detail.getPriceUnitValue());//分项用量
						if (pb.getDiscount() != null)
							row.createCell(10).setCellValue(pb.getDiscount());//折扣
						if (pb.getSuccPayAmount() != null)
							row.createCell(11).setCellValue((pb.getAmount() - pb.getSuccPayAmount()) / 100.0);//优惠金额
					} else {
						int offset = detail.getType() - 1;
						if (detail.getType() == PayBillDict.PropertyFeeDetailDict.FeeType_Other)
							offset = 6 - 1;//FeeType_Other用6来替代

						if (detail.getTotalPrice() != null)
							row.createCell(9 + offset * 3).setCellValue(detail.getTotalPrice() / 100.0);//分项总价
						if (detail.getSignalPrice() != null)
							row.createCell(10 + offset * 3).setCellValue(detail.getSignalPrice() / 100.0);//分项单价
						if (detail.getPriceUnitValue() != null)
							row.createCell(11 + offset * 3).setCellValue(detail.getPriceUnitValue());//分项用量
					}
				}

				//第26列是“用户缴费”列
				if (pb.getSuccPayAmount() != null) {
					row.createCell(27).setCellValue(pb.getSuccPayAmount() / 100.0);
					if (pb.getSuccPayAmount() != null)
						row.createCell(28).setCellValue((pb.getAmount() - pb.getSuccPayAmount()) / 100.0);
				}
				row.createCell(29).setCellValue(pb.getAmount() / 100.0);
				if (pb.getIsPay() != null && pb.getIsPay() == 1) {
					row.createCell(30).setCellValue(pb.getSys0UpdTime());
				}
				row.createCell(31).setCellValue(pb.getPayPeriodStart());
				row.createCell(32).setCellValue(pb.getPayPeriodEnd());
			}
			// 生成提示信息
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String fileName = "对账报表" + format.format(new Date());
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setHeader("content-disposition", "attachment;filename=" + fileName + ".xls");
			wb.write(response.getOutputStream());
		}

		request.setAttribute("result", resultMsg);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/payBill/payBillOprtSuccess");
		return modelAndView;
	}

	/**
	 * 查找
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/search.html")
	public ModelAndView search(HttpServletRequest request) {
		String gbName = request.getParameter("gbName");
		String contractNum = request.getParameter("contractNum");
		String ppName = request.getParameter("ppName");
		String pbMonth = request.getParameter("pbMonth");
		String isPay = request.getParameter("isPay");
		String paymentWay = request.getParameter("paymentWay");
		String payTimeStart = request.getParameter("date01");
		String payTimeEnd = request.getParameter("date02");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("omsUserId", UserContext.getCurrUser().getId());
		if (!"all".equals(gbName)) {
			paramMap.put("gbName", gbName);
		}
		paramMap.put("contractNum", contractNum);
		paramMap.put("ppName", ppName);
		paramMap.put("pbMonth", pbMonth);
		if (!"-1".equals(isPay)) {
			paramMap.put("isPay", isPay);
		}
		if ((!"-1".equals(paymentWay))) {
			paramMap.put("paymentWay", paymentWay);
		}

		paramMap.put("payTimeStart", payTimeStart);
		paramMap.put("payTimeEnd", payTimeEnd);

		handleListOrSearch(request, paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/payBill/payBillList");
		return modelAndView;
	}

	/**
	 * 手工标记
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/markByManual.html")
	@ResponseBody
	public String markByManual(HttpServletRequest request) {
		String id = request.getParameter("id");

		int updateCount = payBillService.markByManual(id);
		String resultInfo = "标记失败";
		if (updateCount == 1) {
			resultInfo = "手工标记成功";
		}

		return resultInfo;
	}

	/**
	 * 统一处理List和Search请求
	 * 
	 * @param request
	 * @param paramMap
	 *            请求参数
	 */
	private void handleListOrSearch(HttpServletRequest request, Map<String, Object> paramMap) {
		int resultSize = payBillService.getPayBill_byUserId_forCount(paramMap);
		request.setAttribute("resultSize", resultSize);

		// 页数的参数名 
		String pageIndexName = new org.displaytag.util.ParamEncoder("row").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = 10;
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);

		//存储查询条件，导出数据时要用到，见exportPayBill()方法
		request.getSession().setAttribute("payBillListQueryParamMap", ((HashMap<String, Object>) paramMap).clone());
		List<PayBillEntity> searchRestList = payBillService.getPayBillList_byUserId_forPage(curPageIndex, pageSize, paramMap);

		request.setAttribute("resList", searchRestList);
	}
}
