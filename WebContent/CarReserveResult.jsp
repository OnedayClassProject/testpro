<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%-- JSTL의 core라이브러리에 속한 태그 사용을 위한 설정 --%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<center>
			<img alt="" src="img/naeyeok.jpg">
			<br><br>
			<table width="1000" border="1" align="center">
				<tr align="center">
					<td align="center" width="150">차량이미지</td>
					<td align="center" width="100">차량명</td>
					<td align="center" width="100">대여일</td>
					<td align="center" width="50">대여기간</td>
					<td align="center" width="100">차량금액</td>
					<td align="center" width="70">보험여부</td>
					<td align="center" width="70">무선WIFI</td>
					<td align="center" width="70">네비게이션</td>
					<td align="center" width="70">베이비시트</td>
					<td align="center" width="100">예약수정</td>
					<td align="center" width="100">예약삭제</td>
				</tr>				
			<%-- 예약한 정보들을 아래에.. JSTL태그인? c:foreach반복문을 이용하여 Vector의 정보를 출력 --%>
			<c:forEach var="bean" items="${requestScope.v}">
				<tr align="center" height="60">
					<td align="center" width="150">
						<img src="img/${bean.carimg}" width="140" height="90"/>
					</td>
					<td align="center" width="100">${bean.carname}</td>
					<td align="center" width="100">${bean.carbegindate}</td>
					<td align="center" width="50">${bean.carreserveday}</td>
					<td align="center" width="100">${bean.carprice}</td>
					<td align="center" width="70">
						<c:if test="${bean.carins == 1}">보험가입</c:if>
						<c:if test="${bean.carins == 0}">보험미가입</c:if>
					</td>
					<td align="center" width="70">
						<c:if test="${bean.carwifi == 1}">WIFI대여</c:if>
						<c:if test="${bean.carwifi == 0}">WIFI미대여</c:if>
					</td>					
					<td align="center" width="70">
						<c:if test="${bean.carnave == 1}">네비대여</c:if>
						<c:if test="${bean.carnave == 0}">네비미대여</c:if>
					</td>
					<td align="center" width="70">
						<c:if test="${bean.carbabyseat == 1}">대여</c:if>
						<c:if test="${bean.carbabyseat == 0}">미대여</c:if>
					</td>	
					<td align="center" width="100">
						<button onclick="location.href='CarConfirmUpdateController.do?orderid=${bean.orderid}&carimg=${bean.carimg}'" >
						     수정
						</button>
					</td>
					<td align="center" width="100">
						<%-- 예약 삭제 화면(비밀번호 입력하는 곳)으로 이동하는데.. 예약아이디 또한 전달 한다. --%>
						<button onclick="location.href='CarMain.jsp?orderid=${bean.orderid}&center=CarConfirmDelete.jsp'" >
						     삭제
						</button>
					</td>																
				</tr>
			</c:forEach>						
			</table>
		</center>
</body>
</html>









