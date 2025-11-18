package dominio;

public class UserFactory {
	
	public static Usuario crearUser(String name, String pass, String rol) //Factory creacion usuarios
	{
		switch (rol)
		{
		case "Administrador": return new Admin(name, pass);
		case "Colaborador": return new Colaborador(name,pass);
		default: return null;
		}
	}

}
