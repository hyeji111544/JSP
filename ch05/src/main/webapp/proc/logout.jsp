<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 현재 클라이언트 세션 삭제
	session.invalidate();


	// 자동 로그인 쿠키 삭제
	Cookie cookie = new Cookie("cid", null);
	cookie.setMaxAge(0);
	cookie.setPath("/ch05"); //로그인이랑 똑같이 쿠키 경로 설정해서 삭제 해야함.
	response.addCookie(cookie);

	// 로그인 화면 이동
	response.sendRedirect("../2.Session.jsp?logout=success");

%>