import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;

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
	public long combinationsTested;
	private long timeElapsed;
	private HashSet<Page> next = new HashSet<Page>();
	private HashSet<Page> curr = new HashSet<Page>();
	private int min = Integer.MAX_VALUE;
	private ArrayList<Page> solution;
	private int shortest = Integer.MAX_VALUE;

	public Solver(Page p) {
		page = p;
		stepsRequired = 0;
		pageStep = null;
		solved = false;
		combinationsTested = 0;
		timeElapsed = 0;
	}

	public Page getPage() {
		return page;
	}

	public void solve() {
		long startTime = System.currentTimeMillis();
		combinationsTested = 0;
		int height = page.getHeight();
		int width = page.getWidth();
		int islandCount = page.getIslandCount();
		int colorCount = page.getColorCount();
		System.out.printf("Solving %d by %d matrix with %d islands and %d colors.\n", height, width, islandCount,					colorCount);
		curr.add(page);
		pageStep = new Page[20];
		solution = new ArrayList<Page>();
		while (!solved) {
			stepsRequired++;
			System.out.printf("Working on %d step solutions...", stepsRequired);
				for (Page p : curr) {
				solved = solved(p);
				if (solved) {
					System.out.print("Done! ");
					System.out.printf("Combinations Tested: %d\n, steps %d\n", combinationsTested, stepsRequired);
					System.out.printf("Time Elapsed: %dms ", timeElapsed);
					if (shortest > stepsRequired) {
						while (p != null) {
							solution.add(0, p);
							p = p.parent;
						}
						solution.add(pageStep[stepsRequired]);
						shortest = stepsRequired;
					}
						break;
				} else {
					// System.out.print("No solutions found. ");
				}
				timeElapsed = System.currentTimeMillis() - startTime;
				// System.out.println();
				// System.out.printf("Time Elapsed: %dms ", timeElapsed);
				// System.out.printf("Combinations Tested: %d\n, steps %d\n", combinationsTested, stepsRequired);
			}
			int count = 0;
			if (!solved) {
				// HashSet<Page> cleaned = new HashSet<Page>();
				// int m = min;
				// for (Page p : next) {
				// 	int num = p.getIslandCount();
				// 	if (num <= min) {
				// 		cleaned.add(p);
				// 	}
				// 	if (num == min && count < 2) {
				// 		cleaned.add(p);
				// 		count++;
				// 	}
				// }
				curr = next;
				next = new HashSet<Page>();
			}
		}
		System.out.printf("Combinations Tested: %d\n, steps %d\n", combinationsTested, stepsRequired);
	
		for (Page b : solution) {
			b.print();
		}
		System.out.printf("Combinations Tested: %d\n, sshortest %d\n", combinationsTested, shortest);
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
		PrintStream stdout = System.out;
		System.setOut(outStream);
		print();
		System.setOut(stdout);
		outStream.close();
		outFile.close();

	}

	public boolean solved(Page p) {
			ArrayList<Integer> shuffled = new ArrayList<Integer>();
			ArrayList<Integer> shuffledColors = new ArrayList<Integer>();

			combinationsTested++;
			int islandCount = p.getIslandCount();
			for (int i = 1; i <=  p.getIslandCount(); i++) {
				shuffled.add(i);
			}
			for (int islandColor : p.colors) {
				shuffledColors.add(islandColor);
			}
			Collections.shuffle(shuffled);
			Collections.shuffle(shuffledColors);

			for (int islandID : shuffled) {
				for (int islandColor : shuffledColors) {
					if (islandColor != p.getIslandColor(islandID)) {
						Page flippedPage = p.flipIsland(islandID, islandColor);
						int islCount = flippedPage.getIslandCount();
						if (islCount < islandCount) {
							int threshold = min;
							if (min < 10) {
								threshold = random(min, (int) (min * 2));
							}
							if (islCount <= threshold) {
								flippedPage.parent = p;
								if (islCount == 1) {
									pageStep[stepsRequired] = flippedPage;
									return true;
								}
								next.add(flippedPage);
								min =  islCount;
							}
						}
					}
				}
			}
		return false;
	}

	public int random(int low, int high) {
		double r = Math.random();    // random between 0 and 1
	    return (int) (low + (high - low) * r);
	}
}