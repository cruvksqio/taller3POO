package dominio;

import java.util.Scanner;

public class Colaborador extends Usuario {

    public Colaborador(String name, String pass) {
        super(name, pass);
    }
    
    @Override
    public void abrirMenu() {
        Scanner sc = new Scanner(System.in);
        int opt;
        
        do {
            System.out.println("\n=== MENÚ COLABORADOR (" + getName() + ") ===");
            System.out.println("1.- Ver proyectos disponibles");
            System.out.println("2.- Ver mis tareas asignadas");
            System.out.println("3.- Actualizar estado de una tarea");
            System.out.println("4.- Analizar tareas (Visitor)");
            System.out.println("0.- Salir");
            System.out.print("Opción: ");
            
            opt = sc.nextInt();
            sc.nextLine();
            
            switch (opt) {
                case 1 -> verProyectos();
                case 2 -> verMisTareas();
                case 3 -> actualizarEstado(sc);
                case 4 -> aplicarVisitor();
                case 0 -> System.out.println("Cerrando sesión...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opt != 0);
    }

    private void verProyectos() {
        FileManager fm = FileManager.getGestor();
        System.out.println("--- Proyectos ---");
        for(Proyecto p : fm.getProyectos()) {
            System.out.println("ID: " + p.getId() + " | Nombre: " + p.getNombre());
        }
    }

    private void verMisTareas() {
        FileManager fm = FileManager.getGestor();
        System.out.println("--- Mis Tareas ---");
        boolean found = false;
        for(Proyecto p : fm.getProyectos()) {
            for(Tarea t : p.getTareas()) {
                if(t.getManaged().equalsIgnoreCase(this.getName())) {
                    System.out.println(p.getId() + " -> " + t.getTaskID() + ": " + t.getDesc() + " [" + t.getState() + "]");
                    found = true;
                }
            }
        }
        if(!found) System.out.println("No tienes tareas asignadas.");
    }

    private void actualizarEstado(Scanner sc) {
        System.out.print("Ingrese ID de la tarea a actualizar (ej: T001): ");
        String idTask = sc.nextLine();
        
        FileManager fm = FileManager.getGestor();
        Tarea tareaEncontrada = null;
        
        // Buscar la tarea
        for(Proyecto p : fm.getProyectos()) {
            for(Tarea t : p.getTareas()) {
                if(t.getTaskID().equals(idTask) && t.getManaged().equalsIgnoreCase(this.getName())) {
                    tareaEncontrada = t;
                    break;
                }
            }
        }
        
        if(tareaEncontrada != null) {
            System.out.println("Estado actual: " + tareaEncontrada.getState());
            System.out.println("1. En progreso");
            System.out.println("2. Completada");
            System.out.print("Seleccione nuevo estado: ");
            
            int stateOpt = sc.nextInt();
            
            if(stateOpt == 1) {
                tareaEncontrada.setState("En progreso");
                System.out.println("Estado actualizado a: En progreso");
            } else if(stateOpt == 2) {
                tareaEncontrada.setState("Completada");
                System.out.println("Estado actualizado a: Completada");
            }
            
        } else {
            System.out.println("Tarea no encontrada o no te pertenece.");
        }
    }

    private void aplicarVisitor() {
        FileManager fm = FileManager.getGestor();
        AnalisisVisitor visitor = new AnalisisVisitor();
        System.out.println("--- Análisis de Tareas (Visitor) ---");
        for(Proyecto p : fm.getProyectos()) {
            for(Tarea t : p.getTareas()) {
                if(t.getManaged().equalsIgnoreCase(this.getName())) {
                    t.accept(visitor);
                }
            }
        }
    }
}