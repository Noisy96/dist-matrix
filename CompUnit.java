
public class CompUnit {
	
	private int[] A;
	private int[][] B;
	private int[] calculationResult;
	
	public CompUnit(int[] A, int[][] B) {
		this.A = A;
		this.B = B;
		performCalculation();
	}
	
	private void performCalculation() {		
		this.calculationResult = CompEngine.calculateArrayMatrixMultiplication(A, B);
	}
	
	public int[] returnCalculationResult() {
		return this.calculationResult;
	}
}