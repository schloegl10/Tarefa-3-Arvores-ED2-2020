import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import  java.util.List;
public class Main {
    private static class Aresta {
        Vertice primeiroVertice;
        Vertice segundoVertice;
        double comprimento;
        public Aresta(Vertice primeiroVertice, Vertice segundoVertice) {
            this.primeiroVertice = primeiroVertice;
            this.segundoVertice = segundoVertice;
            int difX = (primeiroVertice.eixoX - segundoVertice.eixoX);
            int difY = (primeiroVertice.eixoY - segundoVertice.eixoY);
            comprimento = Math.sqrt((difX*difX) + (difY*difY))/100;
        }
    }
    private static class Vertice {
        int eixoX;
        int eixoY;
        ArrayList<Aresta> arestas;
        public Vertice(int eixoX, int eixoY) {
            this.eixoX = eixoX;
            this.eixoY = eixoY;
            this.arestas = new ArrayList<Aresta>();
        }
        public void adicionaAresta(Vertice outroVertice) {
            arestas.add(new Aresta(this, outroVertice));
        }
    }
    private static class Grafo {
        ArrayList<Vertice> vertices;
        ArrayList<Integer> tamanhos = new ArrayList<Integer>();
        public Grafo() {
            this.vertices = new ArrayList<Vertice>();
        }

        public void adicionaVertice(int eixoX, int eixoY) {
            vertices.add(new Vertice(eixoX, eixoY));
        }


        public double calculaComprimento() {
            Aresta menorAresta = vertices.get(0).arestas.get(0);
            ArrayList<Aresta> arestasMenores = new ArrayList<Aresta>();
            double comprimentoTotal = 0;
            ArrayList<Vertice> verticesPreenchidos = new ArrayList<Vertice>();
            ArrayList<Vertice> verticesTemp = vertices;
            boolean parar = false;
            boolean contemPrimeiro1 = false;
            boolean contemSegundo1 = false;
            boolean contemPrimeiro2 = false;
            boolean contemSegundo2 = false;
            int vetorMenorAresta = 0;
            for(int cont = 0; cont < vertices.size(); cont++) {
                for(int cont2 = 0; cont < vertices.get(cont).arestas.size(); cont2++) {
                    if (menorAresta.comprimento > vertices.get(cont).arestas.get(cont2).comprimento) {
                        menorAresta = vertices.get(cont).arestas.get(cont2);
                        vetorMenorAresta = cont;
                    }
                }
            }
            arestasMenores.add(menorAresta);
            vertices.get(vetorMenorAresta).arestas.remove(menorAresta);
            if(vertices.get(vetorMenorAresta).arestas.size() == 0) {
                verticesTemp.remove(vertices.get(vetorMenorAresta));
            }
            verticesPreenchidos.add(menorAresta.primeiroVertice);
            verticesPreenchidos.add(menorAresta.segundoVertice);
            for(int cont = 1; cont < vertices.size() - 1; cont++) {
                parar = false;
                while(!parar) {
                    menorAresta = null;
                    if (!(verticesTemp.size() >= 1)) {
                        parar = true;
                    }
                    for(int cont2 = 0; cont2 < arestasTemp.size(); cont2++) {
                        contemPrimeiro2 = false;
                        contemSegundo2 = false;
                        for(int cont3 = 0; cont3 < verticesPreenchidos.size(); cont3++) {
                            if(arestas.get(cont2).primeiroVertice == verticesPreenchidos.get(cont3)) {
                                contemPrimeiro2 = true;
                            }
                            if(arestas.get(cont2).segundoVertice == verticesPreenchidos.get(cont3)) {
                                contemSegundo2 = true;
                            }
                        }
                        if(contemPrimeiro2 || contemSegundo2) {
                            if(menorAresta == null) {
                                contemPrimeiro1 = contemPrimeiro2;
                                contemSegundo1 = contemSegundo2;
                                menorAresta = arestas.get(cont2);
                            } else if (menorAresta.comprimento > arestas.get(cont2).comprimento) {
                                contemPrimeiro1 = contemPrimeiro2;
                                contemSegundo1 = contemSegundo2;
                                menorAresta = arestas.get(cont2);
                            }
                        }
                    }
                    if(menorAresta != null) {
                        if (!(contemPrimeiro1 && contemSegundo1)) {
                            parar = true;
                            arestasMenores.add(menorAresta);
                            if (!contemPrimeiro1) {
                                verticesPreenchidos.add(menorAresta.primeiroVertice);
                            }
                            if (!contemSegundo1) {
                                verticesPreenchidos.add(menorAresta.segundoVertice);
                            }
                            arestasTemp.remove(menorAresta);
                        } else {
                            parar = false;
                            arestasTemp.remove(menorAresta);
                        }
                    }
                }
            }
            for (int cont = 0; cont < arestasMenores.size(); cont++) {
                comprimentoTotal = comprimentoTotal + arestasMenores.get(cont).comprimento;
            }
            return comprimentoTotal;
        }
    }
    private static Scanner sc = new Scanner(System.in);
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public static void main(String args[]) {
        int quantiaTestes;
        quantiaTestes = Integer.parseInt(sc.nextLine());
        double resultados[] = new double[quantiaTestes];
        Grafo grafo;
        String linhaEntrada;
        List<String> valoresLinha;
        int eixoX;
        int eixoY;
        int numeroPessoas;
        for(int teste = 0; teste < quantiaTestes; teste++) {
            numeroPessoas = Integer.parseInt(sc.nextLine());
            grafo = new Grafo();
            for(int aresta = 0; aresta < numeroPessoas; aresta++) {
                linhaEntrada = sc.nextLine();
                valoresLinha = Arrays.asList(linhaEntrada.split("\\s"));
                eixoX = Integer.parseInt(valoresLinha.get(0));
                eixoY = Integer.parseInt(valoresLinha.get(1));
                grafo.adicionaVertice(eixoX, eixoY);
            }
            for(int cont = 0; cont < numeroPessoas; cont++) {
                for(int cont2 = cont+1; cont2 < numeroPessoas; cont2++) {
                    grafo.vertices.get(cont).adicionaAresta(grafo.vertices.get(cont2));
                }
            }
            if(grafo.vertices.size() > 1) {
                resultados[teste] = grafo.calculaComprimento();
            } else {
                resultados[teste] = 0.0;
            }
        }
        String resposta;
        for(int teste = 0; teste < quantiaTestes; teste++) {
            resposta = " " + df2.format(resultados[teste])+" ";
            resposta = resposta.replace(",",".");
            resposta = resposta.replaceAll("\\.([0-9]) ", ".$10");
            resposta = resposta.replaceAll("\\.([0-9][0-9]) ", ".$1");
            resposta = resposta.replaceAll(" ([0-9]*) ", "$1.00");
            resposta = resposta.replaceAll(" ", "");
            System.out.println(resposta);
        }
    }
}
