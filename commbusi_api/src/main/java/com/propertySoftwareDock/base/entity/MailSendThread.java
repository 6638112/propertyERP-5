package com.propertySoftwareDock.base.entity;

import com.cnfantasia.server.business.commonBusiness.util.MailUtils;

/**
 * @ClassName: MailThread
 * @Date: 2016-11-30 13:07
 * @Auther: kangduo
 * @Description:(发邮件线程)
 */
public class MailSendThread extends Thread {
    private String mailContent;
    private String nofityEmails;
    private String title;


    public MailSendThread(String title, String mailContent, String nofityEmails) {
        this.title = title;
        this.mailContent = mailContent;
        this.nofityEmails = nofityEmails;
    }
    public void run() {
        MailUtils.sendMail(title, mailContent, nofityEmails);
    }
}
