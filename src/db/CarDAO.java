package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//Ŀ�ؼ�Ǯ�� �̿��Ͽ� �����ͺ��̽��� �����Ͽ� �����͸� �Է� ���� ���� �˻��Ҽ� �ִ� DAOŬ����
//MVC�߿�... Model�� ������ ��
public class CarDAO { //����
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//DB�� �̸� ������ ���� Connection��ü ���� �����  DataSource(Ŀ�ؼ�Ǯ)�� ���,
	//DataSource������ Connection��ü�� ��� ���� �޼ҵ�
	public void getCon(){
		try{
			//1.WAS������ ����� CarProject��������Ʈ�� ��� ������ ������ �ִ�   ���ؽ�Ʈ��ü ����
			Context init = new InitialContext();
			
			//2.����� WAS��������  DataSource(Ŀ�ؼ�Ǯ) �˻��ؼ� �������� 
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/jspbeginner");
			
			//DataSource(Ŀ�ؼ�Ǯ)����  DB������ü (Ŀ�ؼ�) ��������  
			con = ds.getConnection(); //DB����
			
		}catch(Exception err){
			System.out.println("Ŀ�ؼ�Ǯ �� Ŀ�ؼ� ��� ����  : " + err);
		}
	}
	
	//DB�� ����� ��ü ���� �˻� �޼ҵ�
	public Vector<CarListBean> getAllCarList(){
		
		//��ü ���� ������  ��� �˻��ؼ� �����ͼ�
		//�˻��� ������.. ���� ������ CarListBean��ü�� ���� ������
		//CarListBean��ü���� Vector�迭�� ���� �ϱ� ���� ���� 
		Vector<CarListBean> v = new Vector<CarListBean>();
		
		//�˻��� ���� �ϳ��� ������? ������ �뵵�� CarListBean�� ������ �������� 
		CarListBean bean = null;
		
		try {
			//Connection��� (DB����)
			getCon();
			//SQL�� �غ�  : ��ü ���� �˻�
			String sql = "select * from carlist";
			//SQL�� ������ ��ü
			pstmt = con.prepareStatement(sql);
			//SQL�� �������� �˻��� ��ü ���� ������ ResultSet�� ��� ���
			rs = pstmt.executeQuery();
			
			//�ݺ����� �����  ResultSet��ü������ ����� �˻��� ��� ���� �������� ����� 
			//CarListBean��ü�� ���������� ���� �� 
			//CarListBean��ü���� Vectory�迭�� ���� �߰� �Ͽ� ����
			while(rs.next()){
				
				bean = new CarListBean();
				bean.setCarno(rs.getInt(1)); //�˻��� ����ȣ 
				bean.setCarname(rs.getString(2));//�˻��� ���̸�
				bean.setCarcompany(rs.getString("carcompany"));//�˻��� ��������
				bean.setCarprice(rs.getInt(4));//�Ѵ�� ��Ʈ ����
				bean.setCarusepeople(rs.getInt("carusepeople"));//�� �ν�����
				bean.setCarinfo(rs.getString(6));//�� ���� 
				bean.setCarimg(rs.getString(7));//�� �̹��� �� 
				bean.setCarcategory(rs.getString("carcategory"));//������(����, ����, ����)
				
				v.add(bean); 
				
			}	
		} catch (Exception e) {
			System.out.println("getAllCarlist�޼ҵ� ���ο��� SQL ���� ���� :" + e.toString() );
		} finally {
			//�ڿ����� 
			try {
				if( pstmt != null)  pstmt.close();
				if( rs != null)  rs.close();
				if( con != null)  con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}//finally
		
		return v; //���� ����
		
	}//getAllCarlist�޼ҵ� ��

	
	//���� ������  ���� ������ ����  ���� �˻�
	public Vector<CarListBean> getCartegoryCarList(String cartegory) {
		
		//���� ������  ���� ������  ��� �˻��ؼ� �����ͼ�
		//�˻��� ������.. ���� ������ CarListBean��ü�� ���� ������
		//CarListBean��ü���� Vector�迭�� ���� �ϱ� ���� ���� 
		Vector<CarListBean> v = new Vector<CarListBean>();
		
		//�˻��� ���� �ϳ��� ������? ������ �뵵�� CarListBean�� ������ �������� 
		CarListBean bean = null;
		
		try {
			//Connection��� (DB����)
			getCon();
			//SQL�� �غ�  : �Ű������� ���� ���� ���� ������ ������ �������� �˻�
			String sql = "select * from carlist where carcategory=?";
			//SQL�� ������ ��ü
			pstmt = con.prepareStatement(sql);
			//?����
			pstmt.setString(1, cartegory);
				
			//SQL�� �������� �˻��� ��ü ���� ������ ResultSet�� ��� ���
			rs = pstmt.executeQuery();
			
			//�ݺ����� �����  ResultSet��ü������ ����� �˻��� ��� ���� �������� ����� 
			//CarListBean��ü�� ���������� ���� �� 
			//CarListBean��ü���� Vectory�迭�� ���� �߰� �Ͽ� ����
			while(rs.next()){
				
				bean = new CarListBean();
				bean.setCarno(rs.getInt(1)); //�˻��� ����ȣ 
				bean.setCarname(rs.getString(2));//�˻��� ���̸�
				bean.setCarcompany(rs.getString("carcompany"));//�˻��� ��������
				bean.setCarprice(rs.getInt(4));//�Ѵ�� ��Ʈ ����
				bean.setCarusepeople(rs.getInt("carusepeople"));//�� �ν�����
				bean.setCarinfo(rs.getString(6));//�� ���� 
				bean.setCarimg(rs.getString(7));//�� �̹��� �� 
				bean.setCarcategory(rs.getString("carcategory"));//������(����, ����, ����)
				
				v.add(bean); 
				
			}	
		} catch (Exception e) {
			System.out.println("getCategoryCarList�޼ҵ� ���ο��� SQL ���� ���� :" + e.toString() );
		} finally {
			//�ڿ����� 
			try {
				if( pstmt != null)  pstmt.close();
				if( rs != null)  rs.close();
				if( con != null)  con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}//finally
		
		return v; //���� ����
	}

	//���� ������ ������ ���� ������ ��ȸ (���� ��ȣ�� �̿��ؼ� �� �˻�)
	public CarListBean getOneCar(int carno){
				
			//�˻��� ���� �ϳ��� ������? ������ �뵵�� CarListBean�� ������ �������� 
			CarListBean bean = null;
			
			try {
				//Connection��� (DB����)
				getCon();
				//SQL�� �غ�  : �Ű������� ���� ���� ���� ��ȣ�� �ش��ϴ�  ������ �˻�
				String sql = "select * from carlist where carno=?";
				//SQL�� ������ ��ü
				pstmt = con.prepareStatement(sql);
				//?����
				pstmt.setInt(1, carno);
					
				//SQL�� �������� �˻��� ��ü ���� ������ ResultSet�� ��� ���
				rs = pstmt.executeQuery();
				
				//�ݺ����� �����  ResultSet��ü������ ����� �˻��� ��� ���� �������� ����� 
				//CarListBean��ü�� ���������� ���� �� 
				//CarListBean��ü���� Vectory�迭�� ���� �߰� �Ͽ� ����
				if(rs.next()){
					
					bean = new CarListBean();
					bean.setCarno(rs.getInt(1)); //�˻��� ����ȣ 
					bean.setCarname(rs.getString(2));//�˻��� ���̸�
					bean.setCarcompany(rs.getString("carcompany"));//�˻��� ��������
					bean.setCarprice(rs.getInt(4));//�Ѵ�� ��Ʈ ����
					bean.setCarusepeople(rs.getInt("carusepeople"));//�� �ν�����
					bean.setCarinfo(rs.getString(6));//�� ���� 
					bean.setCarimg(rs.getString(7));//�� �̹��� �� 
					bean.setCarcategory(rs.getString("carcategory"));//������(����, ����, ����)
					
				}	
			} catch (Exception e) {
				System.out.println("getCategoryCarList�޼ҵ� ���ο��� SQL ���� ���� :" + e.toString() );
			} finally {
				//�ڿ����� 
				try {
					if( pstmt != null)  pstmt.close();
					if( rs != null)  rs.close();
					if( con != null)  con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}			
			}//finally
			
			return bean; //CarListBean��ü�� CarInfoController�� ����		
		
	}
	
	
	//���� �� ������ DB�� INSERT ��Ű�� �޼ҵ�
	public void insertCarOrder(CarOrderBean cbean){
		
		try {
			
			getCon(); //DB����
			
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
				
			pstmt.executeUpdate(); //insert����
				
		} catch (Exception e) {
			System.out.println("insertCarOrder���ο��� ���� : " + e);
		} finally {
			//�ڿ����� 
			try {
				if( pstmt != null)  pstmt.close();
				if( rs != null)  rs.close();
				if( con != null)  con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}	
	}
	
	//����Ȯ�� ���������� �Է��� ��ȸ�� ��ȭ��ȣ�� ��й�ȣ�� �ش��ϴ� �������� ��ȸ �ϴ� �޼ҵ�
	public Vector<CarConfirmBean>  getAllCarOrder(String memberphone, String memberpass){
		
		Vector<CarConfirmBean> v = new Vector<CarConfirmBean>();
		
		//��ȸ�� ���������� �� �ϳ��� ���������� ���� �� CarConfirmBean��ü�� ���� ����
		CarConfirmBean bean = null;
		
		try{
			//DB����
			getCon();
			
			//������ ��¥�� ���糯¥���� ũ��,
			//����� �Է���  ��ȸ�� ��ȭ��ȣ�� ��й�ȣ�� �ش��ϴ� ��Ʈ ���� ������  ��ȸ �ϴµ�..
			//carorder���̺�� carlist���̺��� natural join�Ͽ� ��ȸ!
			//-> ����! ������ ��¥ �� String Ÿ���̹Ƿ� DateŸ������ ���� �ߴ�!
			String sql = "select * from carorder natural join carlist where "
					   + "now() < str_to_date(carbegindate, '%Y-%m-%d') and "
					   + "memberphone=? and memberpass=?";
					
			//����!
			//SELECT ���� * �� ���� ������ �÷� ������ �������� ������..
			//natural join�� ������ �Ǵ� �÷����� �ٸ� ���̺��� �÷����� ���� ��µȴ�.
			//�̶� natural join�� join�� ���� ���� �̸��� �÷��� �ߺ�������� �ʰ� �ϳ��� ó�� �Ѵ�.
			
			//SELECT������ DB�� ���� �Ͽ� ������  PreparedStatement��ü ���
			pstmt = con.prepareStatement(sql);
			//?��ȣ�� �����Ǵ� �� ����
			pstmt.setString(1, memberphone);
			pstmt.setString(2, memberpass);
			
			//�� SELECT������ DB�� ���� �Ͽ� ������ ��ȸ�� ������������ ResultSet��ü�� ��� �޾ƿ�
			rs = pstmt.executeQuery();
			
			//ResultSet��ü�� ����� ��ȸ�� �������� ���� ������ �����.. CarConfirmBean��ü�� ��������������
			//�׸��� CarCaonfirmBean��ü���� Vector�� �߰� �Ͽ� ���� ������
			while (rs.next()) {
				
				bean = new CarConfirmBean(); //VO
				bean.setOrderid(rs.getInt(2)); //�뿩�� ���� id
				bean.setCarqty(rs.getInt(3));//�뿩����
				bean.setCarreserveday(rs.getInt("carreserveday"));//�뿩�Ⱓ
				bean.setCarbegindate(rs.getString(5));//�뿩���� ��¥
				bean.setCarins(rs.getInt(6));//���� ���� ���� 
				bean.setCarwifi(rs.getInt(7));//WIFI���� ����
				bean.setCarnave(rs.getInt(8));//�׺���̼� ���� ����
				bean.setCarbabyseat(rs.getInt(9));//���̺��Ʈ ���� ����
				bean.setCarname(rs.getString(12));//������ ���� �̸� 
				bean.setCarprice(rs.getInt(14));//��Ʈ�� ������ �Ѵ�� ����
				bean.setCarimg(rs.getString(17));//��Ʈ�� ������ �̹����� 
				
				//Vector�� CarConfirmBean�߰�
				v.add(bean);
				
			}//�ݺ���

		}catch(Exception exception){
			System.out.println("getAllCarOrder�޼ҵ� ���ο��� SQL���� ����:" + exception);
		}finally{
			//�ڿ����� 
			try {
				if( pstmt != null)  pstmt.close();
				if( rs != null)  rs.close();
				if( con != null)  con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		}		
		
		return v;//���� getAllCarOrder�޼ҵ带 ȣ���� ��(CarReserveConfirmController)���� 
		         //����(Model��)�� ����!
	}//getAllCarOrder�޼ҵ�
		
	
	//���� id�� �Ű������� ���� �޾�.. ���� id�� �ش� �ϴ� ���������� ��ȸ �ϴ� �޼ҵ�
	public CarConfirmBean getOneOrder(int orderid){
		
		CarConfirmBean cbean = null;
		
		try{
			//DB����
			getCon();
			//���� id�� �ش��ϴ� ���� ���� ��ȸ SQL �����
			//SQL������ PreparedStatement ��ü ���
			pstmt = con.prepareStatement("select * from carorder where orderid=?");
			//? �� ����
			pstmt.setInt(1, orderid);
			//�� SQL�� ������ ��ȸ�� ��� ���
			rs = pstmt.executeQuery();
			
			rs.next();//Ŀ�� ��ġ�� ��ȸ�� ���� �ٷ� �����ְ�!
			
			cbean = new CarConfirmBean(); //��ȸ�� ���� ������ ������ ����
			cbean.setOrderid(orderid);//���� id
			cbean.setCarbegindate(rs.getString(5));//������ ��¥ 
			cbean.setCarreserveday(rs.getInt(4));//���� �Ⱓ(�뿩�Ⱓ)
			cbean.setCarins(rs.getInt(6)); //���� ���� ���ΰ�
			cbean.setCarwifi(rs.getInt(7));//WIFI���� ���ΰ�
			cbean.setCarnave(rs.getInt(8));//�׺� ���� ���� ��
			cbean.setCarbabyseat(rs.getInt(9));//���̺��Ʈ ���� ���ΰ�			
					
		}catch(Exception exception){
			System.out.println("getOneOrder�޼ҵ� ���ο��� SQL���� ���� : " + exception);
		}finally{
			//�ڿ����� 
			try {
				if( pstmt != null)  pstmt.close();
				if( rs != null)  rs.close();
				if( con != null)  con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		return cbean;//��ȸ�� �ϳ��� ���� ������ ��� �ִ� CarConfirmBean��ü�� �������� ����
	}
	
	
	//������ �������� �Ű������� ���� �޾� DB�� UPDATE �ϴ� �޼ҵ�
	public int carOrderUpdate(CarOrderBean bean){
		
		int result = 0; //������ �����ϸ� 1�� ���� �����ϸ� 0�� ���� �� �뵵 
		
		try{
			//DB����
			getCon();
			//UPDATE���� : �Ű������� ���� �޴� �Է��� ��й�ȣ�� ���� ���̵� �ش� �ϴ� ���� ���� ����
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
			
			result = pstmt.executeUpdate(); //UPDATE���� ����
			
		}catch(Exception exception){
				
			System.out.println("carOrderUpdate�޼ҵ� ���ο��� SQL���� ���� : " + exception);
			
		}finally{
			//�ڿ����� 
			try {
				if( pstmt != null)  pstmt.close();
				if( con != null)  con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		
		return result;//������Ʈ�� ���� �ߴ��� 1      ���� �ʾҴ���  0   �� ��Ʈ�ѷ��� ���� 
	}

	//���� id��  �Է��� ��й�ȣ�� �Ű������� ���� �޾�.. ���� ���� �ϳ��� ������
	//������ �����ϸ� CarConfirmDeleteController��  1�� ���� , �����ϸ� 0�� ����!
	public int carOrderDelete(int orderid, String memberpass) {
		
		int result = 0;
		
		try{
			//DB����
			getCon();
			String sql = "DELETE FROM carorder where orderid=? and memberpass=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderid);
			pstmt.setString(2, memberpass);
			//����DELETE�� ���� �ϸ� 1�� ���� , �����ϸ� 0�� ����
			result = pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("carOrderDelete�޼ҵ� ���ο��� SQL���� ���� : " + e);
		}finally{
			//�ڿ����� 
			try {
				if( pstmt != null)  pstmt.close();
				if( con != null)  con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		return result;
	}
		
}//CarDAOŬ���� ��








