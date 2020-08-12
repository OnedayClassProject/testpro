package db;

//MVC중에 Model
//예약할 차량에 대한 정보를 저장하는 자바빈 클래스 (VO)
public class CarOrderBean {

	private int orderid; //렌트(대여) id
	//렌트할 차량 차번호 저장
	private int carno; //->   차번호로 carList테이블과 carorder테이블을 조인 하여 검색 함.
	private int carqty;
	private int carreserveday;
	private String carbegindate;
	private int carins;
	private int carwifi;
	private int carnave;
	private int carbabyseat;
	
	//비회원으로 렌트할 사람의 폰번호 저장
	private String memberphone;
	//비회원으로 렌트시 예약 패스워드 저장 
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
	//getter,setter메소드 
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






