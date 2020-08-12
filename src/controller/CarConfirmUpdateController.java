package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.CarConfirmBean;
import db.CarDAO;


// CarReserveResult.jsp페이지에서  수정버튼을 클릭 했을때...
// 수정 요청을 받는 서블릿 // 예약 id, 예약된 차량의 이미지 명을 전달 받는다.
@WebServlet("/CarConfirmUpdateController.do")
public class CarConfirmUpdateController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 클라이언트의 요청값 얻기 
		// 예약 id, 예약된 차량의 이미지명 얻기
		String carimg = request.getParameter("carimg");
		int orderid =  Integer.parseInt(request.getParameter("orderid"));
		
		//2.요청한값을 이용해 응답할 값 마련
		//수정 하기 전의 하나의 예약정보를  DB로부터 조회 하여 얻기 
		CarDAO cdao = new CarDAO();
		//하나의 예약정보를 DB로부터 조회 하기 위해..
		//getOneOrder()메소드 호출시. 예약 id전달! 하여 조회해 옴
		CarConfirmBean cbean = cdao.getOneOrder(orderid);
		//차량 이미지명 또한? 추가로? 저장
		cbean.setCarimg(carimg);
		
		
		//3.응답할 값을 View페이지로 전달 하기 위해  request내장객체메모리영역에 유지 시킨다.
		request.setAttribute("cbean", cbean);
		
		//4.View페이지로 응답할 값 전달
		RequestDispatcher dis = 
				request.getRequestDispatcher("CarMain.jsp?center=CarConfirmUpdate.jsp");
		//실제 포워딩
		dis.forward(request, response);
	}
}








