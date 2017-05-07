import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel {
	
	private double speedX, speedY, startLocationX, startLocationY, 
					amplitudeX, amplitudeY;
	private double padding = 40, middleX, middleY;
	private double time = 0;
	private boolean running;
	
	private JButton startStopButton, restartButton;
	
	private List<Point> points;
	
	/**
	 * 
	 * @param speedX angular frequency of horizontal pendulum
	 * @param speedY angular frequency of vertical pendulum
	 * @param startLocationX initial phase of horizontal pendulum
	 * @param startLocationY initial phase of vertical pendulum
	 * @param amplitudeX amplitude of horizontal pendulum
	 * @param amplitudeY amplitude of vertical pendulum
	 */
	public DrawingPanel(double speedX, double speedY, double startLocationX, 
			double startLocationY, double amplitudeX, double amplitudeY) {
		super();
		
		this.speedX = speedX;
		this.speedY = speedY;
		this.startLocationX = startLocationX;
		this.startLocationY = startLocationY;
		this.amplitudeX = amplitudeX;
		this.amplitudeY = amplitudeY;
		
		setPreferredSize(new Dimension((int)(amplitudeX * 2 + 2 * padding), (int)(amplitudeY * 2 + 2 * padding)));
		
		init();
		calculateFirstPoint();
	}
	
	public void init() {
		/*
		 * coordinates of middle
		 */
		middleX = amplitudeX + padding;
		middleY = amplitudeY + padding;
		points = new ArrayList<Point>(1000);
		initButtons();	
		running = true;
	}
	
	public void initButtons() {
		startStopButton = new JButton("Stop");
			startStopButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(running) {
						running = false;
						startStopButton.setText("Start");
					}else {
						running = true;
						startStopButton.setText("Stop");
						repaint();
					}
				}
			});
		
		restartButton = new JButton("Restart");
			restartButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					running = false;
					points = new ArrayList<Point>(1000);
					time = 0;
					calculateFirstPoint();
					startStopButton.setText("Stop");
					running = true;
					repaint();
				}
			});
	
		add(startStopButton);
		add(restartButton);
	}
	
	public void calculateFirstPoint() {
		double x = amplitudeX * Math.sin(speedX * time + startLocationX);
		double y = amplitudeY * Math.sin(speedY * time + startLocationY);
		points.add(new Point((int)x, (int)y));
		time += 0.001;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		calculateCoordinates();
		drawCurve(g2d);
		
		if(running)
			repaint();
	}
	
	public void calculateCoordinates() {
		double x = amplitudeX * Math.sin(speedX * time + startLocationX);
		double y = amplitudeY * Math.sin(speedY * time + startLocationY);
		points.add(new Point((int)x, (int)y));
		time += 0.001;
	}
	
	public void drawCurve(Graphics2D g2d) {
		for(int i = 1; i < points.size(); i++) {
			int x1 = (int) points.get(i-1).getX();
			int x2 = (int) points.get(i).getX();
			
			int y1 = (int) points.get(i-1).getY();
			int y2 = (int) points.get(i).getY();
			
			g2d.setColor(Color.black);
			g2d.drawLine(x1 + (int)middleX, y1 + (int)middleY, x2 + (int)middleX, y2 + (int)middleY);
		}
	}
}

