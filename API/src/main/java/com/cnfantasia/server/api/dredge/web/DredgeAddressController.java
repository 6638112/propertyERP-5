package com.cnfantasia.server.api.dredge.web;

import com.cnfantasia.server.api.dredgeAddress.entity.DredgeAddressEntity;
import com.cnfantasia.server.api.dredgeAddress.service.IDredgeAddressService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.domainbase.dredgeAddress.service.IDredgeAddressBaseService;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: DredgeAddressController.
 * @Date: 2017-06-27 10:45
 * @Auther: kangduo
 * @Description: ()
 */
@Controller
@RequestMapping("/dredgeAddress")
public class DredgeAddressController extends BaseController {

    @Resource
    private IDredgeAddressBaseService dredgeAddressBaseService;
    @Resource
    private IDredgeAddressService dredgeAddressService;

    /**
     * 维修地址列表，附带校验商品.
     * @return 维修地址列表
     */
    @RequestMapping(value = "/dredgeAddressList.json")
    @ResponseBody
    public JsonResponse getDredgeAddressList(BigInteger dredgeProductId) {
        BigInteger userId = UserContext.getOperIdBigInteger();
        List<DredgeAddressEntity> dredgeAddressList = dredgeAddressService.getDredgeAddressList(userId, dredgeProductId);
        JsonResponse json = new JsonResponse();
        json.putData("list", dredgeAddressList);
        return json;
    }

    /**
     * 新增维修地址.
     * @return
     */
    @RequestMapping(value = "/addDredgeAddress.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse addDredgeAddress(DredgeAddressEntity entity) {
        BigInteger addressId = dredgeAddressService.addDredgeAddress(entity);
        JsonResponse json = new JsonResponse();
        json.putData("addressId", addressId);
        return json;
    }

    /**
     * 删除维修地址.
     * @param dredgeAddressId
     * @return
     */
    @RequestMapping(value = "/delDredgeAddress.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse delDredgeAddress(BigInteger dredgeAddressId) {
        dredgeAddressBaseService.deleteDredgeAddressLogic(dredgeAddressId);
        return new JsonResponse();
    }

    /**
     * 修改维修地址.
     * @return
     */
    @RequestMapping(value = "/updDredgeAddress.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse updDredgeAddress(DredgeAddressEntity entity) {
        dredgeAddressService.updDredgeAddress(entity);
        return new JsonResponse();
    }
}
