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
		<%--자주 렌트 되는 차량  이미지 3개를 화면에 나타내고 --%>
		<table width="1000">
			<tr>
				<td align="center" width="333">
					<a href="CarMain.jsp?center=CarEvent.jsp">
						<img src="img/lfsonata.jpg" width="280">
					</a>
				</td>
				<td align="center" width="333">
					<a href="CarMain.jsp?center=CarEvent.jsp">
						<img src="img/k5.jpg" width="280">
					</a>
				</td>				
				<td align="center" width="333">
					<a href="CarMain.jsp?center=CarEvent.jsp">
						<img src="img/avante.jpg" width="280">
					</a>
				</td>				
			</tr>
		</table>
		
		<%-- 소형, 중형, 대형을 기준으로 검색 하기 위해 설정 --%>
		<h1><img src="img/ccs.jpg"></h1>
		
			<%-- 소형 대형 중형 중 하나로 검색 요청 하기 위함 --%>
			<form action="CarcategoryController.do" method="post"> 
			<table width="400">
				<tr align="center">
					<td width="100">차량 종류</td>
					<td width="100" height="50">
						<select name="carcategory">
							<option value="Small">소형</option>
							<option value="Mid">중형</option>
							<option value="Big">대형</option>
						</select>
					</td>
					<td align="center">
						<input type="submit" value="검색">
					</td>
					<td align="center">
						<input type="button" value="전체검색" 
						      onclick="location.href='CarListController.do'">
					</td>
				</tr>
			</table>
		</form>
	</center>



</body>
</html>