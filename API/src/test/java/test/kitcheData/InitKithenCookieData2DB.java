/**   
* Filename:    InitKithenCookieData2DB.java   
* @version:    1.0  
* Create at:   2014年8月2日 上午2:25:42   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月2日    shiyl      1.0         1.0 Version   
*/
package test.kitcheData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import com.cnfantasia.server.api.BaseTest;
import com.cnfantasia.server.api.kitchen.constant.KitchenDict;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.kitchenCookbook.dao.IKitchenCookbookBaseDao;
import com.cnfantasia.server.domainbase.kitchenCookbook.entity.KitchenCookbook;
import com.cnfantasia.server.domainbase.kitchenCookbookStep.dao.IKitchenCookbookStepBaseDao;
import com.cnfantasia.server.domainbase.kitchenCookbookStep.entity.KitchenCookbookStep;
import com.cnfantasia.server.domainbase.kitchenCookbookTypeHasTKitchenCookbook.dao.IKitchenCookbookTypeHasTKitchenCookbookBaseDao;
import com.cnfantasia.server.domainbase.kitchenCookbookTypeHasTKitchenCookbook.entity.KitchenCookbookTypeHasTKitchenCookbook;
import com.cnfantasia.server.domainbase.kitchenDetail.dao.IKitchenDetailBaseDao;
import com.cnfantasia.server.domainbase.kitchenDetail.entity.KitchenDetail;

/**
 * Filename:    InitKithenCookieData2DB.java
 * @version:    1.0.0
 * Create at:   2014年8月2日 上午2:25:42
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月2日       shiyl             1.0             1.0 Version
 */
