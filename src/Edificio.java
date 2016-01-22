
public class Edificio {
	private int TipoMejora;
	private int calidad; //cuanto mejora una estadistica - requerira el doble de recursos e inteligencia que la calidad
						// anterior para aumentar la calidad
	public Edificio(int tipo) {
		//tipo: 1 - energia, 2 - fuerza, 3 - inteligencia
		TipoMejora = tipo;
		calidad = 1;
	}
	
	public int efectoMutacion(int tipo, int caracteristica) {
		if (tipo == this.TipoMejora)
			return caracteristica + calidad;
		return caracteristica;
	}

}
