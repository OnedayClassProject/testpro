<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--빨간 라인 부분 --%>
	<hr width="1000" color="red" size="3"/>
	
	<%--아래 로고 이미지 부분 --%>
	<a href="#">
		<img src="img/bo.jpg" width="500" height="50">
	</a>
	<font size="2">
		<b>
			<%--회사 소개 이미지 링크를 눌렀을때 회사 소개 페이지로 이동 --%>
			<a href="CarMain.jsp?center=Company.jsp">
				<img src="img/sodog.jpg">
			</a>
			<%--개인정보 취급방지 | 사이버 신문고 | 이용약관  | 인재 채용 --%>
			<a href="CarMain.jsp?center=info.jsp">
				<img src="img/info.jpg"> | 사이버 신문고 | 이용약관 | 인재채용
			</a>
		</b>
		<br><br>
		<%--글씨 작게 --%>
		<small>
			(주) SM렌탈 사업자 등록번호 214-98754-9874 통신 판매업 신고 번호 : 제 2020-부산-07호 <br>
			
			부산시 부산진구 부전동 강남 빌딩  2층 21호 <br>
			
			대표전화 : 051-1234-1234<br>
			
			FAX : 051-1234-123 
		</small>
	</font>
	
	
</body>
</html>





