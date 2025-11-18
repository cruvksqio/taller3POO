package dominio;

public class Feature extends Tarea {
	
	
	private int priority = 2;
		
		private Feature(Builder builder) {
			super(builder);
		}
		
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

}
