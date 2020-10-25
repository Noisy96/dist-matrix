import eduni.simjava.Sim_event;
import eduni.simjava.Sim_system;
import gridsim.GridSim;

public class CompUnit extends GridSim{
	
	private final int PERFORM_CALCULATION = 10;
	
	private int[] A;
	private int[][] B;
	private int[] calculationResult;
	private int index;
	
	public CompUnit(int index, String s, int[] A, int[][] B) throws Exception {
		super(s);
		System.out.println(s + " is being created");
		this.A = A;
		this.B = B;
		this.index = index;
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
		System.out.println(this.get_name() + " is performing its calculation");
		this.calculationResult = CompEngine.calculateArrayMatrixMultiplication(A, B);
		send("user1", 10, 11, this.index);
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