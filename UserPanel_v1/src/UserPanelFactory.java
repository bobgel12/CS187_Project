
public class UserPanelFactory {

	public static IUserPanel getUserPanelInstance(){
		 IUserPanel userPanel = null;
		
		 ICar car = null;
		 
		if(UserPanelConfiguration.getUserPanelType().equals("default"))
		userPanel = new UserPanel();
		
		userPanel.setNumberFloors(UserPanelConfiguration.getNumberFloors());	
		
		 userPanel.setButtonColor(UserPanelConfiguration.getButtonColor());
		 
		 userPanel.setActiveButtonColor(UserPanelConfiguration.getActiveButtonColor());
		 userPanel.setSelection(UserPanelConfiguration.getSelection());
		 
		 	 
		if(UserPanelConfiguration.getCarType().equals("default")){
			car = new Car();
			
		}else if (UserPanelConfiguration.getCarType().equals("testcar")){
			car = new TestCar();
		}
		userPanel.setCar(car);
		
		 if(UserPanelConfiguration.getQueueType().equals("default")){
				car.setUserPanelQueue(new UserPanelQueue());
		}else if (UserPanelConfiguration.getCarType().equals("testcar")){
				car.setUserPanelQueue(new TestUserPanelQueue());
		}
		
		/*if(UserPanelConfiguration.getQueueType().equals("default")){
			userPanel.setCarControllerQueue(new UserPanelQueue());
		}*/
		
		return userPanel;		
	}

}
