package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//커넥션풀을 이용하여 데이터베이스에 접근하여 데이터를 입력 수정 삭제 검색할수 있는 DAO클래스
//MVC중에... Model의 역할을 함
public class CarDAO { //부장
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//DB와 미리 연결을 맺은 Connection객체 들이 저장된  DataSource(커넥션풀)을 얻고,
	//DataSource내부의 Connection객체를 얻기 위한 메소드
	public void getCon(){
		try{
			//1.WAS서버와 연결된 CarProject웹프로젝트의 모든 정보를 가지고 있는   컨텍스트객체 생성
			Context init = new InitialContext();
			
			//2.연결된 WAS서버에서  DataSource(커넥션풀) 검색해서 가져오기 
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/jspbeginner");
			
			//DataSource(커넥션풀)에서  DB연동객체 (커넥션) 가져오기  
			con = ds.getConnection(); //DB연결
			
		}catch(Exception err){
			System.out.println("커넥션풀 및 커넥션 얻기 실패  : " + err);
		}
	}
	
	//DB에 저장된 전체 차량 검색 메소드
	public Vector<CarListBean> getAllCarList(){
		
		//전체 차량 정보를  모두 검색해서 가져와서
		//검색한 정보중.. 한줄 단위로 CarListBean객체에 각각 저장후
		//CarListBean객체들을 Vector배열에 저장 하기 위해 선언 
		Vector<CarListBean> v = new Vector<CarListBean>();
		
		//검색한 차량 하나의 정보만? 저장할 용도의 CarListBean을 저장할 참조변수 
		CarListBean bean = null;
		
		try {
			//Connection얻기 (DB접속)
			getCon();
			//SQL문 준비  : 전체 차량 검색
			String sql = "select * from carlist";
			//SQL문 실행할 객체
			pstmt = con.prepareStatement(sql);
			//SQL문 실행한후 검색한 전체 차량 정보를 ResultSet에 담아 얻기
			rs = pstmt.executeQuery();
			
			//반복문을 사용해  ResultSet객체영역에 저장된 검색한 모든 차량 정보들을 꺼내어서 
			//CarListBean객체에 한줄정보씩 저장 후 
			//CarListBean객체들을 Vectory배열에 각각 추가 하여 저장
			while(rs.next()){
				
				bean = new CarListBean();
				bean.setCarno(rs.getInt(1)); //검색한 차번호 
				bean.setCarname(rs.getString(2));//검색한 차이름
				bean.setCarcompany(rs.getString("carcompany"));//검색한 차제조사
				bean.setCarprice(rs.getInt(4));//한대당 렌트 가격
				bean.setCarusepeople(rs.getInt("carusepeople"));//차 인승정보
				bean.setCarinfo(rs.getString(6));//차 정보 
				bean.setCarimg(rs.getString(7));//차 이미지 명 
				bean.setCarcategory(rs.getString("carcategory"));//차종류(소형, 대형, 중형)
				
				v.add(bean); 
				
			}	
		} catch (Exception e) {
			System.out.println("getAllCarlist메소드 내부에서 SQL 실행 오류 :" + e.toString() );
		} finally {
			//자원해제 
			try {
				if( pstmt != null)  pstmt.close();
				if( rs != null)  rs.close();
				if( con != null)  con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}//finally
		
		return v; //백터 리턴
		
	}//getAllCarlist메소드 끝

	
	//고객이 선택한  차량 종류에 대한  차량 검색
	public Vector<CarListBean> getCartegoryCarList(String cartegory) {
		
		//고객이 선택한  차량 정보를  모두 검색해서 가져와서
		//검색한 정보중.. 한줄 단위로 CarListBean객체에 각각 저장후
		//CarListBean객체들을 Vector배열에 저장 하기 위해 선언 
		Vector<CarListBean> v = new Vector<CarListBean>();
		
		//검색한 차량 하나의 정보만? 저장할 용도의 CarListBean을 저장할 참조변수 
		CarListBean bean = null;
		
		try {
			//Connection얻기 (DB접속)
			getCon();
			//SQL문 준비  : 매개변수로 전달 받은 고객이 선택한 종류의 차량들을 검색
			String sql = "select * from carlist where carcategory=?";
			//SQL문 실행할 객체
			pstmt = con.prepareStatement(sql);
			//?설정
			pstmt.setString(1, cartegory);
				
			//SQL문 실행한후 검색한 전체 차량 정보를 ResultSet에 담아 얻기
			rs = pstmt.executeQuery();
			
			//반복문을 사용해  ResultSet객체영역에 저장된 검색한 모든 차량 정보들을 꺼내어서 
			//CarListBean객체에 한줄정보씩 저장 후 
			//CarListBean객체들을 Vectory배열에 각각 추가 하여 저장
			while(rs.next()){
				
				bean = new CarListBean();
				bean.setCarno(rs.getInt(1)); //검색한 차번호 
				bean.setCarname(rs.getString(2));//검색한 차이름
				bean.setCarcompany(rs.getString("carcompany"));//검색한 차제조사
				bean.setCarprice(rs.getInt(4));//한대당 렌트 가격
				bean.setCarusepeople(rs.getInt("carusepeople"));//차 인승정보
				bean.setCarinfo(rs.getString(6));//차 정보 
				bean.setCarimg(rs.getString(7));//차 이미지 명 
				bean.setCarcategory(rs.getString("carcategory"));//차종류(소형, 대형, 중형)
				
				v.add(bean); 
				
			}	
		} catch (Exception e) {
			System.out.println("getCategoryCarList메소드 내부에서 SQL 실행 오류 :" + e.toString() );
		} finally {
			//자원해제 
			try {
				if( pstmt != null)  pstmt.close();
				if( rs != null)  rs.close();
				if( con != null)  con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}//finally
		
		return v; //백터 리턴
	}

	//고객이 선택한 차량에 대한 정보를 조회 (차량 번호를 이용해서 차 검색)
	public CarListBean getOneCar(int carno){
				
			//검색한 차량 하나의 정보만? 저장할 용도의 CarListBean을 저장할 참조변수 
			CarListBean bean = null;
			
			try {
				//Connection얻기 (DB접속)
				getCon();
				//SQL문 준비  : 매개변수로 전달 받은 차량 번호에 해당하는  차량을 검색
				String sql = "select * from carlist where carno=?";
				//SQL문 실행할 객체
				pstmt = con.prepareStatement(sql);
				//?설정
				pstmt.setInt(1, carno);
					
				//SQL문 실행한후 검색한 전체 차량 정보를 ResultSet에 담아 얻기
				rs = pstmt.executeQuery();
				
				//반복문을 사용해  ResultSet객체영역에 저장된 검색한 모든 차량 정보들을 꺼내어서 
				//CarListBean객체에 한줄정보씩 저장 후 
				//CarListBean객체들을 Vectory배열에 각각 추가 하여 저장
				if(rs.next()){
					
					bean = new CarListBean();
					bean.setCarno(rs.getInt(1)); //검색한 차번호 
					bean.setCarname(rs.getString(2));//검색한 차이름
					bean.setCarcompany(rs.getString("carcompany"));//검색한 차제조사
					bean.setCarprice(rs.getInt(4));//한대당 렌트 가격
					bean.setCarusepeople(rs.getInt("carusepeople"));//차 인승정보
					bean.setCarinfo(rs.getString(6));//차 정보 
					bean.setCarimg(rs.getString(7));//차 이미지 명 
					bean.setCarcategory(rs.getString("carcategory"));//차종류(소형, 대형, 중형)
					
				}	
			} catch (Exception e) {
				System.out.println("getCategoryCarList메소드 내부에서 SQL 실행 오류 :" + e.toString() );
			} finally {
				//자원해제 
				try {
					if( pstmt != null)  pstmt.close();
					if( rs != null)  rs.close();
					if( con != null)  con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}			
			}//finally
			
			return bean; //CarListBean객체를 CarInfoController로 리턴		
		
	}
	
	
	//예약 할 정보를 DB에 INSERT 시키는 메소드
	public void insertCarOrder(CarOrderBean cbean){
		
		try {
			
			getCon(); //DB연결
			
			String sql = "insert into carorder(carno,carqty,carreserveday,carbegindate,"
					   + "carins,carwifi,carnave,carbabyseat,memberphone,memberpass)"
					   + "values(?,?,?,?,?,?,?,?,?,?);";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, cbean.getCarno());
			pstmt.setInt(2, cbean.getCarqty());
			pstmt.setInt(3, cbean.getCarreserveday());
			pstmt.setString(4, cbean.getCarbegindate());
			pstmt.setInt(5, cbean.getCarins());
			pstmt.setInt(6, cbean.getCarwifi());
			pstmt.setInt(7, cbean.getCarnave());
			pstmt.setInt(8, cbean.getCarbabyseat());
			pstmt.setString(9, cbean.getMemberphone());
			pstmt.setString(10,cbean.getMemberpass());
				
			pstmt.executeUpdate(); //insert실행
				
		} catch (Exception e) {
			System.out.println("insertCarOrder내부에서 오류 : " + e);
		} finally {
			//자원해제 
			try {
				if( pstmt != null)  pstmt.close();
				if( rs != null)  rs.close();
				if( con != null)  con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}	
	}
	
	//예약확인 페이지에서 입력한 비회원 전화번호와 비밀번호에 해당하는 예약정보 조회 하는 메소드
	public Vector<CarConfirmBean>  getAllCarOrder(String memberphone, String memberpass){
		
		Vector<CarConfirmBean> v = new Vector<CarConfirmBean>();
		
		//조회한 예약정보들 중 하나의 예약정보만 저장 할 CarConfirmBean객체를 담을 변수
		CarConfirmBean bean = null;
		
		try{
			//DB연결
			getCon();
			
			//예약한 날짜가 현재날짜보다 크고,
			//예약시 입력한  비회원 전화번호와 비밀번호에 해당하는 렌트 예약 정보를  조회 하는데..
			//carorder테이블과 carlist테이블을 natural join하여 조회!
			//-> 참고! 예약한 날짜 는 String 타입이므로 Date타입으로 변경 했다!
			String sql = "select * from carorder natural join carlist where "
					   + "now() < str_to_date(carbegindate, '%Y-%m-%d') and "
					   + "memberphone=? and memberpass=?";
					
			//참고!
			//SELECT 문에 * 와 같이 별도의 컬럼 순서를 지정하지 않으면..
			//natural join의 기준이 되는 컬럼들이 다른 테이블의 컬럼보다 먼저 출력된다.
			//이때 natural join은 join에 사용된 같은 이름의 컬럼을 중복출력하지 않고 하나로 처리 한다.
			
			//SELECT구문을 DB에 전송 하여 실행할  PreparedStatement객체 얻기
			pstmt = con.prepareStatement(sql);
			//?기호에 대응되는 값 설정
			pstmt.setString(1, memberphone);
			pstmt.setString(2, memberpass);
			
			//위 SELECT구문을 DB에 전송 하여 실행후 조회된 예약정보들을 ResultSet객체에 담아 받아옴
			rs = pstmt.executeQuery();
			
			//ResultSet객체에 저장된 조회한 정보들을 한줄 단위로 꺼내어서.. CarConfirmBean객체의 각변수에저장함
			//그리고 CarCaonfirmBean객체들을 Vector에 추가 하여 최종 저장함
			while (rs.next()) {
				
				bean = new CarConfirmBean(); //VO
				bean.setOrderid(rs.getInt(2)); //대여한 예약 id
				bean.setCarqty(rs.getInt(3));//대여수량
				bean.setCarreserveday(rs.getInt("carreserveday"));//대여기간
				bean.setCarbegindate(rs.getString(5));//대여시작 날짜
				bean.setCarins(rs.getInt(6));//보험 적용 여부 
				bean.setCarwifi(rs.getInt(7));//WIFI적용 여부
				bean.setCarnave(rs.getInt(8));//네비게이션 적용 여부
				bean.setCarbabyseat(rs.getInt(9));//베이비시트 적용 여부
				bean.setCarname(rs.getString(12));//예약한 차량 이름 
				bean.setCarprice(rs.getInt(14));//렌트한 차량의 한대당 가격
				bean.setCarimg(rs.getString(17));//렌트한 차량의 이미지명 
				
				//Vector에 CarConfirmBean추가
				v.add(bean);
				
			}//반복문

		}catch(Exception exception){
			System.out.println("getAllCarOrder메소드 내부에서 SQL실행 오류:" + exception);
		}finally{
			//자원해제 
			try {
				if( pstmt != null)  pstmt.close();
				if( rs != null)  rs.close();
				if( con != null)  con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		}		
		
		return v;//현재 getAllCarOrder메소드를 호출한 곳(CarReserveConfirmController)으로 
		         //백터(Model을)를 리턴!
	}//getAllCarOrder메소드
		
	
	//예약 id를 매개변수로 전달 받아.. 예약 id에 해당 하는 예약정보를 조회 하는 메소드
	public CarConfirmBean getOneOrder(int orderid){
		
		CarConfirmBean cbean = null;
		
		try{
			//DB연결
			getCon();
			//예약 id에 해당하는 예약 정보 조회 SQL 만들기
			//SQL실행할 PreparedStatement 객체 얻기
			pstmt = con.prepareStatement("select * from carorder where orderid=?");
			//? 값 설정
			pstmt.setInt(1, orderid);
			//위 SQL문 실행후 조회된 결과 얻기
			rs = pstmt.executeQuery();
			
			rs.next();//커서 위치를 조회한 행의 줄로 내려주고!
			
			cbean = new CarConfirmBean(); //조회한 예약 정보를 저장할 공간
			cbean.setOrderid(orderid);//예약 id
			cbean.setCarbegindate(rs.getString(5));//예약한 날짜 
			cbean.setCarreserveday(rs.getInt(4));//예약 기간(대여기간)
			cbean.setCarins(rs.getInt(6)); //보험 적용 여부값
			cbean.setCarwifi(rs.getInt(7));//WIFI적용 여부값
			cbean.setCarnave(rs.getInt(8));//네비 적용 여부 값
			cbean.setCarbabyseat(rs.getInt(9));//베이비시트 적용 여부값			
					
		}catch(Exception exception){
			System.out.println("getOneOrder메소드 내부에서 SQL실행 오류 : " + exception);
		}finally{
			//자원해제 
			try {
				if( pstmt != null)  pstmt.close();
				if( rs != null)  rs.close();
				if( con != null)  con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		return cbean;//조회한 하나의 예약 정보를 담고 있는 CarConfirmBean객체를 서블릿으로 리턴
	}
	
	
	//수정할 정보들을 매개변수로 전달 받아 DB에 UPDATE 하는 메소드
	public int carOrderUpdate(CarOrderBean bean){
		
		int result = 0; //수정에 성공하면 1을 저장 실패하면 0을 저장 할 용도 
		
		try{
			//DB연결
			getCon();
			//UPDATE구문 : 매개변수로 전달 받는 입력한 비밀번호와 예약 아이디에 해당 하는 예약 정보 수정
			String sql = "update carorder set carbegindate=?, carreserveday=?, carqty=?,"
					   + "carins=?, carwifi=?, carbabyseat=? where orderid=? and memberpass=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getCarbegindate());
			pstmt.setInt(2, bean.getCarreserveday());
			pstmt.setInt(3, bean.getCarqty());
			pstmt.setInt(4, bean.getCarins());
			pstmt.setInt(5, bean.getCarwifi());
			pstmt.setInt(6, bean.getCarbabyseat());
			pstmt.setInt(7, bean.getOrderid());
			pstmt.setString(8, bean.getMemberpass());
			
			result = pstmt.executeUpdate(); //UPDATE구문 실행
			
		}catch(Exception exception){
				
			System.out.println("carOrderUpdate메소드 내부에서 SQL실행 오류 : " + exception);
			
		}finally{
			//자원해제 
			try {
				if( pstmt != null)  pstmt.close();
				if( con != null)  con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		
		return result;//업데이트에 성공 했느냐 1      하지 않았느냐  0   를 컨트롤러로 리턴 
	}

	//예약 id와  입력한 비밀번호를 매개변수로 전달 받아.. 예약 정보 하나를 삭제함
	//삭제에 성공하면 CarConfirmDeleteController로  1을 리턴 , 실패하면 0을 리턴!
	public int carOrderDelete(int orderid, String memberpass) {
		
		int result = 0;
		
		try{
			//DB연결
			getCon();
			String sql = "DELETE FROM carorder where orderid=? and memberpass=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderid);
			pstmt.setString(2, memberpass);
			//삭제DELETE에 성공 하면 1을 리턴 , 실패하면 0을 리턴
			result = pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("carOrderDelete메소드 내부에서 SQL실행 오류 : " + e);
		}finally{
			//자원해제 
			try {
				if( pstmt != null)  pstmt.close();
				if( con != null)  con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		return result;
	}
		
}//CarDAO클래스 끝








