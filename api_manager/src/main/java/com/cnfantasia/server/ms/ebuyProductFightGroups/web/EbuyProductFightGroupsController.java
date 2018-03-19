package com.cnfantasia.server.ms.ebuyProductFightGroups.web;

import com.cnfantasia.server.api.ebuyProductFightGroups.entity.EbuyProductFightGroupsDto;
import com.cnfantasia.server.api.ebuyProductFightGroups.service.IEbuyProductFightGroupsService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.business.commonBusiness.constant.SmallPicUploadConfig;
import com.cnfantasia.server.business.commonBusiness.entity.WidthHeight;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.ImageZipUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.domainbase.ebuyFightSupplyMerchant.entity.EbuyFightSupplyMerchant;
import com.cnfantasia.server.domainbase.ebuyFightSupplyMerchant.service.IEbuyFightSupplyMerchantBaseService;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.domainbase.ebuyProductFightGroups.entity.EbuyProductFightGroups;
import com.cnfantasia.server.domainbase.ebuyProductFightGroups.service.IEbuyProductFightGroupsBaseService;
import com.cnfantasia.server.domainbase.ebuyProductFightPic.entity.EbuyProductFightPic;
import com.cnfantasia.server.domainbase.ebuyProductFightPic.service.IEbuyProductFightPicBaseService;
import com.cnfantasia.server.domainbase.ebuyProductShelf.entity.EbuyProductShelf;
import com.cnfantasia.server.domainbase.ebuyProductShelf.service.IEbuyProductShelfBaseService;
import com.cnfantasia.server.domainbase.ebuyProductType.entity.EbuyProductType;
import com.cnfantasia.server.domainbase.ebuyProductType.service.IEbuyProductTypeBaseService;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.service.IEbuySupplyMerchantBaseService;
import com.cnfantasia.server.ms.ebuyProduct.service.IEbuyProductService;
import com.cnfantasia.server.ms.ebuyProductType.service.IEbuyProductTypeService;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.comm.CnfantasiaCommUtil;
import com.cnfantasia.server.ms.pub.constant.PathConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 拼购管理后台
 */
@Controller
@RequestMapping(value = "/ebuyProductFightGroups")
public class EbuyProductFightGroupsController extends BaseController{

    @Resource
    private IEbuyProductFightGroupsService ebuyProductFightGroupsService;
    @Resource
    private IEbuyProductTypeBaseService ebuyProductTypeBaseService;
    @Resource
    private IEbuyFightSupplyMerchantBaseService ebuyFightSupplyMerchantBaseService;
    @Resource
	private IEbuyProductService ebuyProductService;
	@Resource
	private IEbuyProductTypeService ebuyProductTypeService;
	@Resource
	private IEbuySupplyMerchantBaseService ebuySupplyMerchantBaseService;
    @Resource
    private IEbuyProductFightPicBaseService ebuyProductFightPicBaseService;
    @Resource
    private IEbuyProductShelfBaseService ebuyProductShelfBaseService;
    @Resource
    private IEbuyProductFightGroupsBaseService ebuyProductFightGroupsBaseService;


