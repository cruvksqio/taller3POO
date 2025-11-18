package dominio;

public class Documentacion extends Tarea {
	
	private int priority = 1;
	
	private Documentacion(Builder builder) {
		super(builder);
	}
	
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
	
	

}
