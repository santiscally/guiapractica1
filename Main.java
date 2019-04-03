import java.util.*;
public class Main{
  public static void main(String[] args) {
    HistoriaClinica[] historias= new HistoriaClinica[1];
    Programa[] programas=new Programa [1];
    opciones(programas, historias);
  }
  public static void opciones(Programa [] programas, HistoriaClinica[]historias){
    Scanner stdin= new Scanner (System.in);
    int opcion=0;

    while(true){
      mostrarOpciones();
      System.out.print ("\nIngrese la opcion que desea ejecutar: ");
      opcion=asignarInt();
      switch(opcion){
        case 1:
          ingresarProgramas(programas);
          break;
        case 2:
          ingresarHistoriasClinicas(programas, historias);
          break;
        case 3:
          mostrarPaciente(historias);
          break;
        case 4:
          mostrarCantSinObraSocial(historias);
          break;
        case 5:
          mostrarPacientesDeProgramas(programas, historias);
          break;
        // FALTA AGREGAR CASO DE USOcase 6:
        //   mostrarVisita(historias);
        //   break;
        // case 7:
        //   mostrarCantTratamientoEstetico();
        //   break;
        case 8:
          System.exit(0);
        default:
          System.out.print("\nOPCION INVALIDA, INGRESAR NUEVAMENTE\n");
          break;
      }
    }

  }
  public static void mostrarOpciones(){
    System.out.println("1. Ingresar informacion para los programas y tratamientos existentes");
    System.out.println("2. Ingresar informacion correspondiente a las historias clinicas");
    System.out.println("3. Mostrar informacion del paciente");
    System.out.println("4. Mostrar la cantidad de pacientes sin obra social");
    System.out.println("5. Buscar por pantalla visitas correspondiente a determinada programa");
    System.out.println("6. Mostrar informacion de los tratamientos esteticos");
    System.out.println("7. Ver visitas y sus respectivos comentarios");
    System.out.println("8. Salir del programa");
  }
  public static void ingresarProgramas(Programa[] programas){
    Scanner stdin=new Scanner (System.in);


    int opcion=0;

    salirDeLaFuncion:
    for (int i=0;i<programas.length;i++){
        System.out.println("Ingresar la informacion de:\n 1. Programa de Nutricion\n 2. Tratamiento estetico\n 3. Volver al menu principal");
        opcion=asignarInt();
        switch (opcion){
          case 1:
            programas[i]=new Nutricion();
            pedirInfo(programas, i  , opcion);
            break;
          case 2:
            programas[i]=new Estetica();
            pedirInfo(programas, i , opcion);
            break;
          case 3:
            break salirDeLaFuncion;
          default:
            System.out.print("Opcion no es valida, ingrese nuevamente: ");
            break;
      }
    }
  }
  public static void pedirInfo(Programa[] programa,int indice, int opcion){
	  Scanner stdin=new Scanner (System.in);
	  System.out.print("\nIngresar el nombre del programa: ");
	  programa[indice].setNombre(stdin.nextLine());

	  System.out.print("\nIngresar numero de identificacion del programa: ");
	  programa[indice].setNumero(asignarInt());



	  if (opcion==1){
		  System.out.print("Ingresar las calorias maximas: ");
		  ((Nutricion)programa[indice]).setMaxCalorias(asignarDouble());

		  System.out.print("\nIngresar las comidas permitidas por este programa: ");
		  for (int i=0;i<((Nutricion)programa[indice]).getComidas().length;i++){
			  ((Nutricion)programa[indice]).setComidas(i, stdin.nextLine());
		  }
		  System.out.print("Ingresar el valor del programa: ");
		  programa[indice].setPrecio(programa[indice].calcularPrecio(asignarDouble()));
	  }else{
		  System.out.print("\nIngresar cantidad de sesiones: ");
		  ((Estetica)programa[indice]).setCantSesiones(asignarInt());
		  System.out.print("Ingresar el valor de cada sesion del programa: ");
      programa[indice].setPrecio(programa[indice].calcularPrecio(asignarDouble()));

	  }
  }
  public static void ingresarHistoriasClinicas(Programa[]programas, HistoriaClinica[]historias){
    Scanner stdin=new Scanner (System.in);

    for (int i=0;i<historias.length;i++){
      historias[i]=new HistoriaClinica();
      System.out.println("Ingresar nombre y apellido del " +(i+1) +" paciente: ");
      historias[i].setNya(stdin.nextLine());

      System.out.println("Ingresar DNI del paciente: ");
      historias[i].setDni(asignarInt());

      System.out.println("Ingresar sexo (MASCULINO O FEMENINO) del paciente: ");
      historias[i].setSexo(asignarSexo());

      System.out.println("Ingresar telefono de contacto del paciente: ");
      historias[i].setTelefono(asignarInt());

      System.out.println("Ingresar fecha de nacimiento del paciente");
      historias[i].setNac(asignarFecha());

      if (historias[i].esMenor()){
        System.out.println("No se permite menores de edad\nSaliendo del programa...");
        System.exit(0);
      }

      System.out.println("Ingresar si tiene obra social o no (1. si 2. no) ");
      salir:
      while (true){
        int tiene=asignarInt();
        switch (tiene){
          case 1:
            historias[i].setObraSocial(true);
            break salir;
          case 2:
            historias[i].setObraSocial(false);
            break salir;
          default:
            System.out.println("VOLVER A INGRESAR OPCION");
            break;
        }
      }

      programasToHistorias(historias, programas, i);

      insertarVisitas(historias, i);

      saldoAPagar(historias, i);

    }
  }
  public static void saldoAPagar(HistoriaClinica[]historias, int indice){
    double plataAgastar=0;
    Scanner stdin=new Scanner (System.in);
    for (int i=0;i<historias[indice].getProgramas().length;i++){
      plataAgastar=plataAgastar+historias[indice].getProgramas()[i].getPrecio();
    }

    System.out.println("Paga en efectivo? (1.si, 2.no): ");
    salirOpcion:
    while (true){
      int efectivo=asignarInt();
      switch (efectivo){
        case 1:
          double excedio=(plataAgastar*((double)(historias[indice].getProgramas()[0].descuento)));
          if (excedio>=(historias[indice].getProgramas()[0].tope)){
            System.out.println("La plata a pagar es: " +(plataAgastar-historias[indice].getProgramas()[0].descuento));
          }else{
            System.out.println("La plata a pagar es: " +(plataAgastar-excedio));
          }
          break salirOpcion;
        case 2:
          System.out.println("La plata a pagar es: " +plataAgastar);
          break salirOpcion;
        default:
          System.out.println("OPCION INVALIDA, VUELVA A INGRESAR");
          break;
      }
    }




  }
  public static HistoriaClinica[] programasToHistorias(HistoriaClinica[]historias, Programa[] programas, int indice){
    Scanner stdin=new Scanner (System.in);
    int valido=-1;
    System.out.println("Ingrese el/los programa/s el paciente tiene asignado");
    System.out.println("Los programas validos son los siguientes: ");
    for (int i=0;i<programas.length;i++){
      System.out.println(programas[i].getNombre());
    }

    for (int i=0;i<historias[indice].getProgramas().length;i++){
      String antesDeAsignar=stdin.nextLine();
      do{
        valido= coincidir(antesDeAsignar, programas);
        if (valido<0){
          System.out.println("PROGRAMA NO CORRESPONDE A NUESTRA BASE DE DATOS, INGRESAR NUEVAMENTE");
          antesDeAsignar=stdin.nextLine();
          valido=coincidir(antesDeAsignar, programas);
        }
      }while (valido<0);
      if (valido>=0){
        Programa [] existente=new Programa[historias[indice].getProgramas().length];
        existente[i]=programas[valido];
        historias[indice].setProgramas(existente);
      }
    }
    return historias;
  }
  public static int coincidir(String antesDeAsignar, Programa[] programas){
    int valido=-1;
    for (int i=0;i<programas.length;i++){
      if (antesDeAsignar.equalsIgnoreCase(programas[i].getNombre())){
        valido=i;
        return valido;
      }
    }
    return valido;
  }
  public static void insertarVisitas(HistoriaClinica[] historias, int indice){

    for (int i=0;i<historias[indice].getVisitas().length;i++){
      Scanner stdin=new Scanner (System.in);
      System.out.println("Ingrese el id correspondiente a la visita n" +(i+1) +": ");
      int id=asignarInt();

      System.out.println("Ingrese la fecha: ");
      Calendar fecha=Calendar.getInstance();
      fecha=asignarFecha();

      System.out.println("Ingrese el nombre del profesional: ");
      String profesional=stdin.nextLine();

      System.out.println("Ingrese los comentarios del profesional: ");
      String comentarios=stdin.nextLine();

      historias[indice].setVisitas(i, id, fecha, profesional, comentarios);
    }

  }
  public static void mostrarPaciente(HistoriaClinica[] historias){
    System.out.println("Ingrese nombre y apellido del paciente que desea buscar: ");
    int buscar=buscarPaciente(historias);
    if (buscar>=0){
      int anio=calcularEdad(historias[buscar].getNac());
      System.out.println("La edad del paciente es: "+anio);
      System.out.println("Ingrese la opcion que quiera ejecutrar\n1. Modificar Obra Social y telefono de Contacto\n2. Volver al menu principal");
      salirOpcion:
      while (true){
        int opcion=asignarInt();
        switch (opcion){
          case 1:
            modificarDatos(historias, buscar);
            break;
          case 2:
            break salirOpcion;
          default:
            System.out.println("OPCION INVALIDA, VUELVA A INGRESAR");
            break;
        }
      }
    }else{
      System.out.println("PACIENTE NO ENCONTRADO");
    }
  }
  public static int buscarPaciente(HistoriaClinica[] historias){
      int encontrado=-1;
      Scanner stdin=new Scanner (System.in);
      String busqueda=stdin.nextLine();
      for (int i=0;i<historias.length;i++){
        if (busqueda.equalsIgnoreCase(historias[i].getNya())){
          encontrado=i;
          return encontrado;
        }
      }
      return encontrado;
    }
  public static int calcularEdad(Calendar fecha) {
      Calendar fechaActual = Calendar.getInstance();
      int years = fechaActual.get(Calendar.YEAR) - fecha.get(Calendar.YEAR);
      int months = fechaActual.get(Calendar.MONTH)+1 - fecha.get(Calendar.MONTH)+1;
      int days = fechaActual.get(Calendar.DAY_OF_MONTH) - fecha.get(Calendar.DAY_OF_MONTH);

      if(months < 0 || (months==0 && days < 0)) {
        years--;
      }
      return years;
    }
  public static void modificarDatos(HistoriaClinica[]historias, int indice){
      System.out.println("Ingresar si tiene obra social o no (1. si 2. no) ");
      salir:
      while (true){
        int tiene=asignarInt();
        switch (tiene){
          case 1:
            historias[indice].setObraSocial(true);
            break salir;
          case 2:
            historias[indice].setObraSocial(false);
            break salir;
          default:
            System.out.println("VOLVER A INGRESAR OPCION");
            break;
        }
      }
      System.out.println("Nuevo telefono de contacto: ");
      historias[indice].setTelefono(asignarInt());
    }

