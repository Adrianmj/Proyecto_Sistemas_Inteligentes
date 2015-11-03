import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.Timer;

public class PanelBotones extends Container {
	JButton start = new JButton("Start");
	JButton step = new JButton("Step");
	JButton pause = new JButton("Pause");
	PanelGrafico Panel;
	public Timer timer;
	public PanelBotones(PanelGrafico Panel) {
		timer = new Timer(500, new timeListener());
		this.Panel = Panel;
		Dimension d = new Dimension(128, 50);
		start.setPreferredSize(d);
		step.setPreferredSize(d);
		pause.setPreferredSize(d);

		this.setLayout(new FlowLayout(1, 1, 1));
		step.addActionListener(new action());
		start.addActionListener(new action());
		pause.addActionListener(new action());

		this.add(start);
		this.add(step);
		this.add(pause);
	}

	public class action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == step) {
				timer.stop();
				Panel.update();
			}
			if (e.getSource() == start) {
					timer.start();
			}
		}
	}
	public class timeListener implements ActionListener{

		
		public void actionPerformed(ActionEvent e) {
				Panel.update();			
		}
		
	}
}
