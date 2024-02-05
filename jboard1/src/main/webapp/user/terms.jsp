<%@page import="ko.co.jboard1.db.SQL"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="ko.co.jboard1.dto.TermsDTO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//String host = "jdbc:mysql://127.0.0.1:3306/jboard";
	//String user = "hyeji";
	//String pass = "abc1234";
	
	TermsDTO dto = null;
	
	try{
		//Class.forName("com.mysql.cj.jdbc.Driver");
		//Connection conn = DriverManager.getConnection(host, user, pass);
		
		Context initCtx = new InitialContext();
		Context ctx= (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)ctx.lookup("jdbc/jboard");
		
		Connection conn = ds.getConnection();
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(SQL.TERMS);
		
		if(rs.next()){
			dto = new TermsDTO();
			dto.setTerms(rs.getString(1));
			dto.setPrivacy(rs.getString(2));

		}
		
		rs.close();
		stmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
%>


<%@ include file="./_header.jsp"%>
<main>
	<section class="terms">
		<table>
			<caption>사이트 이용약관</caption>
			<tr>
				<td><textarea readonly> 
					<%= dto.getTerms() %>
					 </textarea>
					<p>
						<label><input type="checkbox" name="agree1">동의합니다.</label>
					</p></td>
			</tr>
		</table>
		<table>
			<caption>개인정보 취급방침</caption>
			<tr>
				<td>
					<textarea readonly> 
						<%= dto.getPrivacy() %>
						</textarea>
					<p>
						<label><input type="checkbox" name="agree2">동의합니다.</label>
					</p></td>
			</tr>
		</table>
		<div>
			<a href="/jboard1/user/login.jsp" class="btnCancel">취소</a> 
			<a href="/jboard1/user/register.jsp" class="btnNext">다음</a>
		</div>

	</section>
</main>
<%@ include file="./_footer.jsp"%>