    /**
     * 跳转新增页面
     * @return
     */
    @RequestMapping(value = "/addProductGroups.html", method = RequestMethod.GET)
    public ModelAndView jumpToAdd(String epId) {
    	ModelAndView mav = new ModelAndView("ebuyProductFightGroups/addProductGroups");
    	BigInteger productId = CnfantasiaCommUtil.convert2BigInteger(epId);

        //商品附属信息
		EbuyProduct ebuyProduct = ebuyProductService.getEbuyProductBySeqId(productId);
		EbuySupplyMerchant ebuySupplyMerchant = ebuySupplyMerchantBaseService.getEbuySupplyMerchantBySeqId(ebuyProduct.gettSupplyMerchantFId());
		EbuyProductType ebuyProductType = ebuyProductTypeService.getEbuyProductTypeBySeqId(ebuyProduct.gettEbuyProductTypeFId());

        //货架设置的价格信息
        EbuyProductShelf ebuyProductShelf = new EbuyProductShelf();
        ebuyProductShelf.settEbuyProductId(productId);
        ebuyProductShelf.setUpShelfState(0);
        List<EbuyProductShelf> shelfs = ebuyProductShelfBaseService.getEbuyProductShelfByCondition(MapConverter.toMap(ebuyProductShelf));
        if (!DataUtil.isEmpty(shelfs)) {
            for (EbuyProductShelf shelf : shelfs) {
                if ((ebuyProduct.gettEbuyProductTypeFId()).compareTo(shelf.gettEbuyProductTypeId()) == 0) {
                    mav.addObject("priceDiscount", shelf.getPriceDiscount());
                }
            }
        }
		mav.addObject("ebuyProduct", ebuyProduct);
		mav.addObject("ebuyProductType", ebuyProductType);
		mav.addObject("ebuySupplyMerchant", ebuySupplyMerchant);
        return mav;
    }

