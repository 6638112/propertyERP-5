package com.cnfantasia.jfq.share.entity;

import java.util.List;

import com.cnfantasia.server.domainbase.shareActive.entity.ShareActive;
import com.cnfantasia.server.domainbase.shareActiveDetail.entity.ShareActiveDetail;
/**
 * 分享活动实体
 * @author wenfq
 *
 */
public class ShareActiveEntity extends ShareActive {
	List<ShareActiveDetail> shareActiveDetail;

	public List<ShareActiveDetail> getShareActiveDetail() {
		return shareActiveDetail;
	}

	public void setShareActiveDetail(List<ShareActiveDetail> shareActiveDetail) {
		this.shareActiveDetail = shareActiveDetail;
	}
}
