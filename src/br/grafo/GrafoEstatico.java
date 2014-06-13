package br.grafo;

// este serah o grafo que serah inicializado a cada elemento estruturante...
public class GrafoEstatico {

	public static Grafo getInstance() {
		return GRAFO_BASE;
	}
	
	// faz os vertices apontarem para o ElementoEstruturante
	public void setData( int[][] data ) {
		Grafo g = getInstance();
		for( int i=0; i<data.length; i++ ) {
			Vertice<int[]> v = g.getVertices().get( i );
			v.setData( data[ i ] );
		}
	}
	
	// aqui jah deve criar o grafo com 9 vertices
	protected static final Vertice[] LISTA_V = {
		new Vertice<int[]>( 0, "a" ),
		new Vertice<int[]>( 1, "b" ),
		new Vertice<int[]>( 2, "c" ),
		new Vertice<int[]>( 3, "d" ),
		new Vertice<int[]>( 4, "e" ),
		new Vertice<int[]>( 5, "f" ),
		new Vertice<int[]>( 6, "g" ),
		new Vertice<int[]>( 7, "h" ),
		new Vertice<int[]>( 8, "i" )
	};

	protected static final Aresta[] LISTA_A = {
		new Aresta( LISTA_V[0], LISTA_V[1], 0 ),
		new Aresta( LISTA_V[0], LISTA_V[2], 0 ),
		new Aresta( LISTA_V[0], LISTA_V[3], 0 ),
		new Aresta( LISTA_V[0], LISTA_V[4], 0 ),
		
		new Aresta( LISTA_V[5], LISTA_V[3], 0 ),
		new Aresta( LISTA_V[3], LISTA_V[6], 0 ),
		new Aresta( LISTA_V[7], LISTA_V[4], 0 ),
		new Aresta( LISTA_V[4], LISTA_V[8], 0 ),

		new Aresta( LISTA_V[5], LISTA_V[1], 0 ),
		new Aresta( LISTA_V[1], LISTA_V[7], 0 ),
		new Aresta( LISTA_V[6], LISTA_V[2], 0 ),
		new Aresta( LISTA_V[2], LISTA_V[8], 0 ),

		new Aresta( LISTA_V[0], LISTA_V[5], 0 ),
		new Aresta( LISTA_V[3], LISTA_V[1], 0 ),
		new Aresta( LISTA_V[3], LISTA_V[2], 0 ),
		new Aresta( LISTA_V[0], LISTA_V[6], 0 ),

		new Aresta( LISTA_V[7], LISTA_V[0], 0 ),
		new Aresta( LISTA_V[1], LISTA_V[4], 0 ),
		new Aresta( LISTA_V[0], LISTA_V[8], 0 ),
		new Aresta( LISTA_V[2], LISTA_V[4], 0 ),
	};
	
	protected static final Grafo GRAFO_BASE = new Grafo( LISTA_A, LISTA_V );

}
