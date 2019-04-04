abstract public class Programa implements ICalcular {
	protected int numero;
	protected String nombre;
	protected Medicamento[] medicamentos=new Medicamento[1];
	protected double precio;


	Programa(){}
	Programa(int numero, String nombre, Medicamento[] medicamentos, double precio){
		this.numero=numero;
		this.nombre=nombre;
		this.medicamentos=medicamentos;
		this.precio=precio;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Medicamento[] getMedicamento(){
		return medicamentos;
	}
	public void setMedicamento(Medicamento[]medicamentos){
		this.medicamentos=medicamentos;
	}
	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio){
		this.precio=precio;
	}


	public void agregarMedicamento(Medicamento medicamento, int indice){
		medicamentos[indice]=medicamento;
	}

	public boolean esEstetica(){return false;}

	public boolean esNutricion(){return false;}

	abstract public double calcularPrecio(double precio);

}
