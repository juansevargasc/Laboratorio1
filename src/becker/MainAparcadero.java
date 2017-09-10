/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package becker;

import becker.robots.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author juanvargas
 */
public class MainAparcadero
{
    public static void turnRight(Robot r)
    {
        for(int c = 0; c < 3; c++)
        {
            r.turnLeft();
        }
    }
    
    
    public static void main(String args[])
    {
        Scanner input = new Scanner(System.in);
        //Construcción de la ciudad
        City berlin = new City();
        
        //Construcción de aparcadero
        //Zona 1    
        //Wall(City, aStreet, anAvenue, direction);
        Wall z0 = new Wall(berlin, 1, 1, Direction.NORTH);
        Wall z1 = new Wall(berlin, 1, 1, Direction.WEST);
        Wall z2 = new Wall(berlin, 2, 1, Direction.WEST);
        Wall z3 = new Wall(berlin, 3, 1, Direction.WEST);
        Wall z4 = new Wall(berlin, 4, 1, Direction.WEST);
        Wall z5 = new Wall(berlin, 5, 1, Direction.WEST);
        Wall z6 = new Wall(berlin, 5, 1, Direction.EAST);
        Wall z7 = new Wall(berlin, 4, 1, Direction.EAST);
        Wall z8 = new Wall(berlin, 3, 1, Direction.EAST);
        Wall z9 = new Wall(berlin, 2, 1, Direction.EAST);
        Wall z10 = new Wall(berlin, 1, 1, Direction.EAST);
        
        //Zona 2
        Wall z11 = new Wall(berlin, 1, 2, Direction.NORTH);
        Wall z12 = new Wall(berlin, 1, 2, Direction.WEST);
        Wall z13 = new Wall(berlin, 2, 2, Direction.WEST);
        Wall z14 = new Wall(berlin, 3, 2, Direction.WEST);
        Wall z15 = new Wall(berlin, 4, 2, Direction.WEST);
        Wall z16 = new Wall(berlin, 5, 2, Direction.WEST);
        Wall z17 = new Wall(berlin, 5, 2, Direction.EAST);
        Wall z18 = new Wall(berlin, 4, 2, Direction.EAST);
        Wall z19 = new Wall(berlin, 3, 2, Direction.EAST);
        Wall z20 = new Wall(berlin, 2, 2, Direction.EAST);
        Wall z21 = new Wall(berlin, 1, 2, Direction.EAST);
        
        //Zona 3
        Wall z22 = new Wall(berlin, 1, 3, Direction.NORTH);
        Wall z23 = new Wall(berlin, 1, 3, Direction.WEST);
        Wall z24 = new Wall(berlin, 2, 3, Direction.WEST);
        Wall z25 = new Wall(berlin, 3, 3, Direction.WEST);
        Wall z26 = new Wall(berlin, 4, 3, Direction.WEST);
        Wall z27 = new Wall(berlin, 5, 3, Direction.WEST);
        Wall z28 = new Wall(berlin, 5, 3, Direction.EAST);
        Wall z29 = new Wall(berlin, 4, 3, Direction.EAST);
        Wall z30 = new Wall(berlin, 3, 3, Direction.EAST);
        Wall z31 = new Wall(berlin, 2, 3, Direction.EAST);
        Wall z32 = new Wall(berlin, 1, 3, Direction.EAST);
        
        //Zona temporal
        Wall zt0 = new Wall(berlin, 4, 5, Direction.WEST);
        Wall zt1 = new Wall(berlin, 4, 5, Direction.NORTH);
        Wall zt11 = new Wall(berlin, 4, 5, Direction.EAST);
        Wall zt2 = new Wall(berlin, 4, 6, Direction.NORTH);
        Wall zt21 = new Wall(berlin, 4, 6, Direction.EAST);
        Wall zt3 = new Wall(berlin, 4, 7, Direction.NORTH);
        Wall zt31 = new Wall(berlin, 4, 7, Direction.EAST);
        Wall zt4 = new Wall(berlin, 4, 8, Direction.NORTH);
        Wall zt5 = new Wall(berlin, 4, 8, Direction.EAST);
        
        //Entrada
        Wall ent1 = new Wall(berlin, 6, 9, Direction.NORTH);
        Wall ent2 = new Wall(berlin, 6, 9, Direction.SOUTH);
        
        //Via
        Wall v1 = new Wall(berlin, 6, 1, Direction.SOUTH);
        Wall v2 = new Wall(berlin, 6, 2, Direction.SOUTH);
        Wall v3 = new Wall(berlin, 6, 3, Direction.SOUTH);
        Wall v4 = new Wall(berlin, 6, 4, Direction.SOUTH);
        Wall v5 = new Wall(berlin, 6, 5, Direction.SOUTH);
        Wall v6 = new Wall(berlin, 6, 6, Direction.SOUTH);
        Wall v7 = new Wall(berlin, 6, 7, Direction.SOUTH);
        Wall v8 = new Wall(berlin, 6, 8, Direction.SOUTH);
        
        //Complementary walls
        Wall wall1 = new Wall(berlin, 5, 4, Direction.NORTH);
        Wall wall2 = new Wall(berlin, 5, 8, Direction.EAST);
        
        //Creación de robot
        Robot parkBot = new Robot(berlin, 6, 9, Direction.WEST);
        
        //Creación de aparcadero
        Aparcadero parqueadero = new Aparcadero();
        
        //Creacion de autos
        Auto[] autos = new Auto[15];
        
        Thing a1 = new Thing(berlin, 6, 10);
        Auto auto1 = new Auto(a1, "UCZ111");
        autos[0] = auto1;
        
        Thing a2 = new Thing(berlin, 6, 11);
        Auto auto2 = new Auto(a2, "BHX232");
        autos[1] = auto2;
        
        //Creacion de autos por parte del usuario
        for(int i = 2; i < 4; i++)
        {
            System.out.print("Ingrese placa: ");
            Thing a = new Thing(berlin, 6, (10 + i) );
            String placa = input.next();
            Auto auto = new Auto(a, placa);
            autos[i] = auto;
            
        }
        
        
        //Ingreso de vehículo
        parkBot.turnLeft();
        parkBot.turnLeft();
        parkBot.move(); // Posisción: 6, 10
        parkBot.pickThing();
        parkBot.turnLeft();
        parkBot.turnLeft();
        parkBot.move(); // Posición 6, 9
        //Hora de Ingreso de Vehículo
        System.out.print("Ingrese hora de entrada: " );
        int horaEntrada = input.nextInt();
        auto1.setHoraEntrada(horaEntrada);
        parqueadero.ingresarVehiculo(parkBot, auto1);
        
        //Salida de vehículo
        System.out.print("Salida de vehículo. Ingrese placa: " );
        String placa = input.next();
        System.out.print("Ingrese hora de salida: ");
        int horaSalida = input.nextInt();
        parqueadero.sacarVehiculo(parkBot, 1, 1, placa);
        parkBot.move();
        parkBot.putThing();
        
        int duracionEnMin = parqueadero.tiempoEnMinutos(horaEntrada, horaSalida);
        double cobro = parqueadero.cobroMinutos(duracionEnMin, 67); //Tarifa de $67
        parqueadero.setIngresosJornada( parqueadero.getIngresosJornada() + cobro ); //Actualización de ingresos
        System.out.print("Duración: " + duracionEnMin + "\nCobro: " + cobro + "\n");
        
        //Buscar autos retornando las placas en una zona
        String[] placasAutos = new String[5];
        System.out.print("Ingrese la zona: ");
        int zona = input.nextInt();
        placasAutos = parqueadero.mostrarVehiculosZona(zona);
        
        
        //System.out.println("Things in backpack: " + parkBot.countThingsInBackpack());
        
        

        //Ingresos obtenidos en jornada
        System.out.println("Ingresos obtenidos en jornada: " + parqueadero.getIngresosJornada());
   
    }
    
}
