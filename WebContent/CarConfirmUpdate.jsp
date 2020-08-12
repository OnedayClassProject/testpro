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
		<h1>차량 예약 정보 수정 페이지</h1>
	
		<%--수정할 예약정보 입력후  수정 버튼을 클릭했을때.. 
		    CarConfirmUpdateProcController서블릿으로 수정요청을함 --%>
		<form action="CarConfirmUpdateProcController.do?orderid=${requestScope.cbean.orderid}" method="post">
			<table width="1000" border="0" align="center">
				<tr align="center">
					<td rowspan="7" width="600">
						<img src="img/${requestScope.cbean.carimg}" width="500"/>
					</td>
					<td align="center" width="200">대여기간</td>
					<td align="center" width="200">
						<select name="carreserveday">
							<option value="1">1일</option>
							<option value="2">2일</option>
							<option value="3">3일</option>
							<option value="4">4일</option>
							<option value="5">5일</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center">대여일</td>
					<td align="center">
						<input type="date" name="carbegindate" value="${cbean.carbegindate}">
					</td>
				</tr>
				<tr>
					<td align="center" width="200">대여수량</td>
					<td align="center" width="200">
						<select name="carqty">
							<option value="1">1대</option>
							<option value="2">2대</option>
							<option value="3">3대</option>
							<option value="4">4대</option>
							<option value="5">5대</option>
						</select>
					</td>	
				</tr>
				<tr>
					<td align="center" width="200">보험적용</td>
					<td align="center" width="200">
						<select name="carins">
							<option value="1">적용(1일 만원)</option>
							<option value="0">미적용</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center" width="200">무선 WIFI</td>
					<td align="center" width="200">
						<select name="carwifi">
							<option value="1">적용(1일 만원)</option>
							<option value="0">미적용</option>
						</select>
					</td>
				</tr>				
				<tr>
					<td align="center" width="200">베이비시트</td>
					<td align="center" width="200">
						<select name="carbabyseat">
							<option value="1">적용(1일 만원)</option>
							<option value="0">미적용</option>
						</select>
					</td>
				</tr>	
				<tr>
					<td colspan="2" align="center">
						비밀번호 입력 : <input type="text" name="memberpass">
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="submit" value="수정하기">
					</td>
				</tr>		
			</table>
		</form>	
	</center>



</body>
</html>


