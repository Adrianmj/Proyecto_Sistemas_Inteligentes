import java.awt.Point;

public class Edificio {
	private int TipoMejora;
	private int calidad; //cuanto mejora una estadistica - requerira el doble de recursos e inteligencia que la calidad
						// anterior para aumentar la calidad
	private Point punto;
	public Edificio(int tipo, Point punto) {
		//tipo: 1 - energia, 2 - fuerza, 3 - inteligencia
		this.punto = punto;
		TipoMejora = tipo;
		calidad = 0;
	}
	public Point getPunto(){
		return this.punto;
	}
	
	public void setPoint( Point pepe) {
		this.punto = pepe;
	}
	public int efectoMutacion(int caracteristica) {
			return caracteristica + calidad;
	}
	
	public int getTipo() {
		return TipoMejora;
	}
	
	public int getCalidad() {
		return calidad;
	}
	
	public void addCalidad() {
		this.calidad += 1;
	}
}
