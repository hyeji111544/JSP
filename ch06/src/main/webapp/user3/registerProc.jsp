<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String uid = request.getParameter("uid");
	String name = request.getParameter("name");
	String birth = request.getParameter("birth");
	String HP = request.getParameter("HP");
	String addr = request.getParameter("addr");
	
	System.out.println("uid: " + uid);
	System.out.println("name: " + name);
	System.out.println("birth: " + birth);
	System.out.println("hp: " + HP);
	System.out.println("addr: " + addr);
	
	String host= "jdbc:mysql://127.0.0.1:3306/studydb";
	String user= "hyeji";
	String pass= "abc1234";
	
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(host, user, pass);
		
		String sql = "INSERT INTO `user3` VALUES(?, ?, ?, ?, ?)";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, uid);
		psmt.setString(2, name);
		psmt.setString(3, birth);
		psmt.setString(4, HP);
		psmt.setString(5, addr);
		
		
		psmt.executeUpdate();
		
		conn.close();
		psmt.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	response.sendRedirect("./list.jsp");
%>