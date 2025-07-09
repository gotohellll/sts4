<%@ page contentType="text/html; charset=utf-8"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>글 목록</title>
</head>
<body>
	<h1> 직원 목록 </h1>

	<table border="1">
			<tr>
				<th bgcolor="orange" width="100">사원번호</th>
				<th bgcolor="orange" width="200">사원명</th>
				<th bgcolor="orange" width="150">업무</th>
				<th bgcolor="orange" width="150">입사일</th>
				<th bgcolor="orange" width="100">월급</th>
			</tr>
			<c:forEach items="${empList }" var="emp">
				<tr>
					<td>${emp.empno}</td>
					<td>${emp.ename}</td>
					<td>${emp.job}</td>
					<td>${emp.hiredate}</td>
					<td>${emp.sal}</td>
				</tr>
			
		</table>
		<hr/>
		<a href="insertEmployee">직원 등록</a>

</body>
</html>