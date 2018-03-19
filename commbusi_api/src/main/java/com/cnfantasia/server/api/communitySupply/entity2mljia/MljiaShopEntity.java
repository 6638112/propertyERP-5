/**   
* Filename:    MljiaShopEntity.java   
* @version:    1.0  
* Create at:   2015年1月6日 上午8:08:41   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月6日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.entity2mljia;

import java.io.Serializable;
import java.math.BigInteger;

import com.cnfantasia.server.api.communitySupply.constant.CommunitySupplyConstant;

/**
 * Filename:    MljiaShopEntity.java
 * @version:    1.0.0
 * Create at:   2015年1月6日 上午8:08:41
 * Description:获取周边店铺列表,美容类别
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月6日       shiyl             1.0             1.0 Version
 */
public class MljiaShopEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private static final BigInteger supplyTypeId = CommunitySupplyConstant.SupplyTypeId.Meirong_SuppplyTypeId;
	public BigInteger getSupplyTypeId() {
		return supplyTypeId;
	}
	
//"shop_tel":"18666212476",
//"shop_img_url2":"http://pic.mljia.cn/cn.mljia.web/download/image/68819",
//"shop_id":100825,
//"shop_shop_bustiness":"美容·SPA",
//"shop_intr":"      圣荷是泰国产品，18年品质见证。圣荷运用泰式按摩的特别手法，让每个爱美女性足不出户即可感受纯正的泰式丰胸。 　 泰国圣荷丰胸产品在深圳市场上已被众多女性所关注、推崇，依靠良好的口碑，进驻深圳发展8家专营店。深圳市场另外15家店的计划也在积极筹备中。",
//"shop_certification_star":0,
//"shop_business_start_time":"09:30",
//"shop_area":500,
//"shop_name":"泰国圣荷丰胸美体会所福田中心店 ",
//"shop_addr":"福田区 新洲八街蜜圆21号(福田保健院对面) ",
//"shop_credit":1,
//"distance":474.0,
//"shop_business_end_time":"21:00",
//"shop_img_url":"http://pic.mljia.cn/cn.mljia.web/download/image/thumb/68819"},
	
	
	/**店铺编号*/
	private String shop_id; // ok
	/**店铺名称*/
	private String shop_name;//f_name ok
	/**店铺电话*/
	private String shop_tel;//联系方式表
	/**店铺主营业务*/
	private String shop_shop_bustiness;// ok
	/**店铺介绍*/
	private String shop_intr;//f_desc ok
	/**店铺认证星级*/
	private String shop_certification_star;// ok
	/**店铺面积*/
	private String shop_area;// ok
	/**店铺地址*/
	private String shop_addr;//f_address_detail ok
	/**店铺信誉等级*/
	private String shop_credit;// ok
	/**距离单位米*/
	private String distance;//f_distance ok
	/**店铺营业开始时间*/
	private String shop_business_start_time;//ok f_open_hours_desc
	/**店铺营业结束时间*/
	private String shop_business_end_time;//ok f_open_hours_desc
	/**店铺标志URL（缩略图）*/
	private String shop_img_url;//ok --f_pic_url 
	/**店铺标志URL（原图）*/
	private String shop_img_url2;//TODO --f_pic_url
	
	public String getShop_id() {
		return shop_id;
	}
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getShop_tel() {
		return shop_tel;
	}
	public void setShop_tel(String shop_tel) {
		this.shop_tel = shop_tel;
	}
	public String getShop_shop_bustiness() {
		return shop_shop_bustiness;
	}
	public void setShop_shop_bustiness(String shop_shop_bustiness) {
		this.shop_shop_bustiness = shop_shop_bustiness;
	}
	public String getShop_intr() {
		return shop_intr;
	}
	public void setShop_intr(String shop_intr) {
		this.shop_intr = shop_intr;
	}
	public String getShop_certification_star() {
		return shop_certification_star;
	}
	public void setShop_certification_star(String shop_certification_star) {
		this.shop_certification_star = shop_certification_star;
	}
	public String getShop_area() {
		return shop_area;
	}
	public void setShop_area(String shop_area) {
		this.shop_area = shop_area;
	}
	public String getShop_addr() {
		return shop_addr;
	}
	public void setShop_addr(String shop_addr) {
		this.shop_addr = shop_addr;
	}
	public String getShop_credit() {
		return shop_credit;
	}
	public void setShop_credit(String shop_credit) {
		this.shop_credit = shop_credit;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getShop_business_start_time() {
		return shop_business_start_time;
	}
	public void setShop_business_start_time(String shop_business_start_time) {
		this.shop_business_start_time = shop_business_start_time;
	}
	public String getShop_business_end_time() {
		return shop_business_end_time;
	}
	public void setShop_business_end_time(String shop_business_end_time) {
		this.shop_business_end_time = shop_business_end_time;
	}
	public String getShop_img_url() {
		return shop_img_url;
	}
	public void setShop_img_url(String shop_img_url) {
		this.shop_img_url = shop_img_url;
	}
	public String getShop_img_url2() {
		return shop_img_url2;
	}
	public void setShop_img_url2(String shop_img_url2) {
		this.shop_img_url2 = shop_img_url2;
	}


}