package dominio;

public abstract class Usuario {
	
	private String name;
	private String pass;
	
	public Usuario(String name, String pass) {
		this.name=name;
		this.pass=pass;
	}
	
	public static Usuario userFactory(String name, String pass, String rol) //Factory creacion usuarios
	{
		switch (rol)
		{
		case "Administrador": return new Admin(name, pass);
		case "Colaborador": return new Colaborador(name,pass);
		default: return null;
		}
	}
	
	public void abrirMenu() {}
	public void salirMenu() {}



	@Override
	public String toString() {
		return "Usuario [name=" + name + ", pass=" + pass + ", getClass()=" + getClass() + "]";
	}

	public String getName() {
		return name;
	}

	public String getPass() {
		return pass;
	}
	

}
