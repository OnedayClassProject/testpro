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

//고객이 하나의 차량을 렌트 하기 위해...차량 정보를 보려고 합니다.
//하나의 차량 이미지를 클릭했을떄.. 차량 정보를 DB로 부터 검색 하기 위해 요청을 받는 서블릿 
@WebServlet("/CarInfoController.do")  
public class CarInfoController extends HttpServlet {

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
		//선택한 차량의 차량 번호 얻기 
		int carno = Integer.parseInt(request.getParameter("carno"));
		
												   
		//2.요청한 값에 의해 응답값(선택한 차량의 차번호를 이용해  조회한 데이터) 마련
		CarDAO cdao = new CarDAO();
		//요청한 값을 이용해 선택한 하나의  차량 검색을 위하여 메소드 호출!
	    CarListBean bean = cdao.getOneCar(carno);
		   
		//3.View페이지로 응답값을 전달 하기 위해 request내장객체 메모리 영역에  응답값 저장
		request.setAttribute("bean", bean);
		
		//4.View페이지를 재요청해~ 이동시~ request내장객체 메모리는 유지 함
		//CarMain.jsp로 포워딩 
		RequestDispatcher dis = 
				request.getRequestDispatcher("CarMain.jsp?center=CarInfo.jsp");
		dis.forward(request, response);
	}
	
	
	
}
