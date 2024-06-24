import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;


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

    public void imprimirGrafo(Grafo<Tipo> grafo) {
        int vertice = this.vertices.size();
        int arestas = this.arestas.size(); 

        int numMaxArestas = (vertice * (vertice - 1)) / 2;

        boolean isDenso = arestas > numMaxArestas / 2;

        if(isDenso) {
            System.out.println("O grafo é denso");
            imprimirMatrizAdjacencia();
        }else{
            System.out.println("O grafo é denso");
            Map<Tipo, ArrayList<Tipo>> listasAdjacencia = grafo.criarListasAdjacencia();
            System.out.println("Listas de Adjacência:");
            for (Map.Entry<Tipo, ArrayList<Tipo>> entry : listasAdjacencia.entrySet()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
        }

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

    public void imprimirArestaAdjacente() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual indice da aresta você deseja alterar? ");
        int indiceAresta = sc.nextInt();
        sc.nextLine(); 
        // aresta adjacente é que tem um vértice em comum 
        if (indiceAresta < 0 || indiceAresta >= this.arestas.size()) {
            System.out.println("Índice de aresta inválido.");
        }

        Aresta<Tipo> arestaSelecionada = this.arestas.get(indiceAresta);
        Vertice<Tipo> inicio = arestaSelecionada.getInicio();
        Vertice<Tipo> fim = arestaSelecionada.getSaida();

        System.out.println("Arestas adjacentes à aresta (" + inicio.getDado() + " -> " + fim.getDado() + "):");
        for (Aresta<Tipo> aresta : arestas) {
            if (aresta != arestaSelecionada && (aresta.getInicio().equals(inicio) || aresta.getInicio().equals(fim) || aresta.getSaida().equals(inicio) || aresta.getSaida().equals(fim))) {
                System.out.println(aresta.getInicio().getDado() + " -> " + aresta.getSaida().getDado());
            }
        }
    }
    public void imprimirArestasAdjacentes(Tipo dado) {
        Vertice<Tipo> vertice = getVertice(dado);
        if (vertice != null) {
            System.out.println("Arestas adjacentes ao vértice " + dado + ":");
            for (Aresta<Tipo> aresta : vertice.getArestasAdjacentes(vertice)) {
                System.out.println(aresta.getInicio().getDado() + " -> " + aresta.getSaida().getDado() + " (Peso: " + aresta.getPeso() + ")");
            }
        } else {
            System.out.println("Vértice " + dado + " não encontrado.");
        }
    }

  public void imprimirVerticesAdjacentes(Tipo dado) {
    Vertice<Tipo> vertice = getVertice(dado);
    if (vertice != null) {
        ArrayList<Vertice<Tipo>> verticesAdjacentes = vertice.getVerticesAdjacentes(vertice);
        System.out.println("Vértices adjacentes ao vértice " + dado + ":");
        for (Vertice<Tipo> adjacente : verticesAdjacentes) {
            System.out.println(adjacente.getDado());
        }
    } else {
        System.out.println("Vértice " + dado + " não encontrado.");
    }
}

public void imprimirArestasIncidentes(Tipo dado) {
    Vertice<Tipo> vertice = getVertice(dado);
    if (vertice != null) {
        ArrayList<Aresta<Tipo>> arestasIncidentes = vertice.getArestasIncidentes(vertice);
        System.out.println("Arestas incidentes ao vértice " + dado + ":");
        for (Aresta<Tipo> aresta : arestasIncidentes) {
            System.out.println(aresta.getInicio().getDado() + " -> " + aresta.getSaida().getDado() + " (Peso: " + aresta.getPeso() + ")");
        }
    } else {
        System.out.println("Vértice " + dado + " não encontrado.");
    }
}

public void imprimirVerticesIncidentes(Tipo dado) {
    Vertice<Tipo> vertice = getVertice(dado);
    if (vertice != null) {
        ArrayList<Vertice<Tipo>> verticesIncidentes = vertice.getVerticesIncidentes(vertice);
        System.out.println("Vértices incidentes ao vértice " + dado + ":");
        for (Vertice<Tipo> adjacente : verticesIncidentes) {
            System.out.println(adjacente.getDado());
        }
    } else {
        System.out.println("Vértice " + dado + " não encontrado.");
    }
}

public void imprimirGrauVertice(Tipo dado) {
    Vertice<Tipo> vertice = getVertice(dado);
    if (vertice != null) {
        int grau = vertice.grau(); // Chama o método grau() diretamente no objeto vertice
        System.out.println("Grau do vértice " + dado + ": " + grau);
    } else {
        System.out.println("Vértice " + dado + " não encontrado.");
    }
  }

  public boolean saoAdjacentes(Tipo dadoVertice1, Tipo dadoVertice2) {
    Vertice<Tipo> vertice1 = getVertice(dadoVertice1);
    Vertice<Tipo> vertice2 = getVertice(dadoVertice2);
    Boolean verifica = false;

    if (vertice1 != null && vertice2 != null) {
        // Verifica se há uma aresta de vertice1 para vertice2
        for (Aresta<Tipo> aresta : vertice1.getArestasSaida()) {
            if (aresta.getSaida().equals(vertice2)) {
                verifica = true;
                return verifica;
            }
        }
        // Verifica se há uma aresta de vertice2 para vertice1 (se o grafo for não-direcionado)
        for (Aresta<Tipo> aresta : vertice1.getArestasEntrada()) {
            if (aresta.getInicio().equals(vertice2)) {
                verifica = true;
                return verifica;
            }
        }

    }
        return verifica;
    }

    public boolean substituirPesoAresta(Tipo dadoInicio, Tipo dadoSaida, double novoPeso) {
        Vertice<Tipo> inicio = getVertice(dadoInicio);
        Vertice<Tipo> saida = getVertice(dadoSaida);
        boolean verifica = false;

        if (inicio != null && saida != null) {
            // Procura a aresta de inicio para saida
            for (Aresta<Tipo> aresta : inicio.getArestasSaida()) {
                if (aresta.getSaida().equals(saida)) {
                    aresta.setPeso(novoPeso);
                    verifica = true;
                }
            }
        }
        return verifica;
  }

  public void BuscaemLargura() {
    Scanner scanner = new Scanner(System.in);

    // Solicita ao usuário para escolher o vértice inicial
    System.out.println("Digite o dado do vértice inicial para a busca em largura:");
    Tipo dadoInicial = (Tipo) scanner.nextLine();
    Vertice<Tipo> inicial = getVertice(dadoInicial);

    // Verifica se o vértice inicial existe
    if (inicial == null) {
        System.out.println("Vértice " + dadoInicial + " não encontrado.");
        return;
    }

    ArrayList<Vertice<Tipo>> marcados = new ArrayList<>();
    ArrayList<Vertice<Tipo>> fila = new ArrayList<>();
    Map<Vertice<Tipo>, Integer> nivel = new HashMap<>();
    Map<Vertice<Tipo>, Vertice<Tipo>> pai = new HashMap<>();
    int iteracao = 0;

    marcados.add(inicial);
    fila.add(inicial);
    nivel.put(inicial, 0);
    pai.put(inicial, null);

    System.out.println("Busca em Largura a partir do vértice " + inicial.getDado() + ":");

    while (!fila.isEmpty()) {
        Vertice<Tipo> visitado = fila.remove(0);
        int nivelAtual = nivel.get(visitado);
        iteracao++;

        Vertice<Tipo> paiAtual = pai.get(visitado);
        String paiStr = (paiAtual != null) ? paiAtual.getDado().toString() : "null";

        System.out.println("Vértice: " + visitado.getDado() + ", Nível: " + nivelAtual + ", Pai: " + paiStr + ", Iteração: " + iteracao);

        for (Aresta<Tipo> aresta : visitado.getArestasSaida()) {
            Vertice<Tipo> proximo = aresta.getSaida();
            if (!marcados.contains(proximo)) {
                marcados.add(proximo);
                fila.add(proximo);
                nivel.put(proximo, nivelAtual + 1);
                pai.put(proximo, visitado);
            }
        }
    }
}

public void buscaEmProfundidade(Vertice<Tipo> verticeInicial) {
    ArrayList<Vertice<Tipo>> marcados = new ArrayList<>();
    Deque<Vertice<Tipo>> pilha = new ArrayDeque<>(); // Linha 313 onde o erro ocorre
    Map<Vertice<Tipo>, Integer> tempoDescoberta = new HashMap<>();
    Map<Vertice<Tipo>, Integer> tempoFinalizacao = new HashMap<>();
    int tempo = 0;

    pilha.push(verticeInicial);

    System.out.println("Busca em Profundidade a partir do vértice " + verticeInicial.getDado() + ":");

    while (!pilha.isEmpty()) {
        Vertice<Tipo> visitado = pilha.peek();
        if (!marcados.contains(visitado)) {
            marcados.add(visitado);
            tempo++;
            tempoDescoberta.put(visitado, tempo);
            System.out.println("Vértice: " + visitado.getDado() + ", Descoberta: " + tempoDescoberta.get(visitado));
        }

        boolean encontrouAdjacenteNaoMarcado = false;
        List<Vertice<Tipo>> adjacentes = visitado.getVerticesAdjacentes(visitado); // vertices adjacentes

        for (Vertice<Tipo> adj : adjacentes) {
            if (!marcados.contains(adj)) {
                pilha.push(adj);
                encontrouAdjacenteNaoMarcado = true;
                break;
            }
        }

        if (!encontrouAdjacenteNaoMarcado) {
            tempo++;
            tempoFinalizacao.put(visitado, tempo);
            pilha.pop();
            System.out.println("Vértice: " + visitado.getDado() + ", Finalização: " + tempoFinalizacao.get(visitado));
        }
    }

    System.out.println("\nTempos de descoberta e finalização:");
    for (Vertice<Tipo> vertice : tempoDescoberta.keySet()) {
        System.out.println("Vértice: " + vertice.getDado() + ", Descoberta: " + tempoDescoberta.get(vertice) + ", Finalização: " + tempoFinalizacao.get(vertice));
    }
}

public void trocarVertices(Tipo dado1, Tipo dado2) {
    Vertice<Tipo> vertice1 = getVertice(dado1);
    Vertice<Tipo> vertice2 = getVertice(dado2);

    if (vertice1 == null || vertice2 == null) {
        System.out.println("Um ou ambos os vértices não foram encontrados.");
        return;
    }

    // Trocar as referências nas arestas de entrada e saída dos vértices
    for (Aresta<Tipo> aresta : vertice1.getArestasEntrada()) {
        if (aresta.getInicio().equals(vertice1)) {
            aresta.setInicio(vertice2);
        }
    }
    for (Aresta<Tipo> aresta : vertice1.getArestasSaida()) {
        if (aresta.getSaida().equals(vertice1)) {
            aresta.setSaida(vertice2);
        }
    }

    for (Aresta<Tipo> aresta : vertice2.getArestasEntrada()) {
        if (aresta.getInicio().equals(vertice2)) {
            aresta.setInicio(vertice1);
        }
    }
    for (Aresta<Tipo> aresta : vertice2.getArestasSaida()) {
        if (aresta.getSaida().equals(vertice2)) {
            aresta.setSaida(vertice1);
        }
    }

}

public void algoritmoDijkstra() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o dado do vértice inicial para o algoritmo de Dijkstra:");
        Tipo dadoInicial = (Tipo) scanner.nextLine();
        Vertice<Tipo> inicial = getVertice(dadoInicial);

        if (inicial == null) {
            System.out.println("Vértice " + dadoInicial + " não encontrado.");
            return;
        }

        System.out.println("Digite o dado do vértice final para o algoritmo de Dijkstra:");
        Tipo dadoFinal = (Tipo) scanner.nextLine();
        Vertice<Tipo> finalVertice = getVertice(dadoFinal);

        if (finalVertice == null) {
            System.out.println("Vértice " + dadoFinal + " não encontrado.");
            return;
        }

        Map<Vertice<Tipo>, Double> distancias = new HashMap<>();
        Map<Vertice<Tipo>, Vertice<Tipo>> predecessores = new HashMap<>();
        PriorityQueue<Vertice<Tipo>> filaPrioridade = new PriorityQueue<>(Comparator.comparing(distancias::get));

        for (Vertice<Tipo> vertice : this.vertices) {
            if (vertice.equals(inicial)) {
                distancias.put(vertice, 0.0);
            } else {
                distancias.put(vertice, Double.POSITIVE_INFINITY);
            }
            filaPrioridade.add(vertice);
        }

        while (!filaPrioridade.isEmpty()) {
            Vertice<Tipo> atual = filaPrioridade.poll();
            for (Aresta<Tipo> aresta : atual.getArestasSaida()) {
                Vertice<Tipo> adjacente = aresta.getSaida();
                double novaDistancia = distancias.get(atual) + aresta.getPeso();
                if (novaDistancia < distancias.get(adjacente)) {
                    filaPrioridade.remove(adjacente);
                    distancias.put(adjacente, novaDistancia);
                    predecessores.put(adjacente, atual);
                    filaPrioridade.add(adjacente);
                }
            }
        }

        if (distancias.get(finalVertice) == Double.POSITIVE_INFINITY) {
            System.out.println("Não existe caminho do vértice " + dadoInicial + " ao vértice " + dadoFinal + ".");
        } else {
            System.out.println("Distância do vértice " + dadoInicial + " ao vértice " + dadoFinal + ": " + distancias.get(finalVertice));
            List<Vertice<Tipo>> caminho = new ArrayList<>();
            for (Vertice<Tipo> vertice = finalVertice; vertice != null; vertice = predecessores.get(vertice)) {
                caminho.add(vertice);
            }
            Collections.reverse(caminho);
            System.out.print("Caminho: ");
            for (Vertice<Tipo> vertice : caminho) {
                System.out.print(vertice.getDado() + " ");
            }
            System.out.println();
        }
    }

    public void algoritmoFloydWarshall() {
        int n = this.vertices.size();
        double[][] distancias = new double[n][n];
        Vertice<Tipo>[][] predecessores = new Vertice[n][n];

        // Inicialização das distâncias
        for (int i = 0; i < n; i++) {
            Arrays.fill(distancias[i], Double.POSITIVE_INFINITY);
            distancias[i][i] = 0;
        }

        // Configuração das arestas
        for (Aresta<Tipo> aresta : this.arestas) {
            int u = this.vertices.indexOf(aresta.getInicio());
            int v = this.vertices.indexOf(aresta.getSaida());
            distancias[u][v] = aresta.getPeso();
            predecessores[u][v] = aresta.getInicio();
        }

        // Algoritmo de Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distancias[i][j] > distancias[i][k] + distancias[k][j]) {
                        distancias[i][j] = distancias[i][k] + distancias[k][j];
                        predecessores[i][j] = predecessores[k][j];
                    }
                }
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o dado do vértice inicial para o algoritmo de Floyd-Warshall:");
        Tipo dadoInicial = (Tipo) scanner.nextLine();
        Vertice<Tipo> inicial = getVertice(dadoInicial);

        if (inicial == null) {
            System.out.println("Vértice " + dadoInicial + " não encontrado.");
            return;
        }

        int inicioIdx = this.vertices.indexOf(inicial);
        System.out.println("Menor distância do vértice " + dadoInicial + " para todos os outros vértices:");

        for (int i = 0; i < n; i++) {
            if (distancias[inicioIdx][i] == Double.POSITIVE_INFINITY) {
                System.out.println("Não existe caminho do vértice " + dadoInicial + " para o vértice " + this.vertices.get(i).getDado() + ".");
            } else {
                System.out.println("Distância para " + this.vertices.get(i).getDado() + ": " + distancias[inicioIdx][i]);
                System.out.print("Caminho: ");
                imprimirCaminho(inicial, this.vertices.get(i), predecessores);
                System.out.println();
            }
        }
    }

    private void imprimirCaminho(Vertice<Tipo> inicio, Vertice<Tipo> fim, Vertice<Tipo>[][] predecessores) {
        Stack<Vertice<Tipo>> caminho = new Stack<>();
        Vertice<Tipo> atual = fim;

        while (atual != null && !atual.equals(inicio)) {
            caminho.push(atual);
            int idxAtual = this.vertices.indexOf(atual);
            int idxPredecessor = this.vertices.indexOf(predecessores[this.vertices.indexOf(inicio)][idxAtual]);
            atual = this.vertices.get(idxPredecessor);
        }

        if (atual == null) {
            System.out.print("Nenhum caminho encontrado.");
            return;
        }

        System.out.print(inicio.getDado());
        while (!caminho.isEmpty()) {
            System.out.print(" -> " + caminho.pop().getDado());
        }
    }
}












