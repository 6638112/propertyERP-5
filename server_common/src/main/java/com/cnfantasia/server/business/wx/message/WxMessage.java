package com.cnfantasia.server.business.wx.message;

import java.util.List;

/**
 * @ClassName: WxMessage.
 * @Date: 2017-06-28 14:37
 * @Auther: kangduo
 * @Description: ()
 */
public class WxMessage {
    private String touser;
    private String msgtype;
    private WxNews news;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public WxNews getNews() {
        return news;
    }

    public void setNews(WxNews news) {
        this.news = news;
    }

    public static class WxNews {
        private List<WxArticle> articles;

        public List<WxArticle> getArticles() {
            return articles;
        }

        public void setArticles(List<WxArticle> articles) {
            this.articles = articles;
        }
    }
}
