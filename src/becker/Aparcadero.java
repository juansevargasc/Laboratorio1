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
public class Aparcadero 
{
    private double ingresosJornada;
    private Auto[] autos;
    private int numeroAutos;
    private boolean[][] espacios;
    
    public Aparcadero()
    {
        //Número total de cupo del aparcadero
        this.autos = new Auto[15];
        
        this.numeroAutos = 0;
        
        //Hay 5 espacios por cada zona, y a su vez hay 3 zonas
        this.espacios = new boolean[5][3];
        
        //Inicialización de la matriz
        for(int j = 0; j < 3; j++)
        {
            for(int i = 0; i < 5; i++)
            {
                this.espacios[i][j] = false;
            }
        }
        
        //Inicialización de ingresos en 0
        this.ingresosJornada = 0;
    } 

    public double getIngresosJornada() 
    {
        return ingresosJornada;
    }

    public Auto[] getAutos() 
    {
        return autos;
    }

    public int getNumeroAutos() 
    {
        return numeroAutos;
    }

    public boolean[][] getEspacios() 
    {
        return espacios;
    }
    
    

    public void setIngresosJornada(double ingresosJornada) 
    {
        this.ingresosJornada = ingresosJornada;
    }

    public void setAutos(Auto[] autos) 
    {
        this.autos = autos;
    }

    public void setNumeroAutos(int numeroAutos) 
    {
        this.numeroAutos = numeroAutos;
    }

    public void setEspacios(boolean[][] espacios)
    {
        this.espacios = espacios;
    }
    
    
    
    /**
     * 
     * @param r 
     */
    public void turnRight(Robot r)
    {
        for(int c = 0; c < 3; c++)
        {
            r.turnLeft();
        }
    }
    
    /**
     * 
     * @param placa
     * @return Auto
     */
    public Auto buscarAutoPorPlaca(String placa)
    {
        for(int c = 0; c < ( this.autos.length ); c++)
        {
            if(this.autos[c].getPlaca().equals(placa))
            {
                return this.autos[c];
            }
        }
        return null;
            
    }
    
    //Cobro 
    /**
     * Cobro por minutos dada una tarifa
     * @param minutos
     * @param tarifa
     * @return precio
     */
    public double cobroMinutos(int minutos, double tarifa)
    {
        // Tarifa de 67
        double precio = minutos * tarifa;
        return precio;
    }
    
    /**
     * 
     * @param horaEnt
     * @param horaSal
     * @return duracion
     */
    public int tiempoEnMinutos(int horaEnt, int horaSal)
    {
        //
        int horasEntrada = horaEnt/100;
        System.out.println( "horas entrada: " + horasEntrada );
        int minutosEntrada = (int)( ( (horaEnt/100.0) - horasEntrada ) * (100));
        System.out.println( "miutos entrada: " + minutosEntrada );
        //
        int horasSalida = horaSal/100;
        System.out.println( "horas salida: " + horasSalida );
        int minutosSalida = (int)((horaSal/100.0) - horasSalida) * (100);
        System.out.println( "minutos salida: " + minutosSalida );
        
        //
        int duracionHoras = horasSalida - horasEntrada;
        System.out.println( "duracion horas:  " + duracionHoras );
        int duracionMinutos = minutosSalida - minutosEntrada;
        System.out.println( "duracion minutos: " + duracionMinutos );
        int duracion = (duracionHoras * 60) + (duracionMinutos);
        System.out.println( "DURACIÓN: " + duracion );
        return duracion;
    }
    