    public static char asignarSexo(){
      String masculino="MASCULINO";
      String femenino="FEMENINO";
      Scanner stdin= new Scanner (System.in);
      String ingreso;
      do {
      ingreso = stdin.nextLine();
      if ((ingreso.equalsIgnoreCase(masculino))==false && (ingreso.equalsIgnoreCase(femenino))==false){
          System.out.println("Ingrese correctamente el sexo del paciente");
      }
      }while ((ingreso.equalsIgnoreCase(masculino))==false && (ingreso.equalsIgnoreCase(femenino))==false);
        if (ingreso.equalsIgnoreCase(masculino)){
          return 'm';
        }else{
          return 'f';
        }
    }



  public static int asignarInt(){
    Scanner stdin= new Scanner(System.in);
    int numero=-1;

    while ((stdin.hasNextInt()==false)||((numero=stdin.nextInt())<0)){
      System.out.print("Ingrese nuevamente el numero: ");
      stdin.nextLine();
    }
    return numero;
  }


  public static double asignarDouble(){
    Scanner stdin= new Scanner(System.in);
    double numero=-1;

    while ((stdin.hasNextDouble()==false)||((numero=stdin.nextDouble())<0)){
      System.out.print("Ingrese nuevamente el numero: ");
      stdin.nextLine();
    }
    stdin.nextLine();
    return numero;
  }

