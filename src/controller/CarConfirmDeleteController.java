package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.CarConfirmBean;
import db.CarDAO;


//CarConfirmDelete.jsp화면에서 예약 삭제를 위해 삭제 비밀번호를 입력후..
//삭제 요청을 하여 전달 받는 서블릿 
@WebServlet("/CarConfirmDeleteController.do")
public class CarConfirmDeleteController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 클라이언트의 요청값 얻기 
		//비밀번호, 예약id
		int orderid = Integer.parseInt(request.getParameter("orderid"));
		String memberpass = request.getParameter("memberpass");
		
				
		//2.요청한값을 이용해 응답할 값 마련
		//예약 정보를 삭제 하기 위해 CarDAO객체의 메소드 호출시  예약 아이디와  입력한 비밀번호를 전달 하영 DELETE작업함
		CarDAO cdao = new CarDAO();
		
		//DELETE삭제에 성공 했다면 1을 반환 받고 실패 하면 0을 반환 받는다.
		int result = cdao.carOrderDelete(orderid,memberpass);
		
		if(result != 0){ //삭제에 성공 했다면?
			//응답할 데이터 유형 설정 
			response.setContentType("text/html;charset=utf-8");
			//PrintWriter객체 얻기
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('렌트카 예약 정보를 삭제 했습니다.');");
			out.print("location.href='CarListController.do'");
			out.print("</script>");
			
//			RequestDispatcher dis = 
//					request.getRequestDispatcher("CarListController.do");
//			dis.forward(request, response);
			
			
		}else{//삭제에 실패 했다면? -> 비밀번호 틀림
			
			request.setAttribute("result", result);
			
			//삭제요청을 하는 CarConfirmDelete.jsp중앙화면을 재요청 하여 이동
			//이동시~ 위 request에 담긴 result값을 전달 하여 메세지를 나타냄
			RequestDispatcher dis = 
					request.getRequestDispatcher("CarMain.jsp?center=CarConfirmDelete.jsp");
			dis.forward(request, response);
		}	
	
	}//doProcess메소드 끝
}//클래스 끝








