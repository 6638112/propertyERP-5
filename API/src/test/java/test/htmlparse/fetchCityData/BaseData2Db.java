/**   
* Filename:    BaseData2Db.java   
* @version:    1.0  
* Create at:   2014年11月6日 上午8:43:02   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年11月6日    shiyl      1.0         1.0 Version   
*/
package test.htmlparse.fetchCityData;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.BaseTest;
import com.cnfantasia.server.domainbase.addressBlock.entity.AddressBlock;
import com.cnfantasia.server.domainbase.addressBlock.service.IAddressBlockBaseService;
import com.cnfantasia.server.domainbase.addressCity.entity.AddressCity;
import com.cnfantasia.server.domainbase.addressCity.service.IAddressCityBaseService;
import com.cnfantasia.server.domainbase.addressProvince.entity.AddressProvince;
import com.cnfantasia.server.domainbase.addressProvince.service.IAddressProvinceBaseService;

/**
 * Filename:    BaseData2Db.java
 * @version:    1.0.0
 * Create at:   2014年11月6日 上午8:43:02
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年11月6日       shiyl             1.0             1.0 Version
 */
public class BaseData2Db extends BaseTest{
	/**是否连接数据库获取数据*/
	public static final boolean connect2Db = true;
//	/**是否校验数据*/
//	public static final boolean throwException = true;
	/**是否更新数据库*/
	public static final boolean opderDbBatch = false;
	
	IAddressProvinceBaseService addressProvinceBaseService;
	IAddressCityBaseService addressCityBaseService;
	IAddressBlockBaseService addressBlockBaseService;
	
	@Override
	public void init() {
		super.init();
		addressProvinceBaseService = ctx.getBean("addressProvinceBaseService", IAddressProvinceBaseService.class);
		addressCityBaseService = ctx.getBean("addressCityBaseService", IAddressCityBaseService.class);
		addressBlockBaseService= ctx.getBean("addressBlockBaseService", IAddressBlockBaseService.class);
	}
	/**省数据*/
	List<AddressProvince> addressProvinceListAdd = new ArrayList<AddressProvince>();//待新增
	List<AddressProvince> addressProvinceListUpd = new ArrayList<AddressProvince>();//待更新
	/**市数据*/
	List<AddressCity> addressCityListAdd = new ArrayList<AddressCity>();//待新增
	List<AddressCity> addressCityListUpd = new ArrayList<AddressCity>();//待更新
	/**区数据*/
	List<AddressBlock> addressBlockListAdd = new ArrayList<AddressBlock>();//待新增
	List<AddressBlock> addressBlockListUpd = new ArrayList<AddressBlock>();//待更新
	
	//当前数据库数据
	private List<AddressProvince> addressProvinceList_db;
	private List<AddressCity> addressCityList_db;
	private List<AddressBlock> addressBlockList_db;
	
	public static  BigInteger t_address_province_startId = new BigInteger("5000");//5000-5011;
	public static BigInteger get_address_province_Id(){
		t_address_province_startId=t_address_province_startId.add(new BigInteger("1"));
		return t_address_province_startId;
	}
	
	public static  BigInteger t_address_city_startId = new BigInteger("5000");//5000-5290,5311;
	public static BigInteger get_address_city_Id(){
		t_address_city_startId=t_address_city_startId.add(new BigInteger("1"));
		return t_address_city_startId;
	}
	
	public static  BigInteger t_address_block_startId = new BigInteger("5000");//5000-7640,5837,7709,7707,7686;
	public static BigInteger get_address_block_Id(){
		t_address_block_startId=t_address_block_startId.add(new BigInteger("1"));
		return t_address_block_startId;
	}
	
	public void insertDataBatch(){
		if(addressProvinceListAdd.size()>0){
			addressProvinceBaseService.insertAddressProvinceBatch(addressProvinceListAdd);
		}
		if(addressCityListAdd.size()>0){
			addressCityBaseService.insertAddressCityBatch(addressCityListAdd);
		}
		if(addressBlockListAdd.size()>0){
			List<AddressBlock> tmpList = new ArrayList<AddressBlock>();
			for(int i=0;i<addressBlockListAdd.size();i++){
				tmpList.add(addressBlockListAdd.get(i));
				if(i%1000==0){
					if(tmpList.size()>0){
						addressBlockBaseService.insertAddressBlockBatch(tmpList);
					}
					tmpList.clear();
				}
			}
			if(tmpList.size()>0){
				addressBlockBaseService.insertAddressBlockBatch(tmpList);
			}
//			addressBlockBaseService.insertAddressBlockBatch(addressBlockListAdd);
		}
	}
	public void updateDataBatch(){
		if(addressProvinceListUpd.size()>0){
			addressProvinceBaseService.updateAddressProvinceBatch(addressProvinceListUpd);
		}
		if(addressCityListUpd.size()>0){
			addressCityBaseService.updateAddressCityBatch(addressCityListUpd);
		}
		if(addressBlockListUpd.size()>0){
			List<AddressBlock> tmpList = new ArrayList<AddressBlock>();
			for(int i=0;i<addressBlockListUpd.size();i++){
				tmpList.add(addressBlockListUpd.get(i));
				if(i%1000==0){
					if(tmpList.size()>0){
						addressBlockBaseService.updateAddressBlockBatch(tmpList);
					}
					tmpList.clear();
				}
			}
			if(tmpList.size()>0){
				addressBlockBaseService.updateAddressBlockBatch(tmpList);
			}
//			addressBlockBaseService.updateAddressBlockBatch(addressBlockListUpd);
		}
	}
	
