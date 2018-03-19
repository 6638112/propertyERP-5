/**   
* Filename:    PrizeDBDiscountTypeHandler.java   
* @version:    1.0  
* Create at:   2015年3月30日 上午10:10:45   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月30日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.typeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

/**
 * Filename:    PrizeDBDiscountTypeHandler.java
 * @version:    1.0.0
 * Create at:   2015年3月30日 上午10:10:45
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月30日       shiyl             1.0             1.0 Version
 */
public class PrizeDBDiscountTypeHandler implements TypeHandler<Double>{
	@Override
	public void setParameter(PreparedStatement ps, int i, Double parameter, JdbcType jdbcType) throws SQLException {
		//System.out.println("setParameter(PreparedStatement ps, int i, Double parameter, JdbcType jdbcType) - parameter: " + (parameter) + ", jdbcType: " + jdbcType.TYPE_CODE);  
    ps.setDouble(i, parameter);
	}

	@Override
	public Double getResult(ResultSet rs, String columnName) throws SQLException {
		//System.out.println("getResult(ResultSet rs, String columnName) - columnName: " + columnName);  
		Double resDouble =  rs.getDouble(columnName);
		return parseResult(resDouble);
	}

	@Override
	public Double getResult(ResultSet rs, int columnIndex) throws SQLException {
		//System.out.println("getResult(ResultSet rs, int columnIndex) - columnIndex: " + columnIndex);  
		Double resDouble =  rs.getDouble(columnIndex);
		return parseResult(resDouble);
	}

	@Override
	public Double getResult(CallableStatement cs, int columnIndex) throws SQLException {
		//System.out.println("getResult(CallableStatement cs, int columnIndex)  - columnIndex: " + columnIndex);  
		Double resDouble =  cs.getDouble(columnIndex);
		return parseResult(resDouble);
	}
	
	private Double parseResult(Double res){
		return Double.valueOf(new DecimalFormat("0.0").format(res));
	}
	
}
