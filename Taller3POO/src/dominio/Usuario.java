package dominio;

/**
 * Clase abstracta base para todos los usuarios del sistema.
 * Define los atributos comunes y el método abstracto para el menú.
 * @author Constantino Bekios
 * @author Luis Molina
 */
public abstract class Usuario {
	
	private String name;
	private String pass;
	
    /**
     * Constructor base para usuarios.
     * @param name Nombre de usuario.
     * @param pass Contraseña.
     */
	public Usuario(String name, String pass) {
		this.name=name;
		this.pass=pass;
	}
	
	
    /**
     * Método abstracto que debe implementar cada subclase para mostrar su menú específico.
     */
	public void abrirMenu() {}
	
    /**
     * Método estático de utilidad para operaciones de salida (placeholder).
     */
	public static void salirMenu() {}
	

    /**
     * @return El nombre del usuario.
     */
	public String getName() {
		return name;
	}
	
    /**
     * @return La contraseña del usuario.
     */
	public String getPass() {
        return pass;
    }
	
	@Override
	public String toString() {
		return "Usuario [name=" + name + ", pass=" + pass + ", getClass()=" + getClass() + "]";
	}
	

}