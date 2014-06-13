package br.grafo;

import java.util.ArrayList;
import java.util.Collections;

public class MST extends Grafo {

	Grafo umG = null;
	public MST( Grafo g ) {
		umG = g;
	}

	public String toString() {
		// imprimir a lista de arestas...
		return umG.toString();
	}
	
	ArrayList<Aresta> listA = new ArrayList<Aresta>();
	public void runKruskal() {
		// ordenar pelas arestas com menor peso
		Collections.sort( umG.arestas );

		Grafo novoG = new Grafo();
		novoG.initVertices( vertices );
		for( Aresta a : arestas ) {
			// aqui
		}
//		??? return novoGrafo; ???
	}
	
	public void runPrim() {
		
	}
}
