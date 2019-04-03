import java.util.*;
public class HistoriaClinica {
  private int dni;
  private Calendar nac=Calendar.getInstance();
  private String nya;
  private char sexo;
  private int telefono;
  private boolean obraSocial;
  private Programa [] programas= new Programa[1];
  private Visita [] visitas=new Visita[1];

  HistoriaClinica(){
    for(int i=0;i<visitas.length;i++){
      visitas[i]=new Visita();
    }
  }
  HistoriaClinica (int dni, Calendar nac, String nya, char sexo, int telefono, boolean obraSocial, Programa [] programas){
    this.dni=dni;
    this.nac=nac;
    this.nya=nya;
    this.sexo=sexo;
    this.telefono=telefono;
    this.obraSocial=obraSocial;
    this.programas=programas;
    for (int i=0; i<visitas.length;i++){
      visitas[i]=new Visita();
    }
  }
  public int getDni(){
    return dni;
  }
  public Calendar getNac(){
    return nac;
  }
  public String getNya(){
    return nya;
  }
  public char getSexo(){
    return sexo;
  }
  public int getTelefono(){
    return telefono;
  }
  public boolean getObraSocial(){
    return obraSocial;
  }
  public Programa [] getProgramas(){
    return programas;
  }
  public Visita[] getVisitas(){
    return visitas;
  }
  public void setDni(int dni){
    this.dni=dni;
  }
  public void setNac(Calendar nac){
    this.nac=nac;
  }
  public void setNya(String nya){
    this.nya=nya;
  }
  public void setSexo(char sexo){
    this.sexo=sexo;
  }
  public void setTelefono (int telefono){
    this.telefono=telefono;
  }
  public void setObraSocial(boolean obraSocial){
    this.obraSocial=obraSocial;
  }
  public void setProgramas(Programa[] programas){
    this.programas=programas;
  }
  public void setVisitas(int indice, int id, Calendar fechaVisita, String profesional, String comentarios){
    visitas[indice].setId(id);
    visitas[indice].setFechaVisita(fechaVisita);
    visitas[indice].setProfesional(profesional);
    visitas[indice].setComentarios(comentarios);
  }

  public boolean esMenor(){
    Calendar fechaActual= Calendar.getInstance();
    return (fechaActual.get(Calendar.YEAR)-nac.get(Calendar.YEAR))<16;
  }

  

}
