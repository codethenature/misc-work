package assignment4Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph {

	boolean[][] adjacency;
	int nbNodes;

	public Graph(int nb) {
		this.nbNodes = nb;
		this.adjacency = new boolean[nb][nb];
		for (int i = 0; i < nb; i++) {
			for (int j = 0; j < nb; j++) {
				this.adjacency[i][j] = false;
			}
		}
	}

	public void addEdge(int i, int j) {
		adjacency[i][j] = true;
		adjacency[j][i] = true;
	}

	public void removeEdge(int i, int j) {
		adjacency[i][j] = false;
		adjacency[j][i] = false;
	}

	public int nbEdges() {
		int count = 0;
		for (int i = 0; i < nbNodes; i++) {
			for (int j = 0; j < i; j++) {
				if (adjacency[i][j])
					count++;
			}
		}
		return count;
	}

	public boolean cycle(int start) {
		// DFS
		boolean[] visited = new boolean[nbNodes];

		Stack<Integer> st = new Stack<>();
		st.push(start);

		while (!st.isEmpty()) {
			int current = st.pop();

			// go through the complete row
			for (int j = 0; j < nbNodes; j++) {

				// skipping self loops
				if (adjacency[current][j] && current != j) {
					if (!visited[j]) {
						st.push(j);
						visited[j] = true;
					} else if (start == j)
						return true;
				}

			}
		}
		return false;
	}

	public int shortestPath(int start, int end) {
		// BFS
		if (start == end)
			return 0;

		int[] path = new int[nbNodes];
		Arrays.fill(path, nbNodes + 1);
		path[start] = 0;
		boolean[] visited = new boolean[nbNodes];
		Queue<Integer> qu = new LinkedList<>();
		qu.add(start);

		while (!qu.isEmpty()) {
			int current = qu.poll();

			// go through the complete row
			for (int j = 0; j < nbNodes; j++) {

				// skipping self loops
				if (adjacency[current][j] && current != j) {
					if (!visited[j]) {
						qu.add(j);
						path[j] = path[current] + 1;
						visited[j] = true;
					}
				}

			}
		}
		return path[end];
	}

}
