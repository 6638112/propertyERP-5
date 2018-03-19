/**   
* Filename:    Test01.java   
* @version:    1.0  
* Create at:   2014年7月30日 上午7:38:10   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月30日    shiyl      1.0         1.0 Version   
*/
package test.discount;

/**
 * Filename:    Test01.java
 * @version:    1.0.0
 * Create at:   2014年7月30日 上午7:38:10
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月30日       shiyl             1.0             1.0 Version
 */
public class Test01 {
	public static void main(String[] args) {
		System.out.println("start");
		System.out.println(Thread.currentThread().getName());
		System.out.println(Thread.currentThread().getStackTrace());
		System.out.println("end");
	}
}
