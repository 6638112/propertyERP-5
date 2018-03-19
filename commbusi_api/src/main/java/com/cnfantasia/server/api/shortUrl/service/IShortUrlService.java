package com.cnfantasia.server.api.shortUrl.service;

/**
 * @ClassName: IShortUrlService
 * @Date: 2017-04-10 16:04
 * @Auther: kangduo
 * @Description: (短链接服务类)
 */
public interface IShortUrlService {

    public String getRealUrl(String shortUrl);

    public String createShortUrl(String realUrl);

}
