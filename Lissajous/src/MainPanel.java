import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 
 * @author Hubert Skrzypczak
 *
 */

public class MainPanel extends JPanel {

	public static final int WIDTH = 480;

	private JPanel panelX, panelY;
	private JTextField speedX, speedY, startLocationX,
						startLocationY, amplitudeX, amplitudeY;
	private JLabel speedXLabel, speedYLabel, startLocationXLabel, 
					startLocationYLabel, amplitudeXLabel, amplitudeYLabel;
	private JCheckBox piCheckBoxX, piCheckBoxY;
	private Font font;
	private JButton okButton;
	private double dSpeedX, dSpeedY, dStartLocationX, 
					dStartLocationY, dAmplitudeX, dAmplitudeY;

	public MainPanel() {
		super();

		setPreferredSize(new Dimension(WIDTH, 160));
		this.setLayout(new FlowLayout());

		init();
	}

	public void init() {
		initOkButton();
		initPanels();
		initTextFields();
		initLabels();
		initCheckBoxes();
		
		addElementsToPanels();
		addToMainPanel();
	}
	
	public void initOkButton() {
		okButton = new JButton("Rysuj");
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadValues();
				
				Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						new DrawingFrame(dSpeedX, dSpeedY, dStartLocationX, dStartLocationY, dAmplitudeX, dAmplitudeY);
					}
				});
				thread.start();
			}
		});
	}
	
	public void loadValues() {
		if(speedX.getText() == "" || speedY.getText() == "" || 
				amplitudeX.getText() == "" || amplitudeY.getText() == "") return;
		
		dSpeedX = Double.parseDouble(speedX.getText());
		dSpeedY = Double.parseDouble(speedY.getText());
		
		dAmplitudeX = Double.parseDouble(amplitudeX.getText());
		dAmplitudeY = Double.parseDouble(amplitudeY.getText());
		
		if(piCheckBoxX.isSelected()) {
			dStartLocationX = Math.PI/2;
		}else {
			if(startLocationX.getText() == "") return;
			dStartLocationX = Double.parseDouble(startLocationX.getText());
		}
		
		if(piCheckBoxY.isSelected()) {
			dStartLocationY = Math.PI/2;
		}else {
			if(startLocationY.getText() == "") return;
			dStartLocationY = Double.parseDouble(startLocationY.getText());
		}
	}
	
	public void initPanels() {
		panelX = new JPanel(new FlowLayout());
		panelX.setPreferredSize(new Dimension(WIDTH, 50));
		
		panelY = new JPanel(new FlowLayout());
		panelY.setPreferredSize(new Dimension(WIDTH, 50));
	}
	
	public void initTextFields() {
		speedX = new JTextField("2", 3);
		startLocationX = new JTextField("0", 3);
		amplitudeX = new JTextField("200", 4);
		
		speedY = new JTextField("3", 3);
		startLocationY = new JTextField("0", 3);
		amplitudeY = new JTextField("200", 4);
	}
	
	public void initLabels() {
		speedXLabel = new JLabel("     Częstość kołowa w poziomie");
		startLocationXLabel = new JLabel("     Położenie początkowe w poziomie");
		amplitudeXLabel = new JLabel("     Amplituda w poziomie");
		
		speedYLabel = new JLabel("     Częstość kołowa w pionie");
		startLocationYLabel = new JLabel("     Położenie początkowe w pionie");
		amplitudeYLabel = new JLabel("     Amplituda w pionie");

		font = new Font("Arial", Font.PLAIN, 10);
		speedXLabel.setFont(font);
		speedYLabel.setFont(font);
		startLocationXLabel.setFont(font);
		startLocationYLabel.setFont(font);
		amplitudeXLabel.setFont(font);
		amplitudeYLabel.setFont(font);
	}
	
	public void initCheckBoxes() {
		piCheckBoxX = new JCheckBox("PI/2", false);
		piCheckBoxX.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				startLocationX.setEditable(!piCheckBoxX.isSelected());
			}
		});
		
		piCheckBoxY = new JCheckBox("PI/2", false);
		piCheckBoxY.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				startLocationY.setEditable(!piCheckBoxY.isSelected());
			}
		});
	}
	
	public void addElementsToPanels() {
		panelX.add(amplitudeXLabel);
		panelX.add(amplitudeX);
		panelX.add(speedXLabel);
		panelX.add(speedX);
		panelX.add(startLocationXLabel);
		panelX.add(startLocationX);
		panelX.add(piCheckBoxX);

		panelY.add(amplitudeYLabel);
		panelY.add(amplitudeY);
		panelY.add(speedYLabel);
		panelY.add(speedY);
		panelY.add(startLocationYLabel);
		panelY.add(startLocationY);
		panelY.add(piCheckBoxY);
	}
	
	public void addToMainPanel() {
		add(panelX);
		add(panelY);
		add(okButton);
	}
}
