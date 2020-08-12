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
		<%-- <예약확인> 이미지 --%>
		<img src="img/hwakin.jpg"  />
		
		<%-- 서블릿 컨트롤러로 예약 확인 요청을 한다! --%>
		<form action="CarReserveConfirmController.do" method="post">
			<table width=400 border=0>
				<tr height="60" align="center">
					<td align="center" width="200">전화번호 입력:</td>
					<td align="center" width="200"><input type="text" name="memberphone"/></td>
				</tr>
				<tr height="60" align="center">
					<td align="center" width="200">비밀번호 입력:</td>
					<td align="center" width="200"><input type="text" name="memberpass"/></td>
				</tr>
				<tr height="60" align="center">
					<td align="center" colspan="2"><input type="submit" value="예약확인"/></td>
				</tr>								
			</table>
		</form>	
	
	
	</center>



</body>
</html>


