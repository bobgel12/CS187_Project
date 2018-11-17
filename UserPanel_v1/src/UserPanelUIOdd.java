import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

public class UserPanelUIOdd extends JPanel {

	private ICar car = null;

	private UserPanelColor activeButtonColor = null;

	private UserPanelColor buttonColor = null;

	private List<JButton> lstUserPanelButtons = new ArrayList<JButton>();

	public UserPanelUIOdd(int numFloors, UserPanelColor buttonColor,
			UserPanelColor activeButtonColor, ICar car) {
		
		System.out.println("Inside UsserPanel UI "+ car);

		this.car = car;

		this.activeButtonColor = activeButtonColor;

		this.buttonColor = buttonColor;

		int newNumFloors = 0;

		setPreferredSize(new Dimension(200, 200));
		if (numFloors % 3 != 0) {
			newNumFloors = numFloors + (3 - (numFloors % 3));
		} else {
			newNumFloors = numFloors;
		}

		setLayout(new GridLayout(newNumFloors / 3, 3));

		for (int i = 1; i <= numFloors; i+=2) {
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

}
