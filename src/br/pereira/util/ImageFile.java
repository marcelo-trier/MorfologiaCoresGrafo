package br.pereira.util;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

// facilidades para trabalhar com arquivos de imagens... ;o)
public class ImageFile extends GerenteArquivos {

	public static final ImageFile instance = new ImageFile();
	
	protected ImageFile() {}
	
	public static ImageFile getInstance() {
		return instance;
	}
	
	public void save( BufferedImage img ) throws Exception {
		File salvar = getInstance().getSaveFile();
		ImageIO.write(img, "bmp", salvar);
	}
	
	public BufferedImage carregaImagem() throws Exception {
		File file = getOpenFile();
		return ImageIO.read(file);
	}

	public BufferedImage[] carregaImagens() throws Exception {
		File files[] = carregaArquivos();

		BufferedImage imgs[] = new BufferedImage[ files.length  ];
		for( int i=0; i<files.length; i++ ) {
			imgs[i] = ImageIO.read( files[i] );
		}
		return imgs;
	}
}