  public static Calendar asignarFecha(){
    Calendar fecha=Calendar.getInstance();

    System.out.print("\nDia: ");
    int dia=asignarInt();
    while (dia>31){
      System.out.print("Dia invalido, ingresar nuevamente: ");
      dia=asignarInt();
    }
    System.out.print("\nMes: ");
    int mes=asignarInt()-1;
    while (mes>11){
      System.out.print("Mes invalido, ingresar nuevamente: ");
      mes=asignarInt()-1;
    }
    System.out.print("\nAño: ");
    int anio=asignarInt();

    while (anio>fecha.get(Calendar.YEAR)){
      System.out.print("Año invalido: ");
      anio=asignarInt();
    }

    fecha.set(Calendar.DAY_OF_MONTH, dia);
    fecha.set(Calendar.MONTH, mes);
    fecha.set(Calendar.YEAR, anio);
    return fecha;
  }
  public static void mostrarCantSinObraSocial(HistoriaClinica[] historias){
    Calendar fechaActual=Calendar.getInstance();
    int cantidad=0;
    //  verifico que por cada paciente sin obra social, alguna de sus visitas hayan sido en el ultimo mes para poder contabilizar

    for (int i=0;i<historias.length;i++){
      if (historias[i].getObraSocial()==false){
        for (int j=0;j<historias[i].getVisitas().length;j++){
          int years= fechaActual.get(Calendar.YEAR) - historias[i].getVisitas()[j].getFechaVisita().get(Calendar.YEAR);
          int months = fechaActual.get(Calendar.MONTH) - historias[i].getVisitas()[j].getFechaVisita().get(Calendar.MONTH);
          if (years==0 && months==0){
            cantidad++;
          }
        }
      }
    }
    System.out.println("La cantidad de pacientes sin obra social ingresados en el ultimo mes son: \n" +cantidad);
  }

