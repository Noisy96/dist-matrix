import java.util.ArrayList;
import java.util.Calendar;

import gridsim.GridSim;
import gridsim.ParameterException;
import gridsim.net.Link;
import gridsim.net.SimpleLink;

public class MainPanel {
	
	// Making MainPanel a singleton class able to return its own instance
	private static MainPanel instance = null;
	
	// TODO : Replace the hard-coded data with a file reading algorithm
	private static int B[][] = { { 1, 1, 2, 2 }, 
								{ 2, 2, 3, 2 }, 
								{ 3, 3, 5, 3 },
								{ 4, 4, 1, 4 }};

	private static int A[][] = { { 1, 1, 1, 1}, 
								{ 1, 1, 1, 1}, 
								{ 1, 1, 1, 0}};
	
	public static void main(String[] args) {		
		try {			
			// Initialize GridSim package
			int num_user = 2;
			Calendar calendar = Calendar.getInstance();
			boolean trace_flag = true;
			GridSim.init(num_user, calendar, trace_flag);
						
			GridUser gridUser = new GridUser("user1", A, B);
			
			// Starting the grid simulation
			GridSim.startGridSimulation();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
