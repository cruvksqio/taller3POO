package dominio;

public class Bug extends Tarea {
	
	private int priority = 3;
	
	private Bug(Builder builder) {
		super(builder);
	}
	
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

}
