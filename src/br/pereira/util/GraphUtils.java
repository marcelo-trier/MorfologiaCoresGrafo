package br.pereira.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import br.grafo.Aresta;
import br.grafo.Grafo;


public class GraphUtils {
// VERIFICAR:
// https://code.google.com/p/flexigraph/source/browse/trunk/src/gr/forth/ics/graph/io/gml/GmlReader.java?r=4

	/*
	public static Grafo<Integer> readGraphAdjacency( File f ) throws Exception {
		FileReader fr = new FileReader( f );
		BufferedReader br = new BufferedReader( fr );
		String linha;
		Grafo<Integer> novoGrafo = new Grafo<Integer>();
		Integer[] vertices = null;
		int valor;
		int numeroLinha = -1;
		while( ( linha = br.readLine() ) != null ) {
			linha = linha.trim();
			if( linha.equals("") || linha.charAt( 0 ) == '#' ) {
				continue;
			}
			numeroLinha++;
			String[] tokens = linha.split( "\\s+" ); // pega qualquer coisa: espaço, tab, quebra de linha, etc..
			if( vertices == null ) {
				vertices = new Integer[ tokens.length ];
				novoGrafo.addVertices( vertices );
			}
			for( int numeroColuna=0; numeroColuna<tokens.length; numeroColuna++ ) {
				valor = Integer.parseInt( tokens[ numeroColuna ] );
				if( valor != -1 ) {
					Aresta a = new Aresta( numeroLinha, numeroColuna, valor );
					novoGrafo.addAresta( a );
				}
			}
		}
		br.close();
		return novoGrafo;
	}
	
	public static <T> Grafo<T> readGMLFile( String path ) {
		return null;
	}
	
	public static <T> void writeGMLFile(Grafo<T> g) throws IOException {
		GerenteArquivos ga = GerenteArquivos.getInstance();
		FileWriter salvar = new FileWriter(ga.getSaveFile());
		String msg = "\n\n";
		msg += "graph [\n\n";
		msg += "  directed 0\n\n";
		salvar.write(msg);

		for (int i = 0; i < g.getVertices().length; i++) {
			msg = "  node [\n";
			msg += "	id " + i + "\n";
			msg += "	label \"nodo-"+i+"\" \n";
			msg += "  ]\n\n";
			salvar.write( msg );
		}

		salvar.write( "\n\n" );
		for (Aresta a : g.getArestas()) {
			msg = "  edge [\n";
			msg += " 	source " + ( a.vi[0] ) + "\n";
			msg += " 	target " + ( a.vi[1] ) + "\n";
			msg += " 	label \"" + ((int) a.weight ) + "\" \n";
			msg += "  ]\n\n";
			salvar.write( msg );
		}
		salvar.write("]\n");
		salvar.close();
	} */
}
