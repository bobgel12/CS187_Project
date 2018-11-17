
public class AlgorithmFactory {

	public static IAlgorithm getAlgorithmInstance(){
		IAlgorithm algorithm = null;
		
		if(AlgorithmConfiguration.getAlgorithm().equalsIgnoreCase("shortestPath")){
			algorithm = new ShortestPathAlgorithm();
		}else if(AlgorithmConfiguration.getAlgorithm().equalsIgnoreCase("leastTasks")){
			algorithm = new LeastTasksAlgorithm();
		/*** Added new elevator algorithm, Snigdha Gulhati, CMPE 287, Fall 2011 ***/
		}else if(AlgorithmConfiguration.getAlgorithm().equalsIgnoreCase("Least Recently Used")){
			algorithm = new LeastRecentlyUsedAlgorithm();
		}
		return algorithm;
	}
	
}
