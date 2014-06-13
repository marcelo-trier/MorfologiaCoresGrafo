package br.grafo.testes;

import java.io.File;
import br.graph.Grafo;
import br.pereira.util.GerenteArquivos;
import br.pereira.util.GraphUtils;

public class TesteGrafoRead {

	public static void main(String[] args) throws Exception {
		GerenteArquivos ga = GerenteArquivos.getInstance();
		File f = ga.getOpenFile();
		Grafo<Integer> g = GraphUtils.readGraphAdjacency( f );
		GraphUtils.writeGMLFile( g );
	}

}
