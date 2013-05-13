import java.io.IOException;
import java.util.LinkedList;

public class Demo {
	LinkedList<Edge> edges;
	int d[], p[];
	int n, e, s;
	final int INFINITY = 999;

	private static class Edge {
		int u, v, w;

		public Edge(int a, int b, int c) {
			u = a;
			v = b;
			w = c;
		}
	}

	Demo() throws IOException {
		edges = new LinkedList<Edge>();
		
		n = 3;
		edges.add(new Edge(0, 1, 99));
		edges.add(new Edge(0, 2, 1));
		edges.add(new Edge(1, 0, 4));
		edges.add(new Edge(1, 2, 2));
		edges.add(new Edge(2, 0, 2));
		edges.add(new Edge(2, 1, 4));
		
		e = edges.size();
		d = new int[n];
		p = new int[n];

		s = 0;
	}

	void relax() {
		int i, j;
		for (i = 0; i < n; ++i) {
			d[i] = INFINITY;
			p[i] = -1;
		}

		d[s] = 0;
		for (i = 0; i < n - 1; ++i) {
			for (j = 0; j < e; ++j) { // here i am calculating the shortest path
				if (d[edges.get(j).u] + edges.get(j).w < d[edges.get(j).v]) {
					d[edges.get(j).v] = d[edges.get(j).u] + edges.get(j).w;
					p[edges.get(j).v] = edges.get(j).u;
				}
			}
		}
	}

	boolean cycle() {
		int j;
		for (j = 0; j < e; ++j)
			if (d[edges.get(j).u] + edges.get(j).w < d[edges.get(j).v])
				return false;
		return true;
	}

	void print() {
		for (int i = 0; i < n; i++) {
			System.out.println("Vertex " + i + " has predecessor " + p[i]);
		}
	}

	public static void main(String args[]) throws IOException {
		Demo r = new Demo();
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
