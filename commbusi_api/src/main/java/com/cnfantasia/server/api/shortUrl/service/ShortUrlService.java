package com.cnfantasia.server.api.shortUrl.service;

import com.cnfantasia.server.api.cache.constant.RedisCachePrefix;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.UUIDGenerater;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.shortUrl.dao.IShortUrlBaseDao;
import com.cnfantasia.server.domainbase.shortUrl.entity.ShortUrl;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * @ClassName: ShortUrlService
 * @Date: 2017-04-10 16:17
 * @Auther: kangduo
 * @Description: (短链接service)
 */
public class ShortUrlService implements IShortUrlService{

    //一周过期时间
    private static final int TIMEOUT_SECOND = 60 * 60 * 24 * 7;

    private IShortUrlBaseDao shortUrlBaseDao;
    public void setShortUrlBaseDao(IShortUrlBaseDao shortUrlBaseDao) {
        this.shortUrlBaseDao = shortUrlBaseDao;
    }

    private IUuidManager uuidManager;
    public void setUuidManager(IUuidManager uuidManager) {
        this.uuidManager = uuidManager;
    }

    /**
     * 根据短链接查长链接，并放至redis
     * @param shortUrl 短链接
     * @return 长链接
     */
    @Override
    public String getRealUrl(String shortUrl) {
        String key = RedisCachePrefix.Short_Url + shortUrl;
        String realUrl = RedisCacheHandler.get(key);
        if (realUrl == null) {
            ShortUrl url = new ShortUrl();
            url.setShortUrl(shortUrl);
            PageModel pageModel = new PageModel(0, 1);
            List<ShortUrl> shortUrls = shortUrlBaseDao.selectShortUrlByCondition(MapConverter.toMap(url), pageModel, false);
            if (!DataUtil.isEmpty(shortUrls)) {
                realUrl = shortUrls.get(0).getRealUrl();
            }
        }
        if (realUrl != null) {
            RedisCacheHandler.set(key, realUrl, TIMEOUT_SECOND);
        }
        return realUrl;
    }

    /**
     * 根据长链接创建短链接，如果已存在，直接返回，并存至reids
     * 本方法自己一个事务，避免因外部调用处异常导致数据存储不了
     * @param realUrl 长链接
     * @return 短链接
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String createShortUrl(String realUrl) {
        //如果链接已存在，直接返回
        ShortUrl url = new ShortUrl();
        url.setRealUrl(realUrl);
        PageModel pageModel = new PageModel(0, 1);
        List<ShortUrl> shortUrls = shortUrlBaseDao.selectShortUrlByCondition(MapConverter.toMap(url), pageModel, false);
        if (!DataUtil.isEmpty(shortUrls)) {
            return shortUrls.get(0).getShortUrl();
        }
        //不存在该链接，则重新生成一个
        //先校验短链接合法性
        url = new ShortUrl();
        url.setShortUrl(UUIDGenerater.generateShortUuid());
        while (shortUrlBaseDao.selectShortUrlCount(MapConverter.toMap(url), false) > 0) {
            url.setShortUrl(UUIDGenerater.generateShortUuid());
        }
        BigInteger id = uuidManager.getNextUuidBigInteger(SEQConstants.t_short_url);
        url.setId(id);
        url.setRealUrl(realUrl);
        shortUrlBaseDao.insertShortUrl(url);

        //生成短链接后存入redis
        String key = RedisCachePrefix.Short_Url + url.getShortUrl();
        RedisCacheHandler.set(key, url.getRealUrl(), TIMEOUT_SECOND);
        return url.getShortUrl();
    }
}
