package dominio;

import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Representa el rol de Administrador en el sistema.
 * Tiene privilegios para gestionar proyectos, tareas y generar reportes.
 * @author Constantino Bekios
 * @author Luis Molina
 */
public class Admin extends Usuario {

    /**
     * Constructor de la clase Admin.
     * @param name Nombre del administrador.
     * @param pass Contraseña del administrador.
     */
    public Admin(String name, String pass) {
        super(name, pass);
    }
    
    /**
     * Despliega el menú interactivo con las opciones exclusivas del administrador.
     * Implementa la lógica de selección de opciones.
     */
    @Override
    public void abrirMenu() {
        
        Scanner sc = new Scanner(System.in);
        
        int opt;
        
        do {
            
        System.out.println("=".repeat(30));
        System.out.println("=".repeat(10) + "SOY ADMIN" + "=".repeat(10));
        System.out.println("=".repeat(30));
        System.out.println();
        
        System.out.println("1.- Ver lista completa de proyectos y tareas");
        System.out.println("2.- Agregar/Eliminar proyecto");
        System.out.println("3.- Agregar/Eliminar tarea de proyecto");
        System.out.println("4.- Asignar prioridades a tareas");
        System.out.println("5.- Generar reporte de proyectos");
        System.out.println("0.- Salir");
        System.out.println();
        System.out.print("Ingrese una opcion: ");
        
        opt = sc.nextInt();
        sc.nextLine();
        
        switch (opt) {
        
        case 1 -> 
        {
            printearProyectos();
        }
        case 2 -> 
        {
            menuProyectos(sc);
        }
        case 3 -> 
        {
            menuTasks(sc);
        }
        case 4 -> 
        {
            asignarPriorityes(sc);
        }
        case 5 -> 
        {
            makeTxt();
        }
        case 0 -> System.out.println("Saliendo del menú admin...");
        default -> System.out.println("Ingrese un numero correcto"); 
        
        }
        
        } while (opt != 0);
        
    }


    /**
     * Muestra por consola la información detallada de todos los proyectos.
     */
    private void printearProyectos() {
        
        FileManager fm = FileManager.getGestor();
        if (fm.getProyectos().isEmpty()) {
            System.out.println("No hay proyectos existentes.");
            return;
        }
        
        for (Proyecto p : fm.getProyectos()) {
            p.infoProyecto();
        }
    }
    
    /**
     * Submenú para la creación y eliminación de proyectos.
     * @param sc Scanner para leer la entrada del usuario.
     */
    private void menuProyectos(Scanner sc) {
        
        int opt = -1;
        
        do {
        System.out.println("* * Menu Proyectos * *");
        System.out.println("1.- Crear un nuevo proyecto");
        System.out.println("2.- Eliminar proyecto existente");
        System.out.println("0.- Salir");
        System.out.print("Seleccione opción: ");
        
        opt = sc.nextInt();
        sc.nextLine();
        
        switch (opt) {
        
        case 1 -> {
            crearProyecto(sc);
        }
        
        case 2 -> {
            eliminarProyecto(sc);
        }
        
        case 0 -> {return;}
        
        default -> {System.out.println("Ingrese una opcion correcta");}
        
        }
        
        } while (opt != 0);
    }
    
    /**
     * Solicita datos al usuario y crea un nuevo proyecto en el sistema.
     * @param sc Scanner para leer la entrada.
     */
    private void crearProyecto(Scanner sc) {
        FileManager fm = FileManager.getGestor();
        
        System.out.println("=== Crear Nuevo Proyecto ===");
        System.out.print("Ingrese el nombre del proyecto: ");
        String nombre = sc.nextLine();
        
        System.out.print("Ingrese el nombre del responsable: ");
        String responsable = sc.nextLine();
        
        // Verificar si el responsable existe
        boolean responsableExiste = false;
        for (Usuario usuario : fm.getUsuarios()) {
            if (usuario.getName().equals(responsable)) {
                responsableExiste = true;
                break;
            }
        }
        
        if (!responsableExiste) {
            System.out.println("ERROR: El responsable no existe en el sistema");
            return;
        }
        
        // Generar ID único
        String nuevoId = "PR" + String.format("%03d", FileManager.contProy + 1);
        
        // Crear y agregar proyecto
        Proyecto nuevoProyecto = new Proyecto(nuevoId, nombre, responsable);
        fm.getProyectos().add(nuevoProyecto);
        FileManager.contProy++;
        
        System.out.println("Proyecto creado exitosamente! ID: " + nuevoId);
    }
    
