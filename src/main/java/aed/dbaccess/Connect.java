package aed.dbaccess;


import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {

	public Connection conexion;
	
	public Connection Conectar(String servidor)
	{
		try
		{
			String url = "";
			String usuario = "";
			String contrasenia = "";
			
			if(servidor.equals("mysql"))
			{
				url = "jdbc:mysql://localhost:3306/bdfutbol";
				usuario = "root";
				contrasenia = "";
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				conexion = DriverManager.getConnection(url, usuario, contrasenia);
			}
			else if(servidor.equals("sqlserver"))
			{
				url = "jdbc:sqlserver://DESKTOP-2IDJVON\\SQLEXPRESS01;DataBaseName=bdFutbol";
				usuario = "sa";
				contrasenia = "michael";
				
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				
				conexion = DriverManager.getConnection(url, usuario, contrasenia);			
			}
			else if(servidor.equals("access"))
			{
				url = "jdbc:ucanaccess://src/main/resources\\BdFutbolAccess.accdb";
				
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				
				conexion = DriverManager.getConnection(url);
			}
			
			return conexion;
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void Desconectar()
	{
		try
		{
			conexion.close();
		}
		catch(Exception e)
		{
			e.getMessage();
		}
	}
	
}
