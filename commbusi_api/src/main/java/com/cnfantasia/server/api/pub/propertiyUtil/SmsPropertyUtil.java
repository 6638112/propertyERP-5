package com.cnfantasia.server.api.pub.propertiyUtil;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.List;
import java.util.Properties;

/**
 * @ClassName: SmsPropertyUtil
 * @Date: 2016-07-13 17:31
 * @Auther: kangduo
 * @Description:(短信配置读取工具类)
 */
public class SmsPropertyUtil {
    private static Properties prop = new Properties();
    static{
        InputStream in = SmsPropertyUtil.class.getResourceAsStream("/com/cnfantasia/server/commbusi/msg/smsTemplate.properties");
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }

    public static String getProperty(String key, Object ... arguments) {
        String value = prop.getProperty(key);
        return MessageFormat.format(value, arguments);
    }
    
    public static String getProperty(String key, List<Object> arguments) {
        String value = prop.getProperty(key);
        if(arguments != null && arguments.size() > 0) {
        	int size = arguments.size();
        	if(size == 1) {
        		 return MessageFormat.format(value, arguments.get(0));
        	} else if(size == 2) {
        		return MessageFormat.format(value, arguments.get(0), arguments.get(1));
        	} else if(size == 3) {
        		return MessageFormat.format(value, arguments.get(0), arguments.get(1), arguments.get(2));
        	} else if(size == 4) {
        		return MessageFormat.format(value, arguments.get(0), arguments.get(1), arguments.get(2), arguments.get(3));
        	} else if(size == 5) {
        		return MessageFormat.format(value, arguments.get(0), arguments.get(1), arguments.get(2), arguments.get(3), arguments.get(4));
        	} else if(size == 6) {
        		return MessageFormat.format(value, arguments.get(0), arguments.get(1), arguments.get(2), arguments.get(3), arguments.get(4), arguments.get(5));
        	} else if(size == 7) {
        		return MessageFormat.format(value, arguments.get(0), arguments.get(1), arguments.get(2), arguments.get(3), arguments.get(4), arguments.get(5),
        				arguments.get(6));
        	} else if(size == 8) {
        		return MessageFormat.format(value, arguments.get(0), arguments.get(1), arguments.get(2), arguments.get(3), arguments.get(4), arguments.get(5),
        				arguments.get(6), arguments.get(7));
        	} else if(size == 9) {
        		return MessageFormat.format(value, arguments.get(0), arguments.get(1), arguments.get(2), arguments.get(3), arguments.get(4), arguments.get(5),
        				arguments.get(6), arguments.get(7), arguments.get(8));
        	} else if(size == 10) {
        		return MessageFormat.format(value, arguments.get(0), arguments.get(1), arguments.get(2), arguments.get(3), arguments.get(4), arguments.get(5),
        				arguments.get(6), arguments.get(7), arguments.get(8), arguments.get(9));
         	}
        }
        return value;
    }


    public static void main(String[] args) {
        String tel = "18607170879";
        String time = "2016-07-13 17:59";
        int count = 5;
        Object[] param = {tel, time, count};
        System.out.println(getProperty("send_to_supplier_after_paid", tel, time, count));
        System.out.println(getProperty("send_to_supplier_after_paid", param));
    }
}
