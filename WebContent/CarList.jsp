<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%-- JSTL 라이브러에 속한 태그들을 사용 하기 위해 선언 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<img  src="img/cis.jpg"/>  <%--<차량 정보보기> 이미지 --%>
		
		<%-- 소형 대형 중형 중 하나로 검색 요청 하기 위함 --%>
		<form action="CarcategoryController.do" method="post"> 
			<%-- 검색한 전체 차량 정보 출력 --%>
			<table width="1000" border="0" height="470">
				
				<c:set var="j" value="0"/>
				
				<%--CarListController에서 넘겨 받은  request영역 내부에 저장되어 있는 Vector사이즈 반복 --%>
				<c:forEach var="carlistBean"  items="${requestScope.Vector }"  >
						
						<%--4열 씩 자동차 이미지, 내용 정보를 출력해 주기 위해 4번마다 tr태그를 열어준다 --%>
						<c:if test="${j%4 == 0}">
							<tr align="center">
						</c:if>
								<td>
									<%--선택하는 자동차를 렌트 하기 위해 컨트롤러로 요청시 차량 번호 전달 --%>
									<a href="CarInfoController.do?carno=${carlistBean.carno}">
										<img src="img/${carlistBean.carimg}" width="220" height="180">
									</a>
									<br>
									차량명 : ${carlistBean.carname}
									<br>
									대여금액 : ${carlistBean.carprice}
								</td>
				
						<%-- j변수의 값을 1씩 증가 --%>
						<c:set  var="j" value="${j+1}" />
				</c:forEach>
							</tr>
							<tr height="70">
								<td colspan="4"  align="center" >
									종류별 차량 검색 :
									<select name="carcategory">
										<option value="Small">소형
										<option value="Mid">중형
										<option value="Big">대형
									</select> 
									<input type="submit" value="검색">
								</td>
							</tr>
			</table>
		</form>
	</center>
</body>
</html>






