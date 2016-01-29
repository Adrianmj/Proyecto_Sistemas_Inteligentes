
import java.awt.Point;

public class Recurso {
	private int x;
	private int y;
	private int tipo; //tipo 0 = comida 1 = recurso (material edificio)
	private static final int SIZE = 5;

	public Recurso(int x, int y) {
		this.setX(x);
		this.setY(y);
		int random = (int) (Math.random() * 10);
		if (random < 7)
			this.tipo = 0;
		else 
			this.tipo = 1;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Point getPunto() {
		Point punto = new Point(this.getX(), this.getY());
		return punto;
	}

	public int getTipo() {
		return this.tipo;
	}
	
	public static int getSize() {
		return SIZE;
	}
}
