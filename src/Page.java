/**
 * 
 */

/**
 * @author canruoying
 *
 */
public class Page {
	private int[][] pageMatrix;
	private int[][] islandMap;
	private int width;
	private int height;
	private int islandCount;
	private int colorRange;

	public Page(int[][] page) {
		pageMatrix = page;
		width = getPageMatrix(0).length;
		height = getPageMatrix().length;

		createIslandMap();
	}

	public Page(Page p) {
		width = p.getWidth();
		height = p.getHeight();
		islandCount = p.getIslandCount();
		colorRange = p.getColorRange();
		pageMatrix = new int[height][width];
		islandMap = new int[height][width];
		for (int r = 0; r < getHeight(); r++) {
			for (int c = 0; c < getWidth(); c++) {
				pageMatrix[r][c] = p.getPageMatrix(r, c);
				islandMap[r][c] = p.getIslandMap(r, c);
			}
		}
	}

	public int[][] getPageMatrix() {
		return pageMatrix;
	}

	public int[] getPageMatrix(int r) {
		return pageMatrix[r];
	}

	public int getPageMatrix(int r, int c) {
		return pageMatrix[r][c];
	}

	public int[][] getIslandMap() {
		return islandMap;
	}

	public int[] getIslandMap(int r) {
		return islandMap[r];
	}

	public int getIslandMap(int r, int c) {
		return islandMap[r][c];
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getColorRange() {
		return colorRange;
	}

	public int getIslandCount() {
		return islandCount;
	}

	private void createIslandMap() {
		initializeIslandMap();
		islandCount = 1;
		colorRange = 0;
		for (int r = 0; r < getHeight(); r++) {
			for (int c = 0; c < getWidth(); c++) {
				int currentIslandColor = getPageMatrix(r, c);
				if (checkAndMark(r, c, currentIslandColor)) {
					islandCount++;
					if (currentIslandColor > colorRange) {
						colorRange = currentIslandColor;
					}
				}
			}
		}
		islandCount -= 1;
	}

	private boolean checkAndMark(int r, int c, int islandColor) {
		if (boundLimitExceeded(r, c)) {
			return false;
		}
		if (getIslandMap(r, c) != 0) {
			return false;
		} else {
			if (getPageMatrix(r, c) == islandColor) {
				markPoint(r, c, islandCount, islandColor);
			} else {
				return false;
			}
			checkAndMark(r, c + 1, islandColor);
			checkAndMark(r + 1, c, islandColor);
			return true;
		}
	}

	private boolean boundLimitExceeded(int r, int c) {
		return (c < 0 || r < 0 || c >= getWidth() || r >= getHeight());
	}

	private void markPoint(int r, int c, int islandID, int currentIslandColor) {
		islandMap[r][c] = islandID;
	}

	private void initializeIslandMap() {
		islandMap = new int[height][width];
		for (int r = 0; r < getHeight(); r++) {
			for (int c = 0; c < getWidth(); c++) {
				islandMap[r][c] = 0;
			}
		}
	}

	public void printMatrix(int[][] m) {
		System.out.println("=======");
		for (int r = 0; r < m.length; r++) {
			for (int c = 0; c < m[0].length; c++) {
				System.out.printf("%d ", m[r][c]);
			}
			System.out.println();
		}
		System.out.println("=======");
	}

	public void print() {
		printMatrix(getPageMatrix());
	}

	public Page flipIsland(int islandID, int islandColor) {
		Page p = new Page(this);
		for (int r = 0; r < getHeight(); r++) {
			for (int c = 0; c < getWidth(); c++) {
				if (getIslandMap(r, c) == islandID) {
					p.flipPoint(r, c, islandColor);
				}
			}
		}
		p.createIslandMap();
		return p;
	}

	private void flipPoint(int r, int c, int islandColor) {
		pageMatrix[r][c] = islandColor;
	}

	public int getIslandColor(int islandID) {
		for (int r = 0; r < getHeight(); r++) {
			for (int c = 0; c < getWidth(); c++) {
				if (getIslandMap(r, c) == islandID) {
					return getPageMatrix(r, c);
				}
			}
		}
		return 0;
	}
}
