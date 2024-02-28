<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>

	window.onload = function(){
        //btnRemove btnModify
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
       	const btnRemove = document.querySelector('.btnRemove');
		if(btnRemove != null){
			btnRemove.onclick = () => {
				if(confirm('정말 삭제 하시겠습니까?')){
					return true;
				}else{
					return false;
				}
			}
		}
		
		// 댓글 작성 취소 btnCancel
		const btnCancel = document.getElementsByClassName('btnCancel')[0];
		
		btnCancel.onclick = function(e){
			e.preventDefault();
			document.frmComment.reset();
			//document.querySelector('.commentForm textarea[name="content"]').value = '';
		}
		
		// 댓글 삭제 remove 
		const remove = document.querySelectorAll('.remove');
		remove.forEach((item) => {
			item.addEventListener('click', function(event){
				const result = confirm('정말 삭제 하시겠습니까?');
				if(!result){
					event.preventDefault();
				}
				return result;
			});
		});
		
		// 댓글 수정 modify
		const modify = document.querySelectorAll('.modify');
		modify.forEach((item)=> {
			item.onclick = function(e){
				e.preventDefault();
				//alert('수정!');
				if(this.innerText == '수정'){
					this.innerText == '수정완료';
					const textarea = this.parentElement.previousElementSibling;
					textarea.readOnly = false;
					textarea.style.background = 'white';
					textarea.focus();
				}else{
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
	}

</script>
<%@ include file="./_header.jsp" %>
        <main id="board">
            <section class="view">
                
                <table border="0">
                    <caption>글보기</caption>
                    <tr>
                        <th>제목</th>
                        <td><input type="text" name="title" value="${article.title}" readonly/></td>
                    </tr>
                    <c:if test="${article.file > 0}">
	                    <tr>
	                        <th>파일</th>
	                        <td>
			                	<c:forEach var="file" items="${article.fileDTOs}">
			                	<p><a href="/jboard2/fileDownload.do?fno=${file.fno}">${file.oName}</a>&nbsp;<span>${file.download}</span>회 다운로드</p>
			                	</c:forEach>
			                </td>
	                    </tr>
                    </c:if>
                    <tr>
                        <th>내용</th>
                        <td>
                            <textarea name="content" readonly>${article.content}</textarea>
                        </td>
                    </tr>                    
                </table>
                <div>
                <c:if test="${article.writer == sessUser.uid}">                 
                    <a href="./delete.do?no=${article.no}" class="btn btnRemove">삭제</a>
                    <a href="./modify.do?no=${article.no}" class="btn btnModify">수정</a>
                </c:if>  
                    <a href="./list.do?pg=${pg}" class="btn btnList">목록</a>
                </div>

                <!-- 댓글목록 -->
                <section class="commentList">
                    <h3>댓글목록</h3>
                    
                    <c:forEach var="comment" items="${comments}">
                    	<article class="comment">
                    		<span class="nick">${comment.nick}</span>
	                        <span class="date">${comment.rdate}</span>
	                        <p class="content">${comment.content}</p> 
	                        <c:if test="${comment.writer == sessUser.uid}">
		                    	<div>
		                           	<a href="/jboard2/comment.do?parent=${comment.parent}&no=${comment.no}" class="remove">삭제</a>
		                           	<a href="#" class="modify">수정</a>
		                       	</div>
	                    	</c:if>
                    	</article>

                    </c:forEach>
                    <c:if test="${empty comments}">
        				<p class="empty">등록된 댓글이 없습니다.</p>
    				</c:if>            
                </section>

                <!-- 댓글쓰기 -->
                <section class="commentForm">
                    <h3>댓글쓰기</h3>
                    <form action="/jboard2/comment.do" name="frmComment" method="post">
                    <input type="hidden" name="parent" value="${no}" />
    				<input type="hidden" name="uid" value="${sessUser.uid}" />
                        <textarea name="content"></textarea>
                        <div>
                            <a href="#" class="btn btnCancel">취소</a>
                            <input type="submit" value="작성완료" class="btn btnComplete"/>
                        </div>
                    </form>
                </section>

            </section>
        </main>
<%@ include file="./_footer.jsp" %>