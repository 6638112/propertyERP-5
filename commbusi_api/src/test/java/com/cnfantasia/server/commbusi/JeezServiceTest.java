package com.cnfantasia.server.commbusi;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.common.xml.JaxbXMLUtil;
import com.propertySoftwareDock.jeez.entity.JeezFeeEntity;
import com.propertySoftwareDock.jeez.entity.JeezHouse;
import com.propertySoftwareDock.jeez.entity.JeezResult;
import com.propertySoftwareDock.jeez.entity.JeezUser;
import com.propertySoftwareDock.jeez.service.JeezStub;
import com.propertySoftwareDock.jeez.util.JeezUtil;

import java.security.KeyPair;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName: JeezServiceTest
 * @Date: 2016-11-28 13:34
 * @Auther: kangduo
 * @Description:()
 */
public class JeezServiceTest extends AppTest{

//    private static final String rsaPublicKey = "<RSAKeyValue><Modulus>qBVApceiMc3jq+rrs/7cSQgjMr3i+f0QrG6goGeAVPUo/4QDtTuOmdX6pwYwYpx0nkocJeK/r0QauJjoTxdp9CI/VP/YHKL4f+3NsZOp+OIZKLMExNqXqp6KkPR81EslEDqFjGqFL3CvEmFtwF7KLX2T2fbIwE6JxQUGR9jVZQ8=</Modulus><Exponent>AQAB</Exponent></RSAKeyValue>";
//    private static final String rsaPrivateKey = "<RSAKeyValue><Modulus>qBVApceiMc3jq+rrs/7cSQgjMr3i+f0QrG6goGeAVPUo/4QDtTuOmdX6pwYwYpx0nkocJeK/r0QauJjoTxdp9CI/VP/YHKL4f+3NsZOp+OIZKLMExNqXqp6KkPR81EslEDqFjGqFL3CvEmFtwF7KLX2T2fbIwE6JxQUGR9jVZQ8=</Modulus><Exponent>AQAB</Exponent><P>36dY5I9kgC800gTJOzh5zjqaaO9IGKxny0/pLPym8AmeQrHvc7XH7GhC8zvGQ0EBhR8FMPXOYqyP6kzEiV9nOQ==</P><Q>wGRulH3A3J49CLRvOPBhBk3kkKC3m9386CfTUjpTf7u83t1LrAM5BNkrBIxMRYDnI6IoYz6nzeSD6vvkME+mhw==</Q><DP>XtZJfXHIfgqGbWAWLISAMhDrlP+SJNRGlxHloGvqJfMOJt3o2boFcpureBSqt64DBP3oZ6Bzyae+xu2SkWXhEQ==</DP><DQ>bXO1hTzVj61mQ6GC9P2r9Q4zTiRg0IfF7b1ad5k/D5gapx92gXGD6sIxuvCmI5Rl6tvB5lQq2vN6KSEMfH+T2Q==</DQ><InverseQ>of4ixbuLtajTgApFGnwtxIv7j1ma43o/fTLDZAV+V8MyIAZQowlDSAtxsQpTApRt6CTQZSpQsBpIY2XfJXSPKw==</InverseQ><D>LSoYxvMPqCZUyPtE/oa7FnyzSI0xkk7nzg2j57YoC6wcqhwFCwvH05pl0b+d/MFhrK7pRVzaePPp4jRkJ+x4sYs+4waXPz6BjzSdSbancTXf61tD/z2sT19NHf2tGU8TNoxQk6NvngbrpUQmGsHqUuSE/4E1q3zGOIZFg55RJSE=</D></RSAKeyValue>";
//    private static final String serverAddr = "http://dongbinhuayuan.ticp.net/JeezWeb/JeezPMSPotralService.asmx";
    private static final String serverAddr = "http://soft2.jeez.cn:8201/JeezWeb/JeezPMSPotralService.asmx";
    
    private static final String rsaPublicKey = "<RSAKeyValue><Modulus>mQoMukREwhl7+72kFbQByOqsYM+9LIoo9DWPn/L1AJz9ZDwU/gSy1sitkYQ/i7PrT4weJoSP/y0GPs9VYlksmSe7hzi9xfDO0ITOVcPBV9tq9sR3g549RgUi5nJ3uPdNLd2p+p1Xf8QtQc0TT534zec3jYc8SWkNCgEwxSERBbM=</Modulus><Exponent>AQAB</Exponent></RSAKeyValue>";
    private static final String rsaPrivateKey = "<RSAKeyValue><Modulus>mQoMukREwhl7+72kFbQByOqsYM+9LIoo9DWPn/L1AJz9ZDwU/gSy1sitkYQ/i7PrT4weJoSP/y0GPs9VYlksmSe7hzi9xfDO0ITOVcPBV9tq9sR3g549RgUi5nJ3uPdNLd2p+p1Xf8QtQc0TT534zec3jYc8SWkNCgEwxSERBbM=</Modulus><Exponent>AQAB</Exponent><P>zcJKdSENUmjfUtuOHoyTT8pXVaDaLQl5CZ1KNDSahhkNB1CQxDJxJgwmcoQPNqo9gM3qr7SHNYkJgJX5vilpbQ==</P><Q>vmhSyCD9JrRsle9Nv5zb6RGmlzKvFJh1yeitWsJsHuZnyNpzKFzJBeCiLGoRiroWDBYgw3M4ocCsE1qZ0kbXnw==</Q><DP>vafw6bXliaiQ8NbfUNlSj6F6jrsNX6XzoyEQXL6AEErW738bcTztfpyEc9m2DDUvK6KiSAKm/nmqAJQ890NEoQ==</DP><DQ>nJ8LmPspwPCgcSSK+5Z3fdu7AJv8UCCooxD1p7ZNYY18yUaHeyf3YxJH0+jbzRUMlZyODrd0Wfk2kiTwantDWQ==</DQ><InverseQ>MkVtPWZ24C9xW7pKboTq+aTClHKcxI9VtRXS+xbqwA0IIA3LtwASjN6/sJj1yhCDtLcQjDDERHqs4Urokr2w2A==</InverseQ><D>ie2uzuncdnt1bMvcaNsViLlIpvBT9SKICqGDUossGWNkVMR5vv8JF99B35arlwOPHuCQA/MRvNAdgtpmf/8S24kPsHJBcBJOYdAiqegtYzOvW8MuPOTd9FX4tK1CPnVDK3doaHmn5mF0KkNWq8eN4kAqZ2Alql8/BoZ4hrNs0hE=</D></RSAKeyValue>";
//    private static final String serverAddr = "http://192.168.1.4/JeezWeb/JeezPMSPotralService.asmx";
//    private static final String serverAddr = "http://8bb9df23.ngrok.io/JeezWeb/JeezPMSPotralService.asmx";
    
    
    private static final KeyPair keyPair = JeezUtil.getRSAKeyPair(rsaPublicKey, rsaPrivateKey);

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public JeezServiceTest(String testName) {
        super(testName);
    }

