package net.member.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	//�������
	//������
	//�޼ҵ�
	//��񿬰� �ϴ¸޼ҵ�()
	private Connection getConnection() throws Exception{
		Connection con=null;
		Context init=new InitialContext();
		DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/jspbeginner");
		con=ds.getConnection();
		return con;
	}
	
	public boolean insertMember(MemberBean mb){
		// ��� �����ϴ� ���α׷� ��ġ (JDBC)
		// JSP2 - WebContent - WEB-INF - lib - 
		//   mysql-connector-java-5.1.39-bin.jar
//		     com���� mysql ���� jdbc ����   Driver.java
		Connection con=null;
		String sql="";
		PreparedStatement pstmt=null;
		
		int result = 0; //ȸ������ ��������
		try{
			//���ܰ� �߻��� ��ɹ�
			// 1�ܰ� ����̹��δ�	// 2�ܰ� ��񿬰� => �������� ����
			con=getConnection();
			//out.println("���Ἲ��");
			// 3�ܰ� sql ��ü ����
			// Statement  PreparedStatement CallableStatement
			//String sql="insert into ���̺��̸�(���̸�,...) values(��,��)";
			sql="insert into member2(id,pass,name,date,email,address,phone,mobile) "
			+ "values(?,?,?,?,?,?,?,?)";
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1,mb.getId()); //1 ����ǥ ��ġ 
			pstmt.setString(2,mb.getPass()); //2 ����ǥ ��ġ
			pstmt.setString(3,mb.getName()); //3 ����ǥ ��ġ
			pstmt.setTimestamp(4,mb.getDate()); //5 ����ǥ ��ġ
			pstmt.setString(5, mb.getEmail());
			pstmt.setString(6, mb.getAddress());
			pstmt.setString(7, mb.getPhone());
			pstmt.setString(8, mb.getMobile());
			// 4�ܰ� ����
			result = pstmt.executeUpdate(); //ȸ������ �����ϸ� 1����, ���н�0����
			
			//���� ȸ�����Կ� �����ϸ�.. true����
			if(result != 0){
				return true;
			}
		}catch(Exception e){
			//����ó��
			e.printStackTrace();
		}finally{
			//���ܻ������ ������ �۾�
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		
		return false; //ȸ������ ���н�! false����
		
	}// insertMember()�޼ҵ�
	
	/*�α��� ó����.. ����ϴ� �޼ҵ�*/
	//login.jsp���� ����ڷκ��� �Է¹��� id,passwd���� DB�� �ִ� id,passwd���� Ȯ���Ͽ� �α���ó��
	public int userCheck(String id,String pass){
		Connection con=null;
		String sql="";
		PreparedStatement pstmt=null;
		int check=-1;//1 -> ���̵�, ��й�ȣ ����
					//0 -> ���̵� ����, ��й�ȣ Ʋ��
					//-1 -> ���̵� Ʋ��
		ResultSet rs=null;
		try {
			//1�ܰ� ����̹��δ�
			//2�ܰ� ��񿬰�
			con=getConnection();
			//3�ܰ� sql : id�� �ش��ϴ� passwd ��������
			sql="select pass from member2 where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			//4�ܰ� rs = ����
			rs=pstmt.executeQuery();
			//5�ܰ� rs ù��°�� �̵��Ͽ�.. id�� �ش��ϴ� �����Ͱ� pass�� ������.
			if(rs.next()){
				//�α��ν�.. �Է��� pass��  DB�� ����Ǿ� �ִ� pass�� ������
				if(pass.equals(rs.getString("pass"))){
					check=1;//���̵� ����,��й�ȣ ���� �Ǻ��� ����
				//��й�ȣ�� Ʋ����
				}else{
					check=0;//���̵� ����, ��й�ȣƲ�� �Ǻ��� ����
				}
			//id�� �ش��ϴ� �����Ͱ� pass�� ������(���̵� ���ٴ� ��� ����)	
			}else{
				check=-1; //���̵� ���� �Ǻ��� ����
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//������
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		return check;
	}
	
	//getMember(id)
	public MemberBean getMember(String id){
		Connection con=null;
		String sql="";
		PreparedStatement pstmt=null;
		int check=-1;
		ResultSet rs=null;
		MemberBean mb=null;
		try {
			//1 ����̹��δ�
			//2 ��񿬰�
			con=getConnection();
			//3 sql  ���� id=? �ش��ϴ� ��� ������ ��������
			sql="select * from member2 where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			//4 rs =���� ����
			rs=pstmt.executeQuery();
			//5 rs ù��° �� �̵� ������������  mb ��ü����
			//   rs => mb ������� id ����
			if(rs.next()){
				mb=new MemberBean();
				mb.setId(rs.getString("id"));
				mb.setPass(rs.getString("pass"));
				mb.setName(rs.getString("name"));
				mb.setAge(rs.getInt("age"));
				mb.setDate(rs.getTimestamp("date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		return mb;
	}
	
	// getMemberList()
	public List getMemberList(){
		Connection con=null;
		String sql="";
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List memberList=new ArrayList();
		try {
			//1,2
			con=getConnection();
			//3 sql  member���̺� ��� ������ ��������
			sql="select * from member2";
			pstmt=con.prepareStatement(sql);
			//4 rs ���� ����
			rs=pstmt.executeQuery();
			//5 while rs ù��°��  MemberBean��ü ���� mb
			//   mb ������� id <= rs.  id
			//   �迭��ĭ ����  memberList.add(mb)
			while(rs.next()){
				MemberBean mb=new MemberBean();
				mb.setId(rs.getString("id"));
				mb.setPass(rs.getString("pass"));
				mb.setName(rs.getString("name"));
				mb.setAge(rs.getInt("age"));
				mb.setDate(rs.getTimestamp("date"));
				memberList.add(mb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		return memberList;
	}
	
	
}//Ŭ����
