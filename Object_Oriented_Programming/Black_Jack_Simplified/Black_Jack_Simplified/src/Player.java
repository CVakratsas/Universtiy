import java.util.Random;
import java.util.Scanner;

public class Player {
	Random ran = new Random();
	Scanner in = new Scanner(System.in);
	private int sum;
	private int [] f_cards = new int[2]; // First two cards
	
	public Player() {
		f_cards[0] = ran.nextInt(10) + 2;
		f_cards[1] = ran.nextInt(10) + 2;
		if (f_cards[0] == 11 && f_cards[1] == 11) // Two aces drawn
			sum = 21;
		else
			sum = f_cards[0] + f_cards[1];
	}
	
	public int draw() {
		int card = ran.nextInt(10) + 2;
		sum += card;
		return card;
	}
	
	public boolean over21() {
		return (sum > 21);
	}
	
	public boolean playerWon(Dealer d) {
			return (sum > d.getSum());
	}
	
	public int getFirstCard() {
		return f_cards[0];
	}
	
	public int getSecondCard() {
		return f_cards[1];
	}
	
	public int getSum() {
		return sum;
	}
}
