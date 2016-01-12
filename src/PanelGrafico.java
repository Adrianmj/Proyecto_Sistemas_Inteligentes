import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JComponent;

public class PanelGrafico extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Random rand = new Random();
	ArrayList<Comida> coordComidas = new ArrayList<Comida>();
	static ArrayList<Agente> poblacion = new ArrayList<Agente>();
	PanelBotones panel;
	boolean undefined = true;
	private int ano = 0;

	ArrayList<Agente> seleccionados = new ArrayList();
	int NUMCOMIDA = 200;
	int NUMPOB = 10;

	public PanelGrafico(PanelBotones panel) {
		this.panel = panel;
	}

	public static void quicksort(int izq, int der) {
		Agente pivote = poblacion.get(izq); // tomamos primer elemento como
		// pivote
		int i = izq; // i realiza la búsqueda de izquierda a derecha
		int j = der; // j realiza la búsqueda de derecha a izquierda
		Agente aux;

		while (i < j) { // mientras no se crucen las búsquedas
			while (poblacion.get(i).getBest() <= pivote.getBest() && i < j)
				i++; // busca elemento mayor que pivote
			while (poblacion.get(j).getBest() > pivote.getBest())
				j--; // busca elemento menor que pivote
			if (i < j) { // si no se han cruzado
				aux = poblacion.get(i); // los intercambia
				poblacion.set(i, poblacion.get(j));
				poblacion.set(j, aux);
			}
		}

		poblacion.set(izq, poblacion.get(j)); // se coloca el pivote en su lugar
		// de forma que
		// tendremos
		poblacion.set(j, pivote); // los menores a su izquierda y los mayores a
		// su
		// derecha
		if (izq < j - 1)
			quicksort(izq, j - 1); // ordenamos subarray izquierdo
		if (j + 1 < der)
			quicksort(j + 1, der); // ordenamos subarray derecho

	}

	//constructor
	public void definer() {
		int x1, y1;
		for (int i = 0; i < NUMCOMIDA; i++) {
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
		for (int i = 0; i < NUMPOB; i++) {
			x2 = (Math.random() * 2 * radius) - radius;
			ylim = Math.sqrt(radius * radius - x2 * x2);
			y2 = Math.random() * 2 * ylim - ylim;

			x1 = (int) x2 + this.getWidth() / 2;
			y1 = (int) y2 + this.getHeight() / 2;
			poblacion.add(new Agente(x1, y1, coordComidas, this.getWidth(), this.getHeight()));
		}
		undefined = false;
	}

	public boolean update() {
		ano++;
		if (0 == ano % 10) {
			this.quicksort(0, poblacion.size() - 1);
			System.out.println("ordenada:");
			System.out.print("[");
			for (int j = 0; j < poblacion.size(); j++) {
				System.out.print(poblacion.get(j).getBest() + ",");
			}
			System.out.println("]");
			seleccion();
		}
		for (int i = 0; i < poblacion.size(); i++) {
			
			if (!poblacion.get(i).accion()) {
				poblacion.get(i).move();
			}
			if (poblacion.get(i).getEnergy() <= 0) {
				poblacion.remove(i);
			}

		}
		if (poblacion.isEmpty()) {
			return true;
		}
		Graphics g = getGraphics();
		this.paintComponent(g);
		return false;

	}

	public void seleccion() {
		int mejores = poblacion.size() * 20/100 + 1;
		System.out.println(mejores);
		Agente seleccionado;
		
		seleccionados.clear();

		while (seleccionados.size() < 2) {
			seleccionado = poblacion.get((int)(poblacion.size() - Math.random() * mejores));

			if (seleccionados.contains(seleccionado) == false)
				seleccionados.add(seleccionado);
		}
		System.out.println(seleccionados.get(0));
		System.out.println(seleccionados.get(1));
		mutacion();
	}
	
	public void mutacion() {
		int corte = (int) (Math.random() * 3);
		int i = 0;
		//Añade al individuo al panel
		int x1, y1;
		double x2, y2;
		int radius = 15;
		double ylim;
		x2 = (Math.random() * 2 * radius) - radius;
		ylim = Math.sqrt(radius * radius - x2 * x2);
		y2 = Math.random() * 2 * ylim - ylim;
		x1 = (int) x2 + this.getWidth() / 2;
		y1 = (int) y2 + this.getHeight() / 2;
		Agente nuevo = new Agente(x1, y1, coordComidas, this.getWidth(), this.getHeight());
		
		
		//Le pasamos las propiedades
		for (i = 0; i < corte; i++) {
			nuevo.setProp(i, seleccionados.get(0).getProp(i));
		}
		for (i = corte; i < 3; i++) {
			nuevo.setProp(i, seleccionados.get(1).getProp(i));
		}
		poblacion.add(nuevo);
	}


	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		if (undefined) {
			this.definer();
		}
		g2d.setColor(Color.orange);
		for (int i = 0; i < coordComidas.size(); i++) {
			Point punto = coordComidas.get(i).getPunto();
			g2d.fillRect(punto.x, punto.y, 5, 5);

		}

		g.setColor(Color.green);
		for (int i = 0; i < poblacion.size(); i++) {
			Point punto = poblacion.get(i).getPunto();
			g2d.fillRect(punto.x, punto.y, 5, 5);
			g2d.drawOval(punto.x - poblacion.get(i).getRadius(), punto.y - poblacion.get(i).getRadius(),
					poblacion.get(i).getRadius() * 2, poblacion.get(i).getRadius() * 2);

		}

	}

}
