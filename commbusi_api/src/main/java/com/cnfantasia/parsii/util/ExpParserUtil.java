package com.cnfantasia.parsii.util;

import org.apache.commons.lang.StringUtils;
import parsii.eval.Expression;
import parsii.eval.Parser;
import parsii.tokenizer.ParseException;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * 计算表达式计算类
 * Created by yangh on 2016/10/20.
 */
public class ExpParserUtil {

    /**
     * 计算返回double的表达式
     * @param exp 计算表达式
     * @return
     */
    public static long calculate(String exp) {
        long result = 0;
        try {
            Expression expr = Parser.parse(exp);
            double res = expr.evaluate() * 100;//以分计算
            res = getRounding(res,2);//14.45986565656656655 >> 14.5
            res = getRounding(res,0);//>>15
            if(Double.isInfinite(res)) {
                res = 0;
            }
            result = (long) res;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 校验表达式正确性
     * @param exp 计算表达式
     * @return true 表达式正确
     */
    public static boolean verification(String exp) {
        StringBuilder result = new StringBuilder();
        for(int i=0; i<exp.length(); i++){
            String alpha = String.valueOf(exp.charAt(i));
            if(StringUtils.isAlpha(alpha)){
                result.append("1");
            } else {
                result.append(alpha);
            }
        }

        try {
            Expression expr = Parser.parse(result.toString());
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 对double进行四舍五入
     * @param num  数字
     * @param scale 保留位数
     * @return
     */
    public static double getRounding(double num, int scale) {
        if (Double.isNaN(num)) {
            return 0;
        }
        BigDecimal bd = new BigDecimal(num);
        num = bd.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        return num;
    }

}
