import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("Welcome to a simplified version of the game BLACK-JACK");
		outer: while(true) {
			System.out.println("Would you like to start a new game? (YES / NO)");
			String ans = in.nextLine();
			if (!ans.equalsIgnoreCase("YES"))
				break;
			Player p = new Player();
			Dealer d = new Dealer();
			
			System.out.println("You get a " + p.getFirstCard() + " and a " + p.getSecondCard());
			System.out.println("Your total is " + p.getSum());
			System.out.println("\nThe dealer has a " + d.getF_Cards("public") + " and a hidden card");
			
			while (true) {
				System.out.println("Would you like to draw? (YES / NO)");
				ans = in.nextLine();
				if (!ans.equalsIgnoreCase("YES")) {
					break;
				}
				System.out.println("You drew a " + p.draw());
				System.out.println("Your total is " + p.getSum());
				if (p.over21()) {
					System.out.println("\nYou have surpassed 21");
					System.out.println("\n===========\nDEALER WINS\n===========\n");
					continue outer;
				}
			}
			
			System.out.println("\nDealer is playing...");
			System.out.println("His secret card was " + d.getF_Cards("secret"));
			while (true) {
				if (d.drawCondition() != true) {
					System.out.println("\nDealer chooses to stay");
					break;
				}
				else {
					System.out.println("\nDealer chooses to draw");
					System.out.println("He draws a " + d.draw());
					System.out.println("His total is " + d.getSum());
					if (d.over21()) {
						System.out.println("\nDealer has surpassed 21");
						System.out.println("\n=======\nYOU WIN\n=======\n");
						continue outer;
					}
				}
			}
			// Results
			System.out.println("\nDealer total is " + d.getSum() + ".");
			System.out.println("Your total is " + p.getSum() + ".");
			if (p.playerWon(d))
				System.out.println("\n=======\nYOU WIN\n=======\n");
			else
				System.out.println("\n===========\nDEALER WINS\n===========\n");
		}
		System.out.println("~It's only a gambling problem if I'm losing~");
		in.close();
	}

}
