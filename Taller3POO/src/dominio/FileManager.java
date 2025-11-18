package dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager { 
	
	private static FileManager gestor;
	
	private ArrayList<Usuario> usuarios = new ArrayList<>();
	private ArrayList<Proyecto> proyectos = new ArrayList<>();
	public static int contProy = 0;
	public static int conTask = 0;
	

	private FileManager() {
		loadUsers();
		loadProyectos();
		loadTareas();
	}
	


	public static FileManager getGestor() {
		if (gestor == null) {
			gestor = new FileManager();
		}
		return gestor;
	}
	
	// Lectura de usuarios, proyectos y tareas 
	
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
					usuarios.add(UserFactory.crearUser(name, pss, role));   
					} else { System.out.println("Formato incorrecto del txt!!!"); }
				}
		} catch (FileNotFoundException e) { System.out.println("ERROR: Archivo de usuarios no encontrado :c"); }
	
	}
	
	private void loadProyectos() {
		
		File proytxt = new File("proyectos.txt");
		
		try (Scanner proscan = new Scanner(proytxt))
		{
			while (proscan.hasNextLine()) {
				String line = proscan.nextLine();
				if (line.isEmpty()) continue;
				
				String[] parts = line.split("\\|");
				String prID = parts[0];
				String name = parts[1];
				String acargo = null;
				
				for (Usuario u: usuarios) {
					if (u.getName().equals(parts[2])) 
					{
						acargo = u.getName();
					}
				}
				
				proyectos.add(new Proyecto(prID, name, acargo));
				contProy++;
			}
			
		} catch (FileNotFoundException e) { System.out.println("Archivo no encontrado!!"); }
		
		
	}
	
	private void loadTareas() {
		
		File tasktxt = new File("tareas.txt");
		
		try (Scanner sctask = new Scanner(tasktxt))
		{
			while(sctask.hasNextLine()) {
				String line = sctask.nextLine();
				if (line.isEmpty()) continue;
				
				String parts[] = line.split("\\|");
				String idpro = parts[0];
				String idtsk = parts[1];
				String type = parts[2];
				String desc = parts[3];
				String stt = parts[4];
				String byuser = parts[5];
				String complx = parts[6];
				String date = parts[7];
				
				for (Proyecto p : proyectos)
				{
					if (idpro.equals(p.getId())) {
						p.getTareas().add(TareaFactory.crearTarea(idpro, idtsk, type, desc, stt, byuser, complx, date));
						conTask++;
						break;
					}
				}
				
			}
			
			
			
		} catch (FileNotFoundException e) { System.out.println("Archivo no encontrado"); }
		
	}
	
	
	public ArrayList<Usuario> getUsuarios() {
		return usuarios; 
		}
	
	public ArrayList<Proyecto> getProyectos()
	{
		return proyectos;
	}
	

}
