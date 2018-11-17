
public class AlarmFactory {

	public static void alarmConfig(UserPanelUI userPanelUI)
	{
		System.out.println("***********"+AlarmConfiguration.getTextType());
		if (AlarmConfiguration.getTextType().equalsIgnoreCase("text")){
			userPanelUI.AlarmTextOn = "ON";
			userPanelUI.AlarmTextOff = "OFF";
		}else if (AlarmConfiguration.getTextType().equalsIgnoreCase("symbol")){
			userPanelUI.AlarmTextOn = "^^";
			userPanelUI.AlarmTextOff = "vv";
		}
	}
	
	
}
