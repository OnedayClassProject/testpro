package net.member.action;


//하는일1. 포워딩 방식 여부값을 저장한후 반환해주는 역할
//       포워딩 방식 여부값 true일때 -> Response.sendRedirect()리다이렉트방식 
//       포워딩 방식 여부값 false일때 -> dis.forward()디스패치방식

//하는일2. 이동페이지 경로값 저장하여 반환해주는 역할 
public class ActionForward {

	//포워딩 방식 여부값을 저장할 변수 
	private boolean isRedirect = false;
	
	//이동 페이지 경로 주소값 저장할 변수 
	private String path = null;
	
	//포워딩 방식 여부값 변수에 저장 시키는 메소드
	public void setRedirect(boolean isRedirect){
		this.isRedirect = isRedirect;
	}
	//포워딩 방식 여부값(변수값)을 리턴 해주는 메소드 
	public boolean isRedirect(){
		return isRedirect;
	}
	//이동할 페이지 경로 주소값을 위 멤변수에 저장시킬 메소드 
	public void setPath(String path){
		this.path = path;
	}
	//위 path변수에 저장된 이동할 페이지 경로 주소를 리턴 해주는 메소드
	public String getPath(){
		return path;
	}
}





