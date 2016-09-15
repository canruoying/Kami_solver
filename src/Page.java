/**
 * 
 */

/**
 * @author canruoying
 *
 */
public class Page {
	private int[][] islandMap;
	private int width;
	private int height;
	private int islandCount;

//	private int currentIslandColor;
	private boolean islandIDUsed;
	
	public int[][] pageMatrix;

	public Page(int[][] page) {
		pageMatrix = page;
		width = getPageMatrix()[0].length;
		height = getPageMatrix().length;

		createIslandMap();
	}

	public int[][] getPageMatrix() {
		return pageMatrix;
	}

	public int[][] getIslandMap() {
		return islandMap;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private void createIslandMap() {
		initializeIslandMap();
		islandCount++;
		for (int r = 0; r < getHeight(); r++) {
			for (int c = 0; c < getWidth(); c++) {
				int currentIslandColor = getPageMatrix()[r][c];
				if (checkAndMark(r, c, currentIslandColor)) {
					islandCount++;					
				}
			}
		}
	}

	private boolean checkAndMark(int r, int c, int currentIslandColor) {
		if (boundLimitExceeded(r, c)) {
			return false;
		}
		if (islandMap[r][c] != 0) {
			return false;
		} else {
			if (getPageMatrix()[r][c] == currentIslandColor) {
				markPoint(r, c, islandCount, currentIslandColor);
			} else {
				return false;
			}
			checkAndMark(r, c + 1, currentIslandColor);
			checkAndMark(r + 1, c, currentIslandColor);
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
		islandCount = 0;
		for (int r = 0; r < getHeight(); r++) {
			for (int c = 0; c < getWidth(); c++) {
				islandMap[r][c] = 0;
			}
		}
	}

	public void printMatrix(int[][] m) {
		for (int r = 0; r < m.length; r++) {
			for (int c = 0; c < m[0].length; c++) {
				System.out.printf("%d ", m[r][c]);
			}
			System.out.println();
		}
	}
}
