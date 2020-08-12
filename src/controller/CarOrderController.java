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
import db.CarOrderBean;


//CarOrder.jsp페이지에서 결제 하기  버튼을 눌렀을때  예약 요청을 받는 서블릿 
@WebServlet("/CarOrderController.do")
public class CarOrderController extends HttpServlet {


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
	
		//1. 2. CarOrder.jsp에서 결제할 금액을 확인 하고 , 비회원 폰번호와 비밀번호를 입력 후 
		// 결제 하기 버튼을 클릭했을때의  예약할 정보들을 모두 request영역으로 부터 꺼내와서 
		// CarOrderBean객체의 각변수에 저장
		CarOrderBean cbean = new CarOrderBean();
		cbean.setCarno(Integer.parseInt(request.getParameter("carno")));
		cbean.setCarqty(Integer.parseInt(request.getParameter("carqty")));
		cbean.setCarreserveday(Integer.parseInt(request.getParameter("carreserveday")));
		cbean.setCarbegindate(request.getParameter("carbegindate"));
		cbean.setCarins(Integer.parseInt(request.getParameter("carins")));
		cbean.setCarwifi(Integer.parseInt(request.getParameter("carwifi")));
		cbean.setCarnave(Integer.parseInt(request.getParameter("carnave")));
		cbean.setCarbabyseat(Integer.parseInt(request.getParameter("carbabyseat")));
		cbean.setMemberphone(request.getParameter("memberphone"));
		cbean.setMemberpass(request.getParameter("memberpass"));
		
		//3.예약 정보를 DB에 추가 
		CarDAO cdao = new CarDAO();
		cdao.insertCarOrder(cbean); //메소드 호출시 바로 위의 CarOrderBean객체 전달 
		
		//4.예약 정보를 DB에 추가후 ~ 전체 차량  검색 요청을 CarListController서블릿으로 해야함.~
		//포워딩
		RequestDispatcher dis = request.getRequestDispatcher("CarListController.do");
		dis.forward(request, response);
	}
	
}












