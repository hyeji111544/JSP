<%@page import="kr.co.jboard1.dto.ArticleDTO"%>
<%@page import="kr.co.jboard1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String title   = request.getParameter("title");
	String content = request.getParameter("content");
	String file    = request.getParameter("file");
	String writer  = request.getParameter("writer");
	String no	   = request.getParameter("no");
	String regip   = request.getRemoteAddr();

	
	int noInt = Integer.parseInt(no);
	
	ArticleDTO article = new ArticleDTO();
	article.setTitle(title);
	article.setContent(content);
	article.setWriter(writer);
	article.setRegip(regip);
	article.setNo(noInt);
	
	ArticleDAO dao = ArticleDAO.getInstance();
	dao.updatetArticle(article);
	
	response.sendRedirect("/jboard1/view.jsp?no="+no);	
%>