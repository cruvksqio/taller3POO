package dominio;

/**
 * Factory Method para la creación de tareas.
 * Centraliza la lógica de instanciación de las subclases Bug, Feature y Documentación.
 * @author Constantino Bekios
 * @author Luis Molina
 */
public class TareaFactory {
	
    /**
     * Crea una instancia de una tarea específica basada en el tipo proporcionado.
     * Utiliza el patrón Builder de cada subclase para construir el objeto.
     * * @param prID ID del proyecto al que pertenece la tarea.
     * @param tsID ID único de la tarea.
     * @param type Tipo de tarea ("Bug", "Feature", "Documentacion").
     * @param dc Descripción textual de la tarea.
     * @param state Estado actual de la tarea.
     * @param byuser Usuario responsable de la tarea.
     * @param complx Nivel de complejidad.
     * @param date Fecha de creación.
     * @return Una instancia de la subclase correspondiente de Tarea, o null si el tipo no es válido.
     */
	public static Tarea crearTarea(String prID,String tsID, String type, 
			String dc, String state, String byuser, String complx, String date) 
	{
		
		/* Patron autorizado por el ayudante. (Patrón Builder) */
		
		switch (type) {
		
		case "Bug": return new Bug.Builder(tsID)
				.proyect(prID)
				.desc(dc)
				.state(state)
				.managed(byuser)
				.complex(complx)
				.date(date)
				.build();
		
		case "Feature": return new Feature.Builder(tsID)
				.proyect(prID)
				.desc(dc)
				.state(state)
				.managed(byuser)
				.complex(complx)
				.date(date)
				.build();
		
		case "Documentacion": return new Documentacion.Builder(tsID)
				.proyect(prID)
				.desc(dc)
				.state(state)
				.managed(byuser)
				.complex(complx)
				.date(date)
				.build();
		}
		
		
		return null;
	}
	

}


/* String idpro = parts[0];
	String idtsk = parts[1];
	String type = parts[2];
	String desc = parts[3];
	String stt = parts[4];
	String byuser = parts[5];
	String complx = parts[6];
	String date = parts[7]; */