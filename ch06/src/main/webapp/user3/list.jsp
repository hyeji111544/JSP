<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user3::list</title>
	</head>
	<body>
		<h3>User3 목록</h3>
		<a href="../1.Jdbc.jsp">처음으로</a>
		<a href="./register.jsp">등록하기</a>
		
			<table border="1">
				<tr>
					<th>아이디</th>
					<th>이름</th>
					<th>생일</th>
					<th>휴대폰</th>
					<th>주소</th>
					<th>관리</th>
				</tr>
				<tr>
					<td>A101</td>
					<td>김유신</td>
					<td>1968-05-09</td>
					<td>010-1234-1111</td>
					<td>경남 김해시</td>
					<td>
						<a href="#">수정</a>
						<a href="#">삭제</a>
					</td>
				</tr>
			
			</table>
		
	</body>
</html>