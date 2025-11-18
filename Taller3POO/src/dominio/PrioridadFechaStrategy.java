package dominio;

import java.util.List;

public class PrioridadFechaStrategy implements PrioridadStrategy {
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