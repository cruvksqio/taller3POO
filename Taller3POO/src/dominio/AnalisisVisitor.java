package dominio;

/**
 * Implementación concreta del patrón Visitor.
 * Define las operaciones de análisis específicas para cada tipo de tarea.
 * @author Constantino Bekios
 * @author Luis Molina
 */
public class AnalisisVisitor implements Visitor {
    
    /**
     * Analiza una tarea de tipo Bug.
     * Advierte sobre la criticidad del proyecto.
     * @param bug La instancia de la tarea tipo Bug a visitar.
     */
    @Override
    public void visit(Bug bug) {
        System.out.println("[VISITOR] Analizando Bug ID " + bug.getTaskID() + ": Afecta criticidad del proyecto. ¡Revisar urgente!");
    }

    /**
     * Analiza una tarea de tipo Feature.
     * Advierte sobre el impacto en la estimación de tiempo.
     * @param feature La instancia de la tarea tipo Feature a visitar.
     */
    @Override
    public void visit(Feature feature) {
        System.out.println("[VISITOR] Analizando Feature ID " + feature.getTaskID() + ": Impacta en la estimación de tiempo. Recalcular horas.");
    }

    /**
     * Analiza una tarea de tipo Documentacion.
     * Sugiere mejoras de calidad y ortografía.
     * @param doc La instancia de la tarea tipo Documentacion a visitar.
     */
    @Override
    public void visit(Documentacion doc) {
        System.out.println("[VISITOR] Analizando Doc ID " + doc.getTaskID() + ": Mejora la calidad del proyecto. Verificar ortografía.");
    }
}