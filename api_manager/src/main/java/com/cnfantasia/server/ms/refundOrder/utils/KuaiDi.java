package com.cnfantasia.server.ms.refundOrder.utils;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class KuaiDi
{
    public static String searchkuaiDiInfo(String key, String com, String nu)
    {
        String content = "";
        try
        {
            URL url = new URL("http://www.kuaidi100.com/applyurl?key=" + key + "&com=" + com
                              + "&nu=" + nu);
            URLConnection con = url.openConnection();
            con.setAllowUserInteraction(false);
            InputStream urlStream = url.openStream();
            byte b[] = new byte[10000];
            int numRead = urlStream.read(b);
            content = new String(b, 0, numRead==-1?1:numRead);
            while (numRead != -1)
            {
                numRead = urlStream.read(b);
                if (numRead != -1)
                {
                    String newContent = new String(b, 0, numRead, "UTF-8");
                    content += newContent;
                }
            }
            urlStream.close();
        }
        catch (Exception e)
        {
           e.printStackTrace();
        }
        return content;
    }
}
