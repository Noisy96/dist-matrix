import java.util.ArrayList;

// Responsible for separating the matrix and sending it and receiving the result and building it back again
public class GridUser {
	
	private int B[][] = { { 1, 1, 2, 2 }, 
            			{ 2, 2, 3, 2 }, 
            			{ 3, 3, 5, 3 },
            			{ 4, 4, 1, 4 }};
		
	private int A[][] = { { 1, 1, 1, 1}, 
            			{ 2, 2, 2, 2}, 
            			{ 3, 3, 3, 3}};

	private ArrayList<CompUnit> allocatedCompUnits;
	
	public GridUser() {
		this.allocatedCompUnits = new ArrayList<CompUnit>();
		body();
	}
	
	// Separate each matrix into two pieces
	private void body() {
		
		for(int i=0; i<A.length; i++) {
			CompUnit compUnit = new CompUnit(A[i], B);
			allocatedCompUnits.add(compUnit);
		}
		
		ArrayList<int[]> resultsArrays = new ArrayList<int[]>();
		
		for(int i=0; i<allocatedCompUnits.size(); i++) {
			resultsArrays.add(allocatedCompUnits.get(i).returnCalculationResult());
		}
		
		int[][] finalresult = CompEngine.joinMatrixFromArrays(resultsArrays);
		
		CompEngine.printMatrix(finalresult);
		
	}
}
