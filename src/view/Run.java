/**
 * Basic class who runs the view frame application
 * must be erased when the controller is finished
 */
package view;

public class Run {
	
	private View view;
	
	public Run(){
		this.view = new View();
	}
	public static void main(String[] args) {
		new Run();
	}
}
