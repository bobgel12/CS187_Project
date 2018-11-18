import javax.swing.JPanel;

public class EmergencySwitch {
	
	private UserPanel userPanel = null;
	
	private boolean status = false; 
	
	public String getStatus() {
		if (status){
			return "On";
		} else {
			return "Off";
		}
	}
	
	public Boolean getStatusBool() {
		return status;
	}

	public void setStatus(boolean a) {
		this.status = a;
	}
	
	
	public void switchStatus() {
		this.status = !this.status;
	}

	public UserPanel getUserPanel() {
		return userPanel;
	}

	public void setUserPanel(UserPanel userPanel) {
		this.userPanel = userPanel;
	}

	public EmergencySwitch() {
		
	}

}
