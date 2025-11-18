package dominio;

public class TareaFactory {
	
	public static Tarea crearTarea(String prID,String tsID, String type, 
			String dc, String state, String byuser, String complx, String date) 
	{
		switch (type) {
		
		case "Bug": return new Bug.Builder(tsID)
				.proyect(prID)
				.date(dc)
				.state(state)
				.managed(byuser)
				.complex(complx)
				.date(date)
				.build();
		
		case "Feature": return new Feature.Builder(tsID)
				.proyect(prID)
				.date(dc)
				.state(state)
				.managed(byuser)
				.complex(complx)
				.date(date)
				.build();
		
		case "Documentacion": return new Documentacion.Builder(tsID)
				.proyect(prID)
				.date(dc)
				.state(state)
				.managed(byuser)
				.complex(complx)
				.date(date)
				.build();
		}
		
		
		return null;
	}
	

}


/*  String idpro = parts[0];
	String idtsk = parts[1];
	String type = parts[2];
	String desc = parts[3];
	String stt = parts[4];
	String byuser = parts[5];
	String complx = parts[6];
	String date = parts[7]; */

	
