<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
		
		const commentList = document.getElementsByClassName('commentList')[0];
		const btnSubmit = document.commentForm.submit;
		const form = document.commentForm;
		
		//댓글 삭제(동적 이벤트 처리)
		
		document.addEventListener('click', function(e){
			
			
			if(e.target.classList == 'remove'){
				e.preventDefault();
				//해당 삭제에서 가장 가까운 부모 article 문서 객체 생성
				const article = e.target.closest('article');
				
				//사용자 정의 속성 data-no 참조
				const no 	 = e.target.dataset.no;
				const parent = e.target.dataset.parent;
				console.log('no : ' + no);
				console.log('parent : ' + parent);
				
				fetch('/jboard2/comment.do?type=remove&parent='+parent+'&no='+no)
					.then((resp)=> resp.json())
					.then((data)=>{
						
						if(data.result > 0){
							alert('삭제되었습니다.');
							
							//삭제 동적 처리
							article.remove();
						}
						
					})
					.catch((err)=>{
						console.log(err);
					});
			}
		});

		
		//댓글 입력
		btnSubmit.onclick = function(e){
			e.preventDefault();
			
			const parent = form.parent.value;
			const writer = form.writer.value;
			const content = form.content.value;
			
			const jsonData = {
				"parent": parent,	
				"writer": writer,	
				"content": content	
			};
			
			console.log(jsonData);
			
			fetch('/jboard2/comment.do', {
				method: 'POST',
				headers: {"Content-type": "application/json"},
				body: JSON.stringify(jsonData)
			})
			.then((resp)=> resp.json())
			.then((data)=> {
				console.log(data);
				
				
					const today = new Date();
					const year = today.getFullYear() % 100;
					let month = today.getMonth() +1;
					if(month<10){
						month="0"+month;
					}
					const date = today.getDate();
					
					let hours = today.getHours();
					let minutes = today.getMinutes();
					
					// 시간 값이 한 자리 수일 경우 앞에 0을 붙이기
					if (hours < 10) {
					    hours = "0" + hours;
					}
					if (minutes < 10) {
					    minutes = "0" + minutes;
					}
					
					//태그 문자열 생성(JSP 표현언어와 Javascript 템플릿 문자열의 간섭으로 \로 이스케이프 처리)
					let commentArticle = `<article class="comment">
								        		<span class="nick">${sessUser.nick}</span>
								                <span class="date">\${year}-\${month}-\${date} \${hours}:\${minutes}</span>
								                <p class="content">\${content}</p> 
								                <div>
							                		<a href="#" data-no="\${data.pk}" data-parent="\${data.parent}" class="remove">삭제</a>
							                       	<a href="#" class="modify">수정</a>
							                   	</div>
								        	</article>`;
					console.log(commentArticle);
				
				// 태그 문자열 삽입
				commentList.insertAdjacentHTML('beforeend', commentArticle);
				
				// textarea 초기화
				form.content.value = '';
				
				var emptyComments = document.querySelector('.empty');
		        if (emptyComments) {
		            emptyComments.style.display = 'none';
		        }
				
			})
			.catch((err)=> {
				console.log(err);
			});
		}
		
		
		// 댓글 작성 취소 btnCancel
		const btnCancel = document.getElementsByClassName('btnCancel')[0];
		
		btnCancel.onclick = function(e){
			e.preventDefault();
			document.frmComment.reset();
			//document.querySelector('.commentForm textarea[name="content"]').value = '';
		}
		
		/* 댓글 삭제 remove 
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
		*/
		
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
                    <a href="./modify.do?no=${article.no}&pg=${pg}" class="btn btnModify">수정</a>
                </c:if>  
                    <a href="./list.do?pg=${pg}" class="btn btnList">목록</a>
                </div>

                <!-- 댓글목록 -->
                <section class="commentList">
                    <h3>댓글목록</h3>
                    
                    <c:forEach var="articleDTO" items="${comments}">
                    	<article class="comment">
                    		<span class="nick">${articleDTO.nick}</span>
	                        <span class="date">${fn:substring(articleDTO.rdate, 2, 16)}</span>
	                        <p class="content">${articleDTO.content}</p> 
	                        <c:if test="${articleDTO.writer == sessUser.uid}">
		                    	<div>
		                           	<a href="#" data-no="${articleDTO.no}" data-parent="${articleDTO.parent}" class="remove">삭제</a>
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
                    <form  name="commentForm" action="#" method="post">
                    <input type="hidden" name="parent" value="${no}">
            		<input type="hidden" name="writer" value="${sessionScope.sessUser.uid}">
                        <textarea name="content" placeholder="댓글 입력"></textarea>
                        <div>
                            <a href="#" class="btn btnCancel">취소</a>
                            <input type="submit" name="submit" value="작성완료" class="btn btnComplete"/>
                        </div>
                    </form>
                </section>

            </section>
        </main>
<%@ include file="./_footer.jsp" %>