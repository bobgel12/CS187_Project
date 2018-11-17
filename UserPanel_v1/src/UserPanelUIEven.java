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

import UserPanelUI.AlarmListener;
import UserPanelUI.AlarmListener1;

public class UserPanelUIEven extends JPanel {

	private ICar car = null;

	private UserPanelColor activeButtonColor = null;

	private UserPanelColor buttonColor = null;
	
	// Code Added by Deepshikha
		/*JButton AlarmButtonOn = null;
		String AlarmTextOn = "Alarm On";
		
		JButton AlarmButtonOff = null;
		String AlarmTextOff = "Alarm Off";
		
		/* *************************/

	private List<JButton> lstUserPanelButtons = new ArrayList<JButton>();

	public UserPanelUIEven(int numFloors, UserPanelColor buttonColor,
			UserPanelColor activeButtonColor, ICar car) {
		
		System.out.println("Inside UsserPanel UI "+ car);

		this.car = car;

		this.activeButtonColor = activeButtonColor;

		this.buttonColor = buttonColor;

		int newNumFloors = 0;
		
		// Code for Alarm Button - Deepshikha
		  /*
		
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

		setPreferredSize(new Dimension(200, 200));
		if (numFloors % 3 != 0) {
			newNumFloors = numFloors + (3 - (numFloors % 3));
		} else {
			newNumFloors = numFloors;
		}

		setLayout(new GridLayout(newNumFloors / 3, 3));

		for (int i = 2; i <= numFloors; i+=2) {
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
			
			void disableAlarmButtonOff ()
			{
				AlarmButtonOff.setEnabled(false);
				
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
