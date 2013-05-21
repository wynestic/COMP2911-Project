import java.util.LinkedList;


public class LegalCheck {
	public static void checkLegal(Puzzle p, Square s) {
		puzzle = p;
		int row = s.getRow();
		int column = s.getColumn();
		int boxNum = s.getThreeByThreeBox();
		
		hasDuplicate(row, column, s.getCurrentValue());
		hasDuplicateInBox(boxNum, s.getCurrentValue());
	}
	
	/**
	 * Check duplicates in rows and columns.
	 * @param row
	 * @param column
	 * @param val
	 * @return True if there is a duplicate in current row or column.
	 */
	public static boolean hasDuplicate(int row, int column, int val) {
		for (int i = 0; i < Puzzle.COLUMN_NUMBER; i++) {
			if (puzzle[row][i].getType() == 0)
				continue;
			if (puzzle[row][i].getCurrentValue() == val)
				return true;
		}
		
		for (int i = 0; i < Puzzle.ROW_NUMBER; i++) {
			if (puzzle[i][column].getType() == 0)
				continue;
			if (puzzle[i][column].getCurrentValue() == val)
				return true;
		}
		
		return false;
	}
	
	/**
	 * Check if there are duplicates in the 3x3 box where
	 * the square belongs.
	 * @param boxNum
	 * @param val
	 * @return
	 */
	public static boolean hasDuplicateInBox(int boxNum, int val) {
		LinkedList<Square> list = getSquaresInThreeByThree(boxNum);
		for (Square s : list) {
			if (s.getType() == Square.EMPTY_CELL)
				continue;
			if (s.getCurrentValue() == val)
				return true;
		}
		return false;
	}
	
	/**
	 * Get the list of squares that belong to the
	 * 3x3 box number given in the parameter.
	 * @param threeByThreeIndex The 3x3 box number.
	 * @return The list of squares that are in the given 
	 * box number.
	 */
	public LinkedList<Square> getSquaresInThreeByThree(int threeByThreeIndex) {
		LinkedList<Square> threeByList = new LinkedList<Square>();
		int iStart = 0, iEnd = 0;
		int jStart = 0, jEnd = 0;
		if (threeByThreeIndex == 0 ||
				threeByThreeIndex == 1 ||
				threeByThreeIndex == 2) {
			iStart = 0;
			iEnd = 3;
			jStart = 0;
			jEnd = 3;
			if (threeByThreeIndex == 1) {
				jStart = 3;
				jEnd = 6;
			} else if (threeByThreeIndex == 2) {
				jStart = 6;
				jEnd = 6;
			}
		} else if (threeByThreeIndex == 3 ||
				threeByThreeIndex == 4 ||
				threeByThreeIndex == 5) {
			iStart = 3;
			iEnd = 6;
			jStart = 0;
			jEnd = 3;
			if (threeByThreeIndex == 4) {
				jStart = 3;
				jEnd = 6;
			} else if (threeByThreeIndex == 5) {
				jStart = 6;
				jEnd = 9;
			}
		} else if (threeByThreeIndex == 6 ||
				threeByThreeIndex == 7 ||
				threeByThreeIndex == 8) {
			iStart = 6;
			iEnd = 9;
			jStart = 0;
			jEnd = 3;
			if (threeByThreeIndex == 7) {
				jStart = 3;
				jEnd = 6;
			} else if (threeByThreeIndex == 8) {
				jStart = 6;
				jEnd = 9;
			}
		}
		
		for (int i = iStart; i < iEnd; i++) {
			for (int j = jStart; j < jEnd; j++) {
				threeByList.add(puzzle[i][j]);
			}
		}
		return threeByList;
	}
	
	private static Puzzle puzzle;
}
