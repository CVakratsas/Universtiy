import java.util.Random;

public class Dealer {
	Random ran = new Random();
	private int public_card;
	private int secret_card;
	private int sum;
	
	public Dealer() {
		public_card = ran.nextInt(10) + 2;
		secret_card = ran.nextInt(10) + 2;
		if ((public_card == 11) && (secret_card == 11)) // Two aces drawn
			sum = 21;
		else
			sum = public_card + secret_card;
	}
	
	public int draw() {
		int next = ran.nextInt(10) + 2;
		sum += next;
		return next;
	}
	
	public boolean drawCondition() {
		return (sum <= 16);
	}
	
	public int getSum() {
		return sum;
	}
	
	public int getF_Cards(String text) {
		if (text == "public") {
			return public_card;
		}
		else if (text == "secret") {
			return secret_card;
		}
		else {
			System.out.println("Wrong text given");
			return -999;
		}
	}
	
	public boolean over21() {
		return (sum > 21);
	}
}
