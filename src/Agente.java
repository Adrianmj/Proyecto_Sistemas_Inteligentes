import java.awt.Point;
import java.util.ArrayList;

public class Agente {
	private int x;
	private int y;
	ArrayList<Comida> coordComidas;
	private int Radius;
	private int maxW;
	private int maxH;
	double dx;
	double dy;
	public Agente(int x, int y, ArrayList<Comida> coordComidas, int maxW, int maxH) {
		this.coordComidas = coordComidas;
		this.setX(x);
		this.setY(y);
		this.setRadius(25);
		this.maxH = maxH;
		this.maxW = maxW;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public Point getPunto() {
		Point punto = new Point(this.getX(), this.getY());
		return punto;
	}

	public boolean eat(int i) {
	
		dx = Math.abs(coordComidas.get(i).getX() - this.getX());
		dy = Math.abs(coordComidas.get(i).getY() - this.getY());
		double aux = Math.sqrt(dx * dx + dy * dy);
		if (aux < Radius) {

			this.setX(coordComidas.get(i).getX());
			this.setY(coordComidas.get(i).getY());

			coordComidas.remove(i);
			return true;
		}
		return false;
	}

	public void move() {
		int aux = (int) (Math.random() * 4);
		switch (aux) {
		case 0:
			this.setX(this.getX() + this.getRadius() + 1);
			break;
		case 1:
			this.setX(this.getX() - this.getRadius() - 1);

			break;
		case 2:
			this.setY(this.getY() + this.getRadius() + 1);
			break;
		case 3:
			this.setY(this.getY() - this.getRadius() - 1);
			break;
		}
		if (this.getX()> maxW) {
			this.setX(maxW);
		} if(this.getY() > maxH){
			this.setY(maxH);

		} if (this.getX() < 0) {
			this.setX(0);
		}
		if (this.getY() < 0) {
			this.setY(0);

		}
	}

	public boolean accion() {

		for (int i = 0; i < coordComidas.size(); i++) {
			if (eat(i)) {
				return true;
			}
		}
		return false;
	}

	public int getRadius() {
		return Radius;
	}

	public void setRadius(int radius) {
		Radius = radius;
	}
}
