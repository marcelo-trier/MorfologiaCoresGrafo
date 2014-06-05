package br.graphcolormorphology;

public class ElementoEstruturante {
	// cross [ pontoIndex ] [ X/Y ]
	public static final int[][] cross = {
		{ 0, 0 },
		{ 0, -1 },
		{ 0, 1 },
		{ -1, 0 },
		{ 1, 0 },
	};
	
	// square[ PontoIndex ] [ X/Y ]
	public static final int [][] square = {
		{ 0, 0 }, // primeiro o central
		{ 0, -1 }, // o de cima
		{ 0, 1 }, // o de baixo
		{ -1, 0 }, // o do lado esq
		{ 1, 0 }, // lado dir
		{ -1, -1 }, // canto
		{ -1, 1 }, // canto
		{ 1, -1 }, // canto
		{ 1, 1 }, // canto
	};

	// abaixo uma tabela rápida de quem eh adjacente de quem....
	public static final boolean squareAdj[][] = {
		{ false, true, true, true, true, true, true, true, true },
		{ true, false, false, true, true, true, false, true, false },
		{ true, false, false, true, true, false, true, false, true },
		{ true, true, true, false, false, true, true, false, false },
		{ true, true, true, false, false, false, false, true, true },
		{ true, true, false, true, false, false, false, false, false },
		{ true, false, true, true, false, false, false, false, false },
		{ true, true, false, false, true, false, false, false, false },
		{ true, false, true, false, true, false, false, false, false },
	};
	
	public static int [][] offset = ElementoEstruturante.square;
	public static boolean [][] adjacente = ElementoEstruturante.squareAdj;

}
