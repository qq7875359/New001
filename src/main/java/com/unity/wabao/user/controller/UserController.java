package com.unity.wabao.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.unity.wabao.user.mobilecheck.MoblieCheckUtil;
import com.unity.wabao.user.service.UserService;

@Controller
@RequestMapping()
public class UserController {
	
	
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String TEL		 = "tel";
	
	
	@Autowired
	private UserService userService;
	/**
	 * 
	 * 账号密码登录接口
	 * @return
	 */
	
	@RequestMapping(value="/index")
	public String index(){
		return "userLogin/upLogin";
	}
	@RequestMapping(value="uplogin",method=RequestMethod.GET)
	public String getUsername(){
		System.out.println("~~~~~~~~~~~");
		String password = userService.getUsername(1);
		System.out.println(password);
		return "index";
	}
	@RequestMapping("login")
	public String login(String username,String password,Model m) {

		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(username,password);
		try {
			subject.login(token);
			m.addAttribute("msg", "成功");
		} catch (AuthenticationException e) {
			// TODO: handle exception
			m.addAttribute("msg", "失败");
			return "login";
		}
		return "login";
	}
	/**
	 * 短信验证接口
	 * 
	 * @return
	 */
	@RequestMapping(value="tellogin",method=RequestMethod.POST)
	 public String tellogin(HttpServletRequest request,HttpServletResponse response){
		String tel = request.getParameter(TEL);
		MoblieCheckUtil.getRequest2(tel);
		return "index";
	}
	
	
	
}

