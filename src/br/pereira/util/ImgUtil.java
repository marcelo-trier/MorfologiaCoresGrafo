package br.pereira.util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.DataBufferShort;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.xml.crypto.Data;

public class ImgUtil {

	public static float getRGBDistance(int[] p1, int[] p2) {
		int somatorio = 0;
		for (int i = 0; i < p1.length; i++) {
			somatorio += (int) Math.pow(p1[i] - p2[i], 2);
		}
		return (float) Math.sqrt(somatorio);
	}

	public static BufferedImage array3dToRGBImage( int[][][] arrRGB ) {
		int w = arrRGB.length;
		int h = arrRGB[0].length;
		BufferedImage out = new BufferedImage( w, h, IMAGES_TYPE.TYPE_INT_RGB.valor );
		WritableRaster rOut = out.getRaster();
		for( int y=0; y<h; y++ ) {
			for( int x=0; x<w; x++ ) {
				rOut.setPixel( x, y, arrRGB[ x ][ y ] );
			}
		}
		
		return out;
	}
	
	public static BufferedImage array2dToGrayImage(int[][] arrImg)
			throws IOException {
		int w = arrImg.length;
		int h = arrImg[0].length;
		int arrOut[] = new int[w * h];
		int yOffset = 0;
		for (int y = 0; y < h; y++) {
			yOffset = y * w;
			for (int x = 0; x < w; x++) {
				arrOut[x + yOffset] = (byte) arrImg[x][y];
			}
		}

		BufferedImage out = new BufferedImage(w, h,
				BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster rOut = out.getRaster();
		rOut.setPixels(0, 0, w, h, arrOut);

		return out;
	}

	
	static int[] pixTmp = { 0, 0, 0, 0 };
	public static int[][][] getRGBArray(BufferedImage img, int[][][] arrImg)
			throws Exception {
		int w = img.getWidth();
		int h = img.getHeight();
		int pixLen = 0;
		IMAGES_TYPE tipo = IMAGES_TYPE.getType(img.getType());
		pixLen = tipo.lenght();
		
		if (arrImg == null)
			arrImg = new int[w][h][3];
		else if (arrImg.length < w || arrImg[0] == null || arrImg[0].length < h
				|| arrImg[0][0] == null)
			throw new Exception("erros nos tamanhos da imagem e do array...");

		Raster rIn = img.getData();
		//DataBuffer db = rIn.getDataBuffer();

		pixLen = ( pixLen > 3 ) ? 3 : pixLen;
		for( int y=0; y<h; y++ ){
			for( int x=0; x<w; x++ ) {
				pixTmp = rIn.getPixel( x, y, pixTmp );
				System.arraycopy( pixTmp, 0, arrImg[x][y], 0, pixLen );
			}
		}
		return arrImg;
	}
	
	public static BufferedImage rgb2gray(BufferedImage image) {
		if (image.getType() == BufferedImage.TYPE_BYTE_GRAY)
			return image;

		int w = image.getWidth();
		int h = image.getHeight();
		BufferedImage result = new BufferedImage(w, h,
				BufferedImage.TYPE_BYTE_GRAY);
		Graphics g = result.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
		return result;
	}

	public static BufferedImage rgb2gray__OLD(BufferedImage img) {
		Raster rImg = img.getData();
		int w = img.getWidth();
		int h = img.getHeight();

		BufferedImage out = new BufferedImage(w, h,
				BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster rOut = out.getRaster();
		int[] pix = { 0, 0, 0, 0 };
		int[] pixCinza = { 0 };
		float cinza = 0;
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				pix = rImg.getPixel(x, y, pix);
				cinza = ConversorCores.rgb2gray(pix);
				pixCinza[0] = (int) cinza;
				rOut.setPixel(x, y, pixCinza);
			}
		}

		return out;
	}

