public class PuzzleTester {

	public static void main(String args[]) {

		System.out.println("Easy puzzle:");
		Puzzle.createPuzzle(Puzzle.EASY);
		System.out.println();
		System.out.println("Medium puzzle:");
		Puzzle.createPuzzle(Puzzle.MEDIUM);
		System.out.println();
		System.out.println("Hard puzzle:");
		Puzzle.createPuzzle(Puzzle.HARD);
		System.out.println();

		int[][] Sudoku1 = new int[9][9]; 
		HintSystem h = new HintSystem();
		h.Hint(Sudoku1);
		Puzzle p = new Puzzle(0);

//		p.printPuzzle();
	}
}
