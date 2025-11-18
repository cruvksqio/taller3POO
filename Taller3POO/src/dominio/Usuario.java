package dominio;

public abstract class Usuario {
	
	private String name;
	private String pass;
	
	public Usuario(String name, String pass) {
		this.name=name;
		this.pass=pass;
	}
	
	
	public static void abrirMenu() {}
	public static void salirMenu() {}
	

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Usuario [name=" + name + ", pass=" + pass + ", getClass()=" + getClass() + "]";
	}
	

}