	public void showResultAddData(){
		System.out.println("待新增的省数据"+addressProvinceListAdd.size()+"个");
		for(AddressProvince tmpAddressProvince:addressProvinceListAdd){
			System.out.println(tmpAddressProvince);
		}
		System.out.println("待新增的市数据"+addressCityListAdd.size()+"个");
		for(AddressCity tmpAddressCity:addressCityListAdd){
			System.out.println(tmpAddressCity);
		}
		System.out.println("待新增的区数据"+addressBlockListAdd.size()+"个");
		for(AddressBlock tmpAddressBlock:addressBlockListAdd){
			System.out.println(tmpAddressBlock);
		}
	}
	public void showResultUpdData(){
		System.out.println("待更新的省数据"+addressProvinceListUpd.size()+"个");
		for(AddressProvince tmpAddressProvince:addressProvinceListUpd){
			System.out.println(tmpAddressProvince);
		}
		System.out.println("待更新的市数据"+addressCityListUpd.size()+"个");
		for(AddressCity tmpAddressCity:addressCityListUpd){
			System.out.println(tmpAddressCity);
		}
		System.out.println("待更新的区数据"+addressBlockListUpd.size()+"个");
		for(AddressBlock tmpAddressBlock:addressBlockListUpd){
			System.out.println(tmpAddressBlock);
		}
	}
	public void showLastStartId() {
		System.out.println("t_address_province_startId="+t_address_province_startId);
		System.out.println("t_address_city_startId="+t_address_city_startId);
		System.out.println("t_address_block_startId="+t_address_block_startId);
	}
	
	/**
	 * 通过省名称查询已存在的Id
	 * @param name
	 * @return
	 */
	public BigInteger getIdByAddressProvinceName(String name,BigInteger parentId){
		Set<BigInteger> existIds = new HashSet<BigInteger>();
		for(AddressProvince tmpAddressProvince:addressProvinceListAdd){
			if(tmpAddressProvince.getName().trim().equals(name)&&tmpAddressProvince.gettAddressCountryFId().compareTo(parentId)==0){
				existIds.add(tmpAddressProvince.getId());
//				return tmpAddressProvince.getId();
			}
		}
		for(AddressProvince tmpAddressProvince:addressProvinceListUpd){
			if(tmpAddressProvince.getName().trim().equals(name)&&tmpAddressProvince.gettAddressCountryFId().compareTo(parentId)==0){
				existIds.add(tmpAddressProvince.getId());
//			return tmpAddressProvince.getId();
			}
		}
		if(connect2Db){
			if(addressProvinceList_db==null){
				addressProvinceList_db = addressProvinceBaseService.getAddressProvinceByCondition(null);
			}
			for(AddressProvince tmpAddressProvince:addressProvinceList_db){
				if(tmpAddressProvince.getName().trim().equals(name)&&tmpAddressProvince.gettAddressCountryFId().compareTo(parentId)==0){
					existIds.add(tmpAddressProvince.getId());
//				return tmpAddressProvince.getId();
				}
			}
		}
		if(existIds.size()==0){
			return null;
		}else if(existIds.size()==1){
			return new ArrayList<BigInteger>(existIds).get(0);
		}else{
			System.err.println(name+"对应多个省Id。"+JSON.toJSONString(existIds)+"parentId="+parentId);
			throw new RuntimeException(name+"对应多个省。"+JSON.toJSONString(existIds)+"parentId="+parentId);
		}
	}
	
	/**
	 * 通过市名称查询已存在的Id
	 * @param name
	 * @return
	 */
	public BigInteger getIdByAddressCityName(String name,BigInteger parentId){
		Set<BigInteger> existIds = new HashSet<BigInteger>();
		for(AddressCity tmpAddressCity:addressCityListAdd){
			if(tmpAddressCity.getName().trim().equals(name)&&tmpAddressCity.gettProvinceFId().compareTo(parentId)==0){
				existIds.add(tmpAddressCity.getId());
			}
		}
		for(AddressCity tmpAddressCity:addressCityListUpd){
			if(tmpAddressCity.getName().trim().equals(name)&&tmpAddressCity.gettProvinceFId().compareTo(parentId)==0){
					existIds.add(tmpAddressCity.getId());
			}
		}
		if(connect2Db){
			if(addressCityList_db==null){
				addressCityList_db = addressCityBaseService.getAddressCityByCondition(null);
			}
			for(AddressCity tmpAddressCity:addressCityList_db){
				if(tmpAddressCity.getName().trim().equals(name)&&tmpAddressCity.gettProvinceFId().compareTo(parentId)==0){
						existIds.add(tmpAddressCity.getId());
				}
			}
		}
		if(existIds.size()==0){
			return null;
		}else if(existIds.size()==1){
			return new ArrayList<BigInteger>(existIds).get(0);
		}else{
			System.err.println(name+"对应多个市Id。"+JSON.toJSONString(existIds)+",parentId="+parentId);
			throw new RuntimeException(name+"对应多个市Id。"+JSON.toJSONString(existIds)+",parentId="+parentId);
		}
	}
	
