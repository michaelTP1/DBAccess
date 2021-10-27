package aed.dbaccess;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DBAccessApp {
	static String connection;
	static boolean end=false;

	public static void main(String[] args) {
		
do {
		

		Scanner scanner = new Scanner(System.in);
		boolean salir = false;
		int option;

		do {
			System.out.println("\n");
			System.out.println("Elija una opción");
			System.out.println("1. MySQL");
			System.out.println("2. SQLServer");
			System.out.println("3. Access");
			System.out.println("0. Salir\n");
			

			try {
				option = scanner.nextInt();

				switch (option) {
				case 1:
					connection="mysql";
					salir = true;
					break;
				case 2:
					connection="sqlserver";
					salir = true;
					break;
				case 3:
					connection="access";
					salir = true;
					break;
				case 0:
					connection="Exit";
					salir = true;
					end=true;
					scanner.close();
					break;
				
				}
			} catch (InputMismatchException e) {
				System.out.println("Debe escribir un número\n");
				scanner.next();
			}

		} while (!salir);
		
		boolean exit = false;
		int opcion;
		if(!end)
		do {
			
			System.out.println("Elija una opción:");
			System.out.println("1. Mostrar Equipos");
			System.out.println("2. Añadir Equipos");
			System.out.println("3. Modificar Equipos");
			System.out.println("4. Borrar Equipos");
			
		

			try {
				opcion = scanner.nextInt();

				switch (opcion) {
				case 1:
					SQLSentences.Show();
					exit = true;
					break;
				case 2:
					SQLSentences.Insert();
					exit = true;
					break;
				case 3:
					SQLSentences.Modify();
					exit = true;
					break;
				case 4:
					SQLSentences.Delete();
					exit = true;
					break;
				case 0:
					exit = true;
					
					break;
				
				}
			} catch (InputMismatchException e) {
				System.out.println("Introduzca un número");
				scanner.next();
			}

		} while (!exit);
		
		
}while(!end);
		
	}

}
