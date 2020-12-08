/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author _nati
 */
public class Vehiculo extends Thread{
    int idVehiculo;
    Estacionamiento estacionamiento;

    public Vehiculo(Estacionamiento estacionamiento, int idVehiculo) {
        this.estacionamiento = estacionamiento;
        this.idVehiculo=idVehiculo;
    }
    
    
    @Override
    public void run(){
        
        try {
            estacionamiento.verificarEstacionamiento(idVehiculo);//verifica si hay lugares disponibles
            
            estacionamiento.PermitirEntrar(idVehiculo);//Cola de vehiculos entrando si la puerta esta ocupada por vehiculos saliendo
            estacionamiento.entrando(idVehiculo); //
            
            //System.out.println(""+estacionamiento.totalDeVehiculosEntrando);
            //System.out.println("++Vehiculo"+idVehiculo+" entrando"+estacionamiento.totalDeEspaciosOcupados);//+estacionamiento.totalDeVehiculosEntrando

            estacionamiento.estacionandose(idVehiculo);
            estacionamiento.despertandose(idVehiculo);
            
            estacionamiento.permitirSalir(idVehiculo);
            estacionamiento.saliendo(idVehiculo);
            //System.out.println("********************vehiculo"+idVehiculo+ " saliendo"+estacionamiento.totalDeEspaciosOcupados);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Vehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
