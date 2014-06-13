package br.grafo.testes;

import java.io.IOException;
import java.util.Arrays;

import br.graph.Aresta;
import br.graph.Grafo;
import br.pereira.util.GraphUtils;

public class TesteGrafo {
	static Character[] arest = { 'a', 'b', 'c', 'd', 'e', 'f' };
	static char[] vert1 = { 'a', 'a', 'f', 'c', 'c', 'f', 'd', 'd' };
	static char[] vert2 = { 'b', 'f', 'b', 'b', 'f', 'e', 'e', 'c' };
	static int[] pesos = { 4, 2, 5, 6, 1, 4, 2, 3 };

	public static void main( String[] args ) throws IOException {
		Grafo<Character> g = new Grafo<Character>();
		g.addVertices(arest);
		for (int i = 0; i < vert1.length; i++) {
			int a1 = Arrays.binarySearch(arest, vert1[i]);
			int a2 = Arrays.binarySearch(arest, vert2[i]);
			int w = pesos[i];
			Aresta a = new Aresta(a1, a2, w);
			g.addAresta(a);
		}
		GraphUtils.writeGMLFile( g );

		//Grafo<Character> mst = Grafo.kruskalMST(g);
	}
}