import eduni.simjava.Sim_event;
import eduni.simjava.Sim_system;
import gridsim.GridSim;

public class CompUnit extends GridSim{
	
	private final int PERFORM_CALCULATION = 10;
	
	private int[] A;
	private int[][] B;
	private int[] calculationResult;
	private int index;
	private String userName;
	
	public CompUnit(int index, String userName, String s, int[] A, int[][] B) throws Exception {
		super(s);
		System.out.println("[" + s + " of "+ userName +"] is being created");
		this.A = A;
		this.B = B;
		this.index = index;
		this.userName = userName;
	}
	
	public void body() {
		while(Sim_system.running()) {
			Sim_event ev = this.receive();
			if(ev.get_tag() == this.PERFORM_CALCULATION) {
				this.performCalculation();
			}
		}
	}
	
	private void performCalculation() {
		System.out.println("["+this.get_name() + " of "+ this.userName +"] is performing its calculation");
		this.calculationResult = CompEngine.calculateArrayMatrixMultiplication(A, B);
		send(this.userName, 10, 11, this.index);
	}
	
	public int[] returnCalculationResult() {
		return this.calculationResult;
	}
	
	public Sim_event receive() {
        Sim_event ev = new Sim_event();
        super.sim_get_next(ev);
        return ev;
    }
}