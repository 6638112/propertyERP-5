package com.cnfantasia.server.business.wx;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: WxCommUtil.
 * @Date: 2017-06-28 11:30
 * @Auther: kangduo
 * @Description: ()
 */
public class WxCommUtil {

    public static String parseWeixinCallback(HttpServletRequest request) throws IOException {
        // 获取微信调用我们notify_url的返回信息
        String result = "";
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            result = new String(outSteam.toByteArray(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                outSteam.close();
                if(inStream != null){
                    inStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
