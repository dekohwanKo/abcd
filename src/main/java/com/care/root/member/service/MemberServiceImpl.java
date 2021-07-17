package com.care.root.member.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.root.member.dto.MemberDTO;
import com.care.root.mybatis.member.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired MemberMapper mapper;
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public int user_check(HttpServletRequest request) {
		MemberDTO dto = mapper.user_check(request.getParameter("id"));
		if(dto != null) {
			//if(request.getParameter("pw").equals(dto.getPw())) {
			if(request.getParameter("pw").equals(dto.getPw()) ||
					encoder.matches(request.getParameter("pw"), dto.getPw())) {
				return 0;
			}
		}
		return 1;
	}
	public void memberInfo(Model model) {
		model.addAttribute("memberList", mapper.memberInfo());
	}
	public void info(Model model, String userId) {
		model.addAttribute("info", mapper.user_check(userId));
	}
	public int register(MemberDTO dto) {
		System.out.println("암호화 전 : "+dto.getPw());
		String securePw = encoder.encode(dto.getPw());
		System.out.println("암호화 후 : "+securePw);
		System.out.println(securePw.length());
		dto.setsessionId("nan");
	dto.setLimitTime(new Date(System.currentTimeMillis()));
		dto.setPw(securePw);
		try {
			return mapper.register(dto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public void keepLogin(String sessionId, Date limitDate, String id) {
		Map<String, Object> dates = new HashMap<String, Object>();
		dates.put("sessionId", sessionId);
		dates.put("limitDate", limitDate);
		dates.put("id", id);
		mapper.keepLogin(dates);
	}
	public MemberDTO getUserSessionId(String sessionId) {
		return mapper.getUserSessionId(sessionId);
	}
}














