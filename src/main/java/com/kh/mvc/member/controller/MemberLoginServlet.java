package com.kh.mvc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.member.model.dto.Member;
import com.kh.mvc.member.model.service.MemberService;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		// 2. 사용자입력값 처리
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		String saveId= request.getParameter("saveId");
		System.out.println("memberId = " + memberId);
		System.out.println("password = " + password);
		System.out.println("saveId= " + saveId);// "on" | null
		
		// 3. 업무로직 : 로그인여부 판단
		Member member = memberService.findById(memberId);
		System.out.println("member@MemberLoginServlet = " + member);
		
		HttpSession session = request.getSession(true); // 세션이 존재하지 않으면, 새로 생성해서 반환. true생략가능
		System.out.println(session.getId());
		// 로그인 성공
		if(member != null && password.equals(member.getPassword())) {
			session.setAttribute("loginMember", member);
			 
			
//			//saveId 처리
			Cookie cookie = new Cookie("saveId", memberId);
//			cookie.setPath(request.getContextPath());
//			
//			cookie.setMaxAge(7 * 24 *60 * 60);//초단위의 일주일 = 7일로 설정
//			response.addCookie(cookie);//응답메세지에 Set-cookie할목으로 전송
			
			//saveId를 사용하는 경우
			if(saveId != null) {
				cookie.setMaxAge(7 * 24 *60 * 60);//초단위의 일주일 = 7일로 설정
			}
			//saveId를 사용하지 않는 경우
			else {
			
				response.addCookie(cookie);//응답메세지에 Set-cookie할목으로 전송
			}
			response.addCookie(cookie);
		}
		// 로그인 실패 (아이디 존재하지 않는 경우 || 비밀번호를 틀린 경우)
		else {
			session.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		
		
		// 4. 응답 처리 : 로그인후 url변경을 위해 리다이렉트처리
		// 응답 302 redirect 전송.
		// 브라우져에게 location으로 재요청을 명령.
		response.sendRedirect(request.getContextPath() + "/"); // /mvc/
		
	}

}
