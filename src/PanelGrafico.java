import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	ArrayList<Recurso> coordRecursos = new ArrayList<Recurso>();
	ArrayList<Edificio> edificios = new ArrayList<Edificio>();
	static ArrayList<Agente> poblacion = new ArrayList<Agente>();
	PanelBotones panel;
	boolean undefined = true;
	private int ano = 0;

	ArrayList<Agente> seleccionados = new ArrayList<Agente>();
	int NUMRecurso = 200;
	int NUMPOB = 6;

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

	// constructor
	public void definer() {
		int x1, y1;
		for (int i = 0; i < NUMRecurso; i++) {
			x1 = (int) (Math.random() * this.getWidth());
			y1 = (int) (Math.random() * this.getHeight());
			if (x1 > this.getWidth() - 5) {
				x1 = x1 + x1 - this.getWidth() - 5;
			}
			if (y1 > this.getWidth() - 5) {
				y1 = y1 + y1 - this.getHeight() - 5;
			}

			coordRecursos.add(new Recurso(x1, y1));
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
			poblacion.add(new Agente(x1, y1, coordRecursos, this.getWidth(), this.getHeight()));
		}
		undefined = false;
	}

	public boolean update() {
		ano++;
		if (0 == ano % 10) {
			PanelGrafico.quicksort(0, poblacion.size() - 1);
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
		int mejores = poblacion.size() * 20 / 100 + 1;
		System.out.println(mejores);
		Agente seleccionado;

		seleccionados.clear();

		System.out.println("Tamaño de poblasion " + poblacion.size());
		if (poblacion.size() > 1) {
			if (poblacion.size() == 2) {
				seleccionado = poblacion.get(0);
				seleccionados.add(seleccionado);
				seleccionado = poblacion.get(1);
				seleccionados.add(seleccionado);
			} else {
			while (seleccionados.size() < 2 && mejores > 1) {
				System.out.println("TAMAÑO DE LOS SLEKSIONADOS " + seleccionados.size());
				System.out.println("TAMAÑO DE LA POBLASION " + poblacion.size());
				System.out.println(mejores);
	
					seleccionado = poblacion.get((int) (poblacion.size() - Math.random() * mejores));

					if (seleccionados.contains(seleccionado) == false)
						seleccionados.add(seleccionado);

				}
			}
			if (mejores > 1) {
				System.out.println(seleccionados.get(0));
				System.out.println(seleccionados.get(1));
				mutacion();
			}
			else {
				System.out.println("No se ha podido emparejar");
			}
		}

	}

	public void mutacion() {
		int corte = (int) (Math.random() * 3);
		int i = 0;
		// Añade al individuo al panel
		int x1, y1;
		double x2, y2;
		int radius = 15;
		double ylim;
		x2 = (Math.random() * 2 * radius) - radius;
		ylim = Math.sqrt(radius * radius - x2 * x2);
		y2 = Math.random() * 2 * ylim - ylim;
		x1 = (int) x2 + this.getWidth() / 2;
		y1 = (int) y2 + this.getHeight() / 2;
		Agente nuevo = new Agente(x1, y1, coordRecursos, this.getWidth(), this.getHeight());
		//el hijo heredara un 25% de los recursos de cada padre
		int recursosP = 0;
		int recursosM = 0;
		recursosP = seleccionados.get(0).getRecursos()/4;
		recursosM = seleccionados.get(1).getRecursos()/4;
		
		seleccionados.get(0).setRecursos(seleccionados.get(0).getRecursos() - recursosP);
		seleccionados.get(1).setRecursos(seleccionados.get(1).getRecursos() - recursosM);
		
		nuevo.setRecursos(recursosP + recursosM);

		// Le pasamos las propiedades
		for (i = 0; i < corte; i++) {
			nuevo.setProp(i, seleccionados.get(0).getProp(i));
		}
		for (i = corte; i < 3; i++) {
			nuevo.setProp(i, seleccionados.get(1).getProp(i));
		}
		if (edificios.size() > 0) {
			for (i = 0; i < edificios.size(); i++) {
				nuevo.setEnergy(edificios.get(i).efectoMutacion(1, nuevo.getEnergy()));
				nuevo.setStrength(edificios.get(i).efectoMutacion(2, nuevo.getStrength()));
				nuevo.setInt(edificios.get(i).efectoMutacion(3, nuevo.getInt()));
			}
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
		for (int i = 0; i < coordRecursos.size(); i++) {
			if (coordRecursos.get(i).getTipo() == 0) {
				g2d.setColor(Color.blue);
			}
			else {
				g2d.setColor(Color.orange);
			}
			Point punto = coordRecursos.get(i).getPunto();
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
