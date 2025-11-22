package dominio;

/**
 * Interfaz que define el contrato para el patrón Visitor.
 * Permite definir operaciones que se realizan sobre elementos de la jerarquía de tareas
 * sin cambiar las clases de los elementos sobre los que opera.
 * @author Constantino Bekios
 * @author Luis Molina
 */
public interface Visitor {
    
    /**
     * Visita una tarea de tipo Bug.
     * @param bug La instancia de Bug a visitar.
     */
    void visit(Bug bug);
    
    /**
     * Visita una tarea de tipo Feature.
     * @param feature La instancia de Feature a visitar.
     */
    void visit(Feature feature);
    
    /**
     * Visita una tarea de tipo Documentacion.
     * @param doc La instancia de Documentacion a visitar.
     */
    void visit(Documentacion doc);
}