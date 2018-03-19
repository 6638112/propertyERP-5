package com.cnfantasia.server.business.wx.message.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.business.wx.message.WxArticle;
import com.cnfantasia.server.business.wx.message.WxMessage;
import com.cnfantasia.server.business.wx.pay.util.HttpUtil;

/**
 * @ClassName: WxMessageUtil.
 * @Date: 2017-06-22 14:23
 * @Auther: kangduo
 * @Description: ()
 */
public class WxMessageUtil {
    private static Logger logger = LoggerFactory.getLogger(WxMessageUtil.class);

    private static final String MESSAGE_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";

    public static final String Text_Message_Template = "{\"touser\":\"OPENID\",\"msgtype\":\"text\",\"text\":{\"content\":\"content_template\"}}";

    /**
     * Send photoes and text message to user
     * @param articles
     * @param openId
     * @param accessToken
     */
    public static void sendArticleMessage(List<WxArticle> articles, String openId, String accessToken) {
        WxMessage.WxNews news = new WxMessage.WxNews();
        news.setArticles(articles);
        WxMessage message = new WxMessage();
        message.setNews(news);
        message.setTouser(openId);
        message.setMsgtype("news");
        logger.info("发送微信图文消息的内容为：" + JSON.toJSONString(message));
        String result = HttpUtil.postData(MESSAGE_SEND_URL + accessToken, JSON.toJSONString(message));
        logger.info("发送微信图文消息的结果为：" + result);
    }

    /**
     * send text message to user
     * @Auther wenfq 2017-07-29
     * @param openId
     * @param accessToken
     * @param content
     */
    public static void sendTextMessage(String openId, String accessToken, String content) {
        String postData = Text_Message_Template.replace("content_template", content);
        postData = postData.replace("OPENID", openId);
        logger.info("发送发送文本消息内容为：" + JSON.toJSONString(postData));
        String result = HttpUtil.postData(MESSAGE_SEND_URL + accessToken, postData);
        logger.info("发送发送文本消息的结果为：" + result);
    }

    public static void main(String[] args) {
        String openId = "oedx3uKXpKZpgCgC8SLVa_D7mCmI";
        String accessToken = "Icdo1_dUIXHxYbPjAtQIzIkGftql-BvB3WqK5F29iHUPFsLkBh-VP5Fm2Pgwspn-frRmccmS6UIYkqQQnqk3ES8bBjqACBXmlH_JbYCJvBoyHqhErDWG03z8eOe1Mao6DYBdCFANIL";
        String content = "请戳→→<a href='https://blog.prcode.org'>最帅的人博客</a>";
        WxMessageUtil.sendTextMessage(openId, accessToken, content);
    }
}
