<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%-- JSTL태그 사용 을 위한 선언 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"  %>    
       
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- 중앙 영역 공간은 사용자로 부터 계속해서 메뉴를 클릭했을때 화면이 바뀌는 부분이기에..
		 메뉴를 클릭했을때의 요청 주소를 받아 저장
	  --%>
	<%
		/* String center = request.getParameter("center"); */

		//처음으로 CarMain.jsp페이지를 요청하면 당연히 param.center -> 받아 올수 없기 때문에.. 조건주기
//  	if(center == null){
// 			center = "Center.jsp"; //중앙 화면영역의 페이지를 지정
// 		}	
	%>
		<c:set  var="center"  value="${param.center}"/>
		
		
		<c:out value="${center}"/>
		
		
		<c:if test="${center==null}">
			
			<c:set var="center" value="Center.jsp" />
		
		</c:if>

	<center>
		<table width="1000" height="700">
			<tr align="center">
				<td>
					<jsp:include page="Top.jsp"/>  <%--홈페이지의 상단 --%>
				</td>
			</tr>
			<tr>
				<td height="500">
					<jsp:include page="${center}"/> <%--상단에서 각메뉴를 눌렀을때의 중앙 영역 --%>
				</td>				
			</tr>
			<tr>
				<td>
					<jsp:include page="Bootom.jsp"/> <%--홈페이지의 하단 --%>
				</td>
			</tr>
		</table>
	</center>


</body>
</html>







