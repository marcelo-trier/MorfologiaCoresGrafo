package br.graph.testes;

import java.io.File;

import br.grafo_v2.GrafoV2;
import br.grafo_v2.MSTV2;
import br.graphcolormorphology.util.GerenteArquivos;

public class Teste4_GrafoRead {

	public static void main( String[] args ) throws Exception {
		File f = GerenteArquivos.getInstance().getOpenFile();
		GrafoV2 g = new GrafoV2();
		g.loadFromFile( f );
		System.out.println( g );

		MSTV2 mst = new MSTV2( g );
		mst.runKruskal();
		System.out.println( mst );
		//mst.removeTroncos();
	}
}
