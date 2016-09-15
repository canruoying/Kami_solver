/**
 * 
 */

/**
 * @author canruoying
 *
 */
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] test = new int[4][3];
		test[0] = new int[] {1, 1, 1};
		test[1] = new int[] {1, 2, 1};
		test[2] = new int[] {1, 2, 2};
		test[3] = new int[] {2, 3, 3};
		
		Page p1 = new Page(test);
		System.out.printf("p1 width: %d\n", p1.getWidth());
		System.out.printf("p1 height: %d\n", p1.getHeight());
		System.out.println("p1 matrix:");
		p1.printMatrix(p1.getPageMatrix());
		System.out.println("p1 island map:");
		p1.printMatrix(p1.getIslandMap());
	}

}
