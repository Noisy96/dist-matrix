import java.util.Calendar;
import gridsim.GridSim;

public class MainPanel {
	
	// TODO : Replace the hard-coded data with a file reading algorithm
	private static int B[][] = { { 1, 1, 2, 2 }, 
								{ 2, 2, 3, 2 }, 
								{ 3, 3, 5, 3 },
								{ 4, 4, 1, 4 }};

	private static int A[][] = { { 1, 1, 1, 1},
								{ 1, 1, 1, 1}, 
								{ 1, 1, 1, 0}};
	
	private static int C[][];
	private static int D[][];
	
	public static void main(String[] args) {
		
		CompEngine.printMatrix(CompEngine.generateMatrix(10, 10));
		
		C = CompEngine.generateMatrix(50, 50);
		D = CompEngine.generateMatrix(50, 50);
		
		
		try {			
			// Initialize GridSim package
			initSimulation();
						
			// Create a grid user
			createUsers();
			
			// Starting the grid simulation
			startSimulation();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void initSimulation() {
		int num_user = 2;
		Calendar calendar = Calendar.getInstance();
		boolean trace_flag = true;
		GridSim.init(num_user, calendar, trace_flag);
	}
	
	private static void createUsers() throws Exception {
		GridUser gridUser = new GridUser("user1", C, D);
//		GridUser gridUser2 = new GridUser("user2", A, B);
	}
	
	private static void startSimulation() {
		GridSim.startGridSimulation();
	}
}
