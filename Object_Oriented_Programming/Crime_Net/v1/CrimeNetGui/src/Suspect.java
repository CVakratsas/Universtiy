import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

public class Suspect {
	private String name;
	private String codeName;
	private String city;
	private ArrayList<String> phoneNumbersInUse = new ArrayList<>();
	private ArrayList<Suspect> partners = new ArrayList<>();
	private ArrayList<Suspect> suggestedPartners = new ArrayList<>();
	private SuspectNameComparator nameComparator = new SuspectNameComparator();
	
	class SuspectNameComparator implements Comparator<Suspect> {

		@Override
		public int compare(Suspect suspect1, Suspect suspect2) {
			String name1 = suspect1.getName();
			String name2 = suspect2.getName();
			
			return name1.compareToIgnoreCase(name2);
		}
	}
	
	// Constructor
	public Suspect(String aName, String aCodeName, String aCity) {
		this.name = aName;
		this.codeName = aCodeName;
		this.city = aCity;
	}
	
	// Οι ύποπτοι που ψάχνουμε είναι η διαφορά του συνόλου των συνεργατών από τους συνεργάτες των συνεργατών του υπόπτου
	public void suggestedSuspects() {
		TreeSet<Suspect> suggestedPartnersToAdd = new TreeSet<>();
		for(Suspect partner: partners) {
			suggestedPartnersToAdd.addAll(this.differenceOf(partner));
		}
		suggestedPartners.addAll(suggestedPartnersToAdd);
	}
	
	public TreeSet<Suspect> differenceOf(Suspect partner) {
		TreeSet<Suspect> partnersSet = new TreeSet<>(partners);
		TreeSet<Suspect> partnersOfPartnerSet = new TreeSet<>(partner.getPartners());
		partnersOfPartnerSet.removeAll(partnersSet);
		partnersOfPartnerSet.remove(this); // Αφαιρείται ο ίδιος ο ύποπτος
		return partnersOfPartnerSet;
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
	
	// Sorted partners
	public ArrayList<Suspect> getPartners() {
		partners.sort(nameComparator);
		return partners;
	}
	
	public ArrayList<Suspect> getSortedSuggestedPartners() {
		suggestedPartners.sort(nameComparator);
		return suggestedPartners;
	}
	
}
