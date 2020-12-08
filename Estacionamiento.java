/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author _nati
 */
public class Estacionamiento {

    int totalDeEspaciosOcupados = 0;
    int totalDeVehiculosEntrando = 0;
    int colaDeVehiculosEntrando = 0;
    int colaDeVehicuosSaliendo = 0;
    int[] cajones = new int[100];
    boolean puertaOcupadaPorVehiculoEntrando = false;
    boolean puertaOcupadaPorVehiculoSaliendo = false;


    public synchronized void verificarEstacionamiento(int idVehiculo) throws InterruptedException {

        while (totalDeEspaciosOcupados >99) {
            wait(); 
            //System.out.println(".....................");
        }
        totalDeEspaciosOcupados++;
    }

    public synchronized void PermitirEntrar(int idVehiculo) throws InterruptedException {
        while (puertaOcupadaPorVehiculoSaliendo) {
            wait();
        }
        puertaOcupadaPorVehiculoEntrando = true;
        
        colaDeVehiculosEntrando++;

    }

    public synchronized void entrando(int idVehiculo) {
         colaDeVehiculosEntrando--;
         if (colaDeVehiculosEntrando == 0) {
            puertaOcupadaPorVehiculoEntrando = false;
            notifyAll();
        }
        System.out.println(totalDeVehiculosEntrando+": Vehiculo"+idVehiculo+" entrando"+totalDeEspaciosOcupados);
        totalDeVehiculosEntrando++;
    }

    public synchronized void permitirSalir(int idVehiculo) throws InterruptedException {
        while (puertaOcupadaPorVehiculoEntrando) {
            wait();
        }
        puertaOcupadaPorVehiculoSaliendo = true;
        colaDeVehicuosSaliendo++;
    }

    public synchronized void saliendo(int idVehiculo) {
        colaDeVehicuosSaliendo--;
        if (colaDeVehicuosSaliendo == 0) {
            puertaOcupadaPorVehiculoSaliendo = false;
            notifyAll();
        }       
        System.out.println("********************vehiculo"+idVehiculo+ " saliendo"+totalDeEspaciosOcupados);
    }

    public synchronized void estacionandose(int idVehiculo) throws InterruptedException {
        for (int i = 0; i < cajones.length; i++) {
            if (cajones[i] == -1) {
                cajones[i] = idVehiculo;
                break;
            }
        }
    }

    public void despertandose(int idVehiculo) throws InterruptedException {
        Thread.sleep((int) (Math.random() * 5000) + 1000);
        for (int i = 0; i < cajones.length; i++) {
            if (cajones[i] == idVehiculo) {
                cajones[i] = -1;
                puertaOcupadaPorVehiculoSaliendo = true;
                totalDeEspaciosOcupados--;
                break;
            }
        }
    }

    public void inicializarEstacionamiento() {
        for (int i = 0; i < cajones.length; i++) {
            cajones[i] = -1;
        }
    }
}
