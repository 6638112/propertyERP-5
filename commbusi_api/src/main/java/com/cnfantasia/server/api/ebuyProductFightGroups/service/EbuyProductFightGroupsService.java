package com.cnfantasia.server.api.ebuyProductFightGroups.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.cnfantasia.server.api.ebuyProductFightGroups.dao.IEbuyProductFightGroupsDao;
import com.cnfantasia.server.api.ebuyProductFightGroups.entity.EbuyProductFightGroupsDto;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyFightSupplyMerchant.entity.EbuyFightSupplyMerchant;
import com.cnfantasia.server.domainbase.ebuyFightSupplyMerchant.service.IEbuyFightSupplyMerchantBaseService;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.domainbase.ebuyProduct.service.IEbuyProductBaseService;
import com.cnfantasia.server.domainbase.ebuyProductFightGroups.entity.EbuyProductFightGroups;
import com.cnfantasia.server.domainbase.ebuyProductFightGroups.service.IEbuyProductFightGroupsBaseService;
import com.cnfantasia.server.domainbase.ebuyProductFightPic.entity.EbuyProductFightPic;
import com.cnfantasia.server.domainbase.ebuyProductFightPic.service.IEbuyProductFightPicBaseService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kangduo on 2016.05.27
 */
public class EbuyProductFightGroupsService implements IEbuyProductFightGroupsService {

    private IEbuyProductFightGroupsDao ebuyProductFightGroupsDao;

    private IEbuyProductFightGroupsBaseService ebuyProductFightGroupsBaseService;

    private IUuidManager uuidManager;

    private IEbuyProductFightPicBaseService ebuyProductFightPicBaseService;

    private IEbuyProductBaseService ebuyProductBaseService;

    private IEbuyFightSupplyMerchantBaseService ebuyFightSupplyMerchantBaseService;


    @Override
    public List<EbuyProductFightGroupsDto> getEbuyProductFightGroupsByCondition(EbuyProductFightGroupsDto dto, PageModel pageModel) {
        return ebuyProductFightGroupsDao.getEbuyProductFightGroupsByCondition(dto, pageModel);
    }

    @Override
    public List<EbuyProductFightGroupsDto> getEbuyProductFightGroupsByCondition(EbuyProductFightGroupsDto dto) {
        return getEbuyProductFightGroupsByCondition(dto, null);
    }

    @Override
    public BigInteger getEbuyProductFightGroupsCount(EbuyProductFightGroupsDto dto) {
        return ebuyProductFightGroupsDao.getEbuyProductFightGroupsCount(dto);
    }

