package dominio;

import java.util.List;

/**
 * Clase Contexto para el patrón Strategy.
 * Permite cambiar dinámicamente la estrategia de ordenamiento de tareas.
 * @author Constantino Bekios
 * @author Luis Molina
 */
public class GestorPrioridades {
    private PrioridadStrategy strategy;
    
    /**
     * Define la estrategia de ordenamiento a utilizar.
     * @param strategy La estrategia concreta (Fecha, Impacto o Complejidad).
     */
    public void setStrategy(PrioridadStrategy strategy) {
        this.strategy = strategy;
    }
    
    /**
     * Ejecuta el ordenamiento sobre una lista de tareas usando la estrategia actual.
     * @param tareas Lista de tareas a ordenar.
     */
    public void aplicarPrioridad(List<Tarea> tareas) {
        if (strategy != null) {
            strategy.ordenarTareas(tareas);
        } else {
            System.out.println("No se ha seleccionado una estrategia de prioridad");
        }
    }
}