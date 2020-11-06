public class ArvoreAvl {
    public int chave;
    public int altura;
    public ArvoreAvl direita, esquerda;

    public ArvoreAvl(int chave) {
        this.chave = chave;
        this.altura = 1;
    }

    void atualizaAltura(ArvoreAvl raiz) {
        raiz.altura = 1 + Math.max(altura(raiz.esquerda), altura(raiz.direita));
    }

    int altura(ArvoreAvl raiz) {
        return raiz == null ? 0 : raiz.altura;
    }

    int balanco(ArvoreAvl raiz) {
        return (raiz == null) ? 0 : altura(raiz.direita) - altura(raiz.esquerda);
    }

    ArvoreAvl filhoMaisADireita(ArvoreAvl raiz) {
        if(raiz.direita != null) {
            raiz = filhoMaisADireita(raiz.direita);
        }
        return raiz;
    }

}

