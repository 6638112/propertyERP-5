/**   
* Filename:    MicroblogContentEntity.java   
* @version:    1.0  
* Create at:   2014年7月22日 上午8:48:21   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月22日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.microblog.entity;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.domainbase.microblogContent.entity.MicroblogContent;
import com.cnfantasia.server.domainbase.microblogPic.entity.MicroblogPic;
import com.cnfantasia.server.domainbase.microblogType.entity.MicroblogType;

/**
 * Filename:    MicroblogContentEntity.java
 * @version:    1.0.0
 * Create at:   2014年7月22日 上午8:48:21
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月22日       shiyl             1.0             1.0 Version
 */
public class MicroblogContentEntity extends MicroblogContent{
	private static final long serialVersionUID = 1L;
	/**微博图片列表*/
	private List<MicroblogPic> microblogPicList;
	public List<MicroblogPic> getMicroblogPicList() {
		return microblogPicList;
	}
	public void setMicroblogPicList(List<MicroblogPic> microblogPicList) {
		this.microblogPicList = microblogPicList;
	}

	/**微博所属类别*/
	private MicroblogType microblogType;
	public MicroblogType getMicroblogType() {
		return microblogType;
	}
	public void setMicroblogType(MicroblogType microblogType) {
		this.microblogType = microblogType;
	}
	@Override
	public BigInteger gettMicroblogTypeFId() {
		if(microblogType==null){ return null; }
		return microblogType.getId();
	}
	@Override
	public void settMicroblogTypeFId(BigInteger tMicroblogTypeFId) {
		if(microblogType==null){microblogType = new MicroblogType();}
		microblogType.setId(tMicroblogTypeFId);
	}
	
	/**用户信息*/
	private UserSimpleEntity userInfo;
	public UserSimpleEntity getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserSimpleEntity userInfo) {
		this.userInfo = userInfo;
	}
	@Override
	public BigInteger getUserId() {
		if(userInfo==null){
			return null;
		}
		return userInfo.getId();
	}
	@Override
	public void setUserId(BigInteger user) {
		if(userInfo==null){
			userInfo = new UserSimpleEntity();
		}
		userInfo.setId(user);
	}
	
	/**是否被当前用户点赞,1是0否*/
	private Integer extIsFavour;
	public Integer getExtIsFavour() {
		return extIsFavour;
	}
	public void setExtIsFavour(Integer extIsFavour) {
		this.extIsFavour = extIsFavour;
	}

	/**点赞总数*/
	private Integer extFavourCount;
	public Integer getExtFavourCount() {
		return extFavourCount;
	}
	public void setExtFavourCount(Integer extFavourCount) {
		this.extFavourCount = extFavourCount;
	}
	
	/**评论总数*/
	private Integer extCommentCount;
	public Integer getExtCommentCount() {
		return extCommentCount;
	}
	public void setExtCommentCount(Integer extCommentCount) {
		this.extCommentCount = extCommentCount;
	}
	
}
