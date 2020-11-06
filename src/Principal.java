public class Principal {
    private enum Operacao {INSERIR, REMOVER, BUSCAR};
    private static int[] criaLista(int tamanho) {
        int[] lista = new int[tamanho];
        int novo;
        boolean valorJaExiste;
        for(int cont = 0; cont < tamanho; cont++) {
            valorJaExiste = true;
            while(valorJaExiste) {
                valorJaExiste = false;
                novo = (int) Math.round(Math.random()*1000000000);
                for(int cont2 = 0; cont2<cont; cont2++) {
                    if(lista[cont2] == novo) {
                        valorJaExiste = true;
                        break;
                    }
                }
                if (!valorJaExiste) {
                    lista[cont] = novo;
                }
            }
        }
        return lista;
    }

    private static void testaABB(int quantidade, Operacao operacao) {
        abb binariabusca = new abb();
        int[] lista;
        long startTime;
        long endTime;
        long tempoTotal = 0;
        double tempoMedio;
        long niveisTotal = 0;
        double niveisMedio;
        System.out.println("---------------" + operacao + " " + quantidade + "----------------");
        for(int tentativa = 0; tentativa < 30; tentativa++) {
            lista = criaLista(quantidade);
            for(int cont = 0; cont < quantidade;cont++) {
                binariabusca.inserir(lista[cont]);
            }
            binariabusca.Nivel = 0;
            startTime = System.nanoTime();
            if (operacao == Operacao.INSERIR) {
                binariabusca.inserir((int) Math.round(Math.random()*100000000));
            } else if (operacao == Operacao.REMOVER) {
                binariabusca.remover(lista[(int) Math.round(Math.random()*quantidade/10)]);
            } else {
                binariabusca.buscar(lista[(int) Math.round(Math.random()*quantidade/10)]);
            }

            endTime = System.nanoTime();
            tempoTotal = tempoTotal + (endTime - startTime);
            System.out.println("Tempo: " + (endTime-startTime) + "nanosec");
            System.out.println("Niveis: " + binariabusca.Nivel);
            niveisTotal = niveisTotal +binariabusca.Nivel;
        }
        niveisMedio = (double)niveisTotal / 30;
        tempoMedio = (double)tempoTotal / 30;
        System.out.println("Tempo Medio: " + (tempoMedio) + "nanosec");
        System.out.println("Niveis Media: " + (niveisMedio));
    }

    private static void testaAVL(int quantidade, Operacao operacao) {
        AVL avl = new AVL();
        int[] lista;
        long startTime;
        long endTime;
        long tempoTotal = 0;
        double tempoMedio;
        long niveisTotal = 0;
        double niveisMedio;
        long rotacoesTotal = 0;
        double rotacoesMedio;
        long rebalancoTotal = 0;
        double rebalancoMedio;
        System.out.println("---------------" + operacao + " " + quantidade + "----------------");
        for(int tentativa = 0; tentativa < 30; tentativa++) {
            lista = criaLista(quantidade);
            for(int cont = 0; cont < quantidade;cont++) {
                avl.inserir(lista[cont]);
            }
            avl.Nivel = 0;
            avl.Rebalanco = 0;
            avl.Rotacao = 0;
            startTime = System.nanoTime();
            if (operacao == Operacao.INSERIR) {
                avl.inserir((int) Math.round(Math.random()*100000000));
            } else if (operacao == Operacao.REMOVER) {
                avl.remover(lista[(int) Math.round(Math.random()*quantidade/10)]);
            } else {
                avl.buscar(lista[(int) Math.round(Math.random()*quantidade/10)]);
            }

            endTime = System.nanoTime();
            tempoTotal = tempoTotal + (endTime - startTime);
            System.out.println("Tempo: " + (endTime-startTime) + "nanosec");
            System.out.println("Niveis: " + avl.Nivel);
            System.out.println("Rotacao: " + avl.Rotacao);
            System.out.println("Rebalanco: " + avl.Rebalanco);
            niveisTotal = niveisTotal +avl.Nivel;
            rotacoesTotal = rotacoesTotal +avl.Rotacao;
            rebalancoTotal = rebalancoTotal +avl.Rebalanco;
        }
        niveisMedio = (double)niveisTotal / 30;
        tempoMedio = (double)tempoTotal / 30;
        rebalancoMedio = (double)rebalancoTotal / 30;
        rotacoesMedio = (double)rotacoesTotal / 30;
        System.out.println("Tempo Medio: " + (tempoMedio) + "nanosec");
        System.out.println("Niveis Media: " + (niveisMedio));
        System.out.println("Rotações Media: " + (rotacoesMedio));
        System.out.println("Rebalancos Media: " + (rebalancoMedio));
    }

    private static void testaRB(int quantidade, Operacao operacao) {
        RB rubro = new RB();
        int[] lista;
        long startTime;
        long endTime;
        long tempoTotal = 0;
        double tempoMedio;
        long niveisTotal = 0;
        double niveisMedio;
        long rotacoesTotal = 0;
        double rotacoesMedio;
        long rebalancoTotal = 0;
        double rebalancoMedio;
        System.out.println("---------------" + operacao + " " + quantidade + "----------------");
        for(int tentativa = 0; tentativa < 30; tentativa++) {
            lista = criaLista(quantidade);
            for(int cont = 0; cont < quantidade;cont++) {
                rubro.inserir(lista[cont]);
            }
            rubro.Nivel = 0;
            rubro.Rebalanco = 0;
            rubro.Rotacao = 0;
            startTime = System.nanoTime();
            if (operacao == Operacao.INSERIR) {
                rubro.inserir((int) Math.round(Math.random()*100000000));
            } else if (operacao == Operacao.REMOVER) {
                rubro.remover(lista[(int) Math.round(Math.random()*quantidade/10)]);
            } else {
                rubro.buscar(lista[(int) Math.round(Math.random()*quantidade/10)]);
            }

            endTime = System.nanoTime();
            tempoTotal = tempoTotal + (endTime - startTime);
            System.out.println("Tempo: " + (endTime-startTime) + "nanosec");
            System.out.println("Niveis: " + rubro.Nivel);
            System.out.println("Rotacao: " + rubro.Rotacao);
            System.out.println("Rebalanco: " + rubro.Rebalanco);
            niveisTotal = niveisTotal +rubro.Nivel;
            rotacoesTotal = rotacoesTotal +rubro.Rotacao;
            rebalancoTotal = rebalancoTotal +rubro.Rebalanco;
        }
        niveisMedio = (double)niveisTotal / 30;
        tempoMedio = (double)tempoTotal / 30;
        rebalancoMedio = (double)rebalancoTotal / 30;
        rotacoesMedio = (double)rotacoesTotal / 30;
        System.out.println("Tempo Medio: " + (tempoMedio) + "nanosec");
        System.out.println("Niveis Media: " + (niveisMedio));
        System.out.println("Rotações Media: " + (rotacoesMedio));
        System.out.println("Rebalancos Media: " + (rebalancoMedio));
    }

    public static void main(String[] args) {
        // ABB
        testaABB(100, Operacao.INSERIR);
        testaABB(1000, Operacao.INSERIR);
        testaABB(100000, Operacao.INSERIR);
        testaABB(100, Operacao.REMOVER);
        testaABB(1000, Operacao.REMOVER);
        testaABB(100000, Operacao.REMOVER);
        testaABB(100, Operacao.BUSCAR);
        testaABB(1000, Operacao.BUSCAR);
        testaABB(100000, Operacao.BUSCAR);
        //AVL
        testaAVL(100, Operacao.INSERIR);
        testaAVL(1000, Operacao.INSERIR);
        testaAVL(100000, Operacao.INSERIR);
        testaAVL(100, Operacao.REMOVER);
        testaAVL(1000, Operacao.REMOVER);
        testaAVL(100000, Operacao.REMOVER);
        testaAVL(100, Operacao.BUSCAR);
        testaAVL(1000, Operacao.BUSCAR);
        testaAVL(100000, Operacao.BUSCAR);
        //RB
        testaRB(100, Operacao.INSERIR);
        testaAVL(1000, Operacao.INSERIR);
        testaAVL(100000, Operacao.INSERIR);
        testaAVL(100, Operacao.REMOVER);
        testaAVL(1000, Operacao.REMOVER);
        testaAVL(100000, Operacao.REMOVER);
        testaAVL(100, Operacao.BUSCAR);
        testaAVL(1000, Operacao.BUSCAR);
        testaAVL(100000, Operacao.BUSCAR);

    }
}