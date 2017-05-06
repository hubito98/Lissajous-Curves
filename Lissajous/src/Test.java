import java.awt.EventQueue;

/**
 * This program shows projection of two pendulums 
 * it is called Lissajous Curve
 * @author Hubert Skrzypczak
 */

public class Test {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainFrame();
			}
		});
	}
}
