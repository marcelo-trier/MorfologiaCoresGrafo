package br.grafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MST extends Grafo {

	List<List<Vertice>> lConj = new ArrayList<List<Vertice>>();
	
	List<Vertice>[] conjunto_v2 = new List[ vertices.size() ];
	ArrayList<Aresta> listA = new ArrayList<Aresta>();

	public MST( Grafo g ) {
		super( g.arestas, g.vertices );
		for( int i=0; i<conjunto_v2.length; i++ ) {
			conjunto_v2[ i ] = new ArrayList< Vertice >();
		}
	}

	public String toString() {
//		msg += listA;
		return "MST::\n" + super.toString();
	}

	public List findConjunto( Vertice v ) {
		for( List<Vertice> lv : lConj ) {
			if( lv.contains( v ) )
				return lv;
		}
		return null;
	}
	
	public List newList() {
		for( List<Vertice> lv : conjunto_v2 ) {
			if( lv.isEmpty() ) {
				return lv;
			}
		}
		return null;
	}
	
	public void union( List l1, List l2 ) {
		l1.addAll( l2 );
		l2.clear();
		lConj.remove( l2 );
	}
	
	public void makeSet() {
		for( int i=0; i<conjunto_v2.length; i++ ) {
			List<Vertice> l = conjunto_v2[ i ];
			l.clear();
			l.add( vertices.get( i ) );
			lConj.add( l );
		}

		listA.clear();
	}
		
	public void runKruskal() {
		makeSet();
		// ordenar pelas arestas com menor peso
		Collections.sort( arestas );

		for( Aresta a : arestas ) {
			List l1 = findConjunto( a.from );
			List l2 = findConjunto( a.to );
			if( l1 != l2 ) {
				listA.add( a );
				union( l1, l2 );
			}
		}
		arestas.clear();
		arestas.addAll( listA );
	}
}
