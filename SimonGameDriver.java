/**
 * @author Justin Baraniuk
 */

import javax.swing.JOptionPane;      
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner; 

public class SimonGameDriver {
	
	public static void main(String[] args) {
		
		ArrayList<String> computer = new ArrayList<>();
		ArrayList<String> player;
		Scanner tokenize;
		int score = 0;
		boolean again = true;
		String guess;
		
		do {
			// computer's next random color
			computer.add(computer.size(), nextColour());
			
			// display color sequence
			Iterator<String> iter = computer.iterator();
			while (iter.hasNext()) {
				String element = iter.next();
				JOptionPane.showMessageDialog(null, element);
			}
			
			guess = JOptionPane.showInputDialog("");

			try {
				if (guess == null) 
					throw new Exception();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Game cancelled.");
				System.exit(0);
			} 
			
			// get tokens from user input
			player = new ArrayList<>();
			tokenize = new Scanner(guess);
			while (tokenize.hasNext()) 
				player.add(player.size(), tokenize.next());
			tokenize.close();
			
			// compare player and computer selections
			if (player.equals(computer))
				score++;
			else 
				again = false;
			
		} while(again == true);
		
		JOptionPane.showMessageDialog(null, "Game over! Your score is " + score + ".");		
	}
	
	public static String nextColour() {	
		Random r = new Random();
		int num = r.nextInt(4) + 1;
		switch (num) {
			case 1: return "red"; 
			case 2: return "green";
			case 3: return "blue";
			default: return "yellow";
		}	
	}
}
