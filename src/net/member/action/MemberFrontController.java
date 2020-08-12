package net.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//ȸ�� ���� ó���� ���� ��Ʈ�ѷ� Ŭ���� 
//��û1. Top.jsp���� join��ũ �������� ... ȸ������ ȭ������ �̵� ������~ ��� ��û -> 
//      ��û�� ��ü�ּ� : http://192.168.6.200:8080/CarProject/MemberJoin.me 

//��û2. member/join.jsp���������� ������ ȸ������ �Է��� ���� ��ư�� �������� DB�� ��ȸ�� �߰� ������~ ��� ��û->
//      ��û�� ��ü �ּ� : http://localhost:8080/CarProject/MemberJoinAction.me

//��û3. DB�� ȸ�� ���Կ� ���� ����  �α��� ȭ������ �̵� ���� ��~ ��� ��û->
//      ��û�� ��ü �ּ� : http://localhost:8080/CarProject/MemberLogin.me

public class MemberFrontController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. Ŭ���̾�Ʈ�� ��û�� �� ���� �ޱ� (��û�� �ּ� ���)
		//   /CarProject/MemberJoin.me   >>> ȸ�� ���� ȭ������ �̵� ������~��� ��ü ��û�ּ� 
		//   /CarProject/MemberJoinAction.me  >>> ȸ������ ������~��� ��ü ��û �ּ� 
		//   /CarProject/MemberLogin.me  >>>ȸ������ �� �α��� �������� �̵� ������~ ��ü ��û �ּ� 
		String RequestURI = request.getRequestURI();
		System.out.println(RequestURI);
		
		//  /CarProject/MemberJoin.me ��û�� ��ü �ּ��߿���..
		//  ���ؽ�Ʈ �н� �ּ� ���  : /CarProject
		
		//  /CarProject/MemberJoinAction.me ��û�� ��ü �ּ��߿���..
	    //  ���ؽ�Ʈ �н� �ּ� ���  : /CarProject
		
		// /CarProject/MemberLogin.me ��û �� ��ü �ּ��߿���..
		//  ���ؽ�Ʈ �н� �ּ� ���  : /CarProject 
		String contextPath = request.getContextPath();
		System.out.println(contextPath.length());
		
	   //  /CarProject/MemberJoin.me ��û�� ��ü �ּ��߿���...
	   //  			  /MemberJoin.me ��û�� �ּ� �߶󳻼� ���
	
	   //  /CarProject/MemberJoinAction.me ��û�� ��ü �ּ��߿���..
	   //             /MemberJoinAction.me �ּ� �߶󳻼� ��� 
		
		// /CarProject/MemberLogin.me ��û �� ��ü �ּ��߿���..
		//            /MemberLogin.me ��û �� �ּ� �߶󳻼� ��� 
	   String command = RequestURI.substring(contextPath.length());

	   //������ ��� ���ΰ���  �̵������� ��� �ּҰ��� �����Ͽ� ���� ���ִ� �����ϴ� ��ü�� ������ �������� ����
	   ActionForward forward = null;
	   
	   //Action���� �ڽ� ��ü���� ������ �θ� �������̽� Ÿ���� �������� ����
	   Action action = null;
	   
	   //Top.jsp���������� join��ũ�� ���� ȸ������ ������ ȭ������ �̵� ������~ ��� ��û�ּҸ� �޾�����..
	   if(command.equals("/MemberJoin.me")){
		   
		   //������ ��� ���ΰ���  �̵��� �������� ��� �ּҰ��� ���� �Ͽ� ����� ��ü ����
		   forward = new ActionForward();
		   
		   //������ ��� ���ΰ� fasle�� ���� -> ����ġ ���
		   forward.setRedirect(false);
		   
		   //�̵��� ������ ���(ȸ������������) �ּҰ� ����
		   forward.setPath("./CarMain.jsp?center=member/join.jsp");	   
	   
	   //join.jsp���� ȸ�� ���� ��û�� ��� ������..
	   }else if(command.equals("/MemberJoinAction.me")){
		   
		   action = new MemberJoinAction();//ȸ������ ó�� ���(DB�۾� ���))�ϴ� ��ü ����
		   
		   try{
			    //join.jsp���� �Է��� ������ ȸ�������� ����Ǿ� �ִ�  request��ü ������
			   //MemberJoinAction��ü�� execute()�޼ҵ� ȣ��� ���ڷ� �����Ͽ�..
			   //DB�� ȸ������ �߰� ��  ȸ�����Կ� �����ϸ�..
			   //������ ��� ���ΰ���, �̵��� ������ �ּҸ� ��� �ִ� ActionForward��ü�� ��ȯ �޴´�.
			    forward  = action.execute(request, response);
			   
		   }catch(Exception e){
			   e.printStackTrace();
		   }   
		   
	   //ȸ������ ������! �α��� ȭ������ �̵��϶�� ��û �ּҸ� �޾�����..
	   //�Ǵ� 
	   //Top.jsp���� login��ũ�� Ŭ���Ͽ� �α��� ȭ������ �̵��϶�� ��û �ּҸ� �޾�����..   
	   }else if(command.equals("/MemberLogin.me")){
		   
		   //������ ��Ŀ��ΰ�, �������� ��θ� �����Ͽ� ���� ���ִ� ��ü ����
		   forward = new ActionForward();
		   forward.setRedirect(false); // ����ġ ����� ������ 
		   forward.setPath("./CarMain.jsp?center=member/login.jsp");
		 
	   }
	   
	   //������ �ϴ� �κ�
	   if(forward != null){ //ActionForward��ü�� ���� �Ǿ��ٸ�
		   
		   if(forward.isRedirect()){ //true -> �����̷�Ʈ ����� ������ 
			   //�����̷�Ʈ ������� ������ �̵�!  -> ������ �ּ� ��� �������� �ּ�â�� ������!
			   response.sendRedirect(forward.getPath());
			   
		   }else{//false -> ����ġ ����� ������ 
			   //����ġ ������� ������ -> ������ �ּҰ�θ� �������� �ּ�â�� ����X
			   RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
			   dispatcher.forward(request, response);
		   }   
	   }//if   
	   
	}//doProcess	
}//MemberFrontController











