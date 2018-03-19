/**   
 * Filename:    InitKithenCookieData.java   
 * @version:    1.0  
 * Create at:   2014年8月1日 上午8:53:31   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年8月1日    shiyl      1.0         1.0 Version   
 */
package test.kitcheData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import com.cnfantasia.server.common.utils.PinyinUtil;

/**
 * Filename: InitKithenCookieData.java
 * 
 * @version: 1.0.0 Create at: 2014年8月1日 上午8:53:31 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年8月1日 shiyl 1.0 1.0 Version
 */
public class InitKithenCookieData{
//	public static final String srcDirPath = "C:/Users/shiyl/Desktop/解暑";
//	public static final String srcDirPath = "C:/Users/shiyl/Desktop/厨房内容编辑_01/简食";
//	public static final String srcDirPath = "C:/Users/shiyl/Desktop/lmm_菜谱数据_2014-9-26 093140/【菜谱数据】厨房 V1.4.0 140926/简食";
//	public static final String srcDirPath = "F:/programs/Fantasia/received/lmm_菜谱数据_2014-9-26 093140/toDeal";
	public static final String srcDirPath = "F:/programs/Fantasia/received/lmm_菜谱数据 【成都】141103/toDeal";
	public static final String outputPicDirPath = "C:/Users/shiyl/Desktop/pics";

	public static  BigInteger t_kitchen_cookbook_startId = new BigInteger("1070");//200;1000-1058;1070-1082
	public static  BigInteger t_kitchen_cookbook_step_startId = new BigInteger("1280");//200;1000-1269;1280-1408
//	public static  BigInteger t_kitchen_cookbook_type_startId = new BigInteger("1000");//200;
	public static  BigInteger t_kitchen_detail_startId = new BigInteger("1440");//200;1000-1423;1440-1547
	public static  BigInteger t_kitchen_cookbook_type_has_t_kitchen_cookbook_startId = new BigInteger("1200");//200;1000-1077;1090-1102,1200-1212
	
	public static BigInteger getCookbook_Id(){
		t_kitchen_cookbook_startId=t_kitchen_cookbook_startId.add(new BigInteger("1"));
		return t_kitchen_cookbook_startId;
	}
	public static BigInteger getStep_Id(){
		t_kitchen_cookbook_step_startId=t_kitchen_cookbook_step_startId.add(new BigInteger("1"));
		return t_kitchen_cookbook_step_startId;
	}
//	public static BigInteger getTtype_Id(){
//		t_kitchen_cookbook_type_startId=t_kitchen_cookbook_type_startId.add(new BigInteger("1"));
//		return t_kitchen_cookbook_type_startId;
//	}
	public static BigInteger getDetail_Id(){
		t_kitchen_detail_startId=t_kitchen_detail_startId.add(new BigInteger("1"));
		return t_kitchen_detail_startId;
	}
	
	public static BigInteger getKitchen_cookbook_type_has_t_kitchen_cookbook_Id(){
		t_kitchen_cookbook_type_has_t_kitchen_cookbook_startId=t_kitchen_cookbook_type_has_t_kitchen_cookbook_startId.add(new BigInteger("1"));
		return t_kitchen_cookbook_type_has_t_kitchen_cookbook_startId;
	}
	
	public static void main(String[] args) throws IOException, BadHanyuPinyinOutputFormatCombination {
		
	}
	
	public static BigInteger typeName2Id(String typeName){
		typeName = typeName.trim();
		if("解暑".equals(typeName)){return new BigInteger("1");}
		if("美颜".equals(typeName)){return new BigInteger("2");}
		if("简食".equals(typeName)){return new BigInteger("3");}
		if("瘦身".equals(typeName)){return new BigInteger("4");}
		if("育儿".equals(typeName)){return new BigInteger("5");}
		if("孕味".equals(typeName)){return new BigInteger("6");}
		if("健脑".equals(typeName)){return new BigInteger("11");}
		if("月子".equals(typeName)){return new BigInteger("12");}
		if("润肺".equals(typeName)){return new BigInteger("13");}
//		if("香辣".equals(typeName)){return new BigInteger("14");}
		if("香辣".equals(typeName)){return new BigInteger("15");}
		throw new RuntimeException(typeName);
	}
	
	public static String getCookPicName(String signalFileDirName,BigInteger cookBookId) throws BadHanyuPinyinOutputFormatCombination{
//		int pointIndex = signalFileDirName.lastIndexOf('.');
//		String picFileName = PinyinUtil.hanyuToPinyinSimple(signalFileDirName.substring(0, pointIndex))+ signalFileDirName.substring(pointIndex);
		String picFileName = cookBookId+"_"+PinyinUtil.hanyuToPinyinSimple(signalFileDirName);
		return picFileName;
	}
	public static void copyFile(File sFile, File tFile) throws FileNotFoundException {
		FileInputStream fis = new FileInputStream(sFile);
		FileOutputStream fos = new FileOutputStream(tFile);
		int temp = 0;
		try {
			while ((temp = fis.read()) != -1) {
				fos.write(temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void copyFile(String inputFile, String outputFile) throws FileNotFoundException {
		File sFile = new File(inputFile);
		File tFile = new File(outputFile);
		copyFile(sFile, tFile);
	}

}
