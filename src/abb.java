public class abb {
    ArvoreBinaria Raiz;
    int Nivel = 1;
    public void inserir(int chave) {
        this.Raiz = inserirHelper(this.Raiz, chave);
    }
    public ArvoreBinaria inserirHelper(ArvoreBinaria raiz, int chave) {
        if(raiz == null) {
            raiz = new ArvoreBinaria(chave);
        } else if (raiz.chave > chave) {
            raiz.esquerda = inserirHelper(raiz.esquerda, chave);
        } else if (raiz.chave < chave) {
            raiz.direita = inserirHelper(raiz.direita, chave);
        }
        this.Nivel++;
        return raiz;
    }
    public void remover(int chave) {
        removerHelper(this.Raiz, chave);
    }
    public ArvoreBinaria removerHelper(ArvoreBinaria raiz, int chave) {
        if(raiz == null) {
            this.Nivel++;
            return null;
        } else if (raiz.chave == chave) {
            this.Nivel++;
            return null;
        } else if (raiz.chave > chave) {
            raiz.esquerda = removerHelper(raiz.esquerda, chave);
        } else if (raiz.chave < chave) {
            raiz.direita = removerHelper(raiz.direita, chave);
        }
        this.Nivel++;
        return raiz;
    }
    public void buscar(int chave) {
        buscarHelper(this.Raiz, chave);
    }
    public int buscarHelper(ArvoreBinaria raiz, int chave) {
        if(raiz == null) {
            this.Nivel++;
            return 0;
        } else if (raiz.chave == chave) {
            System.out.printf(String.valueOf(chave));
        } else if (raiz.chave > chave) {
            buscarHelper(raiz.esquerda, chave);
        } else if (raiz.chave < chave) {
            buscarHelper(raiz.direita, chave);
        }
        this.Nivel++;
        return 0;
    }

    public void ler(ArvoreBinaria raiz) {
        if (raiz != null) {
            System.out.printf(String.valueOf(raiz.chave));
            ler(raiz.esquerda);
            ler(raiz.direita);
        }
    }
}