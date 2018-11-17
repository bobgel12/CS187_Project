import javax.swing.JButton;
import javax.swing.JPanel;

public class EmergencyIndicator{
	private Emergency emergency = null;
	private String Status = "";
	
	public EmergencyIndicator(Emergency a) {
		this.emergency = a;
	}
	public String getStatus() {
		return emergency.getStatus();
	}
}
