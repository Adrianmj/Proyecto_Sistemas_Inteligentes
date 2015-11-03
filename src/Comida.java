
import java.awt.Point;

public class Comida {
	private int x;
	private int y;
	private static final int SIZE = 5;

	public Comida(int x, int y) {
		this.setX(x);
		this.setY(y);
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

	public static int getSize() {
		return SIZE;
	}
}
