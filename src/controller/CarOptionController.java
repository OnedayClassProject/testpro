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


//CarOption.jsp페이지에서 옵션선택후 예약하기 버튼을 클릭 했을떄 요청을 받는 서블릿
@WebServlet("/CarOptionController.do")
public class CarOptionController extends HttpServlet {


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
	
		//CarOption.jsp에서 전달한  금액 연산을 위하여 데이터를 받아옴
		int carqty = Integer.parseInt(request.getParameter("carqty")); //대여 수량 
		int carprice = Integer.parseInt(request.getParameter("carprice")); //대여 금액
		int carreserveday = Integer.parseInt(request.getParameter("carreserveday")); //대여 기간
		
		//보험 적용 여부 받기  =  보험적용 1일이면  값 1받기
		//                  보험 비적용이면 값 0받기
		int carins = Integer.parseInt(request.getParameter("carins"));
		
		//무선 wifi 적용 여부 받기 
		int carwifi = Integer.parseInt(request.getParameter("carwifi"));
		
		//네비게이션 적용 여부 받기  =  적용(무료) 이면 값 1받기
		//                       비적용 이면 값 0받기
		int carnave = Integer.parseInt(request.getParameter("carnave"));
		
		//베이비 시트 적용 여부 받기 =  적용 1일이면 값 1받기 
		//                       비적용이면 값 0받기 
		int carbabyseat = Integer.parseInt(request.getParameter("carbabyseat"));
		
		//차량가액 = 수량 * 대여기간 * 차량가격
		int totalreserve = carqty * carreserveday * carprice;
		
		//옵션금액 = 각종옵션에 대여기간과 수량을 곱한다.
		int totaloption = ((carins * carreserveday) 
				         + (carwifi * carreserveday) 
				         + (carbabyseat * carreserveday)) * 10000 * carqty;
		
        //자바빈 클래스인? CarOrderBean객체를 생성하여  DB에 추가할 예약정보들을 저장
		CarOrderBean cbean = new CarOrderBean();
		cbean.setCarno(Integer.parseInt(request.getParameter("carno")));
		cbean.setCarreserveday(carreserveday);
		cbean.setCarqty(carqty);
		cbean.setCarnave(carnave);
		cbean.setCarbabyseat(carbabyseat);
		cbean.setCarbegindate(request.getParameter("carbegindate"));
		cbean.setCarins(carins);
		
		
		//CarOrder.jsp로 데이터를 넘겨 출력 해주기 위해 request내장객체 영역에 담아두기
		request.setAttribute("cbean", cbean); 
		request.setAttribute("totalreserve", totalreserve); //예약 차량 기본 금액 저장
		request.setAttribute("totaloption", totaloption); //예약 차량 총 옵션 금액 저장
		
		//디스패처 방식으로 포워딩
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("CarMain.jsp?center=CarOrder.jsp");
		dispatcher.forward(request, response);
	}
	
}









