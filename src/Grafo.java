import java.util.ArrayList;
import java.util.Scanner;

public class Grafo <Tipo> {
    private ArrayList<Vertice<Tipo>> vertices;
    private ArrayList<Aresta<Tipo>> arestas;

    public Grafo(){
        this.vertices = new ArrayList<Vertice<Tipo>>();
        this.arestas = new ArrayList<Aresta<Tipo>>();
    }
     public static void CriarGrafo(Grafo grafo){
        Scanner sc = new Scanner(System.in); 
        String texto;
        System.out.println("Quantos vértices no grafo você quer adicionar?");
        int NumVertices = sc.nextInt();
        System.out.println("Quantas arestas você quer adicionar ?");
        int NumArestas = sc.nextInt();
        String Saida;
        double peso;

        System.out.println("Preencha o valor dos vértices");
        for(int i = 0;i < NumVertices; i ++){
            texto = sc.nextLine();
            grafo.adicionarVertice(texto);
        }

        System.out.println("Preencha o valor das arestas : ");
        for(int j = 0; j < NumArestas; j++){
            System.out.print("Origem :");
           texto = sc.nextLine();
            System.out.println("Saida");
            Saida = sc.nextLine();
            System.out.println("Peso");
            peso = sc.nextInt();

            grafo.adicionarAresta(peso, texto, Saida);
            
        }

        System.out.println("Grafo criado!!!");

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


}
