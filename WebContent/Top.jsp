<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

	<style>
	    /* 정렬  오른쪽 밖여백 20 64 0 0    
	            글자체  Arial, Helvetica, sans-serif;
	            글자 크기 12px
	            단어 간격 5px
	    */
		#login{
			float: right;
			margin: 20px 64px 0 0;
			font-family: Arial,Helvetica,sans-serif;
			font-size: 12px;
			word-spacing: 5px;
		}
		
		#login a{
			/*하이퍼 링크 밑줄 없애기 , 글자색 #333 */
			text-decoration: none;
			color: #333;
		}
		
		#login a:HOVER {
			/*마우스 포인터를 a태그위로 올렸을때 글자색 변경*/
			color: #F90;
		}	
		
		#logo{
			float: left;
			width: 265px;
			margin: 60px 0 0 40px;
		}
	</style>
</head>
<body>

	<%-- 메인 로고이미지 를 누르면  CarMain.jsp메인페이지로 이동 --%>
 <div id="logo">
	<a href="CarMain.jsp">
		<img src="img/RENT.jpg" width="300" height="80">
	</a>
</div>
	
	<%-- 로그인 | 회원가입 링크  --%>
	<table width="1000" height="5">
		<tr>
			<td align="right" colspan="5">
			<% 
				//세션 값 얻기
				String id = (String)session.getAttribute("id");
			
				//세션영역에 값이 저장 되어 있지 않다면
				if(id == null){
			%>
				<div id="login">
					<a href="./MemberLogin.me">login</a> |
					<a href="./MemberJoin.me" >join</a>
				</div>	
			<%		
				}else{//세션영역에 값이 저장되어 있다면
			%>
				<div id="login">
					<%=id %>로그인중..
					<a href="./MemberLogout.me">logout</a>
				</div>
			<%		
				}
			%>
			</td>
		</tr>
	
	
	
	</table>
	
	
	
	
	<%--메뉴 만들기 --%>
	<table width="1000" background="img/aa.jpg" height="5">
		<tr>
			<td align="center" bgcolor="red">
				<a href="CarMain.jsp?center=CarReservation.jsp">
					<img src="img/bb.jpg"> <%--예약하기 메뉴 --%>
				</a>
			</td>
			<td align="center" bgcolor="red">
				<!-- 
					1. Top.jsp의 메뉴 이미지중에서 예약확인 메뉴를 눌렀을때
					   CarReserveConfirm.jsp호출하여...
					   예약확인 화면인 CarReserveConfirm.jsp화면이 중앙에 나오면...
					  이화면에서  사용자에게 전화번호와 비밀번호를 입력 받아..
					 CarReserveConfirmController서블릿에 .. 전달하는 서블릿을 만들자.
				 -->
			
				<a href="CarMain.jsp?center=CarReserveConfirm.jsp">
					<img src="img/cc.jpg"> <%--예약확인 메뉴 --%>
				</a>
			</td>
			<td align="center" bgcolor="red">
									 <%--자유게시판 컨트롤러(서블릿)요청 --%>
				<a href="CarMain.jsp?center=BoardListController.do">
					<img src="img/dd.jpg"> <%-- 자유게시판 --%>
				</a>
			</td>			
			<td align="center" bgcolor="red">
				<a href="CarMain.jsp?center=CarEvent.jsp">
					<img src="img/even.jpg"> <%--이벤트 정보 확인 메뉴 --%>
				</a>
			</td>	
			<td align="center" bgcolor="red">
									 <%--공지사항 게시판 컨트롤러(서블릿)요청 --%>
				<a href="CarMain.jsp?center=AdminBoardListController.do">
					<img src="img/ee.jpg"> <%-- 공지사항 게시판 --%>
				</a>
			</td>						
		</tr>
	</table>	
	
	
</body>
</html>