    @Override
    @Transactional
    public void updateEbuyProductFightGroups(EbuyProductFightGroups ebuyProductFightGroups, String productDesc, String image) {
        //更新拼购
        ebuyProductFightGroupsBaseService.updateEbuyProductFightGroups(ebuyProductFightGroups);
        //更新同一商品的拼购价格
        ebuyProductFightGroupsDao.updateProductFightGroupsPriceByProductId(
                ebuyProductFightGroups.gettEbuyProductFId(),ebuyProductFightGroups.getPriceDiscount());
        //处理图片
        if (!StringUtils.isEmpty(image)) {
            ebuyProductFightGroups = ebuyProductFightGroupsBaseService.getEbuyProductFightGroupsBySeqId(ebuyProductFightGroups.getId());
            BigInteger productId = ebuyProductFightGroups.gettEbuyProductFId();
            EbuyProductFightPic ebuyProductFightPic = new EbuyProductFightPic();
            ebuyProductFightPic.settEbuyProductFId(productId);
            //处理以前的图片
            List<EbuyProductFightPic> pics = ebuyProductFightPicBaseService.getEbuyProductFightPicByCondition(MapConverter.toMap(ebuyProductFightPic));
            List<BigInteger> picIds = new ArrayList<BigInteger>();
            for (EbuyProductFightPic pic : pics) {
                picIds.add(pic.getId());
            }
            if (!DataUtil.isEmpty(picIds)) {
                ebuyProductFightPicBaseService.deleteEbuyProductFightPicLogicBatch(picIds);
            }
            //增加图片
            ebuyProductFightPic.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_product_fight_pic));
            ebuyProductFightPic.setUrlBig(image);
            ebuyProductFightPic.setUrlMini(image);
            ebuyProductFightPicBaseService.insertEbuyProductFightPic(ebuyProductFightPic);
        }
        //拼购描述处理
        if (!StringUtils.isEmpty(productDesc)) {
            EbuyProduct ebuyProduct = new EbuyProduct();
            ebuyProduct.setId(ebuyProductFightGroups.gettEbuyProductFId());
            ebuyProduct.setDesc(productDesc);
            ebuyProductBaseService.updateEbuyProduct(ebuyProduct);
        }
    }
    
    @Override
    @Transactional
    public String addEbuyProductFightGroups(EbuyProductFightGroups ebuyProductFightGroups, String productDesc, String image, List<String> ziTidianIds){
        StringBuilder sb = new StringBuilder();
        if (!DataUtil.isEmpty(ziTidianIds)) {
            List<BigInteger> ids = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_product_fight_groups, ziTidianIds.size());
            List<EbuyProductFightGroups> fightGroupses = new ArrayList<EbuyProductFightGroups>(ziTidianIds.size());
            EbuyProductFightGroups tmp = null;
            for (int i = 0; i < ziTidianIds.size(); i++) {
                tmp = new EbuyProductFightGroups();
                tmp.settEbuyProductFId(ebuyProductFightGroups.gettEbuyProductFId());
                tmp.settEbuyFightSupplyMerchantFId(new BigInteger(ziTidianIds.get(i)));
                int count = ebuyProductFightGroupsBaseService.getEbuyProductFightGroupsCount(MapConverter.toMap(tmp));
                if (count <= 0) {
                    tmp = new EbuyProductFightGroups(ebuyProductFightGroups);
                    tmp.setId(ids.get(i));
                    tmp.settEbuyProductFId(ebuyProductFightGroups.gettEbuyProductFId());
                    tmp.settEbuyFightSupplyMerchantFId(new BigInteger(ziTidianIds.get(i)));
                    fightGroupses.add(tmp);
                } else {
                    EbuyFightSupplyMerchant ebuyFightSupplyMerchant = ebuyFightSupplyMerchantBaseService.getEbuyFightSupplyMerchantBySeqId(new BigInteger(ziTidianIds.get(i)));
                    sb.append(ebuyFightSupplyMerchant.getName()).append(",");
                }
            }
            if (fightGroupses.size() > 0) {
                ebuyProductFightGroupsBaseService.insertEbuyProductFightGroupsBatch(fightGroupses);
            }

            //图片处理
            if (!StringUtils.isEmpty(image)) {
                EbuyProductFightPic ebuyProductFightPic = new EbuyProductFightPic();
                ebuyProductFightPic.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_product_fight_pic));
                ebuyProductFightPic.setUrlBig(image);
                ebuyProductFightPic.setUrlMini(image);
                ebuyProductFightPic.settEbuyProductFId(ebuyProductFightGroups.gettEbuyProductFId());
                ebuyProductFightPicBaseService.insertEbuyProductFightPic(ebuyProductFightPic);
            }
            //拼购描述处理
            if (!StringUtils.isEmpty(productDesc)) {
                EbuyProduct ebuyProduct = new EbuyProduct();
                ebuyProduct.setId(ebuyProductFightGroups.gettEbuyProductFId());
                ebuyProduct.setDesc(productDesc);
                ebuyProductBaseService.updateEbuyProduct(ebuyProduct);
            }
        }
        return sb.toString();
    }

    public void setEbuyProductFightGroupsDao(IEbuyProductFightGroupsDao ebuyProductFightGroupsDao) {
        this.ebuyProductFightGroupsDao = ebuyProductFightGroupsDao;
    }

    public void setEbuyProductFightGroupsBaseService(IEbuyProductFightGroupsBaseService ebuyProductFightGroupsBaseService) {
        this.ebuyProductFightGroupsBaseService = ebuyProductFightGroupsBaseService;
    }

    public void setUuidManager(IUuidManager uuidManager) {
        this.uuidManager = uuidManager;
    }

    public void setEbuyProductFightPicBaseService(IEbuyProductFightPicBaseService ebuyProductFightPicBaseService) {
        this.ebuyProductFightPicBaseService = ebuyProductFightPicBaseService;
    }

    public void setEbuyProductBaseService(IEbuyProductBaseService ebuyProductBaseService) {
        this.ebuyProductBaseService = ebuyProductBaseService;
    }

    public void setEbuyFightSupplyMerchantBaseService(IEbuyFightSupplyMerchantBaseService ebuyFightSupplyMerchantBaseService) {
        this.ebuyFightSupplyMerchantBaseService = ebuyFightSupplyMerchantBaseService;
    }
}
