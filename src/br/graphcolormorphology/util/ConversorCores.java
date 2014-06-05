package br.graphcolormorphology.util;

public class ConversorCores {

	enum ColorChannel {
		RED, GREEN, BLUE
	}
	
	public static float normalizeRGB( float umCanal ) {
		float result = umCanal / 255f;
		return result;
	}
	
	public static float normalizeRGB( int umCanal ) {
		float result = umCanal;
		
		result = result / 255f;
		return result;
	}
	
	public static float[] normalizeRGB( int[] pix, float[] result ) {
		if( result == null )
			result = new float[ pix.length ];

		for( int i=0; i<3; i++ ) {
			result[i] = normalizeRGB( pix[ i ] );
		}
		
		return result;
	}
	
	public static float getMax(float f1, float f2, float f3) {
		float valor = 0;
		if (f1 > f2 && f1 > f3)
			valor = f1;
		if (f2 > f1 && f2 > f3)
			valor = f2;
		if (f3 > f2 && f3 > f1)
			valor = f3;
		return valor;
	}

	public static float getMin(float f1, float f2, float f3) {
		float valor = 0;
		if (f1 < f2 && f1 < f3)
			valor = f1;
		if (f2 < f3 && f2 < f1)
			valor = f2;
		if (f3 < f2 && f3 < f1)
			valor = f3;
		return valor;
	}
	
	public static float rgb2gray( int[] pix ) {
		float cinza;
		float r=pix[0]; 
		float g=pix[1]; 
		float b=pix[2];
		// formula from wikipedia
		cinza = r*0.3f+g*0.59f+b*0.11f;
		
		return cinza;
	}
	
	public static float getLightness( int r, int g, int b ) {
		// código retirado de: http://www.johndcook.com/blog/2009/08/24/algorithms-convert-color-grayscale/
		float max = getMax( r, g, b );
		float min = getMin( r, g, b );
		float res = ( max + min ) / 2;
		return res;
	}

	public static float getLuminosity( int r, int g, int b ) {
		// codigo retirado de: http://www.johndcook.com/blog/2009/08/24/algorithms-convert-color-grayscale/
		float res = 0.21f*r;
		res += 0.72f*g;
		res += 0.07f*b;
		return res;
	}
	
	public static float[] getColor( int[] rgb, float[] result ) {
		if( result == null )
			result = new float[ rgb.length ];

		float cinza = rgb2gray( rgb );
		result[0] = result[1] = result[2] = cinza;
		return result;
	}
	
	public static float[] rgb2hsl( int[] rgb, float[] result ) {
		if( result == null )
			result = new float[ rgb.length ];

		float r, g, b;
		r = (float)rgb[0] / 255f;
		g = (float)rgb[1] / 255f;
		b = (float)rgb[2] / 255f;
		float max = getMax(r, g, b);
		float min = getMin(r, g, b);
		result[0] = result[1] = result[2] = 0;
		result[2] = (max + min) / 2;
		float d = max - min;

		if (d == 0)
			return result;

		if ( result[2] > 0.5f)
			result[1] = d / (2 - max - min);
		else
			result[1] = d / (max + min);

		float tmp = 0;
		if( max == r )
			 tmp = (g - b) / d + (g < b ? 6 : 0);
		if( max == g )
			tmp = (b - r) / d + 2;
		if( max == b )
			result[0] = (r - g) / d + 4;

		tmp /= 6;
		result[0] = tmp;

		return result;
	}

	public int[] hsv2rgb( int[] rgb ) {
		return null;
	}
	
}
