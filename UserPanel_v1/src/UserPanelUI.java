import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

public class UserPanelUI extends JPanel {

	private ICar car = null;

	private UserPanelColor activeButtonColor = null;

	private UserPanelColor buttonColor = null;
	
//	Code Added By Phuc Le for Emergency Switch
	/* -------------------*/
	
	/* -------------------*/
	
//	Code Added By Phuc Le for Emergency Switch
	/* -------------------*/
	
	private EmergencySwitch emergency = null;
	JButton EmergencyButton = null;
	
	/* -------------------*/
	
	
	// Code Added by Deepshikha
	JButton AlarmButtonOn = null;
	String AlarmTextOn = "Alarm On";
	
	JButton AlarmButtonOff = null;
	String AlarmTextOff = "Alarm Off";
	
	/* *************************/

	private List<JButton> lstUserPanelButtons = new ArrayList<JButton>();



	public UserPanelUI(int numFloors, UserPanelColor buttonColor,
			UserPanelColor activeButtonColor, ICar car, EmergencySwitch emergencya) {
		
		this.emergency = emergencya;
		
		System.out.println("Inside UsserPanel UI "+ car);

		this.car = car;

		this.activeButtonColor = activeButtonColor;

		this.buttonColor = buttonColor;

		int newNumFloors = 0;
		
		
		// Code for Alarm Button - Deepshikha
				/* **********************  */
				
				GridBagConstraints gbc = new GridBagConstraints();
				gbc.gridy = 1;
				
				AlarmFactory.alarmConfig(this);
				AlarmButtonOn = new JButton(AlarmTextOn);
				AlarmButtonOn.setBackground(buttonColor.getJColor());
				AlarmListener alarmOnListner = new AlarmListener();
				AlarmButtonOn.addActionListener(alarmOnListner);
				add(AlarmButtonOn , gbc);
				
				
				AlarmButtonOff = new JButton(AlarmTextOff);
				AlarmButtonOff.setBackground(buttonColor.getJColor());
				AlarmListener1 alarmOffListner = new AlarmListener1();
				AlarmButtonOff.addActionListener(alarmOffListner);
				add(AlarmButtonOff , gbc);
				AlarmButtonOff.setEnabled(false);
				
				/* **********************  */

				
//				Code Added By Phuc Le for Emergency Switch
				/* -------------------*/
				
				EmergencyButton = new JButton("Emergency");
				EmergencyButton.setBackground(buttonColor.getJColor());
				EmergencyButton.addActionListener(new EmergencyListerner());
				add(EmergencyButton, gbc);
				
				/* -------------------*/
				
				
		setPreferredSize(new Dimension(200, 200));
		if (numFloors % 3 != 0) {
			newNumFloors = numFloors + (3 - (numFloors % 3));
		} else {
			newNumFloors = numFloors;
		}

		setLayout(new GridLayout(newNumFloors / 3, 3));

		for (int i = 1; i <= numFloors; i++) {
			JButton button = new JButton("" + i);
			
			System.out.println("Button Text" + button.getText());
			System.out.println("Button Color" + buttonColor);
			button.setBackground(buttonColor.getJColor());
			button.addActionListener(
					new UserPanelButtonListener(button.getText(), car, activeButtonColor));
			add(button);
			lstUserPanelButtons.add(button);

		}

	}
	
	public void deactivateFloorButton(int floorNumber){
		
		for(JButton button : lstUserPanelButtons){
			if(Integer.parseInt(button.getText()) == floorNumber){
				button.setBackground(buttonColor.getJColor());
				break;
			}
		}
		
	}
	
	// Code for Alarm Button On ActionListner - Deepshikha
		/* **********************  */
		
		void disableAlarmButtonOn ()
		{
			AlarmButtonOn.setEnabled(false);
			
		}
		
		void processedRequest()
		{
			AlarmButtonOn.setBackground(buttonColor.getJColor());
		}
		
		private class AlarmListener implements ActionListener 
		{
			public void actionPerformed(ActionEvent event)
			{
				AlarmButtonOn.setBackground(Color.RED);
				
				AlarmButtonOff.setEnabled(true);
				
				int currentFloorNumber = car.getCurrentFloorNumber();
				int currentCarNumber = car.getCarID();
				
				// 10/23/2011 - Snigdha, Added logic for Alarm on
				car.setStatus(CarStatus.ALARM_PRESSED);
				for(JButton button : lstUserPanelButtons){
					button.setEnabled(false);
				}

				car.processAlarmRequest(currentFloorNumber, currentCarNumber);
			}
		}
		
		// Code for Alarm Button Off ActionListner - Deepshikha
				/* **********************  */
		
//		Code Added By Phuc Le for Emergency Switch
		/* -------------------*/
		
		private class EmergencyListerner implements ActionListener 
		{
			public void actionPerformed(ActionEvent event)
			{
				emergency.switchStatus();
				System.out.println(emergency.getStatus());
				car.setEmergencyStatus();
			}
		}
		
		/* -------------------*/
		
		
		void disableAlarmButtonOff ()
		{
			AlarmButtonOff.setEnabled(false);
			
		}
					
		void processedRequest1()
		{
			AlarmButtonOff.setBackground(buttonColor.getJColor());
		}	
		
		private class AlarmListener1 implements ActionListener 
		{
			public void actionPerformed(ActionEvent event)
			{
				AlarmButtonOff.setBackground(Color.GREEN);
				AlarmButtonOn.setBackground(Color.GREEN);
				AlarmButtonOff.setEnabled(false);
				
				int currentFloorNumber = car.getCurrentFloorNumber();
				int currentCarNumber = car.getCarID();
				
				//10/23/2011 - Snigdha, Added logic for Alarm off
				car.setStatus(CarStatus.IDLE);
				
				for(JButton button : lstUserPanelButtons){
					button.setEnabled(true);
					button.setBackground(buttonColor.getJColor());;
				}
				
				car.processAlarmReset(currentFloorNumber, currentCarNumber);

			}
		}
				

}
