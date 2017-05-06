import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author Hubert Skrzypczak
 *
 */

public class DrawingFrame extends JFrame{
	
	private JPanel drawingPanel;
	
	
	/**
	 * 
	 * @param speedX angular frequency of horizontal pendulum
	 * @param speedY angular frequency of vertical pendulum
	 * @param startLocationX initial phase of horizontal pendulum
	 * @param startLocationY initial phase of vertical pendulum
	 * @param amplitudeX amplitude of horizontal pendulum
	 * @param amplitudeY amplitude of vertical pendulum
	 */
	public DrawingFrame(double speedX, double speedY, double startLocationX, 
			double startLocationY, double amplitudeX, double amplitudeY) {
		super("Rysowanie figury Lissajous");
		
		drawingPanel = new DrawingPanel(speedX, speedY, startLocationX, startLocationY, amplitudeX, amplitudeY);
		
		add(drawingPanel);
		
		pack();
		setLocation(400, 200);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
