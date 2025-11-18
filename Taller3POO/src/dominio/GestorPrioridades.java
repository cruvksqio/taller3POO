package dominio;

import java.util.List;

public class GestorPrioridades {
    private PrioridadStrategy strategy;
    
    public void setStrategy(PrioridadStrategy strategy) {
        this.strategy = strategy;
    }
    
    public void aplicarPrioridad(List<Tarea> tareas) {
        if (strategy != null) {
            strategy.ordenarTareas(tareas);
        } else {
            System.out.println("No se ha seleccionado una estrategia de prioridad");
        }
    }
}