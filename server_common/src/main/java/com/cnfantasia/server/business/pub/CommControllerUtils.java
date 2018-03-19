package com.cnfantasia.server.business.pub;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.cnfantasia.server.business.pub.constant.CommBussinessConstant;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.exception.FocRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * 描述:负责控制器Controller执行的一些工具方法
 * 
 * @version 1.00
 * @author syl
 * 
 */
public class CommControllerUtils {
	private static Logger logger = Logger.getLogger(CommControllerUtils.class);
	 /**日期格式字符串描述**/
	public static final String sys0LastUpdTimeKey = "sys0LastUpdTime";
	/**
	 * 解析分页
	 */
	public static PageModel getPageModel(HttpServletRequest request){
		Integer page = null;
		Integer pageNum = null;
		try {
			page = Integer.parseInt(request.getParameter(PageModel.PageKey.PAGE));
			pageNum = Integer.parseInt(request.getParameter(PageModel.PageKey.PAGE_NUM));
		} catch (Exception e) {
			throw new FocRuntimeException("request.page.parse.error");
		}
		PageModel pageModel = new PageModel((page-1)*pageNum, pageNum);
		return pageModel;
	}
	/**
	 * 解析分页
	 */
	public static PageModel getPageModel(HttpServletRequest request,Integer defaultPage,Integer defaultPageNum){
		Integer page = null;
		Integer pageNum = null;
		try {
			page = Integer.parseInt(request.getParameter(PageModel.PageKey.PAGE));
			pageNum = Integer.parseInt(request.getParameter(PageModel.PageKey.PAGE_NUM));
		} catch (Exception e) {
			page = defaultPage;
			pageNum = defaultPageNum;
		}
		PageModel pageModel = new PageModel((page-1)*pageNum, pageNum);
		return pageModel;
	}
	/**
	 * 获取请求的
	 * @return
	 */
	private static Long getRequestLastUpdTime(HttpServletRequest request){
		Long resLong = null;
		String time = request.getParameter(sys0LastUpdTimeKey);
		if(!StringUtils.isEmpty(time)){
			try {
				resLong = DateUtil.timeToLong(time, DateUtil.formatSecond.get());
			} catch (Exception e) {
				logger.debug("The request param sys0LastUpdTime parse to long failed.", e);
			}
		}
		return resLong;
	}
	
	/**
	 * 核对是否需要更新
	 * @param request
	 * @param jsonResponse
	 * @param nowUpdTime
	 * @return true表示需要更新，false表示未更新
	 */
	public static boolean checkHasNewData(HttpServletRequest request,JsonResponse jsonResponse,String nowUpdTime){
			boolean needUpd = true;
			//获取客户端提交的更新时间
			Long lastUpdTime = getRequestLastUpdTime(request);
			//若相等 则直接返回
			Long nowTimeLong =  null;
			if(!StringUtils.isEmpty(nowUpdTime)){
				nowTimeLong = DateUtil.timeToLong(nowUpdTime, DateUtil.formatSecond.get());
			}
			if(nowTimeLong!=null){//若不为空
				jsonResponse.putData(sys0LastUpdTimeKey, nowUpdTime);
				if(lastUpdTime!=null&&lastUpdTime.compareTo(nowTimeLong)==0){//若客户端上送的更新时间与服务器获得的时间相同 则直接返回更新时间
					needUpd = false;
				}
			}
			return needUpd;
	}
	
	/**
	 * 解析查询时间的年，月
	 */
	public static String getYearMonth(HttpServletRequest request){
		String resStr = null;
		String yearStr = request.getParameter("year");
		String monthStr = request.getParameter("month");
		try {
			if(!StringUtils.isEmpty(yearStr)&&!StringUtils.isEmpty(monthStr)){
				Integer year = Integer.valueOf(yearStr);
				Integer month = Integer.valueOf(monthStr);
				resStr =  DateUtil.formateYear(year)+DateUtil.formateMonth(month);
			}
		} catch (Exception e) {
			logger.debug("CommControllerUtils.getYearMonth cause exception,yearStr is:"+yearStr+",monthStr is:"+monthStr, e);
		}
		return resStr;
	}
	
	/**
	 * 往JsonResponse填充分页信息
	 * @param jsonResponse
	 * @param list 记录列表
	 * @param isLast 是否是最后一页：true表示为最后一页，每页下一页了;false相反。
	 * @return
	 */
	public static JsonResponse addPageInfo(JsonResponse jsonResponse,List<?> list){
		addPageInfo(jsonResponse, list, true, list==null?0:list.size());
		return jsonResponse;
	}
	
