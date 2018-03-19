/**   
* Filename:    CryptPiple.java   
* @version:    1.0  
* Create at:   2014年6月10日 上午11:48:44   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月10日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.common.cryption.simplepiple;

import java.util.LinkedList;

/**
 * Filename:    CryptPiple.java
 * @version:    1.0.0
 * Create at:   2014年6月10日 上午11:48:44
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月10日       shiyl             1.0             1.0 Version
 */
public class CryptPiple implements ICryptPiple{
	private LinkedList<IDecryptAble> decryptAbleList = new LinkedList<IDecryptAble>();
	
	public CryptPiple(){}
	public CryptPiple(LinkedList<IDecryptAble> decryptAbleList){
		this.decryptAbleList = decryptAbleList;
	}
	
	public LinkedList<IDecryptAble> getDecryptAbleList() {
		return decryptAbleList;
	}
	public void setDecryptAbleList(LinkedList<IDecryptAble> decryptAbleList) {
		this.decryptAbleList = decryptAbleList;
	}

	@Override
	public byte[] encrypt(byte[] data) {
		byte[] res = data;
		for(int i=0;i<decryptAbleList.size();i++){
			IDecryptAble decryptAble = decryptAbleList.get(i);
			res=decryptAble.encrypt(res);
		}
		return res;
	}

	@Override
	public byte[] decrypt(byte[] data) {
		byte[] res = data;
		for(int i=decryptAbleList.size()-1;i>=0;i--){
			IDecryptAble decryptAble = decryptAbleList.get(i);
			res=decryptAble.decrypt(res);
		}
		return res;
	}

	@Override
	public void addLast(IDecryptAble decryptAble) {
		decryptAbleList.addLast(decryptAble);
	}
	@Override
	public void addFirst(IDecryptAble decryptAble) {
		decryptAbleList.addFirst(decryptAble);
	}
	@Override
	public void removeFirst() {
		decryptAbleList.removeFirst();
	}
	@Override
	public void removeLast() {
		decryptAbleList.removeLast();
	}


}
