package br.graphcolormorphology.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import br.graph.Grafo;
import br.graph.Aresta;

public class GraphUtils {

	public static <T> void writeGMLFile(Grafo<T> g) throws IOException {
		GerenteArquivos ga = new GerenteArquivos(null);
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
	}
}
