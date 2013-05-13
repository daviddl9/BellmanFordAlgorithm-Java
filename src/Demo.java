import java.io.IOException;

import com.nickistudio.algorithm.BellmanFordAlgorithm;

public class Demo {
	
	public static void main(String args[]) throws IOException {
		BellmanFordAlgorithm r = new BellmanFordAlgorithm();
		r.relax();
		if (r.cycle()) {
			for (int i = 0; i < r.n; i++)
				System.out.println(r.s + " ==> " + r.d[i]);
		} else {
			System.out.println(" There is a negative edge cycle ");
		}

		r.print();
	}
}
