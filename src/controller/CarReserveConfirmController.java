package controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.CarConfirmBean;
import db.CarDAO;
import db.CarListBean;
import db.CarOrderBean;


/*
  CarReseveConfirm.jsp <예약확인>을 요청 하는 디자인 화면에서
  예약 확인을 위해.. 입력한 전화번호와 비밀번호를 전달 받아..
 DB에 해당하는 레코드가 있는지 검사 하는 서블릿 컨트롤러 클래스 
 */
@WebServlet("/CarReserveConfirmController.do")
public class CarReserveConfirmController extends HttpServlet {


	@Override  //클라이언트가 GET방식으로 요청하면 호출되는 콜백 메소드 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("GET");
		requestPro(request, response);
	}

	@Override  //클라이언트가  POST전송 방식으로 요청하면 호출되는 콜백 메소드 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("POST");
		requestPro(request, response);
	}
	//GET방식으로 요청 하든 POST방식으로 요청하든 클라이언트의 모든 요청 처리를 하는 메소드   
	protected void requestPro(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		//1. 사용자로부터 입력 받은 전화번호와 비밀번호 얻기 (요청한 값 얻기)
		String memberphone = request.getParameter("memberphone");
		String memberpass = request.getParameter("memberpass");
		
		//2. 요청한 값을 통해 응답할 값을 마련 (DB작업- 예약확인을 위한 예약정보를 검색)
		CarDAO cdao = new CarDAO();
		//예약정보 검색을 위한 메소드 호출!
		//렌트 예약시 입력했던 비회원 전화번호와 , 비밀번호를 매개변수로 전달 하여..
		//조건1. 전화번호와 비밀번호를 기준으로 하여 검색 해야 한다.
		//조건2. 현재 날짜 보다 이전 날짜의 예약 현황은 보여 주지 말아라!
		Vector<CarConfirmBean>  vector = cdao.getAllCarOrder(memberphone,memberpass);
		
		//3.응답할 값(예약확인을 위한 검색한 예약 정보들)을 View페이지로 전달 하여 출력(응답) 하기 위해
		// request내장객체 메모리에 임시로 저장 해 둔다.
		request.setAttribute("v", vector);
		
		//4.View페이지로 포워딩시 응답할 데이터를 전달 함.
		RequestDispatcher  dis = 
				request.getRequestDispatcher("CarMain.jsp?center=CarReserveResult.jsp");
		//실제 포워딩
		dis.forward(request, response);
		
	}
	
}












