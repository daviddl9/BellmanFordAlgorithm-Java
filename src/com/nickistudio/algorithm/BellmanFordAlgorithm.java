package com.nickistudio.algorithm;

import java.util.List;

import com.nickistudio.algorithm.type.Edge;
import com.nickistudio.algorithm.type.Graph;
import com.nickistudio.algorithm.type.Vertex;

public class BellmanFordAlgorithm
{
	final int INFINITY = Integer.MAX_VALUE;

	List<Edge> fEdges;
	List<Vertex> fVertices;
	boolean fHasNegative;
	int d[];

	public BellmanFordAlgorithm(Graph aGraph)
	{
		fEdges = aGraph.getEdges();
		fVertices = aGraph.getVertices();
		d = new int[fVertices.size()];
	}

	public void execute(Vertex aStartingVertex)
	{
		int s = fVertices.indexOf(aStartingVertex);

		// initialize
		for (int i = 0; i < fVertices.size(); ++i)
			d[i] = INFINITY;
		d[s] = 0;

		// Relaxation
		for (int i = 0; i < fVertices.size() - 1; ++i) {
			for (Edge edge : fEdges) {
				Vertex src = edge.getSource();
				Vertex dst = edge.getDestination();
				if (d[fVertices.indexOf(dst)] > d[fVertices.indexOf(src)] + edge.getWeight()) {
					d[fVertices.indexOf(dst)] = d[fVertices.indexOf(src)] + edge.getWeight();
				}
			}
		}

		// Check for negative edges
		for (Edge edge : fEdges) {
			Vertex src = edge.getSource();
			Vertex dst = edge.getDestination();
			if (d[fVertices.indexOf(dst)] > d[fVertices.indexOf(src)] + edge.getWeight()) {
				fHasNegative = true;
			}
		}

	}

	public void dump()
	{
		for (Edge edge : fEdges) {
			Vertex dst = edge.getDestination();
			System.out.printf("%s >==%d->%d==> %s\n", edge.getSource(), edge.getWeight(), d[fVertices.indexOf(dst)],
					edge.getDestination());
		}
	}
}
