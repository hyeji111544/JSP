<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<% 

	request.setCharacterEncoding("UTF-8");
	String uid = request.getParameter("uid");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ajax::user2</title>
		<script>
			window.onload = function(){
				
			}
		</script>
	</head>
	<body>
		<h3>user2 수정</h3>
		<a href="./list.jsp">목록이동</a>
			<form action="#">
			<table border="1">
				<tr>
					<td>아이디</td>
					<td><input type="text" readonly name="uid"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td><input type="date" name="birth"></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="age"></td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<input type="submit" name="submit" value="수정하기">
					</td>
				</tr>
			</table>
		</form>
		
	
	</body>
</html>