	public static JsonResponse addPageInfo(JsonResponse jsonResponse,Object list,boolean isLast,Integer count){
		boolean hasNext = !isLast;
		jsonResponse.putData(PageModel.PageKey.LIST, list);
		jsonResponse.putData(PageModel.PageKey.HAS_NEXT, hasNext);
		if(count!=null){
			jsonResponse.putData(PageModel.PageKey.COUNT, count);
		}
		return jsonResponse;
	}
	/**
	 * 防止重复提交 
	 * @param request
	 */
	public static void checkToken(HttpServletRequest request){
		String _token=request.getParameter(CommBussinessConstant.TOKEN);
		String lastToken=(String)request.getSession().getAttribute(CommBussinessConstant.TOKEN);
		if(lastToken!=null&&lastToken.equals(_token)){
			throw new FocRuntimeException("system.repeatSubmit");
		}else{
			request.getSession().setAttribute(CommBussinessConstant.TOKEN,_token);
		}
	}
//	/**
//	 * 新增、更新或者删除操作结果返回数据处理
//	 * @param request request对象
//	 * @param res 操作结果个数
//	 */
//  public static void checkOperRes(HttpServletRequest request,int res){
//		if(res<=0){
//			request.setAttribute(FocConstants.TRANS_RESULT, FocConstants.TRAN_STATUS_FAILED);
//		}else{
//			request.setAttribute(FocConstants.TRANS_RESULT, FocConstants.TRAN_STATUS_SUCCESSFUL);
//		}
//	}
//  /**
//   * 设置操作结果
//   * @param request request对象
//   * @param result 操作结果，成功或者失败
//   */
//  public static void checkOperRes(HttpServletRequest request,String result){
//    request.setAttribute(FocConstants.TRANS_RESULT, result);
//  }
//  /**
//   * 设置操作结果
//   * @param request request对象
//   * @param errorCode 错误码
//   * @param errDatas 错误码对应的错误信息的取值编号
//   */
//	public static void checkOperRes(HttpServletRequest request,String errorCode,Object[] errDatas){
//	  String errorMsg=MessageSourceUtil.getMessage(errorCode, errDatas, null);
//	  request.setAttribute(FocConstants.ERRCODE, errorCode);
//	  request.setAttribute(FocConstants.ERRMESSAGE, errorMsg);
//	  request.setAttribute(FocConstants.TRANS_RESULT, errorMsg);//设定交易结果为错误信息
//	}
	/**
   * 获取request的map组合信息，如请求map/aa,map/bb,则得到map,包含key为aa、bb
   * @param request 
   * @param mapName 例如:map
   * @param split 例如:/
   * @return
   */
  @SuppressWarnings("unchecked")
	public static HashMap<String,Object> getMapByPPName(HttpServletRequest request,String mapName,String split){
    HashMap<String,Object> resMap=new HashMap<String, Object>();
    String compStr=mapName+split;
    Map<String,String[]> srcMap=request.getParameterMap();
    for(String key:srcMap.keySet()){
      if(key.startsWith(compStr)){
        String[] values=srcMap.get(key);
        if(values==null||values.length>1){
          resMap.put(key.substring(compStr.length()), values);//此处取全部的值
        }else{
          resMap.put(key.substring(compStr.length()), values[0]);
        }
      }
    }
    return resMap;
  }
  @SuppressWarnings("unchecked")
	public static Map<String,Object> getParameterMap(HttpServletRequest request,String split){
    HashMap<String,Object> resMap=new HashMap<String, Object>();
    Map<String,String[]> srcMap=request.getParameterMap();
    for(String key:srcMap.keySet()){
      String[] values=srcMap.get(key);
      if(values==null||values.length>1){
        resMap.put(key, values);//此处取全部的值
      }else{
        resMap.put(key, values[0]);
      }
    }
    return (Map<String,Object>)getInitSubs(resMap, split);
  }
  @SuppressWarnings("unchecked")
	public static void main(String[] args) {
    String[] aa=new String[]{};
    System.out.println(aa.getClass());
    HashMap<String,Object> srcDataMap =new HashMap<String, Object>(); 
    srcDataMap.put("aa", 1);
    srcDataMap.put("bb/z01", 2);
    srcDataMap.put("bb/z02", 3);
    srcDataMap.put("cc/x01/y01", 4);
    srcDataMap.put("cc/x01/y02", 5);
    srcDataMap.put("cc/x01/y03", 6);
    Map<String,Object> goal=(Map<String,Object>)getInitSubs(srcDataMap, "/");
    System.out.println(goal);
  }
  /**
   * 将Map的key名字通过split拆分，组织得到信息Map
   * 不会影响到srcDataMap的数据
   * @param tails
   * @param split
   * @param srcDataMap
   * @return
   */
  @SuppressWarnings("unchecked")
	public static Object getInitSubs(HashMap<String,Object> srcDataMap,String split){
    HashMap<String,Object> tmpSrcMap=(HashMap<String,Object>)srcDataMap.clone();
    HashMap<String,Set<String>> head_SubNames=new HashMap<String, Set<String>>();
//    boolean isArr=true;
    Set<String> arrayNames=new HashSet<String>();
    for(String name:tmpSrcMap.keySet()){
      int index=name.indexOf(split);
      if(index!=-1){
        String head=name.substring(0, index);
        String tail=name.substring(index+split.length());
        Set<String> tailsSet=null;
        if(head_SubNames.get(head)==null){
          tailsSet=new HashSet<String>();
        }else{
          tailsSet=head_SubNames.get(head);
        }
        tailsSet.add(tail);
        head_SubNames.put(head,tailsSet);
      }else{
        if(name.matches("^[a-zA-Z0-9_]{1,}\\[[0-9]{1,}\\]$")){
          arrayNames.add(name);
        }
      }
    }
    if(head_SubNames.size()>0){
      for(String tmpHead:head_SubNames.keySet()){
        Set<String> tailsAA=head_SubNames.get(tmpHead);
        HashMap<String,Object> newMap=new HashMap<String, Object>();
        for(String tailsAAName:tailsAA){
          newMap.put(tailsAAName, tmpSrcMap.get(tmpHead+split+tailsAAName));
          tmpSrcMap.remove(tmpHead+split+tailsAAName);
        }
        Object withLastMap=getInitSubs(newMap,split);
        tmpSrcMap.put(tmpHead, withLastMap);
      }
      return tmpSrcMap;
    }else{
      if(arrayNames.size()>0){
        HashMap<String,Set<String>> array_SubNames=new HashMap<String, Set<String>>();
        for(String arrName:arrayNames){
          int index=arrName.indexOf("["); 
          String head=arrName.substring(0, index);
          String tail=arrName.substring(index+"[".length());
          Set<String> arrSet=null;
          if(array_SubNames.get(head)==null){
            arrSet=new HashSet<String>();
          }else{
            arrSet=array_SubNames.get(head);
          }
          arrSet.add(tail);
          array_SubNames.put(head,arrSet);
        }
        for(String arrKey:array_SubNames.keySet()){
          Set<String> aaSet=array_SubNames.get(arrKey);
          Object[] data=new Object[aaSet.size()];
          int i=-1;
          for(String arrTail:aaSet){
            i++;
            data[i]=tmpSrcMap.get(arrKey+"["+arrTail);
            tmpSrcMap.remove(arrKey+"["+arrTail);
          }
          tmpSrcMap.put(arrKey, data);
        }
      }
      return tmpSrcMap;
    }
    
  }
	/**
	 * 获取request的map组合信息，如请求map/aa,map/bb,则得到map,包含key为aa、bb
	 * @param request 
	 * @param mapName 例如:map
	 * @param split 例如:/
	 * @return
	 */
	public static HashMap<String,Object> getMapByPPName(HttpServletRequest request,String mapName,String split,String[] q_paramsArry){
		HashMap<String,Object> resMap=new HashMap<String, Object>();
		HashMap<String,Object> total=getMapByPPName(request, mapName, split);
		for(String key:q_paramsArry){
		  resMap.put(key, total.get(key));
    }
		return resMap;
	}
	/**
	 * 获取request的map组合信息，如请求map/aa,map/bb,则得到map,包含key为aa、bb
	 * @param request 
	 * @param mapName 例如:map
	 * @param split 例如:/
	 * @return 返回key、value都为String类型
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String,String> getMapByPPNameStr(HttpServletRequest request,String mapName,String split){
		HashMap<String,String> resMap=new HashMap<String, String>();
		String compStr=mapName+split;
		Map<String,String[]> srcMap=request.getParameterMap();
		for(String key:srcMap.keySet()){
			if(key.startsWith(compStr)){
				Object[] values=(Object[])srcMap.get(key);
				if(values==null||values.length>1){
					continue;//此处跳过
				}else{
					if(values[0]==null){
						resMap.put(key.substring(compStr.length()), null);
					}else{
						resMap.put(key.substring(compStr.length()), values[0].toString());
					}
				}
			}
		}
		return resMap;
	}
//	/**
//	 * 获取根据表名获取uuid,例如传入表面tbl_Card，则得到要新增的uuid的值
//	 * @param request
//	 * @param tableName
//	 * @return
//	 */
//	public static synchronized String getPrimaryKey(String tableName){
//		String seqid=null;
//		seqid=UuidService.getNextUuid(tableName);
//		return seqid;
//	}
	
