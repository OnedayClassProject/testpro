package net.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//회원 관련 처리를 위한 컨트롤러 클래스 
//요청1. Top.jsp에서 join링크 눌렀을때 ... 회원가입 화면으로 이동 시켜줘~ 라는 요청 -> 
//      요청한 전체주소 : http://192.168.6.200:8080/CarProject/MemberJoin.me 

//요청2. member/join.jsp페이지에서 가입할 회원정보 입력후 가입 버튼을 눌렀을때 DB에 새회원 추가 시켜줘~ 라는 요청->
//      요청한 전체 주소 : http://localhost:8080/CarProject/MemberJoinAction.me

//요청3. DB에 회원 가입에 성공 한후  로그인 화면으로 이동 시켜 줘~ 라는 요청->
//      요청한 전체 주소 : http://localhost:8080/CarProject/MemberLogin.me

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
		
		//1. 클라이언트가 요청한 값 전달 받기 (요청한 주소 얻기)
		//   /CarProject/MemberJoin.me   >>> 회원 가입 화면으로 이동 시켜줘~라는 전체 요청주소 
		//   /CarProject/MemberJoinAction.me  >>> 회원가입 시켜줘~라는 전체 요청 주소 
		//   /CarProject/MemberLogin.me  >>>회원가입 후 로그인 페이지로 이동 시켜줘~ 전체 요청 주소 
		String RequestURI = request.getRequestURI();
		System.out.println(RequestURI);
		
		//  /CarProject/MemberJoin.me 요청한 전체 주소중에서..
		//  컨텍스트 패스 주소 얻기  : /CarProject
		
		//  /CarProject/MemberJoinAction.me 요청한 전체 주소중에서..
	    //  컨텍스트 패스 주소 얻기  : /CarProject
		
		// /CarProject/MemberLogin.me 요청 한 전체 주소중에서..
		//  컨텍스트 패스 주소 얻기  : /CarProject 
		String contextPath = request.getContextPath();
		System.out.println(contextPath.length());
		
	   //  /CarProject/MemberJoin.me 요청한 전체 주소중에서...
	   //  			  /MemberJoin.me 요청한 주소 잘라내서 얻기
	
	   //  /CarProject/MemberJoinAction.me 요청한 전체 주소중에서..
	   //             /MemberJoinAction.me 주소 잘라내서 얻기 
		
		// /CarProject/MemberLogin.me 요청 한 전체 주소중에서..
		//            /MemberLogin.me 요청 한 주소 잘라내서 얻기 
	   String command = RequestURI.substring(contextPath.length());

	   //포워딩 방식 여부값과  이동페이지 경로 주소값을 저장하여 리턴 해주는 역할하는 객체를 저장할 참조변수 선언
	   ActionForward forward = null;
	   
	   //Action관련 자식 객체들을 저장할 부모 인터페이스 타입의 참조변수 선언
	   Action action = null;
	   
	   //Top.jsp페이지에서 join링크를 눌러 회원가입 디자인 화면으로 이동 시켜줘~ 라는 요청주소를 받았을때..
	   if(command.equals("/MemberJoin.me")){
		   
		   //포워딩 방식 여부값과  이동할 뷰페이지 경로 주소값을 저장 하여 사용할 객체 생성
		   forward = new ActionForward();
		   
		   //포워딩 방식 여부값 fasle로 저장 -> 디스패치 방식
		   forward.setRedirect(false);
		   
		   //이동할 페이지 경로(회원가입페이지) 주소값 저장
		   forward.setPath("./CarMain.jsp?center=member/join.jsp");	   
	   
	   //join.jsp에서 회원 가입 요청이 들어 왔을때..
	   }else if(command.equals("/MemberJoinAction.me")){
		   
		   action = new MemberJoinAction();//회원가입 처리 명령(DB작업 명령))하는 객체 생성
		   
		   try{
			    //join.jsp에서 입력한 가입할 회원내용이 저장되어 있는  request객체 영역을
			   //MemberJoinAction객체의 execute()메소드 호출시 인자로 전달하여..
			   //DB에 회원정보 추가 후  회원가입에 성공하면..
			   //포워딩 방식 여부값과, 이동할 페이지 주소를 담고 있는 ActionForward객체를 반환 받는다.
			    forward  = action.execute(request, response);
			   
		   }catch(Exception e){
			   e.printStackTrace();
		   }   
		   
	   //회원가입 성공후! 로그인 화면으로 이동하라는 요청 주소를 받았을때..
	   //또는 
	   //Top.jsp에서 login링크를 클릭하여 로그인 화면으로 이동하라는 요청 주소를 받았을때..   
	   }else if(command.equals("/MemberLogin.me")){
		   
		   //포워딩 방식여부값, 포워딩할 경로를 저장하여 제공 해주는 객체 생성
		   forward = new ActionForward();
		   forward.setRedirect(false); // 디스패치 방식의 포워딩 
		   forward.setPath("./CarMain.jsp?center=member/login.jsp");
		 
	   }
	   
	   //포워딩 하는 부분
	   if(forward != null){ //ActionForward객체가 생성 되었다면
		   
		   if(forward.isRedirect()){ //true -> 리다이렉트 방식의 포워딩 
			   //리다이렉트 방식으로 페이지 이동!  -> 페이지 주소 경로 웹브라우저 주소창에 노출함!
			   response.sendRedirect(forward.getPath());
			   
		   }else{//false -> 디스패치 방식의 포워딩 
			   //디스패치 방식으로 포워딩 -> 페이지 주소경로를 웹브라우저 주소창에 노출X
			   RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
			   dispatcher.forward(request, response);
		   }   
	   }//if   
	   
	}//doProcess	
}//MemberFrontController











