package br.morfcorgraf;

import java.awt.image.BufferedImage;
import java.io.IOException;

import br.grafo.GrafoEstatico;
import br.grafo.mst.MST;
import br.pereira.util.ArrayUtils;
import br.pereira.util.ImgUtil;

public class Morfologia {

	public enum TAREFA {
		EROSAO, 
		DILATACAO,
		EROSAO_GRAFO_MST,
		DILATACAO_GRAFO_MST,
		SUBTRACAO,
		SOMA,
		SAVE_BUFF_ORIGINAL, // retorna o resultado do arrTmp --> para o --> arrOrig
	}
	
	BufferedImage imgOrig;
	int _w=0, _h=0;
	int[][][] arrImg;
	int[][][] arrTmp;
	int[][] EE;
	TAREFA umaTarefa = TAREFA.EROSAO;
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
				case EROSAO:
					arrTmp[ x ][ y ] = ArrayUtils.getMinRGB( EE, arrTmp[ x ][ y ] );
					break;
				case DILATACAO:
					arrTmp[ x ][ y ] = ArrayUtils.getMaxRGB( EE, arrTmp[ x ][ y ] );
					break;
				case EROSAO_GRAFO_MST:
					arrTmp[ x ][ y ] = gMst.getMinRGB( EE, arrTmp[ x ][ y ] );
					break;
				case DILATACAO_GRAFO_MST:
					break;
				default:
					throw new Exception( "escolha erradaaa!!!" );
				}
			}
		}

		for( int y=1; y<_h-1; y++ ) {
			for( int x=1; x<_w-1; x++ ) {
				for( int cor=0; cor<3; cor++ ) {
					arrImg[x][y][cor] = arrTmp[x][y][cor];
				}
			}
		}
	}
	
	public BufferedImage getImage() throws IOException {
		return ImgUtil.array3dToRGBImage( arrImg );
	}
}


