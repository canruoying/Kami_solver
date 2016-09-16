import java.io.IOException;

/**
 * 
 */

/**
 * @author canruoying
 *
 */

class Kami_solver {

	public static void main(String[] args) throws IOException {

		for (int i = 0; i < args.length; i++) {
			Page p = new Page(Page.fileToArray(args[i]));
			Solver s = new Solver(p);
			s.solve();
			s.printToFile(args[i] + ".out");
			System.out.println();
		}
	}

}
