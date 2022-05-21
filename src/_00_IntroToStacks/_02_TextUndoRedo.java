package _00_IntroToStacks;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class _02_TextUndoRedo implements KeyListener {
    /* 
     * Create a JFrame with a JPanel and a JLabel.
     * 
     * 
     * Every time a key is pressed, add that character to the JLabel. It should
     * look like a basic text editor.
     * 
     * Make it so that every time the BACKSPACE key is pressed, the last
     * character is erased from the JLabel.
     * 
     * Save that deleted character onto a Stack of Characters.
     * 
     * Choose a key to be the Undo key. Make it so that when that key is
     * pressed, the top Character is popped  off the Stack and added back to
     * the JLabel.
     */
	
	JFrame frame;
	JPanel panel;
	JLabel label;
	String text = "";
	Stack<String> undo;
	char letter;
	public static void main(String[] args) {
		_02_TextUndoRedo t = new _02_TextUndoRedo();
		t.setup();
		

		
	}
	
	void setup() {
		frame = new JFrame();
		panel = new JPanel();
		label = new JLabel();
        undo = new Stack<String>();

		
		frame.setVisible(true);
		frame.addKeyListener(this);
		frame.add(panel);
		panel.add(label);
		label.setHorizontalAlignment(JLabel.LEFT);
		label.setText(text);
		
		frame.pack();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		letter = e.getKeyChar();
		

		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			text = text+undo.pop();
			label.setText(text);
		} 
		else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			undo.push(text.substring(text.length()-1, text.length()));
			text = text.substring(0, text.length()-1);
			label.setText(text);
		}
		else {
			text = text+letter;
			label.setText(text);
		}
		frame.pack();
		frame.repaint();
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}
