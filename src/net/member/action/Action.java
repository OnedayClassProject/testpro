package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Action���� Ŭ�������� Action�������̽� ������ �߻�޼ҵ带 �������̵� �ؼ� ��� �ϱ� ���� ����
public interface Action {// Ŭ������ ����� ���� Ʋ

	public ActionForward execute(HttpServletRequest request,
								 HttpServletResponse response)throws Exception;
	
	
}
