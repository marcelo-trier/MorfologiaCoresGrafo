package br.grafo.testes;

import java.io.File;

import br.grafo.Grafo;
import br.grafo.MST;
import br.pereira.util.GerenteArquivos;

public class Teste4_GrafoRead {

	public static void main( String[] args ) throws Exception {
		File f = GerenteArquivos.getInstance().getOpenFile();
		Grafo g = new Grafo();
		g.loadFromFile( f );
		System.out.println( g );

		MST mst = new MST( g );
		mst.runKruskal();
		System.out.println( mst );
		//mst.removeTroncos();
	}
}
