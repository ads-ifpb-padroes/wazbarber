package br.edu.ifpb.wazbarber.model.enums;

/**
 *
 * @author romulo
 */
public enum Dia {

    DOMINGO("Domingo"),
    SEGUNDA("Segunda-feira"),
    TERCA("Terça-feira"),
    QUARTA("Quarta-feira"),
    QUINTA("Quinta-feira"),
    SEXTA("Sexta-feira"),
    SABADO("Sábado");

    private final String label;

    private Dia(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
