import java.util.LinkedList;
//
public class Square {
	
	/**
	 * Generate a square with its position and index
	 * defined.
	 * @param row The row in which the square is positioned.
	 * @param column The column in which the square is in.
	 * @param boxNum The 3x3 box number in which the square is in.
	 * @param positionInBox The position in the 3x3 box the square is in.
	 */
	public Square(int boxNum, int val) {
		this.threeByThreeBoxIndex = boxNum;
		this.currentValue = val;
	}
	
	/**
	 * Set the current value of the square. This is
	 * the value to be displayed in the box in GUI.
	 * @param value An integer that specifies the value
	 * to be displayed.
	 */
	public void setCurrentValue(int value) {
		this.currentValue = value;
	}
	
	/**
	 * Changes the value of the draft entry for the given
	 * number.
	 * @param value Integer specifying draft value
	 */
	public void switchDraftValue(int value){
		this.draftEntry[value] = !this.draftEntry[value];
	}
	
	/**
	 * Set the 3x3 box in which the current square is in.
	 * Index from 0 to 8.
	 * @param threeByThreeBoxIndex The 3x3 box number that
	 * the square is in range from 0 to 8.
	 */
	public void setThreeByThreeBox(int threeByThreeBoxIndex) {
		this.threeByThreeBoxIndex = threeByThreeBoxIndex;
	}

	/**
	 * Set the position of the square in the 3x3 box.
	 * @param positionInThreeByThree
	 */
	public void setPositionInThreeByThree(int positionInThreeByThree) {
		this.positionInThreeByThree = positionInThreeByThree;
	}
	
	/**
	 * Get the current value of the square. This is the value
	 * displayed in the box in GUI.
	 * @return An integer that specifies the value displayed.
	 */
	public int getCurrentValue() {
		return this.currentValue;
	}
	
	public boolean isMarkedDraft(int value){
		return this.draftEntry[value];
	}
	
	public int getThreeByThreeBox() {
		return threeByThreeBoxIndex;
	}
	
	public int getPositionInThreeByThree() {
		return positionInThreeByThree;
	}

	private int row;
	private int column;
	private int threeByThreeBoxIndex;
	private int positionInThreeByThree;
	
	private boolean[] draftEntry = {false, false, false, false, false, false, false, false, false};

	//0 for empty
	private int currentValue;
	private LinkedList<Integer> availableValues;
	private LinkedList<Integer> usedValues;
}
