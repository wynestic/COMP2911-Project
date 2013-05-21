import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
//
public class GameInterface {
	static JFrame frame;
	static Container pane;
	//The boxes themselves
	static JButton[][] box;
	//The smaller boxes which can be used to store possible numbers
	static JButton btnInputMode;
	static JLabel lblInputLabel;
	static JButton btnQuit;
	static Square[][] entry;
	
	
	static final int boxWidth = 50;
	static final int boxHeight = 50;
	//static final int subBoxHeight = 20;
	//static final int subBoxWidth = 50;
	static final int frameWidth = 600;
	static final int frameHeight = 600;
	
	public GameInterface(){
		frame = new JFrame("Sudoku");
		frame.setSize(frameWidth, frameHeight);
		pane = frame.getContentPane();
		
		pane.setLayout(null);
		
		box = new JButton[9][9];
		//Get it so that I take this from the puzzle
		entry = new Square[9][9];
		//subBox = new JTextPane[9][9];
		setStartingBoxInfo();
		
		
		btnInputMode = new JButton("Entry Mode");
		lblInputLabel = new JLabel("Current Writing Mode Click or hold Shift to change");
		pane.add(btnInputMode);
		pane.add(lblInputLabel);	
		btnInputMode.setBounds(10, frameHeight - 60, btnInputMode.getPreferredSize().width + 50, btnInputMode.getPreferredSize().height);
		lblInputLabel.setBounds(10, frameHeight - 80, lblInputLabel.getPreferredSize().width, lblInputLabel.getPreferredSize().height);
		btnInputMode.addActionListener(new btnInputModeListener());
		
		btnQuit = new JButton("QUIT");
		pane.add(btnQuit);
		btnQuit.setBounds(200, frameHeight - 60, btnQuit.getPreferredSize().width + 50, btnInputMode.getPreferredSize().height);
		btnQuit.addActionListener(new btnQuitListener());

		frame.addKeyListener(new keyPressedListener());
		frame.requestFocus();
		frame.setVisible(true);
	}
		
	/**
	 * Returns the integer of the given square or -1 if the square is empty
	 * @param x		The x value of the square (0-8)
	 * @param y		The y value of the square (0-8)
	 * @return		The integer of the square
	 */
	public int getBoxValue(int x, int y){
		Scanner converter = new Scanner(box[x][y].getText());
		int contents;
		try {
			contents = converter.nextInt();
		} catch (InputMismatchException e){
			contents = -1;
		} catch (NoSuchElementException e){
			contents = -1;
		}
		return contents;
	}
	
	//TODO Check if game has been won
	
	//TODO Input to set the setting of the boxes (E.g. red "MISTAKE" color)
	/**
	 * De-selects all squares
	 */
	public static void deselectAll(){
		inputX = -1;
		inputY = -1;
		source = null;
	}
	
	/**
	 * Returns whether or not there is a selected square
	 * @return	True if a square is selected, false otherwise
	 */
	public boolean squareSelected(){
		return (source != null);
	}

	
	private static void setStartingBoxInfo(){
		int x = 0;
		int y = 0;
		while (y < 9){
			while (x < 9){
				box[x][y] = new JButton();
				//subBox[x][y] = new JTextPane();
				pane.add(box[x][y]);
				//pane.add(subBox[x][y]);
				box[x][y].setBounds(x*boxWidth+10, y*boxHeight+10, boxWidth, boxHeight);
				box[x][y].addActionListener(new btnSquareListener(x, y));
				//box[x][y].setForeground(defaultBGColor);
				//subBox[x][y].setBounds(x*boxWidth+10, y*boxHeight+10, boxWidth, boxHeight);
				//subBox[x][y].setEnabled(false);
				entry[x][y] = new Square(0, 0);
				x++;
			}
			y++;
			x = 0;
		}
	}
	
	private static void resetSourceBox(){
		System.out.println("STUFF");
		if (entry[inputX][inputY].getCurrentValue() > 0){
			source.setText(Integer.toString(entry[inputX][inputY].getCurrentValue()));
		} else {
			Square tempDraft = entry[inputX][inputY];
			Integer x = 1;
			String boxString = "";
			while (x < 10){
				if (tempDraft.isMarkedDraft(x)){
					boxString = (boxString + " " + x.toString());
				}
				x++;
			}
			source.setText(boxString);
		}
		pane.repaint();

	}
		
