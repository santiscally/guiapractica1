#!/bin/bash

rm -r -f classfiles/ 

mkdir classfiles/

sudo javac -d classfiles/ Estetica.java Main.java Nutricion.java Visita.java HistoriaClinica.java Medicamento.java Programa.java ICalcular.java

cd classfiles/

sudo java Main
