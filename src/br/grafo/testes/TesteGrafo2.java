package br.grafo.testes;

import java.io.IOException;

import br.graph.Aresta;
import br.graph.Grafo;
import br.pereira.util.GraphUtils;

public class TesteGrafo2 {
	static int[][] vertices2 = { { 10, 20, 30 }, { 20, 30, 40 },
			{ 30, 40, 50 }, { 40, 50, 60 }, { 50, 60, 70 }, { 60, 70, 80 },
			{ 70, 80, 90 }, { 80, 90, 99 } };
	static int[][] arestas2 = { { 0, 1 }, { 0, 5 }, { 5, 1 }, { 2, 1 },
			{ 2, 5 }, { 5, 4 }, { 3, 4 }, { 4, 3 }, };
	static int[] pesos2 = { 4, 2, 5, 6, 1, 4, 2, 3 };

	public static void main(String[] args) throws IOException {
		Grafo<int[]> g = new Grafo<int[]>();
		g.addVertices(vertices2);
		for (int i = 0; i < arestas2.length; i++) {
			int[] vi = arestas2[i];
			Aresta a = new Aresta(vi[0], vi[1], pesos2[i]);
			g.addAresta(a);
		}
		GraphUtils.writeGMLFile(g);
	}

}
