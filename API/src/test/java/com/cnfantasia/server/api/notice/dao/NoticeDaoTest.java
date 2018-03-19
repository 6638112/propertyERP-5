/**
 * 
 */
package com.cnfantasia.server.api.notice.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.BaseTest;
import com.cnfantasia.server.api.notice.entity.NoticeBean;
import com.cnfantasia.server.business.pub.entity.PageModel;

/**
 * 类说明：
 *
 * @author hunter
 * @since 2014年6月4日下午3:15:33
 */
public class NoticeDaoTest extends BaseTest{
	
	INoticeDao noticeDao;
	
//	@Test
	public void testSelectAllNoticeByUserid() {
		Integer page = 1;
		Integer pageNum = 6;
		PageModel pageModel = new PageModel((page-1)*pageNum, pageNum);
		noticeDao = (INoticeDao)ctx.getBean("noticeDao");
		List<NoticeBean> noticeBeans = noticeDao.selectAllNoticeByUserid(pageModel, BigInteger.valueOf(34));
		for(NoticeBean bean:noticeBeans){
			System.out.println(bean.getTitle() + " "+bean.getContent() +" "+bean.getNoticeDate());
		}
	}
	
//	@Test
	public void selectAddresssByMessageId(){
		noticeDao = (INoticeDao)ctx.getBean("noticeDao");
		List<NoticeBean> noticeBeans = noticeDao.selectAddresssByMessageId(new BigInteger("40028"));
		System.out.println(noticeBeans.size());
	}

}
