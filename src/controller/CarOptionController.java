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


//CarOption.jsp���������� �ɼǼ����� �����ϱ� ��ư�� Ŭ�� ������ ��û�� �޴� ����
@WebServlet("/CarOptionController.do")
public class CarOptionController extends HttpServlet {


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
	
		//CarOption.jsp���� ������  �ݾ� ������ ���Ͽ� �����͸� �޾ƿ�
		int carqty = Integer.parseInt(request.getParameter("carqty")); //�뿩 ���� 
		int carprice = Integer.parseInt(request.getParameter("carprice")); //�뿩 �ݾ�
		int carreserveday = Integer.parseInt(request.getParameter("carreserveday")); //�뿩 �Ⱓ
		
		//���� ���� ���� �ޱ�  =  �������� 1���̸�  �� 1�ޱ�
		//                  ���� �������̸� �� 0�ޱ�
		int carins = Integer.parseInt(request.getParameter("carins"));
		
		//���� wifi ���� ���� �ޱ� 
		int carwifi = Integer.parseInt(request.getParameter("carwifi"));
		
		//�׺���̼� ���� ���� �ޱ�  =  ����(����) �̸� �� 1�ޱ�
		//                       ������ �̸� �� 0�ޱ�
		int carnave = Integer.parseInt(request.getParameter("carnave"));
		
		//���̺� ��Ʈ ���� ���� �ޱ� =  ���� 1���̸� �� 1�ޱ� 
		//                       �������̸� �� 0�ޱ� 
		int carbabyseat = Integer.parseInt(request.getParameter("carbabyseat"));
		
		//�������� = ���� * �뿩�Ⱓ * ��������
		int totalreserve = carqty * carreserveday * carprice;
		
		//�ɼǱݾ� = �����ɼǿ� �뿩�Ⱓ�� ������ ���Ѵ�.
		int totaloption = ((carins * carreserveday) 
				         + (carwifi * carreserveday) 
				         + (carbabyseat * carreserveday)) * 10000 * carqty;
		
        //�ڹٺ� Ŭ������? CarOrderBean��ü�� �����Ͽ�  DB�� �߰��� ������������ ����
		CarOrderBean cbean = new CarOrderBean();
		cbean.setCarno(Integer.parseInt(request.getParameter("carno")));
		cbean.setCarreserveday(carreserveday);
		cbean.setCarqty(carqty);
		cbean.setCarnave(carnave);
		cbean.setCarbabyseat(carbabyseat);
		cbean.setCarbegindate(request.getParameter("carbegindate"));
		cbean.setCarins(carins);
		
		
		//CarOrder.jsp�� �����͸� �Ѱ� ��� ���ֱ� ���� request���尴ü ������ ��Ƶα�
		request.setAttribute("cbean", cbean); 
		request.setAttribute("totalreserve", totalreserve); //���� ���� �⺻ �ݾ� ����
		request.setAttribute("totaloption", totaloption); //���� ���� �� �ɼ� �ݾ� ����
		
		//����ó ������� ������
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("CarMain.jsp?center=CarOrder.jsp");
		dispatcher.forward(request, response);
	}
	
}