	/**
	 * 通过区名称查询已存在的Id
	 * @param name
	 * @return
	 */
	public BigInteger getIdByAddressBlockName(String name,BigInteger parentId){
		Set<BigInteger> existIds = new HashSet<BigInteger>();
		for(AddressBlock tmpAddressBlock:addressBlockListAdd){
			if(tmpAddressBlock.getName().trim().equals(name)&&tmpAddressBlock.gettCityFId().compareTo(parentId)==0){
				existIds.add(tmpAddressBlock.getId());
			}
		}
		for(AddressBlock tmpAddressBlock:addressBlockListUpd){
			if(tmpAddressBlock.getName().trim().equals(name)&&tmpAddressBlock.gettCityFId().compareTo(parentId)==0){
					existIds.add(tmpAddressBlock.getId());
			}
		}
		if(connect2Db){
			if(addressBlockList_db==null){
				addressBlockList_db = addressBlockBaseService.getAddressBlockByCondition(null);
			}
			for(AddressBlock tmpAddressBlock:addressBlockList_db){
				if(tmpAddressBlock.getName().trim().equals(name)&&tmpAddressBlock.gettCityFId().compareTo(parentId)==0){
						existIds.add(tmpAddressBlock.getId());
				}
			}
		}
		if(existIds.size()==0){
			return null;
		}else if(existIds.size()==1){
			return new ArrayList<BigInteger>(existIds).get(0);
		}else{
			System.err.println(name+"对应多个行政区Id。"+JSON.toJSONString(existIds)+",parentId="+parentId);
			throw new RuntimeException(name+"对应多个行政区Id。"+JSON.toJSONString(existIds)+",parentId="+parentId);
		}
	}
	
	//根据Code查询对应的Id
	/**
	 * 通过省Code查询已存在的Id
	 * @param code
	 * @return
	 */
	public BigInteger getIdByAddressProvinceCode(String code){
		for(AddressProvince tmpAddressProvince:addressProvinceListAdd){
			if(tmpAddressProvince.getCountryCode().trim().equals(code)){
				return tmpAddressProvince.getId();
			}
		}
		for(AddressProvince tmpAddressProvince:addressProvinceListUpd){
			if(tmpAddressProvince.getCountryCode().trim().equals(code)){
				return tmpAddressProvince.getId();
			}
		}
		if(connect2Db){
			if(addressProvinceList_db==null){
				addressProvinceList_db = addressProvinceBaseService.getAddressProvinceByCondition(null);
			}
			for(AddressProvince tmpAddressProvince:addressProvinceList_db){
				if(tmpAddressProvince.getCountryCode().trim().equals(code)){
					return tmpAddressProvince.getId();
				}
			}
		}
		return null;
	}
	/**
	 * 通过市Code查询已存在的Id
	 * @param code
	 * @return
	 */
	public BigInteger getIdByAddressCityCode(String code){
		for(AddressCity tmpAddressCity:addressCityListAdd){
			if(tmpAddressCity.getCountryCode().trim().equals(code)){
				return tmpAddressCity.getId();
			}
		}
		for(AddressCity tmpAddressCity:addressCityListUpd){
			if(tmpAddressCity.getCountryCode().trim().equals(code)){
				return tmpAddressCity.getId();
			}
		}
		if(connect2Db){
			if(addressCityList_db==null){
				addressCityList_db = addressCityBaseService.getAddressCityByCondition(null);
			}
			for(AddressCity tmpAddressCity:addressCityList_db){
				if(tmpAddressCity.getCountryCode().trim().equals(code)){
					return tmpAddressCity.getId();
				}
			}
		}
		return null;
	}
	/**
	 * 通过区Code查询已存在的Id
	 * @param code
	 * @return
	 */
	public BigInteger getIdByAddressBlockCode(String code){
		for(AddressBlock tmpAddressBlock:addressBlockListAdd){
			if(tmpAddressBlock.getCountryCode().trim().equals(code)){
				return tmpAddressBlock.getId();
			}
		}
		for(AddressBlock tmpAddressBlock:addressBlockListUpd){
			if(tmpAddressBlock.getCountryCode().trim().equals(code)){
				return tmpAddressBlock.getId();
			}
		}
		if(connect2Db){
			if(addressBlockList_db==null){
				addressBlockList_db = addressBlockBaseService.getAddressBlockByCondition(null);
			}
			for(AddressBlock tmpAddressBlock:addressBlockList_db){
				if(tmpAddressBlock.getCountryCode().trim().equals(code)){
					return tmpAddressBlock.getId();
				}
			}
		}
		return null;
	}
	
	
}
