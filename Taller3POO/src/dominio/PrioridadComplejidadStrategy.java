package dominio;

import java.util.List;

public class PrioridadComplejidadStrategy implements PrioridadStrategy {
    @Override
    public void ordenarTareas(List<Tarea> tareas) {
        // Ordenamiento burbuja por complejidad (Alta > Media > Baja)
        for (int i = 0; i < tareas.size() - 1; i++) {
            for (int j = 0; j < tareas.size() - i - 1; j++) {
                int comp1 = getComplexityValue(tareas.get(j).getComplex());
                int comp2 = getComplexityValue(tareas.get(j + 1).getComplex());
                
                if (comp1 < comp2) {
                    // Intercambiar
                    Tarea temp = tareas.get(j);
                    tareas.set(j, tareas.get(j + 1));
                    tareas.set(j + 1, temp);
                }
            }
        }
        System.out.println("Tareas ordenadas por complejidad (Alta > Media > Baja)");
    }
    
    private int getComplexityValue(String complejidad) {
        if (complejidad == null) return 0;
        if (complejidad.equalsIgnoreCase("alta")) return 3;
        if (complejidad.equalsIgnoreCase("media")) return 2;
        if (complejidad.equalsIgnoreCase("baja")) return 1;
        return 0;
    }
}