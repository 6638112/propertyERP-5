package com.cnfantasia.server.ms.ebuyProductSettle.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.server.api.redpoint.constant.RedpointConstant;
import com.cnfantasia.server.api.redpoint.constant.RedpointDict;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.dao.IEbuyDeliveryOrderBaseDao;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity.EbuyDeliveryOrder;
import com.cnfantasia.server.domainbase.ebuyProductSettleApplyLog.dao.IEbuyProductSettleApplyLogBaseDao;
import com.cnfantasia.server.domainbase.ebuyProductSettleApplyLog.entity.EbuyProductSettleApplyLog;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.service.IEbuySupplyMerchantBaseService;
import com.cnfantasia.server.domainbase.redpointContent.entity.RedpointContent;
import com.cnfantasia.server.domainbase.redpointContent.service.IRedpointContentBaseService;
import com.cnfantasia.server.domainbase.revenueApply.dao.IRevenueApplyBaseDao;
import com.cnfantasia.server.domainbase.revenueApply.entity.RevenueApply;
import com.cnfantasia.server.ms.ebuyProductSettle.constants.PageType;
import com.cnfantasia.server.ms.ebuyProductSettle.dao.IEbuyProductSettleDao;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.EbuyProductSettleAdminExportDto;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.EbuyProductSettleApplyExportDto;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.EbuyProductSettleApplyListDto;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.EbuyProductSettleApplyListExportDto;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.EbuyProductSettleDetailAdminDto;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.EbuyProductSettleListDetailAdminDto;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.RevenueAllTotalAmount;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.RevenueApplyDto;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.RevenueMoney;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.SettleApplyDialogInfoDto;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
import com.cnfantasia.server.ms.revenue.util.RevenueTkNoGenerator;

/**
 * 购销供应商结算service
 *
 * @author liyulin
 * @version 1.0 2016年6月7日 下午4:53:25
 */
public class EbuyProductSettleService implements IEbuyProductSettleService {
	private final static Log logger = LogFactory.getLog(EbuyProductSettleService.class);
	private IEbuyProductSettleDao ebuyProductSettleDao;
	private IRevenueApplyBaseDao revenueApplyBaseDao;
	private IEbuyProductSettleApplyLogBaseDao ebuyProductSettleApplyLogBaseDao;
	private IEbuyDeliveryOrderBaseDao ebuyDeliveryOrderBaseDao;
	private IUuidManager uuidManager;
	private IEbuySupplyMerchantBaseService ebuySupplyMerchantBaseService;
	private IRedpointContentBaseService redpointContentBaseService;

	public void setRedpointContentBaseService(IRedpointContentBaseService redpointContentBaseService) {
		this.redpointContentBaseService = redpointContentBaseService;
	}

	public void setEbuySupplyMerchantBaseService(IEbuySupplyMerchantBaseService ebuySupplyMerchantBaseService) {
		this.ebuySupplyMerchantBaseService = ebuySupplyMerchantBaseService;
	}

	public void setEbuyProductSettleDao(IEbuyProductSettleDao ebuyProductSettleDao) {
		this.ebuyProductSettleDao = ebuyProductSettleDao;
	}

	public void setRevenueApplyBaseDao(IRevenueApplyBaseDao revenueApplyBaseDao) {
		this.revenueApplyBaseDao = revenueApplyBaseDao;
	}

	public void setEbuyProductSettleApplyLogBaseDao(IEbuyProductSettleApplyLogBaseDao ebuyProductSettleApplyLogBaseDao) {
		this.ebuyProductSettleApplyLogBaseDao = ebuyProductSettleApplyLogBaseDao;
	}

