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

//���� �ϳ��� ������ ��Ʈ �ϱ� ����...���� ������ ������ �մϴ�.
//�ϳ��� ���� �̹����� Ŭ��������.. ���� ������ DB�� ���� �˻� �ϱ� ���� ��û�� �޴� ���� 
@WebServlet("/CarInfoController.do")  
public class CarInfoController extends HttpServlet {

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
		//������ ������ ���� ��ȣ ��� 
		int carno = Integer.parseInt(request.getParameter("carno"));
		
												   
		//2.��û�� ���� ���� ���䰪(������ ������ ����ȣ�� �̿���  ��ȸ�� ������) ����
		CarDAO cdao = new CarDAO();
		//��û�� ���� �̿��� ������ �ϳ���  ���� �˻��� ���Ͽ� �޼ҵ� ȣ��!
	    CarListBean bean = cdao.getOneCar(carno);
		   
		//3.View�������� ���䰪�� ���� �ϱ� ���� request���尴ü �޸� ������  ���䰪 ����
		request.setAttribute("bean", bean);
		
		//4.View�������� ���û��~ �̵���~ request���尴ü �޸𸮴� ���� ��
		//CarMain.jsp�� ������ 
		RequestDispatcher dis = 
				request.getRequestDispatcher("CarMain.jsp?center=CarInfo.jsp");
		dis.forward(request, response);
	}
	
	
	
}
