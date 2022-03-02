import java.awt.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class LaunchPage {
	// Page Icon
	private ImageIcon movingIcon;
	// Panels
	private JPanel topPanel = new JPanel();
	private BottomPanel bottomPanel;

	private HintTextField nameField = new HintTextField("Please enter suspect's name");
	private JButton findButton = new JButton("Find");
	private JFrame frame = new JFrame("Find Suspect");
	
	private ArrayList<String> allSuspectNames = new ArrayList<>();
	private ArrayList<Suspect> allSuspects;
	private Registry registry;
	// Visualize Network
	private JButton visualizeNetworkButton;
	
	public LaunchPage(Registry aRegistry) {
		
		// Bottom Panel
		bottomPanel = new BottomPanel();
		
		// Top Panel
		registry = aRegistry;
		allSuspects = registry.getListOfSuspects();
		for(Suspect suspect: allSuspects) {
			allSuspectNames.add(suspect.getName());
		}
		
		nameField.setFont(new Font(null, Font.PLAIN, 16));
		
		// Visualize Network
		visualizeNetworkButton = new JButton("Visualize Network");
		visualizeNetworkButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new VisualizeNetwork(registry);
			}
			
		});
		
		topPanel.setBackground(Color.LIGHT_GRAY);
		topPanel.add(nameField);
		topPanel.add(findButton);
		topPanel.add(visualizeNetworkButton);
		
		ButtonListener listener = new ButtonListener();
		findButton.addActionListener(listener);
		
		// Page Icon
		movingIcon = new ImageIcon("moving.jpg");
		
		// Panels
		frame.setLayout(new BorderLayout());
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(bottomPanel, BorderLayout.SOUTH);
		frame.setIconImage(movingIcon.getImage());
		frame.setVisible(true);
		frame.setSize(450, 275);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}
	
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			String inputText = nameField.getText();
			Suspect selectedSuspect = null;
			
			if(arg0.getSource().equals(findButton)) {
				frame.dispose();
				if(!allSuspectNames.contains(inputText)) {
					JOptionPane.showMessageDialog(null, "Suspect " + inputText + " Not Found", "Message", JOptionPane.INFORMATION_MESSAGE);
					new LaunchPage(registry);
				}
				else {
					for(Suspect suspect: allSuspects)
						if(suspect.getName().equals(inputText)) {
							selectedSuspect = suspect;
							new SuspectPageFrame(selectedSuspect, registry);
						}
				}
			}
		}
	}
}