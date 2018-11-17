
public interface IMetaController {
	
	void processRequest(int destinationFloorNumber, Direction direction);
	
	void registerCar(ICar car);
	
	void setAlgorithm(IAlgorithm algorithm);

	/*** Added new method to deregister car, Snigdha Gulhati, CMPE 287, Fall 2011 ***/
	void deRegisterCar(ICar car, int index);

}
