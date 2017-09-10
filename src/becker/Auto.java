/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package becker;

import becker.robots.*;

/**
 *
 * @author juanvargas
 */
public class Auto
{
    private Thing a;
    private String placa;
    private int horaEntrada; //horaEntrada y horaSalida escrita como los primeros dos dígitos minutos y los últimos dos horas
    private int horaSalida; 
    private int[] ubicacion;
    
    public Auto() 
    {
        
    }

    public Auto(Thing a, String placa) 
    {
        this.a = a;
        this.placa = placa;
        this.horaEntrada = 0;
        this.horaSalida = 0;
        this.ubicacion = new int[2];
        
    }

    public Thing getA() 
    {
        return a;
    }

    public String getPlaca() 
    {
        return placa;
    }

    public int getHoraEntrada() 
    {
        return horaEntrada;
    }

    public int getHoraSalida() 
    {
        return horaSalida;
    }

    public int[] getUbicacion()
    {
        return ubicacion;
    }
    
    
    

    public void setA(Thing a)
    {
        this.a = a;
    }

    public void setPlaca(String placa) 
    {
        this.placa = placa;
    }

    public void setHoraEntrada(int horaEntrada) 
    {
        this.horaEntrada = horaEntrada;
    }

    public void setHoraSalida(int horaSalida)
    {
        this.horaSalida = horaSalida;
    }

    public void setUbicacion(int[] ubicacion)
    {
        this.ubicacion = ubicacion;
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
