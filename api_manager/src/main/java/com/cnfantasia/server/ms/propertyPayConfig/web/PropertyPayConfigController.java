package com.cnfantasia.server.ms.propertyPayConfig.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.commbusi.alterPeriod.service.LatefeeCalculateService;
import com.cnfantasia.server.commbusi.propertyPayConfig.constant.CalExpItem;
import com.cnfantasia.server.commbusi.propertyPayConfig.constant.PropertyPayConfigConstant;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domainbase.alterPeriodCfg.entity.AlterPeriodCfg;
import com.cnfantasia.server.domainbase.alterPeriodCfg.service.IAlterPeriodCfgBaseService;
import com.cnfantasia.server.domainbase.alterPeriodFeeItem.entity.AlterPeriodFeeItem;
import com.cnfantasia.server.domainbase.alterPeriodFeeItem.service.IAlterPeriodFeeItemBaseService;
import com.cnfantasia.server.domainbase.building.entity.Building;
import com.cnfantasia.server.domainbase.fixedFeeItem.entity.FixedFeeItem;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuilding.service.IGroupBuildingBaseService;
import com.cnfantasia.server.domainbase.mrCalculateRuleCfg.entity.MrCalculateRuleCfg;
import com.cnfantasia.server.domainbase.mrCalculateRuleCfg.service.IMrCalculateRuleCfgBaseService;
import com.cnfantasia.server.domainbase.mrFeeItem.entity.MrFeeItem;
import com.cnfantasia.server.domainbase.mrFeeItem.service.MrFeeItemBaseService;
import com.cnfantasia.server.domainbase.mrFeeItemFormula.entity.MrFeeItemFormula;
import com.cnfantasia.server.domainbase.mrFeeItemFormula.service.IMrFeeItemFormulaBaseService;
import com.cnfantasia.server.domainbase.payBillType.entity.PayBillType;
import com.cnfantasia.server.domainbase.payBillType.service.IPayBillTypeBaseService;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.tmpFeeItem.entity.TmpFeeItem;
import com.cnfantasia.server.domainbase.tmpFeeItem.service.ITmpFeeItemBaseService;
import com.cnfantasia.server.ms.fixedFeeCfg.service.FixedFeeCfgService;
import com.cnfantasia.server.ms.propertyPayConfig.entity.*;
import com.cnfantasia.server.ms.propertyPayConfig.service.IPropertyPayConfigService;
import com.cnfantasia.server.ms.pub.utils.MapConverter;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import parsii.eval.Expression;
import parsii.eval.Parser;
import parsii.tokenizer.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 收费项配置
 *
 * @author liyulin
 * @version 1.0 2016年10月24日 下午2:51:59
 */
@Controller
@RequestMapping("/propertyPayConfig")
public class PropertyPayConfigController {
    @Resource
    private IPropertyPayConfigService propertyPayConfigService;
    @Resource
    private IAlterPeriodFeeItemBaseService alterPeriodFeeItemBaseService;
    @Resource
    private IAlterPeriodCfgBaseService alterPeriodCfgBaseService;
    @Resource
    private IGroupBuildingBaseService groupBuildingBaseService;
    @Resource
    private LatefeeCalculateService latefeeCalculateService;
    @Resource
    private IPayBillTypeBaseService payBillTypeBaseService;
    @Resource
    private IMrFeeItemFormulaBaseService mrFeeItemFormulaBaseService;
    @Resource
    private IMrCalculateRuleCfgBaseService mrCalculateRuleCfgBaseService;

