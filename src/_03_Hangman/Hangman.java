package _03_Hangman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Hangman implements KeyListener {
	JFrame frame;
	JPanel panel;
	JLabel label;
	Stack<String> words = new Stack<String>();
	int length;
	String word;
	String labelText;
	char[] guess;
	int lives;
	
	public static void main(String[] args) {
		Hangman t = new Hangman();
		t.setup();
	}
	
	void setup() {
		//User Input, Number of Words
		String input = JOptionPane.showInputDialog("Enter the number of words to guess (1-100)");
		int numOfWords = Integer.parseInt(input);
		
		for (int i = 0; i < numOfWords; i++) {
			words.push(Utilities.readRandomLineFromFile("dictionary.txt"));
			
		}
		
		//Create Window
		frame = new JFrame();
		panel = new JPanel();
		label = new JLabel();
		
		frame.setVisible(true);
		frame.addKeyListener(this);
		frame.add(panel);
		panel.add(label);
		
		labelText = "";
		lives = 5;
		
		word = words.pop();
		System.out.println(word);
		length = word.length();
		
		guess = new char[length];
		
		for (int i = 0; i < length ; i++) {
			guess[i] = '_';
		}
		System.out.println(guess);
		labelText = String.valueOf(guess);
		
		label.setText(labelText+ "  Lives:" + lives);
		
		

		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		char letter = e.getKeyChar();
		boolean hasGuessedLetter = false;
		int num = 0;
		
		for (int i = 0; i < guess.length; i++) {
			//System.out.println(word.charAt(i));
			if(word.charAt(i)==letter) {
				guess[i]=letter;
				System.out.println(letter);
				hasGuessedLetter = true;
			}

			
		}
		if(hasGuessedLetter == false) {
			lives = lives - 1;
		}

		
		labelText = String.valueOf(guess);
		
		label.setText(labelText+ "  Lives:" + lives);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
