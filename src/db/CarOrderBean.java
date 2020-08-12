package db;

//MVC�߿� Model
//������ ������ ���� ������ �����ϴ� �ڹٺ� Ŭ���� (VO)
public class CarOrderBean {

	private int orderid; //��Ʈ(�뿩) id
	//��Ʈ�� ���� ����ȣ ����
	private int carno; //->   ����ȣ�� carList���̺�� carorder���̺��� ���� �Ͽ� �˻� ��.
	private int carqty;
	private int carreserveday;
	private String carbegindate;
	private int carins;
	private int carwifi;
	private int carnave;
	private int carbabyseat;
	
	//��ȸ������ ��Ʈ�� ����� ����ȣ ����
	private String memberphone;
	//��ȸ������ ��Ʈ�� ���� �н����� ���� 
	private String memberpass;
	
	
   public CarOrderBean() {
	
   }   

   public CarOrderBean(int orderid, 
					   int carqty, 
					   int carreserveday, 
					   String carbegindate, 
					   int carins, 
					   int carwifi,
					   int carbabyseat, 
					   String memberpass) {
		this.orderid = orderid;
		this.carqty = carqty;
		this.carreserveday = carreserveday;
		this.carbegindate = carbegindate;
		this.carins = carins;
		this.carwifi = carwifi;
		this.carbabyseat = carbabyseat;
		this.memberpass = memberpass;
	}
	//getter,setter�޼ҵ� 
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getCarno() {
		return carno;
	}
	public void setCarno(int carno) {
		this.carno = carno;
	}
	public int getCarqty() {
		return carqty;
	}
	public void setCarqty(int carqty) {
		this.carqty = carqty;
	}
	public int getCarreserveday() {
		return carreserveday;
	}
	public void setCarreserveday(int carreserveday) {
		this.carreserveday = carreserveday;
	}
	public String getCarbegindate() {
		return carbegindate;
	}
	public void setCarbegindate(String carbegindate) {
		this.carbegindate = carbegindate;
	}
	public int getCarins() {
		return carins;
	}
	public void setCarins(int carins) {
		this.carins = carins;
	}
	public int getCarwifi() {
		return carwifi;
	}
	public void setCarwifi(int carwifi) {
		this.carwifi = carwifi;
	}
	public int getCarnave() {
		return carnave;
	}
	public void setCarnave(int carnave) {
		this.carnave = carnave;
	}
	public int getCarbabyseat() {
		return carbabyseat;
	}
	public void setCarbabyseat(int carbabyseat) {
		this.carbabyseat = carbabyseat;
	}
	public String getMemberphone() {
		return memberphone;
	}
	public void setMemberphone(String memberphone) {
		this.memberphone = memberphone;
	}
	public String getMemberpass() {
		return memberpass;
	}
	public void setMemberpass(String memberpass) {
		this.memberpass = memberpass;
	}

	
	
	
}






