<%@page import="java.util.List"%>
<%@page import="kr.co.jboard1.dto.ArticleDTO"%>
<%@page import="kr.co.jboard1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp"%>

<%
	request.setCharacterEncoding("UTF-8");
	String no = request.getParameter("no");
	
	ArticleDAO dao = ArticleDAO.getInstance();
	
	// 글 조회
	ArticleDTO article = dao.selectArticle(no);
	
	// 글 조회 카운트 업데이트
	dao.updateHitCount(no);
	
	// 댓글 조회
	List<ArticleDTO> comments = dao.selectComments(no);
%>
<script>

	window.onload = function(){
		
		// 원글 수정
		const btnModify = document.querySelector('.btnModify');
		if(btnModify != null){
			btnModify.onclick = () => {
				if(confirm('수정 하시겠습니까?')){
					return true;
				}else{
					return false;
				}
			}
		}
		
		// 원글 삭제
		const btnDelete = document.querySelector('.btnDelete');
		if(btnDelete != null){
			btnDelete.onclick = () =>{
				if(confirm('정말 삭제 하시겠습니까?')){
					return true;
				}else{
					return false;
				}
			}
		}
		
		//댓글 작성 취소
		const btnCancel = document.getElementsByClassName('btnCancel')[0];
		
		btnCancel.onclick = function(e){
			e.preventDefault();
			document.frmComment.reset(); //폼 초기화
		}
		
		//댓글 삭제
		const del = document.querySelectorAll('.del');
		del.forEach((item) => {
		    item.addEventListener('click', function(event) {
		        const result = confirm('정말 삭제 하시겠습니까?');
		        if (!result) {
		            event.preventDefault(); // 기본 동작(링크 이동) 방지
		        }
		        return result; // 확인을 누르면 true 반환하여 삭제 진행, 취소를 누르면 false 반환하여 삭제 취소
		    });
		});
		
		// 댓글 수정
		const mod = document.querySelectorAll('.mod');
		mod.forEach((item)=> {
			item.onclick = function(e){
				e.preventDefault();
				// alert('수정!');
				
				//console.log(this.parentElement.previousElementSibling);
				
				if(this.innerText == '수정'){
					this.innerText = '수정완료';
					const textarea = this.parentElement.previousElementSibling;
					textarea.readOnly = false;
					textarea.style.background = 'white';
					textarea.focus();
				}else{
					//alert('수정완료 클릭');
					const form = this.closest('form');
					form.submit();
					//수정완료 해제
					this.innerText = '수정';
					const textarea = this.parentElement.previousElementSibling;
					textarea.readOnly = true;
					textarea.style.background = 'transparent';
				}
			}
		});
		
		/**
		document.addEventListener('DOMContentLoaded', function() {
            const mods = document.querySelectorAll('.mod'); // 모든 수정 버튼을 선택합니다.
            mods.forEach(function(mod) { // 모든 수정 버튼에 대해 반복합니다.
                mod.addEventListener('click', function() { // 각 수정 버튼에 클릭 이벤트 리스너를 추가합니다.
                    const textarea = this.parentElement.parentElement.querySelector('textarea[name="comment"]');
                    if (textarea.hasAttribute('readonly')) {
                        textarea.removeAttribute('readonly');
                        this.innerText = '수정완료';
                    } else {
                        textarea.setAttribute('readonly', 'readonly');
                        this.innerText = '수정';
                    }
                });
            });
        });
		**/
		
	}

</script>

<main>
	<section class="view">
		<h3>글보기</h3>
		<table>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" value="<%= article.getTitle() %>" readonly /></td>
			</tr>
			<% if(article.getFile() > 0) { %>
			<tr>
				<td>첨부파일</td>
				<td><a href="#">2020년 상반기 매출자료.xls</a> 
				<span>7회 다운로드</span></td>
			</tr>
			<% } %>
			<tr>
				<td>내용</td>
				<td><textarea name="content" readonly><%= article.getContent() %></textarea></td>
			</tr>
		</table>
		<div>
			<% if(article.getWriter().equals(sessUser.getUid())) {%>
			<a href="/jboard1/proc/deleteProc.jsp?no=<%= article.getNo() %>" class="btnDelete">삭제</a>
			<a href="/jboard1/modify.jsp?no=<%= article.getNo() %>" class="btnModify">수정</a>
			<%
			session.setAttribute("title", article.getTitle());
		    session.setAttribute("content", article.getContent());
			%>
			<% } %>
			<a href="./list.jsp" class="btnList">목록</a>
		</div>

		<!-- 댓글리스트 -->
		<section class="commentList">
			<h3>댓글목록</h3>
			
			<%for(ArticleDTO comment : comments) {%>
			<form action="/jboard1/proc/commentUpdate.jsp" method="post">
				<article class="comment">
					<span>
						<span><%= comment.getNick() %></span>
						<span><%= comment.getRdate().substring(2, 10) %></span>
					</span>
					<textarea name="content" readonly><%= comment.getContent() %></textarea>
					
					<% if(comment.getWriter().equals(sessUser.getUid())) {%>
					<div>
						<a href="/jboard1/proc/commentDelete.jsp?parent=<%= comment.getParent() %>&no=<%= comment.getNo() %>"class="del">삭제</a>
						<a href="#"class="mod">수정</a>
					</div>
					<%
				    session.setAttribute("comment", article.getContent());
					%>
					<% } %>
					
				</article>
			</form>
			<% } %>
			
			<%if(comments.isEmpty()){%>
			<p class="empty">등록된 댓글이 없습니다.</p>
			<% } %>
		</section>

		<!-- 댓글입력폼 -->
		<section class="commentForm">
			<h3>댓글쓰기</h3>
			<form action="/jboard1/proc/commentProc.jsp" name="frmComment" method="post">
				<input type="hidden" name="parent" value="<%= no %>">
				<input type="hidden" name="writer" value="<%= sessUser.getUid() %>">
				<textarea name="content"></textarea>
				<div>
					<a href="#" class="btnCancel">취소</a> 
					<input type="submit" class="btnWrite" value="작성완료" />
				</div>
			</form>
		</section>
	</section>
</main>
<%@ include file="./_footer.jsp"%>