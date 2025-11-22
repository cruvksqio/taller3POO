package dominio;

import java.util.List;

/**
 * Interfaz que define el contrato para las estrategias de priorización.
 * Parte del patrón Strategy.
 * @author Constantino Bekios
 * @author Luis Molina
 */
public interface PrioridadStrategy {
    
    /**
     * Ordena una lista de tareas según un criterio específico.
     * @param tareas La lista de tareas a ordenar.
     */
    void ordenarTareas(List<Tarea> tareas);
}