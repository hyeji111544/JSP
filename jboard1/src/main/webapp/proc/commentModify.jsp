<%@page import="kr.co.jboard1.dto.ArticleDTO"%>
<%@page import="kr.co.jboard1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String content = request.getParameter("content");
	String parent = request.getParameter("parent");
	String no = request.getParameter("no");
	
	int noInt = Integer.parseInt(no);
	int parentInt = Integer.parseInt(parent);
	
	ArticleDTO article = new ArticleDTO();
	article.setContent(content);
	article.setParent(parentInt);
	article.setNo(noInt);
	
	ArticleDAO dao = ArticleDAO.getInstance();
	dao.modifyComment(article);
	
	response.sendRedirect("/jboard1/view.jsp?no="+parent);
%>