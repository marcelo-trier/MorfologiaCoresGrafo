package br.grafo.mst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import br.grafo.Aresta;
import br.grafo.Grafo;
import br.grafo.Vertice;
import br.pereira.util.ImgUtil;

public class MST extends Grafo {
	ConjuntoArvores subArvores = null;
	VerticePool pool = null;
	ArrayList<Aresta> Q = new ArrayList<Aresta>(); // a lista q conterah as respostas

	public MST( Grafo g ) {
		super( g.getArestas(), g.getVertices() );
		pool = new VerticePool( g.getVertices().size() );
		subArvores = new ConjuntoArvores();
	}

	public String toString() {
		return "MST::\n" + super.toString();
	}

	public void makeSet() {
		subArvores.init( pool.getPool() );

		for( int i=0; i<getVertices().size(); i++ ) {
			List<Vertice> l = pool.get( i );
			l.clear();
			l.add( getVertices().get( i ) );
			subArvores.add( l );
		}
		Q.clear();
	}

	
	public int removeTrunks() {
		for( Aresta a : arestas ) {
			a.from.numLigacoes++;
			a.to.numLigacoes++;
			if( a.from.numLigacoes > 1 ) {
				vertices.remove( a.from );
			}
			if( a.to.numLigacoes > 1 ) {
				vertices.remove( a.to );
			}
		}
		arestas.clear();
		for( Vertice v : vertices ) {
			v.numLigacoes = 0;
		}
		linkAll();
		return vertices.size();
	}

	public void linkAll() {
		ArestaPool.getInstance().reset();
		for( int i=0; i<vertices.size(); i++ ) {
			for( int j=i+1; j<vertices.size(); j++ ) {
				Aresta a = ArestaPool.getInstance().getAresta();
				a.from = vertices.get( i );
				a.to = vertices.get( j );
				a.weight = ImgUtil.getRGBDistance( (int[])a.from.getData(), (int[])a.to.getData() );
				arestas.add( a );
			}
		}
	}
	
	public int[] getMinRGB__OLLLDDD( int[][] elEstr, int oResult[] ) throws Exception {
		// no inicio jah foi feito a atualizacao dos ponteiros para o EE
		do {
			runKruskal();
		} while( removeTrunks() > 2 );

		float d1 = ImgUtil.getRGBDistance( (int[]) vertices.get(0).getData(), ImgUtil.pixPreto );
		float d2 = ImgUtil.getRGBDistance( (int[]) vertices.get(1).getData(), ImgUtil.pixPreto );

		int vi = 0;
		// o delta mais próximo da cor de referência é o menor...
		if( d1 > d2 ) {
			vi = 1;
		}
		int[] res = (int[]) vertices.get(vi).getData();
		System.arraycopy(res, 0, oResult, 0, oResult.length );
		return oResult;
	}
	
	public void runKruskal() {
		makeSet();
		// ordenar pelas arestas com menor peso
		Collections.sort( arestas );

		for( Aresta a : arestas ) {
			List l1 = subArvores.findConjunto( a.from );
			List l2 = subArvores.findConjunto( a.to );
			if( l1 != l2 ) {
				Q.add( a );
				subArvores.union( l1, l2 );
			}
		}
		arestas.clear();
		arestas.addAll( Q );
	}
}
