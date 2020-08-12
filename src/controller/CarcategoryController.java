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

//고객이 선택한 종류별(소형, 중형, 대형 중 하나) 차량 검색 
@WebServlet("/CarcategoryController.do")  
public class CarcategoryController extends HttpServlet {

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
		
		//1. 클라이언트가 요청한 파라미터 얻기
		//CarList.jsp페이지에서  소형, 중형, 대형중 선택한  하나의 요청값 얻기 
		String carcategory = request.getParameter("carcategory");
												   
		//2.요청한 값에 의해 응답값(종류별 조회한 데이터) 마련
		CarDAO cdao = new CarDAO();
		//요청한 값을 이용해 종류별 차량 검색을 위하여 메소드 호출!
		Vector<CarListBean> v = cdao.getCartegoryCarList(carcategory);
		
		//3.View페이지로 응답값을 전달 하기 위해 request내장객체 메모리 영역에  응답값 저장
		request.setAttribute("Vector", v);
		
		//4.View페이지를 재요청해~ 이동시~ request내장객체 메모리는 유지 함
		//CarMain.jsp로 포워딩 
		RequestDispatcher dis = 
				request.getRequestDispatcher("CarMain.jsp?center=CarList.jsp");
		dis.forward(request, response);
	}
	
	
	
}