	private static boolean draftEntry = false;
	private static JButton source;
	private static int inputX;
	private static int inputY;
	
	//============================================================================================================================================================
	//AAAAAAAA  CCCCCCCC  TTTTTTTTTT  IIIIII  OOOOOOOO  NN      NN        LL      IIIIII  SSSSSSSS  TTTTTTTTTT  EEEEEEEE  NN      NN  EEEEEEEE  RRRRRR    SSSSSSSS
	//AA    AA  CC            TT        II    OO    OO  NNNN    NN        LL        II    SS            TT      EE        NNNN    NN  EE        RR    RR  SS
	//AA    AA  CC            TT        II    OO    OO  NN  NN  NN        LL        II    SSSS          TT      EEEEEE    NN  NN  NN  EEEEEE    RRRRRR    SSSS
	//AAAAAAAA  CC            TT        II    OO    OO  NN  NN  NN        LL        II        SSSS      TT      EE        NN  NN  NN  EE        RRRR          SSSS
	//AA    AA  CC            TT        II    OO    OO  NN    NNNN        LL        II          SS      TT      EE        NN    NNNN  EE        RR  RR          SS
	//AA    AA  CCCCCCCC      TT      IIIIII  OOOOOOOO  NN      NN        LLLLLL  IIIIII  SSSSSSSS      TT      EEEEEEEE  NN      NN  EEEEEEEE  RR    RR  SSSSSSSS
	//============================================================================================================================================================
	
	/**
	 * Action Listener to change the input mode
	 * @author Sam
	 */
	public static class btnInputModeListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if (draftEntry == true){
				btnInputMode.setText("Entry Mode");
				draftEntry = false;
				deselectAll();
				//switchEntryMode();
			} else {
				btnInputMode.setText("Draft Notes Mode");
				draftEntry = true;
				deselectAll();
				//switchEntryMode();
			}
		}
		
	}
	
	/**
	 * Action Listener to quit
	 * @author Sam
	 */
	public static class btnQuitListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	}
	
	/**
	 * Action Listener for each of the 81 squares 
	 * @author Sam
	 */
	public static class btnSquareListener implements ActionListener{
		int squareX, squareY;
		
		public btnSquareListener(int x, int y){
			squareX = x;
			squareY = y;
		}
		
		public void actionPerformed(ActionEvent e){
			//source.setForeground(defaultBGColor);
			
			System.out.println("square selected " + squareX + " " + squareY);
			
			if (source != null){
				source.setText("");
			}
			source = (JButton) e.getSource();
			inputX = squareX;
			inputY = squareY;
			//source.setText(inputX + " " + inputY);
			//source.setForeground(selectedBGColor);
		}
	}
	
	/**
	 * Key Press listeners for typing
	 * @author Sam
	 */
	public static class keyPressedListener implements KeyListener{
		
		public void keyPressed(KeyEvent e) {
		}

		public void keyReleased(KeyEvent e) {
		}

		public void keyTyped(KeyEvent e) {
			System.out.println("Key Typed " + e.getKeyChar());
			int number = 0;
			boolean shift = false;
			Scanner toInt = new Scanner(e.toString());
			try{
				number = toInt.nextInt();
				if (e.isShiftDown()){
					shift = true;
				}
			} catch (Exception error){
				shift = true;
				if (e.getKeyChar() == '!'){
					number = 1;
				} else if (e.getKeyChar() == '@'){
					number = 2;
				} else if (e.getKeyChar() == '#'){
					number = 3;
				} else if (e.getKeyChar() == '$'){
					number = 4;
				} else if (e.getKeyChar() == '%'){
					number = 5;
				} else if (e.getKeyChar() == '^'){
					number = 6;
				} else if (e.getKeyChar() == '&'){
					number = 7;
				} else if (e.getKeyChar() == '*'){
					number = 8;
				} else if (e.getKeyChar() == '('){
					number = 9;
				}				
				if (shift){
					entry[inputX][inputY].switchDraftValue(number);
				} else {
					entry[inputX][inputY].setCurrentValue(number);
				}
			}
			resetSourceBox();
				
			

			
		}
		
	}
	
	//TODO Action Listener to tell if a number has been pressed and if so, what square to put it in
	//TODO Expansion on that, send the number to the actual board thing.
	
}
