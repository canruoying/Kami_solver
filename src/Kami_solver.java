/**
 * 
 */

/**
 * @author canruoying
 *
 */
class Kami_solver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] test = new int[8][3];
		test[0] = new int[] {1, 1, 1};
		test[1] = new int[] {1, 2, 1};
		test[2] = new int[] {1, 2, 2};
		test[3] = new int[] {2, 3, 3};
		test[4] = new int[] {1, 1, 1};
		test[5] = new int[] {1, 2, 1};
		test[6] = new int[] {1, 2, 2};
		test[7] = new int[] {2, 3, 3};
		
		Page p1 = new Page(test);
		Solver s1 = new Solver(p1, 4);
		
		long startTime = System.currentTimeMillis();
		s1.solve();
		s1.print();
		long elapsed = System.currentTimeMillis() - startTime;
		System.out.printf("Time Elapsed: %d", elapsed);
	}

}
