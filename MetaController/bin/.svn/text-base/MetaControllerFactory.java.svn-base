
public class MetaControllerFactory {
	
	public static IMetaController getMetaControllerInstance(){
		IMetaController metaController = null;
		
		if(MetaControllerConfiguration.getMetaControllerType().equalsIgnoreCase("default")){
			metaController = new MetaController();
		}
		
		if(MetaControllerConfiguration.getAlgorithm().equalsIgnoreCase("shortestPath")){
			metaController.setAlgorithm(new ShortestPathAlgorithm());
		}else if(MetaControllerConfiguration.getAlgorithm().equalsIgnoreCase("random")){
//			metaController.setAlgorithm(new RandomAlgorithm());
		}
		
		return metaController;
	}

}
