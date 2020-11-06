public class ArvoreRB {
    public int chave;
    public enum Cor {
        VERMELHO, PRETO;
    };
    Cor cor;
    public ArvoreRB pai, direita, esquerda;

    public ArvoreRB(int chave) {
        this.chave = chave;
        this.cor = Cor.VERMELHO;
    }



}
