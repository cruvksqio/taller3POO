package dominio;

import java.util.List;

/**
 * Estrategia concreta de ordenamiento por impacto.
 * Define la jerarquía: Bug > Feature > Documentación.
 * @author Constantino Bekios
 * @author Luis Molina
 */
public class PrioridadImpactoStrategy implements PrioridadStrategy {
    
    /**
     * Ordena las tareas basándose en su tipo (Impacto).
     * @param tareas Lista de tareas a ordenar.
     */
    @Override
    public void ordenarTareas(List<Tarea> tareas) {
        // Ordenamiento burbuja por tipo (Bug > Feature > Documentación)
        for (int i = 0; i < tareas.size() - 1; i++) {
            for (int j = 0; j < tareas.size() - i - 1; j++) {
                int prio1 = getPriorityByType(tareas.get(j));
                int prio2 = getPriorityByType(tareas.get(j + 1));
                
                if (prio1 < prio2) {
                    // Intercambiar
                    Tarea temp = tareas.get(j);
                    tareas.set(j, tareas.get(j + 1));
                    tareas.set(j + 1, temp);
                }
            }
        }
        System.out.println("Tareas ordenadas por impacto (Bug > Feature > Documentación)");
    }
    
    /**
     * Asigna un valor numérico a la prioridad según el tipo de clase.
     * @param tarea La tarea a evaluar.
     * @return Valor entero de prioridad (3=Bug, 2=Feature, 1=Doc).
     */
    private int getPriorityByType(Tarea tarea) {
        if (tarea instanceof Bug) return 3;
        if (tarea instanceof Feature) return 2;
        if (tarea instanceof Documentacion) return 1;
        return 0;
    }
}