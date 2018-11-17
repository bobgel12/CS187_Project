/*** 
 * Author - Snigdha Gulhati
 * Class - CMPE 287, Fall 2011.
 * Description - 
	 * This elevator algorithm selects the least recently used car, when a request is made.
	 * Once a car services a request, it is moved to the end of the queue. 
	 * For the next request, the car now at the top of the queue is used to service the request. 
	 * This way, all the cars would be used equally. But, since this algorithm does not take into consideration 
	 * the closest car, it could lead to delays in processing the request. 
 */

import java.util.List;


public class LeastRecentlyUsedAlgorithm implements IAlgorithm {

	@Override
	public ICar findBestCar(List<ICar> lstCars, Direction direction, int destinationFloorNumber) {
		
		System.out.println("In LeastRecentlyUsedAlgorithm.java ");
		ICar bestCar = null;
		
		if(lstCars.size() >= 1){
			bestCar = lstCars.get(0);
			System.out.println("In LeastRecentlyUsedAlgorithm.java - bestCar="+bestCar.getCarID());
		}
		else
			System.out.println("In LeastRecentlyUsedAlgorithm.java - There is no car in the list");
		
		return bestCar;
	}

}
