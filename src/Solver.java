import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 
 */

/**
 * @author canruoying
 *
 */
public class Solver {
	private Page page;
	private int stepsRequired;
	private Page[] pageStep;
	public boolean solved;
	private long timeElapsed;

	public Solver(Page p, int steps) {
		page = p;
		stepsRequired = 0;
		solved = false;
		timeElapsed = 0;
	}

	public Page getPage() {
		return page;
	}

	// public void solve() {
	// long startTime = System.currentTimeMillis();
	// solved = solve(page, 0);
	// timeElapsed = System.currentTimeMillis() - startTime;
	// }

	public void solve() {
		long startTime = System.currentTimeMillis();
		while (!solved) {
			stepsRequired++;
			pageStep = new Page[stepsRequired];
			solved = solve(page, 0);
		}
		timeElapsed = System.currentTimeMillis() - startTime;
	}

	public void print() {
		if (solved) {
			getPage().print();
			for (int i = 0; i < stepsRequired; i++) {
				System.out.printf(" ||\n");
				System.out.printf(" ||\n");
				System.out.printf(" || Step %d\n", i + 1);
				System.out.printf("\\||/\n");
				System.out.printf(" \\/\n");
				if (pageStep[i] != null) {
					pageStep[i].print();
				}
			}
			System.out.printf("Time Elapsed: %dms\n", timeElapsed);
		} else {
			System.out.println("Not solved.");
		}
	}

	public void printToFile(String fileName) throws IOException {
		FileOutputStream outFile = null;
		PrintStream outStream = null;

		outFile = new FileOutputStream(fileName);
		outStream = new PrintStream(outFile);
		System.setOut(outStream);
		print();
		outStream.close();
		outFile.close();

	}

	public boolean solve(Page p, int stepNumber) {
		if (p.getIslandCount() == 1) {
			return true;
		} else if (stepsRequired <= stepNumber) {
			return false;
		}
		for (int islandID = 1; islandID <= p.getIslandCount(); islandID++) {
			for (int islandColor = 1; islandColor <= p.getColorRange(); islandColor++) {
				if (islandColor != p.getIslandColor(islandID)) {
					Page flippedPage = p.flipIsland(islandID, islandColor);
					if (solve(flippedPage, stepNumber + 1)) {
						pageStep[stepNumber] = flippedPage;
						return true;
					}
				}
			}
		}
		return false;
	}
}
