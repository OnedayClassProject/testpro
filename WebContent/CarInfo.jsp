<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<center>
		<img src="img/cis.jpg" border="0">
		
		<%-- 자동차 렌트시.. 옵션선택하기 페이지로 요청 하기 위해 <form>태그 작성 --%>
		<form action="CarMain.jsp?center=CarOption.jsp" method="post">
			<table width="1000" border="0">
				<tr align="center">
					<td rowspan="6" width="600">
						<img src="img/${requestScope.bean.carimg}" width="500" border="0">
					</td>
					<td align="center" width="200" >차량이름</td>
					<td align="center" width="200">${requestScope.bean.carname}</td>
				</tr>
				<tr>
					<td align="center" width="200">대여 수량</td>
					<td align="center" width="200">
						<select name="carqty">
							<option value="1">1대
							<option value="2">2대
							<option value="3">3대
							<option value="4">4대
							<option value="5">5대
						</select>
					</td>				
				</tr>
				<tr>
					<td align="center" width="200">차량 분류</td>
					<td align="center" width="200">
						${requestScope.bean.carcategory}
					</td>
				</tr>
				<tr>
					<td align="center" width="200">대여금액</td>
					<td align="center" width="200">
						${requestScope.bean.carprice}
					</td>
				</tr>
				<tr>
					<td align="center" width="200">제조회사</td>
					<td align="center" width="200">
						${requestScope.bean.carcompany}
					</td>
				</tr>	
				<tr>
					<td align="center" width="200">
						
						<input type="button" value="이전" 
						       onclick="location.href='CarListController.do'">
					</td>
					<td align="center" width="200">
						
						<%--옵션을 추가로 선택 할수 있는 페이지로 이동시.. 
						       차번호, 차이미지명, 차한대당 렌트 가격 또한? request내장객체 영역에 저장 하여 전달 --%>
						<input type="hidden" name="carno" value="${requestScope.bean.carno}">
						<input type="hidden" name="carimg" value="${bean.carimg}">
						<input type="hidden" name="carprice" value="${bean.carprice}">
						
						<input type="submit" value="옵션선택하기">
			
					</td>
				</tr>							
				
			</table>
		</form>
		<p>
			<b>차량 정보 상세 보기</b>
			${requestScope.bean.carinfo}
		</p>
	</center>
</body>

</html>