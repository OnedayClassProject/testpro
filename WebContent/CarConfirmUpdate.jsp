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
		<h1>���� ���� ���� ���� ������</h1>
	
		<%--������ �������� �Է���  ���� ��ư�� Ŭ��������.. 
		    CarConfirmUpdateProcController�������� ������û���� --%>
		<form action="CarConfirmUpdateProcController.do?orderid=${requestScope.cbean.orderid}" method="post">
			<table width="1000" border="0" align="center">
				<tr align="center">
					<td rowspan="7" width="600">
						<img src="img/${requestScope.cbean.carimg}" width="500"/>
					</td>
					<td align="center" width="200">�뿩�Ⱓ</td>
					<td align="center" width="200">
						<select name="carreserveday">
							<option value="1">1��</option>
							<option value="2">2��</option>
							<option value="3">3��</option>
							<option value="4">4��</option>
							<option value="5">5��</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center">�뿩��</td>
					<td align="center">
						<input type="date" name="carbegindate" value="${cbean.carbegindate}">
					</td>
				</tr>
				<tr>
					<td align="center" width="200">�뿩����</td>
					<td align="center" width="200">
						<select name="carqty">
							<option value="1">1��</option>
							<option value="2">2��</option>
							<option value="3">3��</option>
							<option value="4">4��</option>
							<option value="5">5��</option>
						</select>
					</td>	
				</tr>
				<tr>
					<td align="center" width="200">��������</td>
					<td align="center" width="200">
						<select name="carins">
							<option value="1">����(1�� ����)</option>
							<option value="0">������</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center" width="200">���� WIFI</td>
					<td align="center" width="200">
						<select name="carwifi">
							<option value="1">����(1�� ����)</option>
							<option value="0">������</option>
						</select>
					</td>
				</tr>				
				<tr>
					<td align="center" width="200">���̺��Ʈ</td>
					<td align="center" width="200">
						<select name="carbabyseat">
							<option value="1">����(1�� ����)</option>
							<option value="0">������</option>
						</select>
					</td>
				</tr>	
				<tr>
					<td colspan="2" align="center">
						��й�ȣ �Է� : <input type="text" name="memberpass">
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="submit" value="�����ϱ�">
					</td>
				</tr>		
			</table>
		</form>	
	</center>



</body>
</html>


