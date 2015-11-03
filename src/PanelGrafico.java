import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JComponent;

public class PanelGrafico extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Random rand = new Random();
	ArrayList<Comida> coordComidas = new ArrayList<Comida>();
	ArrayList<Agente> poblacion = new ArrayList<Agente>();
	PanelBotones panel;
	boolean undefined = true;
	private int ano = 0;
	public PanelGrafico(PanelBotones panel) {
		this.panel = panel;
	}

	public void definer() {
		int x1, y1;
		for (int i = 0; i < 20; i++) {
			x1 = (int) (Math.random() * this.getWidth());
			y1 = (int) (Math.random() * this.getHeight());
			if (x1 > this.getWidth() - 5) {
				x1 = x1 + x1 - this.getWidth() - 5;
			}
			if (y1 > this.getWidth() - 5) {
				y1 = y1 + y1 - this.getHeight() - 5;
			}

			coordComidas.add(new Comida(x1, y1));
		}
		double x2, y2;
		int radius = 15;
		double ylim;
		for (int i = 0; i < 1; i++) {
			x2 = (Math.random() * 2 * radius) - radius;
			ylim = Math.sqrt(radius * radius - x2 * x2);
			y2 = Math.random() * 2 * ylim - ylim;

			// x1 = 175 + (int) (Math.random() * (this.getWidth()-350));
			// y1 = 200 + (int) (Math.random() * (this.getHeight()-400));

			x1 = (int) x2 + this.getWidth() / 2;
			y1 = (int) y2 + this.getHeight() / 2;
			poblacion.add(new Agente(x1, y1, coordComidas, this.getWidth(), this.getHeight()));
		}
		undefined = false;
	}

	public boolean update() {
		ano++;
		if (ano == 10) {
			System.out.println();
		}
		for (int i = 0; i < poblacion.size(); i++) {

			if (!poblacion.get(i).accion()) {
				poblacion.get(i).move();
			}
			if (poblacion.get(i).getEnergy() <= 0) {
				poblacion.remove(i);
			}
			if (ano == 10) {
				System.out.println(poblacion.get(i).getBest());
			}
		}
		if (poblacion.isEmpty()) {
			return true;
		}
		Graphics g = getGraphics();
		this.paintComponent(g);
		return false;

	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		if (undefined) {
			this.definer();
		}
		g.setColor(Color.orange);
		for (int i = 0; i < coordComidas.size(); i++) {
			Point punto = coordComidas.get(i).getPunto();
			g.fillRect(punto.x, punto.y, 5, 5);

		}

		g.setColor(Color.green);
		for (int i = 0; i < poblacion.size(); i++) {
			Point punto = poblacion.get(i).getPunto();

			g.fillRect(punto.x, punto.y, 5, 5);
			g.drawOval(punto.x - poblacion.get(i).getRadius(), punto.y - poblacion.get(i).getRadius(),
					poblacion.get(i).getRadius() * 2, poblacion.get(i).getRadius() * 2);

		}

	}
}
