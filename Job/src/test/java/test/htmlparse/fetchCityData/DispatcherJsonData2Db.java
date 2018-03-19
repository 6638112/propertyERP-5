/**   
* Filename:    DispatcherJsonData2Db.java   
* @version:    1.0  
* Create at:   2014年11月6日 上午7:55:22   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年11月6日    shiyl      1.0         1.0 Version   
*/
package test.htmlparse.fetchCityData;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.common.utils.FileUtils;
import com.cnfantasia.server.common.utils.PinyinUtil;
import com.cnfantasia.server.domainbase.addressBlock.entity.AddressBlock;
import com.cnfantasia.server.domainbase.addressCity.entity.AddressCity;
import com.cnfantasia.server.domainbase.addressProvince.entity.AddressProvince;

/**
 * Filename:    DispatcherJsonData2Db.java
 * @version:    1.0.0
 * Create at:   2014年11月6日 上午7:55:22
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年11月6日       shiyl             1.0             1.0 Version
 */
public class DispatcherJsonData2Db extends BaseData2Db{
	public static final String provinceJsonPath = "F:\\addressData\\province.json";
	public static final String cityJsonPath = "F:\\addressData\\city.json";
	public static final String blockJsonPath = "F:\\addressData\\county.json";
	
	public static void main(String[] args) throws Exception {
//		DispatcherJsonData2Db jsonData2Db = new DispatcherJsonData2Db();
//		jsonData2Db.main();
//		String placeName = "亳州市";
//		System.out.println(getFirstPinyin(placeName));
	}
	
	public static String getFirstPinyin(String data) throws BadHanyuPinyinOutputFormatCombination{
		return PinyinUtil.hanyuToPinyinFirst(data).substring(0, 1);
	}
	
	/*
	 * ok--trim 的处理
	 *  ok --删除 名字为 直辖市 的数据 省直辖县级行政区划
	 *  ok --彩之云数据的省市区划分与 国家划分的不同
	 *  TODO 校验数据库的placeCode为空的不合理数据
	 *  TODO  网址抓取数据 警告提示
	 *  
	 *  删除主外键 删除所有数据 
	 *  录入生产环境初始数据
	 *  执行导入（设定true,数据库）
	 *  执行调整的sql
	 *  重新创建外键
	 *  
	 *  后续 甘肃省嘉峪关 市辖区的问题
	 */
//	@Test
	public  void main() throws Exception {
		//省数据
		parseProvinceData(provinceJsonPath);
		//市数据
		parseCityData(cityJsonPath);
		//区数据
		parseBlockData(blockJsonPath);
		//输出执行
		showResultAddData();
		showResultUpdData();
		if(opderDbBatch){
			insertDataBatch();//先
			updateDataBatch();//后
		}
		showLastStartId();
	}
	
	public void parseProvinceData(String srcFilePath) throws IOException{
		String srcDataJson = new String(FileUtils.fileToByte(srcFilePath));
		List<AddressInfoEntity> provinceList =JSON.parseArray(srcDataJson, AddressInfoEntity.class);
		for(AddressInfoEntity tmpMap : provinceList){
			System.out.println(tmpMap);
			String placeName = (String)tmpMap.getPlaceName();placeName=placeName==null?null:placeName.trim();
			String placeCode = (String)tmpMap.getPlaceCode();placeCode=placeCode==null?null:placeCode.trim();
			String totalCode = (String)tmpMap.getTotalCode();totalCode=totalCode==null?null:totalCode.trim();
			String subUrl = (String)tmpMap.getSubUrl();subUrl=subUrl==null?null:subUrl.trim();
			addProvinceData(placeName, placeCode, totalCode, new BigInteger("-1"));
		}
	}
	
