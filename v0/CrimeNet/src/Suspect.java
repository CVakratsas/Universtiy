import java.util.ArrayList;

public class Suspect {
	private String name;
	private String codeName;
	private String city;
	private ArrayList<String> phoneNumbersInUse = new ArrayList<>();
	private ArrayList<Suspect> partners = new ArrayList<>();
	
	//Constructor
	public Suspect(String aName, String aCodeName, String aCity) {
		this.name = aName;
		this.codeName = aCodeName;
		this.city = aCity;
	}
	
	public void addNumber(String phoneNumber) {
		phoneNumbersInUse.add(phoneNumber);
	}
	
	// Προστίθεται στην λίστα του υπόπτου ένας συνεργάτης και ο ίδιος ο ύποπτος στην λίστα των συνεργατών αυτού που προστέθηκε αρχικά
	public void addPartner(Suspect aPartner) {
		if(!(partners.contains(aPartner))) {
			this.partners.add(aPartner);
			aPartner.getPartners().add(this);
		}		
	}
	
	public boolean isConnectedTo(Suspect aSuspect) {
		for(Suspect s : partners) {
			if(s == aSuspect)
				return true;
		}
		return false;
	}
	
	public ArrayList<Suspect> getCommonPartners(Suspect aSuspect) {
		ArrayList<Suspect> commonPartners = new ArrayList<>();
		for(Suspect s: partners) {
			for(Suspect sus: aSuspect.getPartners()) {
				if(s == sus) {
					commonPartners.add(s);
					break;
				}
			}
		}
		return commonPartners;
	}
	
	public void printPartners() {
		for(Suspect s : partners) {
			System.out.println("Name: " + s.getName() + " | Code-Name: " + s.getCodeName());
		}
	}
	
	// Getters
	public String getName() {
		return name;
	}

	public String getCodeName() {
		return codeName;
	}

	public String getCity() {
		return city;
	}

	public ArrayList<String> getPhoneNumbersInUse() {
		return phoneNumbersInUse;
	}

	public ArrayList<Suspect> getPartners() {
		return partners;
	}
}
