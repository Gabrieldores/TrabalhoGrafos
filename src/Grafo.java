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
    ArrayList<Vertice<Tipo>> marcados = new ArrayList<>();
    ArrayList<Vertice<Tipo>> fila = new ArrayList<>();
    Map<Vertice<Tipo>, Integer> nivel = new HashMap<>();
    Map<Vertice<Tipo>, Vertice<Tipo>> pai = new HashMap<>();
    int iteracao = 0;

    Vertice<Tipo> inicial = this.vertices.get(0); // Vértice inicial para iniciar a busca
    marcados.add(inicial);
    fila.add(inicial);
    nivel.put(inicial, 0);
    pai.put(inicial, null);

    System.out.println("Busca em Largura a partir do vértice " + inicial.getDado() + ":");

    while (!fila.isEmpty()) {
        Vertice<Tipo> visitado = fila.remove(0);
        int nivelAtual = nivel.get(visitado);
        iteracao++;

        System.out.println("Vértice: " + visitado.getDado() + ", Nível: " + nivelAtual + ", Iteração: " + iteracao);

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
}
  









