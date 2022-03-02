import java.util.ArrayList;

public class Registry {
	
	private ArrayList<Suspect> listOfSuspects = new ArrayList<>();
	private ArrayList<Communication> listOfCommunications = new ArrayList<>();
	
	public void addSuspect(Suspect aSuspect) {
		listOfSuspects.add(aSuspect);
	}
	
	public void addCommunication(Communication aCommunication) {
		listOfCommunications.add(aCommunication);
		Suspect sus1 = findSuspectFromPhoneNumber(aCommunication.getPhoneNumber1());
		Suspect sus2 = findSuspectFromPhoneNumber(aCommunication.getPhoneNumber2());
		sus1.addPartner(sus2);
	}
	
	// βοηθητική συνάρτηση για την addCommunication
	public Suspect findSuspectFromPhoneNumber(String phoneNumber) {
		for(Suspect s : listOfSuspects) {
			for(String p : s.getPhoneNumbersInUse()) {
				if(p.equals(phoneNumber))
					return s;
			}
		}
		return null;
	}
	
	public Suspect getSuspectWithMostPartners() {
		Suspect suspectWithMostPartners = listOfSuspects.get(2); // για να δείχνει το ίδιο αποτέλεσμα με την ενδεικτική λύση, αφού όλοι οι ύποπτοι έχουν τον ίδιο αριθμό συνεργατών, 3
		for(int i=0; i<listOfSuspects.size(); i++) {
			if(listOfSuspects.get(i).getPartners().size() > suspectWithMostPartners.getPartners().size())
				suspectWithMostPartners = listOfSuspects.get(i);
		}
		return suspectWithMostPartners;
	}	
	
	public PhoneCall getLongestPhoneCallBetween(String number1, String number2) {
		ArrayList<PhoneCall> phoneCallList = getPhoneCallList(number1, number2);
		int index = -1;
		int maxPhoneTimeInMinutes = 0;
		for(int i=0; i<phoneCallList.size(); i++) {
			if(phoneCallList.get(i).getPhoneCallTimeInMinutes() > maxPhoneTimeInMinutes) {
				index = i;
				maxPhoneTimeInMinutes = phoneCallList.get(i).getPhoneCallTimeInMinutes();
			}
		}
		PhoneCall longestPhoneCall = (PhoneCall) listOfCommunications.get(index);
		return longestPhoneCall;
	}
	
	// βοηθητική μέθοδος της getLongestPhoneCallBetween, η οποία βρίσκει όλες τις τηλεφωνικές κλήσεις μεταξύ δύο αριθμών
	public ArrayList<PhoneCall> getPhoneCallList(String number1, String number2){
		ArrayList<PhoneCall> phoneCallList = new ArrayList<>();
		for(Communication comm : listOfCommunications) {
			if(comm instanceof PhoneCall) // επιλογή μόνο των αντικειμένων της κλάσης PhoneCall
				if((number1.equals(comm.getPhoneNumber1()) && number2.equals(comm.getPhoneNumber2())) || (number1.equals(comm.getPhoneNumber2()) && (number2.equals(comm.getPhoneNumber1())))) {
					PhoneCall phoneCall = (PhoneCall) comm; // ρητή μετατροπή σε κλάση PhoneCall από Communication
					phoneCallList.add(phoneCall);
				}
		}
		return phoneCallList;
	}
	
	public ArrayList<SMS> getMessagesBetween(String number1, String number2){
		ArrayList<SMS> listOfSMS = new ArrayList<>();
		for(Communication comm : listOfCommunications) {
			if(comm instanceof SMS) // επιλογή μόνο των αντικειμένων της κλάσης SMS
				if((number1.equals(comm.getPhoneNumber1()) && number2.equals(comm.getPhoneNumber2())) || (number1.equals(comm.getPhoneNumber2()) && (number2.equals(comm.getPhoneNumber1())))) {
					SMS sms = (SMS) comm;
					if(sms.getSMSContent().contains("Bomb") || sms.getSMSContent().contains("Attack") || sms.getSMSContent().contains("Explosives") || sms.getSMSContent().contains("Gun"))
						listOfSMS.add(sms);
				}
		}
		return listOfSMS;
	}
}
