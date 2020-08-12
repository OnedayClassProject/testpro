package net.member.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

//�ϴ���
//1.ȸ������ ��(join.jsp)���� �Է��� ��������  MemberBean��ü�� �������� ���� ��Ű��...
//2.���� ��Ų MemberBean��ü�� DB�۾��� �ϱ� ���� DAO��ü�� ���� �Ͽ� DB�� ȸ�� �߰� ��� ��!
//3.ȸ�� ���Կ� �����ϸ�.. �α��� �������� �̵� ��Ű�� ����..
//  ��������� ���ΰ�, �̵��������� ��� ����  new ActionForward��ü�� �����Ͽ� 
//  MemberFrontController�������� ��ȯ ���ִ� ������ ��.

public class MemberJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//�ѱ�ó��
		request.setCharacterEncoding("UTF-8");
		
		//1.ȸ������ ��(join.jsp)���� �Է��� ��������  MemberBean��ü�� �������� ���� ��Ű��...
		MemberBean mb = new MemberBean();
		mb.setId(request.getParameter("id"));
//		mb.setAge(Integer.parseInt(request.getParameter("age")));
		mb.setPass(request.getParameter("pass"));
		mb.setName(request.getParameter("name"));
		mb.setAddress(request.getParameter("address"));
		mb.setEmail(request.getParameter("email"));
		mb.setMobile(request.getParameter("mobile"));
		mb.setPhone(request.getParameter("phone"));
		mb.setDate(new Timestamp(System.currentTimeMillis()));
		
		//ȸ�� ���� ���� ���θ� ������ ���� ����
		boolean result = false;
		
		//2.���� ��Ų MemberBean��ü�� DB�۾��� �ϱ� ���� DAO��ü�� ���� �Ͽ� DB�� ȸ�� �߰� ��� ��!
		MemberDAO mdao = new MemberDAO();
		
		//���� �� �����ϸ� true��ȯ �ް� , ���� �ϸ� false��ȯ �޴´�.
		result = mdao.insertMember(mb);
		
		//3.ȸ������ ���� �������� ���� ������ ó�� 
		if(result == false){//����
			System.out.println("ȸ������ ����");
			return null; //MemberFrontController�������� ����
		}
		
		//ȸ������ ���� ������..
		ActionForward forward = new ActionForward();
		//������ ��� ���ΰ� true�� ����
		forward.setRedirect(true);
		//������ �� �ּҸ� ����
		forward.setPath("./MemberLogin.me");
		
		return forward;//MemberFrontController�������� ����
		
	}//execute()�޼ҵ� ��

}//MemberJoinActionŬ���� ��



