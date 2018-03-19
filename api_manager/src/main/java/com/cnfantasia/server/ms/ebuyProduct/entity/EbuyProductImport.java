package com.cnfantasia.server.ms.ebuyProduct.entity;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;

/**
 * 商品导入对应的POJO
 * 
 * @author wenfq 2017年8月14日
 */
public class EbuyProductImport {
	//-- Excel中字段信息
	String prdtName;//商品名称
	String prdtCode;//货品条码
	String shelfState;//上架 or 下架
	String shelfName;//货架分类：今日促销,新用户专享,优质生鲜,潮流精品,供港蔬菜,时令鲜果,禽类蛋品,休闲驿站,海产干货,生活服务,酒水饮料,母婴专场,烘焙工坊,粮油调味,生活日用,日用百货,礼遇金秋
	String unitName;//单位
	double marketPrice;//市场价
	double sellPrice;//线上售价
	double sellPriceOffLine;//零售价
	double buyPrice;//进货价
	int leftCount;//当前库存
	int importStatu;//导入状态

	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public String getPrdtName() {
		return prdtName;
	}

	public void setPrdtName(String prdtName) {
		this.prdtName = prdtName;
	}

	public String getPrdtCode() {
		return prdtCode;
	}

	public void setPrdtCode(String prdtCode) {
		this.prdtCode = prdtCode;
	}

	public String getShelfState() {
		return shelfState;
	}

	public void setShelfState(String shelfState) {
		this.shelfState = shelfState;
	}

	public String getShelfName() {
		return shelfName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public int getLeftCount() {
		return leftCount;
	}

	public void setLeftCount(int leftCount) {
		this.leftCount = leftCount;
	}

	public int getImportStatu() {
		return importStatu;
	}

	public void setImportStatu(int importStatu) {
		this.importStatu = importStatu;
	}
	
	public double getSellPriceOffLine() {
		return sellPriceOffLine;
	}

	public void setSellPriceOffLine(double sellPriceOffLine) {
		this.sellPriceOffLine = sellPriceOffLine;
	}

	//其它数据库中匹配的信息
	EbuyProduct ebuyProduct;//对应商品

	int firstImport = 1; //是否首次导入

	BigInteger ebuyProductTypeId;

	int upShelfState = 0; // 0上架，1下架

	public int getUpShelfState() {
		return upShelfState;
	}

	public void setUpShelfState(int upShelfState) {
		this.upShelfState = upShelfState;
	}

	public EbuyProduct getEbuyProduct() {
		return ebuyProduct;
	}

	public void setEbuyProduct(EbuyProduct ebuyProduct) {
		this.ebuyProduct = ebuyProduct;
	}

	public BigInteger getEbuyProductTypeId() {
		return ebuyProductTypeId;
	}

	public void setEbuyProductTypeId(BigInteger ebuyProductTypeId) {
		this.ebuyProductTypeId = ebuyProductTypeId;
	}

	public int getFirstImport() {
		return firstImport;
	}

	public void setFirstImport(int firstImport) {
		this.firstImport = firstImport;
	}
}
