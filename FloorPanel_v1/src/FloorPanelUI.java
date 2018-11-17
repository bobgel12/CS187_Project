//import java.awt.GridLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout; //import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FloorPanelUI extends JPanel {
	
	
	FloorPanel floorPanel;
	JButton UpButton = null;
	JButton DownButton = null;
	int currentFloorNumber = 1;
	// Code added by Deeps and Bindiya
	// int currentCarNumber=1;
	

	FloorPanelColor activeButtonColor = null;
	FloorPanelColor buttonColor = null;
	//FloorIndicator flr=null;
	JTextField floorInd2=null;
	JTextField floorInd4=null;
	JTextField floorInd5=null;
	
	
	// Code for Alarm Button - Deepshikha
	/* **********************  */
	JTextField alarmStatus=null;
	JTextField alarmStatus1=null;
	JTextField alarmStatus2=null;
	JTextField alarmStatus3=null;
	JTextField alarmStatus4=null;
	JTextField alarmStatus5=null;
	
	/* **********************  */
	
	public FloorPanelUI(FloorPanel floorPanel, FloorPanelColor buttonColor,	FloorPanelColor activeButtonColor, int CurrentFloorNumber, String upText, String downText, int cars) {
		this.currentFloorNumber = CurrentFloorNumber;
		// Code added by Deepshikha Bindiya
		// this.currentCarNumber = CurrentCarNumber;
		
		// ************************* 
		this.floorPanel = floorPanel;
		this.activeButtonColor = activeButtonColor;
		this.buttonColor = buttonColor;
		
		
		setLayout(new GridBagLayout());
		JLabel label = new JLabel("FloorNumber "+CurrentFloorNumber);
		add(label);
		
		//Pinky added
		JLabel floorInd1= new JLabel("Car ID");
		add(floorInd1);
		floorInd2 = new JTextField(1);
		add(floorInd2);
		JLabel floorInd3= new JLabel("Car Type");
		add(floorInd3);
		floorInd4 = new JTextField(5);
		add(floorInd4);
		floorInd5 = new JTextField(6);
		add(floorInd5);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = 1;
		
		UpButton = new JButton(upText);
		UpButton.setBackground(buttonColor.getJColor());
		UpListener upListener = new UpListener();
		UpButton.addActionListener(upListener);
		add(UpButton, gbc);
		gbc.gridy = 2;
		gbc.gridx = 0;
		
		DownButton = new JButton(downText);
		DownButton.setBackground(buttonColor.getJColor());
		DownListener downListener = new DownListener();
		DownButton.addActionListener(downListener);
		add(DownButton, gbc);
		
			
		// Code for Alarm Button - Deepshikha
				/* **********************  */
		
		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.gridy = 1;
	
				JLabel label1 = new JLabel("AlarmStatus"+CurrentFloorNumber) ;
				add(label1);
				add(label1,gbc2);
				
				alarmStatus = new JTextField(11);			
				alarmStatus.setText("Car1:"+CurrentFloorNumber+":Active");
				
				alarmStatus.setBackground(Color.green);
				alarmStatus.setEditable(false);
				//add(alarmStatus);	
				
				alarmStatus1 = new JTextField(11);			
				alarmStatus1.setText("Car2:Active");
				alarmStatus1.setBackground(Color.green);
				alarmStatus1.setEditable(false);
				//add(alarmStatus1);
				
				alarmStatus2 = new JTextField(11);			
				alarmStatus2.setText("Car3:Active");
				alarmStatus2.setBackground(Color.green);
				alarmStatus2.setEditable(false);
				//add(alarmStatus2);
				
				if ( cars == 1)
					add (alarmStatus,gbc2);
				if( cars == 2){
					add (alarmStatus,gbc2);
					add (alarmStatus1,gbc2);
				}
				if( cars == 3 || cars == 4 || cars == 5){
					add (alarmStatus,gbc2);
					add (alarmStatus1,gbc2);
					add (alarmStatus2,gbc2);
				}	
				
				
				gbc2.gridy = 2;
				gbc2.gridx = 2;

				alarmStatus3 = new JTextField(11);			
				alarmStatus3.setText("Car4:Active");
				alarmStatus3.setBackground(Color.green);
				alarmStatus3.setEditable(false);
				//add(alarmStatus3);

				alarmStatus4 = new JTextField(11);			
				alarmStatus4.setText("Car5:Active");
				alarmStatus4.setBackground(Color.green);
				alarmStatus4.setEditable(false);
				
				//add(alarmStatus4);
				if( cars == 4 || cars == 5){
					gbc2.gridx = 2;
					add (alarmStatus3,gbc2);
				}
				if(cars == 5){
					gbc2.gridx = 3;
					add (alarmStatus4,gbc2);
				}
				
				/* **********************  */
		
		
	}
	
	
	void disableUpButton(){
		System.out.println("Inside DIsable");
		UpButton.setEnabled(false);
			
	}

	
	void disableDownButton(){
		DownButton.setEnabled(false);
	}
	
	
	void processedRequest(){
		//floorInd4.setText("Test");
		//floorInd2.setText("1");
		UpButton.setBackground(buttonColor.getJColor());
		DownButton.setBackground(buttonColor.getJColor());
		
	}
	
	void processIndicatorRequest(int id,String type){
		floorInd2.setText(new Integer(id).toString());
		floorInd4.setText(type);
		//UpButton.setBackground(buttonColor.getJColor());
		//DownButton.setBackground(buttonColor.getJColor());
		
	}
	void processStatusRequest(int status){
		this.floorInd5.setText(new Integer(status).toString());
		/*floorInd4.setText("Test");
		floorInd2.setText("1");
		UpButton.setBackground(buttonColor.getJColor());
		DownButton.setBackground(buttonColor.getJColor());*/
		
	}
	
	void processAlarmRequest(int currentCarNumber){
			switch(currentCarNumber){
			case 1:		
			{
				this.alarmStatus.setText("Car"+currentCarNumber+":Stuck");
				this.alarmStatus.setBackground(Color.RED);	
				break;
			}
			case 2:
			{
				this.alarmStatus1.setText("Car"+currentCarNumber+":Stuck");
				this.alarmStatus1.setBackground(Color.RED);
				break;
			} 
			case 3:			
			{
				this.alarmStatus2.setText("Car"+currentCarNumber+":Stuck");
				this.alarmStatus2.setBackground(Color.RED);
				break;
			}
			case 4:	
			{
				this.alarmStatus3.setText("Car"+currentCarNumber+":Stuck");
				this.alarmStatus3.setBackground(Color.RED);
				break;
			}
			case 5:
			{
				this.alarmStatus4.setText("Car"+currentCarNumber+":Stuck");
				this.alarmStatus4.setBackground(Color.RED);
				break;
			}
			}
	}
	
	
	void processAlarmReset(int currentCarNumber){
		switch(currentCarNumber){
		case 1:		
		{
			this.alarmStatus.setText("Car"+currentCarNumber+":Active");
			this.alarmStatus.setBackground(Color.GREEN);	
			break;
		}
		case 2:
		{
			this.alarmStatus1.setText("Car"+currentCarNumber+":Active");
			this.alarmStatus1.setBackground(Color.GREEN);
			break;
		} 
		case 3:			
		{
			this.alarmStatus2.setText("Car"+currentCarNumber+":Active");
			this.alarmStatus2.setBackground(Color.GREEN);
			break;
		}
		case 4:	
		{
			this.alarmStatus3.setText("Car"+currentCarNumber+":Active");
			this.alarmStatus3.setBackground(Color.GREEN);
			break;
		}
		case 5:
		{
			this.alarmStatus4.setText("Car"+currentCarNumber+":Active");
			this.alarmStatus4.setBackground(Color.GREEN);
			break;
		}
		}
}
	
	private class UpListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			UpButton.setBackground(activeButtonColor.getJColor());
			System.out.println("inside up button");
			floorPanel.setFloorPanelStatus("Active");
			floorPanel.getFloorPanelQueueType().putFloorRequest(currentFloorNumber, Direction.UP);
		}
	}

	private class DownListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			DownButton.setBackground(activeButtonColor.getJColor());
			System.out.println("inside down button");
			floorPanel.setFloorPanelStatus("Active");
			floorPanel.getFloorPanelQueueType().putFloorRequest(currentFloorNumber, Direction.DOWN);
		}
	}	
}
