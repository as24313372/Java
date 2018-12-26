import java.util.*;

public class test12261 {
    public static void main(String[] args) {
	    	Scanner scan = new Scanner( System.in );
			String input = scan.nextLine();
			twoDimension( input );
	}
	static void twoDimension( String in ){
		String oneDim[] = in.split( " " );
		int [][] intTwoDim = new int [oneDim.length][];
		for ( int i=0 ; i < oneDim.length ; i++ ){
			String a[] = oneDim[i].split(",");
			intTwoDim[i] = new int[a.length];
			for(int j = 0 ; j < a.length ; j++){
				intTwoDim[i][j] = Integer.parseInt(a[j]);
			} 
		}
		printTwoDim( intTwoDim );
	}
	static void printTwoDim( int[][] arr ){
		for ( int [] oneD: arr ){
			for ( int cont: oneD )
				System.out.print( cont+ " " );
				System.out.println();
		}
	}
}
