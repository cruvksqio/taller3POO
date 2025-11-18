package dominio;

public abstract class Tarea {
	
	// Atributos 
	protected String taskID;
	protected String proyect;
	// el tipo se crea como subclase hija :P
	protected String desc;
	protected String state;
	protected String managed;
	protected String complex;
	protected String date;
	
	
	protected Tarea(Builder builder) {
		
		this.proyect = builder.proyect;
		this.taskID= builder.taskID;
		this.desc = builder.desc;
		this.state = builder.state;
		this.managed = builder.managed;
		this.complex= builder.complex;
		this.date= builder.date;
		
	}
	
	public static abstract class Builder {
		
		protected String proyect;
		protected String taskID;
		
		protected String desc;
		protected String state;
		protected String managed;
		protected String complex;
		protected String date;
		
		// Builder, Setters
		
		public Builder(String taskID) {
			this.taskID = taskID;
		}
		
		public Builder proyect(String proyect) {
			this.proyect = proyect;
			return this;
		}
		
		public Builder desc(String desc) {
			this.desc = desc;
			return this;
		}
		public Builder state(String state) {
			this.state = state;
			return this;
		}
		public Builder managed(String managed) {
			this.managed = managed;
			return this;
		}
		public Builder complex(String complex) {
			this.complex = complex;
			return this;
		}
		public Builder date(String date) {
			this.date = date;
			return this;
		}
		
		public abstract Tarea build();
		
	}
	
	public String getProyect() {
		return proyect;
	}

	public String getTaskID() {
		return taskID;
	}

	public String getDesc() {
		return desc;
	}

	public String getState() {
		return state;
	}

	public String getManaged() {
		return managed;
	}

	public String getComplex() {
		return complex;
	}

	public String getDate() {
		return date;
	}

	@Override
	public String toString() {
		
	    // Construccion string :3333
	    String resultado = "ID Tarea: " + taskID + "\n" +
	                      "Descripcion: " + desc + "\n" +
	                      "Estado: " + state + "\n" +
	                      "Usuario a cargo: " + managed + "\n" +
	                      "Complejidad" + complex + "\n" +
	                      "Fecha" + date + "\n" +
	                      "=".repeat(100);
		
		return resultado;/* "ID: " + taskID + "Desc=" + desc + ", state=" + state + ", managed=" + managed + ", complex="
				+ complex + ", date=" + date + "]"; */
	}
	
	
	
	
	

}
