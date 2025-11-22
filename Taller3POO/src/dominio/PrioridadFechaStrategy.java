package dominio;

import java.util.List;

/**
 * Estrategia concreta de ordenamiento.
 * Implementa la lógica para ordenar tareas según su fecha de creación.
 * @author Constantino Bekios
 * @author Luis Molina
 */
public class PrioridadFechaStrategy implements PrioridadStrategy {
    
    /**
     * Ordena una lista de tareas comparando sus fechas (String).
     * Utiliza el algoritmo de burbuja para el ordenamiento.
     * @param tareas Lista de tareas a ordenar.
     */
    @Override
    public void ordenarTareas(List<Tarea> tareas) {
        // Ordenamiento burbuja por fecha
        for (int i = 0; i < tareas.size() - 1; i++) {
            for (int j = 0; j < tareas.size() - i - 1; j++) {
                String fecha1 = tareas.get(j).getDate();
                String fecha2 = tareas.get(j + 1).getDate();
                
                if (fecha1.compareTo(fecha2) > 0) {
                    // Intercambiar
                    Tarea temp = tareas.get(j);
                    tareas.set(j, tareas.get(j + 1));
                    tareas.set(j + 1, temp);
                }
            }
        }
        System.out.println("Tareas ordenadas por fecha de creación (más antiguas primero)");
    }
}