package controller;

import java.util.Iterator;
import java.util.Scanner;

import model.logic.Comparendo;
import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}

	/**
	 * Metodo run que corre el sistema
	 */
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;

		while( !fin )
		{
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
			case 1:
				int tamanio = modelo.darTamanoComparendos();
				view.printMessage("El total de comparendos es de: " + tamanio);
				break;

			case 2:
				view.printMessage("El comparendo con el mayor OBJECTID es: " + modelo.darObjectidMayor() + " \n");
				break;

			case 3:
				view.printMessage("La zona MiniMax de los comparendos dentro del siguiente rango " + modelo.darZonaMiniMax()[0] + " a " + modelo.darZonaMiniMax()[1] + " y " + modelo.darZonaMiniMax()[2] + " a " + modelo.darZonaMiniMax()[3] + " es: \n");
				Iterator<Comparendo> resultado1 = modelo.darComparendosZonaMinimax(modelo.darZonaMiniMax()[0], modelo.darZonaMiniMax()[2],modelo.darZonaMiniMax()[1], modelo.darZonaMiniMax()[3]).iterator();
				while(resultado1.hasNext())
				{
					Comparendo elemento = resultado1.next();
					view.printMessage(elemento.getInfraccion() + ", " + elemento.getObjective() + ", " + elemento.getFecha_hora() + ", " + elemento.getClase_vehi() + ", " + elemento.getTipo_servi() + ", " + elemento.getLocalidad());
				}
				break;

			case 4:
				view.printMessage("Por favor ingresar la localidad para ser buscada en el archivo (En caso de ser Barrios Unidos ingresar BARRIOS)");
				String entrada1 = lector.next();
				if(entrada1.startsWith("BARRIOS"))
				{
					entrada1 = "BARRIOS UNIDOS";
				}
				view.printMessage("El primer comparendo en el archivo con la localidad de " + entrada1 + ":");
				view.printMessage(modelo.darPrimerComparendoLocalidad(entrada1) + "\n");
				break;
				
			case 5:
				view.printMessage("Por favor ingresar la infraccion para ser buscada en el archivo");
				String entrada2 = lector.next();
				view.printMessage("El primer comparendo en el archivo con la localidad de " + entrada2 + ":");
				view.printMessage(modelo.darPrimerComparendoInfraccion(entrada2) + "\n");
				break;
				
			case 6:
				view.printMessage("Hasta pronto"); 
				lector.close();
				fin = true;
				break;

			default: 
				view.printMessage("Opcion Invalida !!");
				break;
			}	
		}
	}	
}
