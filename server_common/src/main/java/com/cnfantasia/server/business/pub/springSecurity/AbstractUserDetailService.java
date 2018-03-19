package com.cnfantasia.server.business.pub.springSecurity;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 描述: 从数据库读取用户信息，包括密码、角色、状态等信息
 * 
 */

@Service
public abstract class AbstractUserDetailService implements UserDetailsService {
	/**
   * 装配UserDetails对象
   */
  public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException, DataAccessException {
    return doLoadUserByUsername(account);
  }
	
  public abstract UserDetails doLoadUserByUsername(String account) throws UsernameNotFoundException, DataAccessException;
	
}