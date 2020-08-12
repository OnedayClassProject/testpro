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

//���� ������ ������(����, ����, ���� �� �ϳ�) ���� �˻� 
@WebServlet("/CarcategoryController.do")  
public class CarcategoryController extends HttpServlet {

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
		
		//1. Ŭ���̾�Ʈ�� ��û�� �Ķ���� ���
		//CarList.jsp����������  ����, ����, ������ ������  �ϳ��� ��û�� ��� 
		String carcategory = request.getParameter("carcategory");
												   
		//2.��û�� ���� ���� ���䰪(������ ��ȸ�� ������) ����
		CarDAO cdao = new CarDAO();
		//��û�� ���� �̿��� ������ ���� �˻��� ���Ͽ� �޼ҵ� ȣ��!
		Vector<CarListBean> v = cdao.getCartegoryCarList(carcategory);
		
		//3.View�������� ���䰪�� ���� �ϱ� ���� request���尴ü �޸� ������  ���䰪 ����
		request.setAttribute("Vector", v);
		
		//4.View�������� ���û��~ �̵���~ request���尴ü �޸𸮴� ���� ��
		//CarMain.jsp�� ������ 
		RequestDispatcher dis = 
				request.getRequestDispatcher("CarMain.jsp?center=CarList.jsp");
		dis.forward(request, response);
	}
	
	
	
}
