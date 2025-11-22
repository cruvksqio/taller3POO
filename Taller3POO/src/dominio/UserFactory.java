package dominio;

/**
 * Factory Method para la creación de usuarios.
 * Permite crear instancias de Administrador o Colaborador según el rol especificado.
 * @author Constantino Bekios
 * @author Luis Molina
 */
public class UserFactory {
	
    /**
     * Crea un usuario nuevo basado en el rol proporcionado.
     * * @param name Nombre de usuario.
     * @param pass Contraseña del usuario.
     * @param rol Rol del usuario ("Administrador" o "Colaborador").
     * @return Una instancia de Admin o Colaborador, o null si el rol no existe.
     */
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