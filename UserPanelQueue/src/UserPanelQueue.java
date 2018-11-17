import java.util.Collections;
import java.util.LinkedList;



public class UserPanelQueue implements IUserPanelQueue {

	protected LinkedList<UserPanelRequest> queueUserPanelRequestUp = new LinkedList<UserPanelRequest>();
	protected LinkedList<UserPanelRequest> queueUserPanelRequestDown = new LinkedList<UserPanelRequest>();
 
	protected final static UserPanelRequestAscComparator ASC_COMPARATOR = new UserPanelRequestAscComparator();
	protected final static UserPanelRequestDescComparator DESC_COMPARATOR = new UserPanelRequestDescComparator();
	
	private ICar car = null;
	
	protected int currenRequest = 1;

	public UserPanelQueue() {
		
	}

	@Override
	public void putMessage(int destinationFloorNo) {
		UserPanelRequest userPanelRequest = new UserPanelRequest(destinationFloorNo, car);
		
		int currentFloorNumber = car.getCurrentFloorNumber();
		
		if (!isRequestAlreadyQueued(userPanelRequest)) {
			
			System.out.println("Current  "+currentFloorNumber );
			if (destinationFloorNo >= currenRequest){
				queueUserPanelRequestUp.offer(new UserPanelRequest(destinationFloorNo, car));
				System.out.println("Request Queued Successfully in upQueue "+destinationFloorNo);
			}
			else{
				queueUserPanelRequestDown.offer(new UserPanelRequest(destinationFloorNo, car));
				System.out.println("Request Queued Successfully in downQueue "+destinationFloorNo);
			}
		}
		System.out.println("Hellloooo.....sorting");
		if (queueUserPanelRequestUp.size() != 0) {
			Collections.sort(queueUserPanelRequestUp,UserPanelQueue.ASC_COMPARATOR);
		}
		if (queueUserPanelRequestDown.size() != 0) {
			Collections.sort(queueUserPanelRequestDown,UserPanelQueue.DESC_COMPARATOR);
		}
		
		
		for( UserPanelRequest u : queueUserPanelRequestUp){
			System.out.println("sandhyaaaaa" + u.getDestinationFloorNumber());
		}

	}

	private boolean isRequestAlreadyQueued(UserPanelRequest userPanelRequest) {
		// check if request for the same floor and same car is already in
		// the queue
		
		if(queueUserPanelRequestUp.contains(userPanelRequest) || queueUserPanelRequestDown.contains(userPanelRequest)){
			return true;
		}
		return false;

	}

	protected LinkedList<UserPanelRequest> getQueueUserPanelRequestUp() {
		return queueUserPanelRequestUp;
	}

	protected LinkedList<UserPanelRequest> getQueueUserPanelRequestDown() {
		return queueUserPanelRequestDown;
	}

	@Override
	public int getNumTasks() {

		return 0;
		// return queueUserPanelRequest.size();
	}

	public int pathLength(Direction direction, int destinationFloorNumber) {

		int pathLength = 0;

		if (direction.equals(Direction.UP)) {

			if (queueUserPanelRequestUp.size() != 0) {
				int firstRequest = car.getCurrentFloorNumber();

				if (firstRequest <= destinationFloorNumber) {
					pathLength = destinationFloorNumber - firstRequest;
				} else {
					int lastRequest = queueUserPanelRequestUp.getLast()
							.getDestinationFloorNumber();
					pathLength = (lastRequest - firstRequest)
							+ (lastRequest - destinationFloorNumber);
				}
			}else{
				pathLength = Math.abs(car.getCurrentFloorNumber() - destinationFloorNumber);
			}
		} else {
			if (queueUserPanelRequestDown.size() != 0) {
				int firstRequest = car.getCurrentFloorNumber();

				if (firstRequest >= destinationFloorNumber) {
					pathLength = -destinationFloorNumber + firstRequest;
				} else {
					int lastRequest = queueUserPanelRequestDown.getLast()
							.getDestinationFloorNumber();
					pathLength = (-lastRequest + firstRequest)
							+ (-lastRequest + destinationFloorNumber);
				}
			}else{
				pathLength = Math.abs(car.getCurrentFloorNumber() - destinationFloorNumber);
			}
		}

		return pathLength;

	}


	@Override
	public void setCar(ICar car) {
		this.car = car;
		Thread userPanelQueueMonitorThread = new Thread(
				new UserPanelQueueMonitorThread());
		userPanelQueueMonitorThread.start();
		
	}
	
	public ICar getCar(){
		return car;
	}
	
	class UserPanelQueueMonitorThread implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				try {
					Thread.sleep(5000);
					/**10-23-2011 - Snigdha, code to stop car if alarm is pressed **/  
					if(car.getStatus().equals(CarStatus.ALARM_PRESSED)){
						break;
					}
					
					while (queueUserPanelRequestUp.size() != 0
							|| queueUserPanelRequestDown.size() != 0) {
						while (queueUserPanelRequestUp.size() != 0) {
							
							UserPanelRequest userPanelRequest = queueUserPanelRequestUp.pollFirst();
							currenRequest = userPanelRequest.getDestinationFloorNumber();
							System.out.println("Reading request from queueUp ...."+ userPanelRequest.getDestinationFloorNumber());
							ICarController carController = car.getCarController() ;
							carController.processRequest(userPanelRequest.getDestinationFloorNumber(), Direction.UP);
							
						}

						while (queueUserPanelRequestDown.size() != 0) {
							UserPanelRequest userPanelRequest = queueUserPanelRequestDown
									.pollFirst();
							System.out
									.println("Reading request from queueDown ...."
											+ userPanelRequest
													.getDestinationFloorNumber());
							ICarController carController = car.getCarController() ;
							carController.processRequest(userPanelRequest.getDestinationFloorNumber(), Direction.DOWN);
							
						}

					}
					
					car.setStatus(CarStatus.IDLE);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

		
}


