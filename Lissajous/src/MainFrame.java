import javax.swing.JFrame;

/**
 * 
 * @author Hubert Skrzypczak
 *
 */

public class MainFrame extends JFrame{
	
	public MainFrame() {
		super("Krzywe Lissajous");
		
		add(new MainPanel());
		
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
