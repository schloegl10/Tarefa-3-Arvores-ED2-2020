import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import  java.util.List;
public class Main {
    private static class Grafo {
        int aresta[][];
        int vertices[];
        int comprimento;
        int tamanho;
        int inicio;
        boolean chegouNoInicio = false;
        int ultimoPercorrido;
        int verticesPercorridos[];
        public Grafo(int tamanho, int inicio) {
            this.vertices = new int[tamanho];
            this.inicio = inicio;
            this.verticesPercorridos = new int[tamanho];
            this.aresta = new int[tamanho][tamanho];
            this.comprimento = 0;
            this.tamanho = tamanho;
            for(int cont = 0; cont < tamanho; cont++) {
                vertices[cont] = cont;
                verticesPercorridos[cont] = 0;
                for(int cont2 = 0; cont2 < tamanho; cont2++) {
                    aresta[cont][cont2] = 0;
                }
            }
        }
        public void adicionaAresta(int primeira, int segunda) {
            aresta[primeira][segunda] = 1;
            aresta[segunda][primeira] = 1;
        }
        public void percorreGrafo(int vertice) {
            verticesPercorridos[vertice] = 1;
            for(int cont = 0; cont < this.tamanho; cont++) {
                if(aresta[vertice][cont] == 1) {
                    if ((verticesPercorridos[cont] == 0)) {
                        chegouNoInicio = false;
                        this.comprimento++;
                        ultimoPercorrido = vertice;
                        percorreGrafo(cont);
                        if(!chegouNoInicio) {
                            this.comprimento++;
                        }
                    }  else if (cont == inicio && ultimoPercorrido != cont) {
                        this.comprimento++;
                        ultimoPercorrido = vertice;
                        chegouNoInicio = true;
                        percorreGrafo(cont);
                    }
                }
            }
        }
    }
    private static Scanner sc = new Scanner(System.in);
    public static void main(String args[]) {
        int quantiaTestes;
        int casaInicial;
        String linhaEntrada;
        List<String> valoresLinha;
        int numeroVertices;
        int numeroArestas;
        int primeiroVertice;
        int segundoVertice;
        Grafo grafo;
        quantiaTestes = Integer.parseInt(sc.nextLine());
        int resultados[] = new int[quantiaTestes];
        for(int teste = 0; teste < quantiaTestes; teste++) {
            casaInicial = Integer.parseInt(sc.nextLine());
            linhaEntrada = sc.nextLine();
            valoresLinha = Arrays.asList(linhaEntrada.split("\\s"));
            numeroVertices = Integer.parseInt(valoresLinha.get(0));
            numeroArestas = Integer.parseInt(valoresLinha.get(1));
            grafo = new Grafo(numeroVertices, casaInicial);
            for(int aresta = 0; aresta < numeroArestas; aresta++) {
                linhaEntrada = sc.nextLine();
                valoresLinha = Arrays.asList(linhaEntrada.split("\\s"));
                primeiroVertice = Integer.parseInt(valoresLinha.get(0));
                segundoVertice = Integer.parseInt(valoresLinha.get(1));
                grafo.adicionaAresta(primeiroVertice, segundoVertice);
            }
            grafo.percorreGrafo(casaInicial);
            resultados[teste] = grafo.comprimento;
        }
        for(int teste = 0; teste < quantiaTestes; teste++) {
            System.out.println(resultados[teste]);
        }
    }
}
