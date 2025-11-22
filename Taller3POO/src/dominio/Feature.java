package dominio;

/**
 * Representa una tarea de tipo Feature (Nueva funcionalidad).
 * Tiene una prioridad media por defecto.
 * @author Constantino Bekios
 * @author Luis Molina
 */
public class Feature extends Tarea {
	
	
	private int priority = 2;
		
        /**
         * Constructor privado usado por el Builder.
         * @param builder El builder con los datos.
         */
		private Feature(Builder builder) {
			super(builder);
		}
		
        /**
         * Builder específico para crear instancias de Feature.
         */
		public static class Builder extends Tarea.Builder {
			
			public Builder(String taskID) {
				super(taskID);
			}
			
			@Override
			public Feature build() {
				return new Feature(this);
			}
			
			
		}
		
		public int getPriority() {
			return priority;
		}
	
        /**
         * Permite que un Visitor analice esta tarea.
         * @param visitor Instancia del visitante.
         */
		@Override
	    public void accept(Visitor visitor) {
	        visitor.visit(this);
	    }	
	
}