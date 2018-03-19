/**   
* Filename:    PrizeRecordComparator.java   
* @version:    1.0  
* Create at:   2014年5月12日 上午10:02:19   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月12日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.entity;

import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.domainbase.prizeRecord.entity.PrizeRecord;

/**
 * Filename:    PrizeRecordComparator.java
 * @version:    1.0.0
 * Create at:   2014年5月12日 上午10:02:19
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月12日       shiyl             1.0             1.0 Version
 */
public class PrizeRecordComparator implements Comparator<PrizeRecord>{
	Log logger = LogFactory.getLog(getClass());
	@Override
	public int compare(PrizeRecord o1, PrizeRecord o2) {
		Date thisPrizeTime = null;
		Date goalPrizeTime = null;
		try {
			thisPrizeTime = DateUtil.formatSecond.get().parse(o1.getPrizeTime());
			goalPrizeTime = DateUtil.formatSecond.get().parse(o2.getPrizeTime());
		} catch (ParseException e1) {
			logger.debug(e1);
			return 0;
		}
		return thisPrizeTime.compareTo(goalPrizeTime);
	}

	
}
