
public abstract class Communication {
	private String phoneNumber1;
	private String phoneNumber2;
	private int year;
	private int month;
	private int day;
	
	//Constructor
	public Communication(String phoneNumber1, String phoneNumber2, int day, int month, int year) {
		this.phoneNumber1 = phoneNumber1;
		this.phoneNumber2 = phoneNumber2;
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	public void printInfo() {
		System.out.println("Between " + phoneNumber1 + " --- " + phoneNumber2);
		System.out.println("on " + year + "/" + month + "/" + day);
	}
	
	// Getters
	public String getPhoneNumber1() {
		return phoneNumber1;
	}

	public String getPhoneNumber2() {
		return phoneNumber2;
	}
}