    @RequestMapping("/config.html")
    public ModelAndView config(BigInteger gbId) {
        // 收费项配置
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tGbId", gbId);
        List<AlterPeriodFeeItem> alterPeriodFeeItems = propertyPayConfigService.selectAlterPeriodFeeItemByCondition(paramMap);

        // 滞纳金计算公式
        List<AlterPeriodCfg> alterPeriodCfgs = alterPeriodCfgBaseService.getAlterPeriodCfgByConditionDim(paramMap);

        ModelAndView mav = new ModelAndView();
        mav.addObject("alterPeriodFeeItems", alterPeriodFeeItems);
        if (alterPeriodCfgs != null && alterPeriodCfgs.size() > 0) {
            AlterPeriodCfg alterPeriodCfg = alterPeriodCfgs.get(0);
            mav.addObject("alterPeriodCfg", alterPeriodCfg);

            if (StringUtils.isNotBlank(alterPeriodCfg.getCalculateExpression())) {
                String exp = alterPeriodCfg.getCalculateExpression();
                // 隐藏的值
                StringBuilder expHidden = new StringBuilder();
                for (int i = 0; i < exp.length(); i++) {// "<span></span>"格式，前端需要
                    expHidden.append("<span>");
                    expHidden.append(exp.charAt(i));
                    expHidden.append("</span>");
                }
                mav.addObject("expHidden", expHidden);

                // 显示的值
                StringBuilder expShow = new StringBuilder();
                for (int i = 0; i < exp.length(); i++) {// "<span></span>"格式，前端需要
                    expShow.append("<span>");
                    expShow.append(getItem(exp.charAt(i)));
                    expShow.append("</span>");
                }
                mav.addObject("expShow", expShow);
            }
        }

        GroupBuilding gb = groupBuildingBaseService.getGroupBuildingBySeqId(gbId);

        //选择周期数据信息
        String alterPeriodMonths = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.ALTERPERIODMONTHS);
        if (!StringUtils.isEmpty(alterPeriodMonths)) {
            String[] months = alterPeriodMonths.split(",");
            if (gb.getPropertyPeriodType() != null && gb.getPropertyPeriodType().equals(2)) {//选择周期
                if (!StringUtils.isEmpty(gb.getPeriodMonths())) {
                    String[] alterMonths = gb.getPeriodMonths().split(",");
                    mav.addObject("alterMonths", alterMonths);
                }
            }
            mav.addObject("months", months);
        }

        //账单名称
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("paytimeType", 2);
        resMap.put("isPropFee", 1);
        resMap.put("gbId", gbId);
        List<PayBillType> payBillTypes = payBillTypeBaseService.getPayBillTypeByCondition(resMap);

