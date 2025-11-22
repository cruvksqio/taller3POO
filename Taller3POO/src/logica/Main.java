package logica;
/*
Constantino Bekios 21761616-6
Luis Molina 21564225-9
*/

import dominio.*;
import java.util.*;

/**
 * Clase principal que contiene el punto de entrada de la aplicación.
 * Gestiona el ciclo de vida del login y la sesión de usuarios.
 * @author Constantino Bekios
 * @author Luis Molina
 */
public class Main {
    
    /**
     * Método principal (main).
     * Inicializa el FileManager, maneja el login y redirige a los menús correspondientes.
     * @param args Argumentos de línea de comandos.
     */
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FileManager fm = FileManager.getGestor(); // Carga los datos
        
        System.out.println("=== SISTEMA DE GESTIÓN TASKFORGE ===");
        
     

        while(true) {
            System.out.print("Usuario: ");
            String user = sc.nextLine();
            System.out.print("Contraseña: ");
            String pass = sc.nextLine();
            
            Usuario usuarioLogueado = null;
            
            // Buscar usuario (con protección contra nulos)
            for(Usuario u : fm.getUsuarios()) {
                if(u != null && u.getName().equalsIgnoreCase(user)) { 
                     usuarioLogueado = u; 
                     break;
                }
            }
            
            if(usuarioLogueado != null) {
                System.out.println("Bienvenido " + usuarioLogueado.getName());
                usuarioLogueado.abrirMenu();
            } else {
                System.out.println("ERROR: Usuario no encontrado o credenciales incorrectas.");
            }
            
            System.out.println("¿Salir del sistema? (s/n)");
            if(sc.nextLine().equalsIgnoreCase("s")) break;
        }
        sc.close();
    }
}