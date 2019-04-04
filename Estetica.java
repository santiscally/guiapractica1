public class Estetica extends Programa {
	private int cantSesiones;
	private static int cantidadEstetico;
	Estetica(){
		super();
	}
	Estetica(int numero, String nombre, Medicamento[] medicamentos, double precio, int cantidadEstetico, int cantSesiones){
		super(numero, nombre, medicamentos, precio);
		this.cantSesiones=cantSesiones;
	}
	public int getCantSesiones(){
		return cantSesiones;
	}
	public void setCantSesiones(int cantSesiones){
		this.cantSesiones=cantSesiones;
	}

	public double calcularPrecio(double precio2){
		return precio2*cantSesiones;
	}

	public static void setCantidadEstetico(int cantidadEstetico){
		Estetica.cantidadEstetico=cantidadEstetico;
	}

	public static int getCantidadEstetico(){
		return cantidadEstetico;
	}

	public boolean esEstetica(){return true;}


}
