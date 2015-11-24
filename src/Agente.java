import java.awt.Point;
import java.util.ArrayList;

public class Agente {
	private int x, y;
	ArrayList<Comida> coordComidas;
	private int Radius;
	private int maxW, maxH;
	int lastMove = -1;
	int up, down, left, right;
	private int Energy = 20;
	private int propUp, propDown, propRight, propLeft;
	private int best = -1;
	public Agente(int x, int y, ArrayList<Comida> coordComidas, int maxW, int maxH) {
		this.coordComidas = coordComidas;
		this.setX(x);
		this.setY(y);
		this.setRadius(25);
		this.maxH = maxH;
		this.maxW = maxW;
		this.setPropRight(25);
		this.setPropLeft(25);
		this.setPropUp(25);
		this.setPropDown(25);

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
	
	public int getProp(int i) {
		switch (i) {
		case 0:
			return this.propRight;
		case 1:
			return this.propLeft;
		case 2:
			return this.propUp;
		case 3:
			return this.propDown;
		default:
			return -1;
		}
	}
	
	public void setProp(int i, int valor) {
		switch (i) {
		case 0:
			this.propRight = valor;
			break;
		case 1:
			this.propLeft = valor;
			break;
		case 2:
			this.propUp = valor;
			break;
		case 3:
			this.propDown = valor;
			break;
		default:
			System.out.println("Error");
			break;
		}
	}

	public int getBest() {
		int max = 0;
	
		String mejor = "Nadie";
		if (max <= this.right) {
			max = this.right;
			mejor = "Derecha";
			best = 0;
		}
		if (max <= this.left) {
			max = this.left;
			mejor = "Izquierda";
			best = 1;
		}
		if (max <= this.down) {
			max = this.down;
			mejor = "Abajo";
			best = 2;
		}
		if (max <= this.up) {
			max = this.up;
			mejor = "Arriba";
			best = 3;
		}

		return best;
	}

	public void improve(String mejor) {
		this.propLeft = this.propLeft - 1;
		this.propRight = this.propRight - 1;
		this.propDown = this.propDown - 1;
		this.propUp = this.propUp - 1;
		
		switch (mejor) {
		case "Derecha":
			this.propRight = this.propRight + 4;
			break;
		case "Izquierda":
			this.left = this.left + 4;
			break;
		case "Arriba":
			this.up = this.up + 4;
			break;
		case "Abajo":
			this.down = this.down + 4;
			break;
		default:
			break;
		}
	}

	public void resetStats() {
		this.right = 0;
		this.left = 0;
		this.up = 0;
		this.down = 0;
	}

	public Point getPunto() {
		Point punto = new Point(this.getX(), this.getY());
		return punto;
	}

	public void opcCounter() {
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
			this.opcCounter();
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
		this.setEnergy(this.getEnergy() - 1);
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

	public int getPropUp() {
		return propUp;
	}

	public void setPropUp(int propUp) {
		this.propUp = propUp;
	}

	public int getPropRight() {
		return propRight;
	}

	public void setPropRight(int propRight) {
		this.propRight = propRight;
	}

	public int getPropLeft() {
		return propLeft;
	}

	public void setPropLeft(int propLeft) {
		this.propLeft = propLeft;
	}

	public int getPropDown() {
		return propDown;
	}

	public void setPropDown(int propDown) {
		this.propDown = propDown;
	}
}
