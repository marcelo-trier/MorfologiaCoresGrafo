package br.grafo_v2;

// este serah o grafo que serah inicializado a cada elemento estruturante...
public class GrafoEstatico {

	public static GrafoV2 getInstance() {
		return GRAFO_BASE;
	}
	
	// faz os vertices apontarem para o ElementoEstruturante
	public void setData( int[][] data ) {
		GrafoV2 g = getInstance();
		for( int i=0; i<data.length; i++ ) {
			VerticeV2<int[]> v = g.getVertices().get( i );
			v.setData( data[ i ] );
		}
	}
	
	// aqui jah deve criar o grafo com 9 vertices
	protected static final VerticeV2[] LISTA_V = {
		new VerticeV2<int[]>( 0, "a" ),
		new VerticeV2<int[]>( 1, "b" ),
		new VerticeV2<int[]>( 2, "c" ),
		new VerticeV2<int[]>( 3, "d" ),
		new VerticeV2<int[]>( 4, "e" ),
		new VerticeV2<int[]>( 5, "f" ),
		new VerticeV2<int[]>( 6, "g" ),
		new VerticeV2<int[]>( 7, "h" ),
		new VerticeV2<int[]>( 8, "i" )
	};

	protected static final ArestaV2[] LISTA_A = {
		new ArestaV2( LISTA_V[0], LISTA_V[1], 0 ),
		new ArestaV2( LISTA_V[0], LISTA_V[2], 0 ),
		new ArestaV2( LISTA_V[0], LISTA_V[3], 0 ),
		new ArestaV2( LISTA_V[0], LISTA_V[4], 0 ),
		
		new ArestaV2( LISTA_V[5], LISTA_V[3], 0 ),
		new ArestaV2( LISTA_V[3], LISTA_V[6], 0 ),
		new ArestaV2( LISTA_V[7], LISTA_V[4], 0 ),
		new ArestaV2( LISTA_V[4], LISTA_V[8], 0 ),

		new ArestaV2( LISTA_V[5], LISTA_V[1], 0 ),
		new ArestaV2( LISTA_V[1], LISTA_V[7], 0 ),
		new ArestaV2( LISTA_V[6], LISTA_V[2], 0 ),
		new ArestaV2( LISTA_V[2], LISTA_V[8], 0 ),

		new ArestaV2( LISTA_V[0], LISTA_V[5], 0 ),
		new ArestaV2( LISTA_V[3], LISTA_V[1], 0 ),
		new ArestaV2( LISTA_V[3], LISTA_V[2], 0 ),
		new ArestaV2( LISTA_V[0], LISTA_V[6], 0 ),

		new ArestaV2( LISTA_V[7], LISTA_V[0], 0 ),
		new ArestaV2( LISTA_V[1], LISTA_V[4], 0 ),
		new ArestaV2( LISTA_V[0], LISTA_V[8], 0 ),
		new ArestaV2( LISTA_V[2], LISTA_V[4], 0 ),
	};
	
	protected static final GrafoV2 GRAFO_BASE = new GrafoV2( LISTA_A, LISTA_V );

}
