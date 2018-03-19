package MoneyUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * 红包均分
 * 
 * @author liyulin
 * @version 1.0 2017年3月31日 上午11:28:35
 */
public final class MoneyUtil {

	/**
	 * 红包金额根据所占总金额比例均分
	 * 
	 * @param totalAmt 总金额（单位：分），为null时，会在内部计算其值
	 * @param subAmts 所有资金额（单位：分）
	 * @param totalHbAmt 红包总金额（单位：分）
	 * @return 返回所有均分的金额（单位：分）
	 */
	public static final List<BigDecimal> getSubMoney(BigDecimal totalAmt,
			List<BigDecimal> subAmts, BigDecimal totalHbAmt) {
		if (totalAmt == null) {
			totalAmt = BigDecimal.ZERO;
			for (BigDecimal subAmt : subAmts) {
				totalAmt = totalAmt.add(subAmt);
			}
		}

		/** 因为计算时，最后一个的值为总红包金额减去其余红包总额，如果最后的账单金额为0，会出现红包金额为负数的情况 */
		int dealSize = getDealSubAmtMaxIndex(subAmts);// 从最后一个开始倒序，实付为大于0的索引

		/** 最后实付金额（连续）为非0的处理 */
		List<BigDecimal> subHbAmts = dealNonNegative(dealSize, totalAmt, subAmts, totalHbAmt);
		
		/** 最后实付金额（连续）为0的处理 */
		for (int i = dealSize + 1; i < subAmts.size(); i++) {
			subHbAmts.add(BigDecimal.ZERO);
		}

		/** 分配的最后一个红包为负数的处理 */
		dealLastNegative(dealSize, subHbAmts, subAmts);

		return subHbAmts;
	}
	
	/**
	 * 非0实付处理
	 * @param dealSize
	 * @param totalAmt
	 * @param subAmts
	 * @param totalHbAmt
	 * @return
	 */
	public static final List<BigDecimal> dealNonNegative(int dealSize,
			BigDecimal totalAmt, List<BigDecimal> subAmts, BigDecimal totalHbAmt) {
		List<BigDecimal> subHbAmts = new ArrayList<BigDecimal>();
		BigDecimal otherTotalAmt = BigDecimal.ZERO;
		for (int i = 0; i <= dealSize; i++) {
			BigDecimal subHbAmt = null;
			if (i < dealSize) {
				BigDecimal subAmt = subAmts.get(i);

				subHbAmt = div(subAmt.multiply(totalHbAmt), totalAmt);
				BigDecimal subUpHbAmt = subHbAmt.setScale(0, RoundingMode.UP);
				if (subUpHbAmt.compareTo(subAmt) < 0) {
					subHbAmt = subUpHbAmt;
				} else {
					subHbAmt = subAmt;
				}

				otherTotalAmt = otherTotalAmt.add(subHbAmt);
			} else {// 最后一个
				subHbAmt = totalHbAmt.subtract(otherTotalAmt);
			}

			subHbAmts.add(subHbAmt);
		}

		return subHbAmts;
	}
	
	/**
	 * 获取处理的最后一个子帐单的索引
	 * @param subAmts
	 * @return
	 */
	public static final int getDealSubAmtMaxIndex(List<BigDecimal> subAmts){
		int dealSize = 0;// 从最后一个开始倒叙，实付为大于0的索引
		/** 因为计算时，最后一个的值为总红包金额减去其余红包总额，如果最后的账单金额为0，会出现红包金额为负数的情况 */
		for (int i = subAmts.size() - 1; i >= 0; i--) {
			BigDecimal subAmtTmp = subAmts.get(i);
			if (subAmtTmp.compareTo(BigDecimal.ZERO) != 0) {
				dealSize = i;
				break;
			}
		}
		return dealSize;
	}
	
	/**
	 * 分配的最后一个红包为负数的处理
	 * @param dealSize
	 * @param subHbAmts
	 * @param subAmts
	 */
	public static final void dealLastNegative(int dealSize, List<BigDecimal> subHbAmts, List<BigDecimal> subAmts){
		if(subHbAmts.get(dealSize).compareTo(BigDecimal.ZERO) < 0){
			long balanceAmt = Math.abs(subHbAmts.get(dealSize).longValue());// 相差的金额
			subHbAmts.set(dealSize, BigDecimal.ZERO);
			for (int i = 0; i < dealSize; i++) {
				long partBalanceAmt = subAmts.get(i).longValue() - subHbAmts.get(i).longValue();
				if (partBalanceAmt > 0) {
					if (partBalanceAmt >= balanceAmt) {
						subHbAmts.set(i, subHbAmts.get(i).subtract(BigDecimal.valueOf(balanceAmt)));
						break;
					} else {
						subHbAmts.set(i, subHbAmts.get(i).subtract(BigDecimal.valueOf(partBalanceAmt)));
						balanceAmt -= partBalanceAmt;
						if (balanceAmt == 0) {
							break;
						}
					}
				}
			}
		}
	}
	
	public static final BigDecimal div(BigDecimal num1, BigDecimal num2) {
		if (num1 == null || num2 == null) {
			return null;
		}
		return num1.divide(num2, 4, RoundingMode.DOWN);
	}

	public static void main(String[] args) {
		List<BigDecimal> subAmts = new ArrayList<BigDecimal>();

		subAmts.add(BigDecimal.valueOf(48));
		subAmts.add(BigDecimal.valueOf(122));
		subAmts.add(BigDecimal.valueOf(196));
		subAmts.add(BigDecimal.valueOf(10));

		BigDecimal totalHbAmt = BigDecimal.valueOf(8);

		List<BigDecimal> allSubHbAmts = getSubMoney(null, subAmts, totalHbAmt);
		System.err.println(allSubHbAmts);
	}
}
