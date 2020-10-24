import java.util.ArrayList;
import java.util.Calendar;

import gridsim.GridSim;
import gridsim.ParameterException;
import gridsim.net.Link;
import gridsim.net.SimpleLink;

public class MainPanel {
	
	// Definition of my chosen topology
	private static final double BAUD_RATE = 100; // bits/sec
	private static final double PROPDELAY = 10; // propagation delay in millisecond
	private static final int MTU = 1500; // max. transmission unit in byte
	
	// Making MainPanel a singleton class able to return its own instance
	private static MainPanel instance = null;
	
	private ArrayList<CompUnit> allocatedCompUnits; // Temp
	
	// TODO : Replace the hard-coded data with a file reading algorithm
	private static int B[][] = { { 1, 1, 2, 2 }, 
								{ 2, 2, 3, 2 }, 
								{ 3, 3, 5, 3 },
								{ 4, 4, 1, 4 }};

	private static int A[][] = { { 1, 1, 1, 1}, 
								{ 2, 2, 2, 2}, 
								{ 3, 3, 3, 3}};
	
	public static void main(String[] args) {		
		try {
			// Create an instance of this singleton class in order to reference it from associate objects
			MainPanel mainPanel = MainPanel.getInstance();
			
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
	
	private MainPanel() {
		this.allocatedCompUnits = new ArrayList<CompUnit>();
	}
	
	public static MainPanel getInstance() {
		if(instance == null) instance = new MainPanel();
		return instance;
	}
	
	public CompUnit createCompUnit(GridUser user, int index) {
		Link link = null;
		CompUnit compUnit = null;
		try {
			link = new SimpleLink("link_"+user.get_name()+"_"+index, BAUD_RATE, PROPDELAY, MTU);
			compUnit = new CompUnit("unit"+index, A[index], B);
			link.attach(user, compUnit);
		} catch (ParameterException e) {
            System.err.println("Invalid Parameters creating link " + e.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
        }
		return compUnit;		
	}
}
