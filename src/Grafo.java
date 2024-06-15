import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;


public class Grafo <Tipo> {
    private ArrayList<Vertice<Tipo>> vertices;
    private ArrayList<Aresta<Tipo>> arestas;

    public Grafo(){
        this.vertices = new ArrayList<Vertice<Tipo>>();
        this.arestas = new ArrayList<Aresta<Tipo>>();
    }
   

    public void adicionarVertice(Tipo dado){
        Vertice<Tipo> novoVertice = new Vertice<Tipo>(dado);
        this.vertices.add(novoVertice);
    }

    public void adicionarAresta(Double peso, Tipo DadoInicio, Tipo DadoSaida){
        Vertice<Tipo> inicio = this.getVertice(DadoInicio);
        Vertice<Tipo> fim = this.getVertice(DadoSaida);
        Aresta<Tipo> aresta = new Aresta<Tipo>(peso, inicio, fim);
        inicio.AdicionarArestaSaida(aresta);
        fim.AdicionarArestaEntrada(aresta);
        this.arestas.add(aresta);
    }

    public Vertice<Tipo> getVertice(Tipo dado){
        Vertice<Tipo> vertice = null;
        for(int i = 0; i < this.vertices.size(); i ++)
        {
            if(this.vertices.get(i).getDado().equals(dado)){
                vertice = this.vertices.get(i);
            }
        }
        return vertice;
    } 
    public static <Tipo> void CriarGrafo(Grafo<Tipo> grafo) {
        Scanner sc = new Scanner(System.in);
        String texto;

        System.out.println("Quantos vértices no grafo você quer adicionar?");
        int NumVertices = sc.nextInt();
        sc.nextLine(); // Limpar o buffer após a leitura do número inteiro

        System.out.println("Preencha o valor dos vértices:");
        for (int i = 0; i < NumVertices; i++) {
            texto = sc.nextLine();
            grafo.adicionarVertice((Tipo) texto);
        }

        System.out.println("Quantas arestas você quer adicionar?");
        int NumArestas = sc.nextInt();
        sc.nextLine(); // Limpar o buffer após a leitura do número inteiro

        System.out.println("Preencha o valor das arestas:");
        for (int j = 0; j < NumArestas; j++) {
            System.out.print("Origem: ");
            String origem = sc.nextLine();
            System.out.print("Saída: ");
            String saida = sc.nextLine();
            System.out.print("Peso: ");
            double peso = sc.nextDouble();
            sc.nextLine(); // Limpar o buffer após a leitura do número decimal

            grafo.adicionarAresta(peso, (Tipo) origem, (Tipo) saida);
        }

        System.out.println("Grafo criado!!!");
    }

    public void imprimirMatrizAdjacencia() {
        int tamanho = this.vertices.size();
        int[][] matrizAdjacencia = new int[tamanho][tamanho];

        // Preenchendo a matriz de adjacência com 0s
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                matrizAdjacencia[i][j] = 0;
            }
        }

        // Preenchendo a matriz de adjacência com 1s onde houver arestas
        for (Aresta<Tipo> aresta : this.arestas) {
            int indiceInicio = this.vertices.indexOf(aresta.getInicio());
            int indiceSaida = this.vertices.indexOf(aresta.getSaida());
            matrizAdjacencia[indiceInicio][indiceSaida] = 1;
            matrizAdjacencia[indiceSaida][indiceInicio] = 1; // Para grafo não direcionado
        }

        // Imprimindo a matriz de adjacência
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                System.out.print(matrizAdjacencia[i][j] + " ");
            }
            System.out.println();
        }
    }

    public Map<Tipo, ArrayList<Tipo>> criarListasAdjacencia() {
        Map<Tipo, ArrayList<Tipo>> listasAdjacencia = new HashMap<>();

        for (Vertice<Tipo> vertice : vertices) {
            ArrayList<Tipo> adjacentes = new ArrayList<>();
            for (Aresta<Tipo> aresta : vertice.getArestasSaida()) {
                Vertice<Tipo> destino = aresta.getSaida();
                adjacentes.add(destino.getDado());
            }
            listasAdjacencia.put(vertice.getDado(), adjacentes);
        }

        return listasAdjacencia;
    }

    public void imprimirGrafo(Grafo<Tipo> grafo) {
        int vertice = this.vertices.size();
        int arestas = this.arestas.size(); 

        int numMaxArestas = (vertice * (vertice - 1)) / 2;

        boolean isDenso = arestas > numMaxArestas / 2;

        if(isDenso) {
            System.out.println("O grafo é denso");
            imprimirMatrizAdjacencia();
        }else{
             Map<Tipo, ArrayList<Tipo>> listasAdjacencia = grafo.criarListasAdjacencia();
        System.out.println("Listas de Adjacência:");
            for (Entry<Tipo, ArrayList<Tipo>> entry : listasAdjacencia.entrySet()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
        }

    }

}
