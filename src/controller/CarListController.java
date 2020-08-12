package controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.CarDAO;
import db.CarListBean;

//사장
//CarReservation.jsp화면에서 전체검색 버튼을 클릭했을때  요청 받는 서블릿(Controller)
@WebServlet("/CarListController.do")
public class CarListController extends HttpServlet {

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
	
		//DB와 연결을 맺어 전체 차량 검색을 위하여 Model역할을 하는 객체 생성 
		CarDAO cdao = new CarDAO(); //부장
		
		//DB에 저장된 전체 차량 정보를 검색 해줘~ 메소드 호출
		Vector<CarListBean> v = cdao.getAllCarList();
		
		
		//View페이지로 응답값을 전달 하기 위해...임시로 request내장객체영역에 응답할 값을 저장
		//응답할 값 : DB로 부터 조회한 전체 차량 정보  Vector
		request.setAttribute("Vector", v);
		
		//CarMain.jsp로 포워딩 
		RequestDispatcher dis = 
				request.getRequestDispatcher("CarMain.jsp?center=CarList.jsp");
		dis.forward(request, response);
		
	}
	
	
}







