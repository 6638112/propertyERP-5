package com.cnfantasia.server.common.utils;

import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName: UUIDGenerater
 * @Date: 2016-07-29 13:18
 * @Auther: kangduo
 * @Description:(生成UUID字符窜)
 */
public class UUIDGenerater {
    private static final AtomicLong LAST_TIME_MS = new AtomicLong();

    private static final String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };

    /**
     *
     * @Title: getId
     * @Description: (生成UUID)
     * @return uuid字符窜
     */
    public static String getId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    
    /**
     * 产生 length 位的短位UUID(字母 + 数字)
     * 将32位的uuid，四位合一位，并舍弃最后两位
     * @param length 最大只允许传入8位
     * @return uuid
     */
    public static String generateShortUuid(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < length; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            stringBuilder.append(chars[x % 0x3E]);
        }
        return stringBuilder.toString();
    }

    /**
     * 产生6位的短位UUID(字母 + 数字)
     * 将32位的uuid，四位合一位，并舍弃最后两位
     * @return uuid
     */
    public static String generateShortUuid() {
    	return generateShortUuid(6);
    }

    /**
     *
     * @Title: getFileName
     * @Description: (获取文件名)
     * @return
     */
    public static String getFileName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(getUniqueTimestamp());
    }

    /**
     * @return
     * @Title: getUniqueTimestamp
     * @Description: (生成时间戳)
     */
    private static long getUniqueTimestamp() {
        long now = System.currentTimeMillis();
        while (true) {
            long lastTime = LAST_TIME_MS.get();
            if (lastTime >= now)
                now = lastTime + 1;
            if (LAST_TIME_MS.compareAndSet(lastTime, now))
                return now;
        }
    }

}