public class InitKithenCookieData2DB  extends BaseTest{
	private static final boolean copyPicData = false;
	private static final boolean inert2DbBath = false;
	/**
	 * 1.先run as java Application 校验数据
	 * @param args
	 * @throws IOException
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public static void main(String[] args) throws IOException, BadHanyuPinyinOutputFormatCombination {
		InitKithenCookieData2DB initKithenCookieData2DB = new InitKithenCookieData2DB();
		initKithenCookieData2DB.doKitchenCook2DB();
	}
	
//	@Test
	public void doKitchenCook2DB() throws IOException, BadHanyuPinyinOutputFormatCombination{
		String createTime = "2014-11-14 11:36:54";//TODO ..
		List<KitchenCookbook> kitchenCookbookList = new ArrayList<KitchenCookbook>();
		List<KitchenCookbookStep> kitchenCookbookStepList = new ArrayList<KitchenCookbookStep>();
//		List<KitchenCookbookType> kitchenCookbookTypeList = new ArrayList<KitchenCookbookType>();
		List<KitchenDetail> kitchenDetailList = new ArrayList<KitchenDetail>();
		List<KitchenCookbookTypeHasTKitchenCookbook> kitchenCookbookTypeHasTKitchenCookbookList = new ArrayList<KitchenCookbookTypeHasTKitchenCookbook>();
		
		// 停止服务
		File fileDir = new File(InitKithenCookieData.srcDirPath);
		File[] fileDirAll = fileDir.listFiles();
		for (File signalFileDir : fileDirAll) {
			if (signalFileDir.isDirectory()) {
				System.out.println("Curr directory is :"+signalFileDir.getName());
				//hengren菜谱信息
				BigInteger cookBookId = InitKithenCookieData.getCookbook_Id();
				
				String[] tmpNameArray = signalFileDir.getName().split("==");
				Set<BigInteger> typeIdSet = new HashSet<BigInteger>();
				for(int i=0;i<tmpNameArray.length-1;i++){
					typeIdSet.add(InitKithenCookieData.typeName2Id(tmpNameArray[i]));
				}
				for(BigInteger tmpTypeId:typeIdSet){//菜品所属类别
					BigInteger kitchenCookbookTypeHasTKitchenCookbookId = InitKithenCookieData.getKitchen_cookbook_type_has_t_kitchen_cookbook_Id();
					KitchenCookbookTypeHasTKitchenCookbook kitchenCookbookTypeHasTKitchenCookbook = 
							new KitchenCookbookTypeHasTKitchenCookbook(kitchenCookbookTypeHasTKitchenCookbookId, tmpTypeId, cookBookId, null, null, null, null, null, null, 0);
					kitchenCookbookTypeHasTKitchenCookbookList.add(kitchenCookbookTypeHasTKitchenCookbook);
				}
				String cookBookName = tmpNameArray[tmpNameArray.length-1];
				String eatWeight = null;//后面获取
				String cookTime = null;//后面获取
				String desc = null;//后面获取
				String cookBookPicUrl = null;//后面获取
				String taste = null;//目前不包含口感
				String cookTech = null;//工艺 后面获取
				String tips = null;//小贴士 后面获取
//				KitchenCookbook kitchenCookbook = new KitchenCookbook(cookBookId,cookBookName, cookBookPicUrl, desc, eatWeight, cookTime, null, taste, typeId, null, null, null, null, null, null, 0);
//				KitchenCookbook kitchenCookbook = new KitchenCookbook(cookBookId,cookBookName, cookBookPicUrl, desc, eatWeight, cookTime, null, taste,null, null, null, null, null, null, 0);
//				KitchenCookbook kitchenCookbook = new KitchenCookbook(cookBookId, cookBookName, cookBookPicUrl, desc, eatWeight, cookTime, null, taste, cookTech, tips, null, null, null, null, null, null, 0);
				KitchenCookbook kitchenCookbook = new KitchenCookbook(cookBookId, cookBookName, cookBookPicUrl, desc, null, eatWeight, cookTime, null, taste, cookTech, tips, createTime, null, null, null, null, null, null, 0,null);
				// 1.获取目录下的文件列表
				File[] infoListFile = signalFileDir.listFiles();
				// 2.获取 文本信息
				List<String> txtFileNames = new ArrayList<String>();
				txtFileNames.add("步骤.txt");				txtFileNames.add("做法.txt");
				txtFileNames.add("参数.txt");				txtFileNames.add("辅料.txt");
				txtFileNames.add("调料.txt");				txtFileNames.add("主料.txt");
				txtFileNames.add("食材.txt");
				txtFileNames.add("功能.txt");				txtFileNames.add("简介.txt");
				txtFileNames.add("小贴士.txt");
				for (File tmpFile : infoListFile) {
					String fileName = tmpFile.getName();
					if (!txtFileNames.contains(fileName)) {
						continue;
					}
					System.out.println(tmpFile.getName() + ":");
					BufferedReader buffRead = new BufferedReader(new InputStreamReader(new FileInputStream(tmpFile), "GBK"));
					String line = buffRead.readLine();
					StringBuffer tipsSbf = new StringBuffer();
					int orderNum = 0;
					while (line != null) {
						System.out.println("当前行数据："+line);
						if(!StringUtils.isEmpty(line)){
							line = line.trim();
							if("小贴士.txt".equals(fileName)){
								tipsSbf.append(line+"\r");
							}else if("步骤.txt".equals(fileName)||"做法.txt".equals(fileName)){
								BigInteger kitchenCookbookStepId = InitKithenCookieData.getStep_Id();
								String kitchenCookbookStepPicUrl = null;//目前步骤都不包含图片
								orderNum++;
								KitchenCookbookStep kitchenCookbookStep = new KitchenCookbookStep(kitchenCookbookStepId, line, kitchenCookbookStepPicUrl, cookBookId, orderNum, null, null, null, null, null, null, 0);
								kitchenCookbookStepList.add(kitchenCookbookStep);
							}else if("功能.txt".equals(fileName)||"简介.txt".equals(fileName)){
								kitchenCookbook.setDesc(line);//设置功能描述
							}else{
								int maoHaoIndex = line.indexOf("：");
								if(maoHaoIndex==-1){
									maoHaoIndex = line.indexOf(":");
								}
								if(maoHaoIndex==-1){
									maoHaoIndex = line.length();
								}
								String lineKey = line.substring(0, maoHaoIndex);
								String lineValue = null;
								try {
									lineValue = line.substring(maoHaoIndex+1);
								} catch (Exception e) {
								}
								if("参数.txt".equals(fileName)){
									if(line.startsWith("份量")){
										kitchenCookbook.setEatWeight(lineValue);
									}else if(line.startsWith("制作时间")||line.startsWith("用时")){
										kitchenCookbook.setCookTime(lineValue);
									}else if(line.startsWith("工艺")){
										kitchenCookbook.setCookTech(lineValue);
									}else if(line.startsWith("口味")){
										kitchenCookbook.setTaste(lineValue);
									}
								} else if("辅料.txt".equals(fileName)||"调料.txt".equals(fileName)){
									BigInteger kitchenDetailId = InitKithenCookieData.getDetail_Id();
									Integer type = KitchenDict.KitchenDetail_Type.ASSIST;
									KitchenDetail kitchenDetail = new KitchenDetail(kitchenDetailId, type, null, lineKey, lineValue,cookBookId,null, null, null, null, null, null, 0);
									kitchenDetailList.add(kitchenDetail);
								}else if("主料.txt".equals(fileName)||"食材.txt".equals(fileName)){
									BigInteger kitchenDetailId = InitKithenCookieData.getDetail_Id();
									Integer type = KitchenDict.KitchenDetail_Type.MAIN;
									KitchenDetail kitchenDetail = new KitchenDetail(kitchenDetailId, type, null, lineKey, lineValue,cookBookId,null, null, null, null, null, null, 0);
									kitchenDetailList.add(kitchenDetail);
								}
							}
						}
						line = buffRead.readLine();
					}
					kitchenCookbook.setTips(tipsSbf.toString());
					buffRead.close();
				}
				// 3.定义图片名字，并拷贝图片到指定文件夹
				boolean isExistPic = false;
				for (File tmpFile : infoListFile) {
					String srcFileName = tmpFile.getName();
					if (srcFileName.endsWith(".jpg") || srcFileName.endsWith(".png") || srcFileName.endsWith(".jpeg")){
						isExistPic = true;
						int pointIndex = srcFileName.lastIndexOf('.');
//						String picFileName = PinyinUtil.hanyuToPinyinSimple(srcFileName.substring(0, pointIndex))
//								+ srcFileName.substring(pointIndex);
						cookBookPicUrl = InitKithenCookieData.getCookPicName(signalFileDir.getName().split("==")[1],cookBookId)+srcFileName.substring(pointIndex);
						String goalAbsFilePath = InitKithenCookieData.outputPicDirPath + File.separator + cookBookPicUrl;
						System.out.println("Copy form " + tmpFile.getAbsolutePath());
						System.out.println("to " + goalAbsFilePath);
						if(copyPicData){
							InitKithenCookieData.copyFile(tmpFile, new File(goalAbsFilePath));//图片
						}
						kitchenCookbook.setPicUrl(cookBookPicUrl);//设置图片地址
					}
				}
				if (!isExistPic) {
					throw new RuntimeException();
				}
				
				// 组装新增对象
				kitchenCookbookList.add(kitchenCookbook);
			}
			//输出数据
			{
				System.out.println("菜谱信息：");
				for(KitchenCookbook kitchenCookbook:kitchenCookbookList){
					System.out.println(kitchenCookbook);
				}
				System.out.println("菜谱步骤信息：");
				for(KitchenCookbookStep kitchenCookbookStep:kitchenCookbookStepList){
					System.out.println(kitchenCookbookStep);
				}
				System.out.println("菜谱参数信息：");
				for(KitchenDetail kitchenDetail:kitchenDetailList){
					System.out.println(kitchenDetail);
				}
				System.out.println("菜谱类别关系数据信息：");
				for(KitchenCookbookTypeHasTKitchenCookbook kitchenCookbookTypeHasTKitchenCookbook:kitchenCookbookTypeHasTKitchenCookbookList){
					System.out.println(kitchenCookbookTypeHasTKitchenCookbook);
				}
				
			}
			//各个表数据的批量新增
		}
		
		System.out.println("t_kitchen_cookbook_startId="+InitKithenCookieData.t_kitchen_cookbook_startId);
		System.out.println("t_kitchen_cookbook_step_startId="+InitKithenCookieData.t_kitchen_cookbook_step_startId);
//		System.out.println("t_kitchen_cookbook_type_startId="+InitKithenCookieData.t_kitchen_cookbook_type_startId);
		System.out.println("t_kitchen_detail_startId="+InitKithenCookieData.t_kitchen_detail_startId);
		System.out.println("t_kitchen_cookbook_type_has_t_kitchen_cookbook_startId="+InitKithenCookieData.t_kitchen_cookbook_type_has_t_kitchen_cookbook_startId);
		if(inert2DbBath){//新增到数据库
			IKitchenCookbookBaseDao kitchenCookbookBaseDao = ctx.getBean("kitchenCookbookBaseDao", IKitchenCookbookBaseDao.class);
			IKitchenCookbookStepBaseDao kitchenCookbookStepBaseDao = ctx.getBean("kitchenCookbookStepBaseDao", IKitchenCookbookStepBaseDao.class);
			IKitchenDetailBaseDao kitchenDetailBaseDao = ctx.getBean("kitchenDetailBaseDao", IKitchenDetailBaseDao.class);
			IKitchenCookbookTypeHasTKitchenCookbookBaseDao kitchenCookbookTypeHasTKitchenCookbookBaseDao = ctx.getBean("kitchenCookbookTypeHasTKitchenCookbookBaseDao", IKitchenCookbookTypeHasTKitchenCookbookBaseDao.class);
			
//			kitchenCookbookBaseDao.insertKitchenCookbookBatch(kitchenCookbookList);
//			kitchenCookbookStepBaseDao.insertKitchenCookbookStepBatch(kitchenCookbookStepList);
//			kitchenDetailBaseDao.insertKitchenDetailBatch(kitchenDetailList);
			kitchenCookbookTypeHasTKitchenCookbookBaseDao.insertKitchenCookbookTypeHasTKitchenCookbookBatch(kitchenCookbookTypeHasTKitchenCookbookList);
		}
	}
	
	
}