    /**
     * 新增拼购规则
     * @return
     */
    @RequestMapping(value = "/addProductGroups.html", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse addProductGroups(EbuyProductFightGroups groups, String productDesc, HttpServletRequest request) throws IOException {
        List<String> fightSupplyMerchantIds = Arrays.asList(request.getParameterValues("fightSupplyMerchantIds"));
        String image = uploadImage(request);
        String failZiti = ebuyProductFightGroupsService.addEbuyProductFightGroups(groups, productDesc, image, fightSupplyMerchantIds);
        JsonResponse json = new JsonResponse();
        if (!StringUtils.isEmpty(failZiti)) {
            json.setMessage("该商品在以下自提点重复：" + failZiti + "不再重复添加。");
        } else {
            json.setMessage("拼购发起成功！");
        }
        return json;
    }


    /**
     * 拼购列表页
     * @return
     */
    @RequestMapping(value = "/list.html")
    public ModelAndView jumpToList(EbuyProductFightGroupsDto dto, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("ebuyProductFightGroups/productGroupsList");
        String pageIndexName = new org.displaytag.util.ParamEncoder("row")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);// 页数的参数名

        int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));

        int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ?
                0 : (Integer.parseInt(request.getParameter(pageIndexName))-1);

        PageModel pageModel = new PageModel((curPageIndex)*pageSize, pageSize);
        List<EbuyProductFightGroupsDto> dtos = ebuyProductFightGroupsService.getEbuyProductFightGroupsByCondition(dto, pageModel);
        BigInteger total = ebuyProductFightGroupsService.getEbuyProductFightGroupsCount(dto);

        mav.addObject("param", dto);
        mav.addObject("resList", dtos);
        mav.addObject("resultSize", total.intValue());
        return mav;
    }

    /**
     * 查看拼购详情
     * @return
     */
    @RequestMapping(value = "/productGroupsDetail.html", method = RequestMethod.GET)
    public ModelAndView productGroupsDetail(BigInteger groupId) {
        ModelAndView mav = jumpToUpdOrDetail(groupId);
        mav.setViewName("ebuyProductFightGroups/productGroupsDetail");
        return mav;
    }

    /**
     * 跳转到编辑页面
     * @return
     */
    @RequestMapping(value = "/updProductGroups.html", method = RequestMethod.GET)
    public ModelAndView jumpToUpd(BigInteger groupId) {
        ModelAndView mav = jumpToUpdOrDetail(groupId);
        mav.setViewName("ebuyProductFightGroups/updProductGroups");
        return mav;
    }

    private ModelAndView jumpToUpdOrDetail(BigInteger groupId) {
        ModelAndView mav = new ModelAndView("ebuyProductFightGroups/productGroupsDetail");
        EbuyProductFightGroupsDto dto = new EbuyProductFightGroupsDto();
        dto.setProductFightGroupsId(groupId);
        List<EbuyProductFightGroupsDto> resList = ebuyProductFightGroupsService.getEbuyProductFightGroupsByCondition(dto);
        if (!DataUtil.isEmpty(resList)) {
            dto = resList.get(0);
            mav.addObject("dto", dto);
            BigInteger productType = dto.getEbuyProduct().gettEbuyProductTypeFId();
            //查询商品分类
            EbuyProductType ebuyProductType = ebuyProductTypeBaseService.getEbuyProductTypeBySeqId(productType);
            if (ebuyProductType != null) {
                mav.addObject("productTypeName", ebuyProductType.getTypeName());
            }
            //查自提点名称
            EbuyFightSupplyMerchant ebuyFightSupplyMerchant = ebuyFightSupplyMerchantBaseService.
                    getEbuyFightSupplyMerchantBySeqId(dto.getEbuyProductFightGroups().gettEbuyFightSupplyMerchantFId());
            if (ebuyFightSupplyMerchant != null) {
                mav.addObject("fightMerchantName", ebuyFightSupplyMerchant.getName());
            }

            //货架设置的价格信息
            EbuyProductShelf ebuyProductShelf = new EbuyProductShelf();
            ebuyProductShelf.settEbuyProductId(dto.getEbuyProduct().getId());
            ebuyProductShelf.setUpShelfState(0);
            List<EbuyProductShelf> shelfs = ebuyProductShelfBaseService.getEbuyProductShelfByCondition(MapConverter.toMap(ebuyProductShelf));
            if (!DataUtil.isEmpty(shelfs)) {
                for (EbuyProductShelf shelf : shelfs) {
                    if (productType.compareTo(shelf.gettEbuyProductTypeId()) == 0) {
                        mav.addObject("priceDiscount", shelf.getPriceDiscount());
                    }
                }
            }

            //查看拼购图片
            EbuyProductFightPic ebuyProductFightPic = new EbuyProductFightPic();
            ebuyProductFightPic.settEbuyProductFId(dto.getEbuyProduct().getId());
            PageModel pageModel = new PageModel(0, 1);
            List<EbuyProductFightPic> pics = ebuyProductFightPicBaseService.
                    getEbuyProductFightPicByCondition(MapConverter.toMap(ebuyProductFightPic), pageModel);
            if (!DataUtil.isEmpty(pics)) {
                ebuyProductFightPic = pics.get(0);
                String picPath = OmsSysParamManager.getImageServerUrl(PathConstants.Product_Image_Dir)
                        + PathConstants.Product_Image_Dir + ebuyProductFightPic.getUrlBig();
                mav.addObject("picPath", picPath);
            }
        }
        return mav;
    }

    /**
     * 修改拼购
     * @return
     */
    @RequestMapping(value = "/updProductGroups.html", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse updProductGroups(EbuyProductFightGroups groups, String productDesc, HttpServletRequest request) throws IOException {
        EbuyProductFightGroups old = ebuyProductFightGroupsBaseService.getEbuyProductFightGroupsBySeqId(groups.getId());
        if (isSysTimeLaterThan(old.getStartDate()) && !isSysTimeLaterThan(old.getExpireDate())) {
            throw new BusinessRuntimeException("ebuyProductFightGroupsController.updFightGroups.overStartDate");
        }
        String image = uploadImage(request);
        ebuyProductFightGroupsService.updateEbuyProductFightGroups(groups, productDesc, image);
        return new JsonResponse();
    }

   
	
    /**
     * 修改状态
     * @return
     */
    @RequestMapping(value = "/switchProductGroupsStatus.html", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse switchProductGroupsStatus(BigInteger id) {
        EbuyProductFightGroups ebuyProductFightGroups = new EbuyProductFightGroups();
        ebuyProductFightGroups.setId(id);
        Date end = new Date();
        Date start = new Date(end.getTime() - 1000 * 60);
        String startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(start);
        String expireDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(end);
        ebuyProductFightGroups.setStartDate(startDate);
        ebuyProductFightGroups.setExpireDate(expireDate);
        ebuyProductFightGroupsBaseService.updateEbuyProductFightGroups(ebuyProductFightGroups);
        return new JsonResponse();
    }

    /**
     * 修改状态
     * @return
     */
    @RequestMapping(value = "/batchClose.html", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse batchClose(@RequestParam(value = "closeIds[]") List<BigInteger> closeIds) {

        List<EbuyProductFightGroups> list = new ArrayList<EbuyProductFightGroups>();
        for (BigInteger closeId : closeIds) {
            EbuyProductFightGroups ebuyProductFightGroups = new EbuyProductFightGroups();
            ebuyProductFightGroups.setId(closeId);
            Date end = new Date();
            Date start = new Date(end.getTime() - 1000 * 60);
            String startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(start);
            String expireDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(end);
            ebuyProductFightGroups.setStartDate(startDate);
            ebuyProductFightGroups.setExpireDate(expireDate);
            list.add(ebuyProductFightGroups);
        }

        if(!DataUtil.isEmpty(list))
        ebuyProductFightGroupsBaseService.updateEbuyProductFightGroupsBatch(list);
        return new JsonResponse();
    }
    
    private String uploadImage(HttpServletRequest request) throws IOException {
        // 浏览图片
        String picImgs = null;
        // 详情图片
        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            // 浏览图片
            List<MultipartFile> picImageFiles = multipartRequest.getFiles("productPic");
            if (null != picImageFiles && picImageFiles.size() > 0) {
                String picPath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + PathConstants.Product_Image_Dir;
                int imgIndex = 0;
                for (MultipartFile picImageFile : picImageFiles) {
                    if (picImageFile != null && !com.cnfantasia.server.common.utils.StringUtils.isEmpty(picImageFile.getOriginalFilename())) {//有上传图片
                        int indexOfDot = picImageFile.getOriginalFilename().indexOf(".");
                        String fileNameC = (System.currentTimeMillis() + imgIndex) + picImageFile.getOriginalFilename().substring(indexOfDot);
                        File fileC = new File(picPath + fileNameC);
                        if (!fileC.exists())
                            fileC.mkdirs();
                        picImgs = fileNameC;
                        picImageFile.transferTo(fileC);
                        generateMiniImage(fileC, PathConstants.Product_Image_Dir);
                    }
                    ++imgIndex;
                }
            }
        }
        return picImgs;
    }

    /**
     * 生成小图, 并对原图都有压缩
     *
     * @param bigImageFile
     */
    private static void generateMiniImage(File bigImageFile, String imageDir) {
        Map<String, WidthHeight> guigeList = SmallPicUploadConfig.BusinessModelType.Market.getGuigeList();
        String descDirecBasePath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + imageDir;

        String fileName = bigImageFile.getName();
        int lastPointIndex = fileName.lastIndexOf(".");
        String fileType = fileName.substring(lastPointIndex); //文件后缀名，带.号

        String goalDirectoryFilePath = descDirecBasePath + fileName.substring(0, lastPointIndex);

        // 创建小图的目标目录
        File goalFileDir = new File(goalDirectoryFilePath);
        if (!goalFileDir.exists()) {
            goalFileDir.mkdirs();
        }

        for (String goalFileName : guigeList.keySet()) {
            String smallIcon = goalDirectoryFilePath + "/" + goalFileName + fileType;
            try {
                WidthHeight tmpWidthHeight = guigeList.get(goalFileName);
                ImageZipUtil.zipImageFile(bigImageFile.getAbsolutePath(), null, tmpWidthHeight.getHeight(), 1f, smallIcon);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static boolean isSysTimeLaterThan(String desTime) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(desTime);
        } catch (ParseException e) {
            throw new RuntimeException("Time parse error:"+e.getMessage()+",time is:"+desTime);
        }
        return new Date().getTime() - date.getTime() > 0;
    }
}