        mav.addObject("gbName", gb.getName());
        mav.addObject("billName", DataUtil.isEmpty(payBillTypes) ? "" : payBillTypes.get(0).getName());
        mav.addObject("gbId", gb.getId());
        mav.addObject("calExpElements", PropertyPayConfigConstant.CAL_EXP_SELEMENTS);
        mav.setViewName("/propertyPayConfig/config");
        return mav;
    }

    @Resource
    private FixedFeeCfgService fixedFeeCfgService;
    @Resource
    private MrFeeItemBaseService mrFeeItemBaseService;
    @Resource
    private ITmpFeeItemBaseService tmpFeeItemBaseService;

    @RequestMapping("/chargeItemConfig.html")
    public ModelAndView chargeItemConfig(BigInteger gbId, String gbName) {
        ModelAndView mav = new ModelAndView("/propertyPayConfig/chargeItemConfig");
        //常规收费项设置
        List<FixedFeeItem> itemList = fixedFeeCfgService.getFixedFeeItemList(gbId);
        Collections.reverse(itemList);

        //抄表收费配置
        List<MrFeeItemEntity> mriList = propertyPayConfigService.getMrFeeItemEntityByGbId(gbId);
        //临时收费
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tGbId", gbId);
        List<TmpFeeItem> tmpFeeItemList = tmpFeeItemBaseService.getTmpFeeItemByConditionDim(paramMap);
        Collections.reverse(tmpFeeItemList);

        mav.addObject("itemList", itemList);
        mav.addObject("gbName", gbName);
        mav.addObject("gbId", gbId);
        mav.addObject("mriList", mriList);
        mav.addObject("tmpFeeItemList", tmpFeeItemList);

        return mav;
    }

    /**
     * 临时收费项保存,更新
     *
     * @param request
     * @return
     */
    @RequestMapping("saveTmpFeeItem.json")
    @ResponseBody
    public JsonResponse saveTmpFeeItem(BigInteger gbId, String items) {
        JsonResponse jsonResponse = new JsonResponse();
        if ("[]".equals(items)) {
            jsonResponse.setMessage("收费项不能为空！操作失败！");
            jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
        } else {
            boolean isSuccessed = propertyPayConfigService.saveTmpFeeItem(gbId, items);
            if (isSuccessed) {
                jsonResponse.setMessage("操作成功！");
            } else {
                jsonResponse.setMessage("操作失败！");
                jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
            }
        }
        return jsonResponse;
    }

    /**
     * 临时收费项删除
     *
     * @param request
     * @return
     */
    @RequestMapping("delTmpFeeItem.json")
    @ResponseBody
    public JsonResponse delTmpFeeItem(BigInteger id) {
        JsonResponse jsonResponse = new JsonResponse();
        boolean isSuccessed = true;
        try {
            tmpFeeItemBaseService.deleteTmpFeeItemLogic(id);
        } catch (Exception e) {
            isSuccessed = false;
        }
        if (isSuccessed) {
            jsonResponse.setMessage("操作成功！");
        } else {
            jsonResponse.setMessage("操作失败！");
            jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
        }
        return jsonResponse;
    }

    private final String getItem(char c) {
        CalExpItem item = PropertyPayConfigConstant.CAL_EXP_SELEMENTS.get(String.valueOf(c));
        if (item == null) {
            return String.valueOf(c);
        } else {
            return item.getName();
        }
    }

    /**
     * 保存收费项配置
     *
     * @param gbId
     * @param alterPeriodFeeItems
     * @return
     */
    @RequestMapping("/savePayConfig.html")
    @ResponseBody
    public JsonResponse savePayConfig(BigInteger gbId, String alterPeriodFeeItems, String billName, String periodMonths) {
        JsonResponse jsonResponse = new JsonResponse();
        if ("[]".equals(alterPeriodFeeItems)) {
            jsonResponse.setMessage("收费项不能为空！操作失败！");
            jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
        } else {
            boolean isSuccessed = propertyPayConfigService.savePayConfig(gbId, alterPeriodFeeItems, billName, periodMonths);
            if (isSuccessed) {
                jsonResponse.setMessage("操作成功！");
                latefeeCalculateService.calculateByGroupBuildingByThread(gbId);
            } else {
                jsonResponse.setMessage("操作失败！");
                jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
            }
        }
        return jsonResponse;
    }

    /**
     * 保存滞纳金配置
     *
     * @param request
     * @return
     */
    @RequestMapping("/saveLateMoneyConfig.html")
    @ResponseBody
    public JsonResponse saveLateMoneyConfig(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 公式校验
        String calExp = ParamUtils.getString(request, "calculateExpression", null);
        if (StringUtils.isNotBlank(calExp)) {
            CalExpCheckMsg calExpCheckMsg = checkCalExp(calExp);
            if (!calExpCheckMsg.isValid()) {
                jsonResponse.setMessage(calExpCheckMsg.getMsg());
                jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
                return jsonResponse;
            }
        }

        // 逻辑处理
        BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
        String alterPeriodFeeItems = request.getParameter("alterPeriodFeeItems");//ParamUtils.getString(request, "alterPeriodFeeItems");方法报错，因为“"”单独处理了
        String billName = request.getParameter("billName");
        String periodMonths = request.getParameter("periodMonth");
        Integer latefeeStatus = ParamUtils.getInteger(request, "latefeeStatus", 2);
        double latefeeRate = ParamUtils.getDouble(request, "latefeeRate", 0);

        LateMoneyConfigRequest lateMoneyConfigRequest = new LateMoneyConfigRequest();
        lateMoneyConfigRequest.setGbId(gbId);
        lateMoneyConfigRequest.setAlterPeriodFeeItems(alterPeriodFeeItems);
        lateMoneyConfigRequest.setLatefeeStatus(latefeeStatus);
        lateMoneyConfigRequest.setLatefeeRate(latefeeRate);
        lateMoneyConfigRequest.setCalExp(calExp);
        lateMoneyConfigRequest.setBillName(billName);
        lateMoneyConfigRequest.setPeriodMonths(periodMonths);

        boolean isSuccessed = propertyPayConfigService.saveLateMoneyConfig(lateMoneyConfigRequest);
        if (isSuccessed) {
            jsonResponse.setMessage("操作成功！");
            latefeeCalculateService.calculateByGroupBuildingByThread(gbId);
        } else {
            jsonResponse.setMessage("操作失败！");
            jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
        }

        return jsonResponse;
    }

    /**
     * 删除费用项
     *
     * @param request
     * @return
     */
    @RequestMapping("/deleteItem.json")
    @ResponseBody
    public JsonResponse deleteItem(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        BigInteger itemId = ParamUtils.getBigInteger(request, "id", null);
        BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
        if (itemId != null) {
            String res = propertyPayConfigService.deleteItem(itemId, gbId);
            jsonResponse.setMessage(res);
        } else {
            jsonResponse.setMessage("参数校验失败，请刷新重试！");
        }

        return jsonResponse;
    }

    /**
     * 删除某小区下的所有费用明细
     *
     * @param request
     * @return
     */
    @RequestMapping("/deleteAllDetail.json")
    @ResponseBody
    public JsonResponse deleteAllDetail(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
        if (gbId != null) {
            String res = propertyPayConfigService.deleteAllDetailByGb(gbId);
            jsonResponse.setMessage(res);
        } else {
            jsonResponse.setMessage("参数校验失败，请刷新重试！");
        }

        return jsonResponse;
    }


    /**
     * <p>公式校验<br>
     * 月、天时间元素不能共存<br>
     * </p>
     *
     * @param calExp
     * @return
     */
    private final CalExpCheckMsg checkCalExp(String calExp) {
        CalExpCheckMsg calExpCheckMsg = new CalExpCheckMsg();
        // 月、天时间元素不能共存
        if ((calExp.contains(PropertyPayConfigConstant.CalElement.WY_START_TIME_BY_MONTH)
                //||calExp.contains(PropertyPayConfigConstant.CalElement.WY_END_TIME_BY_MONTH)
                || calExp.contains(PropertyPayConfigConstant.CalElement.ZNJ_START_TIME_BY_MONTH)
                || calExp.contains(PropertyPayConfigConstant.CalElement.USER_PAY_TIME_PER_MONTH))
                // 包含“天”
                && (calExp.contains(PropertyPayConfigConstant.CalElement.ZNJ_START_TIME_BY_DAY)
                || calExp.contains(PropertyPayConfigConstant.CalElement.USER_PAY_TIME_PER_DAY))) {
            calExpCheckMsg.setMsg("月、天时间元素不能共存！公式格式错误！");
            calExpCheckMsg.setValid(false);
        } else {
            // 数值前不能有0
            Pattern pattern = Pattern.compile("([\\D+]*)((0[0-9]+)+)([\\D+]*)");
            Matcher matcher = pattern.matcher(calExp);
            if (matcher.matches()) {
                calExpCheckMsg.setMsg("数字前面不能有0，公式不合法！操作失败！");
                calExpCheckMsg.setValid(false);
            } else {
                // 公式合法性校验
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < calExp.length(); i++) {
                    String alpha = String.valueOf(calExp.charAt(i));
                    if (StringUtils.isAlpha(alpha)) {
                        CalExpItem item = PropertyPayConfigConstant.CAL_EXP_SELEMENTS.get(String.valueOf(alpha));
                        sb.append(item.getCheck());
                    } else {
                        sb.append(alpha);
                    }
                }

                Expression expr = null;
                try {
                    expr = Parser.parse(sb.toString());
                    double result = expr.evaluate();
                    if (Double.isInfinite(result) || Double.isNaN(result)) {
                        calExpCheckMsg.setMsg("公式不合法！操作失败！");
                        calExpCheckMsg.setValid(false);
                    } else {
                        calExpCheckMsg.setValid(true);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    calExpCheckMsg.setMsg("公式不合法！操作失败！");
                    calExpCheckMsg.setValid(false);
                }
            }
        }

        return calExpCheckMsg;
    }

    /**
     * ================================================================================
     * ==                                                                             =
     * ==                                                                             =
     * ==                   V516 一户多表涉及的oos控制层方法                            =
     * ==                                                                             =
     * ==                                                                             =
     * ================================================================================
     */

    /**
     * 跳转到计费规则新增页面
     * @param request
     * @return
     */
    @RequestMapping("/goToAddMrCalculateRuleCfg.html")
    public ModelAndView goToAddMrCalculateRuleCfg(HttpServletRequest request) {
        BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
        BigInteger mfiId = ParamUtils.getBigInteger(request, "mfiId", null);
        BigInteger mrCalculateRuleCfgId = ParamUtils.getBigInteger(request, "mrCalculateRuleCfgId", null);
        String itemName = ParamUtils.getString(request, "itemName");
        String gbName = ParamUtils.getString(request, "gbName");
        if(mfiId == null){
            throw new BusinessRuntimeException("mfiId can't be null");
        }
        if(gbId == null){
            throw new BusinessRuntimeException("gbId can't be null");
        }

        List<MrFeeItemFormula> mrifList = new ArrayList<MrFeeItemFormula>();
        if(mrCalculateRuleCfgId != null) {
            MrFeeItemFormula mrifQry = new MrFeeItemFormula();
            mrifQry.settMrFeeItemFId(mfiId);
            mrifQry.settMrCalculateRuleCfgId(mrCalculateRuleCfgId);
            mrifList = mrFeeItemFormulaBaseService.getMrFeeItemFormulaByCondition(MapConverter.convertBean(mrifQry));
            Collections.reverse(mrifList);

            MrCalculateRuleCfg mrCalculateRuleCfg = mrCalculateRuleCfgBaseService.getMrCalculateRuleCfgBySeqId(mrCalculateRuleCfgId);
            request.setAttribute("mrCalculateRuleCfg", mrCalculateRuleCfg);
        }

        //查询小区费用项
        MrFeeItem mrFeeItem = new MrFeeItem();
        mrFeeItem.setGbId(gbId);
        List<MrFeeItem> mrFeeItemList = mrFeeItemBaseService.getMrFeeItemByCondition(MapConverter.convertBean(mrFeeItem));

        request.setAttribute("mrifList", mrifList);
        request.setAttribute("itemName", itemName);
        request.setAttribute("mrFeeItemList", mrFeeItemList);
        request.setAttribute("mrFeeItemId", mfiId);
        request.setAttribute("mrCalculateRuleCfgId", mrCalculateRuleCfgId);
        request.setAttribute("gbId", gbId);
        request.setAttribute("gbName", gbName);

        return new ModelAndView("/meterReading/mrFeeItemFormulaEdit");
    }

    /**
     * 保存计费规则
     * @param request
     * @return
     */
    @RequestMapping("/saveMrCalculateRuleCfg.json")
    @ResponseBody
    public JsonResponse saveMrCalculateRuleCfg(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        String feeItemFormulaJson = request.getParameter("feeItemFormulaJson");
        BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
        CalculateRuleEntity calculateRuleEntity = JSON.parseObject(feeItemFormulaJson, CalculateRuleEntity.class);
        //tMrFeeItemId无法通过json转换，故将属性改为mrFeeItemId，在这里再次赋值回去
        calculateRuleEntity.settMrFeeItemId(calculateRuleEntity.getMrFeeItemId());
        propertyPayConfigService.saveMrCalculateRuleCfg(calculateRuleEntity, gbId);
        return jsonResponse;
    }

    /**
     * 删除计费规则
     * @param request
     * @return
     */
    @RequestMapping("/delMrCalculateRuleCfg.json")
    @ResponseBody
    public JsonResponse delMrCalculateRuleCfg(BigInteger id) {
        JsonResponse jsonResponse = new JsonResponse();
        //检查计算规则是否在使用中
        Boolean isUse = propertyPayConfigService.calculateRuleIsUse(id);
        if(!isUse) {
            propertyPayConfigService.delMrCalculateRuleCfg(id);
        } else {
            jsonResponse.setStatus("0001");
            jsonResponse.setMessage("该项规则正在使用中，请先修改相应门牌的收费规则后再操作！");
        }

        return jsonResponse;
    }

    /**
     * 查询小区下的楼栋门牌关系树型结构数据
     * @param request
     * @return
     */
    @RequestMapping("/getRoomTree.json")
    @ResponseBody
    public JsonResponse getRoomTree(HttpServletRequest request) {
        BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
        if(gbId == null){
            throw new BusinessRuntimeException("gbId can't be null");
        }
        JsonResponse jsonResponse = new JsonResponse();
        List<GroupBuildingTreeEntity> groupBuildingTreeEntities = propertyPayConfigService.getRoomTreeByGbId(gbId);
        jsonResponse.setDataValue(groupBuildingTreeEntities);

        return jsonResponse;
    }

    /**
     * 跳转到收费标准页面
     * @param request
     * @return
     */
    @RequestMapping("/jumpToGetStandardList.html")
    public ModelAndView jumpToGetStandardList(HttpServletRequest request) {
        BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
        if(gbId == null){
            throw new BusinessRuntimeException("gbId can't be null");
        }
        ModelAndView modelAndView = new ModelAndView("/meterReading/mrStandardList");

        List<GroupBuildingTreeEntity> groupBuildingTreeEntities = propertyPayConfigService.getRoomTreeByGbId(gbId);

        modelAndView.addObject("gbId", gbId);
        modelAndView.addObject("groupBuildingTreeEntity", groupBuildingTreeEntities);
        modelAndView.addObject("gbName", request.getParameter("gbName"));

        return modelAndView;
    }


    /**
     * 收费标准管理列表:小区，楼栋，门牌列表
     * @param request
     * @return
     */
    @RequestMapping("/mrStandardList.json")
    @ResponseBody
    public JsonResponse mrStandardList(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
        BigInteger buildingId = ParamUtils.getBigInteger(request, "buildingId", null);
        BigInteger realRoomId = ParamUtils.getBigInteger(request, "realRoomId", null);
        if(gbId == null && buildingId == null && realRoomId == null){
            throw new BusinessRuntimeException("Parameters can't be null");
        }

        jsonResponse = propertyPayConfigService.getMrStandardList(gbId, buildingId, realRoomId);

        return jsonResponse;
    }

    /**
     * 收费标准管理保存：小区，楼栋，房间
     * @param request
     * @return
     */
    @RequestMapping("/saveMrStandard.json")
    @ResponseBody
    public JsonResponse saveMrStandard(HttpServletRequest request, StandardSaveParam standardSaveParam) {
        JsonResponse jsonResponse = new JsonResponse();
        String result = propertyPayConfigService.saveMrStandard(standardSaveParam);
        jsonResponse.setMessage(result);
        return jsonResponse;
    }

    /**
     * 收费标准管理删除：房间
     * @param request
     * @return
     */
    @RequestMapping("/delMrStandard.json")
    @ResponseBody
    public JsonResponse delMrStandard(BigInteger id) {
        JsonResponse jsonResponse = new JsonResponse();
        propertyPayConfigService.delMrStandard(id);
        return jsonResponse;
    }
}
