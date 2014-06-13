package br.grafo;

import java.util.ArrayList;

public class MSTPereira extends MST {

	public static MSTPereira instance = null;

	// aqui jah deve criar o grafo com 9 vertices
	public static Vertice[] listaV = new Vertice[ 9 ];
	public static ArrayList<Aresta> listaA = new ArrayList<Aresta>();

	public static MSTPereira getInstance() {
		if( instance == null ) {
			Grafo g = new Grafo();
			g.initVertices( listaV );
			// TODO: ARESTAS
			instance = new MSTPereira( g );
		}
		return instance;
	}
	
	public void initVertices() {
		if( listaV[0] == null ) {
			char tmp = 'a';
			for( int i=0; i<listaV.length; i++ ) {
				listaV[ i ] = new Vertice( i, ""+(tmp++) );
			}
		}

		for( Vertice v : listaV ) {
			// TODO:
		}
	}
	
	protected MSTPereira( Grafo g ) {
		super( g );
		initVertices();
		//initArestas();
		
	}
	
	
	
}
