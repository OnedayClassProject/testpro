package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Action관련 클래스들이 Action인터페이스 내부의 추상메소드를 오버라이딩 해서 사용 하기 위해 선언
public interface Action {// 클래스를 만들기 위한 틀

	public ActionForward execute(HttpServletRequest request,
								 HttpServletResponse response)throws Exception;
	
	
}
