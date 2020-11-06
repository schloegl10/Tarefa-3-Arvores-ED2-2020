class AVL {
    ArvoreAvl Raiz;
    int Nivel = 0;
    int Rotacao = 0;
    int Rebalanco = 0;
    void inserir(int chave) {
        this.Raiz = inserirHelper(this.Raiz, chave);
    }
    private ArvoreAvl inserirHelper(ArvoreAvl raiz, int chave) {
        this.Nivel++;
        if(raiz == null) {
            return new ArvoreAvl(chave);
        } else if (raiz.chave > chave) {
            raiz.esquerda = inserirHelper(raiz.esquerda, chave);
        } else if (raiz.chave < chave) {
            raiz.direita = inserirHelper(raiz.direita, chave);
        }
        raiz.atualizaAltura(raiz);
        return rebalancear(raiz);
    }

    void remover(int chave) {
        this.Raiz = removerHelper(this.Raiz, chave);
    }

    private ArvoreAvl removerHelper(ArvoreAvl raiz, int chave) {
        this.Nivel++;
        if(raiz == null) {
            return null;
        } else if (raiz.chave == chave) {
            if (raiz.direita == null || raiz.esquerda == null) {
                raiz = (raiz.esquerda == null) ? raiz.direita : raiz.esquerda;
            } else {
                ArvoreAvl filhoMaisADireita = raiz.filhoMaisADireita(raiz.esquerda);
                raiz.chave = filhoMaisADireita.chave;
                raiz.esquerda = removerHelper(raiz.esquerda, raiz.chave);
            }
        } else if (raiz.chave > chave) {
            raiz.esquerda = removerHelper(raiz.esquerda, chave);
        } else {
            raiz.direita = removerHelper(raiz.direita, chave);
        }
        if (raiz != null) {
            raiz.atualizaAltura(raiz);
            raiz = rebalancear(raiz);
        }
        return raiz;

    }

    void buscar(int chave) {
        buscarHelper(this.Raiz, chave);
    }
    private ArvoreAvl buscarHelper(ArvoreAvl raiz, int chave) {
        this.Nivel++;
        if(raiz == null) {
            return null;
        } else if (raiz.chave == chave) {
            return raiz;
        } else if (raiz.chave > chave) {
            return buscarHelper(raiz.esquerda, chave);
        } else {
            return buscarHelper(raiz.direita, chave);
        }
    }

//    private void ler(ArvoreAvl raiz) {
//        if (raiz != null) {
//            System.out.print(String.valueOf(raiz.chave) + " " + String.valueOf(raiz.altura));
//            System.out.print("\n");
//            ler(raiz.esquerda);
//            ler(raiz.direita);
//        }
//    }

    private ArvoreAvl rotateLeft(ArvoreAvl y) {
        this.Rotacao++;
        ArvoreAvl x = y.direita;
        ArvoreAvl z = x.esquerda;
        x.esquerda = y;
        y.direita = z;
        y.atualizaAltura(y);
        x.atualizaAltura(x);
        return x;
    }

    private ArvoreAvl rotateRight(ArvoreAvl y) {
        this.Rotacao++;
        ArvoreAvl x = y.esquerda;
        ArvoreAvl z = x.direita;
        x.direita = y;
        y.esquerda = z;
        y.atualizaAltura(y);
        x.atualizaAltura(x);
        return x;
    }

    private ArvoreAvl rebalancear(ArvoreAvl raiz) {
        this.Rebalanco++;
        raiz.balanco(raiz);
        int balance = raiz.balanco(raiz);
        if (balance > 1) {
            if (raiz.altura(raiz.direita.direita) >= raiz.altura(raiz.direita.esquerda)) {
                raiz = rotateLeft(raiz);
            } else {
                raiz.direita = rotateRight(raiz.direita);
                raiz = rotateLeft(raiz);
            }
        } else if (balance < -1) {
            if (raiz.altura(raiz.esquerda.esquerda) >= raiz.altura(raiz.esquerda.direita))
                raiz = rotateRight(raiz);
            else {
                raiz.esquerda = rotateLeft(raiz.esquerda);
                raiz = rotateRight(raiz);
            }
        }
        return raiz;
    }
}
