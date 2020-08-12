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


//CarOrder.jsp���������� ���� �ϱ�  ��ư�� ��������  ���� ��û�� �޴� ���� 
@WebServlet("/CarOrderController.do")
public class CarOrderController extends HttpServlet {


	@Override  //Ŭ���̾�Ʈ�� GET������� ��û�ϸ� ȣ��Ǵ� �ݹ� �޼ҵ� 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("GET");
		requestPro(request, response);
	}

	@Override  //Ŭ���̾�Ʈ��  POST���� ������� ��û�ϸ� ȣ��Ǵ� �ݹ� �޼ҵ� 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("POST");
		requestPro(request, response);
	}
	//GET������� ��û �ϵ� POST������� ��û�ϵ� Ŭ���̾�Ʈ�� ��� ��û ó���� �ϴ� �޼ҵ�   
	protected void requestPro(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		//1. 2. CarOrder.jsp���� ������ �ݾ��� Ȯ�� �ϰ� , ��ȸ�� ����ȣ�� ��й�ȣ�� �Է� �� 
		// ���� �ϱ� ��ư�� Ŭ����������  ������ �������� ��� request�������� ���� �����ͼ� 
		// CarOrderBean��ü�� �������� ����
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
		
		//3.���� ������ DB�� �߰� 
		CarDAO cdao = new CarDAO();
		cdao.insertCarOrder(cbean); //�޼ҵ� ȣ��� �ٷ� ���� CarOrderBean��ü ���� 
		
		//4.���� ������ DB�� �߰��� ~ ��ü ����  �˻� ��û�� CarListController�������� �ؾ���.~
		//������
		RequestDispatcher dis = request.getRequestDispatcher("CarListController.do");
		dis.forward(request, response);
	}
	
}












