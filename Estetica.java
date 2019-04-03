public class Estetica extends Programa {
	private int cantSesiones;

	Estetica(){
		super();
	}
	Estetica(int numero, String nombre, Medicamento[] medicamentos, double precio, int cantSesiones){
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

	public boolean esEstetica(){return true;}


}