    public void testGetUsersInfo() throws Exception {

        String res = JeezStub.getUsersInfo("63", "2", "0", "zmwy", keyPair, serverAddr);
        System.out.println(res);
        JeezResult result = JaxbXMLUtil.convertToJavaBean(res, JeezResult.class);
        List<JeezUser> users = result.getCustomers();
        for (JeezUser user : users) {
            res = JeezStub.getRoomInfo(user.getId(), "zmwy", keyPair, serverAddr);
            System.out.println(res);
            System.out.println();
            System.out.println();
            result = JaxbXMLUtil.convertToJavaBean(res, JeezResult.class);
            List<JeezHouse> houses = result.getHouses();

            /*for (JeezHouse house : houses) {
                String s = "(SELECT rr.f_id from t_group_building gb\n" +
                        "INNER JOIN t_building b on b.t_group_building_f_id = gb.f_id\n" +
                        "INNER JOIN t_real_room rr on rr.t_building_f_id = b.f_id\n" +
                        " where gb.f_id = 148096 and b.f_name = '"+ house.getBuildingName()+"' and f_num_tail = '"+house.getSimpleHouseNumber()+"')";
                System.out.println("INSERT INTO `t_realroom_software_mapper` VALUES (uuid_nextval('t_realroom_software_mapper'), "+s+", '"+ house.getId() +"', '"+user.getId()+"', now(), NULL, NULL, NULL, NULL, NULL, 0);");
            }*/
        }
    }

    public void testGetEncryptionKey() throws Exception {
        String result = JeezStub.getEncryptionKey(keyPair, serverAddr);
        System.out.println(result);
    }

    public void testGetRoomInfo() throws Exception {
        String res = JeezStub.getRoomInfo("3530", "zmwy", keyPair, serverAddr);
        System.out.println(res);
        JeezResult result = JaxbXMLUtil.convertToJavaBean(res, JeezResult.class);
        System.out.println(JSON.toJSONString(result));
    }
//#64432 3381
    public void testGetPayBill() throws Exception {
        String xml = JeezStub.getPayBill("12368", "3530", "zmwy", keyPair, serverAddr);
        System.out.println(xml);
        JeezFeeEntity entity = JaxbXMLUtil.convertToJavaBean(xml, JeezFeeEntity.class);
        System.out.println(JSON.toJSONString(entity));
    }

    public void testPay() throws Exception {
        String xml = JeezStub.getPayBill("64747", "3702", "zmwy", keyPair, serverAddr);
        JeezFeeEntity entity = JaxbXMLUtil.convertToJavaBean(xml, JeezFeeEntity.class);
        System.out.println(JSON.toJSONString(entity));

        boolean success = JeezStub.payByBills("64747", "3702", "zmwy", UUID.randomUUID().toString(), entity, keyPair, serverAddr);
        System.out.println(success);
    }
    public void testPushRepair() throws Exception {
        String content = "在4月初，我家楼下住户通过物业告知，主卧室顶渗水，物业安排按装管道的小李来检查维修，他给太阳能热水管保压测试，泄压不严重，但楼下继续渗水。物业又安排他和做防水的人一起，堵住主卧室卫生间的下水管放水，第二天发现楼下卫生间也渗水。我认为:一是水管道漏水；二是卫生间防水有问题。请物业尽快维修，已于4月下旬、5月初、5月中旬，多次找物业，到目前为止没有维修。我申明，我家入住后，不再维修，一切费用和后果概不负责。现通过微信再次申明。";
        String content2 = "[天燃气维修]老婆光明您JOJOlz咯了6464646464mjmjmjmjwjwjw jjjjjjjjjjjjj张";
        String content3 = "我家楼下住户通过物业我家楼下住户通过物业我家楼下住户通过物业我家楼下住户通过物业我家楼下住户通过物业我家楼下住户通过物业";
        String xml = JeezStub.pushRepair(serverAddr,"64625", "2016-12-29 19:09:00", content3, "张三", "18607170879","zmwy", "3587",null, null, null, keyPair);

        JeezResult entity = JaxbXMLUtil.convertToJavaBean(xml, JeezResult.class);
        System.out.println(JSON.toJSONString(entity));
    }

    public void testGetRepairDetail() throws Exception {
        String xml = JeezStub.getRepairDetail(serverAddr,"64624", "FW000038", "zmwy", "3588", keyPair);

        System.out.println(xml);
        JeezResult entity = JaxbXMLUtil.convertToJavaBean(xml, JeezResult.class);
        System.out.println(JSON.toJSONString(entity));
    }
}
