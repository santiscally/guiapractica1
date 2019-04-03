import java.util.*;
public class Visita {
  private int id;
  private Calendar fechaVisita=Calendar.getInstance();
  private String profesional;
  private String comentarios;

  Visita (){}
  Visita (int id, Calendar fechaVisita, String profesional, String comentarios){
    this.id=id;
    this.fechaVisita=fechaVisita;
    this.profesional=profesional;
    this.comentarios=comentarios;
  }
  public int getId(){
    return id;
  }
  public Calendar getFechaVisita(){
    return fechaVisita;
  }
  public String getProfesional(){
    return profesional;
  }
  public String getComentarios(){
    return comentarios;
  }
  public void setId(int id){
    this.id=id;
  }
  public void setFechaVisita(Calendar fechaVisita){
    this.fechaVisita=fechaVisita;
  }
  public void setProfesional(String profesional){
    this.profesional=profesional;
  }
  public void setComentarios(String comentarios){
    this.comentarios=comentarios;
  }
}
