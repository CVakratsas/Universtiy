
public class PhoneCall extends Communication{
	private int phoneCallTimeInMinutes;

	//Constructor
	public PhoneCall(String phoneNumber1, String phoneNumber2, int day, int month, int year, int aPhoneCallTimeInMinutes) {
		super(phoneNumber1, phoneNumber2, day, month, year);
		this.phoneCallTimeInMinutes = aPhoneCallTimeInMinutes;
	}
	
	// override method printInfo from parent Class
	public void printInfo() {
		System.out.println("This phone call has the following info");
		super.printInfo();
		System.out.println("Duration: " + phoneCallTimeInMinutes);
	}
	
	public int getPhoneCallTimeInMinutes() {
		return phoneCallTimeInMinutes;
	}
}
