package com.care.root.member.controller;

import java.sql.Date;
import java.util.Calendar;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.root.member.dto.MemberDTO;
import com.care.root.member.service.MemberService;
import com.care.root.member.session_name.MemberSessionName;

@Controller
@RequestMapping("member")
public class MemberController implements MemberSessionName{
	@Autowired MemberService ms;
	@PostMapping("user_check")
	public String user_check(HttpServletRequest request,
							RedirectAttributes rs) {
		int result = ms.user_check(request);
		if(result == 0) {
			rs.addAttribute("id",request.getParameter("id"));
			rs.addAttribute("autoLogin", request.getParameter("autoLogin"));
			return "redirect:successLogin";
		}
		return "redirect:login";
	}
	@RequestMapping("successLogin")
	public String successLogin(@RequestParam("id") String id,
		@RequestParam(value="autoLogin", required = false) String autoLogin,
								HttpSession session,
								HttpServletResponse response) {
		System.out.println("autoLogin : "+autoLogin);
		session.setAttribute(LOGIN, id);
		if(autoLogin != null) {
			int limittime = 60*60*24*90; //90일
			Cookie cookie = new Cookie("loginCookie", session.getId());
			cookie.setPath("/");
			cookie.setMaxAge(limittime);
			response.addCookie(cookie);
			
			//long ex = System.currentTimeMillis() + (limittime * 1000);
			//Date date = new Date(ex);
			Calendar cal = Calendar.getInstance();
			cal.setTime( new java.util.Date() );
			cal.add(Calendar.MONTH, 3);
			
			Date date = new Date(cal.getTimeInMillis());
			
			ms.keepLogin(session.getId(), date, id);
			
		}
		return "member/successLogin";
	}
	
	
	@GetMapping("/login")
	public String login() {
		return "member/login";
	}
	@GetMapping("logout")
	public String logout(HttpSession session,
			HttpServletResponse response,
	@CookieValue(value="loginCookie", required=false) Cookie loginCookie) {
		if(session.getAttribute(LOGIN) != null) {
			if(loginCookie != null) {
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				ms.keepLogin("nan", new Date(System.currentTimeMillis()),
						(String)session.getAttribute(LOGIN));
			}
			session.invalidate();
		}
		
		return "redirect:/index";
	}
	@GetMapping("memberInfo")
	public String memberInfo(Model model) {
		ms.memberInfo(model);
		return "member/memberInfo";
	}
	@RequestMapping("info")
	public String info(Model model,
						@RequestParam("id") String userId) {
		ms.info(model, userId);
		return "member/info";
	}
	@GetMapping("register_form")
	public String register_form() {
		return "member/register";
	}
	@PostMapping("register")
	public String register(MemberDTO dto) {
		int result = ms.register(dto);
		if(result == 1) {
			return "redirect:login";
		}
		return "redirect:register_form";
	}
}


















