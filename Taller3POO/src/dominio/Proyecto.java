package dominio;

import java.util.ArrayList;

/**
 * Clase que representa un Proyecto dentro del sistema.
 * Contiene la información básica y la lista de tareas asociadas.
 * @author Constantino Bekios
 * @author Luis Molina
 */
public class Proyecto {
	
	private String id;
	private String nombre;
	private String autor;
	private ArrayList<Tarea> tareas = new ArrayList<>();
	
    /**
     * Constructor del Proyecto.
     * @param id Identificador único del proyecto.
     * @param nombre Nombre descriptivo.
     * @param autor Usuario responsable del proyecto.
     */
	public Proyecto(String id, String nombre, String autor) {
		this.id = id;
		this.nombre = nombre;
		this.autor = autor;
	}

    /**
     * @return Lista de tareas asociadas al proyecto.
     */
	public ArrayList<Tarea> getTareas() {
		return tareas;
	}

	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getAutor() {
		return autor;
	}


    /**
     * Imprime en consola la información detallada del proyecto y sus tareas.
     */
	public void infoProyecto() {
		
		System.out.println("*".repeat(20));
		System.out.println("ID: " + id);
		System.out.println("Nombre: " + nombre);
		System.out.println("Responsable: " + autor);
		System.out.println("- ".repeat(5));
		System.out.println("Tareas asociadas: ");
		
		"=".repeat(100);
		for (Tarea t: tareas) {
			if (tareas.isEmpty()) {
				System.out.println("Ninguna tarea asociada a este proyecto");
				break;
			}
			
			System.out.println(t);
		}
		
		
	}
	
}