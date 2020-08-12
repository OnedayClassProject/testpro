package net.member.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

//하는일
//1.회원가입 폼(join.jsp)에서 입력한 정보들을  MemberBean객체의 각변수에 저장 시키고...
//2.저장 시킨 MemberBean객체를 DB작업을 하기 위한 DAO객체에 전달 하여 DB에 회원 추가 명령 함!
//3.회원 가입에 성공하면.. 로그인 페이지로 이동 시키기 위해..
//  포워딩방식 여부값, 이동할페이지 경로 값을  new ActionForward객체에 저장하여 
//  MemberFrontController서블릿으로 반환 해주는 역할을 함.

public class MemberJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//한글처리
		request.setCharacterEncoding("UTF-8");
		
		//1.회원가입 폼(join.jsp)에서 입력한 정보들을  MemberBean객체의 각변수에 저장 시키고...
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
		
		//회원 가입 성공 여부를 저장할 변수 선언
		boolean result = false;
		
		//2.저장 시킨 MemberBean객체를 DB작업을 하기 위한 DAO객체에 전달 하여 DB에 회원 추가 명령 함!
		MemberDAO mdao = new MemberDAO();
		
		//가입 에 성공하면 true반환 받고 , 실패 하면 false반환 받는다.
		result = mdao.insertMember(mb);
		
		//3.회원가입 성공 했을때와 실패 했을때 처리 
		if(result == false){//실패
			System.out.println("회원가입 실패");
			return null; //MemberFrontController서블릿으로 리턴
		}
		
		//회원가입 성공 했을때..
		ActionForward forward = new ActionForward();
		//포워딩 방식 여부값 true로 저장
		forward.setRedirect(true);
		//포워딩 할 주소를 저장
		forward.setPath("./MemberLogin.me");
		
		return forward;//MemberFrontController서블릿으로 리턴
		
	}//execute()메소드 끝

}//MemberJoinAction클래스 끝



