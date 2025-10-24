package dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager { 
	
	private static FileManager gestor;
	
	private ArrayList<Usuario> usuarios;
	

	private FileManager() {
		usuarios = new ArrayList<>();
		loadUsers();
	}
	
	public static FileManager getGestor() {
		if (gestor == null) {
			gestor = new FileManager();
		}
		return gestor;
	}
	
	// Lectura de usuarios 
	
	private void loadUsers() {
		
		File usertxt = new File("usuarios.txt");
		
		try (Scanner usersc = new Scanner(usertxt)) 
		{
			while (usersc.hasNextLine()) {
				String line = usersc.nextLine();
				if (line.isEmpty()) continue;
				
				// Creacion usuarios con Factory
				String[] parts = line.split("\\|");
				if (parts.length==3) {
					String name = parts[0];
					String pss = parts[1];
					String role = parts[2]; 
					usuarios.add(Usuario.userFactory(name, pss, role));   
					} else { System.out.println("Formato incorrecto del txt!!!"); }
				}
		} catch (FileNotFoundException e) { System.out.println("ERROR: Archivo de usuarios no encontrado :c"); }
	
	}
	
	public ArrayList<Usuario> getUsuarios() {
		return usuarios; 
		}
	

}
