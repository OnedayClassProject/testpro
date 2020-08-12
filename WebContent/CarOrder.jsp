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
			<%-- <결제 하기> 이미지 --%>
			<img src="img/haki.jpg">
			
			
			<%-- 결제하기 버튼을 눌렀을때..  컨트롤러 요청시.. 예약정를 전달함 --%>
			<form action="CarOrderController.do" method="post">
				<p>
					<font size="13" color="blue">차량 렌트 비용 : ￦ ${requestScope.totalreserve}원 </font>
				</p>
				<p>
					<font size="13" color="blue">렌트 옵션 비용 : ￦ ${requestScope.totaloption}원 </font>
				</p>
				<p>
					<font size="13" color="blue">총 비용 : ￦ ${totalreserve + totaloption}원 </font>
				</p>				
				<%--실제 예약정보 데이터들을 CarOrderController서블릿으로  넘겨 주기 위해  hidden태그에 설정 --%>
				<input type="hidden" name="carno" value="${requestScope.cbean.carno}">
				<input type="hidden" name="carqty" value="${requestScope.cbean.carqty}">
				<input type="hidden" name="carreserveday" value="${requestScope.cbean.carreserveday}">
				<input type="hidden" name="carins" value="${requestScope.cbean.carins}">
				<input type="hidden" name="carwifi" value="${requestScope.cbean.carwifi}" >
				<input type="hidden" name="carbabyseat" value="${requestScope.cbean.carbabyseat}">
				<input type="hidden" name="carbegindate" value="${requestScope.cbean.carbegindate}">
			   	<input type="hidden" name="carnave" value="${requestScope.cbean.carnave}">
			   
			   
				<p>
					비회원 전화번호 예약: <input type="text" name="memberphone"> &nbsp;&nbsp;&nbsp;
					비밀번호 : <input type="password" name="memberpass">
					<input type="submit" value="결제하기">
				</p>
			</form>
		</center>




</body>
</html>