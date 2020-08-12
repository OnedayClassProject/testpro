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
import db.CarOrderBean;


//CarConfirmUpdate.jsp페이지에서 요청한 수정정보를 받아 DB에 UPDATE 명령 하는 서블릿 
@WebServlet("/CarConfirmUpdateProcController.do")
public class CarConfirmUpdateProcController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 클라이언트의 요청값 얻기 
		// 수정할 정보들 얻기 
		int orderid = Integer.parseInt(request.getParameter("orderid"));
		int carreserveday = Integer.parseInt(request.getParameter("carreserveday"));
		int carqty = Integer.parseInt(request.getParameter("carqty"));
		int carins = Integer.parseInt(request.getParameter("carins"));
		int carwifi = Integer.parseInt(request.getParameter("carwifi"));
		int carbabyseat = Integer.parseInt(request.getParameter("carbabyseat"));
		String carbegindate = request.getParameter("carbegindate");
		String memberpass = request.getParameter("memberpass");	
				
		//CarOrderBean객체를 생성 하여  수정할 정보들을 각변수에 저장
		CarOrderBean bean = new CarOrderBean(orderid, 
				                             carqty, 
				                             carreserveday, 
				                             carbegindate, 
				                             carins, 
				                             carwifi, 
				                             carbabyseat, 
				                             memberpass);
		
		
		//2.요청한값을 이용해 응답할 값 마련
		//위 각 변수에 저장된 값들을 DB에 UPDATE 시키기 
		CarDAO cdao = new CarDAO();
		//수정 메소드 호출
		int result = cdao.carOrderUpdate(bean);
		
		//3.응답할 값을 View페이지로 전달 하기 위해  request내장객체메모리영역에 유지 시킨다.
		//생략
		
		//4.View페이지로
		//-> 수정에 성공하면 다시 전체 차량 조회 하기 위해  매칭 주소를 이용해서  서블릿으로 포워딩 
		RequestDispatcher dis = 
				request.getRequestDispatcher("CarListController.do");
		//실제 포워딩
		dis.forward(request, response);
	}
}








