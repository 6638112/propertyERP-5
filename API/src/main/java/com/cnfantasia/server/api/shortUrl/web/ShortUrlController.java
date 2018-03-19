package com.cnfantasia.server.api.shortUrl.web;

import com.cnfantasia.server.api.shortUrl.service.IShortUrlService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * @ClassName: ShortUrlController
 * @Date: 2017-04-10 16:07
 * @Auther: kangduo
 * @Description: (短链接跳转控制类)
 */
@Controller
public class ShortUrlController {

//    @Resource
//    private IShortUrlService shortUrlService;

    @RequestMapping(value = "{shortUrl:[a-zA-Z0-9]{6}}", method = RequestMethod.GET)
    public String turn2RealUrl(@PathVariable String shortUrl) {
//        String realUrl = shortUrlService.getRealUrl(shortUrl);
        String realUrl = "http://www.baidu.com";
        if (realUrl == null) {
            return "redirect:/";
        }
        return "redirect:" + realUrl;
    }

}
