/**   
* Filename:    Calculate.java   
* @version:    1.0  
* Create at:   2014年8月8日 上午1:02:18   
* Description:  \$\('\.(.*)-(.*)-(.*)'\)\.text\((.*)\);
*   $1_$2_$3 = Double.valueOf($4);
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月8日    shiyl      1.0         1.0 Version   
*/
package test.aixin;

/**
 * Filename:    Calculate.java
 * @version:    1.0.0
 * Create at:   2014年8月8日 上午1:02:18
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月8日       shiyl             1.0             1.0 Version
 */
public class Calculate {
	public static void main(String[] args) {
		Calculate calculate = new Calculate();
		System.out.println(calculate.doCalculate(4000, 0));
//		for(int k=500;k<1000000;k+=1000){
//			double saVal=k;
//			for(int i=0;i<saVal;i+=10){
//				double doVal=i;
//				Result result = calculate.doCalculate(saVal, doVal);
////				System.out.println(result);
//				double res = result.getNum_af_06()-doVal-result.getNum_bf_06();
//				System.out.println(res);
////				if(res>0){
////					System.out.println(res);
////				}
//			}
//		}
		
	}
	
	
	public Result doCalculate(double saVal,double doVal){
		double num_af_01=0,num_af_02=0,num_af_03=0,num_af_04=0,num_af_05=0,num_af_06=0;
		double num_bf_01=0,num_bf_02=0,num_bf_03=0,num_bf_04=0,num_bf_05=0,num_bf_06=0,num_bf_07;
	//扣除住房公积金
		if( saVal > 26091 ){
			num_bf_01 = Double.valueOf(1304.55);
			num_af_01 = Double.valueOf(1304.55);
		} else if( saVal > 0 && saVal < 26091 || saVal == 26091 ) {
			double saVal01=saVal*0.05;
			num_bf_01 = Double.valueOf(saVal01);
			num_af_01 = Double.valueOf(saVal01);	
		}
		
		//扣保险费
		if( saVal > 15654 ){
			num_bf_02 = Double.valueOf(1582.58);
			num_af_02 = Double.valueOf(1582.58);
		} else if( saVal > 0 && saVal < 15654 || saVal == 15654 ) {
			double saVal01=saVal*0.1 + 18.08;
			num_bf_02 = Double.valueOf(saVal01);
			num_af_02 = Double.valueOf(saVal01);	
		}
		
		//税前金额
		double numb01=num_bf_01;
		double numa01=num_af_01;
		double numb02=num_bf_02;
		double numa02=num_af_02;
		num_bf_03 = Double.valueOf(saVal - numb01 - numb02);
		num_af_03 = Double.valueOf(saVal - doVal - numa01 - numa02);
		
		//税金基数
		double numb03=num_bf_03;
		double numa03=num_af_03;
		if( (numb03-3500) > 0 ){
			num_bf_04 = Double.valueOf (numb03-3500 );
			num_af_04 = Double.valueOf (numa03-3500 );
		}
		
		//应扣缴所得税
		double numb04=num_bf_04;
		double numa04=num_af_04;
		if( numb04 > 0 && numb04 < 1500 || numb04 == 1500 ){
			num_bf_05 = Double.valueOf (numb04*0.03 );
		} else if( numb04 > 1500 && numb04 < 4500 || numb04 == 4500 ){
			num_bf_05 = Double.valueOf (numb04*0.1-105 );
		} else if( numb04 > 4500 && numb04 < 9000 || numb04 == 9000 ){
			num_bf_05 = Double.valueOf (numb04*0.2-555 );
		} else if( numb04 > 9000 && numb04 < 35000 || numb04 == 35000 ){
			num_bf_05 = Double.valueOf (numb04*0.25-1005 );
		} else if( numb04 > 35000 && numb04 < 55000 || numb04 == 55000 ){
			num_bf_05 = Double.valueOf (numb04*0.3-2755 );
		} else if( numb04 > 55000 && numb04 < 80000 || numb04 == 80000 ){
			num_bf_05 = Double.valueOf (numb04*0.35-5505 );
		} else if( numb04 > 80000 ){
			num_bf_05 = Double.valueOf (numb04*0.45-13505 );
		}
		
		if( numa04 > 0 && numa04 < 1500 || numa04 == 1500 ){
			num_af_05 = Double.valueOf (numa04*0.03 );
		} else if( numa04 > 1500 && numa04 < 4500 || numa04 == 4500 ){
			num_af_05 = Double.valueOf (numa04*0.1-105 );
		} else if( numa04 > 4500 && numa04 < 9000 || numa04 == 9000 ){
			num_af_05 = Double.valueOf (numa04*0.2-555 );
		} else if( numa04 > 9000 && numa04 < 35000 || numa04 == 35000 ){
			num_af_05 = Double.valueOf (numa04*0.25-1005 );
		} else if( numa04 > 35000 && numa04 < 55000 || numa04 == 55000 ){
			num_af_05 = Double.valueOf (numa04*0.3-2755 );
		} else if( numa04 > 55000 && numa04 < 80000 || numa04 == 80000 ){
			num_af_05 = Double.valueOf (numa04*0.35-5505 );
		} else if( numa04 > 80000 ){
			num_af_05 = Double.valueOf (numa04*0.45-13505 );
		}
		
		//实发合计
		double numb05=num_bf_05;
		double numa05=num_af_05;
		num_bf_06 = Double.valueOf (saVal - numb01 - numb02 - numb05 );
		num_af_06 = Double.valueOf (saVal - numa01 - numa02 - numa05 );
		
		//个税节约额
		double numb06=num_bf_06;
		double numa06=num_af_06;
		num_bf_07 = Double.valueOf (numb06 - numa06 );
		Result result = new Result(num_af_06, num_bf_06, num_bf_07);
		return result;
	}
	class Result{
		private double num_af_06;
		private double num_bf_06;
		private double num_bf_07;
		public Result(){
		}
		
		@Override
		public String toString() {
			StringBuffer sbf = new StringBuffer();
			sbf.append("前:"+num_bf_06+";");
			sbf.append("后:"+num_af_06+";");
			sbf.append("节约:"+num_bf_07+";");
			return sbf.toString();
		}

		public Result(double num_af_06,double num_bf_06,double num_bf_07){
			this.num_af_06 = num_af_06;
			this.num_bf_06 = num_bf_06;
			this.num_bf_07 = num_bf_07;
		}
		public double getNum_af_06() {
			return num_af_06;
		}
		public void setNum_af_06(double num_af_06) {
			this.num_af_06 = num_af_06;
		}
		public double getNum_bf_06() {
			return num_bf_06;
		}
		public void setNum_bf_06(double num_bf_06) {
			this.num_bf_06 = num_bf_06;
		}
		public double getNum_bf_07() {
			return num_bf_07;
		}
		public void setNum_bf_07(double num_bf_07) {
			this.num_bf_07 = num_bf_07;
		}
		
	}
}
