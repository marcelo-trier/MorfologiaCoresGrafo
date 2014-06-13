package br.grafo_v2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MSTV2 extends GrafoV2 {

	List<List<VerticeV2>> lConj = new ArrayList<List<VerticeV2>>();
	
	List<VerticeV2>[] conjunto_v2 = new List[ vertices.size() ];
	ArrayList<ArestaV2> listA = new ArrayList<ArestaV2>();

	public MSTV2( GrafoV2 g ) {
		super( g.arestas, g.vertices );
		for( int i=0; i<conjunto_v2.length; i++ ) {
			conjunto_v2[ i ] = new ArrayList< VerticeV2 >();
		}
	}

	public String toString() {
//		msg += listA;
		return "MST::\n" + super.toString();
	}

	public List findConjunto( VerticeV2 v ) {
		for( List<VerticeV2> lv : lConj ) {
			if( lv.contains( v ) )
				return lv;
		}
		return null;
	}
	
	public List newList() {
		for( List<VerticeV2> lv : conjunto_v2 ) {
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
			List<VerticeV2> l = conjunto_v2[ i ];
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

		for( ArestaV2 a : arestas ) {
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
