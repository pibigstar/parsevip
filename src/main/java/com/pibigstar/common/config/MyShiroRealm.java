package com.pibigstar.common.config;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.pibigstar.system.domain.SystemPermission;
import com.pibigstar.system.domain.SystemRole;
import com.pibigstar.system.domain.SystemUser;
import com.pibigstar.system.service.SystemUserService;

public class MyShiroRealm extends AuthorizingRealm{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SystemUserService userService;
	/**
	 * 此函数在身份认证后也被调用
	 * 为当前登录的用户赋予角色和权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.info("权限配置--->MyShiroRealm.doGetAuthorizationInfo");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		SystemUser user = (SystemUser) principals.getPrimaryPrincipal();
		List<SystemRole> roles = user.getRoleList();
		for (SystemRole systemRole : roles) {
			authorizationInfo.addRole(systemRole.getRole());
			List<SystemPermission> permissions = systemRole.getPermissions();
			for (SystemPermission systemPermission : permissions) {
				authorizationInfo.addStringPermission(systemPermission.getPermission());
			}
		}
		
		return authorizationInfo;
	}
	/**
	 * 验证身份信息  
	 * subject.login(token)调用后会调用此函数
	 * 也就是说验证用户输入的账号和密码是否正确
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		logger.info("身份验证--->MyShiroRealm.doGetAuthorizationInfo");
		String username = (String) token.getPrincipal();
		
		SystemUser user = userService.findUserByUsername(username);
		if (user==null) {
			return null;
		}
		
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,user.getPassword(),user.getUsername());
		
		return authenticationInfo;
	}

}
