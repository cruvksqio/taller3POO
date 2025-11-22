package dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase Singleton encargada de la gestión de datos del sistema.
 * Maneja la lectura de archivos de texto y mantiene las listas de objetos en memoria.
 * @author Constantino Bekios
 * @author Luis Molina
 */
public class FileManager { 
	
	private static FileManager gestor;
	
	private ArrayList<Usuario> usuarios = new ArrayList<>();
	private ArrayList<Proyecto> proyectos = new ArrayList<>();
	public static int contProy = 0;
	public static int conTask = 0;
	
    /**
     * Constructor privado para implementar el patrón Singleton.
     * Carga los datos de usuarios, proyectos y tareas al iniciar.
     */
	private FileManager() {
		loadUsers();
		loadProyectos();
		loadTareas();
	}
	

    /**
     * Obtiene la instancia única del gestor de archivos.
     * @return La instancia de FileManager.
     */
	public static FileManager getGestor() {
		if (gestor == null) {
			gestor = new FileManager();
		}
		return gestor;
	}
	
	// Lectura de usuarios, proyectos y tareas 
	
    /**
     * Lee el archivo usuarios.txt y pobla la lista de usuarios.
     * Utiliza UserFactory para instanciar los usuarios según su rol.
     */
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
					String name = parts[0].trim();
					String pss = parts[1].trim();
					String role = parts[2].trim();
					
					usuarios.add(UserFactory.crearUser(name, pss, role));   
					} else { System.out.println("Formato incorrecto del txt!!!"); }
				}
		} catch (FileNotFoundException e) { System.out.println("ERROR: Archivo de usuarios no encontrado :c"); }
	
	}
	
    /**
     * Lee el archivo proyectos.txt y carga los proyectos en memoria.
     * Asigna el responsable buscando en la lista de usuarios cargada previamente.
     */
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
	
    /**
     * Lee el archivo tareas.txt y asigna las tareas a sus proyectos correspondientes.
     * Utiliza TareaFactory para crear las instancias de tareas.
     */
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
	
	
    /**
     * @return Lista de todos los usuarios cargados en el sistema.
     */
	public ArrayList<Usuario> getUsuarios() {
		return usuarios; 
		}
	
    /**
     * @return Lista de todos los proyectos cargados en el sistema.
     */
	public ArrayList<Proyecto> getProyectos()
	{
		return proyectos;
	}
	

}