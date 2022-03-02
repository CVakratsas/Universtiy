
public class SMS extends Communication{
	private String SMSContent;

	//Constructor
	public SMS(String phoneNumber1, String phoneNumber2, int day, int month, int year, String anSMSContent) {
		super(phoneNumber1, phoneNumber2, day, month, year);
		this.SMSContent = anSMSContent;
	}

	// override method printInfo from parent Class
	public void printInfo() {
		System.out.println("This SMS has the following info");
		super.printInfo();
		System.out.println("Text: " + SMSContent);
	}

	public String getSMSContent() {
		return SMSContent;
	}
}
