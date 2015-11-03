import java.awt.Point;
import java.util.ArrayList;

public class Agente {
	private int x, y;
	ArrayList<Comida> coordComidas;
	private int Radius;
	private int maxW, maxH;
	int lastMove = -1;
	int up, down, left, right;
	private int Energy;

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

	public void improve() {
		switch (this.lastMove) {
		case 0:
			this.right = this.right + 1;
			break;
		case 1:
			this.left = this.left + 1;
			break;
		case 2:
			this.down = this.down + 1;
			break;
		case 3:
			this.up = this.up + 1;
			break;

		default:
			break;
		}
	}

	public boolean eat(int i) {
		double dx, dy;
		dx = Math.abs(coordComidas.get(i).getX() - this.getX());
		dy = Math.abs(coordComidas.get(i).getY() - this.getY());
		double aux = Math.sqrt(dx * dx + dy * dy);
		if (aux < Radius) {

			this.setX(coordComidas.get(i).getX());
			this.setY(coordComidas.get(i).getY());
			this.improve();
			this.setEnergy(this.getEnergy() + 1);
			coordComidas.remove(i);
			return true;
		}
		return false;
	}

	public void move() {
		int aux = (int) (Math.random() * 4);
		this.lastMove = aux;
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
		
		if (this.getX() > maxW) {
			this.setX(maxW);
		}
		if (this.getY() > maxH) {
			this.setY(maxH);

		}
		if (this.getX() < 0) {
			this.setX(0);
		}
		if (this.getY() < 0) {
			this.setY(0);

		}
		this.setEnergy(this.getEnergy()-1);
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

	public int getEnergy() {
		return Energy;
	}

	public void setEnergy(int energy) {
		this.Energy = energy;
	}
}
