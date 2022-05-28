package _03_Hangman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
		
		String labelText = "";
		for (int i = 0; i < words.pop().length() ; i++) {
			labelText = labelText + "_";
		}

		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
