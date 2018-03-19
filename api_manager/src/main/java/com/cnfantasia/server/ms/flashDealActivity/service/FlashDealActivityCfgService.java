package com.cnfantasia.server.ms.flashDealActivity.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.domainbase.ebuyProduct.service.IEbuyProductBaseService;
import com.cnfantasia.server.domainbase.ebuySupplyMerchantHasGroupBuilding.entity.EbuySupplyMerchantHasGroupBuilding;
import com.cnfantasia.server.domainbase.ebuySupplyMerchantHasGroupBuilding.service.IEbuySupplyMerchantHasGroupBuildingBaseService;
import com.cnfantasia.server.domainbase.flashDealActivity.service.IFlashDealActivityBaseService;
import com.cnfantasia.server.domainbase.operationSaHasEbSupply.entity.OperationSaHasEbSupply;
import com.cnfantasia.server.domainbase.operationSaHasEbSupply.service.IOperationSaHasEbSupplyBaseService;
import com.cnfantasia.server.domainbase.operationServiceArea.entity.OperationServiceArea;
import com.cnfantasia.server.domainbase.operationServiceArea.service.IOperationServiceAreaBaseService;
import com.cnfantasia.server.ms.advertise.constants.AdvConstant;
import com.cnfantasia.server.ms.flashDealActivity.dao.IFlashDealActivityCfgDao;
import com.cnfantasia.server.ms.flashDealActivity.entity.FlashDealActivityCfgParam;

/**
 * @ClassName: FlashDealActivityCfgService
 * @Date: 2016-11-23 16:52
 * @Auther: yanghua
 * @Description:(幸运购活动配置)
 */
public class FlashDealActivityCfgService implements IFlashDealActivityCfgService{

    @Resource
    private IFlashDealActivityBaseService flashDealActivityBaseService;
    @Resource
    private IUuidManager uuidManager;
    @Resource
    private IEbuyProductBaseService ebuyProductBaseService;
    @Resource
    private IEbuySupplyMerchantHasGroupBuildingBaseService ebuySupplyMerchantHasGroupBuildingBaseService;
    @Resource
    private IOperationServiceAreaBaseService operationServiceAreaBaseService;
    @Resource
    private IOperationSaHasEbSupplyBaseService operationSaHasEbSupplyBaseService;
    @Resource
    private IFlashDealActivityCfgDao flashDealActivityCfgDao;

    @Override
    @Transactional(propagation= Propagation.NESTED)
    public void saveFlashDealActivityCfg(FlashDealActivityCfgParam flashDealActivityCfgParam) {
        if(flashDealActivityCfgParam.getId() != null) {//更新
            updateFlashDealActivityCfg(flashDealActivityCfgParam);
        } else {//新增
            addFlashDealActivityCfg(flashDealActivityCfgParam);
        }
    }

    /**
     * 新增幸运购活动配置
     * @param flashDealActivityCfgParam
     */
    private void addFlashDealActivityCfg(FlashDealActivityCfgParam flashDealActivityCfgParam) {
        //保存幸运购信息_flash_deal_activity
        flashDealActivityCfgParam.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_flash_deal_activity));
        //拉取商品图片
        EbuyProduct ebuyProduct = ebuyProductBaseService.getEbuyProductBySeqId(flashDealActivityCfgParam.gettEbuyProductFId());
        if(ebuyProduct != null) {
            flashDealActivityCfgParam.setActivityPic(ebuyProduct.getPicBase());
        } else {
            flashDealActivityCfgParam.setActivityPic("1.jpg");//默认图片
        }
        flashDealActivityCfgParam.setActivityPrice(flashDealActivityCfgParam.getPrice());
        flashDealActivityCfgParam.setIsSettle(0);
        flashDealActivityCfgParam.setIntroduceRule(flashDealActivityCfgParam.getIntroduceContent());
        int flashCount = flashDealActivityBaseService.insertFlashDealActivity(flashDealActivityCfgParam);
        if(flashCount == 0) {
            throw new BusinessRuntimeException("幸运购活动添加失败！");
        }
        //保存t_operation_sa_has_eb_supply
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tEbuySupplyMerchantId", flashDealActivityCfgParam.getEbuySupplyMerchantId());
        List<EbuySupplyMerchantHasGroupBuilding> ebuySupplyMerchantHasGroupBuildingByCondition = ebuySupplyMerchantHasGroupBuildingBaseService.getEbuySupplyMerchantHasGroupBuildingByCondition(paramMap);
        if(ebuySupplyMerchantHasGroupBuildingByCondition != null && ebuySupplyMerchantHasGroupBuildingByCondition.size() > 0) {
            BigInteger gbId = ebuySupplyMerchantHasGroupBuildingByCondition.get(0).gettGroupBuildingId();
            paramMap.clear();
            paramMap.put("tEbuySupplyMerchantId", gbId);
            List<OperationServiceArea> operationServiceAreaByCondition = operationServiceAreaBaseService.getOperationServiceAreaByCondition(paramMap);
            if(operationServiceAreaByCondition != null && operationServiceAreaByCondition.size() > 0) {
                OperationSaHasEbSupply operationSaHasEbSupply = new OperationSaHasEbSupply();
                operationSaHasEbSupply.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_operation_sa_has_eb_supply));
                operationSaHasEbSupply.setType(AdvConstant.EbSupplyType.Yi_Yuan_Go);
                operationSaHasEbSupply.setSaId(operationServiceAreaByCondition.get(0).getId());
                operationSaHasEbSupply.setEbSupplyId(flashDealActivityCfgParam.getId());
                operationSaHasEbSupply.setRelation(1);
                int operaCount = operationSaHasEbSupplyBaseService.insertOperationSaHasEbSupply(operationSaHasEbSupply);
                if(operaCount == 0) {
                    throw new BusinessRuntimeException("周边服务于电商商家添加失败！");
                }
            } else {
                throw new BusinessRuntimeException("服务范围为空！");
            }
        } else {
            throw new BusinessRuntimeException("供应没有关联小区！");
        }
    }

    /**
     * 更新幸运购活动配置
     * @param flashDealActivityCfgParam
     */
    private void updateFlashDealActivityCfg(FlashDealActivityCfgParam flashDealActivityCfgParam) {
        flashDealActivityCfgParam.setActivityPrice(flashDealActivityCfgParam.getPrice());
        int flashCount = flashDealActivityBaseService.updateFlashDealActivity(flashDealActivityCfgParam);
        if(flashCount == 0) {
            throw new BusinessRuntimeException("幸运购活动更新失败！");
        }
    }

    @Override
    public List<FlashDealActivityCfgParam> getFlashDealActivityList(Map<String, Object> paramMap) {
        return flashDealActivityCfgDao.getFlashDealActivityList(paramMap);
    }

    @Override
    public int getFlashDealActivityListCount(Map<String, Object> paramMap) {
        return flashDealActivityCfgDao.getFlashDealActivityListCount(paramMap);
    }

    @Override
    public FlashDealActivityCfgParam getFlashDealActivityDetail(BigInteger flashDealActivityId) {
        return flashDealActivityCfgDao.getFlashDealActivityDetail(flashDealActivityId);
    }
}
