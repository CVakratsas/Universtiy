import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BottomPanel extends JPanel implements ActionListener {
	
	final int PANEL_WIDTH = 450; //450
	final int PANEL_HEIGHT = 200; //200
	Image moving;
	Image standing;
	Image resizedBackground;
	Timer timer;
	Timer stage3Timer;
	int xVelocity = 2;
	int x = 0;
	int y = 0;
	int stage = 1;
	
	public BottomPanel() {
		
		this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		this.setBackground(Color.LIGHT_GRAY);
		
		moving = new ImageIcon("moving.jpg").getImage();
		x -= moving.getWidth(null) * 2;
		standing = new ImageIcon("standing.png").getImage();
		Image background = new ImageIcon("urban.jpg").getImage();
		resizedBackground = resizeImage(background, PANEL_WIDTH, PANEL_HEIGHT);
		timer = new Timer(10, this);
		stage3Timer = new Timer(1500, this);
		timer.start();
	}
	
	public Image resizeImage(Image originalImage, int targetWidth, int targetHeight) {
	    BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
	    Graphics2D graphics2D = resizedImage.createGraphics();
	    graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
	    graphics2D.dispose();
	    return resizedImage;
	}
	
	public void paint(Graphics g) {
		
		super.paint(g);
		
		Graphics2D g2D = (Graphics2D) g;
		
		g2D.drawImage(resizedBackground, 0, 0, null);
		if(stage == 1)
			g2D.drawImage(moving, x, y, null);
		else if(stage == 2) {
			
			g2D.drawImage(standing, x, y, null);
			stage = 3;
		}
		else {
			g2D.drawImage(moving, x, y, null);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(stage == 1) {
			x += xVelocity;
		}
		if(stage == 3) {
			stage3Timer.setDelay(6);
			x -= xVelocity * 2;
		}
		if(x == 170) {
			stage = 2;
			timer.stop();
			stage3Timer.setDelay(0);
			stage3Timer.start();
		}
		repaint();
		
	}
}
