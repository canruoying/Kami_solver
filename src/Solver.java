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

	public Solver(Page p, int steps) {
		page = p;
		stepsRequired = steps;
		pageStep = new Page[stepsRequired];
		solved = false;
	}

	public Page getPage() {
		return page;
	}

	public void solve() {
		solved = solve(page, 0);
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
		} else {
			System.out.println("not solved");
		}
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
