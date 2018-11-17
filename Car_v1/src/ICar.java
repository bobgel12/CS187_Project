import javax.swing.JPanel;


public interface ICar {
	
	void setDoor(IDoor door);
	
	void setUserPanel(IUserPanel userPanel);
	
	void setCarController(ICarController carController);
	
	void setUserPanelQueue(IUserPanelQueue userPanelQueue);
	
	IUserPanelQueue getUserPanelQueue();
	
	ICarController getCarController();
	
	JPanel createCar();
	
	void setDoorPanel(IDoorPanel doorPanel);
	
	IDoorPanel getDoorPanel();
	
	void setCurrentFloorNumber(int currentFloorNumber);
	
	int getCurrentFloorNumber();
	
	void moveUp(int destinationFloorNo);
	
	void moveDown(int destinationFloorNo);
	
	CarStatus getStatus();
	
	void setStatus(CarStatus status);
	
	IUserPanel getUserPanel();

	IDoor getDoor();
	
	//Pinky added
	void setCarID(int id);
	int getCarID();
	String getCarType();
	
	/** 10/23/2011 - Snigdha, Alarm handling **/
	public void processAlarmRequest(int currentFloorNumber, int currentCarNumber);
	public void processAlarmReset(int currentFloorNumber, int currentCarNumber);

	
//	Code Added By Phuc Le for Emergency Switch
	/* -------------------*/
	public void setEmergencyStatus();
	/* -------------------*/

}
