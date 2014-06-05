package br.graph;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import br.graphcolormorphology.ElementoEstruturante;
import br.graphcolormorphology.util.ImgUtil;

public class GrafoMST extends Grafo<int[]> {

	private static final boolean CLONE_EE = true;
	
	public GrafoMST( int[][] elEstr ) {
		// vou construir o grafo uma vez, e depois atualizandoooo....
		for( int i=0; i<20; i++ ) { // numero de arestas total
			Aresta a = new Aresta( -1, -1, -1 );
			this.addAresta( a );
		}
	}

	public boolean ehAdjacente( int i1, int i2 ) {
		return ElementoEstruturante.adjacente[i1][i2];
	}
	
	public void initArestas() {
		// pegar a distancia de todos pixels em relacao ao outro... =/
		Iterator<Aresta> ia = arestas.iterator();
		for( int i=0; i<=4; i++ ) {
			for( int j=i+1; j<vertices.length; j++ ) {// o q jah foi visitado nao precisa de novo... por isso: j=i+1
				if( ehAdjacente( i, j ) ) {
					float f = ImgUtil.getRGBDistance( vertices[i], vertices[j] );
					Aresta a = ia.next();
					a.setInfo( i, j, f );
				}
			}
		}
	}
	
	public int[] getMinRGB( int[][] elemEstrut ) {
		int[] result = null;

		if( CLONE_EE )
			elemEstrut = elemEstrut.clone();

		this.addVertices( elemEstrut );

		// eh preciso construir as arestas adjacentes
		initArestas();
		Arrays.fill( numeroLigacoes, 0 );
		GrafoMST novoGrafo = (GrafoMST)kruskalMST( this, numeroLigacoes );
		novoGrafo.removeTroncos();
		
		while( arestas.size() >= 2 ) {
			
		}
		// as proximas iteracoes, eh preciso fazer um complete lattice
		
		return result;
	}

	public void removerArestas( int vertex ) {
		for( Aresta a : arestas ) {
			if( a.vi[0] == vertex || a.vi[1] == vertex ) {
				arestas.remove( a );
			}
		}
	}
	
	// manter somente as folhas (que tem somente uma ligação)...
	public void removeTroncos() {
		for( int i=0; i<numeroLigacoes.length; i++ ) {
			if( numeroLigacoes[i] == 0 || numeroLigacoes[i] >= 2 ) {
				//remover o vertice
				vertices[i] = null;
				// procurar as arestas q tem este vertice e remove-las...
				removerArestas( i );
			}
		}
	}
	
	public static <T> Grafo<T> kruskalMST( Grafo<T> g, int[] visitado ) {
		// ordenar pelas arestas com menor peso
		Collections.sort( g.arestas );
		//int visitado[] = new int[ g.vertices.length ];
		Grafo<T> novoGrafo = new Grafo<T>();
		novoGrafo.addVertices( g.vertices );
		boolean ok = false;
		for( Aresta a : g.arestas ) {
			ok = true;
			for( int i=0; i<a.vi.length; i++ ) {
				if( visitado[ a.vi[i] ] >= 2 ) {
					ok = false;
				}
			}

			if( ok ) {
				for( int i=0; i<a.vi.length; i++ ) {
					visitado[ a.vi[i] ] = visitado[ a.vi[i] ] + 1;
				}
				novoGrafo.addAresta( a );
			}
		}
		return novoGrafo;
	}

	
}
