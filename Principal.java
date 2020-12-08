/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author _nati
 */
public class Principal {
    public static void main(String[] args) throws InterruptedException {
        Estacionamiento estacionamiento = new Estacionamiento();
        estacionamiento.inicializarEstacionamiento();
        for (int i = 0; i < 500; i++) {// secrean 500 vehiculos y se inician entre 1 y 10 ms
            Vehiculo vehiculo = new Vehiculo(estacionamiento,i);
            vehiculo.start();
            Thread.sleep(10);
        }
    } 
}
