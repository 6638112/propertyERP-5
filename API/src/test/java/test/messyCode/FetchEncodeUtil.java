/**  
 * Filename:    FetchEncodeUtil.java  
 * @version:    1.0 
 * Create at:   2014年11月5日 上午8:01:48  
 * Description: 校验编码格式
 *  
 * Modification History:  
 * Date        Author      Version     Description  
 * -----------------------------------------------------------------
 * 2014年11月5日    shiyl      1.0         1.0 Version  
 */
package test.messyCode;

import java.io.UnsupportedEncodingException;

/**
 * Filename: FetchEncodeUtil.java
 *
 * @version: 1.0.0 Create at: 2014年11月5日 上午8:01:48 Description:
 *
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年11月5日 shiyl 1.0 1.0 Version
 */
public class FetchEncodeUtil {
    private static String[] encode = new String[] { "ISO-8859-1", "UTF-8","GBK",/*"UTF-16",*/"US-ASCII", "GB2312",null };
//    private static String[] encode = new String[] { "UTF-8","ISO-8859-1",null };
    private static final int startCount = 2;
    public static void main(String[] args) throws UnsupportedEncodingException {
        String data = "Äqé7K9Àhi,`°=ÙLì³R¯êLçÐ^·¢dé4¤¶ðó²x~ØZD[sL1d®2xÐO]?àSogAÈÆëDªÀkÁÖ°ëòÅB^~è";
//        String data = "2013��ͳ���������ͳ��绮�ִ���(��ֹ2013��8��31��)";
//        String s1 = new String(data.getBytes("ISO-8859-1"),"UTF-8");
//        String s2 = new String(s1.getBytes("ISO-8859-1"),"UTF-8");
//        String s3 = new String(s2.getBytes("ISO-8859-1"),"UTF-8");
//        System.out.println(s1);
//        System.out.println(s2);
//        System.out.println(s3);
//        String data = "顨傦拷";
        checkCount(data, startCount);
//        for (String str01 : encode) {
//            test(data, str01, null);
//        }
//        for (String str01 : encode) {
//            test(data, null, str01);
//        }
    }
    /*
     *     你 --from--UTF-8--to--ISO-8859-1 is-ä½ 
        ä½  --from--UTF-8--to--ISO-8859-1 is-Ã¤Â½Â 
            Ã¤Â½Â  --from--UTF-8--to--ISO-8859-1 is-ÃÂ¤ÃÂ½ÃÂ 
     */
    public static String transdata(String data, String src, String goal) throws UnsupportedEncodingException {
        String res = null;
        if (src != null && goal != null) {
            res = new String(data.getBytes(src), goal);
        } else if (src != null && goal == null) {
            res = new String(data.getBytes(src));
        } else if (src == null && goal != null) {
            res = new String(data.getBytes(), goal);
        }else{
            res = data;
        }
        return res;
    }

//    public static String try01(String data) throws UnsupportedEncodingException {
//        String res = null;
//        for (String str01 : encode) {
//            for (String str02 : encode) {
//                String curr = test(data, str01, str02);
//                System.out.println(str01+"to"+str02+":"+curr);
//                for (String str03 : encode) {
//                    for (String str04 : encode) {
//                        res = test(curr, str03, str04);
//                        System.out.println("=======and "+str03+"to"+str04+":"+res);
//                    }
//                }
//            }
//        }
//        return res;
//    }
    public static void checkCount(String data,int count) throws UnsupportedEncodingException{
        if(count<=0){return;}
        count--;
        for (String srcEncode : encode) {
            for (String goalEncode : encode) {
                if(srcEncode==null&&goalEncode==null){
                   
                }else if(srcEncode!=null&&goalEncode!=null&&srcEncode.equals(goalEncode)){
                   
                }else{
                    String tmp = transdata(data, srcEncode, goalEncode);
                    System.out.println(getPreData(count)+data+" --from--"+srcEncode+"--to--"+goalEncode+" is-"+tmp);
                    if(tmp.contains("<!")){
                    	count = 0;
                    	System.exit(0);
                    }
                    checkCount(tmp, count);
                }
            }
        }
       
    }
    public static String getPreData(int tmpCount){
    	int count = startCount-tmpCount;
    	StringBuffer sbf = new StringBuffer();
    	for(int i=0;i<count;i++){
    		sbf.append("    ");
    	}
    	return sbf.toString();
    }
}

