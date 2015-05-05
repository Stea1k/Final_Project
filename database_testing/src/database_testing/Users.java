package database_testing;

public class Users {
	private String userName;
	private String userType;
	private String userPass;
	private String userPhone;
	
	
	protected String getUserName(){
		return this.userName;
	}
	
	protected String getUserType(){
		return this.userType;
	}
	
	protected String getUserPass(){
		return this.userPass;
	}
	
	protected String getUserPhone(){
		return this.userPhone;
	}
	
	protected void setUserName(String UN){
		this.userName = UN;
	}
	
	protected void setUserType(String UT){
		this.userType = UT;
	}	
	
	protected void setUserPass(String UP){
		this.userPass = UP;
	}
	
	protected void setUserPhone(String UPh){
		this.userPhone = UPh;
	}
	
	public Users(String name, String pass, String phone){
		this.setUserName(name);
		this.setUserPass(pass);
		this.setUserPhone(phone);
	}
}
