import eduni.simjava.Sim_event;
import eduni.simjava.Sim_system;
import gridsim.GridSim;

public class CompUnit extends GridSim{
	
	private int[] A;
	private int[][] B;
	private int[] calculationResult;
	
	public CompUnit(String s, int[] A, int[][] B) throws Exception {
		super(s);
		this.A = A;
		this.B = B;
		performCalculation();
	}
	
	public void body() {
		while(Sim_system.running()) {
			Sim_event ev = this.receive();
			System.out.println("try" + ev.get_tag());
		}
	}
	
	private void performCalculation() {		
		System.out.println(this.get_name() + " is performing its calculation");
		this.calculationResult = CompEngine.calculateArrayMatrixMultiplication(A, B);
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