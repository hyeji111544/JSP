<%@page import="kr.co.jboard1.dto.ArticleDTO"%>
<%@page import="kr.co.jboard1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String content = request.getParameter("content");
	String no = request.getParameter("no");
	String parent = request.getParameter("parent");
	
	
	ArticleDTO comment = new ArticleDTO();
	comment.setContent(content);
	comment.setNo(no);
	
	ArticleDAO dao = ArticleDAO.getInstance();
	dao.updateComment(comment);
	
	response.sendRedirect("/jboard1/view.jsp?no="+parent);
%>