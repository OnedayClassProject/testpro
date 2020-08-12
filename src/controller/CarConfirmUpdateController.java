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


// CarReserveResult.jsp����������  ������ư�� Ŭ�� ������...
// ���� ��û�� �޴� ���� // ���� id, ����� ������ �̹��� ���� ���� �޴´�.
@WebServlet("/CarConfirmUpdateController.do")
public class CarConfirmUpdateController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. Ŭ���̾�Ʈ�� ��û�� ��� 
		// ���� id, ����� ������ �̹����� ���
		String carimg = request.getParameter("carimg");
		int orderid =  Integer.parseInt(request.getParameter("orderid"));
		
		//2.��û�Ѱ��� �̿��� ������ �� ����
		//���� �ϱ� ���� �ϳ��� ����������  DB�κ��� ��ȸ �Ͽ� ��� 
		CarDAO cdao = new CarDAO();
		//�ϳ��� ���������� DB�κ��� ��ȸ �ϱ� ����..
		//getOneOrder()�޼ҵ� ȣ���. ���� id����! �Ͽ� ��ȸ�� ��
		CarConfirmBean cbean = cdao.getOneOrder(orderid);
		//���� �̹����� ����? �߰���? ����
		cbean.setCarimg(carimg);
		
		
		//3.������ ���� View�������� ���� �ϱ� ����  request���尴ü�޸𸮿����� ���� ��Ų��.
		request.setAttribute("cbean", cbean);
		
		//4.View�������� ������ �� ����
		RequestDispatcher dis = 
				request.getRequestDispatcher("CarMain.jsp?center=CarConfirmUpdate.jsp");
		//���� ������
		dis.forward(request, response);
	}
}








