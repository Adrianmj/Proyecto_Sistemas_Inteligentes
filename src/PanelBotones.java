import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class PanelBotones extends Container {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton start = new JButton("Start");
	JButton step = new JButton("Step");
	JButton pause = new JButton("Pause");
	PanelGrafico Panel;
	public Timer timer;

	public PanelBotones(PanelGrafico Panel) {
		timer = new Timer(50, new timeListener());
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


	public void finalizer(){
		this.timer.stop();
		JFrame end = new JFrame();
		end.setTitle("Finalizar");
		end.setSize(230, 100);
		end.setLocationRelativeTo(null);
		JLabel fin = new JLabel("Poblacion Muerta",SwingConstants.CENTER);
		end.add(fin);
		end.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		end.setVisible(true);
	}
	public class action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == step) {
				timer.stop();
				if (Panel.update()) {
					finalizer();
				}
			}
			if (e.getSource() == start) {
					timer.start();
			}
		}
	}

	public class timeListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (Panel.update()) {
				finalizer();
			}
		}

	}
}
