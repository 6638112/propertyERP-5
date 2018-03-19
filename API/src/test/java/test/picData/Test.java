/**   
* Filename:    Test.java   
* @version:    1.0  
* Create at:   2014年9月4日 上午6:20:40   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月4日    shiyl      1.0         1.0 Version   
*/
package test.picData;

/**
 * Filename:    Test.java
 * @version:    1.0.0
 * Create at:   2014年9月4日 上午6:20:40
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月4日       shiyl             1.0             1.0 Version
 */
public class Test {
	public static void main(String[] args) {
		{
			Integer a = 74;
			Integer b = 74;
			System.out.println(a==b);
			System.out.println("compare:"+(a.compareTo(b)==0));
		}
		{
			Integer a = 152;
			Integer b = 152;
			System.out.println(a==b);
			System.out.println("compare:"+(a.compareTo(b)==0));
		}
		
		//所有图片
		int sum = 107+574;
		int count = 79+16+10+119+2+10+3+3+3+2+108+4+4;
		System.out.println(sum);
		System.out.println(count);
		System.out.println((sum+0.0)/count);
		//大图片
		double aa =  68.2+275;
		double bb = 119;
		System.out.println(aa/bb);
		//小图片
		double aa2 =  47/1024.0+5.48;
		double bb2 = 10;
		System.out.println(aa2/bb2);
		
	}
}
