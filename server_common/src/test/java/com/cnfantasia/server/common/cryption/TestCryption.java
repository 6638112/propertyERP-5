/**   
* Filename:    TestCryption.java   
* @version:    1.0  
* Create at:   2014年6月10日 上午11:16:23   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月10日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.common.cryption;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import com.cnfantasia.server.common.cryption.dissymmetry.RSACrypt;

/**
 * Filename:    TestCryption.java
 * @version:    1.0.0
 * Create at:   2014年6月10日 上午11:16:23
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月10日       shiyl             1.0             1.0 Version
 */
public class TestCryption {
	
	public static void main(String[] args) {
		String src="大家好，我是小明！";
		String password="abc123";
		KeyPair keyPair=RSACrypt.getKeyPair(password.getBytes(), 1024);
		PrivateKey privateKey=keyPair.getPrivate();
		PublicKey publicKey=keyPair.getPublic();
		byte[] enRes = RSACrypt.encrypt(src.getBytes(), privateKey);
		byte[] deRes = RSACrypt.decrypt(enRes, publicKey);
		System.out.println(new String(deRes));
		
		KeyPair keyPair2=RSACrypt.getKeyPair(password.getBytes(), 1024);
		PublicKey publicKey2=keyPair2.getPublic();
		byte[] deRes2 = RSACrypt.decrypt(enRes, publicKey2);
		System.out.println(new String(deRes2));
	}
	
}