  public static void mostrarPacientesDeProgramas(Programa[] programas, HistoriaClinica[] historias){
    Scanner stdin=new Scanner(System.in);
    System.out.println("Ingresar 2 letras correspondientes a un programa para ver los pacientes: ");
    String letras=stdin.nextLine();
    String nombreDelPrograma=null;
    boolean formaParte=false;
    Calendar fecha= Calendar.getInstance();
    while (letras.length()!=2){
      System.out.println("Ha ingresado una incorrecta cantidad de letras, recuerde que deben ser 2, vuelva a ingresar: ");
      letras=stdin.nextLine();
    }

    for (int i=0; i<programas.length;i++){
      formaParte=false;
      for (int j=0;j<programas[i].getNombre().length();j++){
        if (letras.charAt(j)==programas[i].getNombre().charAt(j)){
          formaParte=true;
          nombreDelPrograma=programas[i].getNombre();
        }
      }
    }

    if (formaParte){
      formaParte=false;
      for (int i=0; i< historias.length;i++){
        for (int j=0; j<historias[i].getProgramas().length;j++){
          if (nombreDelPrograma.equalsIgnoreCase(historias[i].getProgramas()[j].getNombre())){
            formaParte=true;
          }
        }

        if (formaParte){
          System.out.println("El nombre del paciente que corresponde a este programa es: " +historias[i].getNya() +"\nSu DNI es: "+historias[i].getDni() +"\nY sus visitas fueron: ");

          for (int j=0;j<historias[i].getVisitas().length;j++){
            System.out.println(historias[i].getVisitas()[j].getFechaVisita().get(Calendar.DAY_OF_MONTH) +"/" +historias[i].getVisitas()[j].getFechaVisita().get(Calendar.MONTH) +"/" +historias[i].getVisitas()[j].getFechaVisita().get(Calendar.YEAR));
          }
        }

      }
    }else {
      System.out.println("No existe ningun programa que empiece con las letras ingresadas");
    }


  }


















}
