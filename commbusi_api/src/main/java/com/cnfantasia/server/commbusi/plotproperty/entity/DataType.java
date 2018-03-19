package com.cnfantasia.server.commbusi.plotproperty.entity;

/**
 * 日期类型,折扣或者账单月份
* Filename:    DataType.java
* @version:    1.0.0
* Create at:   2015年12月11日 上午10:11:16
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年12月11日       shiyl             1.0             1.0 Version
 */
public enum  DataType{
	/*base,*/discount,property;

	/**
     * 账单月份,可能为单月可能为多月
    * Filename:    ISectionDate.java
    * @version:    1.0.0
    * Create at:   2015年12月11日 下午3:30:06
    * Description:
    *
    * Modification History:
    * Date           Author           Version           Description
    * ------------------------------------------------------------------
    * 2015年12月11日       shiyl             1.0             1.0 Version
     */
    public interface ISectionDate {

        /**
         * 获取区间描述
         * 例：201507或2015.05-2015.08
         * @return
         */
        String getPeriodDesc();

    }
}
