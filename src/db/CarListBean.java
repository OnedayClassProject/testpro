package db;


//MVC -> M역할    VO클래스의 역할을 함
public class CarListBean {

	private int carno;//차번호 
	private String carname;//차이름
	private String carcompany;//차량 제조사명
	private int carprice;//한대당 렌트 가격
	private int carusepeople;//차인승정보
	private String carinfo;//차정보
	private String carimg;//차량 이미지명
	private String carcategory;//소형,중형,대형
	
	//getter,setter메소드
	public int getCarno() {
		return carno;
	}
	public void setCarno(int carno) {
		this.carno = carno;
	}
	public String getCarname() {
		return carname;
	}
	public void setCarname(String carname) {
		this.carname = carname;
	}
	public String getCarcompany() {
		return carcompany;
	}
	public void setCarcompany(String carcompany) {
		this.carcompany = carcompany;
	}
	public int getCarprice() {
		return carprice;
	}
	public void setCarprice(int carprice) {
		this.carprice = carprice;
	}
	public int getCarusepeople() {
		return carusepeople;
	}
	public void setCarusepeople(int carusepeople) {
		this.carusepeople = carusepeople;
	}
	public String getCarinfo() {
		return carinfo;
	}
	public void setCarinfo(String carinfo) {
		this.carinfo = carinfo;
	}
	public String getCarimg() {
		return carimg;
	}
	public void setCarimg(String carimg) {
		this.carimg = carimg;
	}
	public String getCarcategory() {
		return carcategory;
	}
	public void setCarcategory(String carcategory) {
		this.carcategory = carcategory;
	}

	
	
}


