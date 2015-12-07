import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
public class MainWindow {
	private Dimension size = new Dimension(403,450);
	protected JFrame boardFrame;
	PanelGrafico panel;
	PanelBotones botones;
	public MainWindow() {
		boardFrame = new JFrame();
		boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		boardFrame.setTitle("Proyecto Sistemas Inteligentes");
		boardFrame.setSize(size);
		boardFrame.setLayout(new BorderLayout());
		boardFrame.setMinimumSize(size);
		boardFrame.setLocationRelativeTo(null);
		panel = new PanelGrafico(botones);
		panel.setPreferredSize(new Dimension(boardFrame.getWidth(),boardFrame.getHeight()-50));
		panel.setMaximumSize(panel.getPreferredSize());
		panel.setMinimumSize(panel.getPreferredSize());
		
		boardFrame.add(panel,BorderLayout.CENTER);
		
		botones = new PanelBotones(panel);

		boardFrame.add(botones,BorderLayout.SOUTH);
		boardFrame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainWindow();
	
	}
}
