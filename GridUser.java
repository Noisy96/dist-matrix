import java.util.ArrayList;

import eduni.simjava.Sim_event;
import eduni.simjava.Sim_system;
import gridsim.GridSim;
import gridsim.ParameterException;
import gridsim.net.Link;
import gridsim.net.SimpleLink;

public class GridUser extends GridSim {
	
	// Definition of my chosen topology
	private static final double BAUD_RATE = 100; // bits/sec
	private static final double PROPDELAY = 10; // propagation delay in millisecond
	private static final int MTU = 1500; // max. transmission unit in byte

	private int B[][];
	private int A[][];
	
	private final int PERFORM_CALCULATION = 10;
	private final int RESULT_READY = 11;

	private ArrayList<CompUnit> allocatedCompUnits;
	
	public GridUser(String s, int[][] A ,int[][] B) throws Exception {
		super(s);
		this.A = A;
		this.B = B;
		this.allocatedCompUnits = new ArrayList<CompUnit>();
	}
	
	// Separate each matrix into two pieces
	public void body() {
		
		System.out.println("\nCreating the calculation units:\n");
		
		for(int i=0; i<A.length; i++) {
			Link link = null;
			CompUnit compUnit = null;
			try {
				link = new SimpleLink("link_"+this.get_name()+"_"+i, BAUD_RATE, PROPDELAY, MTU);
				compUnit = new CompUnit(i,"unit"+i, A[i], B);
				link.attach(this, compUnit);
			} catch (ParameterException e) {
	            System.err.println("Invalid Parameters creating link " + e.getMessage());
	        } catch (Exception e) {
	        	System.err.println("Invalid Parameters creating link " + e.getMessage());
	        	e.printStackTrace();
	        }
			this.allocatedCompUnits.add(compUnit);
		}
		System.out.println("\nAttaching the created units to the user:\n");
		
		super.gridSimHold(100);
		
		ArrayList<int[]> resultsArrays = new ArrayList<int[]>();

		System.out.println("\nOrdering the units to start performing the calculations:");
		
		for(int i=0; i<allocatedCompUnits.size(); i++) {
			String entityName = allocatedCompUnits.get(i).get_name();
			super.send(entityName, 10, this.PERFORM_CALCULATION);
		}
		
		while(Sim_system.running()) {
			Sim_event ev = this.receive();
			if(ev.get_tag() == this.RESULT_READY) {
				int i = (int) ev.get_data();
				System.out.println("unit"+ i + " completed calculation and array "+ (i+1) +" is ready");
			}
		}
		
		for(int i=0; i<allocatedCompUnits.size(); i++) {
			resultsArrays.add(allocatedCompUnits.get(i).returnCalculationResult());
		}
		
		int[][] finalresult = CompEngine.joinMatrixFromArrays(resultsArrays);
		
		System.out.println("\nFinal result:");
		CompEngine.printMatrix(finalresult);
		System.out.println();
	}
	
	public Sim_event receive() {
        Sim_event ev = new Sim_event();
        super.sim_get_next(ev);
        return ev;
    }
}
