package br.edu.ifpb.wazbarber.model;

/**
 *
 * @author jozimar
 */
public enum TipoDeAtendente {

    MANICURE("Manicure"),
    BARBEIRO("Barbeiro"),
    CABELEIREIRO("Cabeleireiro");

    private final String label;

    private TipoDeAtendente(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
