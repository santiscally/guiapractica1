
public class Medicamento {
	private int codigo;
	private String nombre;
	private double precio;
	private boolean esCrema;


	Medicamento(){}
	Medicamento (int codigo, String nombre, double precio, boolean esCrema){
		this.codigo=codigo;
		this.nombre=nombre;
		this.precio=precio;
		this.esCrema=esCrema;
	}
	public int getCodigo(){
		return codigo;
	}
	public String getNombre(){
		return nombre;
	}
	public double getPrecio(){
		return precio;
	}
	public boolean getEsCrema(){
		return esCrema;
	}
	public void setCodigo(int codigo){
		this.codigo=codigo;
	}
	public void setNombre(String nombre){
		this.nombre=nombre;
	}
	public void setPrecio(double precio){
		this.precio=precio;
	}
	public void setEsCrema(boolean esCrema){
		this.esCrema=esCrema;
	}

}
