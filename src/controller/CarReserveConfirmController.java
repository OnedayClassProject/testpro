package controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.CarConfirmBean;
import db.CarDAO;
import db.CarListBean;
import db.CarOrderBean;


/*
  CarReseveConfirm.jsp <����Ȯ��>�� ��û �ϴ� ������ ȭ�鿡��
  ���� Ȯ���� ����.. �Է��� ��ȭ��ȣ�� ��й�ȣ�� ���� �޾�..
 DB�� �ش��ϴ� ���ڵ尡 �ִ��� �˻� �ϴ� ���� ��Ʈ�ѷ� Ŭ���� 
 */
@WebServlet("/CarReserveConfirmController.do")
public class CarReserveConfirmController extends HttpServlet {


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
	
		//1. ����ڷκ��� �Է� ���� ��ȭ��ȣ�� ��й�ȣ ��� (��û�� �� ���)
		String memberphone = request.getParameter("memberphone");
		String memberpass = request.getParameter("memberpass");
		
		//2. ��û�� ���� ���� ������ ���� ���� (DB�۾�- ����Ȯ���� ���� ���������� �˻�)
		CarDAO cdao = new CarDAO();
		//�������� �˻��� ���� �޼ҵ� ȣ��!
		//��Ʈ ����� �Է��ߴ� ��ȸ�� ��ȭ��ȣ�� , ��й�ȣ�� �Ű������� ���� �Ͽ�..
		//����1. ��ȭ��ȣ�� ��й�ȣ�� �������� �Ͽ� �˻� �ؾ� �Ѵ�.
		//����2. ���� ��¥ ���� ���� ��¥�� ���� ��Ȳ�� ���� ���� ���ƶ�!
		Vector<CarConfirmBean>  vector = cdao.getAllCarOrder(memberphone,memberpass);
		
		//3.������ ��(����Ȯ���� ���� �˻��� ���� ������)�� View�������� ���� �Ͽ� ���(����) �ϱ� ����
		// request���尴ü �޸𸮿� �ӽ÷� ���� �� �д�.
		request.setAttribute("v", vector);
		
		//4.View�������� �������� ������ �����͸� ���� ��.
		RequestDispatcher  dis = 
				request.getRequestDispatcher("CarMain.jsp?center=CarReserveResult.jsp");
		//���� ������
		dis.forward(request, response);
		
	}
	
}












