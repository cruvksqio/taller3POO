package dominio;

import java.util.List;

public interface PrioridadStrategy {
    void ordenarTareas(List<Tarea> tareas);
}