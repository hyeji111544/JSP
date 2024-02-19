<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String fno = request.getParameter("fno");
	
	try{
		Context ctx =(Context) new InitialContext().lookup("java:comp/env"); //JNDI 기본 환경 이름
		DataSource ds = (DataSource) ctx.lookup("jdbc/studydb");
		Connection conn = ds.getConnection();
		
		PreparedStatement psmt = conn.prepareStatement("DELETE FROM `FileTest` WHERE `fno`= ?");
		psmt.setString(1, fno);
		
		psmt.executeUpdate();
		
		psmt.close();
		conn.close();
			
	}catch(Exception e){
		e.printStackTrace();
	}
	
	response.sendRedirect("../2.fileDownloadTest.jsp");

%>