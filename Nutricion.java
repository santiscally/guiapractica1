public class Nutricion extends Programa{
	private double maxCalorias;
	private String [] comidas=new String[4];

	Nutricion(){
		super();
	}
	Nutricion (int numero, String nombre, Medicamento[] medicamentos, double precio, double maxCalorias, String [] comidas){
		super(numero, nombre, medicamentos, precio);
		this.maxCalorias=maxCalorias;
		this.comidas=comidas;
	}

	public double getMaxCalorias() {
		return maxCalorias;
	}
	public void setMaxCalorias(double maxCalorias) {
		this.maxCalorias = maxCalorias;
	}

	public String[] getComidas(){
		return comidas;
	}

	public void setComidas(int indice, String comida){
		comidas[indice]=comida;
	}
	public void setPrecio(double precio){
		this.precio=precio;
	}
	public double calcularPrecio(double precio2){
		return precio2;
	}

	public boolean esNutricion(){return true;}



}
