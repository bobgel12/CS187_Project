import javax.swing.JButton;
import javax.swing.JPanel;

public class EmergencyIndicator{
	private EmergencySwitch emergency = null;
	private String Status = "";
	
	public EmergencyIndicator(EmergencySwitch a) {
		this.emergency = a;
	}
	public String getStatus() {
		return emergency.getStatus();
	}
}