	public void setEbuyDeliveryOrderBaseDao(IEbuyDeliveryOrderBaseDao ebuyDeliveryOrderBaseDao) {
		this.ebuyDeliveryOrderBaseDao = ebuyDeliveryOrderBaseDao;
	}

	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}

	/**
	 * 查询结算记录
	 *
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<RevenueApplyDto> selectRevenueForList(Map<String, Object> paramMap){
		return ebuyProductSettleDao.selectRevenueForList(paramMap);
	}

	/**
	 * 查询结算记录条数
	 *
	 * @param paramMap
	 * @return
	 */
	@Override
	public int selectRevenueForCount(Map<String, Object> paramMap){
		return ebuyProductSettleDao.selectRevenueForCount(paramMap);
	}

	/**
	 * 查询申请页面列表数据
	 *
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<EbuyProductSettleApplyListDto> selectRevenueApplyForList(Map<String, Object> paramMap, int revenueType){
		return ebuyProductSettleDao.selectRevenueApplyForList(paramMap, revenueType);
	}

	/**
	 * 查询申请页面数据条数
	 *
	 * @param paramMap
	 * @return
	 */
	@Override
	public int selectRevenueApplyForCount(Map<String, Object> paramMap){
		return ebuyProductSettleDao.selectRevenueApplyForCount(paramMap);
	}

	/**
	 * 查询申请页面可结算金额
	 *
	 * @param paramMap
	 * @return
	 */
	@Override
	public BigDecimal selectRevenueApplyForTotalAmount(Map<String, Object> paramMap, Integer revenueType){
		return ebuyProductSettleDao.selectRevenueApplyForTotalAmount(paramMap, revenueType);
	}

	/**
	 * 结算申请弹出框信息
	 * @param paramMap
	 * @return
	 */
	@Override
	public SettleApplyDialogInfoDto selectRevenueApplyInfoForDialog(Map<String, Object> paramMap){
		return ebuyProductSettleDao.selectRevenueApplyInfoForDialog(paramMap);
	}

	/**
	 *
	 * @param userId
	 * @param supplyMerchantId
	 * @param mobile
	 * @param note
	 * @param channelSub 渠道（运营平台9，商户端20）
	 * @param userId null则取session
	 * @return 申请单ID
	 */
	@Override
	@Transactional
	public BigInteger applyRevenue(BigInteger userId, BigInteger supplyMerchantId, String mobile, String note, Integer channelSub){
		int affectedCount = 0;
		// 1、将t_revenue_apply表之前申请的记录标f_sys0_del_state=1
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("supplyMerchantId", supplyMerchantId);
		List<RevenueApply> revenueApplyDeletes = ebuyProductSettleDao.selectRevenueApplyForDelete(paramMap);

		EbuySupplyMerchant merchant = ebuySupplyMerchantBaseService.getEbuySupplyMerchantBySeqId(supplyMerchantId);
		{
			// 修复“revenueRate=null时，报空指针”异常bug
			if(merchant.getRevenueRate()==null){
				merchant.setRevenueRate(0.0);
			}
		}

		if(revenueApplyDeletes != null && revenueApplyDeletes.size()>0){
			affectedCount += revenueApplyBaseDao.updateRevenueApplyBatch(revenueApplyDeletes);
		}

		String now = DateUtils.getCurrentDate();
		BigInteger applyAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_revenue_apply);
		RevenueApply toAddRevenueApply = new RevenueApply();
		// 2、t_revenue_apply
		{   //新增提款记录

			String applyNo = RevenueTkNoGenerator.getOrderNo(applyAddId);//设置提款单号
			toAddRevenueApply.setApplyNo(applyNo);
			toAddRevenueApply.setApplyTime(now);
			toAddRevenueApply.setApplyUserId(userId);
			toAddRevenueApply.setGoalEndTime(now);
			toAddRevenueApply.setGoalStartTime(null);
			toAddRevenueApply.setGoalType(RevenueDict.RevenueProject.DirectPurchase);
			toAddRevenueApply.setChannel(channelSub);
			toAddRevenueApply.setId(applyAddId);
			toAddRevenueApply.setMiniRoleId(supplyMerchantId);
			toAddRevenueApply.setMiniRoleType(RevenueDict.MiniRoleType.DirectPurchase);
			toAddRevenueApply.setOptType(RevenueDict.TkOptType.User);
			toAddRevenueApply.setTkStatus(RevenueDict.TkStatus.Doing);
			toAddRevenueApply.setTkSuccTime(null);
			toAddRevenueApply.setRevenueType(merchant.getRevenueType());
			toAddRevenueApply.setRevenueRate(merchant.getRevenueRate());
			{
				// TODO:
				RevenueMoney revenueMoney = getTotalRevenueMoney(merchant.getId(), merchant.getRevenueType(), merchant.getRevenueRate());
				toAddRevenueApply.setTotalAmount(revenueMoney.getTotalAmount().doubleValue());
				toAddRevenueApply.setAmountPtbt(revenueMoney.getAmountPtbt().doubleValue());
				toAddRevenueApply.setAmountUsrReal(revenueMoney.getAmountUsrReal().doubleValue());
			}
			toAddRevenueApply.setRoleName("");
			{
				paramMap.clear();
				paramMap.put("merchantId", supplyMerchantId);
				SettleApplyDialogInfoDto settleApplyDialogInfoDto = ebuyProductSettleDao.selectRevenueApplyInfoForDialog(paramMap);
				toAddRevenueApply.setRoleName(settleApplyDialogInfoDto.getMerchantName());
				toAddRevenueApply.setbBankName(settleApplyDialogInfoDto.getBankName());
				toAddRevenueApply.setBankBranch(settleApplyDialogInfoDto.getBankBranch());
				toAddRevenueApply.setBankCity(settleApplyDialogInfoDto.getBankCity());
				toAddRevenueApply.setBankProvince(settleApplyDialogInfoDto.getBankProvince());
				toAddRevenueApply.setAccountName(settleApplyDialogInfoDto.getAccountName());
				toAddRevenueApply.setbBankNo(settleApplyDialogInfoDto.getAccountBank());
				toAddRevenueApply.setbConnectName(settleApplyDialogInfoDto.getLinkName());
				toAddRevenueApply.setbPhone((mobile != null) ? mobile : settleApplyDialogInfoDto.getTelphone());
				toAddRevenueApply.setApplyNote(note);
				toAddRevenueApply.setAuditStatus(1);
			}
			toAddRevenueApply.setSys0AddTime(now);
			toAddRevenueApply.settEbuySupplyMerchantFk(supplyMerchantId);
			toAddRevenueApply.setVisibleType(RevenueDict.VisibleType.UserVisible);

			affectedCount += revenueApplyBaseDao.insertRevenueApply(toAddRevenueApply);
		}

		// 3、插入t_ebuy_product_settle_apply_log、标记t_ebuy_delivery_order==>f_settle_status=2
		List<EbuyProductSettleApplyLog> ebuyProductSettleApplyLogs = new ArrayList<EbuyProductSettleApplyLog>();
		List<EbuyDeliveryOrder> ebuyDeliveryOrders = new ArrayList<EbuyDeliveryOrder>();

		paramMap.clear();
		paramMap.put("supplyMerchantId", supplyMerchantId);
		paramMap.put("pageType", PageType.APPLY_LIST);
		// TODO:
		List<EbuyProductSettleApplyListDto> ebuyProductSettleApplyListDtos = ebuyProductSettleDao.selectRevenueApplyForList(paramMap, merchant.getRevenueType());
		List<BigInteger> applyLogIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_product_settle_apply_log, ebuyProductSettleApplyListDtos.size());
		for(int i=0, size=ebuyProductSettleApplyListDtos.size(); i<size; i++){
			{
				EbuyProductSettleApplyLog ebuyProductSettleApplyLog = new EbuyProductSettleApplyLog();
				ebuyProductSettleApplyLog.setId(applyLogIds.get(i));
				ebuyProductSettleApplyLog.settRevenueApplyFId(applyAddId);
				ebuyProductSettleApplyLog.settEbuyDeliveryOrderFId(ebuyProductSettleApplyListDtos.get(i).getDelieveOrderId());
				ebuyProductSettleApplyLog.setAddTime(now);
				ebuyProductSettleApplyLogs.add(ebuyProductSettleApplyLog);
			}
			{
				EbuyDeliveryOrder ebuyDeliveryOrder = new EbuyDeliveryOrder();
				ebuyDeliveryOrder.setId(ebuyProductSettleApplyListDtos.get(i).getDelieveOrderId());
				ebuyDeliveryOrder.setSettleStatus(2);
				ebuyDeliveryOrders.add(ebuyDeliveryOrder);
			}
		}
		affectedCount += ebuyDeliveryOrderBaseDao.updateEbuyDeliveryOrderBatch(ebuyDeliveryOrders);
		affectedCount += ebuyProductSettleApplyLogBaseDao.insertEbuyProductSettleApplyLogBatch(ebuyProductSettleApplyLogs);

		//添加红点
		addSettleStatusChangeRedPiont(supplyMerchantId);
		return affectedCount > 0 ? applyAddId : null;
	}

	/**
	 * 结算申请页面列表导出
	 *
	 * @param paramMap ==>supplyMerchantIds、orderNo、payTimeStart、payTimeEnd
	 * @return
	 */
	@Override
	public HSSFWorkbook exportRevenueApplyList(Map<String, Object> paramMap){
		List<EbuyProductSettleApplyListExportDto> applyExportList = ebuyProductSettleDao.selectForexportRevenueApplyList(paramMap);

		String[] sheetTitle = { "订单号", "支付时间", "供应商名称", "商品名称", "购买数量", "商品结算单价", "商品结算金额", "商品结算小计", "运费", "退款金额", "订单总金额", "结算金额", "收货人", "收货人联系方式", "收货地址" };
		int[] cellWidth = {8000, 7500, 9000, 11000, 2500, 3500, 3500, 3500, 2000, 3000, 3500, 3500, 3000, 5000, 16000};
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("供应商结算明细");
		HSSFFont font = wb.createFont();
		font.setFontName("微软雅黑");
		// 创建单元格样式
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 设置字体
		style.setFont(font);
		// 创建Excel的sheet的一行
		HSSFRow row = sheet.createRow(0);
		// 设定行的高度
		row.setHeight((short)300);
		// title row
		for (int i = 0; i < sheetTitle.length; i++) {
			// 设置excel每列宽度
			sheet.setColumnWidth(i, cellWidth[i]);
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			cell.setCellValue(sheetTitle[i]);
		}
		// content row
		if(applyExportList!=null && applyExportList.size()>0){
			DecimalFormat df = new DecimalFormat("0.00");
			int lineStart = 1, lineEnd = 0;
			for(int i=0, size=applyExportList.size(); i<size; i++){
				EbuyProductSettleApplyListExportDto bean = applyExportList.get(i);
				row = sheet.createRow((i+1));
				row.setHeight((short)400);
				for(int k=0, cellSize=15; k<cellSize; k++){
					sheet.setColumnWidth(k, cellWidth[k]);
					HSSFCell cell = row.createCell(k);
					cell.setCellStyle(style);
					switch (k) {
						case 0:
							cell.setCellValue(bean.getOrderNo());
							break;
						case 1:
							cell.setCellValue(bean.getPayTime());
							break;
						case 2:
							cell.setCellValue(bean.getMerchant());
							break;
						case 3:
							cell.setCellValue(bean.getProductName());
							break;
						case 4:
							cell.setCellValue(bean.getBuyNum());
							break;
						case 5:
							cell.setCellValue(df.format(bean.getPurchasePrice().doubleValue()));
							break;
						case 6:
							cell.setCellValue(df.format(bean.getProductAmount().doubleValue()));
							break;
						case 7:
							cell.setCellValue(df.format(bean.getDeliveryProductAmount().doubleValue()));
							break;
						case 8:
							cell.setCellValue(df.format(bean.getDeliveryFee().doubleValue()));
							break;
						case 9:
							cell.setCellValue(df.format(bean.getRefundAmount().doubleValue()));
							break;
						case 10:
							cell.setCellValue(df.format(bean.getDeliveryAmount().doubleValue()));
							break;
						case 11:
							cell.setCellValue(df.format(bean.getRevenueAmount().doubleValue()));
							break;
						case 12:
							cell.setCellValue(bean.getReceiver());
							break;
						case 13:
							cell.setCellValue(bean.getReceiverMobile());
							break;
						case 14:
							cell.setCellValue(bean.getAddress());
							break;

						default:
							cell.setCellValue("");
					}
				}
				if(i<size-1){
					if(bean.getDelieveOrderId().intValue() == applyExportList.get(i+1).getDelieveOrderId().intValue()){
						lineEnd++;
					} else {
						// 最后一行
						lineEnd++;
						mergeCellForRevenueApplyList(sheet, lineStart, lineEnd, 7, 14);
						lineStart = lineEnd+1;
					}
				} else {
					// 最后一行
					lineEnd++;
					mergeCellForRevenueApplyList(sheet, lineStart, lineEnd, 7, 14);
				}
			}
		}
		return wb;
	}

	/**
	 * 结算申请页面列表导出表合并单元格
	 *
	 * @param sheet
	 * @param lineStart
	 * @param lineEnd
	 */
	@SuppressWarnings("deprecation")
	private void mergeCellForRevenueApplyList(HSSFSheet sheet, int lineStart, int lineEnd, int colStart, int colEnd){
		for(int i=colStart; i<=colEnd; i++){
			// 7-14依次对应：商品结算小计、运费、退款金额、订单总金额、结算金额、收货人、收货人联系方式、收货地址
			CellRangeAddress region=new CellRangeAddress(lineStart, lineEnd, i, i);
			sheet.addMergedRegion(region);
		}
	}

	/**
	 * 结算审核
	 *
	 * @param revenueApplyId
	 * @param auditStatus
	 * @param handlerNote
	 * @param userId
	 * @return
	 */
	@Transactional
	public boolean revenueApplyHandler(BigInteger revenueApplyId, Integer auditStatus, String handlerNote, BigInteger userId){
		int effectedCount = 0;
		// t_revenue_apply
		{
			String now = DateUtils.getCurrentDate();

			RevenueApply revenueApply = new RevenueApply();
			revenueApply.setId(revenueApplyId);
			revenueApply.setAuditStatus(auditStatus);
			revenueApply.setHandlerNote(handlerNote);
			revenueApply.setAuditTime(now);
			revenueApply.setSys0UpdUser(userId);
			revenueApply.setSys0UpdTime(now);

			effectedCount = revenueApplyBaseDao.updateRevenueApply(revenueApply);
		}

		// 更新t_ebuy_delivery_order表f_settle_status
		{
			Integer settleStatus = auditStatus==3?4:3;

			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tRevenueApplyFId", revenueApplyId);
			List<EbuyProductSettleApplyLog> ebuyProductSettleApplyLogList = ebuyProductSettleApplyLogBaseDao.selectEbuyProductSettleApplyLogByCondition(paramMap, true);
			List<EbuyDeliveryOrder> ebuyDeliveryOrderList = new ArrayList<EbuyDeliveryOrder>();
			for (EbuyProductSettleApplyLog ebuyProductSettleApplyLog:ebuyProductSettleApplyLogList) {
				EbuyDeliveryOrder ebuyDeliveryOrder = new EbuyDeliveryOrder();
				ebuyDeliveryOrder.setId(ebuyProductSettleApplyLog.gettEbuyDeliveryOrderFId());
				ebuyDeliveryOrder.setSettleStatus(settleStatus);

				ebuyDeliveryOrderList.add(ebuyDeliveryOrder);
			}
			effectedCount += ebuyDeliveryOrderBaseDao.updateEbuyDeliveryOrderBatch(ebuyDeliveryOrderList);
		}
		RevenueApply apply = revenueApplyBaseDao.selectRevenueApplyBySeqId(revenueApplyId);
		//添加红点
		addSettleStatusChangeRedPiont(apply.gettEbuySupplyMerchantFk());
		return effectedCount>0;
	}

	/**
	 * 结算审核页面导出
	 *
	 * @param request
	 * @return
	 */
	@Override
	public HSSFWorkbook exportRevenueHandlerReport(HttpServletRequest request){
		return null;
	}

	/**
	 * 结算管理页面列表导出
	 *
	 * @param paramMap ==>supplyMerchantId、applyTimeStart、applyTimeEnd、settleStatus
	 * @return
	 */
	@Override
	public HSSFWorkbook selectRevenueAdminExportList(Map<String, Object> paramMap){
		List<EbuyProductSettleAdminExportDto> exportList = ebuyProductSettleDao.selectRevenueAdminExportList(paramMap);

		String[] sheetTitle = { "供应商", "合作模式", "提交时间", "审核时间", "提款单号", "单据编号", "结算时间", "结算金额", "状态"};
		int[] cellWidth = {10000, 6500, 6500, 6500, 8000, 8000, 6500, 5000, 4000};
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("结算管理导出表");
		HSSFFont font = wb.createFont();
		font.setFontName("微软雅黑");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// head加粗
		// 创建单元格样式
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 设置字体
		style.setFont(font);
		// 创建Excel的sheet的一行
		HSSFRow row = sheet.createRow(0);
		// 设定行的高度
		row.setHeight((short)300);
		// title row
		for (int i = 0; i < sheetTitle.length; i++) {
			// 设置excel每列宽度
			sheet.setColumnWidth(i, cellWidth[i]);
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			cell.setCellValue(sheetTitle[i]);
		}
		// content row
		if(exportList!=null && exportList.size()>0){
			font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
			style.setFont(font);
			DecimalFormat df = new DecimalFormat("0.00");
			for(int i=0, size=exportList.size(); i<size; i++){
				EbuyProductSettleAdminExportDto bean = exportList.get(i);
				row = sheet.createRow((i+1));
				row.setHeight((short)400);
				for(int k=0; k<sheetTitle.length; k++){
					sheet.setColumnWidth(k, cellWidth[k]);
					HSSFCell cell = row.createCell(k);
					cell.setCellStyle(style);
					switch (k) {
						case 0:
							cell.setCellValue(bean.getMerchant());
							break;
						case 1:
							if (bean.getRevenueType().compareTo(1) == 0) {
								cell.setCellValue("购销");
							} else {
								cell.setCellValue("抽佣");
							}
							break;
						case 2:
							cell.setCellValue(bean.getApplyTime());
							break;
						case 3:
							cell.setCellValue(bean.getHandlerTime());
							break;
						case 4:
							cell.setCellValue(bean.getApplyNo());
							break;
						case 5:
							cell.setCellValue("");
							break;
						case 6:
							cell.setCellValue(bean.getSettleTime());
							break;
						case 7:
							cell.setCellValue(df.format(bean.getTotalAmount().doubleValue()));
							break;
						case 8:
							if(bean.getAuditStatus()==3){
								if(bean.getTkStatus()==3){
									cell.setCellValue("已支付");
								} else if(bean.getTkStatus()==2){
									cell.setCellValue("待支付");
								} else {
									cell.setCellValue("未提款");
								}
							} else {
								if(bean.getAuditStatus()==2){
									cell.setCellValue("审核不通过");
								} else {
									cell.setCellValue("待审核");
								}
							}
							break;

						default:
							cell.setCellValue("");
					}
				}
			}
		}
		return wb;
	}

	/**
	 * 查询购销供应商id
	 *
	 * @param account
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchant> selectMerchantsWithPurchase(String account){
		return ebuyProductSettleDao.selectMerchantsWithPurchase(account);
	}

	/**
	 * 管理员结算详情导出
	 *
	 * @param revenueApplyId
	 * @return
	 */
	@Override
	public HSSFWorkbook selectRevenueDetailAdminExportList(BigInteger revenueApplyId, String revApplyFinanceId, String visibleType){
		List<EbuyProductSettleListDetailAdminDto> exportList = ebuyProductSettleDao.selectRevenueDetailAdminExport(revenueApplyId, revApplyFinanceId, visibleType);

		String[] sheetTitle = {"订单号","解放号","支付时间","供应商名称","商品名称","购买数量","商品销售单价","采购单价","销售金额小计","运单销售小计","解放区运费","销售退款金额","运单实际销售额","采购小计","运单采购小计","运费","采购退款金额","运单采购金额","运单结算金额","最终实际支付","交易平台","交易流水号","收货人姓名","收货人联系方式","收货地址"};
		int[] cellWidth = {8000, 4000, 6000, 10000, 12000, 2000, 3000, 2000, 3000, 3000, 3000, 3000, 4000, 2000, 3000, 3000, 4000, 4000, 4000, 4000, 4000, 12000, 3000, 4000, 15000};
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("管理员结算详情导出表");
		HSSFFont font = wb.createFont();
		font.setFontName("微软雅黑");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// head加粗
		// 创建单元格样式
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 设置字体
		style.setFont(font);
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
		// 创建Excel的sheet的一行
		HSSFRow row = sheet.createRow(0);
		// 设定行的高度
		row.setHeight((short)300);
		// title row
		for (int i = 0; i < sheetTitle.length; i++) {
			// 设置excel每列宽度
			sheet.setColumnWidth(i, cellWidth[i]);
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			cell.setCellValue(sheetTitle[i]);
		}
		// content row
		if(exportList!=null && exportList.size()>0){
			int lineStart = 1, lineEnd = 0;
			font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
			style.setFont(font);

			BigDecimal totalSellAmount = BigDecimal.ZERO;
			BigDecimal deliveryProductAmount = BigDecimal.ZERO;
			BigDecimal deliveryRealFee = BigDecimal.ZERO;
			BigDecimal refundAmount = BigDecimal.ZERO;
			BigDecimal deliverySellAmount = BigDecimal.ZERO;

			BigDecimal deliverySettlementFee = BigDecimal.ZERO;
			BigDecimal purchaseRefund = BigDecimal.ZERO;
			BigDecimal deliveryTotalPurchaseAmount = BigDecimal.ZERO;
			BigDecimal revenueAmount = BigDecimal.ZERO;
			BigDecimal realPay = BigDecimal.ZERO;

			for(int i=0, size=exportList.size(); i<size; i++){
				EbuyProductSettleListDetailAdminDto bean = exportList.get(i);
				row = sheet.createRow((i+1));
				row.setHeight((short)400);
				for(int k=0; k<sheetTitle.length; k++){
					sheet.setColumnWidth(k, cellWidth[k]);
					HSSFCell cell = row.createCell(k);
					cell.setCellStyle(style);
					switch (k) {
						case 0:
							cell.setCellValue(bean.getOrderNo());
							break;
						case 1:
							cell.setCellValue(bean.getBuyerId());
							break;
						case 2:
							cell.setCellValue(bean.getPayTime());
							break;
						case 3:
							cell.setCellValue(bean.getMerchant());
							break;
						case 4:
							cell.setCellValue(bean.getProductName());
							break;
						case 5:
							cell.setCellValue(bean.getBuyNum());
							break;
						case 6:
							cell.setCellValue(bean.getPrice().doubleValue());
							break;
						case 7:
							cell.setCellValue(bean.getPurchasePrice().doubleValue());
							break;
						// ======1begin
						case 8:
							totalSellAmount = totalSellAmount.add(bean.getTotalSellAmount());
							cell.setCellValue(bean.getTotalSellAmount().doubleValue());
							break;
						case 9:
							cell.setCellValue(bean.getDeliveryProductAmount().doubleValue());
							break;
						case 10:
							cell.setCellValue(bean.getDeliveryRealFee().doubleValue());
							break;
						case 11:
							cell.setCellValue(bean.getRefundAmount().doubleValue());
							break;
						case 12:
							cell.setCellValue(bean.getDeliverySellAmount().doubleValue());
							break;
						// ======1end
						case 13:
							cell.setCellValue(bean.getPurchaseAmount().doubleValue());
							break;
						case 14:
							cell.setCellValue(bean.getDeliveryPurchaseAmount().doubleValue());
							break;
						// ======2begin	
						case 15:
							cell.setCellValue(bean.getDeliverySettlementFee().doubleValue());
							break;
						case 16:
							cell.setCellValue(bean.getPurchaseRefund().doubleValue());
							break;
						case 17:
							cell.setCellValue(bean.getDeliveryTotalPurchaseAmount().doubleValue());
							break;
						case 18:
							cell.setCellValue(bean.getRevenueAmount().doubleValue());
							break;
						case 19:
							cell.setCellValue(bean.getRealPay().doubleValue());
							break;
						// ======2end
						case 20:
							cell.setCellValue(getPayPlatform(bean.getPayMethod()));
							break;
						case 21:
							cell.setCellValue(bean.getFlowNo());
							break;
						case 22:
							cell.setCellValue(bean.getReceiver());
							break;
						case 23:
							cell.setCellValue(bean.getReceiverMobile());
							break;
						case 24:
							cell.setCellValue(bean.getAddress());
							break;

						default:
							cell.setCellValue("");
					}
				}
				if(i<size-1){
					if(bean.getDelieveOrderId().intValue() == exportList.get(i+1).getDelieveOrderId().intValue()){
						lineEnd++;
					} else {
						// 最后一行
						lineEnd++;
						mergeCellForRevenueApplyList(sheet, lineStart, lineEnd, 9, 12);
						mergeCellForRevenueApplyList(sheet, lineStart, lineEnd, 14, 20);
						lineStart = lineEnd+1;

						{
							deliveryProductAmount = deliveryProductAmount.add(bean.getDeliveryProductAmount());
							deliveryRealFee = deliveryRealFee.add(bean.getDeliveryRealFee());
							refundAmount = refundAmount.add(bean.getRefundAmount());
							deliverySellAmount = deliverySellAmount.add(bean.getDeliverySellAmount());
							deliverySettlementFee = deliverySettlementFee.add(bean.getDeliverySettlementFee());
							purchaseRefund = purchaseRefund.add(bean.getPurchaseRefund());
							deliveryTotalPurchaseAmount = deliveryTotalPurchaseAmount.add(bean.getDeliveryTotalPurchaseAmount());
							revenueAmount = revenueAmount.add(bean.getRevenueAmount());
							realPay = realPay.add(bean.getRealPay());
						}
					}
				} else {
					// 最后一行
					lineEnd++;
					mergeCellForRevenueApplyList(sheet, lineStart, lineEnd, 9, 12);
					mergeCellForRevenueApplyList(sheet, lineStart, lineEnd, 14, 20);

					{
						deliveryProductAmount = deliveryProductAmount.add(bean.getDeliveryProductAmount());
						deliveryRealFee = deliveryRealFee.add(bean.getDeliveryRealFee());
						refundAmount = refundAmount.add(bean.getRefundAmount());
						deliverySellAmount = deliverySellAmount.add(bean.getDeliverySellAmount());
						deliverySettlementFee = deliverySettlementFee.add(bean.getDeliverySettlementFee());
						purchaseRefund = purchaseRefund.add(bean.getPurchaseRefund());
						deliveryTotalPurchaseAmount = deliveryTotalPurchaseAmount.add(bean.getDeliveryTotalPurchaseAmount());
						revenueAmount = revenueAmount.add(bean.getRevenueAmount());
						realPay = realPay.add(bean.getRealPay());
					}
				}
			}

			// 总计
			double[] totals = new double[]{totalSellAmount.doubleValue(), deliveryProductAmount.doubleValue(), deliveryRealFee.doubleValue(),
					refundAmount.doubleValue(), deliverySellAmount.doubleValue(), deliverySettlementFee.doubleValue(),
					purchaseRefund.doubleValue(), deliveryTotalPurchaseAmount.doubleValue(), revenueAmount.doubleValue(),
					realPay.doubleValue()};
			initTotalMoney(sheet, exportList.size()+1, style, totals);
		}
		return wb;
	}

	@Override
	public List<EbuyProductSettleApplyExportDto> getEbuyProductSettleApplyList(Map<String, Object> paramMap) {
		return ebuyProductSettleDao.getEbuyProductSettleApplyList(paramMap);
	}

	@Override
	public List<EbuyProductSettleDetailAdminDto> getEbuyProductSettleDetailExport(BigInteger revenueApplyId, String revApplyFinanceId, String visibleType) {
		List<EbuyProductSettleDetailAdminDto> data = ebuyProductSettleDao.getEbuyProductSettleDetailExport(revenueApplyId, revApplyFinanceId, visibleType);
		dealLastlineDetailAdminDto(data);
		return data;
	}

	private void dealLastlineDetailAdminDto(List<EbuyProductSettleDetailAdminDto> data) {
		if (!DataUtil.isEmpty(data)) {
			EbuyProductSettleDetailAdminDto lastLine = new EbuyProductSettleDetailAdminDto();
			lastLine.setDeliveryOrderTotalAmount(BigDecimal.valueOf(0));
			lastLine.setDeliveryOrderSellAmount(BigDecimal.valueOf(0));
			lastLine.setDeliveryOrderSettleAmount(BigDecimal.valueOf(0));
			lastLine.setDeliveryOrderTotalSettleAmount(BigDecimal.valueOf(0));
			lastLine.setDeliveryOrderRealPay(BigDecimal.valueOf(0));
			lastLine.setButie(BigDecimal.valueOf(0));
			lastLine.setRefundFee(BigDecimal.valueOf(0));
			lastLine.setDeliveryRealFee(BigDecimal.valueOf(0));
			lastLine.setProductTotalAmount(BigDecimal.valueOf(0));

			for (int i = 0; i < data.size(); i++) {
				EbuyProductSettleDetailAdminDto dto = data.get(i);
				//有些数据是要合并的显示的
				lastLine.setProductTotalAmount(dto.getProductTotalAmount().add(lastLine.getProductTotalAmount()));
				if (i == 0 || dto.getDeliveryOrderId().compareTo(data.get(i - 1).getDeliveryOrderId()) != 0) {
					//运单销售小计 即商品钱 即运单钱 - 运费 deliveryOrderTotalAmount
					dto.setDeliveryOrderTotalAmount(PriceUtil.div100(Long.valueOf(dto.getDeliveryOrderAmount() + dto.getDeliveryOrderCoupon())).subtract(dto.getDeliveryRealFee()));
					//运单销售额 = 运单销售小计 - 销售退款金额 deliveryOrderSellAmount
					dto.setDeliveryOrderSellAmount(dto.getDeliveryOrderTotalAmount().subtract(dto.getRefundFee()));
					//运单结算金额 = 运单销售金额*（1-佣金比率）deliveryOrderSettleAmount setScale(2, RoundingMode.HALF_UP);
					BigDecimal settleAmount = dto.getDeliveryOrderSellAmount().subtract(dto.getDeliveryOrderSellAmount().multiply(dto.getRevenueRate())).setScale(2, RoundingMode.HALF_UP);
					dto.setDeliveryOrderSettleAmount(settleAmount);
					//运单结算总额 = 运单结算金额+运费 deliveryOrderTotalSettleAmount
					dto.setDeliveryOrderTotalSettleAmount(settleAmount.add(dto.getDeliveryRealFee()));
					//最终实际支付 = 运单拆分之后的实际支付-退款的实际支付 deliveryOrderRealPay
					Integer refundMoney = dto.getRefundMoney().multiply(BigDecimal.valueOf(100)).intValue();
					dto.setDeliveryOrderRealPay(PriceUtil.div100(Long.valueOf(dto.getDeliveryOrderAmount() - refundMoney)));
					//补贴 = 运单结算总额-最终实际支付
					dto.setButie(dto.getDeliveryOrderTotalSettleAmount().subtract(dto.getDeliveryOrderRealPay()));

					lastLine.setDeliveryOrderTotalAmount(dto.getDeliveryOrderTotalAmount().add(lastLine.getDeliveryOrderTotalAmount()));
					lastLine.setDeliveryOrderSellAmount(dto.getDeliveryOrderSellAmount().add(lastLine.getDeliveryOrderSellAmount()));
					lastLine.setDeliveryOrderSettleAmount(settleAmount.add(lastLine.getDeliveryOrderSettleAmount()));
					lastLine.setDeliveryOrderTotalSettleAmount(dto.getDeliveryOrderTotalSettleAmount().add(lastLine.getDeliveryOrderTotalSettleAmount()));
					lastLine.setDeliveryOrderRealPay(dto.getDeliveryOrderRealPay().add(lastLine.getDeliveryOrderRealPay()));
					lastLine.setButie(dto.getButie().add(lastLine.getButie()));
					lastLine.setRefundFee(dto.getRefundFee().add(lastLine.getRefundFee()));
					lastLine.setDeliveryRealFee(dto.getDeliveryRealFee().add(lastLine.getDeliveryRealFee()));

				} else {
					dto.setRefundFee(null);
					dto.setDeliveryRealFee(null);
					dto.setReceiver(null);
					dto.setReceiverMobile(null);
					dto.setDelivAddress(null);
					dto.setPayMethod(null);
					dto.setPayFlowNo(null);
				}
			}
			data.add(lastLine);
		}
	}
	/**
	 * 添加结算申请状态变化红点
	 * @param supplyMerchantId 供应商ID
	 */
	private void addSettleStatusChangeRedPiont(BigInteger supplyMerchantId) {

		String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		RedpointContent redpointContent = new RedpointContent();
		redpointContent.setUserId(supplyMerchantId);
		redpointContent.setUserType(1);
		redpointContent.setModelCode(RedpointConstant.RedpointContent_ModelCode.EBUY_MERCHANT_SETTLE_STAUTS_CHANGE);
		List<RedpointContent> contents = redpointContentBaseService.getRedpointContentByCondition(MapConverter.toMap(redpointContent));
		if (DataUtil.isEmpty(contents)) {
			redpointContent = new RedpointContent();
			redpointContent.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_redpoint_content));
			redpointContent.setUserId(supplyMerchantId);
			redpointContent.setUserType(1);
			redpointContent.setModelCode(RedpointConstant.RedpointContent_ModelCode.EBUY_MERCHANT_SETTLE_STAUTS_CHANGE);
			redpointContent.setClickStatus(RedpointDict.RedpointContent_clickStatus.NOT_CLICK);
			redpointContent.setLastModifyTime(now);
			redpointContentBaseService.insertRedpointContent(redpointContent);
		} else {
			redpointContent = contents.get(0);
			redpointContent.setClickStatus(RedpointDict.RedpointContent_clickStatus.NOT_CLICK);
			redpointContent.setLastModifyTime(now);
			redpointContentBaseService.updateRedpointContent(redpointContent);
		}
	}

	/**
	 * 总计数据初始化
	 *
	 * @param sheet
	 * @param rowNum
	 * @param style
	 * @param df
	 * @param totals
	 */
	private void initTotalMoney(HSSFSheet sheet, int rowNum, HSSFCellStyle style, double[] totals){
		HSSFRow row = sheet.createRow(rowNum);
		row.setHeight((short)400);

		HSSFCell cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("总计（元）：");

		for(int i=0; i<totals.length; i++){
			if(i<5){
				cell = row.createCell(8+i);
			} else {
				cell = row.createCell(10+i);
			}
			cell.setCellStyle(style);
			cell.setCellValue(totals[i]);
		}
	}

	/**
	 * 获取支付方式
	 *
	 * @param payMethod
	 * @return
	 */
	private String getPayPlatform(String payMethod) {
		if(payMethod == null){
			return "";
		} else if (payMethod.equals("1")) {
			return "微信支付";
		} else if (payMethod.equals("2")) {
			return "支付宝";
		} else if (payMethod.equals("3")) {
			return "银联支付";
		} else if (payMethod.equals("4")) {
			return "纯粮票支付";
		} else if (payMethod.equals("5")) {
			return "纯积分支付";
		} else if (payMethod.equals("6")) {
			return "微信轻应用支付";
		} else if (payMethod.equals("7")) {
			return "纯优惠券支付";
		} else if (payMethod.equals("9")) {
			return "双乾支付";
		} else {
			return "";
		}
	}

	/**
	 * <p>获取“结算金额”、“平台补贴额”、“用户实缴”<br>
	 * 说明：<br>
	 *   代销：平台使用费=（商品销售总额-退款商品的销售总额）*佣金比例<br>
	 *   购销：平台使用费=（商品销售总额-退款商品的销售总额+解放区运费）-（商品采购总额+供应商运费）+退款商品的采购总额<br>
	 *   1）优惠 > 退款  <br>
	 *     补贴=优惠-退款 <br>
	 *     实缴=实付-平台使用费  <br>
	 *   2）优惠 < 退款  <br>
	 *     补贴=0 <br>
	 *     实缴=实付-（退款-优惠）-平台使用费  <br>
	 *   3）无退款  <br>
	 *     补贴=优惠  <br>
	 *     实缴=实付-平台使用费  <br>
	 * </p>
	 *
	 * @param merchant
	 * @return
	 */
	private final BigDecimal getDeliveryRevenueMoney(RevenueAllTotalAmount allTotalAmount, Integer revenueType, Double revenueRate){
		BigDecimal usrRealMoney = BigDecimal.ZERO;// 用户实缴
		BigDecimal ptMoney      = BigDecimal.ZERO;// 平台使用费
		if(revenueType.toString().equals("1")){// 购销
			ptMoney = allTotalAmount.getTotalSellAmount().subtract(allTotalAmount.getRefundTotalSellAmount()).add(allTotalAmount.getJfqDeliveryFee())
					.subtract(allTotalAmount.getPurchaseAmount().add(allTotalAmount.getGysDeliveryFee()))
					.add(allTotalAmount.getRefundPurchaseAmount());
		} else {// 代销
			ptMoney = allTotalAmount.getTotalSellAmount().subtract(allTotalAmount.getRefundTotalSellAmount()).multiply(BigDecimal.valueOf(revenueRate));
		}

		if(allTotalAmount.getRefund().doubleValue()>0){// 有退款
			if(allTotalAmount.getPreferAmount().doubleValue() >= allTotalAmount.getRefund().doubleValue()){// 优惠 >= 退款
				usrRealMoney = allTotalAmount.getRealPay().subtract(ptMoney);
			} else {// 优惠 < 退款
				usrRealMoney = allTotalAmount.getRealPay().subtract((allTotalAmount.getRefund().subtract(allTotalAmount.getPreferAmount()))).subtract(ptMoney);
			}
		} else {// 无退款
			usrRealMoney = allTotalAmount.getRealPay().subtract(ptMoney);
		}

		//RevenueMoney deliveryRevenueMoney = new RevenueMoney();
		//deliveryRevenueMoney.setAmountPtbt(allTotalAmount.getTotalMoney().subtract(usrRealMoney));
		//deliveryRevenueMoney.setAmountUsrReal(usrRealMoney);
		//deliveryRevenueMoney.setTotalAmount(allTotalAmount.getTotalMoney());

		return usrRealMoney;
	}

	/**
	 * 计算所有的运单“结算金额”、“平台补贴额”、“用户实缴”之和
	 *
	 * @param totalAmount
	 * @param allTotalAmounts
	 * @param revenueType
	 * @param revenueRate
	 * @return
	 */
	private final RevenueMoney getRevenueMoney(BigDecimal totalAmount, List<RevenueAllTotalAmount> allTotalAmounts, Integer revenueType, Double revenueRate){
		BigDecimal amountUsrReal = BigDecimal.ZERO;
		BigDecimal amountPtbt    = BigDecimal.ZERO;
		for (RevenueAllTotalAmount allTotalAmount : allTotalAmounts) {
			BigDecimal usrRealMoney = getDeliveryRevenueMoney(allTotalAmount, revenueType, revenueRate);
			{
				//totalAmount.add(deliveryRevenueMoney.getTotalAmount());
				amountUsrReal = amountUsrReal.add(usrRealMoney);
			}
		}
		amountPtbt = totalAmount.subtract(amountUsrReal);
		RevenueMoney revenueMoney = new RevenueMoney();
		revenueMoney.setAmountPtbt(amountPtbt);
		revenueMoney.setAmountUsrReal(amountUsrReal);
		revenueMoney.setTotalAmount(totalAmount);

		return revenueMoney;
	}
	/**
	 * 获取总的“结算金额”、“平台补贴额”、“用户实缴”
	 *
	 * @param merchantId
	 * @param revenueType
	 * @param revenueRate
	 * @return
	 */
	private final RevenueMoney getTotalRevenueMoney(BigInteger merchantId, Integer revenueType, Double revenueRate){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("supplyMerchantId", merchantId);
		paramMap.put("revenueType", revenueType);
		List<RevenueAllTotalAmount> allTotalAmounts = ebuyProductSettleDao.selectAllTotalAmount(paramMap);

		paramMap.clear();
		paramMap.put("supplyMerchantId", merchantId);
		paramMap.put("pageType", PageType.APPLY_LIST);
		BigDecimal totalAmount   = ebuyProductSettleDao.selectRevenueApplyForTotalAmount(paramMap, revenueType);

		RevenueMoney revenueMoney = getRevenueMoney(totalAmount, allTotalAmounts, revenueType, revenueRate);

		logger.info(revenueMoney);

		return revenueMoney;
	}

}
