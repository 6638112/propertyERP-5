package com.cnfantasia.server.api.pub.sys;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.HttpUtil;



public class TestSysTask {
	
	protected final static Logger logger = Logger.getLogger(TestSysTask.class);

	private static BlockingQueue<Sys> sysQueue = new LinkedBlockingQueue<Sys>(2000);
	
	static{
		for(int i=0;i < 80;i++){
			new Thread(new SysWorker(sysQueue)).start();
		}
//		try {
//			execTask("E:\\schedule.csv");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	public static void execTask(String path, String data) throws Exception {
		if(path != null) {
			logger.debug("TestSysTask-path:" + path + ";data:" + data);
		}
		logger.debug("TestSysTask-start:" + DateUtils.getCurrentDate());
		List<Sys> sysList = parseFile(path, data);
		int i = 0;
		for(Sys sys : sysList) {
			while(sys.getReqStartTime().getTime() >= System.currentTimeMillis()) {
				Thread.sleep(200);
			}
			if((System.currentTimeMillis() - sys.getReqStartTime().getTime()) < 60000) {
				sysQueue.offer(sys);
				i++;
				if(i % 50 == 0) {
					logger.debug("TestSysTask-execTask:" + i);
				}
			}
		}
		logger.debug("TestSysTask-end:" + DateUtils.getCurrentDate());
	}
	
	public static List<Sys> parseFile(String path, String data) throws Exception {
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader (fr);
		String line="";
		List<Sys> sysList = new LinkedList<Sys>();
	    while ((line=br.readLine())!=null) {
	    	System.out.println(line);
//	    	line = line.replaceAll("\"", "").trim();
	    	String[] arr = line.split("\",\"");
	    	Sys tmp = new Sys(); //Integer.valueOf(arr[0].trim()), arr[1].trim(), arr[2].trim());
	    	tmp.setReqStartTime(DateUtils.strToDateTime2(data + " " + arr[0].replaceAll("\"", "").trim()));
	    	tmp.setIp(arr[1].trim());
	    	tmp.setDeviceId(arr[2].trim());
	    	tmp.setOs(arr[3].trim());
	    	tmp.setChannel(arr[4].replaceAll("\"", "").trim());
	    	sysList.add(tmp);
	    }
	    br.close();
	    fr.close();
	    return sysList;
	}
	
	/**
	 * 每隔一段时间批量插入
	 * @author
	 *
	 */
	static class SysWorker implements Runnable {
		
		private final BlockingQueue<Sys> queue;
		
		private Map<Integer, String> parentTypeMap = new HashMap<Integer, String>();
		
		private Random random = new Random();
		
		SysWorker(BlockingQueue<Sys> objQueue){
			queue = objQueue;
			parentTypeMap.put(0, "111");
			parentTypeMap.put(1, "112");
			parentTypeMap.put(2, "113");
			parentTypeMap.put(3, "114");
		}
		
		public void run() {
			while (true) {
				try {
					List<Sys> sysList = new ArrayList<Sys>();
					queue.drainTo(sysList, 1);
					if(sysList != null && sysList.size() != 0) {
						Sys sys = sysList.get(0);
						HttpUtil httpUtil = new HttpUtil();
						httpUtil.addHeader("ipTest", sys.getIp());
						httpUtil.addHeader("osTest", sys.getOs());
						httpUtil.addHeader("version", "3.1.3");
						httpUtil.addHeader("subChannelId", sys.getChannel()); //1 Android 2 iOs
//						httpUtil.addHeader("subChannelId", "1"); //1 Android 2 iOs
						httpUtil.addHeader("deviceId", sys.getDeviceId());
						httpUtil.post("http://api.jiefangqu.com:8080/API313/performPro/getGlobalValue.json", 5000);
						httpUtil.post("http://api.jiefangqu.com:8080/API313/communitySupply/qrySupplyTypeList3Level.json", 5000);
						
						Thread.sleep(1400 * random.nextInt(5));
						httpUtil.addParameter("page", "1");
						httpUtil.addParameter("pageNum", "15");
						httpUtil.addParameter("parentTypeId", parentTypeMap.get(random.nextInt(4)));
						httpUtil.post("http://api.jiefangqu.com:8080/API313/dredge/getDredgeTypeList.json", 5000);
						
						if(random.nextInt(3) != 1) {
							Thread.sleep(2000 * random.nextInt(5));
							httpUtil.addParameter("parentTypeId", parentTypeMap.get(random.nextInt(4)));
							httpUtil.post("http://api.jiefangqu.com:8080/API313/dredge/getDredgeTypeList.json", 5000);
						}
						
						
						if(random.nextInt(2) != 0) {
							Thread.sleep(2000 * random.nextInt(4));
							httpUtil.addParameter("parentTypeId", parentTypeMap.get(random.nextInt(4)));
							httpUtil.post("http://api.jiefangqu.com:8080/API313/dredge/getDredgeTypeList.json", 5000);
						}
						
						Thread.sleep(1000 * random.nextInt(6));
						
						httpUtil.clearParameter();
						if(random.nextInt(2) != 1) {
							Thread.sleep(3000 * random.nextInt(4));
							httpUtil.post("http://api.jiefangqu.com:8080/API313/xanadu/qryCurrStatus.json", 5000);
							httpUtil.post("http://api.jiefangqu.com:8080/API313/advertise/qryCommunitySuggest.json", 5000);
							
							httpUtil.addParameter("page", "1");
							httpUtil.addParameter("pageNum", "8");
							httpUtil.post("http://api.jiefangqu.com:8080/API313/microblog/qryMicroblogList.json", 5000);
							httpUtil.clearParameter();
						}
						
						if(random.nextInt(2) != 1) {
							Thread.sleep(3100 * random.nextInt(5));
							httpUtil.post("http://api.jiefangqu.com:8080/API313/ebuyNew/qrySearchWord.json", 5000);
							httpUtil.post("http://api.jiefangqu.com:8080/API313/ebuyNew/qryAds.json", 5000);
							httpUtil.addParameter("page", "1");
							httpUtil.addParameter("pageNum", "8");
							httpUtil.post("http://api.jiefangqu.com:8080/API313/ebuyNew/qryHomeProdList.json", 5000);
							httpUtil.clearParameter();
							httpUtil.post("http://api.jiefangqu.com:8080/API313/ebuyNew/qryHomePacketList.json", 5000);
						}
						
					} else {
						Thread.sleep(10000);	//5*60*1000
					}
				} catch (Exception ex) {
					logger.error("add failed!!!", ex);
				}
			}

		}

	}
	

}
