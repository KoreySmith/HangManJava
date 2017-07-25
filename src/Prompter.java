import java.util.Scanner;

public class Prompter {
	private Game game;
	
	public Prompter(Game game) {
		this.game = game;
	}
	
	public boolean promptForGuess() {
		Scanner scanner = new Scanner(System.in);
		boolean isHit = false;
		boolean isAcceptable = false;
		
		do {
			System.out.print("Enter A Letter:  ");
			String guessInput = scanner.nextLine();
			
			try {
				isHit = game.applyGuess(guessInput);
				isAcceptable = true;
			} catch(IllegalArgumentException iae) {
				System.out.printf("%sPlease try again. %n"
						,iae.getMessage());
			}
		} while(! isAcceptable);
		return isHit;
	}
	
	public void displayProgress() {
		System.out.printf("You have %d tries left to solve: %s%n",
				           game.getRemainingTries(),			
				           game.getCurrentProgress());
	}
	
	public void displayOutcome() {
		if (game.isWon() == true) {
			System.out.printf("You have Won!\nYou solved the word '%s', with %d tries remaining!%n",
								game.getAnswer(),
								game.getRemainingTries());
		}
		if(game.getRemainingTries() == 0 && !game.isWon()) {
			System.out.printf("Sorry you lost!\nYou Failed to solve the word '%s'!%n",
								game.getAnswer());
		}
	}
}
