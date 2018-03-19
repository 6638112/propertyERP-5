/**   
 * Filename:    TestFetch_SZ_GuoTu_Second.java   
 * @version:    1.0  
 * Create at:   2014年11月27日 上午8:54:11   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年11月27日    shiyl      1.0         1.0 Version   
 */
package test.baiduApi.groupBuilding;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import test.baiduApi.entity.SZGuoTuGroupBuilding02;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.common.utils.FileUtils;

/**
 * Filename: TestFetch_SZ_GuoTu_Second.java
 * 
 * @version: 1.0.0 Create at: 2014年11月27日 上午8:54:11 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年11月27日 shiyl 1.0 1.0 Version
 */
public class TestFetch_SZ_GuoTu_Second {
	public static void main(String[] args) throws Exception {
		// http://ris.szpl.gov.cn/bol/EsSource.aspx?targetpage=206&zone=&tep_name=
//		Integer goalPage = 206;// 1-206
		List<SZGuoTuGroupBuilding02> resAllList = new ArrayList<SZGuoTuGroupBuilding02>();
		for(int i=1;i<=206;i++){
			Integer goalPage = i;
			List<SZGuoTuGroupBuilding02> tmpList = getSZGuoTuList(goalPage);
			if(tmpList!=null&&tmpList.size()>=0){
				resAllList.addAll(tmpList);
			}
			Thread.sleep(3000);
		}
		System.out.println("==========最终输出=============");
		System.out.println(JSON.toJSONString(resAllList));
		FileUtils.byteToFile(JSON.toJSONString(resAllList).getBytes(), "F:/szGuoTu_Second.json");
	}
	
	public static List<SZGuoTuGroupBuilding02> getSZGuoTuList(Integer goalPage) throws Exception{
		List<SZGuoTuGroupBuilding02> resList = new ArrayList<SZGuoTuGroupBuilding02>();
		String url = "http://ris.szpl.gov.cn/bol/EsSource.aspx?targetpage=" + goalPage + "&zone=&tep_name=";
		System.out.println(url);
		Document doc = Jsoup.connect(url).timeout(60000)
				.get();
		Element dataList1 = doc.getElementById("DataGrid1");
		// System.out.println(dataList1.text());
		Elements rowList = dataList1.select("tr[align=\"center\"]");
		// align="center"
//		 System.out.println(rowList.html());
		for(int i=1;i<rowList.size();i++){//跳过标题头部数据
			Element currRow = rowList.get(i);
			Elements signalColums = currRow.children();
			{
				SZGuoTuGroupBuilding02 tmpGroupBuilding = new SZGuoTuGroupBuilding02();
				String prjName = signalColums.get(0).text();// 项目名称
				String contractFlowNo = signalColums.get(1).text();// 合同流水号
				String blockName = signalColums.get(2).text();// 区属
				String area = signalColums.get(3).text();// 面积
				String useDesc = signalColums.get(4).text();// 用途
				Integer floor = null;
				try {
					floor = Integer.valueOf(signalColums.get(5).text());// 楼层
				} catch (Exception e) {
				}
				
				String roomSrcCode = signalColums.get(6).text();// 房源编码
				Element agentElement = signalColums.get(7);
				String agentName = agentElement.select("a").first().text();// 代理中介名称
				String agentTel = null;
				int lastLeft = agentElement.text().lastIndexOf("：");
				if(lastLeft==-1){lastLeft = agentElement.text().lastIndexOf(":");}
				int lastRight = agentElement.text().lastIndexOf(")");
				lastRight=lastRight==-1?agentElement.text().length():lastRight;
				if(lastLeft!=-1){
					agentTel = agentElement.text().substring(lastLeft+"：".length(),lastRight);// 代理中介电话
				}
				String publishTime = signalColums.get(8).text();// 发布日期
				tmpGroupBuilding.setAgentName(agentName);
				tmpGroupBuilding.setAgentTel(agentTel);
				tmpGroupBuilding.setArea(area);
				tmpGroupBuilding.setBlockName(blockName);
				tmpGroupBuilding.setContractFlowNo(contractFlowNo);
				tmpGroupBuilding.setFloor(floor);
				tmpGroupBuilding.setPrjName(prjName);
				tmpGroupBuilding.setPublishTime(publishTime);
				tmpGroupBuilding.setRoomSrcCode(roomSrcCode);
				tmpGroupBuilding.setUseDesc(useDesc);
				System.out.println(JSON.toJSONString(tmpGroupBuilding));
				resList.add(tmpGroupBuilding);
			}
		}
		return resList;
	}
	
}
