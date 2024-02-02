<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%

	//데이터베이스 작업
	String host= "jdbc:mysql://127.0.0.1:3306/studydb";
	String user= "hyeji";
	String pass= "abc1234";
	
	request.setCharacterEncoding("UTF-8");
	String uid = request.getParameter("uid");
	
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(host, user, pass);
		
		PreparedStatement psmt = conn.prepareStatement("DELETE FROM `User1` WHERE `uid`=?");
		psmt.setString(1, uid);
		
		psmt.executeUpdate();
		
		psmt.close();
		conn.close();
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	
	//목록 이동
	response.sendRedirect("./list.jsp");
%>