	public static BufferedImage subtrai(BufferedImage img1, BufferedImage img2)
			throws Exception {
		BufferedImage out = null;
		Raster rImg1 = img1.getData();
		Raster rImg2 = img2.getData();

		int w1 = img1.getWidth();
		int h1 = img1.getHeight();
		int w2 = img2.getWidth();
		int h2 = img2.getHeight();

		int dW = w1 - w2;
		int dH = h1 - h2;

		if (w1 != w2 || h1 != h2) {
			// sao diferentes os delta... significa que as imagens sao
			// diferentesss..
			throw new Exception("imagens de tamanhos diferentes! Verificar!");
		}

		// int maxW = (w1 > w2) ? w1 : w2;
		// int maxH = (h1 > h2) ? h1 : h2;

		out = new BufferedImage(w1, h1, BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster rOut = out.getRaster();
		int pix[] = { 0, 0, 0, 255 };
		int pix1[] = { 0, 0, 0, 255 };
		int pix2[] = { 0, 0, 0, 255 };
		for (int y = 0; y < h1; y++) {
			for (int x = 0; x < w1; x++) {
				pix1 = rImg1.getPixel(x, y, pix1);
				pix2 = rImg2.getPixel(x, y, pix2);

				for (int rgb = 0; rgb < 3; rgb++) {
					pix[rgb] = pix1[rgb] - pix2[rgb];
					pix[rgb] = ( pix[rgb] < 0 ) ? 0 : pix[rgb];
				}
				rOut.setPixel(x, y, pix);
			}
		}

		return out;
	}

	// mistura os pixels coloridos de várias imagens em apenas uma imagem...
	public static BufferedImage mix(BufferedImage imgArr[]) {
		int w = imgArr[0].getWidth();
		int h = imgArr[0].getHeight();
		BufferedImage out = new BufferedImage(w, h, imgArr[0].getType());
		WritableRaster outRaster = out.getRaster();
		int[] pix = { 0, 0, 0, 255 };
		for (BufferedImage img : imgArr) {
			Raster raster = img.getData();
			try {
				for (int y = 0; y < h; y++) {
					for (int x = 0; x < w; x++) {
						pix = raster.getPixel(x, y, pix);
						int soma = pix[0] + pix[1] + pix[2];
						if (soma > 5) // ruídos (valores muito baixos) serão
										// desconsiderados
							outRaster.setPixel(x, y, pix);
					}
				}

			} catch (Exception ex) {
				int aa = 0;
				aa++;
			}
		}

		return out;
	}

	public enum IMAGES_TYPE {
		TYPE_3BYTE_BGR(BufferedImage.TYPE_3BYTE_BGR, 3), TYPE_4BYTE_ABGR(
				BufferedImage.TYPE_4BYTE_ABGR, 4), TYPE_4BYTE_ABGR_PRE(
				BufferedImage.TYPE_4BYTE_ABGR_PRE, 4), TYPE_BYTE_BINARY(
				BufferedImage.TYPE_BYTE_BINARY, 1), TYPE_BYTE_GRAY(
				BufferedImage.TYPE_BYTE_GRAY, 1), TYPE_BYTE_INDEXED(
				BufferedImage.TYPE_BYTE_INDEXED, 1), TYPE_CUSTOM(
				BufferedImage.TYPE_CUSTOM, 4), TYPE_INT_ARGB(
				BufferedImage.TYPE_INT_ARGB, 4), TYPE_INT_ARGB_PRE(
				BufferedImage.TYPE_INT_ARGB_PRE, 4), TYPE_INT_BGR(
				BufferedImage.TYPE_INT_BGR, 3), TYPE_INT_RGB(
				BufferedImage.TYPE_INT_RGB, 3), TYPE_USHORT_555_RGB(
				BufferedImage.TYPE_USHORT_555_RGB, 3), TYPE_USHORT_565_RGB(
				BufferedImage.TYPE_USHORT_565_RGB, 3), TYPE_USHORT_GRAY(
				BufferedImage.TYPE_USHORT_GRAY, 3);

		int valor;
		int len;

		IMAGES_TYPE(int v, int l) {
			valor = v;
			len = l;
		}

		public static IMAGES_TYPE getType(int v) {
			for (IMAGES_TYPE it : IMAGES_TYPE.values()) {
				if (it.valor == v)
					return it;
			}
			return null;
		}

		public int lenght() {
			return len;
		}
	}
}
