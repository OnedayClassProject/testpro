<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
    
<%-- JSTL �±� ����� ���� ������ �߰� --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"  %>

 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
		<center>
			 <h1>���� �������� ����</h1>
			
			<%--��й�ȣ �Է��� ���� ��û�� �� --%>
			<form action="CarConfirmDeleteController.do" method="post">
				<table width="400">
				
					<c:set var="result" value="${requestScope.result}"  />
				
					<c:if test="${result == null}">
						<c:set var="result" value="${1}" />
					</c:if>
					
					<c:if test="${result == 0 }">
						<script type="text/javascript">
							alert("��й�ȣ�� Ʋ���ϴ�.");
						</script>
					</c:if>
				
						
					<tr align="center" >
						<td align="center">
							<input type="hidden" name="orderid" value="${param.orderid}">
							��й�ȣ �Է� :
							<input type="text" name="memberpass">
							&nbsp;&nbsp;&nbsp;
							<input type="submit" value="�����ϱ�">
						</td>
					</tr>
				</table>				
			
			</form>
		</center>
			
			
</body>
</html>











