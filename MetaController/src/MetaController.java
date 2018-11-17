import java.util.ArrayList;
import java.util.List;


public class MetaController implements IMetaController {
	
	
	private List<ICar> lstCars = new ArrayList<ICar>();
	
	private IAlgorithm algorithm = null;

	public void setAlgorithm(IAlgorithm algorithm) {
		this.algorithm = algorithm;
	}

	@Override
	public void processRequest(int destinationFloorNumber, Direction direction) {
		System.out.println("In MetaController.java: Algorithm ="+AlgorithmConfiguration.getAlgorithm());
		System.out.println("In MetaController.java: Car List =");
		for(int i=0;i<lstCars.size();i++){
			System.out.println(lstCars.get(i).getCarID());
		}
		if(algorithm != null)
			System.out.println(algorithm.getClass());
		else 
			System.out.println("null");
		ICar car = algorithm.findBestCar(lstCars,direction,destinationFloorNumber);
		if(car==null){
			System.out.println("Null Car returned");
		}

		/*** New elevator algorithm, Snigdha Gulhati, CMPE 287, Fall 2011 ***/
		//If LeastRecentlyUsedAlgo used, Move this car at the end of the list, and all the other cars one step forward.

		if(AlgorithmConfiguration.getAlgorithm().equalsIgnoreCase("Least Recently Used")){
			System.out.println("In MetaController.java: Changing car order");
			deRegisterCar(car,0);
			registerCar(car);
			
			System.out.println("In MetaController.java: Car List afterwards =");
			for(int i=0;i<lstCars.size();i++){
				System.out.println(lstCars.get(i).getCarID());
			}
		}

		car.getUserPanelQueue().putMessage(destinationFloorNumber);
		
	}

	@Override
	public void registerCar(ICar car) {
		if(car != null){
			lstCars.add(car);
		}
		
	}
	
	/*** Added new method to deregister car, Snigdha Gulhati, CMPE 287, Fall 2011 ***/
	@Override
	public void deRegisterCar(ICar car, int index) {
		if(car != null){
			lstCars.remove(index);
		}
	}
	
	
}
