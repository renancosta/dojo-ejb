package org.jcb.dojo.ejb.framework;
/**
 *
 * @author jean
 */
public class Exponenciacao extends Operacao {

    public Exponenciacao(Nodo esquerda, Nodo direita) {
        super(esquerda, direita);
    }

    @Override
    protected int executa(int esquerda, int direita) {
        return (int) Math.pow(esquerda,direita);
    }

    @Override
    protected String simbolo() {
        return "^";
    }

}
