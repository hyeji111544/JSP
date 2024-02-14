<%@page import="kr.co.jboard1.dao.ArticleDAO"%>
<%@page import="kr.co.jboard1.dto.ArticleDTO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
	String no = request.getParameter("no");
	String pg = request.getParameter("pg");
	String searchType = request.getParameter("searchType");
	String keyword = request.getParameter("keyword");
    String title = (String) session.getAttribute("title");
    String content = (String) session.getAttribute("content");
    
	System.out.println("modPg: "+ pg);
    
    //수정글 조회
	ArticleDAO dao = ArticleDAO.getInstance();
	
	ArticleDTO article = dao.selectArticle(no);
	
	
	String params = "";
	if(searchType != null && keyword != null){
		params = "&searchType="+searchType+"&keyword="+keyword;
	}
%>
<main>
	<section class="modify">
		<h3>글수정</h3>
		<article>
			<form action="/jboard1/proc/modifyProc.jsp" method="post">
			
			<!-- 추가 전송을 위해 hidden 필드 사용 -->
            	<input type="hidden" name="no" value="<%= no %>">
            	<input type="hidden" name="pg" value="<%= pg %>">
            	<input type="hidden" name="params" value="<%= params %>">
				
				<table>
					<tr>
						<td>제목</td>
						<td><input type="text" name="title" value="<%= article.getTitle()%>"></td>
					</tr>
					<tr>
						<td>내용</td>
						<td><textarea name="content"><%= article.getContent()%></textarea></td>
					</tr>
					<tr>
						<td>첨부</td>
						<td><input type="file" name="file" /></td>
					</tr>
				</table>
				<div>
					<a href="/jboard1/view.jsp?no=<%= no +params%>&pg=<%= pg %>" class="btnCancel">취소</a> 
					<input type="submit" value="수정완료" class="btnWrite">
				</div>
			</form>
		</article>
	</section>
</main>
<%@ include file="./_footer.jsp"%>