    /**
     * Ingresa el vehículo a un espacio, en la matriz de espacios que pueden estar
     * Ocupados o Desocupados, es decir una matriz de bits
     * @param r
     * @param num
     * @return boolean 
     */
    public boolean cupoDisponible()
    {
        boolean ocupado = true;
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                ocupado = (ocupado) & this.espacios[i][j];
            }
        }
        return !(ocupado);
    }
    
    /**
     * Ingreso de un vehículo por parte de un robot r. Lo asigna a la zona con menor cupo
     * @param r
     * @return boolean
     */
    public boolean ingresarVehiculo(Robot r, Auto auto)
    {
        if( cupoDisponible() )
        {
            //1. Criterio de ingresar el vehículo a la zona con menos autos
            //Revisar en cada zona (columna). Tomar el "i" (número de fila)
            //donde aparezca el primer "false". Se tendrán 3 números de fila
            //Se tomará el menor i.

            //Arreglo de int para los tres números de fila en cada zona con asignación "false"
            int[] z = new int[3];

            //Variable para el número de fila menor en una zona. Se inicializa con el número mayor de fila posible.
            //"zona" es la varible del número de la zona en la que se habría menos autos.
            int filaMenor = 5, zona = 0;

            //Se hace doble ciclo para buscar en la matriz la fila con el primer "false" en cada columna
            //Se guarda en el arreglo z
            for(int j = 0; j < 3; j++)
            {
                z[j] = 1;
                for(int i = 0; i < 5; j++)
                {
                    if(this.espacios[i][j] == false)
                    {
                        z[j] = i + 1;
                        break;
                    }

                }
                //Si el número de la fila es menor que la variable "menor", se actualiza un nuevo mínimo
                if( z[j] < filaMenor )
                {
                    //En estas dos variables se guarda el número del espacio en la matriz del aparcadero 
                    //de manera que se guarde en la zona con menos autos.
                    filaMenor = z[j];
                    zona = j + 1;
                }
            }
            
            
            
            
            //2. Asignación
            //Se asume que el robot se encuentra en dirección occidente (WEST)   
            //En la Calle 6 con Avenida 9
            //Moviemientos del robot
            int avenidas = 9 - (zona), calles = 6 - (filaMenor);
            //Prueba System.out.println("Avenidas a recorrer: " + avenidas + "Calles a recorrer: " + calles);
            for(int k = 0; k < avenidas; k++)
            {
                r.move();
            }
            
            turnRight(r);
            
            for(int k = 0; k < calles; k++)
            {
                r.move();
            }
            
            r.putThing();
            
            //Devolver el robot a su punto de inicio por defecto
            r.turnLeft();
            r.turnLeft();
            
            for(int k = 0; k < calles; k++)
            {
                r.move();
            }
            
            r.turnLeft();
            
            for(int k = 0; k < avenidas; k++)
            {
                r.move();
            }
            r.turnLeft();
            r.turnLeft();
            
            //Actualización de variables
            this.espacios[filaMenor - 1][zona - 1] = true;
            this.autos[this.numeroAutos] = auto;
            this.numeroAutos += 1;
            
            int[] ubicacion = new int[2];
            ubicacion[0] = filaMenor;
            ubicacion[1] = zona;
            auto.setUbicacion(ubicacion);
                    
            return true;
            
        }else
        {
            return false;
        }
    }
    
    /**
     * Función para sacar vehículos. Si hay vehículos obstruyendo se dejan en la zona temporal.
     * No devuelve los vehículos de la zona de temporal a su zona original. No descarga el objeto(auto) sino
     * que lo mantiene en su bolsa. Termina en la posición estandar: Calle 6 con Avenida 9
     * Se remueve el automóvil del arreglo del aparcadero.
     * @param r
     * @param posicion
     * @param zona
     * @param placa
     * @return 
     */
    public boolean sacarVehiculo(Robot r, int posicion, int zona, String placa)
    {
        //Movimientos del robot y orden de tomar el vehículo que esta en ese espacio
        //Se asume la posición de entrada, es decir la posición en la Calle 6 con Avenida 9
        //y en dirección oeste (WEST)
        int avenidas = 9 - (zona), calles = 6 - zona; 
        
        //Ida
        for(int k = 0; k < avenidas; k++)
        {
            r.move();
        }
        turnRight(r);
        
        //Para saber si hay vehículos se pregunta al robot si se puede recoger un objeto
        //Si la posición del automovil está en el 5to espacio se remueve directamente, si hay
        //un auto que remover
        if(5 - (posicion) == 0)
        {
            for(int k = 0; k < calles; k++)
            {
                r.move();
            }
            if(r.canPickThing())
            {
                r.pickThing();
            }else
            {
                return false;
            }

            //Vuelta
            r.turnLeft();
            r.turnLeft();
            for(int k = 0; k < calles; k++)
            {
                r.move();
            }
            r.turnLeft();
            for(int k = 0; k < avenidas; k++)
            {
                r.move();
            }
        }else //Sino está en la quinta, sino en otro espacio se ejecuta otro código para remover autos obstruyendo
        {
            
            for(int i = 0; i < (5 - posicion); i++)
            {
                r.move();
                if(r.canPickThing())
                {
                    r.pickThing();
                    //Code for leaving the thing in temporal zone
                    //Se devuelve y anda (i + 1) veces es decir la cantidad de pasos que avanzó
                    r.turnLeft();
                    r.turnLeft();
                    for(int k = 0; k < ( i + 1 ); k++)
                    {
                        r.move();
                    }
                    r.turnLeft();
                    //La zona temporal va desde la Avenida 5 a la 8 sobre la Calle 4ta
                    //El robot está en la Calle 6 con Avenida (zona) en dirección Este (EAST)
                    //Se recorren ( (i + 5) - zona ) posiciones dado que la primera zona es en la avenida 5
                    //Y  la primera iteración i = 0, corresponde al primer posible vehiculo a remover, menos
                    //el número de zona, da el número a recorrer por el robot sobre la calle.
                    for(int k = 0; k < ( (i + 5) - zona ); k++)
                    {
                        r.move();
                    }
                    r.turnLeft();
                    r.move();
                    r.move();
                    r.putThing(); //Deja el auto en el espacio
                    
                    //Se devuelve a la posición en la que recogió el objeto
                    r.turnLeft();
                    r.turnLeft();
                    r.move();
                    r.move();
                    turnRight(r);
                    for(int k = 0; k < ( (i + 5) - zona ); k++) //Recorre sobre la calle, dirección Oeste (WEST)
                    {
                        r.move();
                    }
                    turnRight(r);
                    for(int k = 0; k < ( i + 1 ); k++)
                    {
                        r.move();
                    }
                    //
                    
                }else
                {
                    //Si no hay nada que remover
                }
            }
            //Una vez en despejado, se mueve una vez el robot una vez para posicionarse en
            //el espacio del parqueadero dado.
            r.move();
            if(r.canPickThing())
            {
                r.pickThing();
            }else
            {
                return false;
            }
            
            //Vuelta
            r.turnLeft();
            r.turnLeft();
            for(int k = 0; k < calles; k++)
            {
                r.move();
            }
            r.turnLeft();
            for(int k = 0; k < avenidas; k++)
            {
                r.move();
            }
            
        }
        //Actualización de variables
        this.espacios[posicion - 1][zona - 1] = false;
        for(int i = 0; i < this.numeroAutos; i++) //Se remueve el automóvil del arreglo del parqueadero
        {
            if(this.autos[i].getPlaca().equals(placa))
            {
                this.autos[i] = null;
            }
        }
        //Return
        return true;
    }
    
    public String[] mostrarVehiculosZona(int zona)
    {
        String[] placasZona = new String[5];
        for(int i = 0; i < this.getNumeroAutos(); i++)
        {
            if(this.autos[i].getUbicacion()[1] == zona)
            {
                
                placasZona[i] = this.autos[i].getPlaca();
            }
        }
        return placasZona;
    }
}       
    
    
    
    
    
        
        
        
        
    
    

