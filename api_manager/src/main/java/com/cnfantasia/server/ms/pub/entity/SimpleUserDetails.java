package com.cnfantasia.server.ms.pub.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 描述: 取代org.springframework.security.core.userdetails.User类
 * 
 * @version 1.00
 * 
 */

public class SimpleUserDetails implements Serializable, UserDetails {
	private static final long serialVersionUID = 1L;
	/**用户信息对象*/
  private SysUser sysUser;
  private final boolean accountNonExpired;
  private final boolean accountNonLocked;
  private final boolean credentialsNonExpired;
  private final boolean enabled;
  
  /**用户所拥有的权限集合*/
  Collection<GrantedAuthority> authorities=null;
  
  /**兼容处理*/
  private String userName;
  private String password;
  /**
   * 兼容处理,保证后续用户名密码校验通过
   * @param sysUser
   * @param userName 用户名
   * @param password 密码
   */
  public SimpleUserDetails(SysUser sysUser,String userName,String password){
    this(sysUser,true,true,true,true);
    this.userName = userName;
    this.password = password;
  }
  
  
  public SysUser getSysUser() {
    return sysUser;
  }
  
  public void setSysUser(SysUser sysUser) {
    this.sysUser = sysUser;
  }

  public SimpleUserDetails(SysUser sysUser,boolean accountNonExpired,boolean accountNonLocked,boolean credentialsNonExpired,boolean enabled){
    this.sysUser=sysUser;//用户信息对象
    this.accountNonExpired=accountNonExpired;
    this.accountNonLocked=accountNonLocked;
    this.credentialsNonExpired=credentialsNonExpired;
    this.enabled=enabled;
  }
  public SimpleUserDetails(SysUser sysUser){
    this(sysUser,true,true,true,true);
  }

  @Override
  public Collection<GrantedAuthority> getAuthorities() {
    if(authorities==null){
      authorities=new ArrayList<GrantedAuthority>();
      List<BigInteger> list = sysUser.getRoleIds(); //授权信息
      int size = list.size();
      GrantedAuthority auth = null; //权限对象
      for (int i = 0; i < size; i++){
        auth = new SimpleGrantedAuthority(list.get(i).toString());
        authorities.add(auth); //重新装配 List to Collection;
       }
    }
    return authorities;
  }
	@Override
  public String getUsername() {
    return userName; 
  }
	@Override
  public String getPassword() {
    return password; 
  }
	
	@Override
  public boolean isAccountNonExpired() {
    return accountNonExpired;
  }
	@Override
  public boolean isAccountNonLocked() {
    return accountNonLocked;
  }
	@Override
  public boolean isCredentialsNonExpired() {
    return credentialsNonExpired;
  }
	@Override
  public boolean isEnabled() {
    return enabled;
  }

	@Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((sysUser == null) ? 0 : sysUser.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    SimpleUserDetails other = (SimpleUserDetails) obj;
    if (sysUser == null) {
      if (other.sysUser != null)
        return false;
    } else if (!sysUser.equals(other.sysUser))
      return false;
    return true;
  }

  
}