	public void parseCityData(String srcFilePath) throws Exception{
		String srcDataJson = new String(FileUtils.fileToByte(srcFilePath));
		List<AddressInfoEntity> dataList =JSON.parseArray(srcDataJson, AddressInfoEntity.class);
		for(AddressInfoEntity tmpMap : dataList){
			System.out.println(tmpMap);
			String placeName = (String)tmpMap.getPlaceName();placeName=placeName==null?null:placeName.trim();
			String placeCode = (String)tmpMap.getPlaceCode();placeCode=placeCode==null?null:placeCode.trim();
			String totalCode = (String)tmpMap.getTotalCode();totalCode=totalCode==null?null:totalCode.trim();
//			String subUrl = (String)tmpMap.getSubUrl();subUrl=subUrl==null?null:subUrl.trim();
			if(placeName.equals("市辖区")){
				addCityData(tmpMap.getParentAddressInfoEntity().getPlaceName()+"辖区", placeCode, totalCode, tmpMap.getParentAddressInfoEntity().getPlaceCode(),1);
//				placeName = tmpMap.getParentAddressInfoEntity().getPlaceName()/*+"辖区"*/;
//				placeCode = tmpMap.getParentAddressInfoEntity().getPlaceCode();
//				totalCode = tmpMap.getParentAddressInfoEntity().getTotalCode();
			}else if(placeName.equals("县")){
				addCityData(tmpMap.getParentAddressInfoEntity().getPlaceName()+"辖县", placeCode, totalCode, tmpMap.getParentAddressInfoEntity().getPlaceCode(),1);
//				placeName = tmpMap.getParentAddressInfoEntity().getPlaceName()/*+"辖县"*/;
//				placeCode = tmpMap.getParentAddressInfoEntity().getPlaceCode();
//				totalCode = tmpMap.getParentAddressInfoEntity().getTotalCode();
			}else if(placeName.equals("省直辖县级行政区划")){
				addCityData(tmpMap.getParentAddressInfoEntity().getPlaceName()+"直辖县级行政区划", placeCode, totalCode, tmpMap.getParentAddressInfoEntity().getPlaceCode(),1);
//				placeName = tmpMap.getParentAddressInfoEntity().getPlaceName()+"直辖县级行政区划";
			}else{
				addCityData(placeName, placeCode, totalCode, tmpMap.getParentAddressInfoEntity().getPlaceCode(),0);
			}
			
		}
	}
	
	public void parseBlockData(String srcFilePath) throws IOException, BadHanyuPinyinOutputFormatCombination{
		String srcDataJson = new String(FileUtils.fileToByte(srcFilePath));
		List<AddressInfoEntity> dataList =JSON.parseArray(srcDataJson, AddressInfoEntity.class);
		for(AddressInfoEntity tmpMap : dataList){
			System.out.println(tmpMap);
			String placeName = (String)tmpMap.getPlaceName();placeName=placeName==null?null:placeName.trim();
			String placeCode = (String)tmpMap.getPlaceCode();placeCode=placeCode==null?null:placeCode.trim();
			String totalCode = (String)tmpMap.getTotalCode();totalCode=totalCode==null?null:totalCode.trim();
			String subUrl = (String)tmpMap.getSubUrl();subUrl=subUrl==null?null:subUrl.trim();
			if(tmpMap.getParentAddressInfoEntity().getPlaceName().equals("省直辖县级行政区划")){
				BigInteger cityId = addCityData(placeName, placeCode, totalCode, tmpMap.getParentAddressInfoEntity().getParentAddressInfoEntity().getPlaceCode(), 0);
				addBlockData(placeName, placeCode, totalCode, cityId);
			}else if(tmpMap.getParentAddressInfoEntity().getPlaceName().equals("市辖区")||tmpMap.getParentAddressInfoEntity().getPlaceName().equals("县")){
				BigInteger cityId = addCityData(tmpMap.getParentAddressInfoEntity().getParentAddressInfoEntity().getPlaceName()
						, tmpMap.getParentAddressInfoEntity().getParentAddressInfoEntity().getPlaceCode()
						, tmpMap.getParentAddressInfoEntity().getParentAddressInfoEntity().getTotalCode()
						, tmpMap.getParentAddressInfoEntity().getParentAddressInfoEntity().getPlaceCode(), 0);
				addBlockData(placeName, placeCode, totalCode, cityId);
			}else{
				addBlockData(placeName, placeCode, totalCode, tmpMap.getParentAddressInfoEntity().getPlaceCode());
			}
			
		}
	}
	
