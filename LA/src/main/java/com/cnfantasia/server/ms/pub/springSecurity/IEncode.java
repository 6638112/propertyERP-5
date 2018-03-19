package com.cnfantasia.server.ms.pub.springSecurity;

/**
 * 描述:加密处理接口定义
 * 
 * @version 1.00
 * @author syl
 * 
 */
public interface IEncode {
  /**
   * 加密密码
   * @param password 明文密码
   * @param saltsrc 盐值
   * @return
   */
  public String encodePassword(String rawPass, Object salt);
  
}
