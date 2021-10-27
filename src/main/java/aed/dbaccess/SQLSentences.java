package aed.dbaccess;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SQLSentences {
	
	public static void Show(){
		Connect conexion = new Connect();
		
		try	{
			String sentencia = "select codEquipo, nomEquipo, nomLiga, localidad, internacional from equipos join ligas on equipos.codLiga = ligas.codLiga order by codEquipo";
			PreparedStatement select = conexion.Conectar(DBAccessApp.connection).prepareStatement(sentencia);
			ResultSet resultado = select.executeQuery();
			
			while(resultado.next())
			{
				System.out.println(resultado.getInt(1) + " | " + resultado.getString(2) + " | " + resultado.getString(3) + " | " + resultado.getString(4) + " | " + resultado.getInt(5));
			}
			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		finally	{
			
			conexion.Desconectar();
	
		}
	}
	
	public static void Insert()
	{
		Connect conexion = new Connect();
		Scanner scanner = new Scanner(System.in);
		
		try{
			List<String>codigos = new ArrayList<String>();
			boolean existe = false;
			
			String sentencia = "select * from equipos order by codEquipo";
			PreparedStatement select = conexion.Conectar(DBAccessApp.connection).prepareStatement(sentencia);
			ResultSet resultado = select.executeQuery();
	
			while(resultado.next())
			{
				codigos.add(resultado.getString(3));
			}
			
			System.out.println("Nombre del equipo:");
			String nombre = scanner.nextLine();
			
			System.out.println("Localidad del equipo:");
			String localidad = scanner.nextLine();		
			
			System.out.println("Código de liga:");
			String liga = scanner.nextLine();
			
			for(int i = 0; i < codigos.size(); i++)	{
				if(liga.equals(codigos.get(i))){
					existe = true;
				}
			}
			
			if(!existe)	{
				System.out.println("El código de liga introducido no existe.");
			}
			else{
				System.out.println("Internacional: (1 o 0):");
				int internacional = scanner.nextInt();
				
				if(internacional != 1 && internacional != 0){
					System.out.println("Internacional solo puede ser 1 o 0\n");
				}
				else{
					sentencia = "insert into equipos (nomEquipo, codLiga, localidad, internacional) values (?, ?, ?, ?)";
					PreparedStatement insert = conexion.Conectar(DBAccessApp.connection).prepareStatement(sentencia);
					insert.setString(1, nombre);
					insert.setString(2, liga);
					insert.setString(3, localidad);
					insert.setInt(4, internacional);
					insert.execute();
					System.out.println("Equipo insertado con éxito.");
				}
			}
			
		}
		catch(SQLException e){
			System.out.println("error con los datos.");
		}
		catch(InputMismatchException e)	{
			System.out.println("Escriba un número");
		}
		finally	{
			scanner.close();
			conexion.Desconectar();
	
		}
		
	}
	
	public static void Modify(){
		Connect conexion = new Connect();
		Scanner scanner = new Scanner(System.in);
		
		try	{
			List<String>codigoLiga = new ArrayList<String>();
			List<Integer>codigoEquipo = new ArrayList<Integer>();
			boolean ligaExiste = false;
			boolean equipoExiste = false;
			String nombre = null;
			String localidad = null;
			String liga = null;
			
			String sentencia = "select * from equipos order by codEquipo";
			PreparedStatement select = conexion.Conectar(DBAccessApp.connection).prepareStatement(sentencia);
			ResultSet resultado = select.executeQuery();
	
			while(resultado.next()){
				System.out.println(resultado.getInt(1) + " | " + resultado.getString(2) + " | " + resultado.getString(3) + " | " + resultado.getString(4) + " | " + resultado.getInt(5));
				codigoEquipo.add(resultado.getInt(1));
				codigoLiga.add(resultado.getString(3));
			}
			
			System.out.println("\n");
			System.out.println("Escriba el número del equipo a modificar:");
			int codigo = scanner.nextInt();
			
			for(int i = 0; i < codigoEquipo.size(); i++){
				if(codigo == codigoEquipo.get(i)){			
					equipoExiste = true;
				}
			}
			
			if(equipoExiste){
				System.out.println("Nuevo nombre del equipo:");
				nombre = scanner.nextLine();
				
				System.out.println("Nueva localidad del equipo:");
				localidad = scanner.nextLine();	
				
				System.out.println("Nuevo código de liga:");
				liga = scanner.nextLine();
				
				for(int i = 0; i < codigoLiga.size(); i++)
				{
					if(liga.equals(codigoLiga.get(i)))
					{
						ligaExiste = true;
					}
				}
				
				if(!ligaExiste){
					System.out.println("El código de liga introducido no existe.");
				}
				else{
					System.out.println("Internacional: (1 o 0):");
					int internacional = scanner.nextInt();
					
					if(internacional != 1 && internacional != 0){
						System.out.println("Internacional solo puede ser 1 o 0.");
					}
					else{
						sentencia = "update equipos set nomEquipo = ?, codLiga = ?, localidad = ?, internacional = ? where codEquipo = ?";
						PreparedStatement insert = conexion.Conectar(DBAccessApp.connection).prepareStatement(sentencia);
						insert.setString(1, nombre);
						insert.setString(2, liga);
						insert.setString(3, localidad);
						insert.setInt(4, internacional);
						insert.setInt(5, codigo);
						insert.execute();
						System.out.println("Equipo modificado con éxito.");
					}
				}
			}
			else{
				System.out.println("No existe");
			}
			
		}
		catch(SQLException e){
			System.out.println("error con los datos.");
		}
		catch(InputMismatchException e)	{
			System.out.println("Escriba un número");
		}
		finally	{
			scanner.close();
			conexion.Desconectar();
		
		}
	}
	
	public static void Delete()	{
		Connect conexion = new Connect();
		Scanner scanner = new Scanner(System.in);
		
		try	{
			String sentencia = "select * from equipos order by codEquipo";
			PreparedStatement select = conexion.Conectar(DBAccessApp.connection).prepareStatement(sentencia);
			ResultSet resultado = select.executeQuery();
			
			List<Integer>codigos = new ArrayList<Integer>();
			boolean exist = false;
			
			while(resultado.next())	{
				System.out.println(resultado.getInt(1) + " | " + resultado.getString(2) + " | " + resultado.getString(3) + " | " + resultado.getString(4) + " | " + resultado.getInt(5));
				codigos.add(resultado.getInt(1));
			}
			
			System.out.println("\n");
			System.out.println("Escriba el número del equipo a borrar:");
			
			int equipo = scanner.nextInt();
			
			for(int i = 0; i < codigos.size(); i++)	{
				if(equipo == codigos.get(i)) {
					exist = true;
					PreparedStatement delete = conexion.Conectar(DBAccessApp.connection).prepareStatement("delete from equipos where codEquipo = ?");
					delete.setInt(1, equipo);
					delete.execute();
					System.out.println("Equipo eliminado.");
				}
			}
			
			if(!exist){
				System.out.println("No existe");
			}
			
		}
		catch(SQLException e){
			System.out.println("No se puede eliminar");
		}
		catch(InputMismatchException e)	{
			System.out.println("Escriba un número");
		}
		finally	{
			scanner.close();
			conexion.Desconectar();
			
		}
		
	}
}