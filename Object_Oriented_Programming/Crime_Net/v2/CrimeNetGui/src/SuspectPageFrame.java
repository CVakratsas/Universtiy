import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class SuspectPageFrame {
		
	private Registry registry;
	private JFrame frame = new JFrame();
	
	// First section
	private JPanel infoPanel = new JPanel();
	private JTextField nameField = new JTextField();
	private JTextField codeNameField = new JTextField();
	private JTextArea numbersInUseArea = new JTextArea(4,10);
	
	// Second section
	private JPanel smsPanel = new JPanel();
	private HintTextField phoneNumber = new HintTextField("Enter Number");
	private JTextArea smsTextArea = new JTextArea(10,20);
	private JButton findSMSButton = new JButton("Find SMS");
	
	// Third section
	private JPanel partnersPanel = new JPanel();
	private JLabel partnersLabel = new JLabel("Partners");
	private JTextArea partnersTextArea = new JTextArea(10,20);
	
	// Forth section
	private JPanel suggestedPartnersPanel = new JPanel();
	private JLabel suggestedPartnersLabel = new JLabel("Suggested Partners");
	private JTextArea suggestedPartnersTextArea = new JTextArea(5,20);
	
	// Back to search button
	private JButton backToScreenButton = new JButton("Back To Search Screen");
	
	public SuspectPageFrame(Suspect suspect, Registry aRegistry) {
		
		registry = aRegistry;
		ButtonListener listener = new ButtonListener();
		Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
		
		//------------------------ 1st panel ------------------------
		nameField.setText(suspect.getName());
		codeNameField.setText(suspect.getCodeName());
		for(String number: suspect.getPhoneNumbersInUse()) {
			numbersInUseArea.append(number + "\n");
		}
		
		nameField.setEditable(false);
		nameField.setFont(new Font(null, Font.PLAIN, 16));
		nameField.setBackground(new Color(0xe8e8e8));
		nameField.setPreferredSize(new Dimension(100,25));
		
		codeNameField.setEditable(false);
		codeNameField.setFont(new Font(null, Font.PLAIN, 16));
		codeNameField.setBackground(new Color(0xe8e8e8));
		codeNameField.setPreferredSize(new Dimension(100,25));
		
		numbersInUseArea.setEditable(false);
		numbersInUseArea.setFont(new Font(null, Font.PLAIN, 16));
		numbersInUseArea.setBackground(new Color(0xe8e8e8));

		infoPanel.add(nameField);
		infoPanel.add(codeNameField);
		infoPanel.add(numbersInUseArea);
		
		//------------------------ 2nd panel ------------------------
		phoneNumber.setEditable(true);
		phoneNumber.setFont(new Font(null, Font.PLAIN, 16));
		phoneNumber.setPreferredSize(new Dimension(150,25));
		
		smsTextArea.setEditable(false);
		smsTextArea.setFont(new Font(null, Font.PLAIN, 16));
		smsTextArea.setBackground(new Color(0xe8e8e8));
		
		findSMSButton.addActionListener(listener);
		
		smsPanel.add(phoneNumber);
		smsPanel.add(smsTextArea);
		smsPanel.add(findSMSButton);
		
		//------------------------ 3rd panel ------------------------
		partnersLabel.setFont(new Font(null, Font.BOLD, 16));
		partnersLabel.setPreferredSize(new Dimension(75,25));
		
		partnersTextArea.setFont(new Font(null, Font.PLAIN, 16));
		partnersTextArea.setEditable(false);
		partnersTextArea.setBackground(new Color(0xe8e8e8));
		for(Suspect partner: suspect.getPartners())
			partnersTextArea.append(partner.getName() + ", " + partner.getCodeName() + "\n");
		
		partnersPanel.add(partnersLabel);
		partnersPanel.add(partnersTextArea);
		
		//------------------------ 4rth panel -----------------------
		
		suggestedPartnersLabel.setFont(new Font(null, Font.BOLD, 16));
		suggestedPartnersLabel.setPreferredSize(new Dimension(170,25));
		suggestedPartnersLabel.setBackground(Color.LIGHT_GRAY);
		suggestedPartnersLabel.setOpaque(true);
		
		suggestedPartnersTextArea.setFont(new Font(null, Font.PLAIN, 16));
		suggestedPartnersTextArea.setEditable(false);
		suggestedPartnersTextArea.setBackground(new Color(0xe8e8e8));
		for(Suspect suggestedPartner: suspect.getSortedSuggestedPartners())
			suggestedPartnersTextArea.append(suggestedPartner.getName() + ", " + suggestedPartner.getCodeName() + "\n");
		
		suggestedPartnersPanel.add(suggestedPartnersLabel);
		suggestedPartnersPanel.add(suggestedPartnersTextArea);
		
		backToScreenButton.addActionListener(listener);
		
		//------------------------ Frame -----------------------------
		infoPanel.setBackground(Color.LIGHT_GRAY);
		infoPanel.setBorder(border);
		smsPanel.setBackground(Color.LIGHT_GRAY);
		smsPanel.setBorder(border);
		partnersPanel.setBackground(Color.LIGHT_GRAY);
		partnersPanel.setBorder(border);
		suggestedPartnersPanel.setBackground(Color.LIGHT_GRAY); // purple 0xa7a3d1
		suggestedPartnersPanel.setBorder(border);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY); // light purple
		
		frame.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		
		frame.add(infoPanel);
		frame.add(smsPanel);
		frame.add(partnersPanel);
		frame.add(suggestedPartnersPanel);
		frame.add(backToScreenButton);
		
		ImageIcon frameIcon = new ImageIcon("moving.jpg");
		frame.setIconImage(frameIcon.getImage());
		frame.setTitle("Suspect Page");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,800);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			final int PHONESIZE = 14;
			
			if(e.getSource().equals(backToScreenButton)) {
				frame.dispose();
				new LaunchPage(registry);
			}
			else if (e.getSource().equals(findSMSButton)) {
				smsTextArea.setText(null);
				String inputText = phoneNumber.getText();
				if(inputText.length() == PHONESIZE || isNumeric(inputText)) {
					ArrayList<SMS> smsList = registry.getMessages(phoneNumber.getText());
					for(SMS sms: smsList) {
						smsTextArea.append(sms.getSMSContent() + "\n");
					}
				}
				else {
					System.out.println("INVALID INPUT");
				}
			}
			
		}
		
		public static boolean isNumeric(String str) {
			if (str == null) {
				return false;
			}
			try {
				int num = Integer.parseInt(str);
			}
			catch (NumberFormatException nfe) {
				return false;
			}
			return true;
		}		
	}
}