	private BigInteger addProvinceData(String placeName,String placeCode,String totalCode,BigInteger parentId){
		//校验是否存在
		BigInteger addressProvinceId =  getIdByAddressProvinceName(placeName,parentId);
		if(addressProvinceId==null){//不存在则新增
			addressProvinceId = get_address_province_Id();
			AddressProvince tmpAddressProvince = new AddressProvince(addressProvinceId, placeName, placeCode, totalCode, parentId, null, null, null, null, null, null, 0);
			addressProvinceListAdd.add(tmpAddressProvince);
		}else{//存在则更新
			AddressProvince tmpAddressProvince = new AddressProvince(addressProvinceId, placeName, placeCode, totalCode, parentId, null, null, null, null, null, null, 0);
			addressProvinceListUpd.add(tmpAddressProvince);
		}
		return addressProvinceId;
	}
	
	private BigInteger addCityData(String placeName,String placeCode,String totalCode,String parentPlaceCode,int delState) throws BadHanyuPinyinOutputFormatCombination{
		//根据Code查询对应的parentId
		BigInteger tProvinceFId = getIdByAddressProvinceCode(parentPlaceCode);
		if(tProvinceFId==null){
			throw new RuntimeException(parentPlaceCode+"对应的省信息不存在。"+placeName+"--"+placeCode+"--"+totalCode);
		}
		return addCityData(placeName, placeCode, totalCode, tProvinceFId, delState);
	}
	private BigInteger addCityData(String placeName,String placeCode,String totalCode,BigInteger parentId,int delState) throws BadHanyuPinyinOutputFormatCombination{
		String alpha = getFirstPinyin(placeName).toUpperCase();
		//校验是否存在
		BigInteger addressCityId =  getIdByAddressCityName(placeName,parentId);
		if(addressCityId==null){//不存在则新增
			addressCityId = get_address_city_Id();
			AddressCity tmpAddressCity = new AddressCity(addressCityId, placeName, parentId, alpha, placeCode, totalCode, null, null, null, null, null, null, delState);
			addressCityListAdd.add(tmpAddressCity);
		}else{//存在则更新
			AddressCity tmpAddressCity = new AddressCity(addressCityId, placeName, parentId, alpha, placeCode, totalCode, null, null, null, null, null, null, delState);
			addressCityListUpd.add(tmpAddressCity);
		}
		return addressCityId;
	}
	
	
	private BigInteger addBlockData(String placeName,String placeCode,String totalCode,String parentPlaceCode){
	//根据Code查询对应的parentId
		BigInteger tCityFId = getIdByAddressCityCode(parentPlaceCode);
		if(tCityFId==null){
			throw new RuntimeException(parentPlaceCode+"对应的市信息不存在。"+placeName+"--"+placeCode+"--"+totalCode);
		}
		return addBlockData(placeName, placeCode, totalCode, tCityFId);
	}
	private BigInteger addBlockData(String placeName,String placeCode,String totalCode,BigInteger parentId){
	//	ChineseCharToEn cte = new ChineseCharToEn();
	//	String alpha = cte.getFirstLetter(placeName).toUpperCase();
		//校验是否存在
		BigInteger addressBlockId =  getIdByAddressBlockName(placeName,parentId);
		if(addressBlockId==null){//不存在则新增
			addressBlockId = get_address_block_Id();
			AddressBlock tmpAddressBlock = new AddressBlock(addressBlockId, placeName, parentId, placeCode, totalCode, null, null, null, null, null, null, 0);
			addressBlockListAdd.add(tmpAddressBlock);
		}else{//存在则更新
			AddressBlock tmpAddressBlock = new AddressBlock(addressBlockId, placeName, parentId, placeCode, totalCode, null, null, null, null, null, null, 0);
			addressBlockListUpd.add(tmpAddressBlock);
		}
		return addressBlockId;
	}
	
}
