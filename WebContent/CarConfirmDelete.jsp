<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
    
<%-- JSTL 태그 사용을 위한 지시자 추가 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"  %>

 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
		<center>
			 <h1>차량 예약정보 삭제</h1>
			
			<%--비밀번호 입력후 삭제 요청을 함 --%>
			<form action="CarConfirmDeleteController.do" method="post">
				<table width="400">
				
					<c:set var="result" value="${requestScope.result}"  />
				
					<c:if test="${result == null}">
						<c:set var="result" value="${1}" />
					</c:if>
					
					<c:if test="${result == 0 }">
						<script type="text/javascript">
							alert("비밀번호가 틀립니다.");
						</script>
					</c:if>
				
						
					<tr align="center" >
						<td align="center">
							<input type="hidden" name="orderid" value="${param.orderid}">
							비밀번호 입력 :
							<input type="text" name="memberpass">
							&nbsp;&nbsp;&nbsp;
							<input type="submit" value="삭제하기">
						</td>
					</tr>
				</table>				
			
			</form>
		</center>
			
			
</body>
</html>











