package br.graphcolormorphology.util;

public class ArrayUtils {

	public static int[] getMinRGB( int[][] arr, int oResult[] ) {
		final int[] min = { 999, 999, 999 }; 
		int[] result = min;
		for( int i=0; i<arr.length; i++ ) {
			for( int cor=0; cor<arr[i].length; cor++ ) {
				if( arr[i][cor] > result[ cor ] )
					break;
				if( arr[i][cor] == result[ cor ] )
					continue;
				if( arr[i][cor] < result[ cor ] )
					result = arr[ i ];
				break;
			}
		}
		for( int i=0; i<oResult.length; i++ ) {
			oResult[i] = result[i];
		}
		return oResult;
	}
	
	public static int[] getMaxRGB( int[][] arr, int oResult[] ) {
		final int[] max = { -1, -1, -1 };
		int[] result = max;
		for( int i=0; i<arr.length; i++ ) {
			for( int cor=0; cor<arr[i].length; cor++ ) {
				if( arr[i][cor] < result[ cor ] )
					break;
				if( arr[i][cor] == result[ cor ] )
					continue;
				if( arr[i][cor] > result[ cor ] )
					result = arr[ i ];
				break;
			}
		}
		for( int i=0; i<oResult.length; i++ ) {
			oResult[i] = result[i];
		}
		return oResult;
	}

	public static int[] getMinRGB__OLD( int[][] arr ) {
		final int[] min = { 999, 999, 999 };
		int[] result = min;
		for( int i=0; i<arr.length; i++ ) {
			if( arr[i][0] < result[0] ) {
				result = arr[i];
				continue;
			} 
			if( arr[i][0] == result[0] && arr[i][1] < result[1] ) {
				result = arr[i];
				continue;
			}
			if( arr[i][0]==result[0] && arr[i][1]==result[1] && arr[i][2] < result[2] ) {
				result = arr[i];
				continue;
			}
				
		}
		return result;
	}

	public static int[] getMaxRGB__OLD( int[][] arr ) {
		final int[] max = { -1, -1, -1 };
		int[] result = max;
		for( int i=0; i<arr.length; i++ ) {
			if( arr[i][0] > result[0] ) {
				result = arr[i];
				continue;
			} 
			if( arr[i][0] == result[0] && arr[i][1] > result[1] ) {
				result = arr[i];
				continue;
			}
			if( arr[i][0]==result[0] && arr[i][1]==result[1] && arr[i][2] > result[2] ) {
				result = arr[i];
				continue;
			}
		}
		return result;
	}
	
	public static byte getMin( byte[] arr ) {
		byte menor = (byte)0xFF;
		for( byte b : arr ) {
			if( b < menor )
				menor = b;
		}
		return menor;
	}
	
	public static int getMin( int[] arr ) {
		int menor = 9999;
		for( int a : arr ) {
			if( a < menor )
				menor = a;
		}
		if( menor != 128 )
			return menor;
		else
			return menor;
	}
	

	public static int getMax( int[] arr ) {
		int max = -99999;
		for( int a : arr ) {
			if( a > max )
				max = a;
		}
		return max;
	}
	
	public static void fillOutBoard( int in[][], int out[][] ) {
		int h = in[0].length;
		int w = in.length;
		int outX = h-1;
		int outY = w-1;
		for( int i=0; i<w; i++ ) {
			out[ i ][ 0 ] = in[ i ][ 0 ];
			out[ i ][ outX ] = in[ i ][ outX ];
		}
		
		for( int j=0; j<h; j++ ) {
			out[ 0 ][ j ] = in[ 0 ][ j ];
			out[ outY ][ j ] = in[ outY ][ j ];
		}
	}
	
	
}
