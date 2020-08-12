package net.member.action;


//�ϴ���1. ������ ��� ���ΰ��� �������� ��ȯ���ִ� ����
//       ������ ��� ���ΰ� true�϶� -> Response.sendRedirect()�����̷�Ʈ��� 
//       ������ ��� ���ΰ� false�϶� -> dis.forward()����ġ���

//�ϴ���2. �̵������� ��ΰ� �����Ͽ� ��ȯ���ִ� ���� 
public class ActionForward {

	//������ ��� ���ΰ��� ������ ���� 
	private boolean isRedirect = false;
	
	//�̵� ������ ��� �ּҰ� ������ ���� 
	private String path = null;
	
	//������ ��� ���ΰ� ������ ���� ��Ű�� �޼ҵ�
	public void setRedirect(boolean isRedirect){
		this.isRedirect = isRedirect;
	}
	//������ ��� ���ΰ�(������)�� ���� ���ִ� �޼ҵ� 
	public boolean isRedirect(){
		return isRedirect;
	}
	//�̵��� ������ ��� �ּҰ��� �� �⺯���� �����ų �޼ҵ� 
	public void setPath(String path){
		this.path = path;
	}
	//�� path������ ����� �̵��� ������ ��� �ּҸ� ���� ���ִ� �޼ҵ�
	public String getPath(){
		return path;
	}
}





