package com.unity.wabao.user.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.unity.wabao.user.service.UserService;

public class UserRealm extends AuthorizingRealm{

	@Autowired
	UserService userService;
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken t=(UsernamePasswordToken) token;
		String username=token.getPrincipal().toString();
		String password=userService.getpassword(username);
		System.out.println(username);
		System.out.println(password);
		//String salt="wabao";
		SimpleAuthenticationInfo a=new SimpleAuthenticationInfo(username,password,getName());
		return a;
	}

}
