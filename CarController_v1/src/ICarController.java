import java.util.List;


public interface ICarController {

	void processRequest(int floorNumber, Direction direction);
		
	void setCar(ICar icar);
	
	void processDoorRequest(DoorCommand doorCommand);
	
	void setFloorPanel(IFloorPanel floorPanel);
	
	IFloorPanel getFloorPanel();
	
}
