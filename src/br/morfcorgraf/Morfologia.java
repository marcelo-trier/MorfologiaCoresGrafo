package br.morfcorgraf;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import br.grafo.GrafoEstatico;
import br.grafo.mst.MST;
import br.pereira.util.ArrayUtils;
import br.pereira.util.ImgUtil;

public class Morfologia {

	public enum RefColor {
		BLACK, WHITE, BLUE, YELLOW, RED
	}
	
	public enum TAREFA {
		E_LEX, 
		D_LEX,
		E_GRAPH,
		D_GRAPH,
		SUBTRACAO,
		SOMA,
		SAVE_BUFF_ORIGINAL, // retorna o resultado do arrTmp --> para o --> arrOrig
	}
	
	int[] colorReference = { 0, 0, 0 };
	public void setColorReference( Color umaCor ) {
		colorReference[0] = umaCor.getRed();
		colorReference[1] = umaCor.getGreen();
		colorReference[2] = umaCor.getBlue();
	}
	
	BufferedImage imgOrig;
	int _w=0, _h=0;
	int[][][] arrImg;
	int[][][] arrTmp;
	int[][] EE;
	TAREFA umaTarefa = TAREFA.E_LEX;
	MST gMst;
//	GrafoMST gMst;
	
	public Morfologia( BufferedImage img ) throws Exception {
		loadImage( img );

		// o tamanho dos pixels vizinhos eh igual ao tamanho estruturante... 
		EE = new int[ ElementoEstruturante.offset.length ][ 3 ]; // rgb
		GrafoEstatico.setData( EE ); // aqui faz a ligacao dos vertices com o ElemEstrut
		gMst = new MST( GrafoEstatico.newGrafo() );
		
		// TODO: aqui vai preencher as bordas??
		//ArrayUtils.fillOutBoard( arrImg, arrOut );
	}

	public void setTask( TAREFA t ) {
		umaTarefa = t;
	}

	private void loadImage( BufferedImage img ) throws Exception {
		imgOrig = img;
		_w = imgOrig.getWidth();
		_h = imgOrig.getHeight();

		arrImg = new int[ _w ][ _h ][ 3 ]; // rgb
		arrTmp = new int[ _w ][ _h ][ 3 ];

		arrImg = ImgUtil.getRGBArray( imgOrig, arrImg );
	}
	
	public void execute( int interacoes ) throws Exception {
		while( interacoes-- > 0 ) {
			execute();
		}
	}

	public static int[][] getElemEstrut( int[][][] imgArr, int x, int y, int[][] buffOut ) throws Exception {
		if( buffOut == null ) {
			throw new Exception( "Elemento Estruturante nao criado..." );
		}
		
		for( int ee=0; ee<ElementoEstruturante.offset.length; ee++ ) { // para cada item do elemento estruturante
			int xViz = x + ElementoEstruturante.offset[ ee ][ 0 ]; // pega a coordenada x do elem.estr
			int yViz = y + ElementoEstruturante.offset[ ee ][ 1 ]; // pega a coordenada y do elem.estr.

			for( int canal=0; canal < buffOut[0].length; canal++ ) {// para cada canal [R, G, B]
				buffOut[ee][canal] = imgArr[ xViz ][ yViz ][ canal ]; // agora sim copia do buffer para o elem.estrut
			}
		}
		
		return buffOut;
	}
	
	public void execute() throws Exception {
		// tem q iniciar em 1 por causa do elemento estruturante... ;o)
		for( int y=1; y<_h-1; y++ ) {
			for( int x=1; x<_w-1; x++ ) {
				EE = getElemEstrut( arrImg, x, y, EE );
				switch( umaTarefa ) {
				case E_LEX:
					arrTmp[ x ][ y ] = ArrayUtils.getMinRGB( EE, arrTmp[ x ][ y ] );
					break;
				case D_LEX:
					arrTmp[ x ][ y ] = ArrayUtils.getMaxRGB( EE, arrTmp[ x ][ y ] );
					break;
				case E_GRAPH:
				case D_GRAPH:
//					arrTmp[ x ][ y ] = getMinRGB_Mst( EE, arrTmp[ x ][ y ] );
					arrTmp[ x ][ y ] = getRGB_MST( EE, arrTmp[ x ][ y ], umaTarefa );
					break;
				default:
					throw new Exception( "escolha erradaaa!!!" );
				}
			}
		}

		int[][][] aux = arrTmp;
		arrTmp = arrImg;
		arrImg = aux;

/*		
		for( int y=1; y<_h-1; y++ ) {
			for( int x=1; x<_w-1; x++ ) {
				for( int cor=0; cor<3; cor++ ) {
					arrImg[x][y][cor] = arrTmp[x][y][cor];
				}
			}
		} */
	}

	
	public int[] getRGB_MST( int[][] elEstr, int oResult[], TAREFA umaT ) throws Exception {
		// no inicio jah foi feito a atualizacao dos ponteiros para o EE
		do {
			gMst.runKruskal();
		} while( gMst.removeTrunks() > 2 );


		int[] rgb1 = ( int[] )gMst.getVertices().get( 0 ).getData();
		int[] rgb2 = ( int[] )gMst.getVertices().get( 1 ).getData();
		float d1 = ImgUtil.getRGBDistance( rgb1, colorReference );
		float d2 = ImgUtil.getRGBDistance( rgb2, colorReference );
		
		int[] res = rgb1;

		// pega quem for menor...
		if( umaT == TAREFA.E_GRAPH && d1 > d2 ) {
			res = rgb2;
		}
		
		// pega quem for maior...
		if( umaT == TAREFA.D_GRAPH && d1 < d2 ) {
			res = rgb2;
		}
		
		System.arraycopy(res, 0, oResult, 0, res.length );
		return oResult;
	}
	
	public int[] getMinRGB_Mst__OOOLLDDDD( int[][] elEstr, int oResult[] ) throws Exception {
		// no inicio jah foi feito a atualizacao dos ponteiros para o EE
		do {
			gMst.runKruskal();
		} while( gMst.removeTrunks() > 2 );


		int[] rgb1 = ( int[] )gMst.getVertices().get( 0 ).getData();
		int[] rgb2 = ( int[] )gMst.getVertices().get( 1 ).getData();
		float d1 = ImgUtil.getRGBDistance( rgb1, colorReference );
		float d2 = ImgUtil.getRGBDistance( rgb2, colorReference );
		
		int[] res = rgb1;
		// o delta mais próximo da cor de referência é o menor...
		if( d1 > d2 ) {
			res = rgb2;
		}
		
		System.arraycopy(res, 0, oResult, 0, res.length );
		return oResult;
	}
	
	public BufferedImage getImage() throws IOException {
		return ImgUtil.array3dToRGBImage( arrImg );
	}
}


