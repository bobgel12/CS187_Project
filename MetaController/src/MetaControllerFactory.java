
public class MetaControllerFactory {
	
	public static IMetaController getMetaControllerInstance(){
		IMetaController metaController = null;
		
		if(MetaControllerConfiguration.getMetaControllerType().equalsIgnoreCase("default")){
			metaController = new MetaController();
		}
	
		/*** Bug Fix: Algorithm not being set, Snigdha Gulhati, CMPE 287, Fall 2011 ***/
		MetaControllerConfiguration.setAlgorithm(AlgorithmConfiguration.getAlgorithm());
		System.out.println("In MetaControllerConfiguration.java - "+MetaControllerConfiguration.getAlgorithm());
		
		if((MetaControllerConfiguration.getAlgorithm().equalsIgnoreCase("shortestPath")) || (MetaControllerConfiguration.getAlgorithm().equalsIgnoreCase("shortest Path"))) {
			metaController.setAlgorithm(new ShortestPathAlgorithm());
		}else if(MetaControllerConfiguration.getAlgorithm().equalsIgnoreCase("random")){
			metaController.setAlgorithm(new RandomAlgorithm());
		/*** Added new elevator algorithm, Snigdha Gulhati, CMPE 287, Fall 2011 ***/
		}else if(MetaControllerConfiguration.getAlgorithm().equalsIgnoreCase("Least Recently Used")){
			metaController.setAlgorithm(new LeastRecentlyUsedAlgorithm());
		}
		return metaController;
	}

}
