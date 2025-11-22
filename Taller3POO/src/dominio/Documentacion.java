package dominio;

/**
 * Representa una tarea de tipo Documentación en el sistema.
 * Tiene una prioridad baja por defecto.
 * @author Constantino Bekios
 * @author Luis Molina
 */
public class Documentacion extends Tarea {
	
	private int priority = 1;
	
    /**
     * Constructor privado utilizado por el Builder.
     * @param builder El builder con los datos de la tarea.
     */
	private Documentacion(Builder builder) {
		super(builder);
	}
	
    /**
     * Implementación del patrón Builder para la clase Documentacion.
     */
	public static class Builder extends Tarea.Builder {
		
		public Builder(String taskID) {
			super(taskID);
		}
		
		@Override
		public Documentacion build() {
			return new Documentacion(this);
		}
		
		
	}
	
	public int getPriority() {
		return priority;
	}
	
    /**
     * Acepta un visitante (Visitor) para realizar operaciones sobre esta tarea.
     * @param visitor El visitante que ejecutará la operación.
     */
	@Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}