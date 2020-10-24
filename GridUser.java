import java.util.ArrayList;
import gridsim.GridSim;
import gridsim.GridSimTags;

public class GridUser extends GridSim {

	private int B[][];
	private int A[][];

	private ArrayList<CompUnit> allocatedCompUnits;
	
	public GridUser(String s, int[][] A ,int[][] B) throws Exception {
		super(s);
		this.A = A;
		this.B = B;
		this.allocatedCompUnits = new ArrayList<CompUnit>();
//		body();
	}
	
	// Separate each matrix into two pieces
	public void body() {
		
		MainPanel mainPanel = MainPanel.getInstance();
		
		for(int i=0; i<A.length; i++) {
			CompUnit compUnit;
			try {
				compUnit = mainPanel.createCompUnit(this, i);
				allocatedCompUnits.add(compUnit);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		super.gridSimHold(100);
		super.send("unit2", 100, 15);
		
		ArrayList<int[]> resultsArrays = new ArrayList<int[]>();
			
		for(int i=0; i<allocatedCompUnits.size(); i++) {
			resultsArrays.add(allocatedCompUnits.get(i).returnCalculationResult());
		}
		
		int[][] finalresult = CompEngine.joinMatrixFromArrays(resultsArrays);
		
		System.out.println("\nFinal result:");
		CompEngine.printMatrix(finalresult);
	}
}
