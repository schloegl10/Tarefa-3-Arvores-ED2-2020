public class RB {
    ArvoreRB Raiz;
    int Nivel = 0;
    int Rotacao = 0;
    int Rebalanco = 0;

    ArvoreRB buscar(int chave) {
        return buscarHelper(this.Raiz, chave);
    }
    ArvoreRB buscarHelper(ArvoreRB raiz, int chave) {
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

    void ler(ArvoreRB raiz) {
        if (raiz != null) {
            System.out.print(String.valueOf(raiz.chave));
            System.out.print("\n");
            ler(raiz.esquerda);
            ler(raiz.direita);
        }
    }

    private void fixDelete(ArvoreRB raiz) {
        this.Rebalanco++;
        ArvoreRB aux;
        while (raiz != Raiz && raiz.cor == ArvoreRB.Cor.PRETO) {
            if (raiz == raiz.pai.esquerda) {
                aux = raiz.pai.direita;
                if (aux.cor == ArvoreRB.Cor.VERMELHO) {
                    aux.cor = ArvoreRB.Cor.PRETO;
                    raiz.pai.cor = ArvoreRB.Cor.VERMELHO;
                    rotateLeft(raiz.pai);
                    aux = raiz.pai.direita;
                }

                if (aux.esquerda.cor == ArvoreRB.Cor.PRETO && aux.direita.cor == ArvoreRB.Cor.PRETO) {

                    aux.cor = ArvoreRB.Cor.VERMELHO;
                    raiz = raiz.pai;
                } else {
                    if (aux.direita.cor == ArvoreRB.Cor.PRETO) {

                        aux.esquerda.cor = ArvoreRB.Cor.PRETO;
                        aux.cor = ArvoreRB.Cor.VERMELHO;
                        rotateRight(aux);
                        aux = raiz.pai.direita;
                    }

                    aux.cor = raiz.pai.cor;
                    raiz.pai.cor = ArvoreRB.Cor.PRETO;
                    aux.direita.cor = ArvoreRB.Cor.PRETO;
                    rotateLeft(raiz.pai);
                    raiz = Raiz;
                }
            } else {
                aux = raiz.pai.esquerda;
                if (aux.cor == ArvoreRB.Cor.VERMELHO) {
                    aux.cor = ArvoreRB.Cor.PRETO;
                    raiz.pai.cor = ArvoreRB.Cor.VERMELHO;
                    rotateRight(raiz.pai);
                    aux = raiz.pai.esquerda;
                }

                if (aux.esquerda.cor == ArvoreRB.Cor.PRETO && aux.direita.cor == ArvoreRB.Cor.PRETO) {
                    aux.cor = ArvoreRB.Cor.VERMELHO;
                    raiz = raiz.pai;
                } else {
                    if (aux.esquerda.cor == ArvoreRB.Cor.PRETO ) {
                        aux.direita.cor = ArvoreRB.Cor.PRETO;
                        aux.cor = ArvoreRB.Cor.VERMELHO;
                        rotateLeft(aux);
                        aux = raiz.pai.esquerda;
                    }

                    aux.cor = raiz.pai.cor;
                    raiz.pai.cor = ArvoreRB.Cor.PRETO;
                    aux.esquerda.cor = ArvoreRB.Cor.PRETO;
                    rotateRight(raiz.pai);
                    raiz = Raiz;
                }
            }
        }
        raiz.cor = ArvoreRB.Cor.PRETO;
    }

    private void rbTransplant(ArvoreRB u, ArvoreRB v){
        if (u.pai == null) {
            Raiz = v;
        } else if (u == u.pai.esquerda){
            u.pai.esquerda = v;
        } else {
            u.pai.direita = v;
        }
        v.pai = u.pai;
    }

    private void deleteArvoreRBHelper(ArvoreRB node, int key) {
        this.Nivel++;
        ArvoreRB z = null;
        ArvoreRB x, y;
        while (node != null){
            if (node.chave == key) {
                z = node;
            }

            if (node.chave <= key) {
                node = node.direita;
            } else {
                node = node.esquerda;
            }
        }

        if (z == null) {
            System.out.println("Couldn't find key in the tree");
            return;
        }

        y = z;
        ArvoreRB.Cor yOriginalColor = y.cor;
        if (z.esquerda == null) {
            x = z.direita;
            rbTransplant(z, z.direita);
        } else if (z.direita == null) {
            x = z.esquerda;
            rbTransplant(z, z.esquerda);
        } else {
            y = minimum(z.direita);
            yOriginalColor = y.cor;
            x = y.direita;
            if (y.pai == z) {
                x.pai = y;
            } else {
                rbTransplant(y, y.direita);
                y.direita = z.direita;
                y.direita.pai = y;
            }

            rbTransplant(z, y);
            y.esquerda = z.esquerda;
            y.esquerda.pai = y;
            y.cor = z.cor;
        }
        if (yOriginalColor == ArvoreRB.Cor.PRETO){
            fixDelete(x);
        }
    }

    private void fixInsert(ArvoreRB k){
        this.Rebalanco++;
        ArvoreRB u;
        while (k.pai.cor == ArvoreRB.Cor.VERMELHO) {
            if (k.pai == k.pai.pai.direita) {
                u = k.pai.pai.esquerda; // uncle
                if (u == null) {
                    if (k == k.pai.esquerda) {
                        // case 3.2.2
                        k = k.pai;
                        rotateRight(k);
                    }
                    k.pai.cor = ArvoreRB.Cor.PRETO;
                    k.pai.pai.cor = ArvoreRB.Cor.VERMELHO;
                    rotateLeft(k.pai.pai);
                } else if (u.cor == ArvoreRB.Cor.VERMELHO) {
                    u.cor = ArvoreRB.Cor.PRETO;
                    k.pai.cor = ArvoreRB.Cor.PRETO;
                    k.pai.pai.cor = ArvoreRB.Cor.VERMELHO;
                    k = k.pai.pai;
                } else {
                    if (k == k.pai.esquerda) {
                        // case 3.2.2
                        k = k.pai;
                        rotateRight(k);
                    }
                    k.pai.cor = ArvoreRB.Cor.PRETO;
                    k.pai.pai.cor = ArvoreRB.Cor.VERMELHO;
                    rotateLeft(k.pai.pai);
                }
            } else {
                u = k.pai.pai.direita;
                if (u == null) {
                    if (k == k.pai.direita) {
                        k = k.pai;
                        rotateLeft(k);
                    }
                    k.pai.cor = ArvoreRB.Cor.PRETO;
                    k.pai.pai.cor = ArvoreRB.Cor.VERMELHO;
                    rotateRight(k.pai.pai);
                } else if (u.cor == ArvoreRB.Cor.VERMELHO) {
                    u.cor = ArvoreRB.Cor.PRETO;
                    k.pai.cor = ArvoreRB.Cor.PRETO;
                    k.pai.pai.cor = ArvoreRB.Cor.VERMELHO;
                    k = k.pai.pai;
                } else {
                    if (k == k.pai.direita) {
                        k = k.pai;
                        rotateLeft(k);
                    }
                    k.pai.cor = ArvoreRB.Cor.PRETO;
                    k.pai.pai.cor = ArvoreRB.Cor.VERMELHO;
                    rotateRight(k.pai.pai);
                }
            }
            if (k == Raiz) {
                break;
            }
        }
        Raiz.cor = ArvoreRB.Cor.PRETO;
    }

    private void printHelper(ArvoreRB Raiz, String indent, boolean last) {
        if (Raiz != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "     ";
            } else {
                System.out.print("L----");
                indent += "|    ";
            }

            String sColor = Raiz.cor == ArvoreRB.Cor.VERMELHO?"RED":"BLACK";
            System.out.println(Raiz.chave + "(" + sColor + ")");
            printHelper(Raiz.esquerda, indent, false);
            printHelper(Raiz.direita, indent, true);
        }
    }

    private ArvoreRB minimum(ArvoreRB node) {
        while (node.esquerda != null) {
            node = node.esquerda;
        }
        return node;
    }

    private ArvoreRB maximum(ArvoreRB node) {
        while (node.direita != null) {
            node = node.direita;
        }
        return node;
    }

    
    public ArvoreRB successor(ArvoreRB x) {
        if (x.direita != null) {
            return minimum(x.direita);
        }

        ArvoreRB y = x.pai;
        while (y != null && x == y.direita) {
            x = y;
            y = y.pai;
        }
        return y;
    }

    public ArvoreRB predecessor(ArvoreRB x) {
        if (x.esquerda != null) {
            return maximum(x.esquerda);
        }

        ArvoreRB y = x.pai;
        while (y != null && x == y.esquerda) {
            x = y;
            y = y.pai;
        }

        return y;
    }

    private void rotateLeft(ArvoreRB x) {
        this.Rotacao++;
        ArvoreRB y = x.direita;
        x.direita = y.esquerda;
        if (y.esquerda != null) {
            y.esquerda.pai = x;
        }
        y.pai = x.pai;
        if (x.pai == null) {
            this.Raiz = y;
        } else if (x == x.pai.esquerda) {
            x.pai.esquerda = y;
        } else {
            x.pai.direita = y;
        }
        y.esquerda = x;
        x.pai = y;
    }

    // rotate direita at node x
    private void rotateRight(ArvoreRB x) {
        this.Rotacao++;
        ArvoreRB y = x.esquerda;
        x.esquerda = y.direita;
        if (y.direita != null) {
            y.direita.pai = x;
        }
        y.pai = x.pai;
        if (x.pai == null) {
            this.Raiz = y;
        } else if (x == x.pai.direita) {
            x.pai.direita = y;
        } else {
            x.pai.esquerda = y;
        }
        y.direita = x;
        x.pai = y;
    }

    void inserir(int key) {
        this.Nivel++;
        ArvoreRB node = new ArvoreRB(key);
        node.pai = null;
        node.esquerda = null;
        node.direita = null;

        ArvoreRB y = null;
        ArvoreRB x = this.Raiz;

        while (x != null) {
            y = x;
            if (node.chave < x.chave) {
                x = x.esquerda;
            } else {
                x = x.direita;
            }
        }

        node.pai = y;
        if (y == null) {
            Raiz = node;
        } else if (node.chave < y.chave) {
            y.esquerda = node;
        } else {
            y.direita = node;
        }

        if (node.pai == null){
            node.cor = ArvoreRB.Cor.PRETO;
            return;
        }

        if (node.pai.pai == null) {
            return;
        }

        fixInsert(node);
    }

    public ArvoreRB getRoot(){
        return this.Raiz;
    }

    void remover(int chave) {
        deleteArvoreRBHelper(this.Raiz, chave);
    }
}
