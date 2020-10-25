import java.util.ArrayList;

public class CompEngine {
	
	public static int[][] calculateMaxtrixMultiplication(int[][] A, int[][] B) {
		
		int ArowSize, AcolSize, BcolSize, BrowSize;
		AcolSize = A[0].length;
		ArowSize = A.length;
		BcolSize = B[0].length;
		BrowSize = B.length;
		
		int[][] outputMatrix = new int[A.length][A.length];
		
		if(ArowSize != BcolSize || AcolSize != BrowSize) {
			System.err.println("\n\tWARNING! Multiplication can't be performed\n\tMatrix A row != Matrix B column");
		} else {
			for(int i=0; i<ArowSize; i++) {
				for(int j=0; j<BcolSize; j++) {
					int sum = 0;
					for(int k=0; k<BrowSize; k++) {
						sum += A[i][k] * B[k][j];
					}
					outputMatrix[i][j] = sum;
				}
			}
		}		
		return outputMatrix;
	}
	
	public static int calculateArrayMultiplication(int[] A, int[] B) {
		int output = 0;
		if(A.length != B.length) {
			System.err.println("WARNING: calculateArrayMultiplication can't be performed!\nA.length != B.length");
		}
		else {
			for(int i=0; i<A.length; i++) {
				output += A[i] * B[i];
			}
		}
		return output;
	}
	
	public static int[] calculateArrayMatrixMultiplication(int[] A, int[][] B) {
		
		int[] output = new int[B[0].length];
		
		if(A.length != B.length) {
			System.err.println("\n\tWARNING\n\tcalculateArrayMatrixMultiplication : A.length != B.length");
		}
		else {			
			for(int i=0; i<B[0].length; i++) {
				int sum = 0;
				for(int j=0; j<A.length; j++) {
					sum += A[j] * B[j][i];
				}
				output[i] = sum;
			}
		}
		return output;
	}

	// Matrix A (first matrix) is the one to be separated by rows (into two pieces)
	public static int[][][] seperateMatrixByRow(int[][] mat) {
		
		int colSize = mat[0].length;
		int newRowSize = (mat.length % 2 == 0) ? (mat.length/2) : (mat.length/2) + 1;
		
		int [][][] output = new int[2][newRowSize][colSize];
		
		int i = 0;
		for(; i<newRowSize; i++) {
			for(int j=0; j<mat[0].length; j++) {
				output[0][i][j] = mat[i][j];
			}
		}
		
		for(int k=0; i<mat.length; k++) {
			for(int j=0; j<mat[0].length; j++) {
				output[1][k][j] = mat[i][j];
			}
			i++;
		}
		
		return output;
	}
	
	// Matrix B (second Matrix) is the one to be separated (into two pieces)
	public static int[][][] seperateMatrixByCol(int[][] mat) {
		int newColSize = (mat[0].length % 2 == 0) ? (mat[0].length/2) : (mat[0].length/2) + 1;
		int rowSize = mat.length;
		
		int [][][] output = new int[2][rowSize][newColSize];
		
		int i = 0;
		for(; i<newColSize; i++) {
			for(int j=0; j<mat.length; j++) {
				output[0][j][i] = mat[j][i];
			}
		}
		
		for(int k=0; i<mat[0].length; k++) {
			for(int j=0; j<mat.length; j++) {
				output[1][j][k] = mat[j][i];
			}
			i++;
		}
		
		return output;
	}
	
	public static void printMatrix(int M[][]) {
		if(M == null) {
			System.out.println("Null matrix");
		}
		else {
			int rowSize = M.length;
			int colSize = M[0].length;
			
			for (int i = 0; i < rowSize; i++) { 
				for (int j = 0; j < colSize; j++) 
					System.out.print(M[i][j] + " "); 
					System.out.println(); 
			}
		}
	}
	
	public static int[][] joinMatrices(int[][] A, int[][] B, int[][] C, int[][] D) {
		
		int size = A.length;
		
		int[][] output = new int[size*2][size*2];
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				output[i][j] = A[i][j];
				output[i][j+size] = B[i][j];
				output[i+size][j] = C[i][j];
				output[i+size][j+size] = D[i][j];
			}
		}
		
		return output;
	}
	
	public static int[][] joinMatrixFromArrays(ArrayList<int[]> arrays) {
		int[][] output = null;
		if(arrays == null) {
			System.err.println("null ArraysList");
		}
		else {
			output = new int[arrays.size()][arrays.get(0).length];
			
			for(int i=0; i<arrays.size(); i++) {
				for(int j=0; j<arrays.get(i).length; j++) {
					output[i][j] = arrays.get(i)[j];
				}
			}
		}
		return output;
	}
	
	public static void printArray(int A[]) {
		if(A == null) {
			System.out.println("Null array");
		}
		else {
			for(int i=0; i<A.length; i++) {
				System.out.print(A[i]+" ");
			}
			System.out.println();			
		}
	}
	
}