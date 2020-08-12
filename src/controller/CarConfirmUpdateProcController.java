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


//CarConfirmUpdate.jsp���������� ��û�� ���������� �޾� DB�� UPDATE ��� �ϴ� ���� 
@WebServlet("/CarConfirmUpdateProcController.do")
public class CarConfirmUpdateProcController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. Ŭ���̾�Ʈ�� ��û�� ��� 
		// ������ ������ ��� 
		int orderid = Integer.parseInt(request.getParameter("orderid"));
		int carreserveday = Integer.parseInt(request.getParameter("carreserveday"));
		int carqty = Integer.parseInt(request.getParameter("carqty"));
		int carins = Integer.parseInt(request.getParameter("carins"));
		int carwifi = Integer.parseInt(request.getParameter("carwifi"));
		int carbabyseat = Integer.parseInt(request.getParameter("carbabyseat"));
		String carbegindate = request.getParameter("carbegindate");
		String memberpass = request.getParameter("memberpass");	
				
		//CarOrderBean��ü�� ���� �Ͽ�  ������ �������� �������� ����
		CarOrderBean bean = new CarOrderBean(orderid, 
				                             carqty, 
				                             carreserveday, 
				                             carbegindate, 
				                             carins, 
				                             carwifi, 
				                             carbabyseat, 
				                             memberpass);
		
		
		//2.��û�Ѱ��� �̿��� ������ �� ����
		//�� �� ������ ����� ������ DB�� UPDATE ��Ű�� 
		CarDAO cdao = new CarDAO();
		//���� �޼ҵ� ȣ��
		int result = cdao.carOrderUpdate(bean);
		
		//3.������ ���� View�������� ���� �ϱ� ����  request���尴ü�޸𸮿����� ���� ��Ų��.
		//����
		
		//4.View��������
		//-> ������ �����ϸ� �ٽ� ��ü ���� ��ȸ �ϱ� ����  ��Ī �ּҸ� �̿��ؼ�  �������� ������ 
		RequestDispatcher dis = 
				request.getRequestDispatcher("CarListController.do");
		//���� ������
		dis.forward(request, response);
	}
}








