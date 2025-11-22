package dominio;

import java.util.List;

/**
 * Estrategia concreta de ordenamiento.
 * Implementa la lógica para ordenar tareas según su nivel de complejidad.
 * @author Constantino Bekios
 * @author Luis Molina
 */
public class PrioridadComplejidadStrategy implements PrioridadStrategy {
    
    /**
     * Ordena la lista de tareas basándose en su complejidad (Alta, Media, Baja).
     * @param tareas Lista de tareas a ordenar.
     */
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
    
    /**
     * Convierte el string de complejidad en un valor numérico para comparación.
     * @param complejidad Texto de complejidad (Alta, Media, Baja).
     * @return Valor entero representativo (3=Alta, 2=Media, 1=Baja).
     */
    private int getComplexityValue(String complejidad) {
        if (complejidad == null) return 0;
        if (complejidad.equalsIgnoreCase("alta")) return 3;
        if (complejidad.equalsIgnoreCase("media")) return 2;
        if (complejidad.equalsIgnoreCase("baja")) return 1;
        return 0;
    }
}