	/**
	 * 获取指定名称的parmaters对应的values的值，返回HashMap存储
	 * @param request
	 * @param q_paramsArry
	 * @return
	 */
	public static HashMap<String,Object> getParameterValues(HttpServletRequest request,String[] q_paramsArry){
		HashMap<String,Object> resMap=new HashMap<String, Object>();
		for(String tmp:q_paramsArry){
			Object o=request.getParameter(tmp);
			if(o!=null&&!StringUtils.isStrEmpty(o.toString())){
				resMap.put(tmp, request.getParameter(tmp));
			}
		}
		return resMap;
	}
	/**
	 * 获取指定名称的attributes对应的values的值，返回HashMap存储
	 * @param request
	 * @param q_attributesArry
	 * @return
	 */
	public static HashMap<String,Object> getAttributeValues(HttpServletRequest request,String[] q_attributesArry){
		HashMap<String,Object> resMap=new HashMap<String, Object>();
		for(String tmp:q_attributesArry){
			resMap.put(tmp, request.getAttribute(tmp));
		}
		return resMap;
	}
	/**
	 * 获取request的parameterMap,重新组装得到新Map返回,对应value为数组的情况，舍弃
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String,Object> getNewParameterMap(HttpServletRequest request){
	  Map<String,String[]> srcMap=request.getParameterMap();
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.putAll(srcMap);
		for(String key:paramMap.keySet()){
			Object[] values=(Object[])paramMap.get(key);
			if(values==null||values.length>1){
				continue;
			}else{
				paramMap.put(key, values[0]);
			}
		}
		return paramMap;
	}
	
//	//存入用户信息到session
//	public static void setUserToSession(HttpServletRequest request,SysUser user){
//		HttpSession session = request.getSession(true);
//		session.setAttribute(FocConstants.USER_CONTEXT, user);
//	}
//	//从session中获取用户信息
//	public static User getUserFromSession(HttpServletRequest request){
//		HttpSession session = request.getSession(true);
//		User user=(User)(session.getAttribute(FocConstants.USER_CONTEXT));
//		return user;
//	}
	
//	/**
//	 * 更新重新加载系统参数信息到缓存
//	 * @param request
//	 * @param sysParaCode
//	 * @param paraValue
//	 */
//	public static void updSysParaValue(HttpServletRequest request){
//	  InitCommonListener.initSysParam(request.getSession().getServletContext());
//	}
	/**
	 * 查询指定多个类型的数据字典
	 * @param request
	 * @param dictTypeArry
	 * @return
	 */
//	@SuppressWarnings("unchecked")
//	public static HashMap<String,HashMap<String,Object>> getDataDict(HttpServletRequest request,String[] dictTypeArry){
//		HashMap<String,List<HashMap<String, Object>>> dataDictTotal=(HashMap<String,List<HashMap<String, Object>>>)request.getSession().getServletContext().getAttribute(FocConstants.CONTEXT_DATA_DICT);
//		HashMap<String,HashMap<String,Object>> resDict=new HashMap<String, HashMap<String,Object>>();
//		for(String dictType:dictTypeArry){
//			HashMap<String,Object> tmpMap=new HashMap<String, Object>();
//			List<HashMap<String, Object>> tmpDataDict=dataDictTotal.get(dictType);
//			for(HashMap<String,Object> data:tmpDataDict){
//				if(data.get(FocConstants.DICT_TypeId).equals(dictType)){
//					if(data.get(FocConstants.DICT_KEY)==null){
//						logger.warn("数据字典的"+dictType+"类型存在"+FocConstants.DICT_KEY+"为null的数据。");
//					}else{
//						tmpMap.put(data.get(FocConstants.DICT_KEY).toString(), data.get(FocConstants.DICT_VALUE));
//					}
//				}
//			}
//			resDict.put(dictType, tmpMap);
//		}
//		return resDict;
//	}
	
 	
 	//比较形如2013-3-2 16:18:38格式的两个日志字符串的大小，如果前面比后面大，则返回true
 	public static Boolean compareStringDate(String str1,String str2){
 		if(str1==null||str2==null){return null;}
 		Boolean resBool=null;
 		DateFormat sdf = DateUtil.formatSecond.get();
 		Date date1=null;
 		Date date2=null;
		try {
			date1 = sdf.parse(str1);
			date2 = sdf.parse(str2);
			if(date1.getTime()>date2.getTime()){
	 			resBool=true;
	 		}else{
	 			resBool=false;
	 		}
		} catch (ParseException e) {
			logger.debug(e.getMessage());
			resBool=null;
		}
 		return resBool;
 	}
 	//统计两个时间的时间差
 	public static Long subtracDate(Object str1,Object str2){
 		if(str1==null||str2==null){return null;}
 		DateFormat sdf = DateUtil.formatSecond.get();
 		Date date1=null;
 		Date date2=null;
		try {
			date1 = sdf.parse(str1.toString());
			date2 = sdf.parse(str2.toString());
		} catch (ParseException e) {
			logger.debug(e.getMessage());
			return null;
		}
 		return date1.getTime()-date2.getTime();
 	}
 	/**
	 * 统计所有任务的开始时间最小值和截止时间最大值，结果存入resMap中
	 * @param taskListMap
	 * @param resMap
	 */
//	@SuppressWarnings("unchecked")
//	public static  void getAllTaskStartEndTime(List<HashMap<String,Object>> taskListMap,Map resMap){
//		String start=null;
//		String end=null;
//		boolean haveInitStart=false;
//		boolean haveInitEnd=false;
//		for(HashMap<String,Object> tmpTask:taskListMap){
//			if(tmpTask.get("taskBeginTime")!=null){
//				if(!haveInitStart){//如果尚未对start初始化
//					start=tmpTask.get("taskBeginTime").toString();
//					haveInitStart=true;
//				}else{
//					String currStart=tmpTask.get("taskBeginTime").toString();
//					Boolean resTmpBool=ControllerUtils.compareStringDate(start, currStart);
//					if(resTmpBool!=null&&resTmpBool){
//						start=currStart;
//					}
//				}
//			}
//			if(tmpTask.get("taskEndTime")!=null){
//				if(!haveInitEnd){
//					end=tmpTask.get("taskEndTime").toString();
//					haveInitEnd=true;
//				}else{
//					String currEnd=tmpTask.get("taskEndTime").toString();
//					Boolean resTmpBool=ControllerUtils.compareStringDate(currEnd,end);
//					if(resTmpBool!=null&&resTmpBool){
//						end=currEnd;
//					}
//				}
//			}
//		}
//		if(start!=null){resMap.put("start_Append", start);}
//		if(end!=null){resMap.put("end_Append", end);}
//	}
	public static String getDateStr(long millis) {
		long day=millis/24/60/60/1000;
		long houre=(millis-day*24*60*60*1000)/60/60/1000;
		long minute=(millis-day*24*60*60*1000-houre*60*60*1000)/60/1000;
		long miao=(millis-day*24*60*60*1000-houre*60*60*1000-minute*60*1000)/1000;
		long haomiao=millis-day*24*60*60*1000-houre*60*60*1000-minute*60*1000-miao*1000;
	    StringBuffer sbf=new StringBuffer();
	    if(day>0){sbf.append(day);sbf.append("天");sbf.append(houre);sbf.append("时");sbf.append(minute);sbf.append("分");sbf.append(miao);sbf.append("秒");}
	    else if(houre>0){sbf.append(houre);sbf.append("时");sbf.append(minute);sbf.append("分");sbf.append(miao);sbf.append("秒");}
	    else if(minute>0){sbf.append(minute);sbf.append("分");sbf.append(miao);sbf.append("秒");}
	    else if(haomiao>0){sbf.append(miao);sbf.append("秒");}
	    if(haomiao>0){sbf.append(haomiao);sbf.append("毫秒");}
		return sbf.toString();
		/*Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        Formatter ft=new Formatter(Locale.CHINA);
        return ft.format("%1$tY年%1$tm月%1$td日%1$tA，%1$tT %1$tp", cal).toString();*/
	}
	/**
	 * 比较两个版本号大小  1.3.5
	 * 如果原始字符串大于等于目标字符串则返回true,
	 * 否则返回false
	 * @param src 原始字符串
	 * @param goal 目标字符串
	 * @return
	 */
	public static Boolean compVersion(String src,String goal){
	  String[] srcArr=src.split("\\.");
	  String[] goalArr=goal.split("\\.");
	  int miniLength=srcArr.length<goalArr.length?srcArr.length:goalArr.length;
	  for(int i=0;i<miniLength;i++ ){
	    int resTmp=srcArr[i].compareTo(goalArr[i]);
	    if(resTmp>0){return true;}
	    else if(resTmp<0){return false;}
	  }
	  if(srcArr.length>goalArr.length){return true;}
	  return true;//相等时返回true
	}
	
}