    /**
     * Permite seleccionar y eliminar un proyecto existente junto con sus tareas.
     * @param sc Scanner para leer la selección del usuario.
     */
    private void eliminarProyecto(Scanner sc) {
        FileManager fm = FileManager.getGestor();
        
        System.out.println("=== Eliminar Proyecto ===");
        
        if (fm.getProyectos().isEmpty()) {
            System.out.println("No hay proyectos existentes");
            return;
        }
        
        // Mostrar proyectos
        System.out.println("Proyectos disponibles:");
        for (int i = 0; i < fm.getProyectos().size(); i++) {
            Proyecto p = fm.getProyectos().get(i);
            System.out.println((i + 1) + ".- " + p.getId() + " - " + p.getNombre());
        }
        
        System.out.print("Seleccione un proyecto para eliminar: ");
        int delete = sc.nextInt() - 1;
        sc.nextLine();
        
        if (delete < 0 || delete >= fm.getProyectos().size()) {
            System.out.println("ERROR: Elija un proyecto valido");
            return;
        }
        
        Proyecto proyectoEliminar = fm.getProyectos().get(delete);
        System.out.println("¿Desea eliminar el proyecto " + proyectoEliminar.getId() + " - " + 
                         proyectoEliminar.getNombre() + "? (Se eliminaran " + 
                         proyectoEliminar.getTareas().size() + " tareas asociadas)");
        
        System.out.println("1.- Si");
        System.out.println("2.- No");
        System.out.print("Seleccione: ");
        
        int pick = sc.nextInt();
        sc.nextLine();
        
        if (pick == 1) {
            fm.getProyectos().remove(delete);
            System.out.println("Proyecto eliminado exitosamente!");
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }
    
    /**
     * Submenú para la gestión de tareas (creación y eliminación).
     * @param sc Scanner para la entrada de datos.
     */
    private void menuTasks(Scanner sc) {
        
        int opt = -1;
        
        do {
        System.out.println("* * Menu Tareas * *");
        System.out.println("1.- Crear una nueva tarea en proyecto");
        System.out.println("2.- Eliminar tarea existente");
        System.out.println("0.- Salir");
        System.out.print("Seleccione opción: ");
        
        opt = sc.nextInt();
        sc.nextLine();
        
        switch (opt) {
        
        case 1 -> {
            crearTarea(sc);
        }
        
        case 2 -> {
            eliminarTarea(sc);
        }
        
        case 0 -> {return;}
        
        default -> {System.out.println("Ingrese una opcion correcta");}
        
        }
        
        } while (opt != 0);
        
    }
    
    /**
     * Solicita información detallada para crear una nueva tarea en un proyecto específico.
     * Utiliza TareaFactory para la instanciación.
     * @param sc Scanner para la entrada de datos.
     */
    private void crearTarea(Scanner sc) {
        FileManager fm = FileManager.getGestor();
        
        if (fm.getProyectos().isEmpty()) {
            System.out.println("No hay proyectos existentes. Cree un proyecto primero.");
            return;
        }
        
        // Mostrar proyectos disponibles
        System.out.println("=== Crear Nueva Tarea ===");
        System.out.println("Proyectos disponibles:");
        for (int i = 0; i < fm.getProyectos().size(); i++) {
            Proyecto p = fm.getProyectos().get(i);
            System.out.println((i + 1) + ".- " + p.getId() + " - " + p.getNombre());
        }
        
        System.out.print("Seleccione un proyecto: ");
        int proyectoIndex = sc.nextInt() - 1;
        sc.nextLine();
        
        if (proyectoIndex < 0 || proyectoIndex >= fm.getProyectos().size()) {
            System.out.println("ERROR: Proyecto no válido");
            return;
        }
        
        Proyecto proyectoSeleccionado = fm.getProyectos().get(proyectoIndex);
        
        // Datos de la tarea
        System.out.println("Ingrese los datos de la tarea:");
        
        System.out.print("Tipo de tarea (Bug/Feature/Documentacion): ");
        String tipo = sc.nextLine();
        
        if (!tipo.equals("Bug") && !tipo.equals("Feature") && !tipo.equals("Documentacion")) {
            System.out.println("ERROR: Tipo de tarea no válido");
            return;
        }
        
        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();
        
        System.out.print("Estado (Pendiente/En progreso/Completada): ");
        String estado = sc.nextLine();
        
        System.out.print("Responsable: ");
        String responsable = sc.nextLine();
        
        // Verificar si el responsable existe
        boolean responsableExiste = false;
        for (Usuario usuario : fm.getUsuarios()) {
            if (usuario.getName().equals(responsable)) {
                responsableExiste = true;
                break;
            }
        }
        
        if (!responsableExiste) {
            System.out.println("ERROR: El responsable no existe en el sistema");
            return;
        }
        
        System.out.print("Complejidad (Alta/Media/Baja): ");
        String complejidad = sc.nextLine();
        
        System.out.print("Fecha (YYYY-MM-DD): ");
        String fecha = sc.nextLine();
        
        // Generar ID único
        String nuevoTaskId = "T" + String.format("%03d", FileManager.conTask + 1);
        
        // Crear tarea usando factory
        Tarea nuevaTarea = TareaFactory.crearTarea(
            proyectoSeleccionado.getId(),
            nuevoTaskId,
            tipo,
            descripcion,
            estado,
            responsable,
            complejidad,
            fecha
        );
        
        if (nuevaTarea != null) {
            proyectoSeleccionado.getTareas().add(nuevaTarea);
            FileManager.conTask++;
            System.out.println("Tarea creada exitosamente! ID: " + nuevoTaskId);
        } else {
            System.out.println("Error al crear la tarea");
        }
    }
    
    /**
     * Permite eliminar una tarea específica de un proyecto seleccionado.
     * @param sc Scanner para la selección.
     */
    private void eliminarTarea(Scanner sc) {
        FileManager fm = FileManager.getGestor();
        
        if (fm.getProyectos().isEmpty()) {
            System.out.println("No hay proyectos existentes.");
            return;
        }
        
        // Mostrar proyectos
        System.out.println("=== Eliminar Tarea ===");
        System.out.println("Proyectos disponibles:");
        for (int i = 0; i < fm.getProyectos().size(); i++) {
            Proyecto p = fm.getProyectos().get(i);
            System.out.println((i + 1) + ".- " + p.getId() + " - " + p.getNombre() + 
                             " (" + p.getTareas().size() + " tareas)");
        }
        
        System.out.print("Seleccione un proyecto: ");
        int proyectoIndex = sc.nextInt() - 1;
        sc.nextLine();
        
        if (proyectoIndex < 0 || proyectoIndex >= fm.getProyectos().size()) {
            System.out.println("ERROR: Proyecto no válido");
            return;
        }
        
        Proyecto proyectoSeleccionado = fm.getProyectos().get(proyectoIndex);
        
        if (proyectoSeleccionado.getTareas().isEmpty()) {
            System.out.println("Este proyecto no tiene tareas.");
            return;
        }
        
        // Mostrar tareas del proyecto
        System.out.println("Tareas del Proyecto " + proyectoSeleccionado.getNombre() + ":");
        for (int i = 0; i < proyectoSeleccionado.getTareas().size(); i++) {
            Tarea t = proyectoSeleccionado.getTareas().get(i);
            System.out.println((i + 1) + ".- " + t.getTaskID() + " - " + t.getDesc());
        }
        
        System.out.print("Seleccione una tarea para eliminar: ");
        int tareaIndex = sc.nextInt() - 1;
        sc.nextLine();
        
        if (tareaIndex < 0 || tareaIndex >= proyectoSeleccionado.getTareas().size()) {
            System.out.println("ERROR: Tarea no válida");
            return;
        }
        
        Tarea tareaEliminar = proyectoSeleccionado.getTareas().get(tareaIndex);
        System.out.println("¿Desea eliminar la tarea " + tareaEliminar.getTaskID() + " - " + 
                         tareaEliminar.getDesc() + "?");
        
        System.out.println("1.- Si");
        System.out.println("2.- No");
        System.out.print("Seleccione: ");
        
        int pick = sc.nextInt();
        sc.nextLine();
        
        if (pick == 1) {
            proyectoSeleccionado.getTareas().remove(tareaIndex);
            System.out.println("Tarea eliminada exitosamente!");
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }
    
    /**
     * Aplica estrategias de priorización (Strategy Pattern) a las tareas de los proyectos.
     * @param sc Scanner para seleccionar la estrategia (Fecha, Impacto o Complejidad).
     */
    private void asignarPriorityes(Scanner sc) {
        FileManager fm = FileManager.getGestor();
        
        System.out.println("=== Asignar Prioridades ===");
        System.out.println("1.- Por fecha de creación (más antiguas primero)");
        System.out.println("2.- Por impacto (Bug > Feature > Documentación)");
        System.out.println("3.- Por complejidad (Alta > Media > Baja)");
        System.out.print("Seleccione estrategia: ");
        
        int opcion = sc.nextInt();
        sc.nextLine();
        
        PrioridadStrategy estrategia = null;
        
        switch (opcion) {
            case 1 -> estrategia = new PrioridadFechaStrategy();
            case 2 -> estrategia = new PrioridadImpactoStrategy();
            case 3 -> estrategia = new PrioridadComplejidadStrategy();
            default -> {
                System.out.println("Opción no válida");
                return;
            }
        }
        
        GestorPrioridades gestor = new GestorPrioridades();
        gestor.setStrategy(estrategia);
        
        boolean hayTareas = false;
        for (Proyecto proyecto : fm.getProyectos()) {
            if (!proyecto.getTareas().isEmpty()) {
                hayTareas = true;
                System.out.println("Ordenando tareas del proyecto: " + proyecto.getNombre());
                gestor.aplicarPrioridad(proyecto.getTareas());
                
                // Mostrar resultado del ordenamiento
                System.out.println("Tareas ordenadas:");
                for (int i = 0; i < proyecto.getTareas().size(); i++) {
                    Tarea t = proyecto.getTareas().get(i);
                    System.out.println((i + 1) + ". " + t.getTaskID() + " - " + t.getDesc() + 
                                     " (" + t.getClass().getSimpleName() + ")");
                }
            }
        }
        
        if (!hayTareas) {
            System.out.println("No hay tareas para ordenar en ningún proyecto.");
        }
    }
    
    /**
     * Genera un archivo de texto 'reporte.txt' con estadísticas y detalles de todos los proyectos.
     * Incluye conteo de tareas por estado y detalle de cada tarea.
     */
    private void makeTxt() {
        FileManager fm = FileManager.getGestor();
        
        try (FileWriter writer = new FileWriter("reporte.txt")) {
            writer.write("=== REPORTE DE PROYECTOS ===");
            writer.write("Fecha de generación: " + new Date() + "");
            
            int totalTareas = 0;
            
            for (Proyecto proyecto : fm.getProyectos()) {
                writer.write("PROYECTO: " + proyecto.getNombre() + "");
                writer.write("ID: " + proyecto.getId() + "");
                writer.write("Responsable: " + proyecto.getAutor() + "");
                writer.write("Total de tareas: " + proyecto.getTareas().size() + "");
                writer.write("-".repeat(50) + "");
                totalTareas += proyecto.getTareas().size();
                
                if (proyecto.getTareas().isEmpty()) {
                    writer.write("No hay tareas en este proyecto.");
                } else {
                    // Contar tareas por estado
                    int pendientes = 0;
                    int enProgreso = 0;
                    int completadas = 0;
                    
                    for (Tarea tarea : proyecto.getTareas()) {
                        if ("Pendiente".equals(tarea.getState())) pendientes++;
                        else if ("En progreso".equals(tarea.getState())) enProgreso++;
                        else if ("Completada".equals(tarea.getState())) completadas++;
                    }
                    
                    writer.write("Estadísticas:");
                    writer.write("  - Pendientes: " + pendientes + "");
                    writer.write("  - En progreso: " + enProgreso + "");
                    writer.write("  - Completadas: " + completadas + "");
                    
                    writer.write("TAREAS:");
                    for (Tarea tarea : proyecto.getTareas()) {
                        writer.write("  ID: " + tarea.getTaskID() + "");
                        writer.write("  Tipo: " + tarea.getClass().getSimpleName() + "");
                        writer.write("  Descripción: " + tarea.getDesc() + "");
                        writer.write("  Estado: " + tarea.getState() + "");
                        writer.write("  Responsable: " + tarea.getManaged() + "");
                        writer.write("  Complejidad: " + tarea.getComplex() + "");
                        writer.write("  Fecha: " + tarea.getDate() + "");
                        writer.write("  " + "-".repeat(20) + "");
                    }
                }
                writer.write("" + "=".repeat(80) + "");
            }
            
            writer.write("=== RESUMEN GENERAL ===");
            writer.write("Total de proyectos: " + fm.getProyectos().size() + "");
            writer.write("Total de tareas: " + totalTareas + "");
            
            System.out.println("Reporte generado exitosamente en 'reporte.txt'");
            
        } catch (IOException e) {
            System.out.println("Error al generar el reporte: " + e.getMessage());
        }
    }
}