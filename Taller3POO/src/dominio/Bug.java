package dominio;

/**
 * Representa una tarea de tipo Bug (Error).
 * Tiene una prioridad alta por defecto.
 * @author Constantino Bekios
 * @author Luis Molina
 */
public class Bug extends Tarea {
	
	private int priority = 3;
	
    /**
     * Constructor privado usado por el Builder.
     * @param builder El builder con los datos.
     */
	private Bug(Builder builder) {
		super(builder);
	}
	
    /**
     * Builder específico para la creación de Bugs.
     */
	public static class Builder extends Tarea.Builder {
		
		public Builder(String taskID) {
			super(taskID);
		}
		
		@Override
		public Bug build() {
			return new Bug(this);
		}
		
		
	}
	
	public int getPriority() {
		return priority;
	}
	
    /**
     * Acepta un visitante para analizar la criticidad del Bug.
     * @param visitor El visitante.
     */
	@Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
	
}