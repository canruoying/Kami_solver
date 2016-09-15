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

		Page p1 = new Page(Page.fileToArray("test/in.txt"));
		Solver s1 = new Solver(p1, 3);
		s1.solve();
		s1.printToFile("test/out.log");

	}

}
