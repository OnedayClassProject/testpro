package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.CarConfirmBean;
import db.CarDAO;


//CarConfirmDelete.jspȭ�鿡�� ���� ������ ���� ���� ��й�ȣ�� �Է���..
//���� ��û�� �Ͽ� ���� �޴� ���� 
@WebServlet("/CarConfirmDeleteController.do")
public class CarConfirmDeleteController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. Ŭ���̾�Ʈ�� ��û�� ��� 
		//��й�ȣ, ����id
		int orderid = Integer.parseInt(request.getParameter("orderid"));
		String memberpass = request.getParameter("memberpass");
		
				
		//2.��û�Ѱ��� �̿��� ������ �� ����
		//���� ������ ���� �ϱ� ���� CarDAO��ü�� �޼ҵ� ȣ���  ���� ���̵��  �Է��� ��й�ȣ�� ���� �Ͽ� DELETE�۾���
		CarDAO cdao = new CarDAO();
		
		//DELETE������ ���� �ߴٸ� 1�� ��ȯ �ް� ���� �ϸ� 0�� ��ȯ �޴´�.
		int result = cdao.carOrderDelete(orderid,memberpass);
		
		if(result != 0){ //������ ���� �ߴٸ�?
			//������ ������ ���� ���� 
			response.setContentType("text/html;charset=utf-8");
			//PrintWriter��ü ���
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('��Ʈī ���� ������ ���� �߽��ϴ�.');");
			out.print("location.href='CarListController.do'");
			out.print("</script>");
			
//			RequestDispatcher dis = 
//					request.getRequestDispatcher("CarListController.do");
//			dis.forward(request, response);
			
			
		}else{//������ ���� �ߴٸ�? -> ��й�ȣ Ʋ��
			
			request.setAttribute("result", result);
			
			//������û�� �ϴ� CarConfirmDelete.jsp�߾�ȭ���� ���û �Ͽ� �̵�
			//�̵���~ �� request�� ��� result���� ���� �Ͽ� �޼����� ��Ÿ��
			RequestDispatcher dis = 
					request.getRequestDispatcher("CarMain.jsp?center=CarConfirmDelete.jsp");
			dis.forward(request, response);
		}	
	
	}//doProcess�޼ҵ� ��
}//Ŭ���� ��








