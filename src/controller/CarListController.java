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

//����
//CarReservation.jspȭ�鿡�� ��ü�˻� ��ư�� Ŭ��������  ��û �޴� ������(Controller)
@WebServlet("/CarListController.do")
public class CarListController extends HttpServlet {

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
	
		//DB�� ������ �ξ� ��ü ���� �˻��� ���Ͽ� Model������ �ϴ� ��ü ���� 
		CarDAO cdao = new CarDAO(); //����
		
		//DB�� ����� ��ü ���� ������ �˻� ����~ �޼ҵ� ȣ��
		Vector<CarListBean> v = cdao.getAllCarList();
		
		
		//View�������� ���䰪�� ���� �ϱ� ����...�ӽ÷� request���尴ü������ ������ ���� ����
		//������ �� : DB�� ���� ��ȸ�� ��ü ���� ����  Vector
		request.setAttribute("Vector", v);
		
		//CarMain.jsp�� ������ 
		RequestDispatcher dis = 
				request.getRequestDispatcher("CarMain.jsp?center=CarList.jsp");
		dis.forward(request, response);
		
	}
	
	
}






